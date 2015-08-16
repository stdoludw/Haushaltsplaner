public class Model_Produkt  {

	// Produkt Attribute
	private String mstrName;
	private int mintGewicht;
	private float mfltPreis;
	private int mintID;
	private boolean change = false;

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public Model_Produkt(String name, int gewicht, float preis, int id) {
		this.mstrName = name;
		this.mintGewicht = gewicht;
		this.mfltPreis = preis;
		this.mintID = id;
	}

	public Model_Produkt() {
		this.mstrName = "";
		this.mintGewicht = 0;
		this.mfltPreis = 0;
		this.mintID = -1;
	}

	public String getMstrName() {
		return mstrName;
	}

	public void setMstrName(String mstrName) {
		this.mstrName = mstrName;
	}

	public int getMintGewicht() {
		return mintGewicht;
	}

	public void setMintGewicht(int mintGewicht) {
		this.mintGewicht = mintGewicht;
	}

	public float getMfltPreis() {
		return mfltPreis;
	}

	public void setMfltPreis(float mfltPreis) {
		this.mfltPreis = mfltPreis;
	}

	public String SQLerstellenProdukt() {
		String statment;
		statment = Controll_Statments.produktUpdateInsert.toString() + "\""
				+ mstrName + "\"" + "," + mintGewicht + "," + mfltPreis + mintID+","+mintID+");";

		return statment;

	}

	public String print() {
		return mstrName + "\t" + mintGewicht + "\t" + mfltPreis+"\t"+mintID;
	}

	public boolean equal(Model_Produkt tmp) {
		if (this.mstrName == tmp.mstrName
				&& this.mintGewicht == tmp.mintGewicht
				&& this.mfltPreis == tmp.mfltPreis) {
			return true;
		}
		return false;
	}
	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	public void change(Model_Produkt tmp) {
		this.mstrName = tmp.mstrName;
		this.mintGewicht = tmp.mintGewicht;
		this.mfltPreis = tmp.mfltPreis;
		this.setChange(true);
	}

}
