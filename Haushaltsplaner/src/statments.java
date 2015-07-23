
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
		return "select * from Produkt p;";
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
	}
	
	
		
			
}
