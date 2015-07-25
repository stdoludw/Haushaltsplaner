import java.sql.SQLException;

public class HHP {

	public static void main(String[] args) {

			// einstieg in das Programm
			Controll c = new Controll();

			// init von controll aufrufen
			try {
				c.start();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// beenden des Programms
			c = null;
		

	}

}
