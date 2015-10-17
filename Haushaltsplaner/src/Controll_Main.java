import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


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
		this.mguiAbfrage.getMchgXMLDB().addActionListener(this);

		this.mguiMain.getMntmHinzufgen().addActionListener(this);
		this.mguiMain.getMmenExportiern().addActionListener(this);
		this.mguiMain.getMmenLaden().addActionListener(this);
		this.mguiMain.getComboBox().addActionListener(this);
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
		
		if(!(mguiAbfrage.getMchgXMLDB().getSelectedItem().toString() == "XML")){
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
		else
		{
			try
			{
			accessXML();
			String myarray [] = new String []{"name","preis","gewicht"};

			readXML("data.xml",myarray);
			}
			catch(IOException ex)
			{
				JOptionPane.showMessageDialog(null, "404",
						ex.getMessage(), JOptionPane.OK_CANCEL_OPTION);
			}
		}
	}

	private void accessXML() throws IOException
	{
		
		String myarray [] = new String [] {"XMLProdukt","XMLKonto","XMLMarkt","XMLEinkauf"};
		
		for(String urlPart : myarray)
		{
		StringBuilder result = new StringBuilder();
		@SuppressWarnings("deprecation")
		String urlStr =  "http://dfch-ludwig.de/xml/"+urlPart+".php?username="+
				mguiAbfrage.getMtxtMeta_Username().getText()+"&password="+
				 mguiAbfrage.getMtxtMeta_passwort().getText()+"&db="+
				 mguiAbfrage.getMtxtMeta_DatenabnkName().getText();
		
		//HTTP get auf url
		  URL url = new URL(urlStr);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      
	      //zeilenweise einlesen
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      
	      //schreiben der XML 
	      //#TODO
	      FileWriter fw = new FileWriter("data.xml");
	      BufferedWriter bw = new BufferedWriter(fw);
	      bw.write(result.toString());
	      bw.close();
		}
	      
	}
	
	private void readXML(String filename, String [] elemente)
	{

	    try {

		File fXmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);
				
		doc.getDocumentElement().normalize();				
		NodeList nList = doc.getElementsByTagName("row");
				
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
										
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				for(String e : elemente){
				System.out.println(eElement.getElementsByTagName(e).item(0).getTextContent());
				}

			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
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
	
private void writeXML(Object element)
{
	  try {

		  if(element instanceof Model_Produkt){
			File file = new File("XMLProdukt");
			JAXBContext jaxbContext = JAXBContext.newInstance(Model_Produkt.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal((Model_Produkt)element, file);
		  }
		  else if(element instanceof Model_Konto){
				File file = new File("XMLKonto");
				JAXBContext jaxbContext = JAXBContext.newInstance(Model_Konto.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal((Model_Konto)element, file);
			  }
		  else if(element instanceof Model_Markt){
				File file = new File("XMLMarkt");
				JAXBContext jaxbContext = JAXBContext.newInstance(Model_Markt.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal((Model_Markt)element, file);
			  }
		  else if(element instanceof Model_Einkauf){
				File file = new File("XMLEinkauf");
				JAXBContext jaxbContext = JAXBContext.newInstance(Model_Einkauf.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal((Model_Einkauf)element, file);
			  }
		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
}
	private void update() { 
	
		if(!(mguiAbfrage.getMchgXMLDB().getSelectedItem().toString() == "XML")){
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
					SQLModifizieren(((Model_Produkt)element).SQLUpdate());
				}
			}
			else if(element instanceof Model_Konto)
			{
				if(!((Model_Konto)element).isMboolequal())
				{
					SQLModifizieren(((Model_Konto)element).SQLUpdate(aes));
				}
			}
			else if(element instanceof Model_Markt)
			{
				if(!((Model_Markt)element).isMboolequal())
				{
					SQLModifizieren(((Model_Markt)element).SQLUpdate());
				}
			}
		}
		//abfragen der db
		auslesen();
		}
		
		//##########################################
		//schreibe alles
		for(Object i : mvecModel)
		{
		writeXML(i);
		}
		
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
			if (mvecModel.get(i) instanceof Model_Produkt) 
			{
				if (((Model_Produkt) mvecModel.get(i)).getMstrName().equals(mguiHinzufuegen
						.getMcmbProdukt().getSelectedItem())) 
				{
					p = ((Model_Produkt) mvecModel.get(i));
				}
			} else if (mvecModel.get(i) instanceof Model_Konto)
			{
				if (((Model_Konto) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbKonto().getSelectedItem()) 
				{
					k = ((Model_Konto) mvecModel.get(i));
				}
			} else if (mvecModel.get(i) instanceof Model_Markt) 
			{
				if (((Model_Markt) mvecModel.get(i)).getMstrName() == mguiHinzufuegen
						.getMcmbMarkt().getSelectedItem()) 
				{
					m = ((Model_Markt) mvecModel.get(i));
				}
			}
		}

			
		if(!(mguiAbfrage.getMchgXMLDB().getSelectedItem().toString() == "XML")){

				// Einkauf hinzufuegen
				try {
					SQLModifizieren(Controll_Statments.AddAlles(
							Integer.valueOf(mguiHinzufuegen.getMtxtAlles_Anzahl().getText()), mguiHinzufuegen.getMtxtAlles_Datum().getText(),
							k.getMintID(), p.getMintID(),  m.getMintID()));
						
							
						auslesen();
				} finally {
					mguiHinzufuegen.clear();
				}}
		else
		{
			
			int max = -1;
			for(Object ein : mvecModel)
			{
				if(ein instanceof Model_Einkauf)
				{
					if(((Model_Einkauf) ein).getMintID() > max)
					{
						max = ((Model_Einkauf) ein).getMintID();
					}
				}
			}
			
			
			Model_Einkauf mmeiEinkauf = new Model_Einkauf(Integer.valueOf(mguiHinzufuegen.getMtxtAlles_Anzahl().getText()), mguiHinzufuegen.getMtxtAlles_Datum().getText(), max+1);
			mmeiEinkauf.ModelArray(k, p, m);
			this.mvecModel.add(mmeiEinkauf);
		}
		

		}

	private void AddKonto() {
		
		if(!(mguiAbfrage.getMchgXMLDB().getSelectedItem().toString() == "XML")){
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
		}}
		else
		{
			
			int max = -1;
			for(Object ein : mvecModel)
			{
				if(ein instanceof Model_Konto)
				{
					if(((Model_Konto) ein).getMintID() > max)
					{
						max = ((Model_Konto) ein).getMintID();
					}
				}
			}
			
			String name = aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_name().getText());
			String blz = aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Blz().getText());
			String knr = aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_kontonummer().getText());
			String betrag = aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Betrag().getText());
			String min = aes.verschluesselnAES(mguiHinzufuegen.getMtxtKonto_Min().getText());
			
			Model_Konto mmknKonto = new Model_Konto(name, blz, knr, betrag, min, max+1);
			this.mvecModel.add(mmknKonto);
		}

	}

	private void AddMarkt() {
		
		if(!(mguiAbfrage.getMchgXMLDB().getSelectedItem().toString() == "XML")){
		try {
			SQLModifizieren(Controll_Statments.AddMarkt(mguiHinzufuegen.getMtxtMarkt_Name().getText(), 
					mguiHinzufuegen.getMtxtMarkt_Plz().getText(), 
					mguiHinzufuegen.getMtxtMarkt_Adresse().getText(), Integer.valueOf(mguiHinzufuegen.getMtxtMarkt_Entfernung().getText())));
			

			// Model Akutell halten
			auslesen();
		} finally {
			mguiHinzufuegen.clear();
		}}
		else
		{
			
			int max = -1;
			for(Object ein : mvecModel)
			{
				if(ein instanceof Model_Markt)
				{
					if(((Model_Markt) ein).getMintID() > max)
					{
						max = ((Model_Markt) ein).getMintID();
					}
				}
			}
			
			String name = mguiHinzufuegen.getMtxtMarkt_Name().getText(); 
			String plz = mguiHinzufuegen.getMtxtMarkt_Plz().getText();
			String adr = mguiHinzufuegen.getMtxtMarkt_Adresse().getText(); 
			int entfernung = Integer.valueOf(mguiHinzufuegen.getMtxtMarkt_Entfernung().getText());
	
			
			Model_Markt mmmrkMarkt = new Model_Markt(name, plz, adr, entfernung, max+1);
			this.mvecModel.add(mmmrkMarkt);
		}
	}

	private void AddProdukt() {
		
		if(!(mguiAbfrage.getMchgXMLDB().getSelectedItem().toString() == "XML")){
		try {
			SQLModifizieren(Controll_Statments.AddProdukt(mguiHinzufuegen.getMtxtProdukt_Name().getText(),
					Integer.valueOf(mguiHinzufuegen.getMtxtProdukt_Gewicht().getText()),
					Float.valueOf(mguiHinzufuegen.getMtxtProdukt_Preis().getText())));
			
			// Model Akutell halten
			auslesen();
		} finally {
			mguiHinzufuegen.clear();
		}}
		else
		{
			
			int max = -1;
			for(Object ein : mvecModel)
			{
				if(ein instanceof Model_Produkt)
				{
					if(((Model_Produkt) ein).getMintID() > max)
					{
						max = ((Model_Produkt) ein).getMintID();
					}
				}
			}
			
			String name = mguiHinzufuegen.getMtxtProdukt_Name().getText();
			int gewicht = Integer.valueOf(mguiHinzufuegen.getMtxtProdukt_Gewicht().getText());
			float preis = Float.valueOf(mguiHinzufuegen.getMtxtProdukt_Preis().getText());
	
			
			Model_Produkt mprdProdukt = new Model_Produkt(name, gewicht, preis, max+1);
			this.mvecModel.add(mprdProdukt);
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
					"Betrag", "Minimum", 
					"Marktname", "Adresse", "Entfernung",
					"Postleitzahl","Produktname", "Preis", "Gewicht",};
					
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
		};
	
		
		 
	}


	
}
