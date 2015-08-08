import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;

import lib.*;

public class Controll_Main implements ActionListener {

	// verbindung mit Datenbank
	private String mstrUserName;
	private String mstrPasswort;
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;
	private String mstrStatment;

	// Instanzen von M und V
	private Vector<Model_Main> mvecModel;
	private GUI_Main mguiMain = new GUI_Main();
	private GUI_Abfrage mguiAbfrage = new GUI_Abfrage();
	private GUI_Hinzufuegen mguiHinzufuegen = new GUI_Hinzufuegen();

	public void start() throws SQLException, ClassNotFoundException,
			IOException {

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

		mguiAbfrage.run();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getActionCommand() == mguiAbfrage.LOGIN) {
			mstrUserName = mguiAbfrage.getMtxtMeta_Username().getText();
			mstrPasswort = mguiAbfrage.getMtxtMeta_passwort().getText();
			mstrDatenbankName = mguiAbfrage.getMtxtMeta_DatenabnkName()
					.getText();
			mstrHostName = mguiAbfrage.getMtxtMeta_DatenabnkServer().getText();
			mintPort = 3306;

			try {
				acces();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
				mguiMain.run();
				
				while(true)
				{
					if(ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENALL)
					{
						
					}
					else if(ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENKONTO)
					{
						
					}
					else if(ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENMARKT)
					{
						
					}
					else if(ae.getActionCommand() == mguiHinzufuegen.HINZUFUEGENPRODUKT)
					{
						
					}
				
				}
			
		} else if (ae.getActionCommand() == mguiAbfrage.ERSTELLEN) {
			mstrUserName = mguiAbfrage.getMtxtMeta_Username().getText();
			mstrPasswort = mguiAbfrage.getMtxtMeta_passwort().getText();
			mstrDatenbankName = mguiAbfrage.getMtxtMeta_DatenabnkName()
					.getText();
			mstrHostName = mguiAbfrage.getMtxtMeta_DatenabnkServer().getText();
			mintPort = 3306;
			String kuerzel = null;

			for (int i = 0; i < 3; i++) {
				kuerzel += mstrUserName.toCharArray()[i];
			}
			;

			try {
				SQLNeuErstellen(kuerzel);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			mguiMain.run();

		}

	}

	private void acces() throws ClassNotFoundException, SQLException {

		// Datenbanktreiber für JDBC Schnittstellen laden.
		Class.forName("com.mysql.jdbc.Driver");

		// Verbindung zur JDBC-Datenbank herstellen.
		mconCon = DriverManager.getConnection("jdbc:mysql://" + mstrHostName
				+ ":" + mintPort + "/" + mstrDatenbankName + "?" + "user="
				+ mstrUserName + "&" + "password=" + mstrPasswort);

		SQLAbfrage();
	}

	private void SQLAbfrage() throws SQLException {

		// abfrage statement erstellen
		Statement query = mconCon.createStatement();

		// Query bearbeiten
		String sql = mstrStatment;
		ResultSet result = null;

		// executeQuerry nur für selects
		result = query.executeQuery(sql);

		// speichern in Klasse
		while (result.next()) {

			if (sql == Controll_Statments.all.toString() + this.mstrUserName
					+ ");") {
				Model_Konto k = new Model_Konto(result.getString("k.betrag"),
						result.getString("k.name"),
						result.getString("k.bankleitzahl"),
						result.getString("k.kontonummer"),
						result.getInt("k.minimum"), result.getInt("k.K_ID"));
				Model_Produkt p = new Model_Produkt(result.getString("p.name"),
						result.getInt("p.gewicht"), result.getFloat("p.preis"),
						result.getInt("p.P_ID"));
				Model_Markt m = new Model_Markt(result.getString("m.name"),
						result.getString("m.postleitzahl"),
						result.getString("m.adresse"),
						result.getInt("m.entfernung"), result.getInt("m.M_ID"));
				Model_Main me = new Model_Main(result.getInt("ein.anzahl"),
						result.getString("ein.datum"), true);

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
		// mausAusgabe.setMvecModel(mvecModel);

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

	private void exit() throws SQLException {
	}

}
