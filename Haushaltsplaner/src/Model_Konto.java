public class Model_Konto {

	// Konto attribute
	private String mstrBetrag;
	private String mstrName;
	private String mstrBLZ;
	private String mstrKnr;
	private String mstrMin;
	private int mintID;
	private boolean change;

	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public Model_Konto(String name, String blz, String knr, String betrag,
			String min, int id) {
		this.mstrBetrag = betrag;
		this.mstrName = name;
		this.mstrBLZ = blz;
		this.mstrKnr = knr;
		this.mstrMin = min;
		this.mintID = id;

	}

	public Model_Konto() {
		this.mstrBetrag = "";
		this.mstrName = "";
		this.mstrBLZ = "";
		this.mstrKnr = "";
		this.mstrMin = "";
		this.mintID = -1;

	}

	public String getMstrBetrag() {
		return mstrBetrag;
	}

	public void setMstrBetrag(String mstrBetrag) {
		this.mstrBetrag = mstrBetrag;
	}

	public String getMstrName() {
		return mstrName;
	}

	public void setMstrName(String mstrName) {
		this.mstrName = mstrName;
	}

	public String getMstrBLZ() {
		return mstrBLZ;
	}

	public void setMstrBLZ(String mstrBLZ) {
		this.mstrBLZ = mstrBLZ;
	}

	public String getMstrKnr() {
		return mstrKnr;
	}

	public void setMstrKnr(String mstrKnr) {
		this.mstrKnr = mstrKnr;
	}

	public String getMintMin() {
		return mstrMin;
	}

	public void setMintMin(String mintMin) {
		this.mstrMin = mintMin;
	}

	public String SQLerstellenKonto() {
		String statment;
		statment = Controll_Statments.kontoUpdateInsert.toString() + "\""
				+ mstrName + "\"" + "," + "\"" + mstrBLZ + "\"" + "," + "\""
				+ mstrKnr + "\"" + "," + mstrBetrag + "," + mstrMin+","+mintID + ");";
		return statment;
	}

	public String print() {
		return mstrName + "\t" + mstrBLZ + "\t" + mstrKnr + "\t" + mstrBetrag+"\t"+mstrMin+"\t"+mintID;
				
	}

	public boolean equal(Model_Konto tmp) {
		if (this.mstrBetrag == tmp.mstrBetrag && this.mstrName == tmp.mstrName
				&& this.mstrBLZ == tmp.mstrBLZ && this.mstrKnr == tmp.mstrKnr
				&& this.mstrMin == tmp.mstrMin) {
			return true;
		}
		return false;

	}

	public void change(Model_Konto tmp) {
		this.mstrBetrag = tmp.mstrBetrag;
		this.mstrName = tmp.mstrName;
		this.mstrBLZ = tmp.mstrBLZ;
		this.mstrKnr = tmp.mstrKnr;
		this.mstrMin = tmp.mstrMin;
		this.setChange(true);
	}

}
