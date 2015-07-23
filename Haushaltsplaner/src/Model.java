import java.sql.Date;


public class Model {

	private int mintAnzahl;
	public int getMintAnzahl() {
		return mintAnzahl;
	}
	public void setMintAnzahl(int mintAnzahl) {
		this.mintAnzahl = mintAnzahl;
	}
	public Date getMdateDatum() {
		return mdateDatum;
	}
	public void setMdateDatum(Date mdateDatum) {
		this.mdateDatum = mdateDatum;
	}
	public Konto getMkntKonto() {
		return mkntKonto;
	}
	public void setMkntKonto(Konto mkntKonto) {
		this.mkntKonto = mkntKonto;
	}
	public Produkt getMprdProdukt() {
		return mprdProdukt;
	}
	public void setMprdProdukt(Produkt mprdProdukt) {
		this.mprdProdukt = mprdProdukt;
	}
	public Markt getMmktMarkt() {
		return mmktMarkt;
	}
	public void setMmktMarkt(Markt mmktMarkt) {
		this.mmktMarkt = mmktMarkt;
	}
	private Date mdateDatum;
	private Konto mkntKonto;
	private Produkt mprdProdukt;
	private Markt mmktMarkt;
	
}
