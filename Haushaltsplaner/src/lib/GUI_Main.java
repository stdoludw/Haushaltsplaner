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


@SuppressWarnings("serial")
public class GUI_Main extends JFrame {

	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JPanel contentPane;
	private JMenuItem mmenStatistik;
	private JTextArea textArea;
	private JMenu Speichern;
	private JMenuItem mmenExportiern;
	private JMenuItem mmenLaden;
	private JMenu mnExtras;
	private JMenu mnHinzufgen;
	private JMenuItem mntmAll;
	
	private static GUI_Main frame = null;
	public static final String HINZUFUEGEN = "Hinzufuegen";
	public static final String EXPORT = "Exportieren";
	public static final String LADEN = "Laden";
	public static final String STATISTIK = "Statistik";
	public static final String AUSWAHL = "Auswahl";
	
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private GUI_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 438);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnHinzufgen = new JMenu("Hinzufügen");
		menuBar.add(mnHinzufgen);

		mntmAll = new JMenuItem("All..");
		mnHinzufgen.add(mntmAll);
		mntmAll.setActionCommand(HINZUFUEGEN);

		mnExtras = new JMenu("Extras");
		menuBar.add(mnExtras);

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
		
		comboBox = new JComboBox();
		comboBox.setBounds(136, 50, 145, 24);
		comboBox.setActionCommand(AUSWAHL);
		comboBox.addItem(cmbAuswahl.Produkt.toString());
		comboBox.addItem(cmbAuswahl.Konto.toString());
		comboBox.addItem(cmbAuswahl.Markt.toString());
		comboBox.addItem(cmbAuswahl.Einkauf.toString());

		contentPane.add(comboBox);
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

	public JMenuItem getMntmAll() {
		return mntmAll;
	}

	public void setTextArea(String textAreaContent) {
		textArea.setText(textAreaContent);
	}
	
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comboBox;
	}

}
