public class Model_Konto extends Model_Main {

	// Konto attribute
	private String mstrBetrag;
	private String mstrName;
	private String mstrBLZ;
	private String mstrKnr;
	private int mintMin;
	private int mintID;

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public Model_Konto(String name, String blz, String knr, String betrag,
			int min, int id) {
		super(0,"",-1);		this.mstrBetrag = betrag;
		this.mstrName = name;
		this.mstrBLZ = blz;
		this.mstrKnr = knr;
		this.mintMin = min;
		this.mintID = id;

	}

	public Model_Konto() {
		super(0,"",-1);		this.mstrBetrag = "";
		this.mstrName = "";
		this.mstrBLZ = "";
		this.mstrKnr = "";
		this.mintMin = 0;
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

	public int getMintMin() {
		return mintMin;
	}

	public void setMintMin(int mintMin) {
		this.mintMin = mintMin;
	}

	public String SQLerstellenKonto() {
		String statment;
		statment = Controll_Statments.kontoUpdateInsert.toString() + "\""
				+ mstrName + "\"" + "," + "\"" + mstrBLZ + "\"" + "," + "\""
				+ mstrKnr + "\"" + "," + mstrBetrag + "," + mintMin+","+mintID + ");";
		return statment;
	}

	public String print() {
		return mstrName + "\t" + mstrBLZ + "\t" + mstrKnr + "\t" + mstrBetrag+"\t"+mintMin+"\t"+mintID;
				
	}

	public boolean equal(Model_Konto tmp) {
		if (this.mstrBetrag == tmp.mstrBetrag && this.mstrName == tmp.mstrName
				&& this.mstrBLZ == tmp.mstrBLZ && this.mstrKnr == tmp.mstrKnr
				&& this.mintMin == tmp.mintMin) {
			return true;
		}
		return false;

	}

	public void change(Model_Konto tmp) {
		this.mstrBetrag = tmp.mstrBetrag;
		this.mstrName = tmp.mstrName;
		this.mstrBLZ = tmp.mstrBLZ;
		this.mstrKnr = tmp.mstrKnr;
		this.mintMin = tmp.mintMin;
		super.setChange(true);
	}

}
