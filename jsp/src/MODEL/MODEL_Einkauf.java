package MODEL;

import CONTROLLER.CONTROLLER_Statments;

public class MODEL_Einkauf {

	private int mintAnzahl;
	private String mstrDatum;
	private MODEL_Konto mkntKonto;
	private MODEL_Produkt mprdProdukt;
	private MODEL_Markt mmkrMarkt;
	private int mintID;


	public int getMintID() {
		return mintID;
	}

	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public String getMstrDatum() {
		return mstrDatum;
	}

	public void setMstrDatum(String mstrDatum) {
		
		if(mstrDatum.split("-")[1] == "00" || mstrDatum.split("-")[2] == "00")
		{
			this.mstrDatum = mstrDatum.split("-")[0] + "-01-01";
		}
		else
		{
	 this.mstrDatum = mstrDatum;
		}
	}
	public MODEL_Einkauf(int anzahl, String today, int pk, MODEL_Konto k, MODEL_Produkt p, MODEL_Markt m) {
		this.mintAnzahl = anzahl;
		this.mstrDatum = today;
		this.mintID = pk;
		this.mkntKonto = k;
		this.mmkrMarkt = m;
		this.mprdProdukt = p;
	}

	public MODEL_Einkauf() {
		this.mintAnzahl = -1;
		this.mstrDatum = "";
		this.mintID = -1;
		this.mkntKonto = null;
		this.mmkrMarkt = null;
		this.mprdProdukt = null;
	}

	public int getMintAnzahl() {
		return mintAnzahl;
	}

	public void setMintAnzahl(int mintAnzahl) {
		
		if(mintAnzahl>0)
		{
		this.mintAnzahl = mintAnzahl;
		}
		else
		{
			this.mintAnzahl =1;
		}
	}

	public MODEL_Konto getMkntKonto() {
		return mkntKonto;
	}

	public MODEL_Produkt getMprdProdukt() {
		return mprdProdukt;
	}

	public MODEL_Markt getMmkrMarkt() {
		return mmkrMarkt;
	}

	
	public String SQlinsert() {
		String statement;
		statement = CONTROLLER_Statments.AddEinkauf(this.mintAnzahl, this.mstrDatum, this.mkntKonto.getMintID(), this.mprdProdukt.getMintID(), this.mmkrMarkt.getMintID());
		return statement;

	}
	public String SQlupdate() {
		String statement;
		statement = CONTROLLER_Statments.UpdateEinkauf(this.mintAnzahl, this.mstrDatum, this.mkntKonto.getMintID(), this.mprdProdukt.getMintID(), this.mmkrMarkt.getMintID(), this.mintID);    
		return statement;

	}
	public String SQldelete() {
		String statement;
		statement = CONTROLLER_Statments.DeleteEinkauf(this.mmkrMarkt.getMintID());
		return statement;

	}

}
