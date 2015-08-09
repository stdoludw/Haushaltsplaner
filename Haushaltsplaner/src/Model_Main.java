

public class Model_Main {

	private int mintAnzahl;
	private String mstrDatum;
	
	
	public String getMstrDatum() {
		return mstrDatum;
	}

	
	public Model_Main(int anzahl, String today)
	{
		this.mintAnzahl = anzahl;
		this.mstrDatum = today;
		}
	
	public void ModelArray(int k, int p, int m)
	{
		this.mintIDKonto = k;
		this.mintIDMarkt = m;
		this.mintIDProdukt =p;
	
	}


	public int getMintAnzahl() {
		return mintAnzahl;
	}
	
	public void setMintAnzahl(int mintAnzahl) {
		this.mintAnzahl = mintAnzahl;
	}
	
	public String SQlerstellenAll()
	{
		String statement;
		statement = Controll_Statments.allHinzufügen.toString() + mintAnzahl
				+ "," + "now()" + ","
				+ mintIDKonto + ","
				+ mintIDProdukt + ","
				+ mintIDMarkt + ");";	
	return statement;
	
	}
	
	public String print()
	{
		return mintAnzahl+ "\t" + mstrDatum + "\t";
	}
	
	private int mintIDKonto;
	public int getMintIDKonto() {
		return mintIDKonto;
	}


	public int getMintIDProdukt() {
		return mintIDProdukt;
	}


	public int getMintIDMarkt() {
		return mintIDMarkt;
	}

	private int mintIDProdukt;
	private int mintIDMarkt;
}
