
public class Model {

	private int mintAnzahl;
	private String mstrDatum;
	private boolean mboolValid;
	private int mintIDKonto;
	private int mintIDProdukt;
	private int mintIDMarkt;
	
	public String getMstrDatum() {
		return mstrDatum;
	}

	public boolean isMboolValid() {
		return mboolValid;
	}

	public void setMboolValid(boolean mboolValid) {
		this.mboolValid = mboolValid;
	}
	
	public Model(int anzahl, String today, boolean v)
	{
		this.mintAnzahl = anzahl;
		this.mstrDatum = today;
	
		this.mboolValid = v;
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
		statement = statments.allHinzufügen.toString() + mintAnzahl
				+ "," + "now()" + ","
				+ mintIDKonto + ","
				+ mintIDProdukt + ","
				+ mintIDMarkt + ");";	
	return statement;
	
	}
	
	
}
