import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import lib.*;

public class Controll_Main implements ActionListener {

	// verbindung mit Datenbank
	private String mstrUserName;
	private String mstrPasswort;
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;

	// Instanzen von Model und View
	private Vector<Model_Main> mvecModel;
	private GUI_Main mguiMain;
	private GUI_Abfrage mguiAbfrage;
	private GUI_Hinzufuegen mguiHinzufuegen;

	@SuppressWarnings("static-access")
	public void start() throws SQLException, ClassNotFoundException,
			IOException {

		//reinigen der variablen
		mstrUserName="";
		mstrPasswort="";
		mconCon = null;
		mintPort = 3306;
		mstrDatenbankName="";
		mstrHostName="";
		
		//erstellen der view instanzen
		mguiMain = mguiMain.init();
		mguiAbfrage = mguiAbfrage.init();
		mguiHinzufuegen = mguiHinzufuegen.init();

		//hinzufuegend er Listener
		mguiAbfrage.getMbntErstellen().addActionListener(this);
		mguiAbfrage.getMbtnLogin().addActionListener(this);

		mguiMain.getMntmAll().addActionListener(this);
		mguiMain.getMmenExportiern().addActionListener(this);
		mguiMain.getMmenLaden().addActionListener(this);
		mguiMain.getMmenStatistik().addActionListener(this);

		mguiHinzufuegen.getMbtmKonto().addActionListener(this);
		mguiHinzufuegen.getMbtmMarkt().addActionListener(this);
		mguiHinzufuegen.getMbtmProdukt().addActionListener(this);
		mguiHinzufuegen.getMbtmAlles().addActionListener(this);

		//starten des Hauptfensters
		mguiAbfrage.show(mguiAbfrage);
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getActionCommand() == mguiAbfrage.LOGIN) {

			//hiden des alten fensters
			mguiAbfrage.hide();

			// Hauptgui starten
			
			/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 * 				Warum entsteht hier kein fenster?
			 */
			mguiMain.show();

			try {
				acces();

				// Daten auslesen
				SQLAbfrage(Controll_Statments.all.toString());
				SQLAbfrage(Controll_Statments.konto.toString());
				SQLAbfrage(Controll_Statments.markt.toString());
				SQLAbfrage(Controll_Statments.produkt.toString());

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			// füllen der anzeige box
			String mstrContent = null;
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Produkt) {
					mstrContent += "Name\tGewicht\tPreis\n";
					mstrContent += ((Model_Produkt) mvecModel.get(i)).print()
							+ "\n";
				} else if (mvecModel.get(i) instanceof Model_Konto) {
					mstrContent += "Name\tBankLeitZahl\tKontonummer\tBetrag\tMinimum\n";
					mstrContent += ((Model_Konto) mvecModel.get(i)).print()
							+ "\n";

				} else if (mvecModel.get(i) instanceof Model_Markt) {
					mstrContent += "Name\tPostLeitZahl\tAdresse\tEntfernung\n";
					mstrContent += ((Model_Markt) mvecModel.get(i)).print()
							+ "\n";

				} else {
					mstrContent += "Anzahl\tDatum\n";
					mstrContent += mvecModel.get(i).print() + "\n";

				}
			}

			// füllen der GUI
			mguiMain.setTextArea(mstrContent);

			// ablaufplan
			ablauf(ae);

		} else if (ae.getActionCommand() == mguiAbfrage.ERSTELLEN) {

			// kuerzel als wiedererkennung erstellen
			String kuerzel = null;
			for (int i = 0; i < 3; i++) {
				kuerzel += mstrUserName.toCharArray()[i];
			}

			// !!Neuen user einrichten mit grant auch berechtigungen setzen

			try {
				SQLNeuErstellen(kuerzel);
				acces();

				// Daten auslesen
				SQLAbfrage(Controll_Statments.all.toString());
				SQLAbfrage(Controll_Statments.konto.toString());
				SQLAbfrage(Controll_Statments.markt.toString());
				SQLAbfrage(Controll_Statments.produkt.toString());
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			ablauf(ae);

		}
	}

	@SuppressWarnings("static-access")
	private void ablauf(ActionEvent ae) {

		while (true) {
			if (ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENALL) {
				// m_ID,k_ID,p_ID,u_ID
				try {
					SQLModifizieren(Controll_Statments.allHinzufügen.toString()
							+ mguiHinzufuegen.getMtxtAlles_Anzahl().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtAlles_Datum().getText()
							+ ");");
					SQLAbfrage(Controll_Statments.all.toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENKONTO) {
				try {
					SQLModifizieren(Controll_Statments.kontoHinzufügen
							.toString()
							+ mguiHinzufuegen.getMtxtKonto_name().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtKonto_Blz().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtKonto_kontonummer()
									.getText()
							+ ","
							+ mguiHinzufuegen.getMtxtKonto_Betrag().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtKonto_Min().getText()
							+ ");");
					SQLAbfrage(Controll_Statments.konto.toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENMARKT) {
				try {
					SQLModifizieren(Controll_Statments.marktHinzufügen
							.toString()
							+ mguiHinzufuegen.getMtxtMarkt_Name().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtMarkt_Plz().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtMarkt_Adresse().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtMarkt_Entfernung()
							+ ");");
					SQLAbfrage(Controll_Statments.markt.toString());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENPRODUKT) {
				try {
					SQLModifizieren(Controll_Statments.produktHinzufügen
							.toString()
							+ mguiHinzufuegen.getMtxtProdukt_Name().getText()
							+ ","
							+ mguiHinzufuegen.getMtxtProdukt_Gewicht()
									.getText()
							+ ","
							+ mguiHinzufuegen.getMtxtProdukt_Preis().getText()
							+ ");");
					SQLAbfrage(Controll_Statments.produkt.toString());

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (ae.getActionCommand() == mguiMain.EXPORT) {
				try {
					SQLExport();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (ae.getActionCommand() == mguiMain.LADEN) {
				try {
					SQLImport();
				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@SuppressWarnings("deprecation")
	private void acces() throws ClassNotFoundException, SQLException {

		//neuen Vector erstellen
		this.mvecModel = new Vector<Model_Main>();
		
		mstrUserName = mguiAbfrage.getMtxtMeta_Username().getText();
		mstrPasswort = mguiAbfrage.getMtxtMeta_passwort().getText();
		mstrDatenbankName = mguiAbfrage.getMtxtMeta_DatenabnkName().getText();
		mstrHostName = mguiAbfrage.getMtxtMeta_DatenabnkServer().getText();
		mintPort = 3306;

		// Datenbanktreiber für JDBC Schnittstellen laden.
		Class.forName("com.mysql.jdbc.Driver");

		// Verbindung zur JDBC-Datenbank herstellen.
		mconCon = DriverManager.getConnection("jdbc:mysql://" + mstrHostName
				+ ":" + mintPort + "/" + mstrDatenbankName + "?" + "user="
				+ mstrUserName + "&" + "password=" + mstrPasswort);

	}

	private void SQLAbfrage(String sql) throws SQLException {

		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// Query bearbeiten
		ResultSet result = null;

		// executeQuerry nur für selects
		result = query.executeQuery(sql);

		// speichern in Klasse
		while (result.next()) {

			if (sql == Controll_Statments.all.toString()) {
				Model_Konto k = new Model_Konto(result.getString("betrag"),
						result.getString("Kontoinhaber"),
						result.getString("bankleitzahl"),
						result.getString("kontonummer"),
						result.getInt("minimum"), result.getInt("K_ID"));
				Model_Produkt p = new Model_Produkt(
						result.getString("Produktname"),
						result.getInt("gewicht"), result.getFloat("preis"),
						result.getInt("P_ID"));
				Model_Markt m = new Model_Markt(result.getString("Marktname"),
						result.getString("postleitzahl"),
						result.getString("adresse"),
						result.getInt("entfernung"), result.getInt("M_ID"));
				Model_Main me = new Model_Main(result.getInt("anzahl"),
						result.getString("datum"), true);

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

		// scließen des streams
		result.close();
	}

	private void SQLModifizieren(String sql) throws SQLException {
		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// executeQuerry nur für insert/update/alter
		query.executeUpdate(sql);
	}

	private void SQLNeuErstellen(String kuerzel) throws SQLException {

		// neue Datenbank erstellen
		Vector<String> mvecMod = Controll_Statments.toExtendString(kuerzel);
		mvecMod.add(Controll_Statments.commit.toString());

		for (int i = 0; i < mvecMod.size(); i++) {
			SQLModifizieren(mvecMod.get(i));
		}

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

}
