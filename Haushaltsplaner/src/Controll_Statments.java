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
			return "insert into ein(anzahl,datum,k_ID,p_ID,m_ID) Values(";

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

	kontoUpdate {
		public String toString() {
			return "delete from Konto where K_ID = ";
		}
	},
	kontoUpdateInsert {
		public String toString() {
			return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum,K_ID) Values(";
		}
	},
	
	produktUpdate {
		public String toString() {
			return "delete from Produkt where P_ID = ";
		}
	},
	produktUpdateInsert {
		public String toString() {
			return "insert into Produkt(name,gewicht,preis,P_ID) Values(";
		}
	},
	marktUpdate {
		public String toString() {
			return "delete from Markt where M_ID = ";
		}
	},
	marktUpdateInsert {
		public String toString() {
			return "insert into Markt(name,postleitzahl,adresse,entfernung,M_ID) Values(";
		}
	},
	
	AllUpdate {
		public String toString() {
			return "delete from Markt where M_ID = ";
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
	;

	@SuppressWarnings("null")
	public static Vector<String> toExtendString(String kuerzel) {
		Vector<String> mstrAttNew = null;
		mstrAttNew.add("create database HausHaltsPlaner_" + kuerzel + ";");
		mstrAttNew.add("use HausHaltsPlaner_" + kuerzel + ";");
		mstrAttNew.add("create table Einkauf like master.Einkauf;");
		mstrAttNew.add("create table Konto like master.Konto;");
		mstrAttNew.add("create table Markt like master.Markt;");
		mstrAttNew.add("create table Produkt like master.Produkt;");
		mstrAttNew.add("create view ViewAll as select * from master.ViewAll;");
		mstrAttNew
				.add("create view SortAusgaben as select * from master.SortAusgaben;");
		mstrAttNew
				.add("create view SortDatum as select * from master.SortDatum;");
		mstrAttNew
				.add("create view SortEntfernung as select * from master.SortEntfernung;");
		mstrAttNew
				.add("create view SortPreis as select * from master.SortPreis;");

		return mstrAttNew;
	}

	@SuppressWarnings("null")
	public static Vector<String> MasterErstellen() {
		Vector<String> mstrAttNew = null;
		
		mstrAttNew.add("create database master");
		mstrAttNew.add("use master;");
		mstrAttNew.add("create table Konto(betrag float, name VARCHAR(255), bankleitzahl VARCHAR(8), kontonummer VARCHAR(9), minimum INT, K_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(K_ID));");
		mstrAttNew.add("create table Produkt(preis float, name VARCHAR(255), gewicht INT, P_ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(P_ID));");
		mstrAttNew.add("create table Markt(name VARCHAR(255), postleitzahl VARCHAR(5), adresse VARCHAR(255), entfernung INT, M_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(M_ID));");
		mstrAttNew.add("create table Einkauf(anzahl INT, datum DATE, m_ID INT, FOREIGN KEY(m_ID) REFERENCES Markt(M_ID) ON DELETE CASCADE,"+
		"k_ID INT, FOREIGN KEY(k_ID) REFERENCES Konto(K_ID) ON DELETE CASCADE,"+
		"p_ID INT,FOREIGN KEY(p_ID) REFERENCES Produkt(P_ID) ON DELETE CASCADE,"+
 		"PRIMARY KEY(m_ID,p_ID,k_ID));");
		mstrAttNew.add("create view ViewAll as select" 
		+"k.betrag, k.name as 'Kontoinhaber', k.bankleitzahl, k.kontonummer,k.minimum, k.K_ID,"
		+"p.name as 'Produktname',p.gewicht, p.preis, p.P_ID,"
		+"m.name as 'Marktname', m.postleitzahl,m.adresse, m.entfernung, m.M_ID,"
		+"ein.anzahl,ein.datum"
		+"from Einkauf ein, Produkt p, Konto k, Markt m where ein.m_ID = m.M_ID AND ein.p_ID = p.P_ID AND ein.k_ID = k.K_ID;");
		mstrAttNew.add("create view SortPreis as"
		+"select p.name as 'Produktname', p.Preis, p.gewicht from Produkt p order by p.Preis;");
		mstrAttNew.add("create view SortAusgaben as"
		+"select p.name as 'Produktname', p.preis , ein.anzahl ,ROUND((p.preis * ein.anzahl),2) as 'gesamtpreis' from Produkt p, Einkauf ein where p.P_ID = ein.p_ID;");
		mstrAttNew.add("create view SortDatum as"
		+"select p.name as 'Produktname', p.preis , p.gewicht , ein.datum  from Produkt p, Einkauf ein where p.P_ID = ein.p_ID Order by ein.Datum;");
		mstrAttNew.add("create view SortEntfernung as"
		+"select m.name as 'Martname', m.entfernung , m.postleitzahl, m.adresse  from Markt m;");
		
		
		return mstrAttNew;
	}

}
