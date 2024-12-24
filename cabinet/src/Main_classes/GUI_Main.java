package Main_classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GUI_Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Main frame = new GUI_Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI_Main() {// creating a frame 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the user can close the panel easely
		setBounds(100, 100, 407, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreating = new JButton("Create Account");
		btnCreating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//about account
			}
		});
		btnCreating.setBackground(new Color(128, 128, 128));
		btnCreating.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCreating.setBounds(32, 170, 331, 106);
		contentPane.add(btnCreating);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//about login btn
			}
		});
		btnLogin.setBackground(new Color(128, 128, 128));
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnLogin.setBounds(32, 303, 331, 106);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit\r\n\r\n\r\n");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//about exit btn
			}
		});
		btnExit.setBackground(new Color(128, 128, 128));
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnExit.setBounds(32, 432, 331, 106);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Medical Cabient");
		lblNewLabel.setBackground(new Color(0, 128, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(10, 10, 373, 114);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		panel.setBounds(0, 10, 393, 125);
		contentPane.add(panel);
	}
}
