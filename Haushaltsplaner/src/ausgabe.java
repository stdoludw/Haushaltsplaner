import java.util.Scanner;
import java.util.Vector;

public class ausgabe {

	private String mstrUserName;
	private String mstrPasswort;
	private String mstrDatenbankName;
	private String mstrHostName;
	private boolean mboolEinlesen;
	private Vector<Model_Main> mvecModel;

	// wird für sync benötigt
	public Vector<Model_Main> getMvecModel() {
		return mvecModel;
	}

	// wird für die ausgabe benötigt
	public void setMvecModel(Vector<Model_Main> mvecModelp) {
		this.mvecModel = mvecModelp;
	}

	public ausgabe() {

		this.mstrPasswort = "";
		this.mstrUserName = "";
		this.mstrDatenbankName = "";
		this.mstrHostName = "";
		this.mboolEinlesen = false;

	}

	public boolean isMboolEinlesen() {
		return mboolEinlesen;
	}

	public boolean metaDaten() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("\t\tWillkommen zum Haushaltsplaner V4\n\n");
		
		System.out.println("Neue Datenbank anlegen (J/N): ");
		if(scanner.next().toUpperCase() == "J")
		{
			return true;
		}
		
		System.out.println("IP Adresse des DatenbankServers eingeben: ");
		mstrHostName = scanner.next();

		System.out.println("Datenbankname eingeben: ");
		mstrDatenbankName = scanner.next();

		System.out.println("Username eingeben: ");
		mstrUserName = scanner.next();

		System.out.println("Passwort eingeben: ");
		mstrPasswort = scanner.next();
		
		return false;
	}

	public String getMstrUserName() {
		return mstrUserName;
	}

	public String getMstrPasswort() {
		return mstrPasswort;
	}

	public String getMstrDatenbankName() {
		return mstrDatenbankName;
	}

	public String getMstrHostName() {
		return mstrHostName;
	}

	public String choice() {

		String auswahl, se = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n\n\n");
		System.out.println("(P)rodukte anzeigen: ");
		System.out.println("(K)onten anzeigen: ");
		System.out.println("(M)ärkte anzeigen: ");
		System.out.println("(A)lles anzeigen: ");
		System.out.println("(H)inzufügen: ");
		System.out.println("(B)eenden: ");

		auswahl = scanner.next();

		switch (auswahl.toUpperCase()) {
		case "P":
			se = Controll_Statments.produkt.toString();
			break;
		case "K":
			se = Controll_Statments.konto.toString();
			break;
		case "M":
			se = Controll_Statments.markt.toString();
			break;
		case "H":
			mboolEinlesen = true;
			eingabe();
			break;
		case "B":
			System.exit(0);

		default:
			se = Controll_Statments.all.toString();
			break;
		}

		return se;

	}

	public void print(String auswahl) {

		for (int i = 0; i < mvecModel.size(); i++) {
			// Produkt ausgabe -> wenn ken Preis vorhanden -> kein Produkt
			if (auswahl == Controll_Statments.produkt.toString()
					&& ((Model_Produkt) mvecModel.get(i)).getMfltPreis() != 0.0) {
				System.out
						.println("-------------------------------------------------");
				System.out.println("|name\t\t\t|gewicht\t|preis\t|");
				System.out
						.println("-------------------------------------------------");
				System.out.print("|"
						+ ((Model_Produkt) mvecModel.get(i)).getMstrName() + "\t");
				System.out.print("|"
						+ ((Model_Produkt) mvecModel.get(i)).getMintGewicht()
						+ "\t\t");
				System.out.println("|"
						+ ((Model_Produkt) mvecModel.get(i)).getMfltPreis() + "\t|");
				System.out
						.println("-------------------------------------------------\n");
			}

			// Konto ausgabe -> ohne Kontonummer -> kein konto
			else if (auswahl == Controll_Statments.konto.toString()
					&& ((Model_Konto) mvecModel.get(i)).getMstrKnr() != "") {

				System.out
						.println("-------------------------------------------------------------------------");
				System.out
						.println("|name\t\t\t|kontonummmer\t|bankleitzahl\t|betrag\t|min\t|");
				System.out
						.println("-------------------------------------------------------------------------");

				System.out.print("|"
						+ ((Model_Produkt) mvecModel.get(i)).getMstrName() + "\t\t");
				System.out.print("|" + ((Model_Konto) mvecModel.get(i)).getMstrKnr()
						+ "\t\t");
				System.out.print("|" + ((Model_Konto) mvecModel.get(i)).getMstrBLZ()
						+ "\t");
				System.out.print("|"
						+ ((Model_Konto) mvecModel.get(i)).getMstrBetrag() + "\t");
				System.out.println("|"
						+ ((Model_Konto) mvecModel.get(i)).getMintMin() + "\t|");
				System.out
						.println("-------------------------------------------------------------------------\n");
			}

			// Märkte ausgabe -> ohne entfernung -> kein Markt
			else if (auswahl == Controll_Statments.markt.toString()
					&& ((Model_Markt) mvecModel.get(i)).getMintEntfernung() != 0) {
				System.out
						.println("-------------------------------------------------------------------------");
				System.out
						.println("|name\t\t\t|adresse\t|postLeitzahl\t|entfernung\t|");
				System.out
						.println("-------------------------------------------------------------------------");
				System.out.print("|" + ((Model_Markt) mvecModel.get(i)).getMstrName()
						+ "\t\t");
				System.out.print("|" + ((Model_Markt) mvecModel.get(i)).getMstrAdr()
						+ "\t");
				System.out.print("|" + ((Model_Markt) mvecModel.get(i)).getMstrPLZ()
						+ "\t\t");
				System.out.println("|"
						+ ((Model_Markt) mvecModel.get(i)).getMintEntfernung()
						+ "\t\t|");
				System.out
						.println("-------------------------------------------------------------------------\n");
			}

			// merge
			else if (auswahl == Controll_Statments.all.toString()) {
				System.out
						.println("---------------------------------------------------------");
				System.out.println("|anzahl\t\t\t|Datum\t\t\t\t|");
				System.out
						.println("---------------------------------------------------------");

				System.out.print("|" + mvecModel.get(i).getMintAnzahl()
						+ "\t\t\t");
				System.out.println("|" + mvecModel.get(i).getMstrDatum()
						+ "\t\t\t|");
				System.out
						.println("---------------------------------------------------------\n");
			}
		}

	}

	private void eingabe() {
		String auswahl;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("(P)rodukte hinzufügen: ");
		System.out.println("(K)onten hinzufügen: ");
		System.out.println("(M)ärkte hinzufügen: ");
		System.out.println("(A)lles hinzufügen: ");

		auswahl = scanner.next();

		switch (auswahl.toUpperCase()) {
		case "P":
			produktEinlesen();
			break;
		case "K":
			kotoEinlesen();
			break;
		case "M":
			marktEinlesen();
			break;
		case "A":
			allEinlesen();
			break;
		}
	}

	private void allEinlesen() {

		// abfrage der werte von Markt, Produkt und Konto
		marktEinlesen();
		produktEinlesen();
		kotoEinlesen();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("anzahl eingeben: ");
		int anzahl = scanner.nextInt();

		// holen der IDs der letzen drei werte
		// konto element
		int kontoID = ((Model_Markt) mvecModel.get(mvecModel.size())).getMintID();

		// produkt element
		int produktID = ((Model_Markt) mvecModel.get(mvecModel.size() - 1))
				.getMintID();

		// markt element
		int marktID = ((Model_Markt) mvecModel.get(mvecModel.size() - 2)).getMintID();

		// valid auf false für sync
		Model_Main me = new Model_Main(anzahl, "", false);
		me.ModelArray(kontoID, produktID, marktID);
		mvecModel.add(me);
	}

	private void marktEinlesen() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Model_Markt ma = new Model_Markt();

		System.out.println("Marktname eingeben: ");
		ma.setMstrName(scanner.next());

		System.out.println("MarktPlz eingeben: ");
		ma.setMstrPLZ(scanner.next());

		System.out.println("MarktAdr eingeben: ");
		ma.setMstrAdr(scanner.next());

		System.out.println("Marktentfernung eingeben: ");
		ma.setMintEntfernung(scanner.nextInt());

		// damit später gesynct werden kann
		ma.setMboolValid(false);

		// hinzufügen zu Klassen vektor
		this.mvecModel.add(ma);
	}

	private void produktEinlesen() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Model_Produkt pe = new Model_Produkt();

		System.out.println("Produktname eingeben: ");
		pe.setMstrName(scanner.next());

		System.out.println("Produktgewicht eingeben: ");
		pe.setMintGewicht(scanner.nextInt());

		System.out.println("Produktpreis eingeben: ");
		pe.setMfltPreis(scanner.nextFloat());

		// damit später gesynct werden kann
		pe.setMboolValid(false);

		// hinzufügen zu Klassen vektor
		this.mvecModel.add(pe);
	}

	private void kotoEinlesen() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Model_Konto ke = new Model_Konto();

		System.out.println("Kontoname eingeben: ");
		ke.setMstrName(scanner.next());

		System.out.println("Kontobetrag eingeben: ");
		ke.setMstrBetrag(scanner.next());

		System.out.println("Kontoblz eingeben: ");
		ke.setMstrBLZ(scanner.next());

		System.out.println("Kontonummer eingeben: ");
		ke.setMstrKnr(scanner.next());

		System.out.println("Kontominimum eingeben: ");
		ke.setMintMin(scanner.nextInt());

		// damit später gesynct werden kann
		ke.setMboolValid(false);

		// hinzufügen zu Klassen vektor
		this.mvecModel.add(ke);
	}

	public String sort()
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("(P)reissortierung");
		System.out.println("(A)usgaben");
		System.out.println("(D)atumsortierung");
		System.out.println("(E)ntfernungsortierung");
		
		String auswahl = scanner.next();
		String statment = null;
		
		switch (auswahl.toUpperCase().toCharArray()[0]) {
		case 'P':
			statment = Controll_Statments.PreisSortierung.toString();
			break;
		case 'D':
			statment = Controll_Statments.DatumSortierung.toString();
			break;
		case 'E':
			statment = Controll_Statments.EntfernungsSortierung.toString();
				break;
		case 'A':
			statment = Controll_Statments.AusgabenSortierung.toString();
			break;
		}
		
		return statment;
		
	}

}
