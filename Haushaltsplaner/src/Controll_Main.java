import java.util.Scanner;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

import lib.*;

//#TODO Kontstand erhöhen jeden monat	mit SQL
//#TODO statistiken als bild
//#TODO canvas test
//#TODO observer für GUI und controll
//#TODO portierung auf android/php

public class Controll_Main {

	// verbindung mit Datenbank
	private String mstrUserName;test
	private String mstrPasswort;
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;
	private String mstrStatment;

	// Instanzen von M und V
	private Vector<Model_Main> mvecModel;

	public void start() throws SQLException, ClassNotFoundException,
			IOException {

		// abfragen der initdaten mausAusgabe = new ausgabe();
/*
		// neue Datenbank erstellen
		if (mausAusgabe.metaDaten()) {
			SQLNeuErstellen();
		}

		// vector inizialisieren
		this.mvecModel = new Vector<Model_Main>();

		while (true) { // init lader Datenbank acces();

			// init abfrage der Daten SQLAbfrage(); this.mstrStatment =
			Controll_Statments.produkt.toString();
			this.mstrStatment = Controll_Statments.konto.toString();
			this.mstrStatment = Controll_Statments.markt.toString();

			// neue werte in Datenbank schreiben
			exit();
		}
*/
	}

	private void acces() throws ClassNotFoundException, SQLException {

		// Datenbanktreiber für JDBC Schnittstellen laden.
		Class.forName("com.mysql.jdbc.Driver");

		// Verbindung zur JDBC-Datenbank herstellen.
		mconCon = DriverManager.getConnection("jdbc:mysql://" + mstrHostName
				+ ":" + mintPort + "/" + mstrDatenbankName + "?" + "user="
				+ mstrUserName + "&" + "password=" + mstrPasswort);
	}

	private void SQLAbfrage() throws SQLException {

		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// Query bearbeiten
		String sql = mstrStatment;
		ResultSet result = null;

		// executeQuerry nur für selects
		result = query.executeQuery(sql);

		// speichern in Klasse
		while (result.next()) {

			if (sql == Controll_Statments.all.toString() + this.mstrUserName
					+ ");") {
				Model_Konto k = new Model_Konto(result.getString("k.betrag"),
						result.getString("k.name"),
						result.getString("k.bankleitzahl"),
						result.getString("k.kontonummer"),
						result.getInt("k.minimum"), result.getInt("k.K_ID"));
				Model_Produkt p = new Model_Produkt(result.getString("p.name"),
						result.getInt("p.gewicht"), result.getFloat("p.preis"),
						result.getInt("p.P_ID"));
				Model_Markt m = new Model_Markt(result.getString("m.name"),
						result.getString("m.postleitzahl"),
						result.getString("m.adresse"),
						result.getInt("m.entfernung"), result.getInt("m.M_ID"));
				Model_Main me = new Model_Main(result.getInt("ein.anzahl"),
						result.getString("ein.datum"), true);

				// verknüpfung reaisieren
				me.ModelArray(k.getMintID(), p.getMintID(), m.getMintID());
				mvecModel.add(me);
			}

			else if (sql == Controll_Statments.konto.toString()) {
				Model_Konto k = new Model_Konto(result.getString("k.betrag"),
						result.getString("k.name"),
						result.getString("k.bankleitzahl"),
						result.getString("k.kontonummer"),
						result.getInt("k.minimum"), result.getInt("k.K_ID"));

				mvecModel.add(k);

			} else if (sql == Controll_Statments.markt.toString()) {
				Model_Markt m = new Model_Markt(result.getString("m.name"),
						result.getString("m.postleitzahl"),
						result.getString("m.adresse"),
						result.getInt("m.entfernung"), result.getInt("m.M_ID"));

				mvecModel.add(m);

			} else if (sql == Controll_Statments.produkt.toString()) {
				Model_Produkt p = new Model_Produkt(result.getString("p.name"),
						result.getInt("p.gewicht"), result.getFloat("p.preis"),
						result.getInt("p.P_ID"));

				mvecModel.add(p);
			}

		}
		//mausAusgabe.setMvecModel(mvecModel);

		// scließen des streams
		result.close();
	}

	private void SQLModifizieren(String sql) throws SQLException {
		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// executeQuerry nur für insert/update/alter
		query.executeUpdate(sql);
	}

	private int getNext(Model_Main m) {
		int max = -1;

		if (m instanceof Model_Produkt) {
			for (int i = 0; i < mvecModel.size(); i++) {
				if (((Model_Produkt) mvecModel.get(i)).getMintID() > max) {
					max = ((Model_Produkt) mvecModel.get(i)).getMintID();
				}
			}
			return max + 1;
		} else if (m instanceof Model_Konto) {
			for (int i = 0; i < mvecModel.size(); i++) {
				if (((Model_Konto) mvecModel.get(i)).getMintID() > max) {
					max = ((Model_Konto) mvecModel.get(i)).getMintID();
				}
			}
			return max + 1;
		} else if (m instanceof Model_Markt) {
			for (int i = 0; i < mvecModel.size(); i++) {
				if (((Model_Markt) mvecModel.get(i)).getMintID() > max) {
					max = ((Model_Markt) mvecModel.get(i)).getMintID();
				}
			}
			return max + 1;
		}

		// standart return
		return -1;

	}

	private void SQLNeuErstellen() throws SQLException {
		System.out.println("Datenbanknamen eingeben: ");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();

		// neue Datenbank erstellen
		SQLModifizieren(Controll_Statments.DatenbasisHinzufügen.toString()
				+ name + ";");

		// neue Datenbank benutzen
		SQLModifizieren(Controll_Statments.DatenbasisBenutzen.toString() + name
				+ ";");

		// SQL query für ProduktTabelle
		SQLModifizieren(Controll_Statments.ProduktTabelleHinzufügen.toString());

		// SQL query für MarktTabelle
		SQLModifizieren(Controll_Statments.MarktTabelleHinzufügen.toString());

		// SQL query für MergeTabelle
		SQLModifizieren(Controll_Statments.EinkaufTabelleHinzufügen.toString());

		// SQL query für KontoTabelle
		SQLModifizieren(Controll_Statments.KontoTabelleHinzufügen.toString());

		// commit
		SQLModifizieren(Controll_Statments.commit.toString());

	}

	private void SQLExport() throws IOException {
		FileWriter file = new FileWriter("../Backup.sql");
		BufferedWriter mfioStream = new BufferedWriter(file);

		for (int i = 0; i < mvecModel.size(); i++) {
			if (((Model_Produkt) mvecModel.get(i)).getMfltPreis() != 0.0) {
				mfioStream.write(((Model_Produkt) mvecModel.get(i))
						.SQLerstellenProdukt());
				mfioStream.newLine();
			} else if (((Model_Konto) mvecModel.get(i)).getMstrKnr() != "") {
				mfioStream.write(((Model_Konto) mvecModel.get(i))
						.SQLerstellenKonto());
				mfioStream.newLine();
			} else if (((Model_Markt) mvecModel.get(i)).getMintEntfernung() != 0) {
				mfioStream.write(((Model_Markt) mvecModel.get(i))
						.SQLerstellenMarkt());
				mfioStream.newLine();
			} else {
				mfioStream.write(mvecModel.get(i).SQlerstellenAll());
				mfioStream.newLine();
			}
		}

		mfioStream.close();

	}

	private void SQLImport() throws IOException, SQLException {
		FileReader file = new FileReader("../Backup.sql");
		BufferedReader mfosStream = new BufferedReader(file);
		String read = null;

		while ((read = mfosStream.readLine()) != null) {

			SQLModifizieren(read);

		}

		mfosStream.close();

	}

	private void exit() throws SQLException {
		for (int i = 0; i < mvecModel.size(); i++) {
			// screiben wenn nicht valid if
			if (!mvecModel.get(i).isMboolValid()) { // erstellen der IDs
				if (((Model_Produkt) mvecModel.get(i)).getMfltPreis() != 0.0) {
					((Model_Produkt) mvecModel.get(i))
							.setMintID(getNext(new Model_Produkt()));
					SQLModifizieren(((Model_Produkt) mvecModel.get(i))
							.SQLerstellenProdukt());
				} else if (((Model_Konto) mvecModel.get(i)).getMstrKnr() != "") {
					((Model_Konto) mvecModel.get(i))
							.setMintID(getNext(new Model_Konto()));
					SQLModifizieren(((Model_Konto) mvecModel.get(i))
							.SQLerstellenKonto());

				} else if (((Model_Markt) mvecModel.get(i)).getMintEntfernung() != 0) {
					((Model_Markt) mvecModel.get(i))
							.setMintID(getNext(new Model_Markt()));
					SQLModifizieren(((Model_Markt) mvecModel.get(i))
							.SQLerstellenMarkt());

				} else {
					// immer zuletzt ausführen
					SQLModifizieren(mvecModel.get(i).SQlerstellenAll());
				}

			}
		}

		// vektor clearen
		this.mvecModel.clear();

		// commiten der Datenbank
		SQLModifizieren(Controll_Statments.commit.toString());
	}

}
