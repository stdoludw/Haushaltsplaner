public enum Controll_Statments {
	all {
		public String toString() {
			// user einfügen
			return "select * from Einkauf ein, Produkt p, Konto k, Markt m where ein.m_ID = m.M_ID AND ein.p_ID = p.P_ID AND ein.k_ID = k.K_ID AND ein.u_ID =";
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
			return "select p.Preis, p.name from Produkt p order by p.Preis;";
		}
	},

	AusgabenSortierung {
		public String toString() {
			return "select p.name,p.preis,ein.anzahl,ROUND((p.preis * me.anzahl),2) as gesamtpreis from Produkt p, Einkauf ein where p.P_ID = ein.p_ID;";
		}
	},

	DatumSortierung {
		public String toString() {
			return "select * from Produkt p, Einkauf ein where p.P_ID = ein.p_ID Order by ein.Datum;";
		}
	},
	EntfernungsSortierung {
		public String toString() {
			return "select m.entfernung,m.name from Markt m;";
		}
	},

	KontoTabelleHinzufügen {
		public String toString() {
			return "create table Konto(betrag float, name VARCHAR(255), bankleitzahl VARCHAR(8), kontonummer VARCHAR(9), minimum INT, K_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(K_ID));";
		}
	},

	ProduktTabelleHinzufügen {
		public String toString() {
			return "create table Produkt(preis float, name VARCHAR(255), gewicht INT, P_ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(P_ID));";
		}
	},
	MarktTabelleHinzufügen {
		public String toString() {
			return "create table Markt(name VARCHAR(255), postleitzahl VARCHAR(5), adresse VARCHAR(255), entfernung INT, M_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(M_ID));";
		}
	},

	EinkaufTabelleHinzufügen {
		public String toString() {
			return "create table Einkauf(anzahl INT, datum DATE, m_ID INT, FOREIGN KEY(m_ID) REFERENCES Markt(M_ID) ON DELETE CASCADE,"
					+ "k_ID INT, FOREIGN KEY(k_ID) REFERENCES Konto(K_ID) ON DELETE CASCADE,"
					+ "p_ID INT,FOREIGN KEY(p_ID) REFERENCES Produkt(P_ID) ON DELETE CASCADE,"
					+ "u_ID INT, FOREIGN KEY(u_ID) REFERENCES User(U_ID) ON DELETE CASCADE,"
					+ "PRIMARY KEY(m_ID,p_ID,k_ID));";
		}
	},
	commit {
		public String toString() {
			return "commit";
		}
	},

	DatenbasisHinzufügen {
		public String toString() {
			return "create databases ";
		}
	},

	DatenbasisBenutzen {
		public String toString() {
			return "use ";
		}
	}

}
