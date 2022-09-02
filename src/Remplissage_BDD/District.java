package Remplissage_BDD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class District {
	public void Insertion() throws Exception {
		// Connexion à la base de données
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
		// Création de la table dans la BD
		statement.executeUpdate("Create Table IF NOT EXISTS district(district_id SERIAL PRIMARY KEY ,A2 varchar ,A3 varchar,A4 int ,A5 int,A6 int ,A7 int,A8 int,A9 int,A10 decimal ,A11 int, A12 decimal ,A13 decimal, A14 int, A15 int, A16 int);");
		String csvFile = "dataset\\district_clean.csv";
		Scanner scanner = new Scanner(new File(csvFile));
		scanner.nextLine();
		while (scanner.hasNext()) {
		    List<String> line = Scan.parseLine(scanner.nextLine());
		    int district_id = Integer.parseInt(line.get(0));
		    String A2 = line.get(1);
		    String A3 = line.get(2);
		    int A4 = Integer.parseInt(line.get(3));
		    int A5 = Integer.parseInt(line.get(4));
		    int A6 = Integer.parseInt(line.get(5));
		    int A7 = Integer.parseInt(line.get(6));
		    int A8 = Integer.parseInt(line.get(7));
		    int A9 = Integer.parseInt(line.get(8));
		    float A10 = Float.parseFloat(line.get(9));
		    int A11 = Integer.parseInt(line.get(10));
		    float A12 = Float.parseFloat(line.get(11));
		    float A13 = Float.parseFloat(line.get(12));
		    int A14 = Integer.parseInt(line.get(13));
		    int A15 = Integer.parseInt(line.get(14));
		    int A16 = Integer.parseInt(line.get(15));		   
		    
		    statement.executeUpdate(
		        "INSERT INTO district(district_id, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) " +
		        "VALUES ("+district_id+",'"+A2+ "','"+A3+"',"+A4+","+A5+","+A6+","+A7+","+A8+","+A9+","+A10+","+A11+","+A12+","+A13+","+A14+","+A15+","+A16+")"
		        );
		    }
		statement.close();
		scanner.close();
	}					
	
	public void Suppression() throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
	    statement.executeUpdate ("DROP TABLE IF EXISTS district CASCADE;");
	    statement.close();
	}
}

