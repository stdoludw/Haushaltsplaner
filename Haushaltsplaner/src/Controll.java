import java.util.Date;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controll{

	// verbindung mit Datenbank
	private String mstrUserName;
	private String mstrPasswort;
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;
	private statments mstStatment;
	

	// Instanzen von M und V
	private Vector<Model> mvecModel;
	private ausgabe mausAusgabe;

	void start() {
		
		//abfragen der initdaten
		mausAusgabe = new ausgabe();
		mausAusgabe.metaDaten();
		
		//vector inizialisieren
		this.mvecModel = new Vector<Model>();
		this.mstrPasswort = "dlu";
		this.mstrUserName = "dlu";
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName = "HausHaltsPlaner_Database";
		this.mstrHostName = "dfch-ludwig.de";
		
		//Daten aus masuAusgabe in this schreiben
		/*this.mstrPasswort = mausAusgabe.getMstrPasswort();
		this.mstrUserName = mausAusgabe.getMstrUserName();
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName = mausAusgabe.getMstrDatenbankName();
		this.mstrHostName = mausAusgabe.getMstrHostName();*/
		
		//was soll abgefragt werden
		this.mstStatment = mausAusgabe.choice();
		
		//Datenbank befragen
		acces();
		
		//ausgabe der gefundenen Werte
		mausAusgabe.print(this.mvecModel);
		
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
			Statement query;
			query = mconCon.createStatement();

			// Query bearbeiten
			String sql = mstStatment.toString();
			ResultSet result = query.executeQuery(sql);
			Date today =java.util.Calendar.getInstance().getTime();;
			
			// speichern in Klasse
			while (result.next()) {

				
				if (sql == statments.all.toString()) {
					Konto k = new Konto(result.getString("k.betrag"), result.getString("k.name"), result.getString("k.bankleitzahl"), result.getString("k.kontonummer"), result.getInt("k.minimum"), result.getInt("k.K_ID"));
					Produkt p = new Produkt(result.getString("p.name"), result.getInt("p.gewicht"), result.getFloat("p.preis"), result.getInt("p.P_ID"));
					Markt m = new Markt(result.getString("m.name"), result.getString("m.postleitzahl"), result.getString("m.adresse"), result.getInt("m.entfernung"), result.getInt("m.M_ID"));
					mvecModel.addElement(new Model(result.getInt("me.anzahl"), result.getDate("me.datum"),k,p,m));
				}

				else if (sql == statments.konto.toString()) {
					Konto k = new Konto(result.getString("k.betrag"), result.getString("k.name"), result.getString("k.bankleitzahl"), result.getString("k.kontonummer"), result.getInt("k.minimum"), result.getInt("k.K_ID"));
					mvecModel.addElement(new Model(result.getInt("me.anzahl"), result.getDate("me.datum"),k,new Produkt(),new Markt()));
					
				} else if (sql == statments.markt.toString()) {
					Markt m = new Markt(result.getString("m.name"), result.getString("m.postleitzahl"), result.getString("m.adresse"), result.getInt("m.entfernung"), result.getInt("m.M_ID"));
					mvecModel.addElement(new Model(result.getInt("me.anzahl"), result.getDate("me.datum"),new Konto(),new Produkt(),m));
					
				} else if (sql == statments.produkt.toString()) {
					Produkt p = new Produkt(result.getString("p.name"), result.getInt("p.gewicht"), result.getFloat("p.preis"), result.getInt("p.P_ID"));
					mvecModel.addElement(new Model(result.getInt("me.anzahl"), result.getDate("me.datum"),new Konto(),p,new Markt()));
					
				}

			}

			
			// scließen des streams
			result.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}










}
