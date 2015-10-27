package CONTROLLER;

import java.util.ArrayList;

import MODEL.AES_verschluesselung;
import MODEL.MODEL_Konto;

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

	private  ArrayList<MODEL_Konto> mvecModel;
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
		this.mvecModel = new ArrayList<MODEL_Konto>();
		
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
			ArrayList<String> mvecMod = CONTROLLER_Statments.createDatenbank(kuerzel);

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
		SQLAbfrage(CONTROLLER_Statments.ViewKonto());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();

		}
	}


	public ArrayList<MODEL_Konto> getObjXML()
	{
		for (int i = 0; i<this.mvecModel.size();i++)
			if (this.mvecModel.get(i) instanceof MODEL_Konto) 
			{
				((MODEL_Konto) this.mvecModel.get(i)).setMintMin(aes.verschluesselnAES((((MODEL_Konto) this.mvecModel.get(i)).getMintMin())));
				((MODEL_Konto) this.mvecModel.get(i)).setMstrBetrag(aes.verschluesselnAES(((MODEL_Konto) this.mvecModel.get(i)).getMstrBetrag()));
				((MODEL_Konto) this.mvecModel.get(i)).setMstrBLZ(aes.verschluesselnAES(((MODEL_Konto) this.mvecModel.get(i)).getMstrBLZ()));
				((MODEL_Konto) this.mvecModel.get(i)).setMstrKnr(aes.verschluesselnAES(((MODEL_Konto) this.mvecModel.get(i)).getMstrKnr()));
				((MODEL_Konto) this.mvecModel.get(i)).setMstrName(aes.verschluesselnAES(((MODEL_Konto) this.mvecModel.get(i)).getMstrName()));
			}                 
		
		
		return this.mvecModel;
		
	}
	
	
	
	public ArrayList<MODEL_Konto> getMvecModel() {
		return mvecModel;
	}

	

	public   AES_verschluesselung getAes() {
		return aes;
	}


}
