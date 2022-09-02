package Remplissage_Etoile;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateEtoile {
	
	Connection conn;
	Statement stat;
	ResultSet rs;
	
	public void Creation() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("Create Table IF NOT EXISTS DateDim ("
				+ "date_id int PRIMARY KEY,"
				+ "day int,"
				+ "day_week varchar,"
				+ "calendar_week int,"
				+ "calendar_month int,"
				+ "calendar_quarter int,"
				+ "calendar_year int);");
		stat.close();
	}
	
	public void Suppression() throws SQLException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();
		
		stat.executeUpdate("DROP TABLE IF EXISTS DateDim CASCADE;");
		stat.close();
	}
	
	public void Remplissage() throws SQLException, FileNotFoundException {
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres", "root");
		stat = conn.createStatement();

		// --- get min and max date --- //
	    rs = stat.executeQuery("SELECT MAX(date) AS max_date, MIN(date) AS min_date FROM trans");
	    rs.next();
	    Calendar current_date = Calendar.getInstance();
	    current_date.setTime(rs.getDate("min_date"));
	    Date max_date = rs.getDate("max_date");
	    // ---------------------------- //
	    
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Financial_DataWarehouse", "postgres", "root");
		stat = conn.createStatement();

	    SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		while(current_date.getTime().compareTo(max_date) <= 0) {
		    String strDate = formatter.format(current_date.getTime());
		    int quarter = (current_date.get(Calendar.MONTH) / 3) + 1;

			stat.executeUpdate("INSERT INTO DateDim (date_id, day, day_week, calendar_week, calendar_month, calendar_quarter, calendar_year) "
					+ "VALUES ("+strDate+","+current_date.get(Calendar.DAY_OF_MONTH)+","+current_date.get(Calendar.DAY_OF_WEEK)+","+current_date.get(Calendar.WEEK_OF_MONTH)+","+(current_date.get(Calendar.MONTH)+1)+","+quarter+","+current_date.get(Calendar.YEAR)+");");
		    current_date.add(Calendar.DATE, 1);
		}
		stat.close();
	}
}
