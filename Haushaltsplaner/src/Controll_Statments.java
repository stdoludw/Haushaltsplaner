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
			// user einfügen
			return "select * from Konto k where k.u_ID =";
		}
	},
	markt {
		public String toString() {
			return "select * from Markt m;";
		}
	},

	allHinzufügen {
		public String toString() {
			return "insert into ein(anzahl,datum,m_ID,k_ID,p_ID,u_ID) Values(";

		}
	},
	produktHinzufügen {
		public String toString() {
			return "insert into Produkt(name,gewicht,preis) Values(";
		}
	},
	kontoHinzufügen {
		public String toString() {
			return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values(";
		}
	},
	marktHinzufügen {
		public String toString() {
			return "insert into Markt(name,postleitzahl,adresse,entfernung) Values(";
		}
	},

	userHinzufügen {
		public String toString() {
			return "insert into User(name) Values(";
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

	NeuHinzufügen
	{
		@SuppressWarnings("null")
		public Vector<String> toString(String kuerzel)
		{
			Vector<String> mstrAttNew = null;
			mstrAttNew.add("create database HausHaltsPlaner_"+kuerzel+";");
			mstrAttNew.add("use HausHaltsPlaner_"+kuerzel+";");
			mstrAttNew.add("create table Einkauf like Master.Einkauf;");
			mstrAttNew.add("create table Konto like Master.Konto;");
			mstrAttNew.add("create table Markt like Master.Markt;");
			mstrAttNew.add("create table Produkt like Master.Produkt;");
			mstrAttNew.add("create view ViewAll as select * from Master.ViewAll;");
			mstrAttNew.add("create view SortAusgaben as select * from Master.SortAusgaben;");
			mstrAttNew.add("create view SortDatum as select * from Master.SortDatum;");
			mstrAttNew.add("create view SortEntfernung as select * from master.SortEntfernung;");
			mstrAttNew.add("create view SortPreis as select * from Master.SortPreis;");
			
			return mstrAttNew;
		}
	},
	commit {
		public String toString() {
			return "commit";
		}
	},


}
