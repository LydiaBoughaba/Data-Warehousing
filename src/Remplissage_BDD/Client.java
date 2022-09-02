package Remplissage_BDD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Client {
	public void Insertion() throws Exception {
		// Connexion à la base de données
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
		Statement statement_2 = connection.createStatement();
		// Création de la table dans la BD
		statement.executeUpdate("Create Table IF NOT EXISTS client(client_id int PRIMARY KEY ,gender varchar ,birth_date date, district_id int );");
		String csvFile = "dataset\\client_clean.csv";
		Scanner scanner = new Scanner(new File(csvFile));
		scanner.nextLine();
		while (scanner.hasNext()) {
		    List<String> line = Scan.parseLine(scanner.nextLine());
		    int client_id = Integer.parseInt(line.get(0));
		    String gender = line.get(1);
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    Date birth_date = formatter.parse(line.get(2)); 
		    int district_id = Integer.parseInt(line.get(3));
		    statement.executeUpdate(
		        "INSERT INTO client(client_id, gender ,birth_date, district_id ) " +
		        "VALUES ("+client_id+",'" + gender + "' , '" + birth_date + "',"+district_id+")"
		        );
		    }
		ResultSet k = statement.executeQuery("SELECT district_id FROM client WHERE NOT EXISTS (SELECT * FROM district WHERE client.district_id = district.district_id);");
		
        while (k.next()) {
        	String[] arr = null;
            String em = k.getString("district_id");
           arr = em.split("\n");
           for (int i =0; i < arr.length; i++){
              // System.out.println(arr[i]);
        	   int x = Integer.parseInt(arr[i]);
        	   statement_2.executeUpdate("DELETE FROM client WHERE district_id="+x+";");
           }
        }
        statement.executeUpdate ("ALTER TABLE client ADD FOREIGN KEY (district_id) REFERENCES district(district_id);");
		statement.close();
		scanner.close();
	}	
	
	public void Suppression() throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
	    statement.executeUpdate ("DROP TABLE IF EXISTS client CASCADE;");
	    statement.close();
	}			
}
