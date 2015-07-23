import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 


public class Controll {

	//verbindung mit Datenbank
	private String mstrUserName;
	private String mstrPasswort;
	
	private Connection mconCon;
	private int mintPort;
	private String mstrDatenbankName;
	private String mstrHostName;
	
	//Instanzen von M und V
	private Vector<Model> mvecModel;
	private View mvieView;
	
	void start()
	{
		this.mvieView = new View();
		this.mstrPasswort = "dlu";
		this.mstrUserName = "dlu";
		this.mconCon = null;
		this.mintPort = 3306;
		this.mstrDatenbankName ="HausHaltsPlaner_Database";
		this.mstrHostName ="dfch-ludwig.de";
		
		acces();
	}
	
	void acces()
	{
		try {
			// Datenbanktreiber für JDBC Schnittstellen laden.
	        Class.forName("com.mysql.jdbc.Driver"); 
	 
	        // Verbindung zur JDBC-Datenbank herstellen.
	        mconCon = DriverManager.getConnection("jdbc:mysql://"+mstrHostName+":"+ mintPort+"/"+mstrDatenbankName+"?"+"user="+mstrUserName+"&"+"password="+mstrPasswort);
		
	        //abfrage statement erstellen
	        Statement query;
	        query = mconCon.createStatement();
		
	        //Query bearbeiten
	        String sql = "select * from merge me, Produkt p, Konto k, Markt m where me.m_ID = m.M_ID AND me.p_ID = p.P_ID AND me.k_ID = k.K_ID;";
	        ResultSet result = query.executeQuery(sql);
	        
	        //speichern in Klasse
	        while(result.next())
	        {
	        	System.out.println(result.getString("k.name"));
	        }
	        
	        //scließen des streams
	        result.close();
		
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	
	
	
}
