package GUI_Advance;

import Cabinet.Management.PatientSheet;
import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import Main_classes.MedicalOffice;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class AdvancedMedicalUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private MedicalOffice office = new MedicalOffice();
    private Accounts accounts = new Accounts();
    private Doctor currentDoctor;
    private Secretary currentSecretary;

    private JTabbedPane tabbedPane;
    private JPanel mainPanel, loginPanel, registrationPanel, doctorPanel, secretaryPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPanel leftPanel;
    private Timer slideTimer;
    private int leftPanelX = -300; // Start off-screen
    private boolean isSliding = false;

    public AdvancedMedicalUI() {
        setTitle("Medical Cabinet Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 700);
        setLocationRelativeTo(null);
        setLayout(null);

        // Initialize panels first
        mainPanel = createMainPanel();
        loginPanel = createLoginPanel();
        registrationPanel = createRegistrationPanel();
        doctorPanel = createDoctorPanel();
        secretaryPanel = createSecretaryPanel();

        // Add and show main panel first
        mainPanel.setBounds(0, 0, 1100, 700);
        add(mainPanel);
        mainPanel.setVisible(true);

        // Initialize the slide timer
        slideTimer = new Timer(10, e -> slideLeftPanel());
        
        // Create tabbed pane but keep it hidden initially
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 1100, 700);
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Register", registrationPanel);
        add(tabbedPane);
        tabbedPane.setVisible(false);
        
        // Hide the tab header
        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                return 0;
            }
        });
        
        // Create but hide the left panel initially
        leftPanel = createLeftPanel();
        leftPanel.setBounds(leftPanelX, 0, 300, 700);
        add(leftPanel);
        leftPanel.setVisible(false);

        // Initially hide other panels
        doctorPanel.setVisible(false);
        secretaryPanel.setVisible(false);

        // Add buttons to main panel to navigate to login/register
        JButton btnGetStarted = createStyledButton("Get Started", 300, 420, 100, 40);
        btnGetStarted.addActionListener(e -> {
            mainPanel.setVisible(false);
            tabbedPane.setVisible(true);
            tabbedPane.setSelectedIndex(0); // Show login panel first
        });
        mainPanel.add(btnGetStarted);
    }

    private void startSlideAnimation() {
        if (!isSliding) {
            isSliding = true;
            leftPanel.setVisible(true);
            slideTimer.start();
        }
    }

    private void slideLeftPanel() {
        if (leftPanelX < 0) {
            leftPanelX += 20;
            leftPanel.setBounds(leftPanelX, 0, 300, 700);
            repaint();
        } else {
            slideTimer.stop();
            leftPanelX = 0;
            isSliding = false;
        }
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(33, 37, 41));
        leftPanel.setPreferredSize(new Dimension(300, 700));
        leftPanel.setLayout(null);

        // Logo
        JLabel lblLogo = new JLabel("MC");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblLogo.setBounds(40, 40, 220, 60);
        leftPanel.add(lblLogo);

        JLabel lblLogoSub = new JLabel("Medical Cabinet");
        lblLogoSub.setForeground(new Color(158, 161, 178));
        lblLogoSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLogoSub.setBounds(40, 100, 220, 25);
        leftPanel.add(lblLogoSub);

        // Different navigation buttons based on user type
        if (currentDoctor != null) {
            addNavButton(leftPanel, "Dashboard", e -> showPanel(doctorPanel), 200);
            addNavButton(leftPanel, "View Patients", e -> displayPatients(), 260);
            addNavButton(leftPanel, "View Schedule", e -> displaySchedule(), 320);
            addNavButton(leftPanel, "Logout", e -> handleLogout(), 380);
        } else if (currentSecretary != null) {
            addNavButton(leftPanel, "Dashboard", e -> showPanel(secretaryPanel), 200);
            addNavButton(leftPanel, "Add Patient", e -> addPatient(), 260);
            addNavButton(leftPanel, "Add Appointment", e -> addAppointment(), 320);
            addNavButton(leftPanel, "View Patients", e -> displayPatients(), 380);
            addNavButton(leftPanel, "View Appointments", e -> displayAppointments(), 440);
            addNavButton(leftPanel, "Logout", e -> handleLogout(), 500);
        }

        return leftPanel;
    }

    private void addNavButton(JPanel panel, String text, ActionListener action, int y) {
        JButton button = createNavButton(text, 40, y);
        button.addActionListener(action);
        panel.add(button);
    }

    private JButton createNavButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 220, 45);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(52, 58, 64));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(73, 80, 87));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 58, 64));
            }
        });
        
        return button;
    }

    private void showPanel(JPanel panel) {
        // Position the panels correctly
        if (panel == doctorPanel || panel == secretaryPanel) {
            panel.setBounds(300, 0, 800, 700); // Position to the right of left panel
        } else {
            panel.setBounds(0, 0, 1100, 700); // Full width for other panels
        }
        
        // Hide all panels first
        mainPanel.setVisible(false);
        loginPanel.setVisible(false);
        registrationPanel.setVisible(false);
        doctorPanel.setVisible(false);
        secretaryPanel.setVisible(false);
        
        // Show the selected panel
        panel.setVisible(true);
        
        // Ensure panel is added to frame if not already
        if (!Arrays.asList(getContentPane().getComponents()).contains(panel)) {
            add(panel);
        }
        
        // Bring panel to front
        panel.revalidate();
        panel.repaint();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 242, 245));
        panel.setLayout(null);

        // Background Image Panel
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(new Color(33, 37, 41));
        bgPanel.setBounds(0, 0, 1100, 300);
        panel.add(bgPanel);

        // Welcome Card
        JPanel welcomeCard = new JPanel();
        welcomeCard.setBackground(Color.WHITE);
        welcomeCard.setBounds(200, 150, 700, 450);
        welcomeCard.setLayout(null);
        panel.add(welcomeCard);

        // Logo at the top with colored background
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(21, 101, 192));
        logoPanel.setBounds(0, 0, 700, 150);
        logoPanel.setLayout(null);
        welcomeCard.add(logoPanel);

        JLabel lblLogo = new JLabel("MC");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 72));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(0, 20, 700, 80);
        logoPanel.add(lblLogo);

        JLabel lblLogoSub = new JLabel("Medical Cabinet");
        lblLogoSub.setForeground(Color.WHITE);
        lblLogoSub.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        lblLogoSub.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoSub.setBounds(0, 100, 700, 30);
        logoPanel.add(lblLogoSub);

        // Welcome message
        JLabel lblWelcome = new JLabel("Welcome to Our Medical Office");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblWelcome.setForeground(new Color(33, 37, 41));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(50, 180, 600, 50);
        welcomeCard.add(lblWelcome);

        // Description with icons
        JLabel lblDesc = new JLabel("<html><center>✓ Manage Appointments<br>✓ Track Patient Records<br>✓ Efficient Workflow</center></html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblDesc.setForeground(new Color(108, 117, 125));
        lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesc.setBounds(100, 250, 500, 100);
        welcomeCard.add(lblDesc);

        // Decorative elements
        JSeparator separator1 = new JSeparator();
        separator1.setBounds(150, 370, 400, 2);
        separator1.setForeground(new Color(21, 101, 192));
        welcomeCard.add(separator1);

        // Footer text
        JLabel lblFooter = new JLabel("© 2024 Medical Cabinet Management System");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFooter.setForeground(new Color(108, 117, 125));
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        lblFooter.setBounds(150, 390, 400, 20);
        welcomeCard.add(lblFooter);

        // Add shadow effect
        welcomeCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 20), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        // Add hover effect
        addPanelHoverEffect(welcomeCard);

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 242, 245));
        panel.setLayout(null);

        // Background Panel
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(new Color(33, 37, 41));
        bgPanel.setBounds(0, 0, 1100, 200);
        panel.add(bgPanel);

        // Login Card
        JPanel loginCard = new JPanel();
        loginCard.setBackground(Color.WHITE);
        loginCard.setBounds(200, 100, 700, 500);
        loginCard.setLayout(null);
        panel.add(loginCard);

        // Logo at the top with colored background
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(21, 101, 192));
        logoPanel.setBounds(0, 0, 700, 100);
        logoPanel.setLayout(null);
        loginCard.add(logoPanel);

        JLabel lblLogo = new JLabel("Login to Your Account");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(0, 30, 700, 40);
        logoPanel.add(lblLogo);

        // Account Type Selection with modern radio buttons
        JLabel lblAccountType = new JLabel("Select Account Type");
        lblAccountType.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAccountType.setBounds(50, 120, 600, 30);
        loginCard.add(lblAccountType);

        JRadioButton rdbDoc = new JRadioButton("Doctor");
        rdbDoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rdbDoc.setBounds(50, 160, 150, 30);
        rdbDoc.setBackground(Color.WHITE);
        loginCard.add(rdbDoc);

        JRadioButton rdbSec = new JRadioButton("Secretary");
        rdbSec.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rdbSec.setBounds(210, 160, 150, 30);
        rdbSec.setBackground(Color.WHITE);
        loginCard.add(rdbSec);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbDoc);
        group.add(rdbSec);

        // Initialize the text fields
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        // Add the fields to the panel with modern styling
        addFormField(loginCard, "Username", txtUsername, 210);
        addFormField(loginCard, "Password", txtPassword, 290);

        // Login Button with modern styling
        JButton btnLogin = createStyledButton("Login", 50, 380, 600, 45);
        btnLogin.addActionListener(e -> handleLogin(rdbDoc, rdbSec, txtPassword));
        loginCard.add(btnLogin);

        // Add "Create Account" link
        JLabel lblNoAccount = new JLabel("Don't have an account?");
        lblNoAccount.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNoAccount.setForeground(new Color(108, 117, 125));
        lblNoAccount.setBounds(50, 440, 200, 30);
        loginCard.add(lblNoAccount);

        JButton btnToRegister = new JButton("Create one");
        btnToRegister.setBounds(250, 440, 100, 30);
        btnToRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnToRegister.setForeground(new Color(21, 101, 192));
        btnToRegister.setContentAreaFilled(false);
        btnToRegister.setBorderPainted(false);
        btnToRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToRegister.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        loginCard.add(btnToRegister);

        // Add shadow effect
        loginCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 20), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        addPanelHoverEffect(loginCard);
        return panel;
    }

    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 242, 245));
        panel.setLayout(null);

        // Background Panel
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(new Color(33, 37, 41));
        bgPanel.setBounds(0, 0, 1100, 200);
        panel.add(bgPanel);

        // Create a container panel for the scroll pane
        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setLayout(null);
        containerPanel.setPreferredSize(new Dimension(700, 800)); // Taller to accommodate all content

        // Logo at the top with colored background
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(21, 101, 192));
        logoPanel.setBounds(0, 0, 700, 100);
        logoPanel.setLayout(null);
        containerPanel.add(logoPanel);

        JLabel lblLogo = new JLabel("Create New Account");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(0, 30, 700, 40);
        logoPanel.add(lblLogo);

        // Account Type Selection
        JLabel lblAccountType = new JLabel("Select Account Type");
        lblAccountType.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAccountType.setBounds(50, 120, 600, 30);
        containerPanel.add(lblAccountType);

        JRadioButton rdbdoctor = new JRadioButton("Doctor");
        rdbdoctor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rdbdoctor.setBounds(50, 160, 150, 30);
        rdbdoctor.setBackground(Color.WHITE);
        containerPanel.add(rdbdoctor);

        JRadioButton rdbsecretery = new JRadioButton("Secretary");
        rdbsecretery.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rdbsecretery.setBounds(210, 160, 150, 30);
        rdbsecretery.setBackground(Color.WHITE);
        containerPanel.add(rdbsecretery);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbdoctor);
        group.add(rdbsecretery);

        // Form Fields with modern styling
        JTextField txtFullname = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JTextField txtEmail = new JTextField();
        JTextField txtSpc = new JTextField();
        JTextField txtPhonenum = new JTextField();

        addFormField(containerPanel, "Full Name", txtFullname, 210);
        addFormField(containerPanel, "Password", txtPassword, 280);
        addFormField(containerPanel, "Email", txtEmail, 350);
        addFormField(containerPanel, "Specialization", txtSpc, 420);
        addFormField(containerPanel, "Phone Number", txtPhonenum, 490);

        // Disable fields for secretary
        rdbsecretery.addActionListener(e -> {
            txtEmail.setEnabled(false);
            txtSpc.setEnabled(false);
            txtPhonenum.setEnabled(false);
        });

        rdbdoctor.addActionListener(e -> {
            txtEmail.setEnabled(true);
            txtSpc.setEnabled(true);
            txtPhonenum.setEnabled(true);
        });

        // Create Account Button
        JButton btnConfirm = createStyledButton("Create Account", 50, 560, 600, 45);
        btnConfirm.addActionListener(e -> {
            if (validateRegistration(rdbdoctor, rdbsecretery, txtFullname, txtPassword)) {
                createAccount(rdbdoctor, txtFullname, txtPassword, txtEmail, txtSpc, txtPhonenum);
            }
        });
        containerPanel.add(btnConfirm);

        // Add "Back to Login" link
        JLabel lblHaveAccount = new JLabel("Already have an account?");
        lblHaveAccount.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHaveAccount.setForeground(new Color(108, 117, 125));
        lblHaveAccount.setBounds(200, 620, 200, 30);
        containerPanel.add(lblHaveAccount);

        JButton btnToLogin = new JButton("Login here");
        btnToLogin.setBounds(400, 620, 100, 30);
        btnToLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnToLogin.setForeground(new Color(21, 101, 192));
        btnToLogin.setContentAreaFilled(false);
        btnToLogin.setBorderPainted(false);
        btnToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnToLogin.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        containerPanel.add(btnToLogin);

        // Create scroll pane and add the container panel to it
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setBounds(200, 100, 700, 550);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane);

        // Add shadow effect to the scroll pane
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 20), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        return panel;
    }

    private boolean validateRegistration(JRadioButton rdbDoctor, JRadioButton rdbSecretary, 
            JTextField txtFullname, JPasswordField txtPassword) {
        if (txtFullname.getText().isEmpty() || new String(txtPassword.getPassword()).isEmpty() || 
            (!rdbDoctor.isSelected() && !rdbSecretary.isSelected())) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all required fields and select an account type.");
            return false;
        }
        return true;
    }

    private void createAccount(JRadioButton rdbDoctor, JTextField txtFullname, 
            JPasswordField txtPassword, JTextField txtEmail, JTextField txtSpc, JTextField txtPhonenum) {
        if (rdbDoctor.isSelected()) {
            Doctor doc = new Doctor(
                txtFullname.getText(),
                txtSpc.getText(),
                txtEmail.getText(),
                txtPhonenum.getText(),
                new String(txtPassword.getPassword())
            );
            accounts.AddDocAccount(doc);
        } else {
            Secretary sec = new Secretary(
                txtFullname.getText(),
                new String(txtPassword.getPassword())
            );
            accounts.AddSecAccount(sec);
        }
        JOptionPane.showMessageDialog(this, "Account created successfully!");
        tabbedPane.setSelectedIndex(1); // Switch to login tab
    }

    private void handleLogin(JRadioButton rdbDoc, JRadioButton rdbSec, JPasswordField txtPassword) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.");
            return;
        }

        if (rdbDoc.isSelected()) {
            currentDoctor = accounts.getDoctorByCredentials(username, password);
            if (currentDoctor != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                tabbedPane.setVisible(false);
                startSlideAnimation();
                showPanel(doctorPanel);
                updateDoctorLabels();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid doctor credentials.");
            }
        } else if (rdbSec.isSelected()) {
            currentSecretary = accounts.getSecretaryByCredentials(username, password);
            if (currentSecretary != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                tabbedPane.setVisible(false);
                startSlideAnimation();
                showPanel(secretaryPanel);
                updateSecretaryLabels();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid secretary credentials.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an account type.");
        }
    }

    private JPanel createDoctorPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 242, 245));
        panel.setLayout(null);

        // Create a main scroll panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(240, 242, 245));
        
        // Doctor Info Card
        JPanel infoCard = new JPanel();
        infoCard.setBackground(Color.WHITE);
        infoCard.setBounds(60, 40, 680, 140);
        infoCard.setLayout(null);
        contentPanel.add(infoCard);

        // Initialize with placeholder text, will update when doctor logs in
        JLabel lblWelcome = new JLabel("Welcome, Doctor");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setForeground(new Color(33, 37, 41));
        lblWelcome.setBounds(30, 20, 620, 35);
        infoCard.add(lblWelcome);

        JLabel lblSpeciality = new JLabel("Speciality: ");
        lblSpeciality.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSpeciality.setBounds(30, 60, 620, 25);
        infoCard.add(lblSpeciality);

        JLabel lblEmail = new JLabel("Email: ");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(30, 90, 620, 25);
        infoCard.add(lblEmail);

        addPanelHoverEffect(infoCard);

        // Actions Panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setBounds(60, 200, 680, 500); // Increased height
        actionsPanel.setLayout(null);
        contentPanel.add(actionsPanel);

        JLabel lblActions = new JLabel("Quick Actions");
        lblActions.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblActions.setBounds(30, 20, 620, 30);
        actionsPanel.add(lblActions);

        addActionButton(actionsPanel, "Update Profile", 
            "Update your personal information", 80, 
            e -> updateDoctorProfile());

        addActionButton(actionsPanel, "View Schedule", 
            "Check your appointments", 170, 
            e -> displaySchedule());

        addActionButton(actionsPanel, "Medical Files", 
            "Manage patient medical records", 260, 
            e -> managePatientSheet());

        addActionButton(actionsPanel, "Logout", 
            "Return to main menu", 350, 
            e -> handleLogout());

        addPanelHoverEffect(actionsPanel);

        // Set content panel size for scrolling
        contentPanel.setPreferredSize(new Dimension(800, 800));

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(0, 0, 800, 700);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane);

        return panel;
    }

    private void viewAllPatientSheets() {
        JDialog sheetsDialog = new JDialog(this, "Patient Sheets Management", true);
        sheetsDialog.setLayout(new BorderLayout());
        sheetsDialog.setSize(800, 600);
        sheetsDialog.setLocationRelativeTo(this);

        // Add a header panel with title and new sheet button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 37, 41));
        headerPanel.setPreferredSize(new Dimension(800, 60));
        
        JLabel titleLabel = new JLabel("Patient Sheets");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        JButton newSheetButton = createStyledButton("New Sheet", 0, 0, 120, 40);
        newSheetButton.addActionListener(e -> {
            sheetsDialog.dispose();
            managePatientSheet();
        });
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(newSheetButton, BorderLayout.EAST);
        
        // Create main panel for sheets
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (office.PatientSheets.isEmpty()) {
            JLabel emptyLabel = new JLabel("No patient sheets available. Create a new one!");
            emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(emptyLabel);
        } else {
            // Add each sheet as a card
            for (PatientSheet sheet : office.PatientSheets) {
                JPanel sheetCard = createSheetCard(sheet);
                mainPanel.add(sheetCard);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        sheetsDialog.add(headerPanel, BorderLayout.NORTH);
        sheetsDialog.add(scrollPane, BorderLayout.CENTER);
        sheetsDialog.setVisible(true);
    }

    private JPanel createSheetCard(PatientSheet sheet) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 20), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Header with patient name
        JLabel nameLabel = new JLabel(sheet.FullName);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        card.add(nameLabel, BorderLayout.NORTH);

        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        contentPanel.setBackground(Color.WHITE);

        contentPanel.add(new JLabel("Phone: " + sheet.PhoneNum));
        contentPanel.add(new JLabel("Weight: " + sheet.Weight + " kg"));
        contentPanel.add(new JLabel("Height: " + sheet.Height + " cm"));
        contentPanel.add(new JLabel("Medical Observations: " + sheet.MedicalObservations));
        contentPanel.add(new JLabel("Surgical Observations: " + sheet.SurgicalObservations));

        card.add(contentPanel, BorderLayout.CENTER);

        // Add hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(248, 249, 250));
                contentPanel.setBackground(new Color(248, 249, 250));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                contentPanel.setBackground(Color.WHITE);
            }
        });

        return card;
    }

    private JPanel createSecretaryPanel() {
        // Create a main scroll panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(0, 0, 800, 700);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Secretary Info Card
        JPanel infoCard = new JPanel();
        infoCard.setBackground(Color.WHITE);
        infoCard.setBounds(60, 40, 680, 140);
        infoCard.setLayout(null);
        mainPanel.add(infoCard);

        // Initialize with placeholder text
        JLabel lblWelcome = new JLabel("Welcome, Secretary");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setForeground(new Color(33, 37, 41));
        lblWelcome.setBounds(30, 20, 620, 35);
        infoCard.add(lblWelcome);

        JLabel lblRole = new JLabel("Medical Office Management System");
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblRole.setBounds(30, 60, 620, 25);
        infoCard.add(lblRole);

        addPanelHoverEffect(infoCard);

        // Actions Panel with more space for buttons
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setBounds(60, 200, 680, 600); // Increased height
        actionsPanel.setLayout(null);
        mainPanel.add(actionsPanel);

        JLabel lblActions = new JLabel("Quick Actions");
        lblActions.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblActions.setBounds(30, 20, 620, 30);
        actionsPanel.add(lblActions);

        // Add all action buttons with more spacing
        addActionButton(actionsPanel, "Add Patient", 
            "Register a new patient in the system", 80, 
            e -> addPatient());

        addActionButton(actionsPanel, "Add Appointment", 
            "Schedule a new appointment", 170, 
            e -> addAppointment());

        addActionButton(actionsPanel, "View Patients", 
            "See list of all registered patients", 260, 
            e -> displayPatients());

        addActionButton(actionsPanel, "View Appointments", 
            "Check all scheduled appointments", 350, 
            e -> displayAppointments());

        addActionButton(actionsPanel, "Logout", 
            "Return to main menu", 440, 
            e -> handleLogout());

        addPanelHoverEffect(actionsPanel);
        
        // Set preferred size for scroll pane
        mainPanel.setPreferredSize(new Dimension(800, 900));
        
        JPanel containerPanel = new JPanel(null);
        containerPanel.add(scrollPane);
        return containerPanel;
    }

    private void handleLogout() {
        currentDoctor = null;
        currentSecretary = null;
        // Slide out animation could be added here
        leftPanel.setVisible(false);
        tabbedPane.setVisible(true);
        leftPanelX = -300; // Reset position
        JOptionPane.showMessageDialog(this, "Logged out successfully!");
    }

    private void displayPatients() {
        StringBuilder patientsList = new StringBuilder();
        if (office.Clients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No patients registered.");
            return;
        }

        for (Client client : office.Clients) {
            patientsList.append(String.format("%s (Phone: %s)\n",
                client.FullName, client.PhoneNum));
        }

        showScrollableDialog(patientsList.toString(), "Registered Patients");
    }

    private void displaySchedule() {
        if (!office.Appointments.stream().anyMatch(a -> a.doctor.equals(currentDoctor))) {
            JOptionPane.showMessageDialog(this, "No appointments scheduled.");
            return;
        }

        // Create a custom dialog
        JDialog scheduleDialog = new JDialog(this, "Your Schedule", true);
        scheduleDialog.setLayout(new BorderLayout());
        scheduleDialog.setSize(600, 500);
        scheduleDialog.setLocationRelativeTo(this);

        // Create main panel with dark header
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 242, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 37, 41));
        headerPanel.setPreferredSize(new Dimension(600, 60));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

        JLabel headerLabel = new JLabel("Upcoming Appointments");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Create scroll pane for appointments
        JPanel appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));
        appointmentsPanel.setBackground(new Color(240, 242, 245));
        appointmentsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add appointment cards
        office.Appointments.stream()
                .filter(a -> a.doctor.equals(currentDoctor))
                .forEach(appointment -> {
                    JPanel appointmentCard = createAppointmentCard(appointment);
                    appointmentsPanel.add(appointmentCard);
                    appointmentsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing between cards
                });

        JScrollPane scrollPane = new JScrollPane(appointmentsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        scheduleDialog.add(mainPanel);
        scheduleDialog.setVisible(true);
    }

    private JPanel createAppointmentCard(VisitDates appointment) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(550, 120));
        card.setMaximumSize(new Dimension(550, 120));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 20), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Left side - Date and time
        JPanel datePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        datePanel.setBackground(Color.WHITE);
        
        String[] dateTime = appointment.Date.split(" ");
        JLabel dateLabel = new JLabel(dateTime[0]);
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        JLabel timeLabel = new JLabel(dateTime.length > 1 ? dateTime[1] : "");
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timeLabel.setForeground(new Color(108, 117, 125));
        
        datePanel.add(dateLabel);
        datePanel.add(timeLabel);

        // Right side - Patient info
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel("Patient: " + appointment.patient.FullName);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel phoneLabel = new JLabel("Phone: " + appointment.patient.PhoneNum);
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneLabel.setForeground(new Color(108, 117, 125));
        
        infoPanel.add(nameLabel);
        infoPanel.add(phoneLabel);

        card.add(datePanel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);

        // Add hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(248, 249, 250));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }

    private void displayAppointments() {
        if (office.Appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No appointments scheduled.");
            return;
        }

        StringBuilder appointmentsList = new StringBuilder();
        for (VisitDates appointment : office.Appointments) {
            appointmentsList.append(String.format(
                "Date: %s\nPatient: %s\nDoctor: %s\n-----------------\n",
                appointment.Date, appointment.patient.FullName, 
                appointment.doctor.FullName));
        }

        showScrollableDialog(appointmentsList.toString(), "All Appointments");
    }

    private void showScrollableDialog(String content, String title) {
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, title, 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Helper methods
    private void addFormField(JPanel panel, String labelText, JComponent field, int yPosition) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setBounds(40, yPosition, 600, 25);
        panel.add(label);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBounds(40, yPosition + 25, 600, 35);
        panel.add(field);
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(33, 37, 41));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 58, 64));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(33, 37, 41));
            }
        });
        
        return button;
    }

    private void addPanelHoverEffect(JPanel panel) {
        panel.setBorder(BorderFactory.createLineBorder(new Color(21, 101, 192), 2));
    }

    private void addActionButton(JPanel panel, String title, String description, int yPos, ActionListener action) {
        JPanel buttonCard = new JPanel();
        buttonCard.setBackground(new Color(240, 242, 245));
        buttonCard.setBounds(30, yPos, 620, 70);
        buttonCard.setLayout(null);
        panel.add(buttonCard);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setBounds(20, 10, 580, 25);
        buttonCard.add(lblTitle);

        JLabel lblDesc = new JLabel(description);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDesc.setForeground(new Color(108, 117, 125));
        lblDesc.setBounds(20, 35, 580, 25);
        buttonCard.add(lblDesc);

        buttonCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.actionPerformed(null);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCard.setBackground(new Color(233, 236, 239));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCard.setBackground(new Color(240, 242, 245));
            }
        });
    }

    private void addAppointment() {
        if (office.Clients.isEmpty() || accounts.Doctors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Need both patients and doctors to create an appointment.");
            return;
        }

        String[] patientNames = office.Clients.stream()
                .map(c -> c.FullName)
                .toArray(String[]::new);

        String[] doctorNames = accounts.Doctors.stream()
                .map(d -> d.FullName)
                .toArray(String[]::new);

        String selectedPatient = (String) JOptionPane.showInputDialog(
                this, "Select a patient:", "Patient Selection",
                JOptionPane.QUESTION_MESSAGE, null, patientNames, patientNames[0]);

        if (selectedPatient == null) return;

        String selectedDoctor = (String) JOptionPane.showInputDialog(
                this, "Select a doctor:", "Doctor Selection",
                JOptionPane.QUESTION_MESSAGE, null, doctorNames, doctorNames[0]);

        if (selectedDoctor == null) return;

        // Create date picker dialog
        JDialog dateDialog = new JDialog(this, "Select Appointment Date", true);
        dateDialog.setLayout(new BorderLayout());
        dateDialog.setSize(300, 200);
        dateDialog.setLocationRelativeTo(this);

        // Create date spinner
        JPanel datePanel = new JPanel(new FlowLayout());
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm");
        dateSpinner.setEditor(dateEditor);
        datePanel.add(new JLabel("Select Date and Time: "));
        datePanel.add(dateSpinner);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        dateDialog.add(datePanel, BorderLayout.CENTER);
        dateDialog.add(buttonPanel, BorderLayout.SOUTH);

        final java.util.Date[] selectedDate = new java.util.Date[1];

        okButton.addActionListener(e -> {
            selectedDate[0] = (java.util.Date) dateSpinner.getValue();
            dateDialog.dispose();
        });

        cancelButton.addActionListener(e -> {
            selectedDate[0] = null;
            dateDialog.dispose();
        });

        dateDialog.setVisible(true);

        if (selectedDate[0] != null) {
            // Format the date to string
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
            String appointmentDate = sdf.format(selectedDate[0]);

            Client patient = office.Clients.stream()
                    .filter(c -> c.FullName.equals(selectedPatient))
                    .findFirst()
                    .orElse(null);

            Doctor doctor = accounts.Doctors.stream()
                    .filter(d -> d.FullName.equals(selectedDoctor))
                    .findFirst()
                    .orElse(null);

            if (patient != null && doctor != null) {
                VisitDates appointment = new VisitDates(appointmentDate, patient, doctor);
                office.addAppointment(appointment);
                JOptionPane.showMessageDialog(this, String.format(
                    "Appointment scheduled successfully!\nPatient: %s\nDoctor: %s\nDate: %s",
                    patient.FullName, doctor.FullName, appointmentDate));
            }
        }
    }

    private void addPatient() {
        String name = JOptionPane.showInputDialog(this, "Enter Patient Name:");
        String password = JOptionPane.showInputDialog(this, "Enter Password:");
        
        if (name != null && password != null && !name.trim().isEmpty() && !password.trim().isEmpty()) {
            Client patient = new Client(name, password);
            office.addClient(patient);
            JOptionPane.showMessageDialog(this, "Patient added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
        }
    }

    private void updateDoctorLabels() {
        Component[] components = doctorPanel.getComponents();
        for(Component c : components) {
            if(c instanceof JPanel) {
                JPanel card = (JPanel)c;
                Component[] cardComps = card.getComponents();
                for(Component cc : cardComps) {
                    if(cc instanceof JLabel) {
                        JLabel label = (JLabel)cc;
                        if(label.getText().startsWith("Welcome")) {
                            label.setText("Welcome, Dr. " + currentDoctor.FullName);
                        } else if(label.getText().startsWith("Speciality")) {
                            label.setText("Speciality: " + currentDoctor.Profession);
                        } else if(label.getText().startsWith("Email")) {
                            label.setText("Email: " + currentDoctor.Mail);
                        }
                    }
                }
            }
        }
    }

    private void updateSecretaryLabels() {
        Component[] components = secretaryPanel.getComponents();
        for(Component c : components) {
            if(c instanceof JPanel) {
                JPanel card = (JPanel)c;
                Component[] cardComps = card.getComponents();
                for(Component cc : cardComps) {
                    if(cc instanceof JLabel) {
                        JLabel label = (JLabel)cc;
                        if(label.getText().startsWith("Welcome")) {
                            label.setText("Welcome, " + currentSecretary.FullName);
                        }
                    }
                }
            }
        }
    }

    private void updateDoctorProfile() {
        JDialog updateDialog = new JDialog(this, "Update Profile", true);
        updateDialog.setLayout(new BorderLayout());
        updateDialog.setSize(600, 500);
        updateDialog.setLocationRelativeTo(this);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 37, 41));
        headerPanel.setPreferredSize(new Dimension(600, 60));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

        JLabel headerLabel = new JLabel("Update Your Profile");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Create a container panel for scrolling
        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setLayout(null);
        containerPanel.setPreferredSize(new Dimension(580, 700));

        // Create form fields
        JTextField txtFullname = new JTextField(currentDoctor.FullName);
        JTextField txtEmail = new JTextField(currentDoctor.Mail);
        JTextField txtSpecialization = new JTextField(currentDoctor.Profession);
        JTextField txtPhone = new JTextField(currentDoctor.PhoneNum);
        JPasswordField txtNewPassword = new JPasswordField();

        // Add form fields with more spacing
        addFormField(containerPanel, "Full Name", txtFullname, 20);
        addFormField(containerPanel, "Email", txtEmail, 100);
        addFormField(containerPanel, "Specialization", txtSpecialization, 180);
        addFormField(containerPanel, "Phone Number", txtPhone, 260);
        addFormField(containerPanel, "New Password (leave blank to keep current)", txtNewPassword, 340);

        // Create scroll pane with vertical scrolling only
        JScrollPane scrollPane = new JScrollPane(containerPanel, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnSave = createStyledButton("Save Changes", 0, 0, 200, 40);
        JButton btnCancel = createStyledButton("Cancel", 0, 0, 120, 40);
        btnCancel.setBackground(new Color(108, 117, 125));

        btnSave.addActionListener(e -> {
            // Update doctor information
            currentDoctor.FullName = txtFullname.getText();
            currentDoctor.Mail = txtEmail.getText();
            currentDoctor.Profession = txtSpecialization.getText();
            currentDoctor.PhoneNum = txtPhone.getText();
            
            String newPassword = new String(txtNewPassword.getPassword());
            if (!newPassword.trim().isEmpty()) {
                currentDoctor.PassWord = newPassword;
            }

            updateDoctorLabels();
            JOptionPane.showMessageDialog(updateDialog, "Profile updated successfully!");
            updateDialog.dispose();
        });

        btnCancel.addActionListener(e -> updateDialog.dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // Add all panels to dialog
        updateDialog.add(headerPanel, BorderLayout.NORTH);
        updateDialog.add(scrollPane, BorderLayout.CENTER);
        updateDialog.add(buttonPanel, BorderLayout.SOUTH);

        updateDialog.setVisible(true);
    }

    private void managePatientSheet() {
        if (office.Clients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No patients registered.");
            return;
        }

        // Create main dialog
        JDialog medDialog = new JDialog(this, "Medical Files Management", true);
        medDialog.setLayout(new BorderLayout());
        medDialog.setSize(800, 600);
        medDialog.setLocationRelativeTo(this);

        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 37, 41));
        headerPanel.setPreferredSize(new Dimension(800, 60));
        
        JLabel titleLabel = new JLabel("Medical Files");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        JButton newSheetButton = createStyledButton("New Sheet", 0, 0, 120, 40);
        newSheetButton.addActionListener(e -> {
            medDialog.dispose();
            createNewSheet();
        });
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(newSheetButton, BorderLayout.EAST);

        // Create main panel for existing sheets
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (office.PatientSheets.isEmpty()) {
            JLabel emptyLabel = new JLabel("No medical files available. Create a new one!");
            emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(emptyLabel);
        } else {
            // Add each sheet as a card
            for (PatientSheet sheet : office.PatientSheets) {
                JPanel sheetCard = createSheetCard(sheet);
                mainPanel.add(sheetCard);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        medDialog.add(headerPanel, BorderLayout.NORTH);
        medDialog.add(scrollPane, BorderLayout.CENTER);
        medDialog.setVisible(true);
    }

    private void createNewSheet() {
        String[] patientNames = office.Clients.stream()
                .map(c -> c.FullName)
                .toArray(String[]::new);

        String selectedPatient = (String) JOptionPane.showInputDialog(
                this,
                "Select a patient:",
                "Patient Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                patientNames,
                patientNames[0]);

        if (selectedPatient == null) return;

        Client patient = office.Clients.stream()
                .filter(c -> c.FullName.equals(selectedPatient))
                .findFirst()
                .orElse(null);

        if (patient == null) return;

        // Create patient sheet dialog
        JDialog sheetDialog = new JDialog(this, "New Patient Sheet", true);
        sheetDialog.setLayout(new BorderLayout());
        sheetDialog.setSize(700, 600);
        sheetDialog.setLocationRelativeTo(this);

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 37, 41));
        headerPanel.setPreferredSize(new Dimension(700, 60));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

        JLabel headerLabel = new JLabel("New Medical Sheet - " + patient.FullName);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);

        // Create form fields
        JTextField txtWeight = new JTextField();
        JTextField txtHeight = new JTextField();
        JTextArea txtMedicalObs = new JTextArea();
        JTextArea txtSurgicalObs = new JTextArea();

        // Style text areas
        txtMedicalObs.setLineWrap(true);
        txtMedicalObs.setWrapStyleWord(true);
        txtSurgicalObs.setLineWrap(true);
        txtSurgicalObs.setWrapStyleWord(true);

        // Add form fields with labels
        addFormField(formPanel, "Weight (kg)", txtWeight, 20);
        addFormField(formPanel, "Height (cm)", txtHeight, 100);
        
        // Add text areas with labels
        JLabel lblMedical = new JLabel("Medical Observations");
        lblMedical.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMedical.setBounds(40, 180, 600, 25);
        formPanel.add(lblMedical);

        JScrollPane medicalScroll = new JScrollPane(txtMedicalObs);
        medicalScroll.setBounds(40, 210, 600, 100);
        formPanel.add(medicalScroll);

        JLabel lblSurgical = new JLabel("Surgical Observations");
        lblSurgical.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSurgical.setBounds(40, 320, 600, 25);
        formPanel.add(lblSurgical);

        JScrollPane surgicalScroll = new JScrollPane(txtSurgicalObs);
        surgicalScroll.setBounds(40, 350, 600, 100);
        formPanel.add(surgicalScroll);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnSave = createStyledButton("Save Sheet", 0, 0, 200, 40);
        JButton btnCancel = createStyledButton("Cancel", 0, 0, 120, 40);
        btnCancel.setBackground(new Color(108, 117, 125));

        btnSave.addActionListener(e -> {
            try {
                int weight = Integer.parseInt(txtWeight.getText());
                int height = Integer.parseInt(txtHeight.getText());
                
                PatientSheet sheet = new PatientSheet(
                    patient.FullName,
                    patient.PhoneNum,
                    txtMedicalObs.getText(),
                    txtSurgicalObs.getText(),
                    weight,
                    height
                );
                
                office.addPatientSheet(sheet);
                JOptionPane.showMessageDialog(sheetDialog, "Patient sheet saved successfully!");
                sheetDialog.dispose();
                managePatientSheet(); // Refresh the medical files view
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(sheetDialog, 
                    "Please enter valid numbers for weight and height.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> sheetDialog.dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // Add all panels to dialog
        sheetDialog.add(headerPanel, BorderLayout.NORTH);
        sheetDialog.add(new JScrollPane(formPanel), BorderLayout.CENTER);
        sheetDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size for form panel
        formPanel.setPreferredSize(new Dimension(680, 500));

        sheetDialog.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdvancedMedicalUI frame = new AdvancedMedicalUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
} 