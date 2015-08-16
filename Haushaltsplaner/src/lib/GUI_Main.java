package lib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class GUI_Main extends JFrame {

	private JComboBox<String> comboBox;
	private JPanel contentPane;
	private JMenuItem mmenStatistik;
	private JTextArea textArea;
	private JMenu Speichern;
	private JMenuItem mmenExportiern;
	private JMenuItem mmenLaden;
	private JMenu mnExtras;
	private JMenuItem mntmHinzufgen;
	private JButton mbtnupdate;
	private JComboBox<String> mcmbMonat;
	

	private static GUI_Main frame = null;
	public static final String HINZUFUEGEN = "Hinzufuegen";
	public static final String EXPORT = "Exportieren";
	public static final String LADEN = "Laden";
	public static final String STATISTIK = "Statistik";
	public static final String AUSWAHL = "Auswahl";
	public static final String UPDATE = "update";

	public static GUI_Main init() {

				try {
					if (frame == null) {
						frame = new GUI_Main();
					}
					else
					{
						throw new Exception("Schon eine Instance vorhanden");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			
		return frame;
	}
	
	public void show(GUI_Main tmp)
	{
		tmp.setVisible(true);
	}
	
	public void clear()
	{
		textArea.removeAll();
	}
	
	
	private GUI_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 438);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnExtras = new JMenu("Extras");
		menuBar.add(mnExtras);
		
		mntmHinzufgen = new JMenuItem("Hinzufuegen");
		mnExtras.add(mntmHinzufgen);
		mntmHinzufgen.setActionCommand(HINZUFUEGEN);

		mmenStatistik = new JMenuItem("Statistik");
		mnExtras.add(mmenStatistik);
		mmenStatistik.setActionCommand(STATISTIK);

		Speichern = new JMenu("Speichern");
		menuBar.add(Speichern);

		mmenExportiern = new JMenuItem("Exportieren ...");
		Speichern.add(mmenExportiern);
		mmenExportiern.setActionCommand(EXPORT);

		mmenLaden = new JMenuItem("Laden ...");
		Speichern.add(mmenLaden);
		mmenLaden.setActionCommand(LADEN);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 86, 412, 284);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel lblAnzeigen = new JLabel("Anzeigen");
		lblAnzeigen.setBounds(175, 12, 70, 15);
		contentPane.add(lblAnzeigen);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(152, 51, 145, 24);
		comboBox.setActionCommand(AUSWAHL);
		comboBox.addItem(cmbAuswahl.Produkt.toString());
		comboBox.addItem(cmbAuswahl.Konto.toString());
		comboBox.addItem(cmbAuswahl.Markt.toString());
		comboBox.addItem(cmbAuswahl.Einkauf.toString());
		comboBox.addItem(cmbAuswahl.Statistik.toString());

		contentPane.add(comboBox);
		
		mbtnupdate = new JButton("update");
		mbtnupdate.setBounds(307, 50, 117, 25);
		contentPane.add(mbtnupdate);
		mbtnupdate.setActionCommand(UPDATE);
		
		mcmbMonat = new JComboBox<String>();
		mcmbMonat.setEnabled(false);
		mcmbMonat.setBounds(12, 51, 130, 24);
		contentPane.add(mcmbMonat);
		mcmbMonat.setActionCommand(AUSWAHL);
		mcmbMonat.addItem(cmbAuswahl.Alles.toString());
		mcmbMonat.addItem(cmbAuswahl.Januar.toString());
		mcmbMonat.addItem(cmbAuswahl.Februar.toString());
		mcmbMonat.addItem(cmbAuswahl.März.toString());
		mcmbMonat.addItem(cmbAuswahl.April.toString());
		mcmbMonat.addItem(cmbAuswahl.Mai.toString());
		mcmbMonat.addItem(cmbAuswahl.Juni.toString());
		mcmbMonat.addItem(cmbAuswahl.Juli.toString());
		mcmbMonat.addItem(cmbAuswahl.August.toString());
		mcmbMonat.addItem(cmbAuswahl.September.toString());
		mcmbMonat.addItem(cmbAuswahl.Oktober.toString());
		mcmbMonat.addItem(cmbAuswahl.November.toString());
		mcmbMonat.addItem(cmbAuswahl.Dezember.toString());
	}

	public JMenuItem getMmenStatistik() {
		return mmenStatistik;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JMenuItem getMmenExportiern() {
		return mmenExportiern;
	}

	public JMenuItem getMmenLaden() {
		return mmenLaden;
	}

	public void setTextArea(String textAreaContent) {
		textArea.setText(textAreaContent);
	}
	
	public JComboBox<String> getMcmbMonat() {
		return mcmbMonat;
	}
	
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public JMenuItem getMntmHinzufgen() {
		return mntmHinzufgen;
	}
	
	public JButton getMbtnupdate() {
		return mbtnupdate;
	}
}
