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
		this.mguiMain.getBtnUpdate().addActionListener(this);

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
		if (cmbAuswahl.LoginAbfrageAktion.toString().equals(mstrCommand)) {
			login();
		} else if (cmbAuswahl.ErstellenAbfrageAktion.toString().equals(mstrCommand)) {
			create();
		} else if (cmbAuswahl.AllHinzufuegenAktion.toString().equals(mstrCommand)) {
			AddAll();
		} else if (cmbAuswahl.KontoHinzufuegenAktion.toString().equals(mstrCommand)) {
			AddKonto();
		} else if (cmbAuswahl.MarktHinzufuegenAktion.toString().equals(mstrCommand)) {
			AddMarkt();
		} else if (cmbAuswahl.ProduktHinzufuegenAktion.toString().equals(mstrCommand)) {
			AddProdukt();
		} else if (cmbAuswahl.ExportMainAktion.toString().equals(mstrCommand)) {
			SQLExport();
		} else if (cmbAuswahl.LadenMainAktion.toString().equals(mstrCommand)) {
			SQLImport();
		} else if (cmbAuswahl.AuswahlMainAktion.toString().equals(mstrCommand)) {
			print();
		}
		else if(cmbAuswahl.UpdateMainAktion.toString().equals(mstrCommand))
		{
			update();
		}
		else if (cmbAuswahl.HinzufuegenMainAktion.toString().equals(mstrCommand)) {
			GUIHinzufuegen();
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

				if (sql == Controll_Statments.ViewAll()){
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

				} else if (sql == Controll_Statments.ViewMarkt()) {
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
			Vector<String> mvecMod = Controll_Statments.createDatenbank(kuerzel);

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
							.SQLerstellen());
					mfioStream.newLine();

				} else if (mvecModel.get(i) instanceof Model_Konto) {

					mfioStream.write(((Model_Konto) mvecModel.get(i))
							.SQLerstellen(aes));
					mfioStream.newLine();

				} else if (mvecModel.get(i) instanceof Model_Markt) {
					mfioStream.write(((Model_Markt) mvecModel.get(i))
							.SQLerstellen());
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
		mvecModel.clear();
		SQLAbfrage(Controll_Statments.ViewAll());
		SQLAbfrage(Controll_Statments.ViewKonto());
		SQLAbfrage(Controll_Statments.ViewMarkt());
		SQLAbfrage(Controll_Statments.ViewProdukt());
	}

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

	private void update() { 
	
		//aus table in toUpdate
		Vector<Object> toUpdate = new Vector<Object>();
		if(mguiMain.getComboBox().getSelectedItem() == cmbAuswahl.Produkt.toString())
		{
			for(int i=0;i<mguiMain.getMtblTable().getRowCount();i++)
			{
					
				toUpdate.add(new Model_Produkt(
						mguiMain.getMtblTable().getValueAt(i, 0).toString(), 
						Integer.valueOf( mguiMain.getMtblTable().getValueAt(i, 1).toString()),
						Float.valueOf( mguiMain.getMtblTable().getValueAt(i, 2).toString()), 
						Integer.valueOf(mguiMain.getMtblTable().getValueAt(i, 3).toString())));
			}
		}
		else if(mguiMain.getComboBox().getSelectedItem() == cmbAuswahl.Konto.toString())
		{
			for(int i=0;i<mguiMain.getMtblTable().getRowCount();i++)
			{
				toUpdate.add(new Model_Konto(
						mguiMain.getMtblTable().getValueAt(i, 0).toString(),
						mguiMain.getMtblTable().getValueAt(i, 1).toString(),
						mguiMain.getMtblTable().getValueAt(i, 2).toString(),
						mguiMain.getMtblTable().getValueAt(i, 3).toString(),
						mguiMain.getMtblTable().getValueAt(i, 4).toString(),
						Integer.valueOf(mguiMain.getMtblTable().getValueAt(i, 5).toString())));
			}
		}
		else if(mguiMain.getComboBox().getSelectedItem() == cmbAuswahl.Markt.toString())
		{
			for(int i=0;i<mguiMain.getMtblTable().getRowCount();i++)
			{
				toUpdate.add(new Model_Markt(
						mguiMain.getMtblTable().getValueAt(i, 0).toString(),
						mguiMain.getMtblTable().getValueAt(i, 1).toString(),
						mguiMain.getMtblTable().getValueAt(i, 2).toString(),
						Integer.valueOf(mguiMain.getMtblTable().getValueAt(i, 3).toString()),
						Integer.valueOf(mguiMain.getMtblTable().getValueAt(i, 4).toString())));
			}
		}
		
		//prüfen auf gleichheit
		for (Object element : toUpdate) {
			
				for(int i=0;i<mvecModel.size();i++)
				{
					if(mvecModel.get(i) instanceof Model_Produkt && element instanceof Model_Produkt)
					{
						if(((Model_Produkt)mvecModel.get(i)).getMintID() == ((Model_Produkt)element).getMintID())
						{
							((Model_Produkt)mvecModel.get(i)).equal(((Model_Produkt)element));
						}
			
					}
					else if(mvecModel.get(i) instanceof Model_Konto && element instanceof Model_Konto)
					{
						if(((Model_Konto)mvecModel.get(i)).getMintID() == ((Model_Konto)element).getMintID())
						{
							((Model_Konto)mvecModel.get(i)).equal(((Model_Konto)element));
						}
			
					}
					else if(mvecModel.get(i) instanceof Model_Markt && element instanceof Model_Markt)
					{
						if(((Model_Markt)mvecModel.get(i)).getMintID() == ((Model_Markt)element).getMintID())
						{
							((Model_Markt)mvecModel.get(i)).equal(((Model_Markt)element));
						}
			
					}
					
				}
		}
	
		//schreibe in db
		for(Object element : mvecModel)
		{
			if(element instanceof Model_Produkt)
			{
				if(!((Model_Produkt)element).isMboolequal())
				{
					SQLModifizieren(((Model_Produkt)element).SQLentfernen());
				}
			}
			else if(element instanceof Model_Konto)
			{
				if(!((Model_Konto)element).isMboolequal())
				{
					SQLModifizieren(((Model_Konto)element).SQLentfernen());
				}
			}
			else if(element instanceof Model_Markt)
			{
				if(!((Model_Markt)element).isMboolequal())
				{
					SQLModifizieren(((Model_Markt)element).SQLentfernen());
				}
			}
		}
		//abfragen der db
		auslesen();
		}
	
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
					SQLModifizieren(Controll_Statments.AddAlles(
							Integer.valueOf(mguiHinzufuegen.getMtxtAlles_Anzahl().getText()), mguiHinzufuegen.getMtxtAlles_Datum().getText(),
							k.getMintID(), p.getMintID(),  m.getMintID()));
						
							
						auslesen();
				} finally {
					mguiHinzufuegen.clear();
				}

			}
		}
	}

	private void AddKonto() {
		try {
			SQLModifizieren(Controll_Statments.AddKonto(mguiHinzufuegen.getMtxtKonto_name()
					.getText(), mguiHinzufuegen.getMtxtKonto_Blz()
					.getText(), mguiHinzufuegen
					.getMtxtKonto_kontonummer().getText(), mguiHinzufuegen
					.getMtxtKonto_Betrag().getText(), mguiHinzufuegen.getMtxtKonto_Min()
					.getText(), aes));
				

			// Model Akutell halten
			auslesen();

		} finally {
			mguiHinzufuegen.clear();
		}

	}

	private void AddMarkt() {
		try {
			SQLModifizieren(Controll_Statments.AddMarkt(mguiHinzufuegen.getMtxtMarkt_Name().getText(), 
					mguiHinzufuegen.getMtxtMarkt_Plz().getText(), 
					mguiHinzufuegen.getMtxtMarkt_Adresse().getText(), Integer.valueOf(mguiHinzufuegen.getMtxtMarkt_Entfernung().getText())));
			

			// Model Akutell halten
			auslesen();
		} finally {
			mguiHinzufuegen.clear();
		}
	}

	private void AddProdukt() {
		try {
			SQLModifizieren(Controll_Statments.AddProdukt(mguiHinzufuegen.getMtxtProdukt_Name().getText(),
					Integer.valueOf(mguiHinzufuegen.getMtxtProdukt_Gewicht().getText()),
					Float.valueOf(mguiHinzufuegen.getMtxtProdukt_Preis().getText())));
			
			// Model Akutell halten
			auslesen();
		} finally {
			mguiHinzufuegen.clear();
		}
	}

	private void print() {
		mguiMain.getMcmbMonat().setEnabled(false);

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
		else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Statistik
				.toString()) {
			
			mguiMain.getMcmbMonat().setEnabled(true);
			Object[][] databaseInfo = null;
			Object[] columns = {"info"};
					
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
				
			  Object [][] statistic = getStatistic();
			  
			  for(int i=0;i<statistic.length;i++)
			  {
				dTableModel.addRow(statistic[i]);
			  }
			
			
			mguiMain.setTableModel(dTableModel);
		}
		
		 
	}

	private Object[][] getStatistic()
{
	  int max = -1, min = 999; 
	  String name_max = null, name_min = null; 
	  Vector<Object> dataReturn = new Vector<>();
	  
			 
			  for (Object element : mvecModel) {
			  
			  if (element instanceof Model_Konto) 
			  { 
				  if (Integer.valueOf(((Model_Konto) element).getMstrBetrag()) < min) 
				  {
					  min = Integer.valueOf(((Model_Konto) element).getMstrBetrag());
					  name_min = ((Model_Konto) element).getMstrName(); 
			  } 
				  else
				  { 
					  max =  Integer.valueOf(((Model_Konto) element).getMstrBetrag());
					  name_max = ((Model_Konto) element).getMstrName();
			  }
			  
			  if (Integer.valueOf(((Model_Konto) element).getMstrBetrag()) <=  Integer .valueOf(((Model_Konto) element).getMintMin())) 
			  {
				  
				 
				  dataReturn.add("Person: "+((Model_Konto) element).getMstrName()+" hat sein Limit überschritten!"
						  +"Betrag: "+Integer.valueOf(((Model_Konto) element).getMstrBetrag())
								  +"Minimum: "+ Integer .valueOf(((Model_Konto) element).getMintMin()));
			  } 
			 }
			  
			  }
			  dataReturn.add("Den niedrigsten Kontostand hat " + name_min + " mit "
			  + min + " EURO\n");
			  
			  dataReturn.add("Den hoechsten Kontostand hat " + name_max + " mit "
					  + max + " EURO\n");

			  
			  Vector<String> bezeichner = new Vector<String>();
			  bezeichner.add("Name\tadresse\tAVG(entfernung): ");
			  bezeichner.add("Name\tadresse\tmax(entfernung): ");
			  bezeichner.add("Name\tadresse\tmin(entfernung): ");
			  bezeichner.add("Marktname\tProduktname\tmax(anzahl): ");
			  bezeichner.add("Marktname\tProduktname\tmin(anzahl): ");
			  bezeichner.add("Marktname\tProduktname\tmax(datum): ");
			  bezeichner.add("Marktname\tProduktname\tmin(datum): ");
			  bezeichner.add("Produktname\tGewicht\tmax(preis): ");
			  bezeichner.add("Produktname\tGewicht\tmin(preis): ");
			  bezeichner.add("Produktname\tGewicht\tavg(preis): ");
			  
			  for (int i = 0; i < Controll_Statments.statistic().size(); i++) {
				  dataReturn.add(bezeichner.get(i).toString()+"\t"+SQLStatistic(Controll_Statments.statistic().get(i)));
			
			  }
			  
			  
			 if((mguiMain.getMcmbMonat().getSelectedItem().equals(cmbAuswahl.Q1.toString()))) 
			 {
				 String toComp = SQLStatistic(Controll_Statments.Q1.toString());
				 if(toComp != "")
				 {
					 dataReturn.add("Einkauf in Quartal 1: " + toComp);

				 }
			  }
			  else if((mguiMain.getMcmbMonat().getSelectedItem().equals(cmbAuswahl.Q2.toString()))) { 
				  String toComp = SQLStatistic(Controll_Statments.Q2.toString());
					 if(toComp != "")
					 {
						 dataReturn.add("Einkauf in Quartal 2: " + toComp);
						  }
			  }
			  else if((mguiMain.getMcmbMonat().getSelectedItem().equals(cmbAuswahl.Q3.toString()))) 
			  { 
				  String toComp = SQLStatistic(Controll_Statments.Q3.toString());
					 if(toComp != "")
					 {
						 dataReturn.add("Einkauf in Quartal 3: " + toComp);
						  }
			  }
			  
			 //umwandeln von vector<Object> in Object[][]
			  Object [][] returnObject = new Object[dataReturn.size()][1];
			  
			  for (int i=0;i<dataReturn.size();i++)
			  {
				  returnObject[i][0] = dataReturn.get(i);
			  }
			  
			  
			  
			  
			  return returnObject;
			  
			  } 
	
}
