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

	@SuppressWarnings({ "static-access", "unchecked", "deprecation" })
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
				JOptionPane.showMessageDialog(null, "Login war erfolgreich", "Erfolg", JOptionPane.OK_CANCEL_OPTION);

			} catch (ClassNotFoundException | SQLException e) {
				System.err.println(e.getMessage() + " Daten leider fehlerhaft");
				JOptionPane.showMessageDialog(null, "Logindaten fehlerhaft\n" + e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);

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
					mguiHinzufuegen.getMcmbProdukt().addItem(((Model_Produkt) mvecModel.get(i)).getMstrName());

				} else if (mvecModel.get(i) instanceof Model_Konto) {

					mguiHinzufuegen.getMcmbKonto().addItem(((Model_Konto) mvecModel.get(i)).getMstrName());

				} else if (mvecModel.get(i) instanceof Model_Markt) {
					mguiHinzufuegen.getMcmbMarkt().addItem(((Model_Markt) mvecModel.get(i)).getMstrName());
				}
			}

		} else if (mguiMain.UPDATE.equals(mstrCommand)) {

			String content = mguiMain.getTextArea().getText();
			String[] tmp = content.split("\n");
			Vector<String[]> value = new Vector<>();

			for (int i = 0; i < tmp.length; i++) {
				value.add(tmp[i].split("\t"));
			}
			Object[] tmpObject = new Object[value.size()];
			Object selectedItem = mguiMain.getComboBox().getSelectedItem();

			if (selectedItem == cmbAuswahl.Produkt) {

				for (int i = 0; i < value.size(); i++) {
					tmpObject[i] = new Model_Produkt(value.get(i)[0], Integer.valueOf(value.get(i)[1]),
							Float.valueOf(value.get(i)[2]), -1);
				}

				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Produkt) {
						for (int j = 0; j < tmpObject.length; j++) {
							((Model_Produkt) mvecModel.get(i)).equal((Model_Produkt) tmpObject[j]);
							break;
						}
					}
				}

			} else if (selectedItem == cmbAuswahl.Markt) {

				for (int i = 0; i < value.size(); i++) {
					tmpObject[i] = new Model_Markt(value.get(i)[0], value.get(i)[1], value.get(i)[2],
							Integer.valueOf(value.get(i)[3]), -1);
				}

				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Markt) {
						for (int j = 0; j < tmpObject.length; j++) {
							((Model_Markt) mvecModel.get(i)).equal((Model_Markt) tmpObject[j]);
							break;
						}
					}
				}

			} else if (selectedItem == cmbAuswahl.Konto) {

				for (int i = 0; i < value.size(); i++) {
					tmpObject[i] = new Model_Konto(value.get(i)[0], value.get(i)[1], value.get(i)[2], value.get(i)[3],
							value.get(i)[4], -1);
				}

				for (int i = 0; i < mvecModel.size(); i++) {
					if (mvecModel.get(i) instanceof Model_Konto) {
						for (int j = 0; j < tmpObject.length; j++) {
							((Model_Konto) mvecModel.get(i)).equal((Model_Konto) tmpObject[j]);
							break;
						}
					}
				}
			}

			// wenn !equal im tmpObject schreibe in DB
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Konto) {
					if (!((Model_Konto) mvecModel.get(i)).isMboolequal()) {
						try {
							SQLModifizieren(Controll_Statments.kontoUpdate.toString()
									+ ((Model_Konto) mvecModel.get(i)).getMintID() + ");");

							SQLModifizieren(Controll_Statments.kontoUpdateInsert.toString()
									+ aes.verschluesselnAES(((Model_Konto) mvecModel.get(i)).getMstrName()) + ","
									+ aes.verschluesselnAES(((Model_Konto) mvecModel.get(i)).getMstrBLZ()) + ","
									+ aes.verschluesselnAES(((Model_Konto) mvecModel.get(i)).getMstrKnr()) + ","
									+ aes.verschluesselnAES(((Model_Konto) mvecModel.get(i)).getMintMin()) + ","
									+ ((Model_Konto) mvecModel.get(i)).getMintID() + ");");

						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				} else if (mvecModel.get(i) instanceof Model_Produkt) {
					if (!((Model_Produkt) mvecModel.get(i)).isMboolequal()) {

						try {
							SQLModifizieren(Controll_Statments.produktUpdate.toString()
									+ ((Model_Produkt) mvecModel.get(i)).getMintID() + ");");

							SQLModifizieren(Controll_Statments.produktUpdateInsert.toString()
									+ ((Model_Produkt) mvecModel.get(i)).getMstrName() + ","
									+ ((Model_Produkt) mvecModel.get(i)).getMintGewicht() + ","
									+ ((Model_Produkt) mvecModel.get(i)).getMfltPreis() + ","
									+ ((Model_Produkt) mvecModel.get(i)).getMintID() + ");");

						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				} else if (mvecModel.get(i) instanceof Model_Markt) {
					if (!((Model_Markt) mvecModel.get(i)).isMboolequal()) {

						try {
							SQLModifizieren(Controll_Statments.marktUpdate.toString()
									+ ((Model_Markt) mvecModel.get(i)).getMintID() + ");");

							SQLModifizieren(Controll_Statments.marktUpdateInsert.toString()
									+ ((Model_Markt) mvecModel.get(i)).getMstrName() + ","
									+ ((Model_Markt) mvecModel.get(i)).getMstrPLZ() + ","
									+ ((Model_Markt) mvecModel.get(i)).getMstrAdr() + ","
									+ ((Model_Markt) mvecModel.get(i)).getMintEntfernung() + ","
									+ ((Model_Markt) mvecModel.get(i)).getMintID() + ");");

						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				}
			}

		} else if (mguiAbfrage.ERSTELLEN.equals(mstrCommand)) {
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

				JOptionPane.showMessageDialog(null, "Viel Spass beim benutzen der Software", "Erfolg",
						JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Konto konnte nicht hinzugefuegt werden\n" + e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);
			} finally {
				mguiAbfrage.clear();
			}
		} else if (mguiHinzufuegen.HINZUFUEGENALL.equals(mstrCommand)) {

			// Konto, Produkt und Markt PK ermitteln
			int K_ID = 0, M_ID = 0, P_ID = 0;
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Produkt) {
					if (((Model_Produkt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen.getMcmbProdukt()
							.getSelectedItem()) {
						P_ID = ((Model_Produkt) mvecModel.get(i)).getMintID();
					}
				} else if (mvecModel.get(i) instanceof Model_Konto) {

					if (((Model_Konto) mvecModel.get(i)).getMstrName() == mguiHinzufuegen.getMcmbKonto()
							.getSelectedItem()) {
						K_ID = ((Model_Konto) mvecModel.get(i)).getMintID();
					}
				} else if (mvecModel.get(i) instanceof Model_Markt) {
					if (((Model_Markt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen.getMcmbMarkt()
							.getSelectedItem()) {
						M_ID = ((Model_Markt) mvecModel.get(i)).getMintID();
					}

					// Einkauf hinzufuegen
					try {
						SQLModifizieren(Controll_Statments.allHinzufuegen.toString()
								+ mguiHinzufuegen.getMtxtAlles_Anzahl().getText() + "," + "STR_TO_DATE(" + "\'"
								+ mguiHinzufuegen.getMtxtAlles_Datum().getText().toString() + "\', '%d-%m-%Y') " + ","
								+ M_ID + "," + K_ID + "," + P_ID + ");");

						SQLAbfrage(Controll_Statments.all.toString());
						JOptionPane.showMessageDialog(null, "Einkauf wure hinzugefuegt", "Erfolg",
								JOptionPane.OK_CANCEL_OPTION);

					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"Einkauf konnte nicht hinzugefuegt werden\n" + e.getMessage(), "Fail",
								JOptionPane.OK_CANCEL_OPTION);

					} finally {
						mguiHinzufuegen.clear();
					}

				}
			}
		} else if (mguiHinzufuegen.HINZUFUEGENKONTO.equals(mstrCommand)) {
			try {
				SQLModifizieren(Controll_Statments.kontoHinzufuegen.toString() + '\''
						+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_name().getText()) + '\'' + "," + '\''
						+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Blz().getText()) + '\'' + "," + '\''
						+ aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_kontonummer().getText()) + '\'' + ","
						+ '\'' + aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Betrag().getText()) + '\'' + ","
						+ '\'' + aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Min().getText()) + '\'' + ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.konto.toString());
				JOptionPane.showMessageDialog(null, "Konto wure hinzugefuegt", "Erfolg", JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Konto konnte nicht hinzugefuegt werden\n" + e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);
			} finally {
				mguiHinzufuegen.clear();
			}

		} else if (mguiHinzufuegen.HINZUFUEGENMARKT.equals(mstrCommand)) {
			try {
				SQLModifizieren(Controll_Statments.marktHinzufuegen.toString() + '\''
						+ mguiHinzufuegen.getMtxtMarkt_Name().getText() + '\'' + ","
						+ mguiHinzufuegen.getMtxtMarkt_Plz().getText() + "," + '\''
						+ mguiHinzufuegen.getMtxtMarkt_Adresse().getText() + '\'' + ","
						+ mguiHinzufuegen.getMtxtMarkt_Entfernung() + ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.markt.toString());
				JOptionPane.showMessageDialog(null, "Markt wure hinzugefuegt", "Erfolg", JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Markt konnte nicht hinzugefuegt werden\n" + e.getMessage(), "Fail",
						JOptionPane.OK_CANCEL_OPTION);
			} finally {
				mguiHinzufuegen.clear();
			}

		} else if (mguiHinzufuegen.HINZUFUEGENPRODUKT.equals(mstrCommand)) {
			try {
				SQLModifizieren(Controll_Statments.produktHinzufuegen.toString() + '\''
						+ mguiHinzufuegen.getMtxtProdukt_Name().getText() + '\'' + ","
						+ mguiHinzufuegen.getMtxtProdukt_Gewicht().getText() + ","
						+ mguiHinzufuegen.getMtxtProdukt_Preis().getText() + ");");

				// Model Akutell halten
				SQLAbfrage(Controll_Statments.produkt.toString());
				JOptionPane.showMessageDialog(null, "Produkt wure hinzugefuegt", "Erfolg",
						JOptionPane.OK_CANCEL_OPTION);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Produkt konnte nicht hinzugefuegt werden\n" + e.getMessage(),
						"Fail", JOptionPane.OK_CANCEL_OPTION);
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
			String mstrContentEinkauf = null;
			if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Einkauf.toString()) {
				mstrContent = "Anzahl\tDatum\tProdukt\tKonto\tMarkt\n";
				mguiMain.getMcmbMonat().setEnabled(true);

				for (int i = 0; i < mvecModel.size(); i++) {
					{			
						mstrContentEinkauf += ((Model_Einkauf) mvecModel.get(i)).print() + "\n";
					if (mvecModel.get(i) instanceof Model_Produkt) {
						mguiMain.getTextArea().setEditable(true);

						if (((Model_Produkt) mvecModel.get(i)).getMintID() == ((Model_Einkauf) mvecModel.get(i))
								.getMintIDProdukt()) {
							mstrContentEinkauf += ((Model_Produkt) mvecModel.get(i)).print() + "\n";
						} else if (mvecModel.get(i) instanceof Model_Konto)
							if (((Model_Konto) mvecModel.get(i)).getMintID() ==

							((Model_Einkauf) mvecModel.get(i)).getMintIDKonto()) {
								mstrContentEinkauf += ((Model_Konto) mvecModel.get(i)).print() + "\n";
							} else if (mvecModel.get(i) instanceof Model_Markt)
								if (((Model_Markt) mvecModel.get(i)).getMintID() == ((Model_Einkauf) mvecModel.get(i))
										.getMintIDMarkt()) {
									mstrContentEinkauf += ((Model_Markt) mvecModel.get(i)).print() + "\n";
								}
					}
				}
					
				String string = mguiMain.getMcmbMonat().getSelectedItem().toString();
				if (cmbAuswahl.Januar.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.Januar.toString(),mstrContentEinkauf);
				} else if (cmbAuswahl.Februar.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.Februar.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.März.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.März.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.April.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.April.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.Mai.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.Mai.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.Juni.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.Juni.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.Juli.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.Juli.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.August.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.August.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.September.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.September.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.Oktober.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.Oktober.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.November.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.November.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.Dezember.toString().equals(string)) {
					mstrContentEinkauf = removeDate(cmbAuswahl.Dezember.toString(),mstrContentEinkauf);

				} else if (cmbAuswahl.Alles.toString().equals(string)) {
					mstrContent = mstrContentEinkauf;
				} 
								
			}
		} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Produkt.toString()) {
			mstrContent = "Name\tGewicht\tPreis\n";
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Produkt) {
					mstrContent += ((Model_Produkt) mvecModel.get(i)).print() + "\n";
				}
			}
		} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Markt.toString()) {
			mstrContent = "Name\tPostLeitZahl\tAdresse\tEntfernung\n";
			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Markt) {
					mstrContent += ((Model_Markt) mvecModel.get(i)).print() + "\n";
				}
			}
		} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Konto.toString()) {
			mstrContent = "Name\tBankLeitZahl\tKontonummer\tBetrag\tMinimum\n";

			for (int i = 0; i < mvecModel.size(); i++) {
				if (mvecModel.get(i) instanceof Model_Konto) {
					mstrContent += ((Model_Konto) mvecModel.get(i)).print() + "\n";
				}
			}
		} else if (mguiMain.getComboBox().getSelectedItem().toString() == cmbAuswahl.Statistik.toString()) {
			// hoechste kontostand -> niedirgste kontostand
			// minimum erreicht per konto
			int max = -1, min = 999;
			String name_max = null, name_min = null;
			Vector<String> name_minimumGrenze = new Vector<>();

			for (Object element : mvecModel) {

				if (element instanceof Model_Konto) {
					if (Integer.valueOf(((Model_Konto) element).getMstrBetrag()) < min) {
						min = Integer.valueOf(((Model_Konto) element).getMstrBetrag());
						name_min = ((Model_Konto) element).getMstrName();
					} else {
						max = Integer.valueOf(((Model_Konto) element).getMstrBetrag());
						name_max = ((Model_Konto) element).getMstrName();

					}

					if (Integer.valueOf(((Model_Konto) element).getMstrBetrag()) <= Integer
							.valueOf(((Model_Konto) element).getMintMin())) {
						name_minimumGrenze.addElement(((Model_Konto) element).getMstrName());
					}
				}

			}

			mstrContent += "Den niedrigsten Kontostand hat " + name_min + " mit " + min + " EURO\n";
			mstrContent += "Den hoechsten Kontostand hat " + name_max + " mit " + max + " EURO\n";

			for (int i = 0; i < name_minimumGrenze.size(); i++)
				mstrCommand += "Folgende Leute haben ihre Limitbegrenzung ueberschritten: " + name_minimumGrenze.get(i)
						+ "\n";

			// #TODO prüfen der SQL

			try {
				for (int i = 0; i < Controll_Statments.statistic().size(); i++) {
					System.out.println(SQLStatistic(Controll_Statments.statistic().get(i)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		// fuellen der GUI
		mguiMain.setTextArea(mstrContent);
	}

	}

	private String removeDate(String enumDate, String mstrContentEinkauf) {
		String[] tmp = mstrContentEinkauf.split("\n");
		Vector<String[]> value = new Vector<>();

		for (int i = 0; i < tmp.length; i++) {
			value.add(tmp[i].split("\t"));
		}
		
		
		//enum to String
		//bsp: Januar to 1
		String valueDate = null;
		if(cmbAuswahl.Januar.toString().equals(enumDate)){
			valueDate = "01";
		}else if (cmbAuswahl.Februar.toString().equals(enumDate)) {
			valueDate = "02";
		} else if (cmbAuswahl.März.toString().equals(enumDate)) {
			valueDate = "03";
		} else if (cmbAuswahl.April.toString().equals(enumDate)) {
			valueDate = "04";
		} else if (cmbAuswahl.Mai.toString().equals(enumDate)) {
			valueDate = "05";
		} else if (cmbAuswahl.Juni.toString().equals(enumDate)) {
			valueDate = "06";
		} else if (cmbAuswahl.Juli.toString().equals(enumDate)) {
			valueDate = "07";
		} else if (cmbAuswahl.August.toString().equals(enumDate)) {
			valueDate = "08";
		} else if (cmbAuswahl.September.toString().equals(enumDate)) {
			valueDate = "09";
		} else if (cmbAuswahl.Oktober.toString().equals(enumDate)) {
			valueDate = "10";
		} else if (cmbAuswahl.November.toString().equals(enumDate)) {
			valueDate = "11";
		} else if (cmbAuswahl.Dezember.toString().equals(enumDate)) {
			valueDate = "12";
		}
		
		
		for(int i=0;i<value.size();i++)
		{
			if(value.get(i)[2].split(".")[2] == valueDate)
			{
				value.remove(i);
			}
			
		}
		
		String returnDate = null;
		for(int i=0;i<value.size();i++)
		{
			returnDate += value.get(i)[0] + "\t"+ value.get(i)[1] +"\t"+ value.get(i)[2]+"\n";
		}
		
		
		return returnDate;
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

	private String SQLStatistic(String sql) throws SQLException {

		String resultString = null;

		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// Query bearbeiten
		ResultSet result = null;

		// executeQuerry nur fuer selects
		result = query.executeQuery(sql);

		// speichern in Klasse
		while (result.next()) {
			for (int i = 0; i < 2; i++) {
				resultString += result.getString(i) + "\n";
			}
		}
		return resultString;
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
