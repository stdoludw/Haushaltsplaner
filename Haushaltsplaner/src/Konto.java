
public class Konto {

	//Einige Attribut wurden entgegen ihres natürlichen Datentypes
	//als String abbgebildet, da bei Zahlen mit führender Null 
	//der Eigentliche Wert verfallen würde. 
	
	//Konto attribute
	private String mstrBetrag;
	private String mstrName;
	private String mstrBLZ;		
	private String mstrKnr;
	private int mintMin;
	private int mintID;
	
	
	public Konto(String betrag, String name, String blz, String knr, int min, int id)
	{
		this.mstrBetrag = betrag;
		this.mstrName = name;
		this.mstrBLZ = blz;
		this.mstrKnr = knr;
		this.mintMin = min;
		this.mintID = id;
	
	}
	
	public Konto()
	{
		this.mstrBetrag = "";
		this.mstrName = "";
		this.mstrBLZ = "";
		this.mstrKnr = "";
		this.mintMin = 0;
		this.mintID = 999;
	
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
	public int getMintID() {
		return mintID;
	}
	public void setMintID(int mintID) {
		this.mintID = mintID;
	}
	
	
}
