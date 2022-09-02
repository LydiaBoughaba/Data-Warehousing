package Remplissage_Etoile;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DistrictEtoile {
	
	Connection conn;
	Statement stat;
	ResultSet rs;
	
	public void Creation() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("Create Table IF NOT EXISTS DistrictDim ("
				+ "district_id int PRIMARY KEY,"
				+ "A2 varchar,"
				+ "A3 varchar,"
				+ "A4 int,"
				+ "A5 int,"
				+ "A6 int,"
				+ "A7 int,"
				+ "A8 int,"
				+ "A9 int,"
				+ "A10 decimal,"
				+ "A11 int,"
				+ "A12 decimal,"
				+ "A13 decimal,"
				+ "A14 int,"
				+ "A15 int,"
				+ "A16 int);");
		stat.close();
	}
	
	public void Suppression() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("DROP TABLE IF EXISTS DistrictDim CASCADE;");
		stat.close();
	}
	
	public void Remplissage() throws SQLException, FileNotFoundException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres", "root");
		stat = conn.createStatement();

	    rs = stat.executeQuery("SELECT * FROM district");

	    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
	    stat = conn.createStatement();
	    
		while (rs.next()) {
		    stat.executeUpdate("INSERT INTO DistrictDim (district_id, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) "
		    		+ "VALUES("+rs.getInt("district_id")+",'"+rs.getString("a2")+"','"+rs.getString("a3")+"',"+rs.getInt("a4")+","+rs.getInt("a5")+","+rs.getInt("a6")+","+rs.getInt("a7")+","+rs.getInt("a8")+","+rs.getInt("a9")+","+rs.getFloat("a10")+","+rs.getInt("a11")+","+rs.getFloat("a12")+","+rs.getFloat("a13")+","+rs.getInt("a14")+","+rs.getInt("a15")+","+rs.getInt("a16")+");");
		}
		stat.close();
	}
}
