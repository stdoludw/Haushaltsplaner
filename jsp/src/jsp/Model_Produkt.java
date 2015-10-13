package jsp;

public class Model_Produkt {

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
		this.mstrName = name;
		this.mintGewicht = gewicht;
		this.mfltPreis = preis;
		this.mintID = id;

	}
public Model_Produkt() {
	// TODO Auto-generated constructor stub
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

	public String SQLerstellen() {
	
		return Controll_Statments.AddProdukt(this.mstrName, this.mintGewicht, this.mfltPreis);

	}
	
	public String SQLUpdate() {

		return Controll_Statments.UpdateProdukt(this.mstrName, this.mintGewicht, this.mfltPreis,this.mintID);

	}



}
