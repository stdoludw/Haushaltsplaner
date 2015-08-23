package lib;

public enum cmbAuswahl {
	
	Q1 {
		public String toString() {
			return "Quartal eins";
		}
	},
	Q2 {
		public String toString() {
			return "Quartal zwei";
		}
	},
	Q3 {
		public String toString() {
			return "Quartal drei";
		}
	},
	
	Produkt {
		public String toString() {
			return "Produkt anzeigen";
		}
	},
	Konto {
		public String toString() {
			return "Konto anzeigen";
		}
	},
	Markt {
		public String toString() {
			return "Markt anzeigen";
		}
	},
	Einkauf {
		public String toString() {
			return "Einkauf anzeigen";
		}
	},
	
	Statistik {
		public String toString() {
			return "Statistik anzeigen";
		}
	},
	HinzufuegenMainAktion
	{
		public String toString() {
		return "Hinzufuegen";}
	},
	 ExportMainAktion
	 {
		public String toString() {return "Exportieren";}
	 
	 },
	LadenMainAktion
	{
		 public String toString() {
		return "Laden";
		 }
	},
	StatistikMainAktion
	{
		public String toString() {return "Statistik";}
	},
	AuswahlMainAktion
	{
		public String toString() {return "Auswahl";}
	
	},
	UpdateMainAktion
	{
		public String toString() {return "Update";}
	
	},
	ProduktHinzufuegenAktion 
	{
		public String toString() {return "HinzufuegenProdukt";}
	},
	KontoHinzufuegenAktion 
	{
		public String toString() {return "HinzufuegenKonto";}
	},
	MarktHinzufuegenAktion
	{
		public String toString() {return "HinzufuegenMarkt";}
	},
	AllHinzufuegenAktion 
	{
		public String toString() {return  "HinzufuegenAll";}
	},
	LoginAbfrageAktion
	{
		public String toString() {return  "Login";}
	},
	ErstellenAbfrageAktion
	{
		public String toString() {return  "Erstellen";}
	}
	
	
}
