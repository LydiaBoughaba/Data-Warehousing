package Remplissage_Etoile;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountEtoile {
	
	Connection conn;
	Statement stat;
	ResultSet rs;
	
	public void Creation() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("Create Table IF NOT EXISTS AccountDim ("
				+ "accountDim_id int PRIMARY KEY,"
				+ "account_id int,"
				+ "account_date date,"
				+ "disp_id int,"
				+ "disp_type varchar,"
				+ "card_id int,"
				+ "card_type varchar,"
				+ "card_issued date);");
		stat.close();
	}
	
	public void Suppression() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("DROP TABLE IF EXISTS AccountDim CASCADE;");
		stat.close();
	}
	
	public void Remplissage() throws SQLException, FileNotFoundException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres", "root");
		stat = conn.createStatement();

	    rs = stat.executeQuery("SELECT account.account_id, account.frequency, account.date, disp.disp_id, disp.type AS disp_type, card.card_id, card.type AS card_type, card.issued "
	    		+ "FROM ((account "
	    		+ "LEFT JOIN disp ON account.account_id = disp.account_id) "
	    		+ "LEFT JOIN card ON card.disp_id = disp.disp_id);");
	    
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();

		int id_counter = 0;
		String disp_id, card_id;
		String disp_type, card_type, issued;
		while (rs.next()) {
			id_counter ++;
			
			disp_id = rs.getInt("disp_id") + "";
			if(disp_id.equals("0"))
				disp_id = null;
			
			disp_type = rs.getString("disp_type");
			if(disp_type != null)
				disp_type = "'"+disp_type+"'";
			
			card_id = rs.getInt("card_id") + "";
			if(card_id.equals("0"))
				card_id = null;
			
			card_type = rs.getString("card_type");
			if(card_type != null)
				card_type = "'"+card_type+"'";

			if(rs.getDate("issued") == null)
				issued = null;
			else
				issued = "'"+rs.getDate("issued")+"'";
			
			stat.executeUpdate("INSERT INTO AccountDim (accountDim_id, account_id, account_date, disp_id, disp_type, card_id, card_type, card_issued) "
					+ "VALUES ("+id_counter+","+rs.getInt("account_id")+",'"+rs.getDate("date")+"',"+disp_id+","+disp_type+","+card_id+","+card_type+","+issued+");");
		}
		stat.close();
	}
}
