import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Model_Einkauf {
//#TODO evt ginbt es mit der XML erstellung ärger
	private int mintAnzahl;
	private String mstrDatum;
	private Model_Konto mkntKonto;
	private Model_Produkt mprdProdukt;
	private Model_Markt mmkrMarkt;
	private int mintID;
	private boolean mboolequal;

	public boolean isMboolequal() {
		return mboolequal;
	}

	public int getMintID() {
		return mintID;
	}
	@XmlElement
	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public String getMstrDatum() {
		return mstrDatum;
	}

	public Model_Einkauf(int anzahl, String today, int pk) {
		this.mintAnzahl = anzahl;
		this.mstrDatum = today;
		this.mintID = pk;
		this.mboolequal = true;
	}

	public void ModelArray(Model_Konto k, Model_Produkt p, Model_Markt m) {
		this.mkntKonto = k;
		this.mmkrMarkt = m;
		this.mprdProdukt = p;

	}

	public int getMintAnzahl() {
		return mintAnzahl;
	}
	@XmlElement
	public void setMintAnzahl(int mintAnzahl) {
		this.mintAnzahl = mintAnzahl;
	}

	
	public Model_Konto getMkntKonto() {
		return mkntKonto;
	}

	@XmlElement
	public void setMkntKonto(Model_Konto mkntKonto) {
		this.mkntKonto = mkntKonto;
	}

	public Model_Produkt getMprdProdukt() {
		return mprdProdukt;
	}
	
	@XmlElement
	public void setMprdProdukt(Model_Produkt mprdProdukt) {
		this.mprdProdukt = mprdProdukt;
	}

	public Model_Markt getMmkrMarkt() {
		return mmkrMarkt;
	}

	@XmlElement
	public void setMmkrMarkt(Model_Markt mmkrMarkt) {
		this.mmkrMarkt = mmkrMarkt;
	}
	
	
	public String SQlerstellenAll() {
		String statement;
		statement = Controll_Statments.AddAlles(this.mintAnzahl, this.mstrDatum, this.mkntKonto.getMintID(), this.mprdProdukt.getMintID(), this.mmkrMarkt.getMintID());
		return statement;

	}


	public boolean equal(Model_Einkauf tmp) {
		if (
				(this.mintAnzahl == tmp.mintAnzahl) 
				&& (this.mstrDatum == tmp.mstrDatum)
				&& (this.mprdProdukt.getMintID() == tmp.mprdProdukt.getMintID())
				&& (this.mmkrMarkt.getMintID() == tmp.mmkrMarkt.getMintID())
				&& (this.mkntKonto.getMintID() == tmp.mkntKonto.getMintID())
			) 
				{
			mboolequal = true;
			return true;
				}
		mboolequal = false;
		
		this.mintAnzahl = tmp.mintAnzahl;
		this.mstrDatum = tmp.mstrDatum;
		this.mprdProdukt = tmp.mprdProdukt;
		this.mmkrMarkt = tmp.mmkrMarkt;
		this.mkntKonto = tmp.mkntKonto;
		return false;
	}

	public Object[] print() {
	
		Object [] tmp=
			{
				
				this.mstrDatum,this.mintAnzahl,
				this.mkntKonto.getMstrName(),this.mkntKonto.getMstrKnr(),this.mkntKonto.getMstrBLZ(),this.mkntKonto.getMstrBetrag(),this.mkntKonto.getMintMin(),
				this.mmkrMarkt.getMstrName(),this.mmkrMarkt.getMstrPLZ(),this.mmkrMarkt.getMstrAdr(),this.mmkrMarkt.getMintEntfernung(),
				this.mprdProdukt.getMstrName(),this.mprdProdukt.getMintGewicht(),this.mprdProdukt.getMintGewicht()};
				return tmp;
		}
	
	

}
