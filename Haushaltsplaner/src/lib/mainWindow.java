package lib;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JTable;

public class mainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBoxMenuItem mckMarked;
	private JCheckBoxMenuItem mckKonto;
	private JCheckBoxMenuItem mckAll;
	private JCheckBoxMenuItem mckStatistic;
	private JCheckBoxMenuItem mckProduct;
	private JPanel contentPane;
	private JTable table;
	
	public JCheckBoxMenuItem getMckProduct() {
		return mckProduct;
	}

	public JCheckBoxMenuItem getMckMarked() {
		return mckMarked;
	}

	public JCheckBoxMenuItem getMckKonto() {
		return mckKonto;
	}

	public JCheckBoxMenuItem getMckAll() {
		return mckAll;
	}

	public JCheckBoxMenuItem getMckStatistic() {
		return mckStatistic;
	}

	public mainWindow() {
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(26, 276, 664, -222);
		getContentPane().add(table);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 765, 21);
		getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("optionen");
		menuBar.add(mnNewMenu);

		mckProduct = new JCheckBoxMenuItem("sort by product");
		mnNewMenu.add(mckProduct);

		mckMarked = new JCheckBoxMenuItem("sort by marked");
		mnNewMenu.add(mckMarked);

		mckKonto = new JCheckBoxMenuItem("sort by konto");
		mnNewMenu.add(mckKonto);

		mckAll = new JCheckBoxMenuItem("sort by all");
		mnNewMenu.add(mckAll);

		mckStatistic = new JCheckBoxMenuItem("statistic");
		mnNewMenu.add(mckStatistic);
	}

	public void setmTxtArea(String p) {
		
		
	    String[] headers = {"Fahrzeug", "Geschw. (km/h)", "Preis (T\u20ac)"};
	    
	    Object[][] data = {{"Fahrrad", new Integer(30), new Double(0.6)},
	                       {"RegioExpress", new Integer(110), new Double(3500)},
	                       {"ICE", new Integer(320), new Double(7500)},
	                       {"EuroStar", new Integer(380), new Double(4500)},
	                       {"A320", new Integer(950), new Double(44000)}};
	    
	    table = new JTable(data,headers);
		table.setBounds(26, 276, 664, -222);
		getContentPane().add(table);
		
		
	        
	}

	public void run() {

		mainWindow frame = new mainWindow();
		frame.setVisible(true);
		frame.setSize(770, 400);
	}

	void mainWindow() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
