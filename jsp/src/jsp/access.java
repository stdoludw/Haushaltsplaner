package jsp;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class access {

	private String mstrUserName;
	private String mstrPasswort;
	private static Connection mconCon;
	private final int mintPort = 3306;
	private static String mstrDatenbankName;
	private final String mstrHostName = "localhost";

	private static ArrayList<Object> mvecModel;
	private static ArrayList<String> mvecStatistic;
	private static AES_verschluesselung aes;

	public access() {
	}

	@SuppressWarnings("static-access")
	public void login(String username, String passwort, String db) {

		this.aes = new AES_verschluesselung();
		this.mvecModel = new ArrayList<Object>();
		this.mvecStatistic = new ArrayList<String>();
		
		try {
			// neuen Vector erstellen
			this.aes.setkey(passwort);
			
			mstrUserName =  username;
			mstrPasswort = passwort;
			mstrDatenbankName = db;

			// Datenbanktreiber fuer JDBC Schnittstellen laden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur JDBC-Datenbank herstellen.
			mconCon = DriverManager.getConnection("jdbc:mysql://"
					+ mstrHostName + ":" + mintPort + "/" + mstrDatenbankName
					+ "?" + "user=" + mstrUserName + "&" + "password="
					+ mstrPasswort);
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean status()
	{
		return !mconCon.equals(null);
	
	}
	
	public static void SQLAbfrage(String sql, int multi) {

		try {
			// abfrage statement erstellen
			Statement query = mconCon.createStatement();

			// Query bearbeiten
			ResultSet result = null;

			// executeQuerry nur fuer selects
			result = query.executeQuery(sql);
			
			//fuer iteration in statistic
			int i=1;
			
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
				else if(multi == 0)
				{
					mvecStatistic.add(result.getString(i));
					i++;
				}
				else if(multi == 1)
				{
					mvecStatistic.add(result.getString(1));
					mvecStatistic.add(result.getString(2));

				}
				else if(multi == 3)
				{
					mvecStatistic.add(result.getString(1));
					mvecStatistic.add(result.getString(2));
					mvecStatistic.add(result.getString(3));


				}
				

			}

			// scliessen des streams
			result.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void SQLModifizieren(String sql) {
		try {

			// abfrage statement erstellen
			Statement query = mconCon.createStatement();

			// executeQuerry nur fuer insert/update/alter
			query.executeUpdate(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		
		}
	}

	public void SQLNeuErstellen(String kuerzel) {

		try {
			// neue Datenbank erstellen
			Vector<String> mvecMod = Controll_Statments.createDatenbank(kuerzel);

			for (int i = 0; i < mvecMod.size(); i++) {
				SQLModifizieren(mvecMod.get(i));
			}
		} catch(Exception ex) {
			ex.printStackTrace();

		}
	}

	public static void auslesen() {
		try{
		mvecModel.clear();
		SQLAbfrage(Controll_Statments.ViewAll(),2);
		SQLAbfrage(Controll_Statments.ViewKonto(),2);
		SQLAbfrage(Controll_Statments.ViewMarkt(),2);
		SQLAbfrage(Controll_Statments.ViewProdukt(),2);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();

		}
	}


	public static ArrayList<Object> getMvecModel() {
		return mvecModel;
	}

	
	public static ArrayList<String> getStatistik()
	{
		
		for(int i=0;i<Controll_Statments.getstatisticMonth(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(Controll_Statments.getstatisticMonth(mstrDatenbankName).get(i),0);
		}
		
		for(int i=0;i<Controll_Statments.getStatisticMulti(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(Controll_Statments.getStatisticMulti(mstrDatenbankName).get(i),1);
		}
	
		for(int i=0;i<Controll_Statments.getstatisticAllMonth(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(Controll_Statments.getstatisticAllMonth(mstrDatenbankName).get(i),0);
		}
		
		for(int i=0;i<Controll_Statments.getStatisticMultiTable(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(Controll_Statments.getStatisticMultiTable(mstrDatenbankName).get(i),3);
		}
		
		
		return mvecStatistic;
	}
	public static AES_verschluesselung getAes() {
		return aes;
	}


}
