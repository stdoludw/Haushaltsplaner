import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*TODO
 Erstellen von unabhänigen datenbanken
 Kontstand erhöhen jeden monat
 erkennen von schon verwendeten objekten
 commiten beim beenden
 IDs automatisch erhöhen
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

	void start() {

		// abfragen der initdaten
		mausAusgabe = new ausgabe();
		mausAusgabe.metaDaten();

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
			//init laden der werte
			this.mstrStatment = statments.all.toString();
			acces();
			
			// was soll abgefragt werden
			this.mstrStatment = mausAusgabe.choice();

			// Datenbank befragen
			acces();

			// ausgabe der gefundenen Werte
			mausAusgabe.print();

			// neue Werte in valid Model hinzufügen
			if (mausAusgabe.isMboolEinlesen()) {
				this.mvecModel.add(mausAusgabe.getmMdlModel());
				mausAusgabe.setMvecModel(mvecModel);
			}
			
			//vektor clearen
			this.mvecModel.clear();
		}

	}

	void acces() {
		try {
			// Datenbanktreiber für JDBC Schnittstellen laden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur JDBC-Datenbank herstellen.
			mconCon = DriverManager.getConnection("jdbc:mysql://"
					+ mstrHostName + ":" + mintPort + "/" + mstrDatenbankName
					+ "?" + "user=" + mstrUserName + "&" + "password="
					+ mstrPasswort);

			// abfrage statement erstellen
			Statement query = mconCon.createStatement();

			// Query bearbeiten
			String sql = mstrStatment;
			ResultSet result = null;

			// insert -> update -> drop -> delete
			if ((mstrStatment.toCharArray()[0] == 'i'
					|| mstrStatment.toCharArray()[0] == 'u' || mstrStatment
					.toCharArray()[0] == 'd')
					&& (mstrStatment.toCharArray()[1] == 'n'
							|| mstrStatment.toCharArray()[1] == 'p'
							|| mstrStatment.toCharArray()[1] == 'r' || mstrStatment
							.toCharArray()[1] == 'e')) {
				// executeUpdate nur für modifizierende querrys
				query.executeUpdate(sql);
			} else {
				// executeQuerry nur für selects
				result = query.executeQuery(sql);
			
				// speichern in Klasse
				while (result.next()) {

					if (sql == statments.all.toString()) {
						Konto k = new Konto(result.getString("k.betrag"),
								result.getString("k.name"),
								result.getString("k.bankleitzahl"),
								result.getString("k.kontonummer"),
								result.getInt("k.minimum"),
								result.getInt("k.K_ID"));
						Produkt p = new Produkt(result.getString("p.name"),
								result.getInt("p.gewicht"),
								result.getFloat("p.preis"),
								result.getInt("p.P_ID"));
						Markt m = new Markt(result.getString("m.name"),
								result.getString("m.postleitzahl"),
								result.getString("m.adresse"),
								result.getInt("m.entfernung"),
								result.getInt("m.M_ID"));
						mvecModel.addElement(new Model(result
								.getInt("me.anzahl"), result
								.getString("me.datum"), k, p, m, true));
					}

					else if (sql == statments.konto.toString()) {
						Konto k = new Konto(result.getString("k.betrag"),
								result.getString("k.name"),
								result.getString("k.bankleitzahl"),
								result.getString("k.kontonummer"),
								result.getInt("k.minimum"),
								result.getInt("k.K_ID"));
						mvecModel.addElement(new Model(0,"" , k, new Produkt(),
								new Markt(), true));

					} else if (sql == statments.markt.toString()) {
						Markt m = new Markt(result.getString("m.name"),
								result.getString("m.postleitzahl"),
								result.getString("m.adresse"),
								result.getInt("m.entfernung"),
								result.getInt("m.M_ID"));
						mvecModel.addElement(new Model(0,"" , new Konto(),
								new Produkt(), m, true));

					} else if (sql == statments.produkt.toString()) {
						Produkt p = new Produkt(result.getString("p.name"),
								result.getInt("p.gewicht"),
								result.getFloat("p.preis"),
								result.getInt("p.P_ID"));
						mvecModel.addElement(new Model(result
								.getInt("me.anzahl"), result
								.getString("me.datum"), new Konto(), p,
								new Markt(), true));
					}

				}
				mausAusgabe.setMvecModel(mvecModel);

				// scließen des streams
				result.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
