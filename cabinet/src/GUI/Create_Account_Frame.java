package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import Cabinet.Personnels.Accounts;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Create_Account_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFullname;
	private JTextField txtPhonenum;
	private JTextField txtEmail;
	private JTextField txtSpc;
	private JPasswordField txtPassword;
	private Main_Menu mainFrame;
	public Accounts accountManager;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Create_Account_Frame(Main_Menu mainFrame ,Accounts AccountManager) {
		
		this.accountManager = AccountManager;
		this.mainFrame = mainFrame;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 641);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Information");
		lblNewLabel.setForeground(new Color(25, 118, 210));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 409, 53);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbsecretery = new JRadioButton("Secretary");
		rdbsecretery.setFont(new Font("Segoe UI", Font.BOLD, 13));
		rdbsecretery.setBackground(new Color(245, 245, 245));
		rdbsecretery.setBounds(161, 112, 103, 21);
		contentPane.add(rdbsecretery);
		
		JRadioButton rdbdoctor = new JRadioButton("Doctor");
		rdbdoctor.setFont(new Font("Segoe UI", Font.BOLD, 13));
		rdbdoctor.setBackground(new Color(245, 245, 245));
		rdbdoctor.setBounds(56, 112, 103, 21);
		contentPane.add(rdbdoctor);
		
		// Grouping the radio buttons to ensure only one can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbsecretery);
        genderGroup.add(rdbdoctor);

		
		JLabel lblNewLabel_1 = new JLabel("Account Type:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 84, 140, 22);
		contentPane.add(lblNewLabel_1);
		
		txtFullname = new JTextField();
		txtFullname.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtFullname.setBounds(10, 185, 430, 30);
		contentPane.add(txtFullname);
		txtFullname.setColumns(10);
		
		txtPhonenum = new JTextField();
		txtPhonenum.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtPhonenum.setColumns(10);
		txtPhonenum.setBounds(10, 453, 430, 30);
		contentPane.add(txtPhonenum);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 319, 430, 30);
		contentPane.add(txtEmail);
		
		txtSpc = new JTextField();
		txtSpc.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtSpc.setColumns(10);
		txtSpc.setBounds(10, 386, 430, 30);
		contentPane.add(txtSpc);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtPassword.setBounds(10, 252, 430, 30);
		contentPane.add(txtPassword);
		
		  rdbsecretery.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Disable irrelevant fields for Secretary account creation
	                txtFullname.setEnabled(true);
	                txtPassword.setEnabled(true);
	                txtEmail.setEnabled(false);
	                txtSpc.setEnabled(false);
	                txtPhonenum.setEnabled(false);
	            }
		  });
		
		JButton btnconfirm = new JButton("Create Account");
		btnconfirm.setBackground(new Color(25, 118, 210));
		btnconfirm.setForeground(new Color(255, 255, 255));
		btnconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    
			            if (txtFullname.getText().isEmpty() || txtPassword.getText().isEmpty()
		                        || (!rdbdoctor.isSelected() && !rdbsecretery.isSelected())) {
		                    JOptionPane.showMessageDialog(Create_Account_Frame.this, "Please fill in all required fields and select an account type.");
		                    return;
		                }
				if(rdbdoctor.isSelected()) {
					String FullName = txtFullname.getText();
					String PhoneNum = txtPhonenum.getText();
					String Email = txtEmail.getText();
					String Speciality = txtSpc.getText();
					String Password = txtPassword.getText();
					Doctor doc = new Doctor(FullName, Speciality, Email, PhoneNum, Password);
					accountManager.AddDocAccount(doc); 
					JOptionPane.showMessageDialog(Create_Account_Frame.this, "The Account is add ");
					dispose();
					mainFrame.setVisible(true);
				}else if(rdbsecretery.isSelected()){
					String FullName = txtFullname.getText();
					String Password = txtPassword.getText();
					
					
					
					 Secretary sec = new Secretary(FullName, Password);
		                accountManager.AddSecAccount(sec);
		                JOptionPane.showMessageDialog(Create_Account_Frame.this, "The Account is add ");
						dispose();
						mainFrame.setVisible(true);
					
				}
			}
		
		});
		btnconfirm.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnconfirm.setBounds(120, 509, 200, 35);
		contentPane.add(btnconfirm);
		
		JLabel txtFullName = new JLabel("FullName :");
		txtFullName.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtFullName.setBounds(10, 154, 78, 21);
		contentPane.add(txtFullName);
		
		JLabel txtpassword = new JLabel("Password :");
		txtpassword.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtpassword.setBounds(10, 221, 85, 21);
		contentPane.add(txtpassword);
		
		JLabel txtemail = new JLabel("Email :");
		txtemail.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtemail.setBounds(10, 288, 60, 21);
		contentPane.add(txtemail);
		
		JLabel txtspecialiti = new JLabel("Specialization (for Doctors) :");
		txtspecialiti.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtspecialiti.setBounds(10, 355, 249, 21);
		contentPane.add(txtspecialiti);
		
		JLabel txtphonenum = new JLabel("Phone Number :");
		txtphonenum.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtphonenum.setBounds(10, 422, 140, 21);
		contentPane.add(txtphonenum);
		
		JButton btnreturn = new JButton("Back");
		btnreturn.setForeground(new Color(255, 255, 255));
		btnreturn.setBackground(new Color(25, 118, 210));
		btnreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mainFrame.setVisible(true);
			}
		});
		btnreturn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnreturn.setBounds(120, 559, 200, 35);
		contentPane.add(btnreturn);
	}
}
