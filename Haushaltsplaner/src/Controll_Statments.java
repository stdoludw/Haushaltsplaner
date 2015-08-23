import java.util.Vector;

public enum Controll_Statments {
	all {
		public String toString() {
			return "select * from ViewAll;";
		}
	},

	produkt {
		public String toString() {
			return "select * from Produkt p;";
		}
	},
	konto {
		public String toString() {
			return "select * from Konto k;";
		}
	},
	markt {
		public String toString() {
			return "select * from Markt m;";
		}
	},

	allHinzufuegen {
		public String toString() {
			return "insert into Einkauf(anzahl,datum,k_ID,p_ID,m_ID) Values(";

		}
	},
	produktHinzufuegen {
		public String toString() {
			return "insert into Produkt(name,gewicht,preis) Values(";
		}
	},
	kontoHinzufuegen {
		public String toString() {
			return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values(";
		}
	},
	marktHinzufuegen {
		public String toString() {
			return "insert into Markt(name,postleitzahl,adresse,entfernung) Values(";
		}
	},

	kontoUpdateDelete {
		public String toString() {
			return "delete from Konto where K_ID = ";
		}
	},
	kontoUpdateInsert {
		public String toString() {
			return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum,K_ID) Values(";
		}
	},

	produktUpdateDelete {
		public String toString() {
			return "delete from Produkt where P_ID = ";
		}
	},
	produktUpdateInsert {
		public String toString() {
			return "insert into Produkt(name,gewicht,preis,P_ID) Values(";
		}
	},
	marktUpdateDelete {
		public String toString() {
			return "delete from Markt where M_ID = ";
		}
	},
	marktUpdateInsert {
		public String toString() {
			return "insert into Markt(name,postleitzahl,adresse,entfernung,M_ID) Values(";
		}
	},

	PreisSortierung {
		public String toString() {
			return "select * from SortPreis;";
		}
	},

	AusgabenSortierung {
		public String toString() {
			return "select * from SortAusgaben;";
		}
	},

	DatumSortierung {
		public String toString() {
			return "select * from SortDatum;";
		}
	},
	EntfernungsSortierung {
		public String toString() {
			return "select * from SortEntfernung;";
		}
	},
	commit {
		public String toString() {
			return "commit";
		}
	},
	Q1
	{
		public String toString() {
		return "select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-01-__')"
				+ "		or ve.datum like('____-02-__')or ve.datum like('____-03-__')or ve.datum like('____-04-__');";
		}
		
	},Q2
	{
		public String toString() {
		return"select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-05-__')"
				+ "		or ve.datum like('____-06-__')or ve.datum like('____-07-__')or ve.datum like('____-08-__');";
		}
		},
	Q3
	{
			public String toString() {
		return "select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-09-__')"
				+ "		or ve.datum like('____-10-__')or ve.datum like('____-11-__')or ve.datum like('____-12-__');";
			
			}
	};

	@SuppressWarnings("null")
	public static Vector<String> toExtendString(String kuerzel) {
		Vector<String> mstrAttNew = null;
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

	@SuppressWarnings("null")
	public static Vector<String> MasterErstellen() {
		Vector<String> mstrAttNew = null;

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
