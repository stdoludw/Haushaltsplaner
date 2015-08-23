package lib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class GUI_Main extends JFrame {

	private JComboBox<String> comboBox;
	private JPanel contentPane;
	private JMenu Speichern;
	private JMenuItem mmenExportiern;
	private JMenuItem mmenLaden;
	private JMenu mnExtras;
	private JMenuItem mntmHinzufgen;
	private JComboBox<String> mcmbMonat;
	private JTable table;
	private JButton btnUpdate;

	private static GUI_Main frame = null;

	
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
		table.removeAll();
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
		mntmHinzufgen.setActionCommand(cmbAuswahl.HinzufuegenMainAktion.toString());

		Speichern = new JMenu("Speichern");
		menuBar.add(Speichern);

		mmenExportiern = new JMenuItem("Exportieren ...");
		Speichern.add(mmenExportiern);
		mmenExportiern.setActionCommand(cmbAuswahl.ExportMainAktion.toString());

		mmenLaden = new JMenuItem("Laden ...");
		Speichern.add(mmenLaden);
		mmenLaden.setActionCommand(cmbAuswahl.LadenMainAktion.toString());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAnzeigen = new JLabel("Anzeigen");
		lblAnzeigen.setBounds(175, 12, 70, 15);
		contentPane.add(lblAnzeigen);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(104, 51, 188, 24);
		comboBox.setActionCommand(cmbAuswahl.AuswahlMainAktion.toString());
		comboBox.addItem(cmbAuswahl.Produkt.toString());
		comboBox.addItem(cmbAuswahl.Konto.toString());
		comboBox.addItem(cmbAuswahl.Markt.toString());
		comboBox.addItem(cmbAuswahl.Einkauf.toString());
		comboBox.addItem(cmbAuswahl.Statistik.toString());
		contentPane.add(comboBox);
		
		mcmbMonat = new JComboBox<String>();
		mcmbMonat.setEnabled(false);
		mcmbMonat.setBounds(22, 51, 70, 24);
		contentPane.add(mcmbMonat);
		mcmbMonat.setActionCommand(cmbAuswahl.AuswahlMainAktion.toString());
		
		table = new JTable();
		table.setBounds(22, 87, 402, 283);
		contentPane.add(table);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(307, 51, 117, 25);
		btnUpdate.setActionCommand(cmbAuswahl.UpdateMainAktion.toString());
		contentPane.add(btnUpdate);
		mcmbMonat.addItem(cmbAuswahl.Q1.toString());
		mcmbMonat.addItem(cmbAuswahl.Q2.toString());
		mcmbMonat.addItem(cmbAuswahl.Q3.toString());

	
	}
	public void setTableModel(DefaultTableModel mdoel)
	{
		table.setModel(mdoel);
	}


	public JMenuItem getMmenExportiern() {
		return mmenExportiern;
	}

	public JMenuItem getMmenLaden() {
		return mmenLaden;
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
	public JButton getBtnUpdate() {
		return btnUpdate;
	}
}
