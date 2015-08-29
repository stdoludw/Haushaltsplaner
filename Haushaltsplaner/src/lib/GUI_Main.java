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
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class GUI_Main extends JFrame {

	private JComboBox<String> comboBox;
	private JPanel mjpPane;
	private JMenu menSpeichern;
	private JMenuItem mmenExportiern;
	private JMenuItem mmenLaden;
	private JMenu mnExtras;
	private JMenuItem mntmHinzufgen;
	private JTable mtblTable;
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
		mtblTable.removeAll();
	}
	
	
	private GUI_Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 438);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnExtras = new JMenu("Extras");
		menuBar.add(mnExtras);
		
		mntmHinzufgen = new JMenuItem("Hinzufuegen");
		mnExtras.add(mntmHinzufgen);
		mntmHinzufgen.setActionCommand(cmbAuswahl.HinzufuegenMainAktion.toString());

		menSpeichern = new JMenu("Speichern");
		menuBar.add(menSpeichern);

		mmenExportiern = new JMenuItem("Exportieren ...");
		menSpeichern.add(mmenExportiern);
		mmenExportiern.setActionCommand(cmbAuswahl.ExportMainAktion.toString());

		mmenLaden = new JMenuItem("Laden ...");
		menSpeichern.add(mmenLaden);
		mmenLaden.setActionCommand(cmbAuswahl.LadenMainAktion.toString());

		mjpPane = new JPanel();
		mjpPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mjpPane);
		mjpPane.setLayout(null);

		JLabel lblAnzeigen = new JLabel("Anzeigen");
		lblAnzeigen.setBounds(369, 12, 70, 15);
		mjpPane.add(lblAnzeigen);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(22, 51, 596, 24);
		comboBox.setActionCommand(cmbAuswahl.AuswahlMainAktion.toString());
		comboBox.addItem(cmbAuswahl.Produkt.toString());
		comboBox.addItem(cmbAuswahl.Konto.toString());
		comboBox.addItem(cmbAuswahl.Markt.toString());
		comboBox.addItem(cmbAuswahl.Einkauf.toString());
		comboBox.addItem(cmbAuswahl.Statistik.toString());
		mjpPane.add(comboBox);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(630, 51, 117, 25);
		btnUpdate.setActionCommand(cmbAuswahl.UpdateMainAktion.toString());
		mjpPane.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane(mtblTable);
		scrollPane.setBounds(22, 95, 725, 275);
		mjpPane.add(scrollPane);
		
		mtblTable = new JTable();
		scrollPane.setViewportView(mtblTable);

	
	}
	public void setTableModel(DefaultTableModel mdoel)
	{
		mtblTable.setModel(mdoel);
	}


	public JMenuItem getMmenExportiern() {
		return mmenExportiern;
	}

	public JMenuItem getMmenLaden() {
		return mmenLaden;
	}
	
	public JTable getMtblTable() {
		return mtblTable;
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
