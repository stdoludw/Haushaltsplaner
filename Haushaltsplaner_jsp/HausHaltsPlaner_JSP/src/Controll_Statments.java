
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
	

	public static String UpdateProdukt(String name, int gewicht, float preis, int PK) {

		return "update Produkt set name = " + "\'" +name+"\' , gewicht = " + gewicht+ ", preis = " + "\'" +preis+"\' where P_ID = "+ PK +";";

		
	}

public static String UpdateKonto(String name, String bankleitzahl, String kontonummer, String betrag, String minimum, int PK, AES_verschluesselung aes) 
{
	return "update Konto set name = " + "\'" +aes.verschluesselnAES(name)+"\' ,bankleitzahl = " + "\'" +aes.verschluesselnAES(bankleitzahl)+"\',"
			+ " kontonummer = " + "\'" +aes.verschluesselnAES(kontonummer)+"\' ,betrag = " + "\'" +aes.verschluesselnAES(betrag)+"\' ,minimum = " + "\'" +aes.verschluesselnAES(minimum)+"\' where K_ID = "+ PK +";";

}

public static String  UpdateMarkt(String name, String postleitzahl, String adresse, int entfernung, int PK) {
	return "update Markt set name = " + "\'" +name+"\' , postleitzahl = " + "\'" +postleitzahl+"\' , adresse = " + "\'" +adresse+"\' "
			+ ", entfernung = " + +entfernung+ " where M_ID = "+ PK +";";
	}


}
