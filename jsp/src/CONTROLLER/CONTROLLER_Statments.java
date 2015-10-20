package CONTROLLER;

import java.util.ArrayList;
import MODEL.AES_verschluesselung;

public class CONTROLLER_Statments {

	public enum caller {
		index {
			public String toString() {
				return "index";
			}
		},
		usermanagement {
			public String toString() {
				return "CREATE_User";
			}
		},

		Insert_Einkauf {
			public String toString() {
				return "INSERT_Einkauf_SQL";
			}
		},
		Update_Einkauf {
			public String toString() {
				return "UPDATE_Einkauf_SQL";
			}
		},
		Delete_Einkauf {
			public String toString() {
				return "DELETE_Einkauf_SQL";
			}
		}

		,
		Insert_Konto {
			public String toString() {
				return "INSERT_Konto_SQL";
			}
		},

		Update_Konto {
			public String toString() {
				return "UPDATE_Konto_SQL";
			}
		},
		Delete_Konto {
			public String toString() {
				return "DELETE_Konto_SQL";
			}
		},
		Insert_Markt {
			public String toString() {
				return "INSERT_Markt_SQL";
			}
		},
		Update_Markt {
			public String toString() {
				return "UPDATE_Markt_SQL";
			}
		},
		Delete_Markt {
			public String toString() {
				return "DELETE_Markt_SQL";
			}
		},

		Insert_Produkt {
			public String toString() {
				return "INSERT_Produkt_SQL";
			}
		},
		Update_Produkt {
			public String toString() {
				return "UPDATE_Produkt_SQL";
			}
		},
		Delete_Produkt {
			public String toString() {
				return "DELETE_Produkt_SQL";
			}
		}
	}

	public enum session {
		mvecModel {
			public String toString() {
				return "mvecMODEL";
			}
		},
		mvStatistik {
			public String toString() {
				return "mvStatistik";
			}
		},
		Insert_Einkauf {
			public String toString() {
				return "Insert_Einkauf";
			}
		},
		Update_Einkauf {
			public String toString() {
				return "Update_Einkauf";
			}
		},
		Delete_Einkauf {
			public String toString() {
				return "Delete_Einkauf";
			}
		},

		Insert_Konto {
			public String toString() {
				return "Insert_Konto";
			}
		},
		Update_Konto {
			public String toString() {
				return "Update_Konto";
			}
		},
		Delete_Konto {
			public String toString() {
				return "Delete_Konto";
			}
		},

		Insert_Markt {
			public String toString() {
				return "Insert_Markt";
			}
		},
		Update_Markt {
			public String toString() {
				return "Update_Markt";
			}
		},
		Delete_Markt {
			public String toString() {
				return "Delete_Markt";
			}
		},
		Insert_Produkt {
			public String toString() {
				return "Insert_Produkt";
			}
		},
		Update_Produkt {
			public String toString() {
				return "Update_Produkt";
			}
		},
		Delete_Produkt {
			public String toString() {
				return "Delelete_Produkt";
			}
		},

		usermanagement_data {
			public String toString() {
				return "usermanagement_data";
			}
		}
	}

	public enum redirect {
		login_success {
			public String toString() {
				return "login-success.jsp";
			}
		},
		error {
			public String toString() {
				return "error.jsp";
			}
		},
		
		Controller
		{
			public String toString()
			{
				return "Controller";
			}
		},
		
		
		VIEW_Einkauf {
			public String toString() {
				return "VIEW_Einkauf.jsp";
			}
		},
		VIEW_Markt {
			public String toString() {
				return "VIEW_Markt.jsp";
			}
		},
		VIEW_Produkt {
			public String toString() {
				return "VIEW_Produkt.jsp";
			}
		},
		VIEW_Konto {
			public String toString() {
				return "VIEW_Konto.jsp";
			}
		}
		,
		VIEW_Statistik {
			public String toString() {
				return "VIEW_Statistik.jsp";
			}
		},
		
		
		VIEW_UPDATE_Einkauf {
			public String toString() {
				return "VIEW_UPDATE_Einkauf.jsp";
			}
		},
		VIEW_UPDATE_Konto {
			public String toString() {
				return "VIEW_UPDATE_Konto.jsp";
			}
		},
		VIEW_UPDATE_Markt {
			public String toString() {
				return "VIEW_UPDATE_Markt.jsp";
			}
		},
		VIEW_UPDATE_Produkt {
			public String toString() {
				return "VIEW_UPDATE_Produkt.jsp";
			}
		}
		
		
		,
		VIEW_INSERT_Einkauf {
			public String toString() {
				return "VIEW_INSERT_Einkauf.jsp";
			}
		},
		VIEW_INSERT_Konto {
			public String toString() {
				return "VIEW_INSERT_Konto.jsp";
			}
		},
		VIEW_INSERT_Markt {
			public String toString() {
				return "VIEW_INSERT_Markt.jsp";
			}
		},
		VIEW_INSERT_Produkt {
			public String toString() {
				return "VIEW_INSERT_Produkt.jsp";
			}
		}
		
		
		
		
		
		
		
		
	};

	public enum menu {
		login {
			public String toString() {
				return "index.jsp";
			}

		},
		Hauptmenue {
			public String toString() {
				return "login-success.jsp";
			}
		},

		Benutzerverwaltung {
			public String toString() {
				return "VIEW_Usermanagement.jsp";
			}

		}

	};

	public static String ViewAll() {
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

	public static String AddEinkauf(int anzahl, String datum, int K_ID, int P_ID, int M_ID) {
		return "insert into Einkauf(anzahl,datum,k_ID,p_ID,m_ID) Values(" + anzahl + "," + "STR_TO_DATE(\'" + datum
				+ "\', '%d-%m-%Y')" + "," + K_ID + "," + P_ID + "," + M_ID + ");";

	}

	public static String AddProdukt(String name, int gewicht, float preis) {
		return "insert into Produkt(name,gewicht,preis) Values(" + "\'" + name + "\'" + "," + gewicht + "," + preis
				+ ");";

	}

	public static String AddKonto(String name, String bankleitzahl, String kontonummer, String betrag, String minimum,
			AES_verschluesselung aes) {
		return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values(" + "\'"
				+ aes.verschluesselnAES(name) + "\'" + "," + "\'" + aes.verschluesselnAES(bankleitzahl) + "\'" + ","
				+ "\'" + aes.verschluesselnAES(kontonummer) + "\'" + "," + "\'" + aes.verschluesselnAES(betrag) + "\'"
				+ "," + "\'" + aes.verschluesselnAES(minimum) + "\'" + ");";
	}

	public static String AddMarkt(String name, String postleitzahl, String adresse, int entfernung) {
		return "insert into Markt(name,postleitzahl,adresse,entfernung) Values(" + "\'" + name + "\'" + "," + "\'"
				+ postleitzahl + "\'" + "," + "\'" + adresse + "\'" + "," + entfernung + ");";
	}

	public static String UpdateProdukt(String name, int gewicht, float preis, int PK) {

		return "update Produkt set name = " + "\'" + name + "\' , gewicht = " + gewicht + ", preis = " + "\'" + preis
				+ "\' where P_ID = " + PK + ";";

	}

	public static String UpdateKonto(String name, String bankleitzahl, String kontonummer, String betrag,
			String minimum, int PK, AES_verschluesselung aes) {
		return "update Konto set name = " + "\'" + aes.verschluesselnAES(name) + "\' ,bankleitzahl = " + "\'"
				+ aes.verschluesselnAES(bankleitzahl) + "\'," + " kontonummer = " + "\'"
				+ aes.verschluesselnAES(kontonummer) + "\' ,betrag = " + "\'" + aes.verschluesselnAES(betrag)
				+ "\' ,minimum = " + "\'" + aes.verschluesselnAES(minimum) + "\' where K_ID = " + PK + ";";

	}

	public static String UpdateMarkt(String name, String postleitzahl, String adresse, int entfernung, int PK) {
		return "update Markt set name = " + "\'" + name + "\' , postleitzahl = " + "\'" + postleitzahl
				+ "\' , adresse = " + "\'" + adresse + "\' " + ", entfernung = " + entfernung + " where M_ID = " + PK
				+ ";";
	}

	public static String UpdateEinkauf(int anzahl, String datum, int k_ID, int p_ID, int m_ID, int PK) {
		return "update Einkauf set anzahl = " + anzahl + ", datum = " + "\'" + datum + "\'" + " , k_ID = " + k_ID
				+ ", p_ID = " + p_ID + " , m_ID = " + m_ID + " where E_ID = " + PK + ";";
	}

	public static String DeleteProdukt(int PK) {

		return "delete from Produkt where P_ID = " + PK + ";";
	}

	public static String DeleteKonto(int PK) {
		return "delete from Konto where K_ID = " + PK + ";";

	}

	public static String DeleteMarkt(int PK) {
		return "delete from Markt where M_ID = " + PK + ";";
	}

	public static String DeleteEinkauf(int PK) {
		return "delete from Einkauf where E_ID = " + PK + ";";
	}

	public static ArrayList<String> createUser(String name, String passwort, String db) {
		ArrayList<String> mstrAttNew = new ArrayList<String>();
		mstrAttNew.add("create user '" + name + "'@'%' IDENTIFIED BY '" + passwort + "';");
		mstrAttNew.add("create database HausHaltsPlaner_" + name + ";");
		mstrAttNew.add("grant select, insert, create, update, CREATE VIEW on HausHaltsPlaner_" + name + ".* to '" + name
				+ "'@'%';");
		mstrAttNew.add("grant select on master.* to '" + name + "'@'%';");
		mstrAttNew.add("grant CREATE on *.* to '" + name + "'@'%';");
		return mstrAttNew;
	}

	public static ArrayList<String> createDatenbank(String name) {
		ArrayList<String> mstrAttNew = new ArrayList<String>();

		mstrAttNew.add("create database HausHaltsPlaner_" + name + ";");
		mstrAttNew.add("use HausHaltsPlaner_" + name + ";");
		mstrAttNew.add("create table Einkauf like master.Einkauf;");
		mstrAttNew.add("create table Konto like master.Konto;");
		mstrAttNew.add("create table Markt like master.Markt;");
		mstrAttNew.add("create table Produkt like master.Produkt;");
		mstrAttNew.add("create view HausHaltsPlaner_" + name
				+ ".ViewAll as select k.betrag, k.name as 'Kontoinhaber', k.bankleitzahl, k.kontonummer,k.minimum, k.K_ID, p.name as 'Produktname',p.gewicht, p.preis, p.P_ID, m.name as 'Marktname',"
				+ "m.postleitzahl,m.adresse, m.entfernung, m.M_ID,"
				+ "ein.anzahl,ein.datum,ein.E_ID from HausHaltsPlaner_" + name + ".Einkauf ein, HausHaltsPlaner_" + name
				+ ".Produkt p, HausHaltsPlaner_" + name + ".Konto k, HausHaltsPlaner_" + name
				+ ".Markt m where ein.m_ID = m.M_ID AND ein.p_ID = p.P_ID AND ein.k_ID = k.K_ID;");

		mstrAttNew.add("create view HausHaltsPlaner_" + name + ".SortPreis as"
				+ "			select p.name as 'Produktname', p.Preis, p.gewicht from HausHaltsPlaner_" + name
				+ ".Produkt p order by p.Preis;");

		mstrAttNew.add("create view HausHaltsPlaner_" + name + ".SortAusgaben as"
				+ "			select p.name as 'Produktname', p.preis , ein.anzahl,ein.E_ID ,ROUND((p.preis * ein.anzahl),2) as 'gesamtpreis',ein.E_ID from HausHaltsPlaner_"
				+ name + ".Produkt p, HausHaltsPlaner_" + name + ".Einkauf ein where p.P_ID = ein.p_ID;");

		mstrAttNew.add("create view HausHaltsPlaner_" + name + ".SortDatum as"
				+ "			select p.name as 'Produktname', p.preis , p.gewicht , ein.datum,ein.E_ID  from HausHaltsPlaner_"
				+ name + ".Produkt p, HausHaltsPlaner_" + name
				+ ".Einkauf ein where p.P_ID = ein.p_ID Order by ein.Datum;");

		mstrAttNew.add("create view HausHaltsPlaner_" + name + ".SortEntfernung as"
				+ "			select m.name as 'Martname', m.entfernung , m.postleitzahl, m.adresse  from HausHaltsPlaner_"
				+ name + ".Markt m;");

		return mstrAttNew;
	}

	public static ArrayList<String> getstatisticMonth(String db) {
		ArrayList<String> mstrAttNew = new ArrayList<String>();
		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve  where ve.datum like('____-01-__') or "
				+ "ve.datum like('____-02-__') or ve.datum like('____-03-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve where ve.datum like('____-04-__') or "
				+ "ve.datum like('____-05-__') or ve.datum like('____-06-__');");

		mstrAttNew.add(" select SUM(ve.preis) from " + db + ".ViewAll ve where ve.datum like('____-07-__') or "
				+ "       ve.datum like('____-08-__') or ve.datum like('____-09-__');");

		mstrAttNew.add(" select SUM(ve.preis) from " + db + ".ViewAll ve where ve.datum like('____-10-__') or "
				+ "ve.datum like('____-11-__') or  ve.datum like('____-12-__');");

		return mstrAttNew;
	}

	public static ArrayList<String> getstatisticAllMonth(String db) {
		ArrayList<String> mstrAttNew = new ArrayList<String>();
		mstrAttNew.add(" select SUM(ve.preis) from " + db + ".ViewAll ve" + " where ve.datum like('____-01-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + "where ve.datum like('____-02-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + " where ve.datum like('____-03-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + " where ve.datum like('____-04-__');");

		mstrAttNew.add(" select SUM(ve.preis) from " + db + ".ViewAll ve " + " where ve.datum like('____-05-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + " where ve.datum like('____-06-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + "where ve.datum like('____-07-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + "where ve.datum like('____-08-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + "where ve.datum like('____-09-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + " where ve.datum like('____-10-__');");

		mstrAttNew.add("select SUM(ve.preis) from " + db + ".ViewAll ve " + " where ve.datum like('____-11-__');");

		mstrAttNew.add(" select SUM(ve.preis) from " + db + ".ViewAll ve " + "where ve.datum like('____-12-__');");

		return mstrAttNew;
	}

	public static ArrayList<String> getStatisticMulti(String db) {
		ArrayList<String> mstrAttNew = new ArrayList<String>();

		mstrAttNew.add("select Produktname, SUM(anzahl) from " + db + ".ViewAll "
				+ "group by Produktname order by 2 desc LIMIT 10;");

		mstrAttNew.add("select Marktname, Count(Marktname) from " + db + ".ViewAll "
				+ "group by Marktname order by 2 desc LIMIT 3;");
		return mstrAttNew;
	}

	public static ArrayList<String> getStatisticMultiTable(String db) {
		ArrayList<String> mstrAttNew = new ArrayList<String>();

		mstrAttNew.add("select name, AVG(entfernung), adresse from " + db + ".Markt;");

		mstrAttNew.add("select name, max(entfernung), adresse from " + db + ".Markt;");

		mstrAttNew.add("select name, min(entfernung), adresse from " + db + ".Markt;");

		mstrAttNew.add("select m.name as marktname, p.name as produktname, max(ein.anzahl) from " + db
				+ ".Einkauf ein, " + "" + db + ".Produkt p, " + db + ".Markt m "
				+ "where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and anzahl;");

		mstrAttNew.add("select m.name as marktname, p.name as produktname, min(ein.anzahl) from " + db
				+ ".Einkauf ein, " + " " + db + ".Produkt p, " + db + ".Markt m "
				+ "where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and anzahl;");

		mstrAttNew.add("select m.name as marktname, p.name as produktname, max(ein.datum) from " + db + ".Einkauf ein, "
				+ " " + db + ".Produkt p, " + db + ".Markt m "
				+ "where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and datum;");

		mstrAttNew.add("select m.name as marktname, p.name as produktname, min(ein.datum) from " + db + ".Einkauf ein,"
				+ " " + db + ".Produkt p, " + db + ".Markt m "
				+ "where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and datum;");

		mstrAttNew.add("select name, max(preis), gewicht from " + db + ".Produkt;");

		mstrAttNew.add("select name, min(preis), gewicht from " + db + ".Produkt;");

		mstrAttNew.add("select name, avg(preis), gewicht from " + db + ".Produkt;");

		return mstrAttNew;
	}

}
