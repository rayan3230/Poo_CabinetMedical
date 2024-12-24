package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cabinet.Personnels.Secretary;
import Main_classes.MedicalOffice;
import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Login_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFullname;
	private JPasswordField txtpassword;
	private Login_Doctor_menu Login_Doctor_menu;
	private Login_Sec_menu Login_Sec_menu;
	private MedicalOffice office;
	public Accounts accountManger;
	private Main_Menu mainFrame;
	private Doctor  logInDoctor;
	private Secretary logInSecretary;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Login_Frame(Main_Menu MainFrame ,Accounts  AccountManager ,MedicalOffice office  ) {
		
		this.office = office; 
		this.accountManger = AccountManager;
		this.mainFrame = MainFrame;
		
		this.Login_Sec_menu = new Login_Sec_menu(office, mainFrame, accountManger, null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", lblNewLabel_1.getFont().getStyle() | Font.BOLD, 15));
		lblNewLabel_1.setBounds(168, 161, 90, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", lblNewLabel_1_1.getFont().getStyle() | Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(178, 244, 74, 32);
		contentPane.add(lblNewLabel_1_1);
		
		txtFullname = new JTextField();
		txtFullname.setBackground(new Color(255, 255, 255));
		txtFullname.setForeground(new Color(0, 0, 0));
		txtFullname.setBounds(168, 203, 90, 32);
		contentPane.add(txtFullname);
		txtFullname.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(168, 286, 90, 32);
		contentPane.add(txtpassword);
		
		JRadioButton rdbDoc = new JRadioButton("Doctor");
		rdbDoc.setFont(new Font("Segoe UI", Font.BOLD, 13));
		rdbDoc.setBackground(new Color(245, 245, 245));
		rdbDoc.setBounds(64, 130, 103, 21);
		contentPane.add(rdbDoc);
		
		JRadioButton rdbSec = new JRadioButton("Secretery");
		rdbSec.setHorizontalAlignment(SwingConstants.LEFT);
		rdbSec.setFont(new Font("Segoe UI", Font.BOLD, 13));
		rdbSec.setBackground(new Color(245, 245, 245));
		rdbSec.setBounds(271, 130, 103, 21);
		contentPane.add(rdbSec);
		
		// Grouping the radio buttons to ensure only one can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbSec);
        genderGroup.add(rdbDoc);

		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(25, 118, 210));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbDoc.isSelected()) {
					String FullName = txtFullname.getText();
					String Password = txtpassword.getText();
					logInDoctor = accountManger.getDoctorByCredentials(FullName, Password);
					
						
						if(logInDoctor != null) {
							JOptionPane.showMessageDialog(Login_Frame.this,"Login successful!"); 
							
							dispose();
							Login_Doctor_menu doctorMenu = new Login_Doctor_menu(office, mainFrame, accountManger, logInDoctor);
							doctorMenu.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(Login_Frame.this,"Invalid Credentials.");
						}
				} else if(rdbSec.isSelected()) {
					String FullName = txtFullname.getText();
					String Password = txtpassword.getText();
					Secretary logInSecretary = accountManger.getSecretaryByCredentials(FullName, Password);
					
					if(logInSecretary != null) {
						JOptionPane.showMessageDialog(Login_Frame.this,"Login successful!");
						dispose();
						Login_Sec_menu secMenu = new Login_Sec_menu(office, mainFrame, accountManger, logInSecretary);
						secMenu.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(Login_Frame.this,"Invalid Credentials.");
					}
				}
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLogin.setBounds(117, 355, 200, 35);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(25, 118, 210));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mainFrame.setVisible(true);
			
			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBack.setBounds(117, 400, 200, 35);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome Back");
		lblNewLabel_2.setBackground(new Color(25, 118, 210));
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(97, 10, 235, 68);
		contentPane.add(lblNewLabel_2);
	}

}
