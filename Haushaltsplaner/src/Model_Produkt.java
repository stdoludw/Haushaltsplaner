public class Model_Produkt extends Model_Main{

	// Einige Attribut wurden entgegen ihres natürlichen Datentypes
	// als String abbgebildet, da bei Zahlen mit führender Null
	// der Eigentliche Wert verfallen würde.

	// Produkt Attribute
	private String mstrName;
	private int mintGewicht;
	private float mfltPreis;
	private int mintID;

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public Model_Produkt(String name, int gewicht, float preis, int id) {
		super(0,"", true);

		this.mstrName = name;
		this.mintGewicht = gewicht;
		this.mfltPreis = preis;
		this.mintID = id;
	}

	public Model_Produkt() {
		super(0,"", true);

		this.mstrName = "";
		this.mintGewicht = 0;
		this.mfltPreis = 0;
		this.mintID = -1;
	}

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

	public String SQLerstellenProdukt()
	{
		String statment;
		statment = Controll_Statments.produktHinzufügen.toString() + "\""
				+ mstrName + "\"" + ","
				+ mintGewicht + ","
				+ mfltPreis + ");";
	
	return  statment;

	}
	
	public String print()
	{
	return  mstrName + "\t" + mintGewicht + "\t"+ mfltPreis;
	}
	
}
