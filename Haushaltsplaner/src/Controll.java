import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sun.security.jca.GetInstance.Instance;

/*TODO
 Erstellen von unabhänigen datenbanken
 Kontstand erhöhen jeden monat
 erkennen von schon verwendeten objekten
 commiten beim beenden
 statistiken als bild
 portierung auf android/php
 */

public class Controll {

	// verbindung mit Datenbank
	private String mstrUserName;
	private String mstrPasswort;
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;
	private String mstrStatment;

	// Instanzen von M und V
	private Vector<Model> mvecModel;
	private ausgabe mausAusgabe;

	void start() throws Exception {

		// abfragen der initdaten
		mausAusgabe = new ausgabe();
		// mausAusgabe.metaDaten();

		// vector inizialisieren
		this.mvecModel = new Vector<Model>();
		this.mstrPasswort = "dlu";
		this.mstrUserName = "dlu";
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName = "HausHaltsPlaner_Database";
		this.mstrHostName = "dfch-ludwig.de";

		// Daten aus masuAusgabe in this schreiben
		/*
		 * this.mstrPasswort = mausAusgabe.getMstrPasswort(); this.mstrUserName
		 * = mausAusgabe.getMstrUserName(); this.mconCon = null; this.mintPort =
		 * 3306; this.mstrDatenbankName = mausAusgabe.getMstrDatenbankName();
		 * this.mstrHostName = mausAusgabe.getMstrHostName();
		 */

		while (true) {
			// init lader Datenbank
			acces();

			// init abfrage der Daten
			SQLAbfrage();
			this.mstrStatment = statments.produkt.toString();
			this.mstrStatment = statments.konto.toString();
			this.mstrStatment = statments.markt.toString();

			// was soll abgefragt werden
			this.mstrStatment = mausAusgabe.choice();

			// wenn nicht neue werte einfügen -> vector clear
			if (!mausAusgabe.isMboolEinlesen()) {
				this.mvecModel.clear();
			}
			// updaten des vectors local
			else {
				this.mvecModel.clear();
				this.mvecModel = mausAusgabe.getMvecModel();
			}

			// Datenbank befragen
			SQLAbfrage();

			// ausgabe der gefundenen Werte
			mausAusgabe.print(mstrStatment);

			// neue werte in Datenbank schreiben
			for (int i = 0; i < mvecModel.size(); i++) {
				// screiben wenn nicht valid
				if (!mvecModel.get(i).isMboolValid()) {
					// erstellen der IDs
					if (((Produkt) mvecModel.get(i)).getMfltPreis() != 0.0) {
						((Produkt) mvecModel.get(i))
								.setMintID(getNext(new Produkt()));
						SQLModifizieren(((Produkt) mvecModel.get(i))
								.SQLerstellenProdukt());
					} else if (((Konto) mvecModel.get(i)).getMstrKnr() != "") {
						((Konto) mvecModel.get(i))
								.setMintID(getNext(new Konto()));
						SQLModifizieren(((Konto) mvecModel.get(i))
								.SQLerstellenKonto());

					} else if (((Markt) mvecModel.get(i)).getMintEntfernung() != 0) {
						((Markt) mvecModel.get(i))
								.setMintID(getNext(new Markt()));
						SQLModifizieren(((Markt) mvecModel.get(i))
								.SQLerstellenMarkt());

					} else {
						// immer zuletzt ausführen
						SQLModifizieren(mvecModel.get(i).SQlerstellenAll());
					}

				}
			}

			// vektor clearen
			this.mvecModel.clear();
		}

	}

	void acces() throws Exception {

		// Datenbanktreiber für JDBC Schnittstellen laden.
		Class.forName("com.mysql.jdbc.Driver");

		// Verbindung zur JDBC-Datenbank herstellen.
		mconCon = DriverManager.getConnection("jdbc:mysql://" + mstrHostName
				+ ":" + mintPort + "/" + mstrDatenbankName + "?" + "user="
				+ mstrUserName + "&" + "password=" + mstrPasswort);
	}

	void SQLAbfrage() throws Exception {

		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// Query bearbeiten
		String sql = mstrStatment;
		ResultSet result = null;

		// executeQuerry nur für selects
		result = query.executeQuery(sql);

		// speichern in Klasse
		while (result.next()) {

			if (sql == statments.all.toString()) {
				Konto k = new Konto(result.getString("k.betrag"),
						result.getString("k.name"),
						result.getString("k.bankleitzahl"),
						result.getString("k.kontonummer"),
						result.getInt("k.minimum"), result.getInt("k.K_ID"));
				Produkt p = new Produkt(result.getString("p.name"),
						result.getInt("p.gewicht"), result.getFloat("p.preis"),
						result.getInt("p.P_ID"));
				Markt m = new Markt(result.getString("m.name"),
						result.getString("m.postleitzahl"),
						result.getString("m.adresse"),
						result.getInt("m.entfernung"), result.getInt("m.M_ID"));
				Model me = new Model(result.getInt("me.anzahl"),
						result.getString("me.datum"), true);

				// verknüpfung reaisieren
				me.ModelArray(k.getMintID(), p.getMintID(), m.getMintID());
				mvecModel.add(me);
			}

			else if (sql == statments.konto.toString()) {
				Konto k = new Konto(result.getString("k.betrag"),
						result.getString("k.name"),
						result.getString("k.bankleitzahl"),
						result.getString("k.kontonummer"),
						result.getInt("k.minimum"), result.getInt("k.K_ID"));

				mvecModel.add(k);

			} else if (sql == statments.markt.toString()) {
				Markt m = new Markt(result.getString("m.name"),
						result.getString("m.postleitzahl"),
						result.getString("m.adresse"),
						result.getInt("m.entfernung"), result.getInt("m.M_ID"));

				mvecModel.add(m);

			} else if (sql == statments.produkt.toString()) {
				Produkt p = new Produkt(result.getString("p.name"),
						result.getInt("p.gewicht"), result.getFloat("p.preis"),
						result.getInt("p.P_ID"));

				mvecModel.add(p);
			}

		}
		mausAusgabe.setMvecModel(mvecModel);

		// scließen des streams
		result.close();
	}

	void SQLModifizieren(String sql) throws Exception {
		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// executeQuerry nur für selects
		query.executeUpdate(sql);
	}

	private int getNext(Model m) {
		int max = -1;

		if (m instanceof Produkt) {
			for (int i = 0; i < mvecModel.size(); i++) {
				if (((Produkt) mvecModel.get(i)).getMintID() > max) {
					max = ((Produkt) mvecModel.get(i)).getMintID();
				}
			}
			return max + 1;
		} else if (m instanceof Konto) {
			for (int i = 0; i < mvecModel.size(); i++) {
				if (((Konto) mvecModel.get(i)).getMintID() > max) {
					max = ((Konto) mvecModel.get(i)).getMintID();
				}
			}
			return max + 1;
		} else if (m instanceof Markt) {
			for (int i = 0; i < mvecModel.size(); i++) {
				if (((Markt) mvecModel.get(i)).getMintID() > max) {
					max = ((Markt) mvecModel.get(i)).getMintID();
				}
			}
			return max + 1;
		}
		
		//standart return
		return -1;

	}

}
