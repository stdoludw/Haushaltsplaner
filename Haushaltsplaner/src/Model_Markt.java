public class Model_Markt {

	// Markt Attribute
	private String mstrName;
	private String mstrPLZ;
	private String mstrAdr;
	private int mintEntfernung;
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

	public Model_Markt(String name, String plz, String adr, int entfernung, int id) {
		this.mstrName = name;
		this.mstrPLZ = plz;
		this.mstrAdr = adr;
		this.mintEntfernung = entfernung;
		this.mintID = id;
		this.mboolequal = true;

	}

	public Model_Markt() {
		this.mstrName = "";
		this.mstrPLZ = "";
		this.mstrAdr = "";
		this.mintEntfernung = 0;
		this.mintID = -1;
	}

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

	public String SQLerstellen() {
		String statment;
		statment = Controll_Statments.marktUpdateInsert.toString() + "\"" + mstrName + "\"" + "," + "\"" + mstrPLZ
				+ "\"" + "," + "\"" + mstrAdr + "\"" + "," + mintEntfernung + mintID + ");";

		return statment;
	}
	
	public String SQLentfernen() {
		String statment;
		statment = Controll_Statments.marktUpdateDelete.toString() + mintID + ";";

		return statment;
	}
	public Object[] print() {
		Object[] tmp = {this.mstrName, this.mstrPLZ , this.mstrAdr ,this.mintEntfernung,this.mintID};
		return tmp;
	}

	public boolean equal(Model_Markt tmp) {
		if (this.mstrName == tmp.mstrName && this.mstrPLZ == tmp.mstrPLZ && this.mstrAdr == tmp.mstrAdr
				&& this.mintEntfernung == tmp.mintEntfernung) {
			mboolequal = true;
			return true;
		}
		mboolequal = false;
		this.mstrName = tmp.mstrName;
		this.mstrPLZ = tmp.mstrPLZ;
		this.mstrAdr = tmp.mstrAdr;
		this.mintEntfernung = tmp.mintEntfernung;
		return false;
	}

}
