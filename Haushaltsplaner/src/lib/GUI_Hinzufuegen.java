package lib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class GUI_Hinzufuegen extends JFrame {

	private JPanel contentPane;
	private JTextField mtxtProdukt_Name;
	private JTextField mtxtProdukt_Gewicht;
	private JTextField mtxtProdukt_Preis;
	private JTextField mtxtMarkt_Name;
	private JTextField mtxtMarkt_Adresse;
	private JTextField mtxtMarkt_Plz;
	private JTextField mtxtMarkt_Entfernung;
	private JTextField mtxtKonto_name;
	private JTextField mtxtKonto_kontonummer;
	private JTextField mtxtKonto_Blz;
	private JTextField mtxtKonto_Betrag;
	private JTextField mtxtAlles_Anzahl;
	private JTextField mtxtAlles_Datum;
	private JTextField mtxtKonto_Min;
	private JButton mbtmKonto;
	private JButton mbtmMarkt;
	private JButton mbtmProdukt;
	private JButton mbtmAlles;
	private JComboBox mcmbProdukt;
	private JComboBox mcmbMarkt;
	private JComboBox mcmbKonto;
	private static GUI_Hinzufuegen frame = null;

	public static final String HINZUFUEGENPRODUKT = "HinzufuegenProdukt";
	public static final String HINZUFUEGENKONTO = "HinzufuegenKonto";
	public static final String HINZUFUEGENMARKT = "HinzufuegenMarkt";
	public static final String HINZUFUEGENALL = "HinzufuegenAll";
	

	public JTextField getMtxtProdukt_Name() {
		return mtxtProdukt_Name;
	}

	public JTextField getMtxtProdukt_Gewicht() {
		return mtxtProdukt_Gewicht;
	}

	public JTextField getMtxtProdukt_Preis() {
		return mtxtProdukt_Preis;
	}

	public JTextField getMtxtMarkt_Name() {
		return mtxtMarkt_Name;
	}

	public JTextField getMtxtMarkt_Adresse() {
		return mtxtMarkt_Adresse;
	}

	public JTextField getMtxtMarkt_Plz() {
		return mtxtMarkt_Plz;
	}

	public JTextField getMtxtMarkt_Entfernung() {
		return mtxtMarkt_Entfernung;
	}

	public JTextField getMtxtKonto_name() {
		return mtxtKonto_name;
	}

	public JTextField getMtxtKonto_kontonummer() {
		return mtxtKonto_kontonummer;
	}

	public JTextField getMtxtKonto_Blz() {
		return mtxtKonto_Blz;
	}

	public JTextField getMtxtKonto_Betrag() {
		return mtxtKonto_Betrag;
	}

	public JTextField getMtxtAlles_Anzahl() {
		return mtxtAlles_Anzahl;
	}

	public JTextField getMtxtAlles_Datum() {
		return mtxtAlles_Datum;
	}

	public JTextField getMtxtKonto_Min() {
		return mtxtKonto_Min;
	}

	public JButton getMbtmKonto() {
		return mbtmKonto;
	}

	public JButton getMbtmMarkt() {
		return mbtmMarkt;
	}

	public JButton getMbtmProdukt() {
		return mbtmProdukt;
	}

	public JButton getMbtmAlles() {
		return mbtmAlles;
	}

	public JComboBox getMcmbProdukt() {
		return mcmbProdukt;
	}

	public JComboBox getMcmbMarkt() {
		return mcmbMarkt;
	}

	public JComboBox getMcmbKonto() {
		return mcmbKonto;
	}

	public static GUI_Hinzufuegen init()
	{
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				if (frame == null) {
					frame = new GUI_Hinzufuegen();
				}
				else
				{
					throw new Exception("Schon eine Instance vorhanden");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	return frame;
	}

	private GUI_Hinzufuegen() {
		setBounds(100, 100, 457, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblHinzufgen = new JLabel("Hinzufügen");
		lblHinzufgen.setBounds(164, 17, 101, 15);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 44, 417, 294);
		contentPane.setLayout(null);
		contentPane.add(lblHinzufgen);
		contentPane.add(tabbedPane);

		JPanel mpnlKonto = new JPanel();
		tabbedPane.addTab("Konto", null, mpnlKonto, null);
		mpnlKonto.setLayout(null);

		mtxtKonto_name = new JTextField();
		mtxtKonto_name.setColumns(10);
		mtxtKonto_name.setBounds(121, 29, 238, 19);
		mpnlKonto.add(mtxtKonto_name);

		mtxtKonto_kontonummer = new JTextField();
		mtxtKonto_kontonummer.setColumns(10);
		mtxtKonto_kontonummer.setBounds(121, 60, 238, 19);
		mpnlKonto.add(mtxtKonto_kontonummer);

		mtxtKonto_Blz = new JTextField();
		mtxtKonto_Blz.setColumns(10);
		mtxtKonto_Blz.setBounds(121, 91, 238, 19);
		mpnlKonto.add(mtxtKonto_Blz);

		mtxtKonto_Betrag = new JTextField();
		mtxtKonto_Betrag.setColumns(10);
		mtxtKonto_Betrag.setBounds(121, 122, 238, 19);
		mpnlKonto.add(mtxtKonto_Betrag);

		mbtmKonto = new JButton("Hinzufügen");
		mbtmKonto.setBounds(242, 194, 117, 25);
		mpnlKonto.add(mbtmKonto);
		mbtmKonto.setActionCommand(HINZUFUEGENKONTO);

		Label label = new Label("Name");
		label.setBounds(10, 29, 68, 21);
		mpnlKonto.add(label);

		Label label_1 = new Label("KontoNummer");
		label_1.setBounds(10, 60, 96, 21);
		mpnlKonto.add(label_1);

		Label label_2 = new Label("BankLeitZahl");
		label_2.setBounds(10, 91, 84, 21);
		mpnlKonto.add(label_2);

		Label label_3 = new Label("Betrag");
		label_3.setBounds(10, 120, 68, 21);
		mpnlKonto.add(label_3);

		mtxtKonto_Min = new JTextField();
		mtxtKonto_Min.setColumns(10);
		mtxtKonto_Min.setBounds(121, 154, 238, 19);
		mpnlKonto.add(mtxtKonto_Min);

		Label label_4 = new Label("Min");
		label_4.setBounds(10, 152, 68, 21);
		mpnlKonto.add(label_4);

		JPanel mpnlMarkt = new JPanel();
		tabbedPane.addTab("Markt", null, mpnlMarkt, null);
		mpnlMarkt.setLayout(null);

		mtxtMarkt_Name = new JTextField();
		mtxtMarkt_Name.setColumns(10);
		mtxtMarkt_Name.setBounds(129, 29, 238, 19);
		mpnlMarkt.add(mtxtMarkt_Name);

		mtxtMarkt_Adresse = new JTextField();
		mtxtMarkt_Adresse.setColumns(10);
		mtxtMarkt_Adresse.setBounds(129, 60, 238, 19);
		mpnlMarkt.add(mtxtMarkt_Adresse);

		mtxtMarkt_Plz = new JTextField();
		mtxtMarkt_Plz.setColumns(10);
		mtxtMarkt_Plz.setBounds(129, 91, 238, 19);
		mpnlMarkt.add(mtxtMarkt_Plz);

		mtxtMarkt_Entfernung = new JTextField();
		mtxtMarkt_Entfernung.setColumns(10);
		mtxtMarkt_Entfernung.setBounds(129, 122, 238, 19);
		mpnlMarkt.add(mtxtMarkt_Entfernung);

		Label label_5 = new Label("Name");
		label_5.setBounds(27, 27, 68, 21);
		mpnlMarkt.add(label_5);

		Label label_6 = new Label("Adresse");
		label_6.setBounds(27, 58, 96, 21);
		mpnlMarkt.add(label_6);

		Label label_7 = new Label("PostLeitZahl");
		label_7.setBounds(27, 86, 84, 21);
		mpnlMarkt.add(label_7);

		Label label_8 = new Label("Entfernung");
		label_8.setBounds(27, 113, 84, 21);
		mpnlMarkt.add(label_8);

		mbtmMarkt = new JButton("Hinzufügen");
		mbtmMarkt.setBounds(267, 197, 117, 25);
		mpnlMarkt.add(mbtmMarkt);
		mbtmMarkt.setActionCommand(HINZUFUEGENMARKT);

		JPanel mpnlProdukt = new JPanel();
		tabbedPane.addTab("Produkt", null, mpnlProdukt, null);
		mpnlProdukt.setLayout(null);

		mtxtProdukt_Name = new JTextField();
		mtxtProdukt_Name.setBounds(114, 34, 238, 19);
		mpnlProdukt.add(mtxtProdukt_Name);
		mtxtProdukt_Name.setColumns(10);

		mtxtProdukt_Gewicht = new JTextField();
		mtxtProdukt_Gewicht.setColumns(10);
		mtxtProdukt_Gewicht.setBounds(114, 65, 238, 19);
		mpnlProdukt.add(mtxtProdukt_Gewicht);

		mtxtProdukt_Preis = new JTextField();
		mtxtProdukt_Preis.setColumns(10);
		mtxtProdukt_Preis.setBounds(114, 96, 238, 19);
		mpnlProdukt.add(mtxtProdukt_Preis);

		Label label_9 = new Label("Name");
		label_9.setBounds(24, 34, 68, 21);
		mpnlProdukt.add(label_9);

		Label label_10 = new Label("Gewicht");
		label_10.setBounds(22, 63, 74, 21);
		mpnlProdukt.add(label_10);

		Label label_11 = new Label("Preis");
		label_11.setBounds(24, 96, 84, 21);
		mpnlProdukt.add(label_11);

		mbtmProdukt = new JButton("Hinzufügen");
		mbtmProdukt.setBounds(267, 214, 117, 25);
		mpnlProdukt.add(mbtmProdukt);
		mbtmProdukt.setActionCommand(HINZUFUEGENPRODUKT);

		JPanel mpnlAlles = new JPanel();
		tabbedPane.addTab("Alles", null, mpnlAlles, null);
		mpnlAlles.setLayout(null);

		mtxtAlles_Anzahl = new JTextField();
		mtxtAlles_Anzahl.setColumns(10);
		mtxtAlles_Anzahl.setBounds(127, 40, 238, 19);
		mpnlAlles.add(mtxtAlles_Anzahl);

		mtxtAlles_Datum = new JTextField();
		mtxtAlles_Datum.setColumns(10);
		mtxtAlles_Datum.setBounds(127, 71, 238, 19);
		mpnlAlles.add(mtxtAlles_Datum);

		mbtmAlles = new JButton("Hinzufügen");
		mbtmAlles.setBounds(267, 214, 117, 25);
		mpnlAlles.add(mbtmAlles);
		mbtmAlles.setActionCommand(HINZUFUEGENALL);

		Label label_12 = new Label("Anzahl");
		label_12.setBounds(27, 40, 68, 21);
		mpnlAlles.add(label_12);

		Label label_13 = new Label("Datum");
		label_13.setBounds(27, 69, 74, 21);
		mpnlAlles.add(label_13);
		
		mcmbProdukt = new JComboBox();
		mcmbProdukt.setBounds(127, 107, 238, 19);
		mpnlAlles.add(mcmbProdukt);
		
		mcmbMarkt = new JComboBox();
		mcmbMarkt.setBounds(127, 142, 238, 19);
		mpnlAlles.add(mcmbMarkt);
		
		mcmbKonto = new JComboBox();
		mcmbKonto.setBounds(127, 176, 238, 19);
		mpnlAlles.add(mcmbKonto);
		
		Label label_14 = new Label("Markt");
		label_14.setBounds(27, 140, 74, 21);
		mpnlAlles.add(label_14);
		
		Label label_15 = new Label("Produkt");
		label_15.setBounds(27, 105, 68, 21);
		mpnlAlles.add(label_15);
		
		Label label_16 = new Label("Konto");
		label_16.setBounds(27, 176, 74, 21);
		mpnlAlles.add(label_16);
	}
}
