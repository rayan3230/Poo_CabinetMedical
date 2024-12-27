package GUI;

import javax.swing.*;
import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Main_classes.MedicalOffice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Doctor_menu extends JFrame {
    private JFrame frame;
    private Accounts accountManger;
    private Main_Menu mainFrame;
    private MedicalOffice office;
    private Doctor logDoctor;

    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public Login_Doctor_menu(MedicalOffice office, Main_Menu MainFrame, Accounts AccountManager, Doctor logDoctor) {
        // Frame settings
        frame = new JFrame();

        this.accountManger = AccountManager;
        this.office = office;
        this.mainFrame = MainFrame;
        this.logDoctor = logDoctor;

        setTitle("Doctor Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 801, 600);
        getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);

        // Title label
        JLabel lblTitle = new JLabel("Doctor Menu");
        lblTitle.setForeground(new Color(25, 118, 210));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(141, 10, 484, 50);
        getContentPane().add(lblTitle);

        /*----------------------------------------------------Update profile button-------------------------*/
        JButton btnAddPatient = new JButton("Update Profile");
        btnAddPatient.setBackground(new Color(25, 118, 210));
        btnAddPatient.setForeground(new Color(255, 255, 255));
        btnAddPatient.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAddPatient.setBounds(227, 218, 300, 50);

        getContentPane().add(btnAddPatient);

        // ----------------------------------------============= Display Patients
        // button------------=====================/
        JButton btnDisplayPatients = new JButton("View Patient");
        btnDisplayPatients.setBackground(new Color(25, 118, 210));
        btnDisplayPatients.setForeground(new Color(255, 255, 255));
        btnDisplayPatients.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDisplayPatients.setBounds(227, 289, 300, 50);
        btnDisplayPatients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // You can also show the result in a JOptionPane dialog
                office.displayClients();

                // You can also show the result in a JOptionPane dialog
                StringBuilder patientsList = new StringBuilder();
                for (int i = 0; i < office.Clients.size(); i++) {
                    patientsList.append(i + 1).append(". ").append(office.Clients.get(i).FullName)
                            .append(", Phone: ").append(office.Clients.get(i).PhoneNum).append("\n");
                }
                if (patientsList.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "No patients found.");
                } else {
                    JOptionPane.showMessageDialog(frame, patientsList.toString());
                }
            }
        });
        getContentPane().add(btnDisplayPatients);

        // -----------------------------------------------------View Schedule
        // button-------------------------------------/
        JButton btnDisplayAppointments = new JButton("View Schedule");
        btnDisplayAppointments.setBackground(new Color(25, 118, 210));
        btnDisplayAppointments.setForeground(new Color(255, 255, 255));
        btnDisplayAppointments.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDisplayAppointments.setBounds(227, 355, 300, 50);
        btnDisplayAppointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder appointmentsList = new StringBuilder();
                boolean hasAppointments = false;

                for (VisitDates appointment : office.Appointments) {
                    if (appointment.doctor.equals(logDoctor)) {
                        hasAppointments = true;
                        appointmentsList.append("Date: ").append(appointment.Date)
                                .append("\nPatient: ").append(appointment.patient.FullName)
                                .append("\nPhone: ").append(appointment.patient.PhoneNum)
                                .append("\n-----------------\n");
                    }
                }

                if (!hasAppointments) {
                    JOptionPane.showMessageDialog(Login_Doctor_menu.this,
                            "No appointments found for Dr. " + logDoctor.FullName);
                } else {
                    JTextArea textArea = new JTextArea(appointmentsList.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(300, 200));

                    JOptionPane.showMessageDialog(Login_Doctor_menu.this,
                            scrollPane,
                            "Appointments for Dr. " + logDoctor.FullName,
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        getContentPane().add(btnDisplayAppointments);

        // --------------------------------------------------------------Exit
        // button---------------------------------------------------/
        JButton btnExit = new JButton("Logout");
        btnExit.setForeground(new Color(255, 255, 255));
        btnExit.setBackground(new Color(25, 118, 210));
        btnExit.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnExit.setBounds(227, 487, 300, 50);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainFrame.setVisible(true);
            }
        });
        getContentPane().add(btnExit);

        JLabel WelcomingLabel = new JLabel("Welcome,Dr. " + logDoctor.FullName);
        WelcomingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        WelcomingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        WelcomingLabel.setBounds(276, 57, 220, 30);
        getContentPane().add(WelcomingLabel);

        JLabel lblNewLabel = new JLabel("Speciality :" + logDoctor.Profession);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNewLabel.setBounds(276, 82, 220, 30);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Email :" + logDoctor.Mail);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(276, 107, 220, 30);
        getContentPane().add(lblNewLabel_1);
    }

    /**
     * Launch the application.
     */
    /*
     * public static void main(String[] args) {
     * EventQueue.invokeLater(() -> {
     * try {
     * MedicalOffice office = new MedicalOffice(); // Create MedicalOffice instance
     * Accounts accountManager = new Accounts();
     * Main_Menu MainFrame = new Main_Menu();
     * Doctor LogDoctor = new Doctor();
     * // Initialize sample doctors
     * Login_Doctor_menu frame = new Login_Doctor_menu(office,MainFrame
     * ,accountManager , logDoctor);
     * frame.setVisible(true);
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * });
     * }
     */

}
