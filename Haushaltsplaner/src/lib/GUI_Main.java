package lib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import java.awt.Label;

@SuppressWarnings("serial")
public class GUI_Main extends JFrame {

	private JPanel contentPane;
	private JSlider msldAnzahl;
	private JMenuItem mmenStatistik;
	private JTextArea textArea;
	private JSlider msldPreis;
	private JSlider msldDatum;
	private JSlider msldMarkt;
	private JComboBox<?> mcmbBox;
	private JMenu Speichern;
	private JMenuItem mmenExportiern;
	private JMenuItem mmenLaden;
	private JMenu mnExtras;
	private JMenu mnHinzufgen;
	private JMenuItem mntmAll;

	public void run() {

		this.setVisible(true);

	}


	public GUI_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 438);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnHinzufgen = new JMenu("Hinzufügen");
		menuBar.add(mnHinzufgen);
		
		mntmAll = new JMenuItem("All..");
		mnHinzufgen.add(mntmAll);
		mntmAll.setActionCommand("Hinzufuegen");

		mnExtras = new JMenu("Extras");
		menuBar.add(mnExtras);

		mmenStatistik = new JMenuItem("Statistik");
		mnExtras.add(mmenStatistik);

		Speichern = new JMenu("Speichern");
		menuBar.add(Speichern);

		mmenExportiern = new JMenuItem("Exportieren ...");
		Speichern.add(mmenExportiern);

		mmenLaden = new JMenuItem("Laden ...");
		Speichern.add(mmenLaden);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 163, 412, 207);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel lblAnzeigen = new JLabel("Anzeigen");
		lblAnzeigen.setBounds(175, 12, 70, 15);
		contentPane.add(lblAnzeigen);

		msldPreis = new JSlider();
		msldPreis.setBounds(12, 55, 200, 16);
		contentPane.add(msldPreis);

		msldDatum = new JSlider();
		msldDatum.setBounds(12, 98, 200, 16);
		contentPane.add(msldDatum);

		msldMarkt = new JSlider();
		msldMarkt.setBounds(224, 55, 200, 16);
		contentPane.add(msldMarkt);

		msldAnzahl = new JSlider();
		msldAnzahl.setBounds(224, 98, 200, 16);
		contentPane.add(msldAnzahl);

		mcmbBox = new JComboBox<Object>();
		mcmbBox.setBounds(126, 126, 174, 19);
		contentPane.add(mcmbBox);

		Label label = new Label("Preis");
		label.setBounds(12, 31, 68, 21);
		contentPane.add(label);

		Label label_1 = new Label("Datum");
		label_1.setBounds(12, 71, 68, 21);
		contentPane.add(label_1);

		Label label_2 = new Label("Markt");
		label_2.setBounds(224, 31, 68, 21);
		contentPane.add(label_2);

		Label label_3 = new Label("Anzahl");
		label_3.setBounds(224, 71, 68, 21);
		contentPane.add(label_3);
	}

	public JSlider getMsldAnzahl() {
		return msldAnzahl;
	}

	public JMenuItem getMmenStatistik() {
		return mmenStatistik;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JSlider getMsldPreis() {
		return msldPreis;
	}

	public JSlider getMsldDatum() {
		return msldDatum;
	}

	public JSlider getMsldMarkt() {
		return msldMarkt;
	}

	public JComboBox<?> getMcmbBox() {
		return mcmbBox;
	}

	public JMenuItem getMmenExportiern() {
		return mmenExportiern;
	}

	public JMenuItem getMmenLaden() {
		return mmenLaden;
	}

	public JMenuItem getMntmAll() {
		return mntmAll;
	}
}
