import java.util.Vector;

public class Model_Einkauf {

	private int mintAnzahl;
	private String mstrDatum;
	private int mintIDProdukt;
	private int mintIDMarkt;
	private int mintIDKonto;
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
		statement = Controll_Statments.allHinzufuegen.toString() + mintAnzahl + "," + "now()" + "," + mintIDKonto + ","
				+ mintIDProdukt + "," + mintIDMarkt + ");";
		return statement;

	}

	public String print(Vector<Object> tmp) {

		String produkt = null, markt = null, konto = null;
		for (Object element : tmp) {
			if (element instanceof Model_Produkt) {
				if (((Model_Produkt) element).getMintID() == this.mintIDProdukt) {
					produkt = ((Model_Produkt) element).print();
				}
			} else if (element instanceof Model_Konto) {
				if (((Model_Konto) element).getMintID() == this.mintIDKonto) {
					konto = ((Model_Konto) element).print();
				}
			} else if (element instanceof Model_Markt) {
				if (((Model_Markt) element).getMintID() == this.mintIDMarkt) {
					markt = ((Model_Markt) element).print();
				}
			}
		}
		return mintAnzahl + "\t" + mstrDatum + "\t" + produkt + "\t" + konto + "\t" + markt +"\n";
	}

	public String print()
	{
		return mintAnzahl + "\t" + mstrDatum + "\t" + mintIDKonto + "\t" + mintIDMarkt + "\t" + mintIDProdukt +"\n";

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

	public boolean equal(Model_Einkauf tmp) {
		if (this.mintAnzahl == tmp.mintAnzahl && this.mstrDatum == tmp.mstrDatum
				&& this.mintIDProdukt == tmp.mintIDProdukt && this.mintIDMarkt == tmp.mintIDMarkt
				&& this.mintIDKonto == tmp.mintIDKonto) {
			mboolequal = true;
			return true;
		}
		mboolequal = false;
		this.mintAnzahl = tmp.mintAnzahl;
		this.mstrDatum = tmp.mstrDatum;
		this.mintIDProdukt = tmp.mintIDProdukt;
		this.mintIDMarkt = tmp.mintIDMarkt;
		this.mintIDKonto = tmp.mintIDKonto;
		return false;
	}

}
