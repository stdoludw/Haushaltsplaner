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
		
		CONTROLLER_Import {
			public String toString() {
				return "CONTROLLER_Import";
			}
		},
		CONTROLLER_Export {
			public String toString() {
				return "CONTROLLER_Export";
			}
		}
	}

	public enum session {
		mvecModel {
			public String toString() {
				return "mvecMODEL";
			}
		},

		filename
		{
			public String toString() {
				return "filename";
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
		data_xml
		{
			public String toString()
			{
				return "http://www.dfch-ludwig.de/xml/data.xml";
			}
		},
		
		Controller
		{
			public String toString()
			{
				return "Controller";
			}
		},
		
		
		VIEW_Konto {
			public String toString() {
				return "VIEW_Konto.jsp";
			}
		},
		
		VIEW_UPDATE_Konto {
			public String toString() {
				return "VIEW_UPDATE_Konto.jsp";
			}
		},
		
		VIEW_INSERT_Konto {
			public String toString() {
				return "VIEW_INSERT_Konto.jsp";
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
		,

		Import {
			public String toString() {
				return "VIEW_Import.jsp";
			}

		},

		Export {
			public String toString() {
				return "VIEW_Export.jsp";
			}

		}

	};

	public enum upload
	{
		
	path
	{
		public String toString() {
		return ("/home/dlu/tomcat/upload/");	
		}
	},
	path_xml
	{
		public String toString() {
		return ("/var/www/html/xml/data.xml");	
		}
	},
	
	}
	

	public static String TruncateKonto() {

		return("truncate Konto;");
	}
	
	
	

	public static String ViewKonto() {
		return "select * from Konto k;";

	}

	

	public static String AddKonto(String name, String bankleitzahl, String kontonummer, String betrag, String minimum,
			AES_verschluesselung aes) {
		return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values(" + "\'"
				+ aes.verschluesselnAES(name) + "\'" + "," + "\'" + aes.verschluesselnAES(bankleitzahl) + "\'" + ","
				+ "\'" + aes.verschluesselnAES(kontonummer) + "\'" + "," + "\'" + aes.verschluesselnAES(betrag) + "\'"
				+ "," + "\'" + aes.verschluesselnAES(minimum) + "\'" + ");";
	}

	

	public static String UpdateKonto(String name, String bankleitzahl, String kontonummer, String betrag,
			String minimum, int PK, AES_verschluesselung aes) {
		return "update Konto set name = " + "\'" + aes.verschluesselnAES(name) + "\' ,bankleitzahl = " + "\'"
				+ aes.verschluesselnAES(bankleitzahl) + "\'," + " kontonummer = " + "\'"
				+ aes.verschluesselnAES(kontonummer) + "\' ,betrag = " + "\'" + aes.verschluesselnAES(betrag)
				+ "\' ,minimum = " + "\'" + aes.verschluesselnAES(minimum) + "\' where K_ID = " + PK + ";";

	}
	

	public static String DeleteKonto(int PK) {
		return "delete from Konto where K_ID = " + PK + ";";

	}


	public static ArrayList<String> createDatenbank(String name) {
		ArrayList<String> mstrAttNew = new ArrayList<String>();

		

		return mstrAttNew;
	}



}
