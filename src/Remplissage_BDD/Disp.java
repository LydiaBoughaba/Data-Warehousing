package Remplissage_BDD;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.util.List; 
import java.util.Scanner;

public class Disp {
public void Insertion() throws Exception {
	// Connexion à la base de données
	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
	Statement statement = connection.createStatement();
	Statement statement_2 = connection.createStatement();
	Statement statement_3 = connection.createStatement();
	// Création de la table dans la BD
	statement.executeUpdate("Create Table IF NOT EXISTS disp(disp_id int PRIMARY KEY ,client_id int ,account_id int,type varchar);");
	String csvFile = "dataset\\disp_clean.csv";
	Scanner scanner = new Scanner(new File(csvFile));
	scanner.nextLine();
	while (scanner.hasNext()) {
	    List<String> line = Scan.parseLine(scanner.nextLine());
	    int disp_id = Integer.parseInt(line.get(0));
	    int client_id = Integer.parseInt(line.get(1));
	    int account_id = Integer.parseInt(line.get(2));
	    String type = line.get(3);
	    
	    statement.executeUpdate(
	        "INSERT INTO disp(disp_id,client_id, account_id , type ) " + 
	        "VALUES ("+disp_id+","+client_id+","+ account_id + " , '"+type+"')"
	        );
	    }
		ResultSet k = statement.executeQuery("SELECT client_id ,account_id FROM disp WHERE NOT EXISTS (SELECT * FROM client,account WHERE disp.client_id = client.client_id and disp.account_id= account.account_id);");
		
        while (k.next()) {
        	String[] arr1 = null;
        	String[] arr2 = null;
            String em1 = k.getString("client_id");
            String em2 = k.getString("account_id");
           arr1 = em1.split("\n");
           arr2 = em2.split("\n");
           for (int i =0; i < arr1.length; i++){
              // System.out.println(arr[i]);
        	   int x = Integer.parseInt(arr1[i]);
        	   statement_2.executeUpdate("DELETE FROM disp WHERE client_id="+x+";");
           }
           for (int i =0; i < arr2.length; i++){
               // System.out.println(arr[i]);
         	   int x = Integer.parseInt(arr2[i]);
         	   statement_3.executeUpdate("DELETE FROM disp WHERE account_id="+x+";");
            }
        }
        statement.executeUpdate ("ALTER TABLE disp ADD FOREIGN KEY (client_id) REFERENCES client(client_id)");
        statement.executeUpdate ("ALTER TABLE disp ADD FOREIGN KEY (account_id) REFERENCES account(account_id)");
		statement.close(); 
	scanner.close();
}				

public void Suppression() throws Exception {
	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
	Statement statement = connection.createStatement();
    statement.executeUpdate ("DROP TABLE IF EXISTS disp CASCADE;");
    statement.close();
}			
}