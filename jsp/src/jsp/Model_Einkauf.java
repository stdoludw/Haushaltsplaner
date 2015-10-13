package jsp;

public class Model_Einkauf {

	private int mintAnzahl;
	private String mstrDatum;
	private Model_Konto mkntKonto;
	private Model_Produkt mprdProdukt;
	private Model_Markt mmkrMarkt;
	private int mintID;


	public int getMintID() {
		return mintID;
	}

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public String getMstrDatum() {
		return mstrDatum;
	}

	public Model_Einkauf(int anzahl, String today, int pk) {
		this.mintAnzahl = anzahl;
		this.mstrDatum = today;
		this.mintID = pk;
	}

	public void ModelArray(Model_Konto k, Model_Produkt p, Model_Markt m) {
		this.mkntKonto = k;
		this.mmkrMarkt = m;
		this.mprdProdukt = p;

	}

	public int getMintAnzahl() {
		return mintAnzahl;
	}

	public void setMintAnzahl(int mintAnzahl) {
		this.mintAnzahl = mintAnzahl;
	}

	public Model_Konto getMkntKonto() {
		return mkntKonto;
	}

	public Model_Produkt getMprdProdukt() {
		return mprdProdukt;
	}

	public Model_Markt getMmkrMarkt() {
		return mmkrMarkt;
	}

	
	public String SQlerstellenAll() {
		String statement;
		statement = Controll_Statments.AddEinkauf(this.mintAnzahl, this.mstrDatum, this.mkntKonto.getMintID(), this.mprdProdukt.getMintID(), this.mmkrMarkt.getMintID());
		return statement;

	}

}
