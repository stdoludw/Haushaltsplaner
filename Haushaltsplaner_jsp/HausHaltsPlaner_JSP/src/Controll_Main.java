import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

import javax.swing.JOptionPane;


public class Controll_Main  {

	// verbindung mit Datenbank
	private String mstrUserName;
	private String mstrPasswort;
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;

	// Instanzen von Model und View
	private Vector<Object> mvecModel;


	public Vector<Object> getMvecModel() {
		return mvecModel;
	}

	// aes verschluesslung
	AES_verschluesselung aes;

	public Controll_Main() {

		// reinigen der variablen
		this.mstrUserName = "";
		this.mstrPasswort = "";
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName = "";
		this.mstrHostName = "";

		// aes verschluesselung
		this.aes = new AES_verschluesselung();
	}


	public void acces() {

		try {
			// neuen Vector erstellen
			this.mvecModel = new Vector<Object>();
			mvecModel.add(new Object());
			mstrUserName = "bro";
			mstrPasswort = "bro";
			mstrDatenbankName = "HausHaltsPlaner_Database";
			mstrHostName = "dfch-ludwig.de";
			mintPort = 3306;

			// Datenbanktreiber fuer JDBC Schnittstellen laden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur JDBC-Datenbank herstellen.
			mconCon = DriverManager.getConnection("jdbc:mysql://"
					+ mstrHostName + ":" + mintPort + "/" + mstrDatenbankName
					+ "?" + "user=" + mstrUserName + "&" + "password="
					+ mstrPasswort);
			
					
		} catch (Exception ex) {
			ex.getMessage();
		}
	}

	public void SQLAbfrage(String sql) {

		try {
			// abfrage statement erstellen
			Statement query = mconCon.createStatement();

			// Query bearbeiten
			ResultSet result = null;

			// executeQuerry nur fuer selects
			result = query.executeQuery(sql);

			// speichern in Klasse
			while (result.next()) {

				if (sql == Controll_Statments.ViewAll()){
					Model_Konto k = new Model_Konto(aes.entschluesselnAES(result.getString("Kontoinhaber")),
							aes.entschluesselnAES(result
									.getString("bankleitzahl")),
							aes.entschluesselnAES(result
									.getString("kontonummer")),
							aes.entschluesselnAES(result.getString("betrag")),
							aes.entschluesselnAES(result.getString("minimum")),
							result.getInt("K_ID"));
					Model_Produkt p = new Model_Produkt(
							result.getString("Produktname"),
							result.getInt("gewicht"), result.getFloat("preis"),
							result.getInt("P_ID"));
					Model_Markt m = new Model_Markt(
							result.getString("Marktname"),
							result.getString("postleitzahl"),
							result.getString("adresse"),
							result.getInt("entfernung"), result.getInt("M_ID"));
					Model_Einkauf me = new Model_Einkauf(
							result.getInt("anzahl"), result.getString("datum"),
							result.getInt("E_ID"));

					// verknuepfung reaisieren
					me.ModelArray(k, p, m);
					mvecModel.add(me);
				}

				else if (sql == Controll_Statments.ViewKonto()) {
					Model_Konto k = new Model_Konto(
							aes.entschluesselnAES(result.getString("k.name")),
							aes.entschluesselnAES(result
									.getString("k.bankleitzahl")),
							aes.entschluesselnAES(result
									.getString("k.kontonummer")),
							aes.entschluesselnAES(result.getString("k.betrag")),
							aes.entschluesselnAES(result.getString("k.minimum")),
							result.getInt("k.K_ID"));

					mvecModel.add(k);

				} else if (sql == Controll_Statments.ViewMarkt()) {
					Model_Markt m = new Model_Markt(result.getString("m.name"),
							result.getString("m.postleitzahl"),
							result.getString("m.adresse"),
							result.getInt("m.entfernung"),
							result.getInt("m.M_ID"));

					mvecModel.add(m);

				} else if (sql == Controll_Statments.ViewProdukt()) {
					Model_Produkt p = new Model_Produkt(
							result.getString("p.name"),
							result.getInt("p.gewicht"),
							result.getFloat("p.preis"), result.getInt("p.P_ID"));

					mvecModel.add(p);
				}

			}

			// scliessen des streams
			result.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Fail",
					JOptionPane.OK_CANCEL_OPTION);
		}
	}

	public void SQLModifizieren(String sql) {
		try {

			// abfrage statement erstellen
			Statement query = mconCon.createStatement();

			// executeQuerry nur fuer insert/update/alter
			query.executeUpdate(sql);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Fail",
					JOptionPane.OK_CANCEL_OPTION);
		}
	}

	public void SQLExport() {

		try {
			FileWriter file = new FileWriter("../Backup.sql");
			BufferedWriter mfioStream = new BufferedWriter(file);

			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Produkt) {

					mfioStream.write(((Model_Produkt) mvecModel.get(i))
							.SQLerstellen());
					mfioStream.newLine();

				} else if (mvecModel.get(i) instanceof Model_Konto) {

					mfioStream.write(((Model_Konto) mvecModel.get(i))
							.SQLerstellen(aes));
					mfioStream.newLine();

				} else if (mvecModel.get(i) instanceof Model_Markt) {
					mfioStream.write(((Model_Markt) mvecModel.get(i))
							.SQLerstellen());
					mfioStream.newLine();
				} else if (mvecModel.get(i) instanceof Model_Einkauf) {
					mfioStream.write(((Model_Einkauf) mvecModel.get(i))
							.SQlerstellenAll());
					mfioStream.newLine();
				}
			}

			mfioStream.close();
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

	public void auslesen() {
		SQLAbfrage(Controll_Statments.ViewAll());
		SQLAbfrage(Controll_Statments.ViewKonto());
		SQLAbfrage(Controll_Statments.ViewMarkt());
		SQLAbfrage(Controll_Statments.ViewProdukt());
	}

/*
	private void AddAll() {
		// Konto, Produkt und Markt ermitteln
		Model_Konto k= null;
		Model_Markt m = null;
		Model_Produkt p= null;
		
	
		
		//objekte überschreiben
		for (int i = 0; i < mvecModel.size(); i++) {
			if (mvecModel.get(i) instanceof Model_Produkt) 
			{
				if (((Model_Produkt) mvecModel.get(i)).getMstrName().equals(mguiHinzufuegen
						.getMcmbProdukt().getSelectedItem())) 
				{
					p = ((Model_Produkt) mvecModel.get(i));
				}
			} else if (mvecModel.get(i) instanceof Model_Konto)
			{
				if (((Model_Konto) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbKonto().getSelectedItem()) 
				{
					k = ((Model_Konto) mvecModel.get(i));
				}
			} else if (mvecModel.get(i) instanceof Model_Markt) 
			{
				if (((Model_Markt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbMarkt().getSelectedItem()) 
				{
					m = ((Model_Markt) mvecModel.get(i));
				}
			}
		}

			
				
				// Einkauf hinzufuegen
				try {
					SQLModifizieren(Controll_Statments.AddAlles(
							Integer.valueOf(mguiHinzufuegen.getMtxtAlles_Anzahl().getText()), mguiHinzufuegen.getMtxtAlles_Datum().getText(),
							k.getMintID(), p.getMintID(),  m.getMintID()));
						
							
						auslesen();
				} finally {
					mguiHinzufuegen.clear();
				}

		}

	private void AddKonto() {
		try {
			SQLModifizieren(Controll_Statments.AddKonto(mguiHinzufuegen.getMtxtKonto_name()
					.getText(), mguiHinzufuegen.getMtxtKonto_Blz()
					.getText(), mguiHinzufuegen
					.getMtxtKonto_kontonummer().getText(), mguiHinzufuegen
					.getMtxtKonto_Betrag().getText(), mguiHinzufuegen.getMtxtKonto_Min()
					.getText(), aes));
				

			// Model Akutell halten
			auslesen();

		} finally {
			mguiHinzufuegen.clear();
		}

	}

	private void AddMarkt() {
		try {
			SQLModifizieren(Controll_Statments.AddMarkt(mguiHinzufuegen.getMtxtMarkt_Name().getText(), 
					mguiHinzufuegen.getMtxtMarkt_Plz().getText(), 
					mguiHinzufuegen.getMtxtMarkt_Adresse().getText(), Integer.valueOf(mguiHinzufuegen.getMtxtMarkt_Entfernung().getText())));
			

			// Model Akutell halten
			auslesen();
		} finally {
			mguiHinzufuegen.clear();
		}
	}

	private void AddProdukt() {
		try {
			SQLModifizieren(Controll_Statments.AddProdukt(mguiHinzufuegen.getMtxtProdukt_Name().getText(),
					Integer.valueOf(mguiHinzufuegen.getMtxtProdukt_Gewicht().getText()),
					Float.valueOf(mguiHinzufuegen.getMtxtProdukt_Preis().getText())));
			
			// Model Akutell halten
			auslesen();
		} finally {
			mguiHinzufuegen.clear();
		}
	}

	*/
	
		
		 
}
