package Interface_graphique;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Remplissage_BDD.Account;
import Remplissage_BDD.Card;
import Remplissage_BDD.Client;
import Remplissage_BDD.Disp;
import Remplissage_BDD.District;
import Remplissage_BDD.Loan;
import Remplissage_BDD.Order;
import Remplissage_BDD.Transaction;
import Remplissage_Etoile.AccountEtoile;
import Remplissage_Etoile.ClientEtoile;
import Remplissage_Etoile.DateEtoile;
import Remplissage_Etoile.DistrictEtoile;
import Remplissage_Etoile.TransEtoile;
import cleaner.AccountCleaner;
import cleaner.CardCleaner;
import cleaner.ClientCleaner;
import cleaner.DispCleaner;
import cleaner.DistrictCleaner;
import cleaner.LoanCleaner;
import cleaner.OrderCleaner;
import cleaner.TransCleaner;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.SystemColor;

public class Interface {

	private JFrame frame;
	static JTextArea notifyField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		frame.setBackground(Color.WHITE);
		JLabel lbl=new JLabel("",JLabel.CENTER);
		ImageIcon icon=new ImageIcon("images/background.png");
		lbl.setIcon(icon);
		frame.getContentPane().add(lbl);
		frame.setBounds(100, 100, 510, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Cr\u00E9er les Tables");
		btnNewButton.setBackground(new Color(250, 235, 215));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				District d = new District();
				Account a = new Account();
				Client c = new Client();
				Loan l = new Loan();
				Order o = new Order();
				Transaction t = new Transaction();
				Disp dd = new Disp();
				Card cc = new Card();
				Interface.notify("Creation des tables en cours..");
				try {
					AccountCleaner.nettoyer();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
				}
				try {
					CardCleaner.nettoyer();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					ClientCleaner.nettoyer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					DispCleaner.nettoyer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					LoanCleaner.nettoyer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					OrderCleaner.nettoyer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					TransCleaner.nettoyer();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					DistrictCleaner.nettoyer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					d.Insertion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					a.Insertion();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					c.Insertion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					l.Insertion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					o.Insertion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					t.Insertion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					dd.Insertion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					cc.Insertion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Interface.notify("creation des tables a été terminé");
				
				JOptionPane.showMessageDialog(frame,"Les tables ont été bien crées et nettoyés");
			}
		});
		btnNewButton.setFont(new Font("Open Sans", Font.BOLD, 12));
		btnNewButton.setBounds(10, 60, 230, 50);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Sch\u00E9ma Relationnel");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 230, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnSupprimerLesTables = new JButton("Supprimer les Tables");
		btnSupprimerLesTables.setBackground(new Color(250, 235, 215));
		btnSupprimerLesTables.setForeground(new Color(0, 0, 0));
		btnSupprimerLesTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				District d = new District();
				Account a = new Account();
				Client c = new Client();
				Loan l = new Loan();
				Order o = new Order();
				Transaction t = new Transaction();
				Disp dd = new Disp();
				Card cc = new Card();
				Interface.notify("Suppression des tables en cours..");
				try {
					cc.Suppression();
					l.Suppression();
					o.Suppression();
					t.Suppression();
					dd.Suppression();
					a.Suppression();
					c.Suppression();
					d.Suppression();
				} catch (Exception e1) {
					
				}
				Interface.notify("Suppression des tables a été terminé");
				JOptionPane.showMessageDialog(frame,"Les tables ont été bien supprimés");
			}
		}
		
		);
		btnSupprimerLesTables.setFont(new Font("Open Sans", Font.BOLD, 12));
		btnSupprimerLesTables.setBounds(10, 121, 230, 50);
		frame.getContentPane().add(btnSupprimerLesTables);
		
		JLabel lblSchmaEtoile = new JLabel("Sch\u00E9ma Etoile");
		lblSchmaEtoile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSchmaEtoile.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchmaEtoile.setBounds(250, 10, 230, 50);
		frame.getContentPane().add(lblSchmaEtoile);
		
		JButton button = new JButton("Supprimer les Tables");
		button.setBackground(new Color(250, 235, 215));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistrictEtoile d = new DistrictEtoile();
				ClientEtoile c = new ClientEtoile();
				AccountEtoile a = new AccountEtoile();
				DateEtoile date = new DateEtoile();
				TransEtoile t = new TransEtoile();

				// suppression en cours
				Interface.notify("Suppresion d'entrepôt de données en cours..");
				try {
					t.Suppression();
					date.Suppression();
					a.Suppression();
					c.Suppression();
					d.Suppression();
					// suppression terminé
				} catch (SQLException e1) {
					e1.printStackTrace();
					// erreur lors de la suppression
				}
				Interface.notify("Suppresion terminé.");
				JOptionPane.showMessageDialog(frame,"Les tables ont été bien supprimés");
			}
		});
		button.setFont(new Font("Open Sans", Font.BOLD, 12));
		button.setBounds(250, 121, 230, 50);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cr\u00E9er les Tables");
		button_1.setBackground(new Color(250, 235, 215));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistrictEtoile d = new DistrictEtoile();
				ClientEtoile c = new ClientEtoile();
				AccountEtoile a = new AccountEtoile();
				DateEtoile date = new DateEtoile();
				TransEtoile t = new TransEtoile();

				Interface.notify("creation de l'entrepôt de données en cours..");
				try {
					d.Creation();
					c.Creation();
					a.Creation();
					date.Creation();
					t.Creation();

					d.Remplissage();
					c.Remplissage();
					a.Remplissage();
					date.Remplissage();
					t.Remplissage();

				
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();

				}
				Interface.notify("creation de l'entrepôt a été bien terminé");
				JOptionPane.showMessageDialog(frame,"Les tables ont été bien crées");
			}
		});
		button_1.setFont(new Font("Open Sans", Font.BOLD, 12));
		button_1.setBounds(250, 60, 230, 50);
		frame.getContentPane().add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 470, 163);
		frame.getContentPane().add(scrollPane);
		
		notifyField = new JTextArea();
		scrollPane.setViewportView(notifyField);
		notifyField.setFont(new Font("Monospaced", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setBackground(new Color(250, 235, 215));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); 
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(360, 356, 98, 50);
		frame.getContentPane().add(btnNewButton_1);
		frame.setTitle("Projet TP");


	}
	
	public static void notify(String notif) {
		notifyField.setText(notifyField.getText()+"\n"+notif);
	}
}