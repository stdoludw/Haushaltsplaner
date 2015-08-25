
public class Model_Einkauf {

	private int mintAnzahl;
	private String mstrDatum;
	private Model_Konto mkntKonto;
	private Model_Produkt mprdProdukt;
	private Model_Markt mmkrMarkt;
	private int mintID;
	private boolean mboolequal;

	public boolean isMboolequal() {
		return mboolequal;
	}

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
		this.mboolequal = true;
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

	public String SQlerstellenAll() {
		String statement;
		statement = Controll_Statments.AddAlles(this.mintAnzahl, this.mstrDatum, this.mkntKonto.getMintID(), this.mprdProdukt.getMintID(), this.mmkrMarkt.getMintID());
		return statement;

	}


	public boolean equal(Model_Einkauf tmp) {
		if (
				(this.mintAnzahl == tmp.mintAnzahl) 
				&& (this.mstrDatum == tmp.mstrDatum)
				&& (this.mprdProdukt.getMintID() == tmp.mprdProdukt.getMintID())
				&& (this.mmkrMarkt.getMintID() == tmp.mmkrMarkt.getMintID())
				&& (this.mkntKonto.getMintID() == tmp.mkntKonto.getMintID())
			) 
				{
			mboolequal = true;
			return true;
				}
		mboolequal = false;
		
		//#TODO Fehler wegen tiefenkopie?
		this.mintAnzahl = tmp.mintAnzahl;
		this.mstrDatum = tmp.mstrDatum;
		this.mprdProdukt = tmp.mprdProdukt;
		this.mmkrMarkt = tmp.mmkrMarkt;
		this.mkntKonto = tmp.mkntKonto;
		return false;
	}

	public Object[] print() {
	
		Object [] tmp=
			{
				this.mstrDatum,this.mintAnzahl,this.mkntKonto.print(), this.mmkrMarkt.print(), this.mprdProdukt.print()
			};
				return tmp;
		}

}
