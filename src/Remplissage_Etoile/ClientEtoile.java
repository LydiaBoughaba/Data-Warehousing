package Remplissage_Etoile;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class ClientEtoile {
	
	Connection conn;
	Statement stat;
	ResultSet rs;
	
	public void Creation() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("Create Table IF NOT EXISTS ClientDim ("
				+ "client_id int PRIMARY KEY,"
				+ "birth_date date);");
		stat.close();
	}
	
	public void Suppression() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("DROP TABLE IF EXISTS ClientDim CASCADE;");
		stat.close();
	}
	
	public void Remplissage() throws SQLException, FileNotFoundException, ParseException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres", "root");
		stat = conn.createStatement();
		
	    rs = stat.executeQuery("SELECT client_id, birth_date FROM client");
	    
	    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
	    stat = conn.createStatement();
	    
	    while(rs.next()) {
		    stat.executeUpdate("INSERT INTO ClientDim (client_id, birth_date) "
		    		+ "VALUES("+rs.getInt("client_id")+", '"+rs.getDate("birth_date")+"');");
	    }
	    stat.close();
	}
}
