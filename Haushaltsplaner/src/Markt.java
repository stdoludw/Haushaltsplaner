public class Markt {

	// Einige Attribut wurden entgegen ihres natürlichen Datentypes
	// als String abbgebildet, da bei Zahlen mit führender Null
	// der Eigentliche Wert verfallen würde.

	// Markt Attribute
	private String mstrName;
	private String mstrPLZ;
	private String mstrAdr;
	private int mintEntfernung;
	private int ID;
	public String getMstrName() {
		return mstrName;
	}
	public void setMstrName(String mstrName) {
		this.mstrName = mstrName;
	}
	public String getMstrPLZ() {
		return mstrPLZ;
	}
	public void setMstrPLZ(String mstrPLZ) {
		this.mstrPLZ = mstrPLZ;
	}
	public String getMstrAdr() {
		return mstrAdr;
	}
	public void setMstrAdr(String mstrAdr) {
		this.mstrAdr = mstrAdr;
	}
	public int getMintEntfernung() {
		return mintEntfernung;
	}
	public void setMintEntfernung(int mintEntfernung) {
		this.mintEntfernung = mintEntfernung;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

}
