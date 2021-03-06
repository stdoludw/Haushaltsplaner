package MODEL;

import CONTROLLER.CONTROLLER_Statments;

public class MODEL_Markt {

	// Markt Attribute
	private String mstrName;
	private String mstrPLZ;
	private String mstrAdr;
	private int mintEntfernung;
	private int mintID;

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public MODEL_Markt(String name, String plz, String adr, int entfernung, int id) {
		this.mstrName = name;
		this.mstrPLZ = plz;
		this.mstrAdr = adr;
		this.mintEntfernung = entfernung;
		this.mintID = id;

	}

	public MODEL_Markt() {
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

	public String SQLinsert() {
	
		return CONTROLLER_Statments.AddMarkt(this.mstrName, this.mstrPLZ, this.mstrAdr, this.mintEntfernung);
	}
	
	public String SQLdelete() {

		return CONTROLLER_Statments.DeleteMarkt(this.mintID);
	}
	
	public String SQLupdate() {

		return CONTROLLER_Statments.UpdateMarkt(this.mstrName, this.mstrPLZ, this.mstrAdr, this.mintEntfernung, this.mintID);
	}

	
}
