
public enum statments {
	all
	{
		public String toString()
		{
		return "select * from merge me, Produkt p, Konto k, Markt m where me.m_ID = m.M_ID AND me.p_ID = p.P_ID AND me.k_ID = k.K_ID;";
		}
	},
	
	produkt
	{
		public String toString()
		{
		return "select * from Produkt p, merge me where p.P_ID = me.p_ID;";
		}
	},
	konto
	{
		public String toString()
		{
		return "select * from Konto k;";
		}
	},
	markt
	{
		public String toString()
		{
		return "select * from Markt m;";
		}
	},
	
	allHinzufügen
	{
		public String toString()
		{
			return "insert into merge(anzahl,datum,m_ID,k_ID,p_ID) Values(";
 
		}
	},
	produktHinzufügen
	{
		public String toString()
		{
			return "insert into Produkt(name,gewicht,preis) Values(";
		}
	},
	kontoHinzufügen
	{
		public String toString()
		{
			return "insert into Konto(name,bankleitzahl,kontonummer,betrag,minimum) Values(";
		}
	},
	marktHinzufügen
	{
		public String toString()
		{
			return "insert into Markt(name,postleitzahl,adresse,entfernung) Values(";
		}
	}		
}
