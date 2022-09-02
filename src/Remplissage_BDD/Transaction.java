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

public class Transaction {
	public void Insertion() throws Exception {
		// Connexion à la base de données
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
		Statement statement_2 = connection.createStatement();
		// Création de la table dans la BD
		statement.executeUpdate("Create Table IF NOT EXISTS trans(trans_id int PRIMARY KEY ,account_id int ,date date , type varchar, operation varchar, amount int, balance int, k_symbol varchar, bank varchar, account int);");
		String csvFile = "dataset\\trans_clean.csv";
		Scanner scanner = new Scanner(new File(csvFile));
		scanner.nextLine();
		while (scanner.hasNext()) {
		    List<String> line = Scan.parseLine(scanner.nextLine());
		    int trans_id = Integer.parseInt(line.get(0));
		    int account_id = Integer.parseInt(line.get(1));
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    Date date = formatter.parse(line.get(2)); 
		    String type = line.get(3);
		    String operation = line.get(4);
		    int amount = Integer.parseInt(line.get(5));
		    int balance = Integer.parseInt(line.get(6));
		    String k_symbol = line.get(7);
		    String bank = line.get(8);
		    int account = Integer.parseInt(line.get(9));
		    statement.executeUpdate(
		        "INSERT INTO trans(trans_id, account_id, date, type, operation, amount, balance, k_symbol, bank, account ) " + 
		        "VALUES ("+trans_id+","+ account_id + " , '"+date+"','"+type+"','"+operation+"',"+amount+",'"+balance+"','"+k_symbol+"','"+bank+"',"+account+")"
		        );
		    }
			ResultSet k = statement.executeQuery("SELECT account_id FROM trans WHERE NOT EXISTS (SELECT * FROM account WHERE trans.account_id = account.account_id);");
			
	        while (k.next()) {
	        	String[] arr = null;
	            String em = k.getString("account_id");
	           arr = em.split("\n");
	           for (int i =0; i < arr.length; i++){
	              // System.out.println(arr[i]);
	        	   int x = Integer.parseInt(arr[i]);
	        	   statement_2.executeUpdate("DELETE FROM trans WHERE account_id="+x+";");
	           }
	        }
	        statement.executeUpdate ("ALTER TABLE trans ADD FOREIGN KEY (account_id) REFERENCES account(account_id);");
			statement.close();
		scanner.close();
	}					
	
	public void Suppression() throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
	    statement.executeUpdate ("DROP TABLE IF EXISTS trans CASCADE;");
	    statement.close();
	}
}
