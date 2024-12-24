package GUI;

import java.awt.EventQueue;

import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import Main_classes.MedicalOffice;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main_Menu {

	private JFrame frame1;
	private Accounts sharedAccounts = new Accounts();
	private MedicalOffice sharedOffice = new MedicalOffice();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu window = new Main_Menu();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Menu() {
		initialize();
	}

	
	private void initialize() {
		frame1 = new JFrame();
		frame1.getContentPane().setBackground(new Color(255, 255, 255));
		frame1.getContentPane().setLayout(null);
		frame1.setSize(801, 603);
        frame1.setLocationRelativeTo(null);
/*-----------------------------------------------------------creating_user_Account-------------------------------------*/
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.setBackground(new Color(25, 118, 210));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				Create_Account_Frame createAccountFrame = new Create_Account_Frame(Main_Menu.this , sharedAccounts);
                createAccountFrame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton.setBounds(236, 293, 298, 50);
		frame1.getContentPane().add(btnNewButton);
/*---------------------------------------------------------Login--------------------------------------------------------------------*/
/*-----------------------------------------------------Exit-----------------------------------------------------------------------*/
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(25, 118, 210));
		btnExit.setForeground(new Color(255, 255, 255));
	
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnExit.setBounds(236, 365, 298, 50);
		frame1.getContentPane().add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 118, 210));
		panel.setForeground(new Color(0, 0, 255));
		panel.setBounds(42, 0, 699, 117);
		frame1.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Medical Cabinet");
		lblNewLabel.setBounds(168, 10, 358, 56);
		panel.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(30, 144, 255));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", lblNewLabel.getFont().getStyle() | Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Management System");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(251, 59, 201, 31);
		panel.add(lblNewLabel_1);
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(236, 218, 298, 50);
		frame1.getContentPane().add(btnLogin);
		btnLogin.setToolTipText("");
		btnLogin.setBackground(new Color(25, 118, 210));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				Login_Frame login_frame = new Login_Frame(Main_Menu.this ,sharedAccounts,sharedOffice); // Share the same array of accounts
                login_frame.setVisible(true);
				
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnExit.addActionListener(e -> System.exit(0));
		frame1.setBounds(100, 100, 801, 601);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setVisible(boolean visible) {
        frame1.setVisible(visible);
    }
}


