package lib;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextArea;

import javax.swing.JList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

public class mainWindow extends JFrame {
	
	private JCheckBoxMenuItem mckProduct;
	private JCheckBoxMenuItem mckMarked;
	private JCheckBoxMenuItem mckKonto;
	private JCheckBoxMenuItem mckAll; 
	private JCheckBoxMenuItem mckStatistic;
	
	
	public mainWindow() {
		getContentPane().setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(22, 80, 714, 211);
		getContentPane().add(textArea);
		
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

	private JPanel contentPane;

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
