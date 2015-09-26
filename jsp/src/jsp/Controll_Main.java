package jsp;

import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import javax.swing.JOptionPane;


public class Controll_Main {

	// verbindung mit Datenbank
	private String mstrUserName;
	private String mstrPasswort;
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;

	// Instanzen von Model und View
	private Vector<Object> mvecModel;

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
			this.aes.setkey("bro");
			
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

				} 
				else if (sql == Controll_Statments.ViewMarkt()) {
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

	public void SQLNeuErstellen(String kuerzel) {

		try {
			// neue Datenbank erstellen
			Vector<String> mvecMod = Controll_Statments.createDatenbank(kuerzel);

			for (int i = 0; i < mvecMod.size(); i++) {
				SQLModifizieren(mvecMod.get(i));
			}
		} finally {

		}
	}

	public void auslesen() {
		mvecModel.clear();
		//SQLAbfrage(Controll_Statments.ViewAll());
		SQLAbfrage(Controll_Statments.ViewKonto());
		//SQLAbfrage(Controll_Statments.ViewMarkt());
		//SQLAbfrage(Controll_Statments.ViewProdukt());
	}

	//#################################################################
	
	public String print()
	{
		return (String) ((Model_Konto) mvecModel.get(0)).print()[0];
	}
	
	
	private void AddAll() {
			
		}
		
	private void AddKonto() {

	}

	private void AddMarkt() {
		
	}

	private void AddProdukt() {
		
	}

	
}
