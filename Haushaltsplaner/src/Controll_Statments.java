import java.util.Vector;

public class Controll_Statments {
	public static String ViewAll()
	{
			return "select * from ViewAll;";
	}


	public static String ViewProdukt() {
			return "select * from Produkt p;";
		
	}
	public static String ViewKonto() {
			return "select * from Konto k;";
		
	}
	public static String ViewMarkt() {
			return "select * from Markt m;";
		
	}

	public static String AddAlles(int anzhal,String datum, int K_ID, int P_ID, int M_ID) {
			return "insert into Einkauf(anzahl,datum,k_ID,p_ID,m_ID) Values("+anzhal+","+
	"STR_TO_DATE(\'"+datum+"\', '%d-%m-%Y')"+","+K_ID+","+P_ID+","+M_ID+");";

		}
	public static String AddProdukt(String name, int gewicht, float preis) {
			return "insert into Produkt(name,gewicht,preis) Values("+"\'"+name+"\'"+","+gewicht+","+preis+");";
			
		}
	
	public static String AddKonto(String name, String bankleitzahl, String kontonummer, String betrag, String minimum, AES_verschluesselung aes) 
	{
			return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values("+"\'" + aes.verschluesselnAES(name) +"\'" + ","
					+"\'" + aes.verschluesselnAES(bankleitzahl) +"\'" + ","+"\'" +aes.verschluesselnAES( kontonummer) +"\'" + ","+"\'" + aes.verschluesselnAES(betrag) +"\'" + ","+"\'" + aes.verschluesselnAES(minimum) +"\'" + ");";
	}
	
	public static String  AddMarkt(String name, String postleitzahl, String adresse, int entfernung) {
			return "insert into Markt(name,postleitzahl,adresse,entfernung) Values("+"\'" + name +"\'" + ","
					+"\'" + postleitzahl +"\'" + ","+"\'" + adresse +"\'" + ","+entfernung + ");";
		}
	
	/*wird eventuel spaeter mal verwendet
	public static String  SortPreis() {
			return "select * from SortPreis;";
		}

	public static String  SortAusgaben() {
			return "select * from SortAusgaben;";
		}

	public static String SortDatum() {
			return "select * from SortDatum;";
		}
	public static String SortEntfernung() {
			return "select * from SortEntfernung;";
		}
		
			public static String SortQ1()
	{
		return "select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-01-__')"
				+ "		or ve.datum like('____-02-__')or ve.datum like('____-03-__')or ve.datum like('____-04-__');";
		
		
	}
	public static String SortQ2()
	{
		return"select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-05-__')"
				+ "		or ve.datum like('____-06-__')or ve.datum like('____-07-__')or ve.datum like('____-08-__');";
		}
		
	public static String SortQ3()
	{

		return "select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-09-__')"
				+ "		or ve.datum like('____-10-__')or ve.datum like('____-11-__')or ve.datum like('____-12-__');";
			
	}
	*/


	public static String UpdateProdukt(String name, int gewicht, float preis, int PK) {

		return "update Produkt set name = " + "\'" +name+"\' , gewicht = " + gewicht+ ", preis = " + "\'" +preis+"\' where P_ID = "+ PK +";";

		
	}

public static String UpdateKonto(String name, String bankleitzahl, String kontonummer, String betrag, String minimum, int PK) 
{
	return "update Konto set name = " + "\'" +name+"\' ,bankleitzahl = " + "\'" +bankleitzahl+"\',"
			+ " kontonummer = " + "\'" +kontonummer+"\' ,betrag = " + "\'" +betrag+"\' ,minimum = " + "\'" +minimum+"\' where K_ID = "+ PK +";";

}

public static String  UpdateMarkt(String name, String postleitzahl, String adresse, int entfernung, int PK) {
	return "update Markt set name = " + "\'" +name+"\' , postleitzahl = " + "\'" +postleitzahl+"\' , adresse = " + "\'" +adresse+"\' "
			+ ", entfernung = " + +entfernung+ " where M_ID = "+ PK +";";
	}

	


	public static Vector<String> createDatenbank(String kuerzel) {
		Vector<String> mstrAttNew = new Vector<String>();
		mstrAttNew.add("create database HausHaltsPlaner_" + kuerzel + ";");
		mstrAttNew.add("use HausHaltsPlaner_" + kuerzel + ";");
		mstrAttNew.add("create table Einkauf like master.Einkauf;");
		mstrAttNew.add("create table Konto like master.Konto;");
		mstrAttNew.add("create table Markt like master.Markt;");
		mstrAttNew.add("create view HausHaltsPlaner_" + kuerzel
				+ ".ViewAll as select k.betrag, k.name as 'Kontoinhaber', k.bankleitzahl, k.kontonummer,k.minimum, k.K_ID, p.name as 'Produktname',p.gewicht, p.preis, p.P_ID, m.name as 'Marktname',"
				+ "m.postleitzahl,m.adresse, m.entfernung, m.M_ID,"
				+ "ein.anzahl,ein.datum,ein.E_ID from HausHaltsPlaner_" + kuerzel + ".Einkauf ein, HausHaltsPlaner_"
				+ kuerzel + ".Produkt p, HausHaltsPlaner_" + kuerzel + ".Konto k, HausHaltsPlaner_" + kuerzel
				+ ".Markt m where ein.m_ID = m.M_ID AND ein.p_ID = p.P_ID AND ein.k_ID = k.K_ID;");

		mstrAttNew.add("create view HausHaltsPlaner_" + kuerzel + ".SortPreis as"
				+ "			select p.name as 'Produktname', p.Preis, p.gewicht from HausHaltsPlaner_" + kuerzel
				+ ".Produkt p order by p.Preis;");

		mstrAttNew.add("create view HausHaltsPlaner_" + kuerzel + ".SortAusgaben as"
				+ "			select p.name as 'Produktname', p.preis , ein.anzahl,ein.E_ID ,ROUND((p.preis * ein.anzahl),2) as 'gesamtpreis',ein.E_ID from HausHaltsPlaner_"
				+ kuerzel + ".Produkt p, HausHaltsPlaner_" + kuerzel + ".Einkauf ein where p.P_ID = ein.p_ID;");

		mstrAttNew.add("create view HausHaltsPlaner_" + kuerzel + ".SortDatum as"
				+ "			select p.name as 'Produktname', p.preis , p.gewicht , ein.datum,ein.E_ID  from HausHaltsPlaner_"
				+ kuerzel + ".Produkt p, HausHaltsPlaner_" + kuerzel
				+ ".Einkauf ein where p.P_ID = ein.p_ID Order by ein.Datum;");

		mstrAttNew.add("create view HausHaltsPlaner_" + kuerzel + ".SortEntfernung as"
				+ "			select m.name as 'Martname', m.entfernung , m.postleitzahl, m.adresse  from HausHaltsPlaner_"
				+ kuerzel + ".Markt m;");

		return mstrAttNew;
	}

	public static Vector<String> createMaster() {
		Vector<String> mstrAttNew = new Vector<String>();

		mstrAttNew.add("create database master");
		mstrAttNew.add("use master;");
		mstrAttNew.add(
				"create table Konto(betrag float, name VARCHAR(255), bankleitzahl VARCHAR(8), kontonummer VARCHAR(9), minimum INT, K_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(K_ID));");
		mstrAttNew.add(
				"create table Produkt(preis float, name VARCHAR(255), gewicht INT, P_ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(P_ID));");
		mstrAttNew.add(
				"create table Markt(name VARCHAR(255), postleitzahl VARCHAR(5), adresse VARCHAR(255), entfernung INT, M_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(M_ID));");
		mstrAttNew
				.add("create table Einkauf(anzahl INT, datum DATE, m_ID INT, FOREIGN KEY(m_ID) REFERENCES Markt(M_ID),"
						+ "k_ID INT, FOREIGN KEY(k_ID) REFERENCES Konto(K_ID),"
						+ "p_ID INT,FOREIGN KEY(p_ID) REFERENCES Produkt(P_ID)," + "E_ID INT NOT NULL AUTO_INCREMENT,"
						+ "PRIMARY KEY(E_ID));");
		mstrAttNew.add("create view ViewAll as select"
				+ "k.betrag, k.name as 'Kontoinhaber', k.bankleitzahl, k.kontonummer,k.minimum, k.K_ID,"
				+ "p.name as 'Produktname',p.gewicht, p.preis, p.P_ID,"
				+ "m.name as 'Marktname', m.postleitzahl,m.adresse, m.entfernung, m.M_ID," + "ein.anzahl,ein.datum"
				+ "from Einkauf ein, Produkt p, Konto k, Markt m where ein.m_ID = m.M_ID AND ein.p_ID = p.P_ID AND ein.k_ID = k.K_ID;");
		mstrAttNew.add("create view SortPreis as"
				+ "select p.name as 'Produktname', p.Preis, p.gewicht from Produkt p order by p.Preis;");
		mstrAttNew.add("create view SortAusgaben as"
				+ "select p.name as 'Produktname', p.preis , ein.anzahl ,ROUND((p.preis * ein.anzahl),2) as 'gesamtpreis' from Produkt p, Einkauf ein where p.P_ID = ein.p_ID;");
		mstrAttNew.add("create view SortDatum as"
				+ "select p.name as 'Produktname', p.preis , p.gewicht , ein.datum  from Produkt p, Einkauf ein where p.P_ID = ein.p_ID Order by ein.Datum;");
		mstrAttNew.add("create view SortEntfernung as"
				+ "select m.name as 'Martname', m.entfernung , m.postleitzahl, m.adresse  from Markt m;");

		return mstrAttNew;
	}

	public static Vector<String> statistic() {

		Vector<String> mstrAttNew = new Vector<String>();

		// maximale anzahl an Produkten mit name des produkts und Markt
		mstrAttNew.add("select name, AVG(entfernung), adresse from HausHaltsPlaner_Database.Markt;");
		mstrAttNew.add("select name, max(entfernung), adresse from HausHaltsPlaner_Database.Markt;");
		mstrAttNew.add("select name, min(entfernung), adresse from HausHaltsPlaner_Database.Markt; ");

		mstrAttNew
				.add("select  m.name,p.name, max(ein.anzahl) from HausHaltsPlaner_Database.Einkauf ein, HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m"
						+ "		where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and anzahl;");
		mstrAttNew
				.add("select  m.name,p.name, min(ein.anzahl) from HausHaltsPlaner_Database.Einkauf ein, HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m"
						+ "		where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and anzahl;");

		mstrAttNew
				.add("select  m.name,p.name,max(ein.datum) from HausHaltsPlaner_Database.Einkauf ein, HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m"
						+ "		where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and datum;");
		mstrAttNew
				.add("select  m.name,p.name,min(ein.datum) from HausHaltsPlaner_Database.Einkauf ein, HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m"
						+ "		where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and datum;");

		mstrAttNew.add("select name,max(preis),gewicht from HausHaltsPlaner_Database.Produkt;");
		mstrAttNew.add("select name,min(preis),gewicht from HausHaltsPlaner_Database.Produkt;");
		mstrAttNew.add("select name,avg(preis),gewicht from HausHaltsPlaner_Database.Produkt;");

	

		return mstrAttNew;

	}
}
