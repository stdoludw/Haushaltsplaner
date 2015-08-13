
public class Model_Main {

	private int mintAnzahl;
	private String mstrDatum;
	private int mintIDProdukt;
	private int mintIDMarkt;
	private int mintIDKonto;
	private int mintID;
	private boolean change = false;

	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
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

	public Model_Main(int anzahl, String today, int pk) {
		this.mintAnzahl = anzahl;
		this.mstrDatum = today;
		this.mintID = pk;
	}

	public void ModelArray(int p, int k, int m) {
		this.mintIDKonto = k;
		this.mintIDMarkt = m;
		this.mintIDProdukt = p;

	}

	public int getMintAnzahl() {
		return mintAnzahl;
	}

	public void setMintAnzahl(int mintAnzahl) {
		this.mintAnzahl = mintAnzahl;
	}

	public String SQlerstellenAll() {
		String statement;
		statement = Controll_Statments.allHinzufuegen.toString() + mintAnzahl
				+ "," + "now()" + "," + mintIDKonto + "," + mintIDProdukt + ","
				+ mintIDMarkt + ");";
		return statement;

	}

	public String print() {
		return mintAnzahl + "\t" + mstrDatum + "\t"+mintID;
	}

	public int getMintIDKonto() {
		return mintIDKonto;
	}

	public int getMintIDProdukt() {
		return mintIDProdukt;
	}

	public int getMintIDMarkt() {
		return mintIDMarkt;
	}

	public boolean equal(Model_Main tmp) {
		if (this.mintAnzahl == tmp.mintAnzahl
				&& this.mstrDatum == tmp.mstrDatum
				&& this.mintIDProdukt == tmp.mintIDProdukt
				&& this.mintIDMarkt == tmp.mintIDMarkt
				&& this.mintIDKonto == tmp.mintIDKonto) {
			return true;
		}
		return false;
	}

	public void change(Model_Main tmp) {
		this.mintAnzahl = tmp.mintAnzahl;
		this.mstrDatum = tmp.mstrDatum;
		this.change = true;
	}

}
