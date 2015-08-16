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

	@SuppressWarnings("static-access")
	public void start() throws SQLException, ClassNotFoundException, IOException {

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
		this.mguiMain.getMbtnupdate().addActionListener(this);
		this.mguiMain.getMcmbMonat().addActionListener(this);

		this.mguiHinzufuegen.getMbtmAlles().addActionListener(this);
		this.mguiHinzufuegen.getMbtmMarkt().addActionListener(this);
		this.mguiHinzufuegen.getMbtmProdukt().addActionListener(this);
		this.mguiHinzufuegen.getMbtmKonto().addActionListener(this);

		// starten des Hauptfensters
		this.mguiAbfrage.show(mguiAbfrage);

		this.aes = new AES_verschluesselung();
	}

	@SuppressWarnings({ "static-access", "unchecked", "deprecation", "null" })
	@Override
	public void actionPerformed(ActionEvent ae) {

		String mstrCommand = ae.getActionCommand().toString();
		if (mguiAbfrage.LOGIN.equals(mstrCommand)) {

			aes.setkey(mguiAbfrage.getMtxtMeta_passwort().getText());

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
				JOptionPane.showMessageDialog(null, "Login war erfolgreich",
						"Erfolg", JOptionPane.OK_CANCEL_OPTION);

			} catch (ClassNotFoundException | SQLException e) {
				System.err.println(e.getMessage() + " Daten leider fehlerhaft");
				JOptionPane.showMessageDialog(null, "Logindaten fehlerhaft\n"
						+ e.getMessage(), "Fail", JOptionPane.OK_CANCEL_OPTION);

			} finally {
				mguiAbfrage.clear();
			}
		} else if (mguiMain.HINZUFUEGEN.equals(mstrCommand)) {
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

		} else if (mguiMain.STATISTIK.equals(mstrCommand)) {

		} else if (mguiMain.UPDATE.equals(mstrCommand)) {
			String mstrevent = mguiMain.getComboBox().getSelectedItem()
					.toString();
			if (cmbAuswahl.Konto.toString().equals(mstrevent)) {
				String myarray[] = mguiMain.getTextArea().getText().split("\n");

				for (int ii = 1; ii < myarray.length; ii++) {

					String splitoftab[] = myarray[ii].split("\t");
					Model_Konto tmp = new Model_Konto(splitoftab[0],
							splitoftab[1], splitoftab[2], splitoftab[3],
							splitoftab[4], Integer.valueOf(splitoftab[5]));

					for (int i = 0; i < mvecModel.size(); i++) {
						if (mvecModel.get(i) instanceof Model_Konto) 
						if(((Model_Konto) mvecModel.get(i)).getMintID() == tmp.getMintID())
						
						{
							if (!((Model_Konto) mvecModel.get(i)).equals(tmp)) {
								((Model_Konto) mvecModel.get(i)).change(tmp);
							}
						}
					}
				}

			} else if (cmbAuswahl.Markt.toString().equals(mstrevent)) {

				String myarray[] = mguiMain.getTextArea().getText().split("\n");

				for (int ii = 1; ii < myarray.length; ii++) {

					String splitoftab[] = myarray[ii].split("\t");
					Model_Markt tmp = new Model_Markt(splitoftab[0],
							splitoftab[1], splitoftab[2],
							Integer.valueOf(splitoftab[3]), Integer.valueOf(splitoftab[4]));

					for (int i = 0; i < mvecModel.size(); i++) {
						if (mvecModel.get(i) instanceof Model_Markt) {
							if(((Model_Markt) mvecModel.get(i)).getMintID() == tmp.getMintID())
							{
							if (!((Model_Markt) mvecModel.get(i)).equals(tmp)) {
								((Model_Markt) mvecModel.get(i)).change(tmp);
							}
							}
						}
					}
				}
			} else if (cmbAuswahl.Produkt.toString().equals(mstrevent)) {

				String myarray[] = mguiMain.getTextArea().getText().split("\n");

				for (int ii = 1; ii < myarray.length; ii++) {

					String splitoftab[] = myarray[ii].split("\t");
					Model_Produkt tmp = new Model_Produkt(splitoftab[0],
							Integer.valueOf(splitoftab[1]),
							Float.valueOf(splitoftab[2]), Integer.valueOf(splitoftab[3]));

					for (int i = 0; i < mvecModel.size(); i++) {
						if (mvecModel.get(i) instanceof Model_Produkt) {
							if(((Model_Produkt) mvecModel.get(i)).getMintID() == tmp.getMintID())
							{
							if (!((Model_Produkt) mvecModel.get(i)).equals(tmp)) {
								((Model_Produkt) mvecModel.get(i)).change(tmp);
							}}
						}
					}
				}
			} else if (cmbAuswahl.Einkauf.toString().equals(mstrevent)) {

				String myarray[] = mguiMain.getTextArea().getText().split("\n");

				for (int ii = 1; ii < myarray.length; ii++) {

					String splitoftab[] = myarray[ii].split("\t");
					Model_Einkauf tmp = new Model_Einkauf(
							Integer.valueOf(splitoftab[0]), splitoftab[1],Integer.valueOf(splitoftab[2]));

					for (int i = 0; i < mvecModel.size(); i++) {
						if (mvecModel.get(i) instanceof Model_Einkauf) {
							if(((Model_Einkauf) mvecModel.get(i)).getMintID() == tmp.getMintID())
							{
							if (!((Model_Einkauf) mvecModel.get(i)).equals(tmp)) {
								((Model_Einkauf) mvecModel.get(i)).change(tmp);
							}
							}
						}
					}
				}
			}

			// veraenderungen schreiben
			for (int i = 0; i < mvecModel.size(); i++) {
				
					if (mvecModel.get(i) instanceof Model_Produkt) {
						if (((Model_Produkt)mvecModel.get(i)).isChange()) 
						{
						try {
							SQLModifizieren(Controll_Statments.produktUpdate
									.toString()
									+ ((Model_Produkt) mvecModel.get(i))
											.getMintID() + ";");
							SQLModifizieren(((Model_Produkt) mvecModel.get(i))
									.SQLerstellenProdukt());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}else if (mvecModel.get(i) instanceof Model_Konto) {
					if (((Model_Konto)mvecModel.get(i)).isChange()) 
					{
						try {
							SQLModifizieren(Controll_Statments.kontoUpdate
									.toString()
									+ ((Model_Konto) mvecModel.get(i))
											.getMintID() + ";");
							SQLModifizieren(((Model_Konto) mvecModel.get(i))
									.SQLerstellenKonto());
						} catch (SQLException e) {
							e.printStackTrace();
						}

					}} else if (mvecModel.get(i) instanceof Model_Markt) {
						if (((Model_Markt)mvecModel.get(i)).isChange()) 
						{
						try {
							SQLModifizieren(Controll_Statments.marktUpdate
									.toString()
									+ ((Model_Markt) mvecModel.get(i))
											.getMintID() + ";");
							SQLModifizieren(((Model_Markt) mvecModel.get(i))
									.SQLerstellenMarkt());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					}else if (mvecModel.get(i) instanceof Model_Einkauf) {
						if (((Model_Einkauf)mvecModel.get(i)).isChange()) 
						{
						try {
							SQLModifizieren(Controll_Statments.AllUpdate
									.toString()
									+ ((Model_Einkauf)mvecModel.get(i)).getMintIDMarkt()
									+ "and p_ID = "
									+ ((Model_Einkauf)mvecModel.get(i)).getMintIDProdukt()
									+ " and k_ID = "
									+ ((Model_Einkauf)mvecModel.get(i)).getMintIDKonto() + ";");

							SQLModifizieren(((Model_Einkauf)mvecModel.get(i)).SQlerstellenAll());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}}

			}
		}
			
		 else if (mguiAbfrage.ERSTELLEN.equals(mstrCommand)) {
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

				JOptionPane.showMessageDialog(null,
						"Viel Spass beim benutzen der Software", "Erfolg",
						JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(
						null,
						"Konto konnte nicht hinzugefuegt werden\n"
								+ e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);
			} finally {
				mguiAbfrage.clear();
			}
		} else if (mguiHinzufuegen.HINZUFUEGENALL.equals(mstrCommand)) {

			// Konto, Produkt und Markt PK ermitteln
			int K_ID = 0, M_ID = 0, P_ID = 0;
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Produkt) {
					if (((Model_Produkt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
							.getMcmbProdukt().getSelectedItem()) {
						P_ID = ((Model_Produkt) mvecModel.get(i)).getMintID();
					}
				} else if (mvecModel.get(i) instanceof Model_Konto) {

					if (((Model_Konto) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
							.getMcmbKonto().getSelectedItem()) {
						K_ID = ((Model_Konto) mvecModel.get(i)).getMintID();
					}
				} else if (mvecModel.get(i) instanceof Model_Markt) {
					if (((Model_Markt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
							.getMcmbMarkt().getSelectedItem()) {
						M_ID = ((Model_Markt) mvecModel.get(i)).getMintID();
					}

					// Einkauf hinzufuegen
					try {
						SQLModifizieren(Controll_Statments.allHinzufuegen
								.toString()
								+ mguiHinzufuegen.getMtxtAlles_Anzahl()
										.getText()
								+ ","
								+ "STR_TO_DATE("
								+ "\'"
								+ mguiHinzufuegen.getMtxtAlles_Datum()
										.getText().toString()
								+ "\', '%d-%m-%Y') "
								+ ","
								+ M_ID
								+ ","
								+ K_ID
								+ "," + P_ID + ");");

						SQLAbfrage(Controll_Statments.all.toString());
						JOptionPane.showMessageDialog(null,
								"Einkauf wure hinzugefuegt", "Erfolg",
								JOptionPane.OK_CANCEL_OPTION);

					} catch (SQLException e) {
						JOptionPane.showMessageDialog(
								null,
								"Einkauf konnte nicht hinzugefuegt werden\n"
										+ e.getMessage(), "Fail",
								JOptionPane.OK_CANCEL_OPTION);

					} finally {
						mguiHinzufuegen.clear();
					}

				}
			}
		} else if (mguiHinzufuegen.HINZUFUEGENKONTO.equals(mstrCommand)) {
			try {
				SQLModifizieren(Controll_Statments.kontoHinzufuegen.toString()
						+ '\'' + aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_name().getText())
						+ '\'' + ","+ '\''
						+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Blz().getText()) + '\''+","
						+ '\''+aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_kontonummer().getText())+'\''
						+ ","+'\'' + aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Betrag().getText())+'\''
						+ "," +'\''+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Min().getText())+'\''
						+ ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.konto.toString());
				JOptionPane.showMessageDialog(null, "Konto wure hinzugefuegt",
						"Erfolg", JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(
						null,
						"Konto konnte nicht hinzugefuegt werden\n"
								+ e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);
			} finally {
				mguiHinzufuegen.clear();
			}

		} else if (mguiHinzufuegen.HINZUFUEGENMARKT.equals(mstrCommand)) {
			try {
				SQLModifizieren(Controll_Statments.marktHinzufuegen.toString()
						+ '\'' + mguiHinzufuegen.getMtxtMarkt_Name().getText()
						+ '\'' + ","
						+ mguiHinzufuegen.getMtxtMarkt_Plz().getText() + ","
						+ '\''
						+ mguiHinzufuegen.getMtxtMarkt_Adresse().getText()
						+ '\'' + ","
						+ mguiHinzufuegen.getMtxtMarkt_Entfernung() + ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.markt.toString());
				JOptionPane.showMessageDialog(null, "Markt wure hinzugefuegt",
						"Erfolg", JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(
						null,
						"Markt konnte nicht hinzugefuegt werden\n"
								+ e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);
			} finally {
				mguiHinzufuegen.clear();
			}

		} else if (mguiHinzufuegen.HINZUFUEGENPRODUKT.equals(mstrCommand)) {
			try {
				SQLModifizieren(Controll_Statments.produktHinzufuegen
						.toString()
						+ '\''
						+ mguiHinzufuegen.getMtxtProdukt_Name().getText()
						+ '\''
						+ ","
						+ mguiHinzufuegen.getMtxtProdukt_Gewicht().getText()
						+ ","
						+ mguiHinzufuegen.getMtxtProdukt_Preis().getText()
						+ ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.produkt.toString());
				JOptionPane.showMessageDialog(null, "Produkt wure hinzugefuegt",
						"Erfolg", JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(
						null,
						"Produkt konnte nicht hinzugefuegt werden\n"
								+ e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);
			} finally {
				mguiHinzufuegen.clear();
			}

		} else if (mguiMain.EXPORT.equals(mstrCommand)) {
			try {
				SQLExport();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (mguiMain.LADEN.equals(mstrCommand)) {
			try {
				SQLImport();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		} else if (mguiMain.AUSWAHL.equals(mstrCommand)) {

			// fuellen der anzeige box
			String mstrContent = null;
			if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Einkauf
					.toString()) {
				mstrContent = "Anzahl\tDatum\tProdukt\tKonto\tMarkt\n";
				mguiMain.getMcmbMonat().setEnabled(true);

				for (int i = 0; i < mvecModel.size(); i++) {
					{
						mstrContent += ((Model_Einkauf)mvecModel.get(i)).print() + "\n";
						if (mvecModel.get(i) instanceof Model_Produkt) {
							if (((Model_Produkt) mvecModel.get(i)).getMintID() == 
									((Model_Einkauf)mvecModel.get(i)).getMintIDProdukt())
							{
								mstrContent += ((Model_Produkt) mvecModel
										.get(i)).print() + "\n";
							} else if (mvecModel.get(i) instanceof Model_Konto)
								if (((Model_Konto) mvecModel.get(i))
										.getMintID() == 
										
												((Model_Einkauf)mvecModel.get(i)).getMintIDKonto()) {
									mstrContent += ((Model_Konto) mvecModel
											.get(i)).print() + "\n";
								} else if (mvecModel.get(i) instanceof Model_Markt)
									if (((Model_Markt) mvecModel.get(i))
											.getMintID() == ((Model_Einkauf)mvecModel.get(i))
											.getMintIDMarkt()) {
										mstrContent += ((Model_Markt) mvecModel
												.get(i)).print() + "\n";
									}
						}

					}
				}
			} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Produkt
					.toString()) {
				mstrContent = "Name\tGewicht\tPreis\n";
				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Produkt) {
						mstrContent += ((Model_Produkt) mvecModel.get(i))
								.print() + "\n";
					}
				}
			} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Markt
					.toString()) {
				mstrContent = "Name\tPostLeitZahl\tAdresse\tEntfernung\n";
				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Markt) {
						mstrContent += ((Model_Markt) mvecModel.get(i)).print()
								+ "\n";
					}
				}
			} 
			else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Konto
					.toString()) {
				mstrContent = "Name\tBankLeitZahl\tKontonummer\tBetrag\tMinimum\n";

				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Konto) {
						mstrContent += ((Model_Konto) mvecModel.get(i)).print()
								+ "\n";
					}
				}
			}
			else if(mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Statistik.toString())
			{
				//hoechste kontostand -> niedirgste kontostand
				//minimum erreicht per konto
				int max = -1, min = 999;
				String name_max = null, name_min = null;
				Vector<String> name_minimumGrenze = null;
				
				for (Object element : mvecModel) {
				
					if(element instanceof Model_Konto)
					{
						if(Integer.valueOf(((Model_Konto) element).getMstrBetrag()) < min)
						{
							min = Integer.valueOf(((Model_Konto) element).getMstrBetrag());
							name_min = ((Model_Konto) element).getMstrName();
						}
						else
						{
							max = Integer.valueOf(((Model_Konto) element).getMstrBetrag());
							name_max = ((Model_Konto) element).getMstrName();

						}
						
						if(Integer.valueOf(((Model_Konto) element).getMstrBetrag()) <= Integer.valueOf(((Model_Konto) element).getMintMin()))
								{
							name_minimumGrenze.addElement(((Model_Konto) element).getMstrName());
								}		
					}
					
				}
				
				mstrContent += "Den niedrigsten Kontostand hat "+name_min+" mit " +min+" EURO\n";
				mstrContent += "Den hoechsten Kontostand hat "+name_max+" mit " +max+" EURO\n";
				for(int i=0;i<name_minimumGrenze.size();i++)
				mstrCommand += "Folgende Leute haben ihre Limitbegrenzung ueberschritten: "+name_minimumGrenze.get(i)+"\n";
				
				
				for(int i=0;i<Controll_Statments.statistic().size();i++)
					try {
						SQLModifizieren(Controll_Statments.statistic().get(i));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				
			
				
			
				
				
			}
			// fuellen der GUI
			mguiMain.setTextArea(mstrContent);
		}

	}

	@SuppressWarnings("deprecation")
	private void acces() throws ClassNotFoundException, SQLException {

		// neuen Vector erstellen
		this.mvecModel = new Vector<Object>();

		mstrUserName = mguiAbfrage.getMtxtMeta_Username().getText();
		mstrPasswort = mguiAbfrage.getMtxtMeta_passwort().getText();
		mstrDatenbankName = mguiAbfrage.getMtxtMeta_DatenabnkName().getText();
		mstrHostName = mguiAbfrage.getMtxtMeta_DatenabnkServer().getText();
		mintPort = 3306;

		// Datenbanktreiber fuer JDBC Schnittstellen laden.
		Class.forName("com.mysql.jdbc.Driver");

		// Verbindung zur JDBC-Datenbank herstellen.
		mconCon = DriverManager.getConnection("jdbc:mysql://" + mstrHostName + ":" + mintPort + "/" + mstrDatenbankName
				+ "?" + "user=" + mstrUserName + "&" + "password=" + mstrPasswort);

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
				Model_Konto k = new Model_Konto(result.getString("betrag"), result.getString("Kontoinhaber"),
						result.getString("bankleitzahl"), result.getString("kontonummer"), result.getString("minimum"),
						result.getInt("K_ID"));
				Model_Produkt p = new Model_Produkt(result.getString("Produktname"), result.getInt("gewicht"),
						result.getFloat("preis"), result.getInt("P_ID"));
				Model_Markt m = new Model_Markt(result.getString("Marktname"), result.getString("postleitzahl"),
						result.getString("adresse"), result.getInt("entfernung"), result.getInt("M_ID"));
				Model_Einkauf me = new Model_Einkauf(result.getInt("anzahl"), result.getString("datum"),
						result.getInt("E_ID"));

				// verknuepfung reaisieren
				me.ModelArray(k.getMintID(), p.getMintID(), m.getMintID());
				mvecModel.add(me);
			}

			else if (sql == Controll_Statments.konto.toString()) {
				Model_Konto k = new Model_Konto(aes.entschluesselnAES(result.getString("k.name")),
						aes.entschluesselnAES(result.getString("k.bankleitzahl")),
						aes.entschluesselnAES(result.getString("k.kontonummer")),
						aes.entschluesselnAES(result.getString("k.betrag")),
						aes.entschluesselnAES(result.getString("k.minimum")), result.getInt("k.K_ID"));

				mvecModel.add(k);

			} else if (sql == Controll_Statments.markt.toString()) {
				Model_Markt m = new Model_Markt(result.getString("m.name"), result.getString("m.postleitzahl"),
						result.getString("m.adresse"), result.getInt("m.entfernung"), result.getInt("m.M_ID"));

				mvecModel.add(m);

			} else if (sql == Controll_Statments.produkt.toString()) {
				Model_Produkt p = new Model_Produkt(result.getString("p.name"), result.getInt("p.gewicht"),
						result.getFloat("p.preis"), result.getInt("p.P_ID"));

				mvecModel.add(p);
			}

		}

		// scliessen des streams
		result.close();
	}

	private void SQLModifizieren(String sql) throws SQLException {
		System.out.println(sql);
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

				mfioStream.write(((Model_Produkt) mvecModel.get(i)).SQLerstellenProdukt());
				mfioStream.newLine();

			} else if (mvecModel.get(i) instanceof Model_Konto) {

				mfioStream.write(((Model_Konto) mvecModel.get(i)).SQLerstellenKonto());
				mfioStream.newLine();

			} else if (mvecModel.get(i) instanceof Model_Markt) {
				mfioStream.write(((Model_Markt) mvecModel.get(i)).SQLerstellenMarkt());
				mfioStream.newLine();
			} else {
				// #TODO kein enter in diesen block?
				mfioStream.write(((Model_Einkauf) mvecModel.get(i)).SQlerstellenAll());
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
