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
	private JMenuItem mmenHinzufuegen_Produkt;
	private JMenuItem mmenHinzufuegen_Konto;
	private JMenuItem mmenHinzufuegen_Markt;
	private JMenuItem mmenHinzufuegen_All;
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
	
	public void run() {

		GUI_Main frame = new GUI_Main();
		frame.setVisible(true);

	}

	public GUI_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 438);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Hinzufügen");
		menuBar.add(mnNewMenu);
		
				mmenHinzufuegen_Produkt = new JMenuItem("Produkt");
				mnNewMenu.add(mmenHinzufuegen_Produkt);
		
				mmenHinzufuegen_Konto = new JMenuItem("Konto");
				mnNewMenu.add(mmenHinzufuegen_Konto);
		
				mmenHinzufuegen_Markt = new JMenuItem("Markt");
				mnNewMenu.add(mmenHinzufuegen_Markt);
		
				 mmenHinzufuegen_All = new JMenuItem("All");
				 mnNewMenu.add(mmenHinzufuegen_All);
		
		Speichern = new JMenu("Speichern");
		menuBar.add(Speichern);
		
		mmenExportiern = new JMenuItem("Exportieren ...");
		Speichern.add(mmenExportiern);
		
		mmenLaden = new JMenuItem("Laden ...");
		Speichern.add(mmenLaden);
		
		mnExtras = new JMenu("Extras");
		menuBar.add(mnExtras);
		
				 mmenStatistik = new JMenuItem("Statistik");
				 mnExtras.add(mmenStatistik);
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
}
