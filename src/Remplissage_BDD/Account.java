package Remplissage_BDD;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.util.List; 
import java.util.Scanner; 
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
public void Insertion() throws Exception {
	// Connexion à la base de données
	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
	Statement statement = connection.createStatement();
	Statement statement_2 = connection.createStatement();
	// Création de la table dans la BD
	statement.executeUpdate("Create Table IF NOT EXISTS account(account_id int PRIMARY KEY ,district_id int ,frequency varchar,date date);");
	String csvFile = "dataset\\account_clean.csv";
	Scanner scanner = new Scanner(new File(csvFile));
	scanner.nextLine();
	while (scanner.hasNext()) {
	    List<String> line = Scan.parseLine(scanner.nextLine());
	    int account_id = Integer.parseInt(line.get(0));
	    int district_id = Integer.parseInt(line.get(1));
	    String frequency = line.get(2);
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = formatter .parse(line.get(3)); 
	    statement.executeUpdate(
	        "INSERT INTO account(account_id,district_id, frequency , date ) " + 
	        "VALUES ("+account_id+",'"+district_id+"','"+ frequency + "' , '"+date+"')"
	        );
	    }
		ResultSet k = statement.executeQuery("SELECT district_id FROM account WHERE NOT EXISTS (SELECT * FROM district WHERE account.district_id = district.district_id);");
		
        while (k.next()) {
        	String[] arr = null;
            String em = k.getString("district_id");
           arr = em.split("\n");
           for (int i =0; i < arr.length; i++){
              // System.out.println(arr[i]);
        	   int x = Integer.parseInt(arr[i]);
        	   statement_2.executeUpdate("DELETE FROM account WHERE district_id="+x+";");
           }
        }
        statement.executeUpdate ("ALTER TABLE account ADD FOREIGN KEY (district_id) REFERENCES district(district_id);");
		statement.close(); 
	scanner.close();
}	
public void Suppression() throws Exception {
	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
	Statement statement = connection.createStatement();
    statement.executeUpdate ("DROP TABLE IF EXISTS account CASCADE;");
    statement.close();
}
}