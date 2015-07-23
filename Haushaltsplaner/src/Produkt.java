public class Produkt {

	// Einige Attribut wurden entgegen ihres natürlichen Datentypes
	// als String abbgebildet, da bei Zahlen mit führender Null
	// der Eigentliche Wert verfallen würde.

	// Produkt Attribute
	private String mstrName;
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
	public int getMintID() {
		return mintID;
	}
	public void setMintID(int mintID) {
		this.mintID = mintID;
	}
	private int mintGewicht;
	private float mfltPreis;
	private int mintID;

}
