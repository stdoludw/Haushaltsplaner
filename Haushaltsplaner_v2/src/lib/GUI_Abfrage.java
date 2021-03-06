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
import javax.swing.JComboBox;


@SuppressWarnings("serial")
public class GUI_Abfrage extends JFrame {

	private JPanel contentPane;
	private JPasswordField mtxtMeta_passwort;
	private TextField mtxtMeta_Username;
	private TextField mtxtMeta_DatenabnkServer;
	private TextField mtxtMeta_DatenabnkName;
	private JButton mbntErstellen;
	private JButton mbtnLogin;
	private JComboBox<String> mchgXMLDB;
	
	private static GUI_Abfrage frame = null;


	
	public void clear()
	{
		mtxtMeta_passwort.setText("");
		mtxtMeta_Username.setText("");
		mtxtMeta_DatenabnkServer.setText("");
		mtxtMeta_DatenabnkName.setText("");
	}
	public JPasswordField getMtxtMeta_passwort() {
		return mtxtMeta_passwort;
	}

	public TextField getMtxtMeta_Username() {
		return mtxtMeta_Username;
	}

	public TextField getMtxtMeta_DatenabnkServer() {
		return mtxtMeta_DatenabnkServer;
	}

	public TextField getMtxtMeta_DatenabnkName() {
		return mtxtMeta_DatenabnkName;
	}

	public JButton getMbntErstellen() {
		return mbntErstellen;
	}

	public JButton getMbtnLogin() {
		return mbtnLogin;
	}

	
	public static GUI_Abfrage init()
	{
	
			try {
				if (frame == null) {
					frame = new GUI_Abfrage();
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
	
	public void show(GUI_Abfrage tmp)
	{
		tmp.setVisible(true);
	}

	private GUI_Abfrage() {
		setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		setBounds(100, 100, 450, 297);
		
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ComboBox.buttonHighlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 213, 417, 14);
		contentPane.add(separator);
		
		mtxtMeta_passwort = new JPasswordField();
		mtxtMeta_passwort.setBounds(184, 146, 219, 19);
		contentPane.add(mtxtMeta_passwort);
		
		mtxtMeta_Username = new TextField();
		mtxtMeta_Username.setBounds(184, 109, 219, 19);
		mtxtMeta_Username.setText("bro");
		contentPane.add(mtxtMeta_Username);
		
		Label label = new Label("Datenbankname:");
		label.setBounds(37, 42, 116, 21);
		contentPane.add(label);
		
		mtxtMeta_DatenabnkServer = new TextField();
		mtxtMeta_DatenabnkServer.setText("dfch-ludwig.de");
		mtxtMeta_DatenabnkServer.setBounds(184, 75, 219, 19);
		contentPane.add(mtxtMeta_DatenabnkServer);
		
		mtxtMeta_DatenabnkName = new TextField();
		mtxtMeta_DatenabnkName.setText("HausHaltsPlaner_dlu");
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
		mbntErstellen.setBounds(37, 229, 158, 25);
		contentPane.add(mbntErstellen);
		mbntErstellen.setActionCommand(cmbAuswahl.ErstellenAbfrageAktion.toString());
		
		mbtnLogin = new JButton("login");
		mbtnLogin.setBounds(231, 229, 172, 25);
		contentPane.add(mbtnLogin);
		mbtnLogin.setActionCommand(cmbAuswahl.LoginAbfrageAktion.toString());
		
		mchgXMLDB= new JComboBox<String>();
		mchgXMLDB.addItem("synchronisieren mit DB");
		mchgXMLDB.addItem("Arbeiten mit DB");
		mchgXMLDB.addItem("Arbeiten mit XML");


		mchgXMLDB.setBounds(184, 177, 219, 24);
		contentPane.add(mchgXMLDB);
		
		Label label_4 = new Label("Arbeitsumgebung:");
		label_4.setBounds(37, 177, 116, 21);
		contentPane.add(label_4);
	}
	public JComboBox<String> getMchgXMLDB() {
		return mchgXMLDB;
	}
}
