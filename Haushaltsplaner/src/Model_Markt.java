import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Model_Markt {

	// Markt Attribute
	private String mstrName;
	private String mstrPLZ;
	private String mstrAdr;
	private int mintEntfernung;
	private int mintID;
	private boolean mboolequal;

	public boolean isMboolequal() {
		return mboolequal;
	}
	@XmlElement
	public void setMintID(int mintID) {
		this.mintID = mintID;
	}

	public int getMintID() {
		return mintID;
	}

	public Model_Markt(String name, String plz, String adr, int entfernung, int id) {
		this.mstrName = name;
		this.mstrPLZ = plz;
		this.mstrAdr = adr;
		this.mintEntfernung = entfernung;
		this.mintID = id;
		this.mboolequal = true;

	}

	public Model_Markt() {
		this.mstrName = "";
		this.mstrPLZ = "";
		this.mstrAdr = "";
		this.mintEntfernung = 0;
		this.mintID = -1;
	}

	public String getMstrName() {
		return mstrName;
	}
	@XmlElement
	public void setMstrName(String mstrName) {
		this.mstrName = mstrName;
	}

	public String getMstrPLZ() {
		return mstrPLZ;
	}
	@XmlElement
	public void setMstrPLZ(String mstrPLZ) {
		this.mstrPLZ = mstrPLZ;
	}

	public String getMstrAdr() {
		return mstrAdr;
	}
	@XmlElement
	public void setMstrAdr(String mstrAdr) {
		this.mstrAdr = mstrAdr;
	}

	public int getMintEntfernung() {
		return mintEntfernung;
	}
	@XmlElement
	public void setMintEntfernung(int mintEntfernung) {
		this.mintEntfernung = mintEntfernung;
	}

	public String SQLerstellen() {
	
		return Controll_Statments.AddMarkt(this.mstrName, this.mstrPLZ, this.mstrAdr, this.mintEntfernung);
	}
	
	public String SQLUpdate() {

		return Controll_Statments.UpdateMarkt(this.mstrName, this.mstrPLZ, this.mstrAdr, this.mintEntfernung, this.mintID);
	}
	public Object[] print() {
		Object[] tmp = {this.mstrName, this.mstrPLZ , this.mstrAdr ,this.mintEntfernung,this.mintID};
		return tmp;
	}

	public boolean equal(Model_Markt tmp) {
		if (this.mstrName == tmp.mstrName && this.mstrPLZ == tmp.mstrPLZ && this.mstrAdr == tmp.mstrAdr
				&& this.mintEntfernung == tmp.mintEntfernung) {
			mboolequal = true;
			return true;
		}
		mboolequal = false;
		this.mstrName = tmp.mstrName;
		this.mstrPLZ = tmp.mstrPLZ;
		this.mstrAdr = tmp.mstrAdr;
		this.mintEntfernung = tmp.mintEntfernung;
		return false;
	}

}
