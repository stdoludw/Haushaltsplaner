package jsp;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class access {

	private String mstrUserName;
	private String mstrPasswort;
	private static Connection mconCon;
	private final int mintPort = 3306;
	private String mstrDatenbankName;
	private final String mstrHostName = "dfch-ludwig.de";

	private static Vector<Object> mvecModel;
	private static Vector<Object> mvecStatistic;


	private static AES_verschluesselung aes;

	public access() {
	}

	@SuppressWarnings("static-access")
	public void login(String username, String passwort, String db) {

		this.aes = new AES_verschluesselung();
		this.mvecModel = new Vector<Object>();
		this.mvecStatistic = new Vector<Object>();
		
		try {
			// neuen Vector erstellen
			this.aes.setkey("bro");
			
			mstrUserName = "bro";
			mstrPasswort = "P@ssw0rd";
			mstrDatenbankName = "HausHaltsPlaner_Database";

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

	public boolean status()
	{
		if (mconCon.equals(null))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void SQLAbfrage(String sql) {

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
				else 
				{
					int i=0;
					while(result.next())
					{
						mvecStatistic.add(result.getString(i));
					}
				}

			}

			// scliessen des streams
			result.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Fail",
					JOptionPane.OK_CANCEL_OPTION);
		}
	}

	public static void SQLModifizieren(String sql) {
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
		SQLAbfrage(Controll_Statments.ViewAll());
		SQLAbfrage(Controll_Statments.ViewKonto());
		SQLAbfrage(Controll_Statments.ViewMarkt());
		SQLAbfrage(Controll_Statments.ViewProdukt());
	}

	public String getMstrUserName() {
		return mstrUserName;
	}

	public String getMstrDatenbankName() {
		return mstrDatenbankName;
	}

	public String getMstrHostName() {
		return mstrHostName;
	}

	public static Vector<Object> getMvecModel() {
		return mvecModel;
	}

	@SuppressWarnings("static-access")
	public void setMvecModel(Vector<Object> mvecModel) {
		this.mvecModel = mvecModel;
	}
	
	public static Vector<Object> getStatistik()
	{
		
		for(String i : Controll_Statments.statistik())
		{
			SQLAbfrage(i);
		}
	
		return mvecStatistic;
	}
	public static AES_verschluesselung getAes() {
		return aes;
	}

	
}
