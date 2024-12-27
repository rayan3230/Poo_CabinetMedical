package GUI;

import javax.swing.*;
import Main_classes.MedicalOffice;
import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Sec_menu extends JFrame {
    private JFrame frame;
    private MedicalOffice office;
    private Accounts accountManger;
    private Main_Menu mainFrame;
    private Secretary logSecretary;

    private static final long serialVersionUID = 1L;

    public Login_Sec_menu(MedicalOffice office, Main_Menu mainFrame, Accounts accountManager, Secretary logSecretary) {
        this.frame = new JFrame();
        this.office = office;
        this.mainFrame = mainFrame;
        this.accountManger = accountManager;
        this.logSecretary = logSecretary;

        setTitle("Secretary Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 802, 600);
        getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);

        // Title label
        JLabel lblTitle = new JLabel("Secretary Menu");
        lblTitle.setForeground(new Color(25, 118, 210));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(131, 0, 484, 50);
        getContentPane().add(lblTitle);

        // Add Patient button
        JButton btnAddPatient = new JButton("Add Patient");
        btnAddPatient.setBackground(new Color(25, 118, 210));
        btnAddPatient.setForeground(new Color(255, 255, 255));
        btnAddPatient.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAddPatient.setBounds(234, 156, 300, 50);
        btnAddPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter Patient Name:");
                String password = JOptionPane.showInputDialog(frame, "Enter Password:");
                if (name != null && password != null && !name.trim().isEmpty() && !password.trim().isEmpty()) {
                    Client patient = new Client(name, password);
                    office.addClient(patient);
                    JOptionPane.showMessageDialog(frame, "Patient added successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.");
                }
            }
        });
        getContentPane().add(btnAddPatient);

        // Add Appointment button
        JButton btnAddAppointment = new JButton("Add Appointment");
        btnAddAppointment.setBackground(new Color(25, 118, 210));
        btnAddAppointment.setForeground(new Color(255, 255, 255));
        btnAddAppointment.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAddAppointment.setBounds(234, 226, 300, 50);
        btnAddAppointment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (office.Clients.isEmpty() || accountManger.Doctors.isEmpty()) {
                    JOptionPane.showMessageDialog(Login_Sec_menu.this,
                            "Need both patients and doctors to create an appointment.");
                    return;
                }

                // Create lists of patient and doctor names
                String[] patientNames = new String[office.Clients.size()];
                for (int i = 0; i < office.Clients.size(); i++) {
                    patientNames[i] = office.Clients.get(i).FullName;
                }

                String[] doctorNames = new String[accountManger.Doctors.size()];
                for (int i = 0; i < accountManger.Doctors.size(); i++) {
                    doctorNames[i] = accountManger.Doctors.get(i).FullName;
                }

                // Get patient selection
                String selectedPatient = (String) JOptionPane.showInputDialog(
                        frame, "Select a patient:", "Patient Selection",
                        JOptionPane.QUESTION_MESSAGE, null, patientNames, patientNames[0]);

                if (selectedPatient == null)
                    return;

                // Get doctor selection
                String selectedDoctor = (String) JOptionPane.showInputDialog(
                        frame, "Select a doctor:", "Doctor Selection",
                        JOptionPane.QUESTION_MESSAGE, null, doctorNames, doctorNames[0]);

                if (selectedDoctor == null)
                    return;

                // Get appointment date
                String appointmentDate = JOptionPane.showInputDialog(
                        frame, "Enter appointment date (YYYY-MM-DD):");

                if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
                    // Find selected patient and doctor objects
                    Client patient = null;
                    Doctor doctor = null;

                    for (Client c : office.Clients) {
                        if (c.FullName.equals(selectedPatient)) {
                            patient = c;
                            break;
                        }
                    }

                    for (Doctor d : accountManger.Doctors) {
                        if (d.FullName.equals(selectedDoctor)) {
                            doctor = d;
                            break;
                        }
                    }

                    if (patient != null && doctor != null) {
                        VisitDates appointment = new VisitDates(appointmentDate, patient, doctor);
                        office.addAppointment(appointment);
                        JOptionPane.showMessageDialog(frame,
                                "Appointment scheduled successfully!\nPatient: " + patient.FullName +
                                        "\nDoctor: " + doctor.FullName +
                                        "\nDate: " + appointmentDate);
                    }
                }
            }
        });
        getContentPane().add(btnAddAppointment);

        // Display Patients button
        JButton btnDisplayPatients = new JButton("Display Patients");
        btnDisplayPatients.setBackground(new Color(25, 118, 210));
        btnDisplayPatients.setForeground(new Color(255, 255, 255));
        btnDisplayPatients.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDisplayPatients.setBounds(234, 296, 300, 50);
        btnDisplayPatients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder patientsList = new StringBuilder();
                for (int i = 0; i < office.Clients.size(); i++) {
                    Client client = office.Clients.get(i);
                    patientsList.append(i + 1).append(". ")
                            .append(client.FullName)
                            .append(" (Phone: ").append(client.PhoneNum).append(")")
                            .append("\n");
                }

                if (patientsList.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "No patients registered.");
                } else {
                    JTextArea textArea = new JTextArea(patientsList.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(300, 200));

                    JOptionPane.showMessageDialog(frame, scrollPane,
                            "Registered Patients", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        getContentPane().add(btnDisplayPatients);

        // Display Appointments button
        JButton btnDisplayAppointments = new JButton("Display Appointments");
        btnDisplayAppointments.setBackground(new Color(25, 118, 210));
        btnDisplayAppointments.setForeground(new Color(255, 255, 255));
        btnDisplayAppointments.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDisplayAppointments.setBounds(234, 372, 300, 50);
        btnDisplayAppointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder appointmentsList = new StringBuilder();

                for (VisitDates appointment : office.Appointments) {
                    appointmentsList.append("Date: ").append(appointment.Date)
                            .append("\nPatient: ").append(appointment.patient.FullName)
                            .append("\nDoctor: ").append(appointment.doctor.FullName)
                            .append("\n-----------------\n");
                }

                if (appointmentsList.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "No appointments scheduled.");
                } else {
                    JTextArea textArea = new JTextArea(appointmentsList.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(300, 200));

                    JOptionPane.showMessageDialog(frame, scrollPane,
                            "Scheduled Appointments", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        getContentPane().add(btnDisplayAppointments);

        // Back button
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.setBackground(new Color(25, 118, 210));
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setBounds(234, 464, 300, 50);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainFrame.setVisible(true);
            }
        });
        getContentPane().add(btnBack);

        JLabel lblNewLabel = new JLabel("Welcome to the medical Cabinet System ");
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNewLabel.setBounds(234, 47, 300, 30);
        getContentPane().add(lblNewLabel);
    }
}