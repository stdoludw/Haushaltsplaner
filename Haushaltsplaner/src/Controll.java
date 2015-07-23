import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controll {

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
	private View mvieView;

	void start() {
		this.mvieView = new View();
		this.mstrPasswort = "dlu";
		this.mstrUserName = "dlu";
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName = "HausHaltsPlaner_Database";
		this.mstrHostName = "dfch-ludwig.de";

		acces();
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
			String sql = mstStatment.markt.toString();
			ResultSet result = query.executeQuery(sql);

			// speichern in Klasse
			while (result.next()) {
				// mvecModel.addElement(new Model());

				
				if (sql== statments.all.toString()) {
					System.out.println(result.getString("k.K_ID"));
					System.out.println(result.getString("k.betrag"));
					System.out.println(result.getString("k.name"));
					System.out.println(result.getString("k.bankleitzahl"));
					System.out.println(result.getString("k.kontonummer"));
					System.out.println(result.getString("k.minimum"));

					System.out.println(result.getString("p.P_ID"));
					System.out.println(result.getString("p.name"));
					System.out.println(result.getString("p.gewicht"));
					System.out.println(result.getString("p.preis"));

					System.out.println(result.getString("m.M_ID"));
					System.out.println(result.getString("m.name"));
					System.out.println(result.getString("m.postleitzahl"));
					System.out.println(result.getString("m.adresse"));
					System.out.println(result.getString("m.entfernung"));

					System.out.println(result.getString("me.anzahl"));
					System.out.println(result.getString("me.datum"));
				}

				else if (sql == statments.konto.toString()) {
					System.out.println(result.getString("k.K_ID"));
					System.out.println(result.getString("k.betrag"));
					System.out.println(result.getString("k.name"));
					System.out.println(result.getString("k.bankleitzahl"));
					System.out.println(result.getString("k.kontonummer"));
					System.out.println(result.getString("k.minimum"));
				} else if (sql == statments.markt.toString()) {
					System.out.println(result.getString("m.M_ID"));
					System.out.println(result.getString("m.name"));
					System.out.println(result.getString("m.postleitzahl"));
					System.out.println(result.getString("m.adresse"));
					System.out.println(result.getString("m.entfernung"));
				} else if (sql == statments.produkt.toString()) {
					System.out.println(result.getString("p.P_ID"));
					System.out.println(result.getString("p.name"));
					System.out.println(result.getString("p.gewicht"));
					System.out.println(result.getString("p.preis"));
				}

			}

			// scließen des streams
			result.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
