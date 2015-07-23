import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JFormattedTextField;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;


public class initAbfrage {

	private JFrame frame;
	private JTextField mTxtHost;
	private JTextField mTxtDatabase;
	private JTextField mTxtUsername;
	private JTextField mTxtPassword;
	private JButton mBttAcces ;
	
			public void run() {
			
					initAbfrage window = new initAbfrage();
					window.frame.setVisible(true);
					
				
			}
		

	public initAbfrage() {
		initialize();
	}


	private void initialize() {
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 300);
		
		JLabel label = new JLabel("Database");
		label.setBounds(43, 79, 69, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(43, 141, 70, 15);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Host");
		label_2.setBounds(53, 52, 33, 15);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Username");
		label_3.setBounds(43, 114, 72, 15);
		frame.getContentPane().add(label_3);
		
		mTxtHost = new JTextField();
		mTxtHost.setColumns(10);
		mTxtHost.setBounds(146, 50, 226, 19);
		frame.getContentPane().add(mTxtHost);
		
		mTxtDatabase = new JTextField();
		mTxtDatabase.setColumns(10);
		mTxtDatabase.setBounds(146, 87, 226, 19);
		frame.getContentPane().add(mTxtDatabase);
		
		mTxtUsername = new JTextField();
		mTxtUsername.setColumns(10);
		mTxtUsername.setBounds(146, 112, 226, 19);
		frame.getContentPane().add(mTxtUsername);
		
		mTxtPassword = new JTextField();
		mTxtPassword.setColumns(10);
		mTxtPassword.setBounds(146, 144, 226, 19);
		frame.getContentPane().add(mTxtPassword);
		
		 mBttAcces = new JButton("Access");
		mBttAcces.setBounds(307, 228, 117, 25);
		frame.getContentPane().add(mBttAcces);
	}
}
