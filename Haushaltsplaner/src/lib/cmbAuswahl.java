package lib;

public enum cmbAuswahl {
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
	}
}
