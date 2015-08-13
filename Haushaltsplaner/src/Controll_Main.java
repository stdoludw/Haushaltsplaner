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

//#TODO best‰aedigung auf button click
//#TODO ausgabe mit update und delete -> button
//#TODO verschl¸sseln von Konto -> name ->kontonummer->bankleitzahl->betrag->minimum

public class Controll_Main implements ActionListener  {

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

		// reinigen der variablen
		this.mstrUserName = "";
		this.mstrPasswort = "";
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName = "";
		this.mstrHostName = "";

		// erstellen der view instanzen
		this.mguiMain = mguiMain.init();
		this.mguiAbfrage = mguiAbfrage.init();
		this.mguiHinzufuegen = mguiHinzufuegen.init();

		// hinzufuegend der Listener
		this.mguiAbfrage.getMbntErstellen().addActionListener(this);
		this.mguiAbfrage.getMbtnLogin().addActionListener(this);

		this.mguiMain.getMntmHinzufgen().addActionListener(this);
		this.mguiMain.getMmenExportiern().addActionListener(this);
		this.mguiMain.getMmenLaden().addActionListener(this);
		this.mguiMain.getMmenStatistik().addActionListener(this);
		this.mguiMain.getComboBox().addActionListener(this);

		this.mguiHinzufuegen.getMbtmAlles().addActionListener(this);
		this.mguiHinzufuegen.getMbtmMarkt().addActionListener(this);
		this.mguiHinzufuegen.getMbtmProdukt().addActionListener(this);
		this.mguiHinzufuegen.getMbtmKonto().addActionListener(this);

		// starten des Hauptfensters
		this.mguiAbfrage.show(mguiAbfrage);
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public void actionPerformed(ActionEvent ae) {

		String string = ae.getActionCommand().toString();
		if (mguiAbfrage.LOGIN.equals(string)) {
			// hiden des alten fensters
			mguiAbfrage.dispose();
			// Hauptgui starten
			// Das Main Window wird nur angezeigt wenn Daten fuer connection ok
			mguiMain.show(mguiMain);
			try {
				acces();

				// Daten auslesen
				SQLAbfrage(Controll_Statments.all.toString());
				SQLAbfrage(Controll_Statments.konto.toString());
				SQLAbfrage(Controll_Statments.markt.toString());
				SQLAbfrage(Controll_Statments.produkt.toString());

			} catch (ClassNotFoundException | SQLException e) {
				System.err.println(e.getMessage() + " Daten leider fehlerhaft");
				
			}
			finally
			{
				mguiAbfrage.clear();

			}
		} else if (mguiMain.HINZUFUEGEN.equals(string)) {
			mguiHinzufuegen.show(mguiHinzufuegen);
			
			//erst comboboxen clearen
			mguiHinzufuegen.clear();
			
				// Comboboxen fuellen
					for (int i = 0; i < mvecModel.size(); i++) {
						if (mvecModel.get(i) instanceof Model_Produkt) {
							mguiHinzufuegen.getMcmbProdukt().addItem(
									((Model_Produkt) mvecModel.get(i))
											.getMstrName());
							

						} else if (mvecModel.get(i) instanceof Model_Konto) {

							mguiHinzufuegen.getMcmbKonto().addItem(
									((Model_Konto) mvecModel.get(i))
											.getMstrName());

						} else if (mvecModel.get(i) instanceof Model_Markt) {
							mguiHinzufuegen.getMcmbMarkt().addItem(
									((Model_Markt) mvecModel.get(i))
											.getMstrName());
						} 
				}

				
		}
			else if (mguiMain.STATISTIK.equals(string)) {
			
			
		} 

			else if (mguiAbfrage.ERSTELLEN.equals(string)) {
			// hiden des alten fensters
			mguiAbfrage.dispose();
			// kuerzel als wiedererkennung erstellen
			String kuerzel = null;
			for (int i = 0; i < 3; i++) {
				kuerzel += mstrUserName.toCharArray()[i];
			}
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
			finally
			{
				mguiAbfrage.clear();
			}
		} else if (mguiHinzufuegen.HINZUFUEGENALL.equals(string)) {
		
			// Konto, Produkt und Markt PK ermitteln
			int K_ID = 0, M_ID = 0, P_ID = 0;
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Produkt){
						if(((Model_Produkt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbProdukt().getSelectedItem()) {
					P_ID = ((Model_Produkt) mvecModel.get(i)).getMintID();
						}	
				} else if (mvecModel.get(i) instanceof Model_Konto)
				{
				
				if(((Model_Konto) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbKonto().getSelectedItem()) {
					K_ID = ((Model_Konto) mvecModel.get(i)).getMintID();
				}
				} else if (mvecModel.get(i) instanceof Model_Markt)
				{
						if(((Model_Markt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbMarkt().getSelectedItem()) {
					M_ID = ((Model_Markt) mvecModel.get(i)).getMintID();
				}
			
				// Einkauf hinzufuegen
				try {
					SQLModifizieren(Controll_Statments.allHinzufuegen.toString()
							+ mguiHinzufuegen.getMtxtAlles_Anzahl().getText() + ","
							+ mguiHinzufuegen.getMtxtAlles_Datum().getText() + ","
							+ M_ID + "," + K_ID + "," + P_ID + ");");
					
					SQLAbfrage(Controll_Statments.all.toString());

				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally
				{
					mguiHinzufuegen.clear();
				}

			
		}
			}
		}
		else if (mguiHinzufuegen.HINZUFUEGENKONTO.equals(string)) {
			try {
				SQLModifizieren(Controll_Statments.kontoHinzufuegen.toString()
						+ '\''+mguiHinzufuegen.getMtxtKonto_name().getText() + '\''+","
						+ mguiHinzufuegen.getMtxtKonto_Blz().getText() + ","
						+ mguiHinzufuegen.getMtxtKonto_kontonummer().getText()
						+ "," + mguiHinzufuegen.getMtxtKonto_Betrag().getText()
						+ "," + mguiHinzufuegen.getMtxtKonto_Min().getText()
						+ ");");
			

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.konto.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				mguiHinzufuegen.clear();
			}

		} else if (mguiHinzufuegen.HINZUFUEGENMARKT.equals(string)) {
			try {
				SQLModifizieren(Controll_Statments.marktHinzufuegen.toString()
						+ '\''+mguiHinzufuegen.getMtxtMarkt_Name().getText() + '\''+","
						+ mguiHinzufuegen.getMtxtMarkt_Plz().getText() + ","
						+'\''+ mguiHinzufuegen.getMtxtMarkt_Adresse().getText()+'\''
						+ "," + mguiHinzufuegen.getMtxtMarkt_Entfernung()
						+ ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.markt.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				mguiHinzufuegen.clear();
			}

			
		} else if (mguiHinzufuegen.HINZUFUEGENPRODUKT.equals(string)) {
			try {
				SQLModifizieren(Controll_Statments.produktHinzufuegen.toString()
						+'\''+ mguiHinzufuegen.getMtxtProdukt_Name().getText() + '\''+","
						+ mguiHinzufuegen.getMtxtProdukt_Gewicht().getText()
						+ ","
						+ mguiHinzufuegen.getMtxtProdukt_Preis().getText()
						+ ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.produkt.toString());

			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				mguiHinzufuegen.clear();
			}

		} else if (mguiMain.EXPORT.equals(string)) {
			try {
				SQLExport();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mguiMain.LADEN.equals(string)) {
			try {
				SQLImport();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		} else if (mguiMain.AUSWAHL.equals(string)) {
			
			// fuellen der anzeige box
			String mstrContent = null;
			if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Einkauf
					.toString()) {
				mstrContent += "Anzahl\tDatum\tProdukt\tKonto\tMarkt\n";
				
				for (int i = 0; i < mvecModel.size(); i++) {
					{
						mstrContent += mvecModel.get(i).print() + "\n";
						if (mvecModel.get(i) instanceof Model_Produkt) {
							if (((Model_Produkt) mvecModel.get(i)).getMintID() == mvecModel
									.get(i).getMintIDProdukt()) {
								mstrContent += ((Model_Produkt) mvecModel
										.get(i)).print() + "\n";
							} else if (mvecModel.get(i) instanceof Model_Konto)
								if (((Model_Konto) mvecModel.get(i))
										.getMintID() == mvecModel.get(i)
										.getMintIDKonto()) {
									mstrContent += ((Model_Konto) mvecModel
											.get(i)).print() + "\n";
								} else if (mvecModel.get(i) instanceof Model_Markt)
									if (((Model_Markt) mvecModel.get(i))
											.getMintID() == mvecModel.get(i)
											.getMintIDMarkt()) {
										mstrContent += ((Model_Markt) mvecModel
												.get(i)).print() + "\n";
									}
						}

					}
				}
			} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Produkt
					.toString()) {
				mstrContent += "Name\tGewicht\tPreis\n";
				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Produkt) {
						mstrContent += ((Model_Produkt) mvecModel.get(i))
								.print() + "\n";
					}
				}
			} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Markt
					.toString()) {
				mstrContent += "Name\tPostLeitZahl\tAdresse\tEntfernung\n";
				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Markt) {
						mstrContent += ((Model_Markt) mvecModel.get(i)).print()
								+ "\n";
					}
				}
			} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Konto
					.toString()) {
				mstrContent += "Name\tBankLeitZahl\tKontonummer\tBetrag\tMinimum\n";

				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Konto) {
						mstrContent += ((Model_Konto) mvecModel.get(i)).print()
								+ "\n";
					}
				}
			}
			// fuellen der GUI
			mguiMain.setTextArea(mstrContent);
		}
		
		
	}

	@SuppressWarnings("deprecation")
	private void acces() throws ClassNotFoundException, SQLException {

		// neuen Vector erstellen
		this.mvecModel = new Vector<Model_Main>();

		mstrUserName = mguiAbfrage.getMtxtMeta_Username().getText();
		mstrPasswort = mguiAbfrage.getMtxtMeta_passwort().getText();
		mstrDatenbankName = mguiAbfrage.getMtxtMeta_DatenabnkName().getText();
		mstrHostName = mguiAbfrage.getMtxtMeta_DatenabnkServer().getText();
		mintPort = 3306;

		// Datenbanktreiber fuer JDBC Schnittstellen laden.
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

		// executeQuerry nur fuer selects
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
						result.getString("datum"));

				// verknuepfung reaisieren
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

		// sclie√üen des streams
		result.close();
	}

	private void SQLModifizieren(String sql) throws SQLException {
		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// executeQuerry nur fuer insert/update/alter
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
			if (mvecModel.get(i) instanceof Model_Produkt) {

				mfioStream.write(((Model_Produkt) mvecModel.get(i))
						.SQLerstellenProdukt());
				mfioStream.newLine();

			} else if (mvecModel.get(i) instanceof Model_Konto) {

				mfioStream.write(((Model_Konto) mvecModel.get(i))
						.SQLerstellenKonto());
				mfioStream.newLine();

			} else if (mvecModel.get(i) instanceof Model_Markt) {
				mfioStream.write(((Model_Markt) mvecModel.get(i))
						.SQLerstellenMarkt());
				mfioStream.newLine();
			} else {
				//#TODO kein enter in diesen block?
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
