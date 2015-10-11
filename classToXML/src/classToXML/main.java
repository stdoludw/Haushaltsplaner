package classToXML;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class main {

	public static void main(String[] args) {
		 loadXML();
		 //XML to DB
		 //DB to DB
		}
	
	private static void saveXML()
	{
		 Kunde customer = new Kunde();
		  
		  customer.setId(100);
		  customer.setName("Ludwig");
		  customer.setVorname("Dominik");

		  try {

			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Kunde.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customer, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
	}
	
	private static void loadXML()
	{
		 try {

				File file = new File("file.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Kunde.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Kunde customer = (Kunde) jaxbUnmarshaller.unmarshal(file);

			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
	}
		
		
	}
