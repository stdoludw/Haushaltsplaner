package classToXML;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Kunde {

	private String name;
	private String vorname;
	private int id;

	
	public int getId() {
		return id;
	}
	
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	@XmlElement
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public void generateXML()
	{
		
	}
	
}
