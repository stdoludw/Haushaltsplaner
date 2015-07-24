public class Markt extends Model{

	// Einige Attribut wurden entgegen ihres natürlichen Datentypes
	// als String abbgebildet, da bei Zahlen mit führender Null
	// der Eigentliche Wert verfallen würde.

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

	public Markt(String name, String plz, String adr, int entfernung, int id)
	{
		this.mstrName = name;
		this.mstrPLZ=plz;
		this.mstrAdr = adr;
		this.mintEntfernung = entfernung;
		this.mintID = id;
	}
	
	public Markt()
	{
		this.mstrName = "";
		this.mstrPLZ="";
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
	
	private void SQLerstellenMarkt()
	{
	
		mMdlModel = marktEinlesen();
		se = statments.marktHinzufügen.toString() + "\""
				+ mMdlModel.getMmktMarkt().getMstrName() + "\"" + ","
				+ "\"" + mMdlModel.getMmktMarkt().getMstrPLZ() + "\"" + ","
				+ "\"" + mMdlModel.getMmktMarkt().getMstrAdr() + "\"" + ","
				+ mMdlModel.getMmktMarkt().getMintEntfernung() + ");";
	}
	
}
