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

public class Card {
	public void Insertion() throws Exception {
		// Connexion à la base de données
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
		Statement statement_2 = connection.createStatement();
		// Création de la table dans la BD
		statement.executeUpdate("Create Table IF NOT EXISTS card(card_id int PRIMARY KEY ,disp_id int ,type varchar, issued date );");
		String csvFile = "dataset\\card_clean.csv";
		Scanner scanner = new Scanner(new File(csvFile));
		scanner.nextLine();
		while (scanner.hasNext()) {
		    List<String> line = Scan.parseLine(scanner.nextLine());
		    int card_id = Integer.parseInt(line.get(0));
		    int disp_id = Integer.parseInt(line.get(1));
		    String type = line.get(2);
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    Date issued = formatter.parse(line.get(3)); 

		    statement.executeUpdate(
		        "INSERT INTO card(card_id ,disp_id, type, issued ) " +
		        "VALUES (" + card_id + " , " + disp_id + ",'"+type+"','"+issued+"')"
		        );
		    }
		ResultSet k = statement.executeQuery("SELECT disp_id FROM card WHERE NOT EXISTS (SELECT * FROM disp WHERE card.disp_id = disp.disp_id);"); 
		
        while (k.next()) {
        	String[] arr = null;
            String em = k.getString("disp_id");
           arr = em.split("\n");
           for (int i =0; i < arr.length; i++){
              // System.out.println(arr[i]);
        	   int x = Integer.parseInt(arr[i]);
        	   statement_2.executeUpdate("DELETE FROM card WHERE disp_id="+x+";");
           }
        }
        statement.executeUpdate ("ALTER TABLE card ADD FOREIGN KEY (disp_id) REFERENCES disp(disp_id);");
		statement.close();
		scanner.close();
	}		
	
	public void Suppression() throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Finantial", "postgres","root");
		Statement statement = connection.createStatement();
	    statement.executeUpdate ("DROP TABLE IF EXISTS card CASCADE;");
	    statement.close();
	}		
}
