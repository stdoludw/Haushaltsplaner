import java.io.IOException;
import java.sql.SQLException;

public class Init_HHP {

	public static void main(String[] args) {

			// einstieg in das Programm
			Controll_Main c = new Controll_Main();

			// init von controll aufrufen
			try {
				c.start();
			} catch (ClassNotFoundException | SQLException  | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			// beenden des Programms
			c = null;
		

	}

}
