package lib;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.TextField;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class GUI_Abfrage extends JFrame {

	private JPanel contentPane;
	private JPasswordField mtxtMeta_passwort;
	private TextField mtxtMeta_Username;
	private TextField mtxtMeta_DatenabnkServer;
	private TextField mtxtMeta_DatenabnkName;
	private JButton mbntErstellen;
	private JButton mbtnLogin;
	
	public void run() {
		GUI_Abfrage frame = new GUI_Abfrage();
		frame.setVisible(true);
	}

	public GUI_Abfrage() {
		setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 271);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.buttonHighlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 175, 417, 14);
		contentPane.add(separator);
		
		mtxtMeta_passwort = new JPasswordField();
		mtxtMeta_passwort.setBounds(184, 144, 219, 19);
		contentPane.add(mtxtMeta_passwort);
		
		mtxtMeta_Username = new TextField();
		mtxtMeta_Username.setBounds(184, 109, 219, 19);
		contentPane.add(mtxtMeta_Username);
		
		Label label = new Label("Datenbankname:");
		label.setBounds(37, 42, 116, 21);
		contentPane.add(label);
		
		mtxtMeta_DatenabnkServer = new TextField();
		mtxtMeta_DatenabnkServer.setBounds(184, 75, 219, 19);
		contentPane.add(mtxtMeta_DatenabnkServer);
		
		mtxtMeta_DatenabnkName = new TextField();
		mtxtMeta_DatenabnkName.setBounds(184, 42, 219, 19);
		contentPane.add(mtxtMeta_DatenabnkName);
		
		Label label_1 = new Label("DatenbankServer:");
		label_1.setBounds(37, 75, 116, 21);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Username:");
		label_2.setBounds(37, 107, 116, 21);
		contentPane.add(label_2);
		
		Label label_3 = new Label("Passwort:");
		label_3.setBounds(37, 144, 116, 21);
		contentPane.add(label_3);
		
		JLabel lblHaushaltsplanerV = new JLabel("Haushaltsplaner V4");
		lblHaushaltsplanerV.setBounds(141, 12, 167, 15);
		contentPane.add(lblHaushaltsplanerV);
		
		mbntErstellen = new JButton("Neue erstellen");
		mbntErstellen.setBounds(58, 201, 158, 25);
		contentPane.add(mbntErstellen);
		
		mbtnLogin = new JButton("login");
		mbtnLogin.setBounds(231, 201, 172, 25);
		contentPane.add(mbtnLogin);
	}
}
