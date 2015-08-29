public class Model_Konto {

	// Konto attribute
	private String mstrBetrag;
	private String mstrName;
	private String mstrBLZ;
	private String mstrKnr;
	private String mstrMin;
	private int mintID;
	private boolean mboolequal;

	public boolean isMboolequal() {
		return mboolequal;
	}

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public Model_Konto(String name, String blz, String knr, String betrag, String min, int id) {
		this.mstrBetrag = betrag;
		this.mstrName = name;
		this.mstrBLZ = blz;
		this.mstrKnr = knr;
		this.mstrMin = min;
		this.mintID = id;
		this.mboolequal = true;

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

	public String SQLerstellen(AES_verschluesselung aes) {
	
		return Controll_Statments.AddKonto(this.mstrName, this.mstrBLZ, this.mstrKnr, this.mstrBetrag, this.mstrMin,aes);
	}
	public String SQLUpdate(AES_verschluesselung aes) {

		return Controll_Statments.UpdateKonto(this.mstrName, this.mstrBLZ, this.mstrKnr, this.mstrBetrag, this.mstrMin, this.mintID,aes);
	}
	public Object[] print() {
		Object [] tmp = {this.mstrName,this.mstrKnr,this.mstrBLZ,this.mstrBetrag,this.mstrMin,this.mintID};
		return tmp;

	}

	public boolean equal(Model_Konto tmp) {
		if (this.mstrBetrag == tmp.mstrBetrag && this.mstrName == tmp.mstrName && this.mstrBLZ == tmp.mstrBLZ
				&& this.mstrKnr == tmp.mstrKnr && this.mstrMin == tmp.mstrMin) {
			mboolequal = true;
			return true;
		}
		mboolequal = false;

		this.mstrBetrag = tmp.mstrBetrag;
		this.mstrName = tmp.mstrName;
		this.mstrBLZ = tmp.mstrBLZ;
		this.mstrKnr = tmp.mstrKnr;
		this.mstrMin = tmp.mstrMin;

		return false;

	}

}
