public class HHP {

	public static void main(String[] args) {

		try {
			// einstieg in das Programm
			Controll c = new Controll();

			// init von controll aufrufen
			c.start();

			// beenden des Programms
			c = null;
		} catch (Exception p) {
			System.err.println(p.getMessage());
		}

	}

}
