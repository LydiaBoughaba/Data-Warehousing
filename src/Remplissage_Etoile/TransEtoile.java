package Remplissage_Etoile;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Interface_graphique.Interface;

public class TransEtoile {
	
	Connection conn, conn2;
	Statement stat, stat2;
	ResultSet rs;
	
	public void Creation() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("Create Table IF NOT EXISTS TransFact ("
				+ "accountDim_id int REFERENCES AccountDim(accountDim_id),"
				+ "district_id int REFERENCES DistrictDim(district_id),"
				+ "client_id int REFERENCES ClientDim(client_id),"
				+ "date_id int REFERENCES DateDim(date_id),"
				+ "amount int,"
				+ "balance int,"
				+ "total_credits decimal,"
				+ "total_debits decimal,"
				+ "days_below_min_balance int,"
				+ "PRIMARY KEY(accountDim_id, client_id, district_id, date_id));");
		stat.close();
	}
	
	public void Suppression() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("DROP TABLE IF EXISTS TransFact CASCADE;");
		stat.close();
	}
	
	public void Remplissage() throws SQLException, FileNotFoundException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres", "root");
		stat = conn.createStatement();
	    
		conn2 = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat2 = conn2.createStatement();
		
	    rs = stat.executeQuery("SELECT DISTINCT COUNT(*) AS nb_rows "
	    		+ "FROM ((trans "
	    		+ "INNER JOIN account ON trans.account_id = account.account_id) "
	    		+ "INNER JOIN client ON account.district_id = client.district_id);");
	    rs.next();
		int nb_rows = rs.getInt("nb_rows");

		System.out.println("avancement du remplissage de la table TransFact");
		int skip = 0;
		final int take = 6000; // a changer par rapport au performances
		while(skip < nb_rows) {
		    rs = stat.executeQuery("SELECT DISTINCT trans.account_id, account.district_id, client.client_id, trans.date, trans.amount, trans.balance "
		    		+ "FROM ((trans "
		    		+ "INNER JOIN account ON trans.account_id = account.account_id) "
		    		+ "INNER JOIN client ON account.district_id = client.district_id) "
		    		+ "OFFSET ("+skip+") ROWS FETCH NEXT ("+take+") ROWS ONLY;");
			
			float total_credits = 0;
			float total_debits = 0;
			int days_below = 0;

		    SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
			while (rs.next()) {
			    String strDate = formatter.format(rs.getDate("date"));

				stat2.executeUpdate("INSERT INTO TransFact (accountDim_id, district_id, client_id, date_id, amount, balance, total_credits, total_debits, days_below_min_balance) "
						+ "VALUES ("+rs.getInt("account_id")+","+rs.getInt("district_id")+","+rs.getInt("client_id")+","+strDate+","+rs.getInt("amount")+","+rs.getInt("balance")+","+total_credits+","+total_debits+","+days_below+");");
				skip++;
			}
			Interface.notify(skip+"/"+nb_rows);
		}
		stat.close();
	}
}
