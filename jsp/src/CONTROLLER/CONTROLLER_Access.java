package CONTROLLER;

import java.util.ArrayList;
import java.util.Vector;

import MODEL.AES_verschluesselung;
import MODEL.MODEL_Einkauf;
import MODEL.MODEL_Konto;
import MODEL.MODEL_Markt;
import MODEL.MODEL_Produkt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CONTROLLER_Access {

	private String mstrUserName;
	private String mstrPasswort;
	private  Connection mconCon;
	private final int mintPort = 3306;
	private  String mstrDatenbankName;
	private final String mstrHostName = "dfch-ludwig.de";

	private  ArrayList<Object> mvecModel;
	private  ArrayList<String> mvecStatistic;
	private  AES_verschluesselung aes;
	private static CONTROLLER_Access frame = null;
	
	public static CONTROLLER_Access init()
	{
	
			try {
				if (frame == null) {
					frame = new CONTROLLER_Access();
				}
				else
				{
					throw new Exception("Schon eine Instance vorhanden");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	return frame;
	}
	
	private CONTROLLER_Access() {
	}

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
	
	public void SQLAbfrage(String sql, int multi) {

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

				if (sql.equals(CONTROLLER_Statments.ViewAll())){
					MODEL_Konto k = new MODEL_Konto(aes.entschluesselnAES(result.getString("Kontoinhaber")),
							aes.entschluesselnAES(result
									.getString("bankleitzahl")),
							aes.entschluesselnAES(result
									.getString("kontonummer")),
							aes.entschluesselnAES(result.getString("betrag")),
							aes.entschluesselnAES(result.getString("minimum")),
							result.getInt("K_ID"));
					MODEL_Produkt p = new MODEL_Produkt(
							result.getString("Produktname"),
							result.getInt("gewicht"), result.getFloat("preis"),
							result.getInt("P_ID"));
					MODEL_Markt m = new MODEL_Markt(
							result.getString("Marktname"),
							result.getString("postleitzahl"),
							result.getString("adresse"),
							result.getInt("entfernung"), result.getInt("M_ID"));
					MODEL_Einkauf me = new MODEL_Einkauf(
							result.getInt("anzahl"), result.getString("datum"),
							result.getInt("E_ID"),k,p,m);

					mvecModel.add(me);
				}

				else if (sql.equals( CONTROLLER_Statments.ViewKonto())) {
					MODEL_Konto k = new MODEL_Konto(
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
				else if (sql.equals( CONTROLLER_Statments.ViewMarkt())) {
					MODEL_Markt m = new MODEL_Markt(result.getString("m.name"),
							result.getString("m.postleitzahl"),
							result.getString("m.adresse"),
							result.getInt("m.entfernung"),
							result.getInt("m.M_ID"));

					mvecModel.add(m);

				} else if (sql.equals( CONTROLLER_Statments.ViewProdukt())) {
					MODEL_Produkt p = new MODEL_Produkt(
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

	public void SQLModifizieren(String sql) {
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
			Vector<String> mvecMod = CONTROLLER_Statments.createDatenbank(kuerzel);

			for (int i = 0; i < mvecMod.size(); i++) {
				SQLModifizieren(mvecMod.get(i));
			}
		} catch(Exception ex) {
			ex.printStackTrace();

		}
	}

	public void auslesen() {
		try{
		mvecModel.clear();
		SQLAbfrage(CONTROLLER_Statments.ViewAll(),2);
		SQLAbfrage(CONTROLLER_Statments.ViewKonto(),2);
		SQLAbfrage(CONTROLLER_Statments.ViewMarkt(),2);
		SQLAbfrage(CONTROLLER_Statments.ViewProdukt(),2);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();

		}
	}


	public ArrayList<Object> getMvecModel() {
		return mvecModel;
	}

	
	public   ArrayList<String> getStatistik()
	{
		
		for(int i=0;i<CONTROLLER_Statments.getstatisticMonth(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(CONTROLLER_Statments.getstatisticMonth(mstrDatenbankName).get(i),0);
		}
		
		for(int i=0;i<CONTROLLER_Statments.getStatisticMulti(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(CONTROLLER_Statments.getStatisticMulti(mstrDatenbankName).get(i),1);
		}
	
		for(int i=0;i<CONTROLLER_Statments.getstatisticAllMonth(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(CONTROLLER_Statments.getstatisticAllMonth(mstrDatenbankName).get(i),0);
		}
		
		for(int i=0;i<CONTROLLER_Statments.getStatisticMultiTable(mstrDatenbankName).size();i++)
		{
			SQLAbfrage(CONTROLLER_Statments.getStatisticMultiTable(mstrDatenbankName).get(i),3);
		}
		
		
		return mvecStatistic;
	}
	public   AES_verschluesselung getAes() {
		return aes;
	}


}
