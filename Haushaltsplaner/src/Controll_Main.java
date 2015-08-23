import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	private Vector<Object> mvecModel;
	private GUI_Main mguiMain;
	private GUI_Abfrage mguiAbfrage;
	private GUI_Hinzufuegen mguiHinzufuegen;

	// aes verschluesslung
	AES_verschluesselung aes;

	public void start() {

		// reinigen der variablen
		this.mstrUserName = "";
		this.mstrPasswort = "";
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName = "";
		this.mstrHostName = "";

		// erstellen der view instanzen
		this.mguiMain = GUI_Main.init();
		this.mguiAbfrage = GUI_Abfrage.init();
		this.mguiHinzufuegen = GUI_Hinzufuegen.init();

		// hinzufuegend der Listener
		this.mguiAbfrage.getMbntErstellen().addActionListener(this);
		this.mguiAbfrage.getMbtnLogin().addActionListener(this);

		this.mguiMain.getMntmHinzufgen().addActionListener(this);
		this.mguiMain.getMmenExportiern().addActionListener(this);
		this.mguiMain.getMmenLaden().addActionListener(this);
		this.mguiMain.getComboBox().addActionListener(this);
		this.mguiMain.getMcmbMonat().addActionListener(this);

		this.mguiHinzufuegen.getMbtmAlles().addActionListener(this);
		this.mguiHinzufuegen.getMbtmMarkt().addActionListener(this);
		this.mguiHinzufuegen.getMbtmProdukt().addActionListener(this);
		this.mguiHinzufuegen.getMbtmKonto().addActionListener(this);

		// starten des Hauptfensters
		this.mguiAbfrage.show(mguiAbfrage);

		// aes verschluesselung
		this.aes = new AES_verschluesselung();
	}

	public void actionPerformed(ActionEvent ae) {

		// auslesen des listeners
		String mstrCommand = ae.getActionCommand().toString();
		if (GUI_Abfrage.LOGIN.equals(mstrCommand)) {
			login();
		} else if (GUI_Main.HINZUFUEGEN.equals(mstrCommand)) {
			GUIHinzufuegen();
		} else if (GUI_Abfrage.ERSTELLEN.equals(mstrCommand)) {
			create();
		} else if (GUI_Hinzufuegen.HINZUFUEGENALL.equals(mstrCommand)) {
			AddAll();
		} else if (GUI_Hinzufuegen.HINZUFUEGENKONTO.equals(mstrCommand)) {
			AddKonto();
		} else if (GUI_Hinzufuegen.HINZUFUEGENMARKT.equals(mstrCommand)) {
			AddMarkt();
		} else if (GUI_Hinzufuegen.HINZUFUEGENPRODUKT.equals(mstrCommand)) {
			AddProdukt();
		} else if (GUI_Main.EXPORT.equals(mstrCommand)) {
			SQLExport();
		} else if (GUI_Main.LADEN.equals(mstrCommand)) {
			SQLImport();
		} else if (GUI_Main.AUSWAHL.equals(mstrCommand)) {
			print();
		}
	}

	@SuppressWarnings("deprecation")
	private void acces() {

		try {
			// neuen Vector erstellen
			this.mvecModel = new Vector<Object>();

			mstrUserName = mguiAbfrage.getMtxtMeta_Username().getText();
			mstrPasswort = mguiAbfrage.getMtxtMeta_passwort().getText();
			mstrDatenbankName = mguiAbfrage.getMtxtMeta_DatenabnkName()
					.getText();
			mstrHostName = mguiAbfrage.getMtxtMeta_DatenabnkServer().getText();
			mintPort = 3306;

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

	@SuppressWarnings("finally")
	private String SQLStatistic(String sql) {
		String resultString = "";
		try {

			// abfrage statement erstellen
			Statement query = mconCon.createStatement();

			// Query bearbeiten
			ResultSet result = null;

			// executeQuerry nur fuer selects
			result = query.executeQuery(sql);

			// speichern in Klasse
			while (result.next()) {
				resultString += result.getObject(1).toString() + "\t";
				resultString += result.getObject(2).toString() + "\t";
				resultString += result.getObject(3).toString() + "\t";

			}

			return resultString;
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {
			return resultString;
		}

	}

	private void SQLAbfrage(String sql) {

		try {
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
							result.getString("minimum"), result.getInt("K_ID"));
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

				else if (sql == Controll_Statments.konto.toString()) {
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

				} else if (sql == Controll_Statments.markt.toString()) {
					Model_Markt m = new Model_Markt(result.getString("m.name"),
							result.getString("m.postleitzahl"),
							result.getString("m.adresse"),
							result.getInt("m.entfernung"),
							result.getInt("m.M_ID"));

					mvecModel.add(m);

				} else if (sql == Controll_Statments.produkt.toString()) {
					Model_Produkt p = new Model_Produkt(
							result.getString("p.name"),
							result.getInt("p.gewicht"),
							result.getFloat("p.preis"), result.getInt("p.P_ID"));

					mvecModel.add(p);
				}

			}

			// scliessen des streams
			result.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Fail",
					JOptionPane.OK_CANCEL_OPTION);
		}
	}

	private void SQLModifizieren(String sql) {
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

	private void SQLNeuErstellen(String kuerzel) {

		try {
			// neue Datenbank erstellen
			Vector<String> mvecMod = Controll_Statments.toExtendString(kuerzel);
			mvecMod.add(Controll_Statments.commit.toString());

			for (int i = 0; i < mvecMod.size(); i++) {
				SQLModifizieren(mvecMod.get(i));
			}
		} finally {

		}
	}

	private void SQLExport() {

		try {
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
				} else if (mvecModel.get(i) instanceof Model_Einkauf) {
					mfioStream.write(((Model_Einkauf) mvecModel.get(i))
							.SQlerstellenAll());
					mfioStream.newLine();
				}
			}

			mfioStream.close();
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

	private void SQLImport() {
		try {
			FileReader file = new FileReader("../Backup.sql");
			BufferedReader mfosStream = new BufferedReader(file);
			String read = null;

			while ((read = mfosStream.readLine()) != null) {

				SQLModifizieren(read);

			}

			mfosStream.close();
		} catch (IOException ex) {
			ex.getMessage();
		}

	}

	@SuppressWarnings("deprecation")
	private void login() {
		// clearen des vectors
		this.mvecModel = new Vector<Object>();

		// einmaliges setzen des aes key
		aes.setkey(mguiAbfrage.getMtxtMeta_passwort().getText());

		// hiden des alten fensters
		mguiAbfrage.dispose();

		// Hauptgui starten
		// Das Main Window wird nur angezeigt wenn Daten fuer connection ok
		mguiMain.show(mguiMain);
		try {
			acces();

			// Daten auslesen
			auslesen();
			JOptionPane.showMessageDialog(null, "Login war erfolgreich",
					"Erfolg", JOptionPane.OK_CANCEL_OPTION);

		} finally {
			mguiAbfrage.clear();
		}
	}

	private void auslesen() {
		SQLAbfrage(Controll_Statments.all.toString());
		SQLAbfrage(Controll_Statments.konto.toString());
		SQLAbfrage(Controll_Statments.markt.toString());
		SQLAbfrage(Controll_Statments.produkt.toString());
	}

	@SuppressWarnings("unchecked")
	private void GUIHinzufuegen() {
		mguiHinzufuegen.show(mguiHinzufuegen);

		// erst comboboxen clearen
		mguiHinzufuegen.clear();

		// Comboboxen fuellen
		for (int i = 0; i < mvecModel.size(); i++) {
			if (mvecModel.get(i) instanceof Model_Produkt) {
				mguiHinzufuegen.getMcmbProdukt().addItem(
						((Model_Produkt) mvecModel.get(i)).getMstrName());

			} else if (mvecModel.get(i) instanceof Model_Konto) {

				mguiHinzufuegen.getMcmbKonto().addItem(
						((Model_Konto) mvecModel.get(i)).getMstrName());

			} else if (mvecModel.get(i) instanceof Model_Markt) {
				mguiHinzufuegen.getMcmbMarkt().addItem(
						((Model_Markt) mvecModel.get(i)).getMstrName());
			}
		}

	}

	/*
	 * @SuppressWarnings("unused") private void update() { String content =
	 * mguiMain.getTextArea().getText(); String[] tmp = content.split("\n");
	 * Vector<String[]> value = new Vector<>();
	 * 
	 * for (int i = 0; i < tmp.length; i++) { value.add(tmp[i].split("\t")); }
	 * Vector<Object> tmpObject = new Vector<Object>();
	 * 
	 * String selectedItem = mguiMain.getComboBox().getSelectedItem()
	 * .toString(); if (selectedItem == cmbAuswahl.Produkt.toString()) {
	 * tmpObject.clear(); // einzelne Produkte in vektor schreiben for (int i =
	 * 1; i < value.size(); i++) { tmpObject.add(new
	 * Model_Produkt(value.get(i)[0], Integer .valueOf(value.get(i)[1]),
	 * Float.valueOf(value.get(i)[2]), -1)); }
	 * 
	 * // vergleiche vector tmpObject mit mvec for (int i = 0; i <
	 * mvecModel.size(); i++) { if (mvecModel.get(i) instanceof Model_Produkt) {
	 * for (int j = 0; j < tmpObject.size(); j++) { ((Model_Produkt)
	 * mvecModel.get(i)) .equal((Model_Produkt) tmpObject.get(j)); break; } } }
	 * 
	 * } else if (selectedItem == cmbAuswahl.Markt.toString()) {
	 * tmpObject.clear();
	 * 
	 * for (int i = 1; i < value.size(); i++) { tmpObject.add(new
	 * Model_Markt(value.get(i)[0], value.get(i)[1], value.get(i)[2],
	 * Integer.valueOf(value.get(i)[3]), -1)); }
	 * 
	 * for (int i = 0; i < mvecModel.size(); i++) { if (mvecModel.get(i)
	 * instanceof Model_Markt) { for (int j = 0; j < tmpObject.size(); j++) {
	 * ((Model_Markt) mvecModel.get(i)) .equal((Model_Markt) tmpObject.get(j));
	 * break; } } }
	 * 
	 * } else if (selectedItem == cmbAuswahl.Konto.toString()) {
	 * tmpObject.clear();
	 * 
	 * for (int i = 1; i < value.size(); i++) { tmpObject.add(new
	 * Model_Konto(value.get(i)[0], value.get(i)[1], value.get(i)[2],
	 * value.get(i)[3], value.get(i)[4], -1)); }
	 * 
	 * for (int i = 0; i < mvecModel.size(); i++) { if (mvecModel.get(i)
	 * instanceof Model_Konto) { for (int j = 0; j < tmpObject.size(); j++) {
	 * ((Model_Konto) mvecModel.get(i)) .equal((Model_Konto) tmpObject.get(j));
	 * break; } } } }
	 * 
	 * // wenn !equal im tmpObject schreibe in DB for (int i = 0; i <
	 * mvecModel.size(); i++) { if (mvecModel.get(i) instanceof Model_Konto) {
	 * if (!((Model_Konto) mvecModel.get(i)).isMboolequal()) {
	 * 
	 * // nur not equal entfernen
	 * SQLModifizieren(Controll_Statments.kontoUpdate.toString() +
	 * ((Model_Konto) mvecModel.get(i)).getMintID() + ";");
	 * 
	 * // beonserheit insert into mit aes
	 * SQLModifizieren(Controll_Statments.kontoUpdateInsert .toString() + "\'" +
	 * aes.verschluesselnAES(((Model_Konto) mvecModel .get(i)).getMstrName()) +
	 * "\'" + "," + "\'" + aes.verschluesselnAES(((Model_Konto) mvecModel
	 * .get(i)).getMstrBLZ()) + "\'" + "," + "\'" +
	 * aes.verschluesselnAES(((Model_Konto) mvecModel .get(i)).getMstrKnr()) +
	 * "\'" + "," + "\'" + aes.verschluesselnAES(((Model_Konto) mvecModel
	 * .get(i)).getMintMin()) + "\'" + "," + ((Model_Konto)
	 * mvecModel.get(i)).getMintID() + ");");
	 * 
	 * } } else if (mvecModel.get(i) instanceof Model_Produkt) { if
	 * (!((Model_Produkt) mvecModel.get(i)).isMboolequal()) {
	 * 
	 * SQLModifizieren(Controll_Statments.produktUpdate.toString() +
	 * ((Model_Produkt) mvecModel.get(i)).getMintID() + ";");
	 * 
	 * SQLModifizieren(Controll_Statments.produktUpdateInsert .toString() + "\'"
	 * + ((Model_Produkt) mvecModel.get(i)).getMstrName() + "\'" + "," +
	 * ((Model_Produkt) mvecModel.get(i)) .getMintGewicht() + "," +
	 * ((Model_Produkt) mvecModel.get(i)).getMfltPreis() + "," +
	 * ((Model_Produkt) mvecModel.get(i)).getMintID() + ");");
	 * 
	 * } } else if (mvecModel.get(i) instanceof Model_Markt) { if
	 * (!((Model_Markt) mvecModel.get(i)).isMboolequal()) {
	 * 
	 * SQLModifizieren(Controll_Statments.marktUpdate.toString() +
	 * ((Model_Markt) mvecModel.get(i)).getMintID() + ";");
	 * SQLModifizieren(Controll_Statments.marktUpdateInsert .toString() + "\'" +
	 * ((Model_Markt) mvecModel.get(i)).getMstrName() + "\'" + "," + "\'" +
	 * ((Model_Markt) mvecModel.get(i)).getMstrPLZ() + "\'" + "," + "\'" +
	 * ((Model_Markt) mvecModel.get(i)).getMstrAdr() + "\'" + "," +
	 * ((Model_Markt) mvecModel.get(i)) .getMintEntfernung() + "," +
	 * ((Model_Markt) mvecModel.get(i)).getMintID() + ");");
	 * 
	 * } } // Daten auslesen auslesen(); } }
	 */
	private void create() {
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
			auslesen();

		} finally {
			mguiAbfrage.clear();
		}
	}

	private void AddAll() {
		// Konto, Produkt und Markt ermitteln
		Model_Konto k= null;
		Model_Markt m = null;
		Model_Produkt p= null;
		
		//objekte überschreiben
		for (int i = 0; i < mvecModel.size(); i++) {
			if (mvecModel.get(i) instanceof Model_Produkt) {
				if (((Model_Produkt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbProdukt().getSelectedItem()) {
					p = ((Model_Produkt) mvecModel.get(i));
				}
			} else if (mvecModel.get(i) instanceof Model_Konto) {

				if (((Model_Konto) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbKonto().getSelectedItem()) {
					k = ((Model_Konto) mvecModel.get(i));
				}
			} else if (mvecModel.get(i) instanceof Model_Markt) {
				if (((Model_Markt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbMarkt().getSelectedItem()) {
					m = ((Model_Markt) mvecModel.get(i));
				}

				// Einkauf hinzufuegen
				try {
					SQLModifizieren(Controll_Statments.allHinzufuegen
							.toString()
							+ mguiHinzufuegen.getMtxtAlles_Anzahl().getText()
							+ ","
							+ "STR_TO_DATE("
							+ "\'"
							+ mguiHinzufuegen.getMtxtAlles_Datum().getText()
									.toString()
							+ "\', '%d-%m-%Y') "
							+ ","
							+ m.getMintID() + "," + k.getMintID() + "," + p.getMintID() + ");");

					SQLAbfrage(Controll_Statments.all.toString());
				} finally {
					mguiHinzufuegen.clear();
				}

			}
		}
	}

	private void AddKonto() {
		try {
			SQLModifizieren(Controll_Statments.kontoHinzufuegen.toString()
					+ '\''
					+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_name()
							.getText())
					+ '\''
					+ ","
					+ '\''
					+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Blz()
							.getText())
					+ '\''
					+ ","
					+ '\''
					+ aes.verschluesselnAES(mguiHinzufuegen
							.getMtxtKonto_kontonummer().getText())
					+ '\''
					+ ","
					+ '\''
					+ aes.verschluesselnAES(mguiHinzufuegen
							.getMtxtKonto_Betrag().getText())
					+ '\''
					+ ","
					+ '\''
					+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Min()
							.getText()) + '\'' + ");");

			// Model Akutell halten
			SQLAbfrage(Controll_Statments.konto.toString());

		} finally {
			mguiHinzufuegen.clear();
		}

	}

	private void AddMarkt() {
		try {
			SQLModifizieren(Controll_Statments.marktHinzufuegen.toString()
					+ '\'' + mguiHinzufuegen.getMtxtMarkt_Name().getText()
					+ '\'' + "," + mguiHinzufuegen.getMtxtMarkt_Plz().getText()
					+ "," + '\''
					+ mguiHinzufuegen.getMtxtMarkt_Adresse().getText() + '\''
					+ "," + mguiHinzufuegen.getMtxtMarkt_Entfernung() + ");");

			// Model Akutell halten
			SQLAbfrage(Controll_Statments.markt.toString());
		} finally {
			mguiHinzufuegen.clear();
		}
	}

	private void AddProdukt() {
		try {
			SQLModifizieren(Controll_Statments.produktHinzufuegen.toString()
					+ '\'' + mguiHinzufuegen.getMtxtProdukt_Name().getText()
					+ '\'' + ","
					+ mguiHinzufuegen.getMtxtProdukt_Gewicht().getText() + ","
					+ mguiHinzufuegen.getMtxtProdukt_Preis().getText() + ");");

			// Model Akutell halten
			SQLAbfrage(Controll_Statments.produkt.toString());

		} finally {
			mguiHinzufuegen.clear();
		}
	}

	private void print() {

		if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Produkt
				.toString()) {

			Object[][] databaseInfo = null;
			Object[] columns = { "Produktname", "Preis", "Gewicht", "PK" };

			DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo,
					columns) {
				/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

				public Class<?> getColumnClass(int column) {
					Class<?> returnValue;
					if ((column >= 0) && (column < getColumnCount())) {
						returnValue = getValueAt(0, column).getClass();
					} else {
						returnValue = Object.class;
					}
					return returnValue;
				}
			};
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Produkt) {

					dTableModel.addRow(((Model_Produkt) mvecModel.get(i))
							.print());

				}
			}
			mguiMain.setTableModel(dTableModel);
		}

		else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Konto
				.toString()) {
			Object[][] databaseInfo = null;
			Object[] columns = { "Kontoinhaber", "Kontonummer", "Bankleitzahl",
					"Betrag", "Minimum", "PK" };

			DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo,
					columns) {
				/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

				public Class<?> getColumnClass(int column) {
					Class<?> returnValue;
					if ((column >= 0) && (column < getColumnCount())) {
						returnValue = getValueAt(0, column).getClass();
					} else {
						returnValue = Object.class;
					}
					return returnValue;
				}
			};
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Konto) {

					dTableModel.addRow(((Model_Konto) mvecModel.get(i))
							.print());

				}
			}
			mguiMain.setTableModel(dTableModel);
		}

		else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Markt
				.toString()) {
			Object[][] databaseInfo = null;
			Object[] columns = { "Name", "Adresse", "Entfernung",
					"Postleitzahl", "PK" };

			DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo,
					columns) {
				/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

				public Class<?> getColumnClass(int column) {
					Class<?> returnValue;
					if ((column >= 0) && (column < getColumnCount())) {
						returnValue = getValueAt(0, column).getClass();
					} else {
						returnValue = Object.class;
					}
					return returnValue;
				}
			};
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Markt) {

					dTableModel.addRow(((Model_Markt) mvecModel.get(i))
							.print());

				}
			}
			mguiMain.setTableModel(dTableModel);
		}
		else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Einkauf
				.toString()) {
			Object[][] databaseInfo = null;
			Object[] columns = { "Datum", "Anzahl", "Kontoinhaber",
					"Kontonummer", "Bankleitzahl",
					"Betrag", "Minimum","K_PK", 
					"Marktname", "Adresse", "Entfernung",
					"Postleitzahl","M_PK","Produktname", "Preis", "Gewicht","P_PK"};
					
			DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo,
					columns) {
				/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

				public Class<?> getColumnClass(int column) {
					Class<?> returnValue;
					if ((column >= 0) && (column < getColumnCount())) {
						returnValue = getValueAt(0, column).getClass();
					} else {
						returnValue = Object.class;
					}
					return returnValue;
				}
			};
			
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Einkauf) {

					dTableModel.addRow(((Model_Einkauf) mvecModel.get(i)).print());

				}
			}
			mguiMain.setTableModel(dTableModel);
		}
		
		
		
		
		/*
		 * (mguiMain.getComboBox().getSelectedItem().toString() ==
		 * cmbAuswahl.Statistik.toString()) {
		 * 
		 * mguiMain.getMcmbMonat().setEnabled(true); mguiMain.clear(); //
		 * hoechste kontostand -> niedirgste kontostand // minimum erreicht per
		 * konto int max = -1, min = 999; String name_max = null, name_min =
		 * null; Vector<String> name_minimumGrenze = new Vector<>();
		 * 
		 * for (Object element : mvecModel) {
		 * 
		 * if (element instanceof Model_Konto) { if
		 * (Integer.valueOf(((Model_Konto) element).getMstrBetrag()) < min) {
		 * min = Integer.valueOf(((Model_Konto) element).getMstrBetrag());
		 * name_min = ((Model_Konto) element).getMstrName(); } else { max =
		 * Integer.valueOf(((Model_Konto) element).getMstrBetrag()); name_max =
		 * ((Model_Konto) element).getMstrName();
		 * 
		 * }
		 * 
		 * if (Integer.valueOf(((Model_Konto) element).getMstrBetrag()) <=
		 * Integer .valueOf(((Model_Konto) element).getMintMin())) {
		 * name_minimumGrenze.addElement(((Model_Konto) element).getMstrName());
		 * } }
		 * 
		 * }
		 * 
		 * mstrContent += "Den niedrigsten Kontostand hat " + name_min + " mit "
		 * + min + " EURO\n"; mstrContent += "Den hoechsten Kontostand hat " +
		 * name_max + " mit " + max + " EURO\n";
		 * 
		 * for (int i = 0; i < name_minimumGrenze.size(); i++) mstrContent +=
		 * "Folgende Leute haben ihre Limitbegrenzung ueberschritten: " +
		 * name_minimumGrenze.get(i) + "\n";
		 * 
		 * Vector<String> bezeichner = new Vector<String>();
		 * bezeichner.add("Name\tadresse\tAVG(entfernung)");
		 * bezeichner.add("Name\tadresse\tmax(entfernung)");
		 * bezeichner.add("Name\tadresse\tmin(entfernung)");
		 * bezeichner.add("Marktname\tProduktname\tmax(anzahl)");
		 * bezeichner.add("Marktname\tProduktname\tmin(anzahl)");
		 * bezeichner.add("Marktname\tProduktname\tmax(datum)");
		 * bezeichner.add("Marktname\tProduktname\tmin(datum)");
		 * bezeichner.add("Produktname\tGewicht\tmax(preis)");
		 * bezeichner.add("Produktname\tGewicht\tmin(preis)");
		 * bezeichner.add("Produktname\tGewicht\tavg(preis)");
		 * 
		 * for (int i = 0; i < Controll_Statments.statistic().size(); i++) {
		 * mstrContent += bezeichner.get(i).toString() + "\n"; mstrContent +=
		 * SQLStatistic(Controll_Statments.statistic().get(i)) + "\n"; }
		 * 
		 * 
		 * if((mguiMain.getMcmbMonat().getSelectedItem().equals(cmbAuswahl.Q1.
		 * toString()))) { mstrContent +=
		 * "(Alles) Kontoinhaber\tProduktname\tMarktname\n"; String tmp =
		 * SQLStatistic(Controll_Statments.Q1.toString()); String myArray[] =
		 * tmp.split("\t"); //mstrContent +=
		 * aes.entschluesselnAES(myArray[0])+"\t"+myArray[1]+"\t"+myArray[2]; }
		 * else
		 * if((mguiMain.getMcmbMonat().getSelectedItem().equals(cmbAuswahl.Q2
		 * .toString()))) { mstrContent +=
		 * "(Alles) Kontoinhaber\tProduktname\tMarktname\n"; String tmp =
		 * SQLStatistic(Controll_Statments.Q2.toString()); String myArray[] =
		 * tmp.split("\t"); mstrContent +=
		 * aes.entschluesselnAES(myArray[0])+"\t"+myArray[1]+"\t"+myArray[2]; }
		 * else
		 * if((mguiMain.getMcmbMonat().getSelectedItem().equals(cmbAuswahl.Q3
		 * .toString()))) { mstrContent +=
		 * "(Alles) Kontoinhaber\tProduktname\tMarktname\n"; String tmp =
		 * SQLStatistic(Controll_Statments.Q3.toString()); String myArray[] =
		 * tmp.split("\t"); //mstrContent +=
		 * aes.entschluesselnAES(myArray[0])+"\t"+myArray[1]+"\t"+myArray[2]; }
		 * 
		 * } // fuellen der GUI mguiMain.setTextArea(mstrContent);
		 */
	}
}
