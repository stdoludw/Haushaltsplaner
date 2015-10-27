package MODEL;

import CONTROLLER.CONTROLLER_Statments;

public class MODEL_Konto {

	// Konto attribute
	private String mstrBetrag;
	private String mstrName;
	private String mstrBLZ;
	private String mstrKnr;
	private String mstrMin;
	private int mintID;

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public MODEL_Konto(String name, String blz, String knr, String betrag, String min, int id) {
		this.mstrBetrag = betrag;
		this.mstrName = name;
		this.mstrBLZ = blz;
		this.mstrKnr = knr;
		this.mstrMin = min;
		this.mintID = id;

	}

	public MODEL_Konto() {
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

	public String SQLinsert(AES_verschluesselung aes) {
	
		return CONTROLLER_Statments.AddKonto(this.mstrName, this.mstrBLZ, this.mstrKnr, this.mstrBetrag, this.mstrMin,aes);
	}
	public String SQLUpdate(AES_verschluesselung aes) {

		return CONTROLLER_Statments.UpdateKonto(this.mstrName, this.mstrBLZ, this.mstrKnr, this.mstrBetrag, this.mstrMin, this.mintID,aes);
	}
	public String SQLdelete() {

		return CONTROLLER_Statments.DeleteKonto(this.mintID);
	}



}
