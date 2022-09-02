package Remplissage_BDD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Order {
	public void Insertion() throws Exception {
		// Connexion à la base de données
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
		Statement statement_2 = connection.createStatement();
		// Création de la table dans la BD
		statement.executeUpdate("Create Table IF NOT EXISTS orderr(order_id int PRIMARY KEY ,account_id int ,bank_to varchar, account_to int, amount real, k_symbol varchar);");
		String csvFile = "dataset\\order_clean.csv";
		Scanner scanner = new Scanner(new File(csvFile));
		scanner.nextLine();
		while (scanner.hasNext()) {
		    List<String> line = Scan.parseLine(scanner.nextLine());
		    int order_id = Integer.parseInt(line.get(0));
		    int account_id = Integer.parseInt(line.get(1));
		    String bank_to = line.get(2);
		    int account_to = Integer.parseInt(line.get(3));
		    float amount = Float.parseFloat(line.get(4));
		    String k_symbol = line.get(5);
		    statement.executeUpdate(
		        "INSERT INTO orderr(order_id, account_id , bank_to, account_to, amount, k_symbol ) " + 
		        "VALUES ("+order_id+","+ account_id + " , '"+bank_to+"',"+account_to+","+amount+",'"+k_symbol+"')"
		        );
		    }
			ResultSet k = statement.executeQuery("SELECT account_id FROM orderr WHERE NOT EXISTS (SELECT * FROM account WHERE orderr.account_id = account.account_id);");
			
	        while (k.next()) {
	        	String[] arr = null;
	            String em = k.getString("account_id");
	           arr = em.split("\n");
	           for (int i =0; i < arr.length; i++){
	              // System.out.println(arr[i]);
	        	   int x = Integer.parseInt(arr[i]);
	        	   statement_2.executeUpdate("DELETE FROM orderr WHERE account_id="+x+";");
	           }
	        }
	        statement.executeUpdate ("ALTER TABLE orderr ADD FOREIGN KEY (account_id) REFERENCES account(account_id);");
			statement.close();
		scanner.close();
	}							
	
	public void Suppression() throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
	    statement.executeUpdate ("DROP TABLE IF EXISTS orderr CASCADE;");
	    statement.close();
	}
}
