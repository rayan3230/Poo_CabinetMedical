package Main_classes;

import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import java.util.Scanner;

public class Main {
    static Scanner Scan = new Scanner(System.in);
    static MedicalOffice office = new MedicalOffice();  // Managing office-related operations
    static Accounts accountManager = new Accounts();  // Managing accounts

    private static boolean handleLogin(String accountType) { // method for handle the type of the login
        System.out.print("Name: ");
        String accountName = Scan.next();  // Use nextLine for full input

        System.out.print("Password: ");
        String accountPassword = Scan.next();

        boolean loginSuccess = false;
        if (accountType.equals("Doctor")) {
            loginSuccess = accountManager.SearchDocAccount(accountName, accountPassword);
        } else if (accountType.equals("Secretary")) {
            Secretary tempSec = new Secretary(accountName, accountPassword);
            loginSuccess = accountManager.SearchSecAccount(accountName, accountPassword);
        }
        return loginSuccess;
    }

    public static void main(String[] args) {
        boolean accountCreated = false;

        System.out.println("ADDING ACCOUNT : -------------------- :");
        System.out.print("What type of account do you want 1-Doctor or 2-Secretary: ");
        int accountType = Scan.nextInt();

        // Account creation
        while (!accountCreated) {// loop for make sure user have at least one account 
            if (accountType == 1) {  //Creating Doctor account
                System.out.print("Name: ");
                
                String accountName = Scan.next();

                System.out.print("Password: ");
                String accountPassword = Scan.next();

                System.out.print("Specialization: ");
                String specialization = Scan.next();

                System.out.print("Email: ");
                String email = Scan.next();

                System.out.print("Phone number: ");
                String phone = Scan.next();

                Doctor doc = new Doctor(accountName, specialization, email, phone, accountPassword);
                accountManager.AddDocAccount(doc);  // Add to accounts list
                System.out.println("Your Doctor account has been added, please log in now.");
                accountCreated = true;

            } else if (accountType == 2) {  // Secretary account
                System.out.print("Name: ");
                String accountName = Scan.next();

                System.out.print("Password: ");
                String accountPassword = Scan.next();

                Secretary sec = new Secretary(accountName, accountPassword);
                accountManager.AddSecAccount(sec);
                System.out.println("Your Secretary account has been added, please log in now.");
                accountCreated = true;

            } else {
                System.out.println("Please enter the value 1 or 2 only.");
            }
        }

        // Login process
        System.out.println("LOGGING IN : ------------------------------ :");
        boolean loggedIn = false;

        System.out.print("What type of account do you have 1-Doctor or 2-Secretary: ");
        int loginType = Scan.nextInt();

        if (loginType == 1) {  // Doctor login
            do {
                loggedIn = handleLogin("Doctor");

                if (loggedIn) {
                    System.out.println("You have successfully logged in, welcome to the terminal.");
                } else {
                    System.out.println("Something went wrong, please check your login details.");
                }
            } while (!loggedIn);

            // Doctor-specific menu options
            while (true) {
                System.out.println("\nDoctor Menu:");
                System.out.println("1. Add Patient");
                System.out.println("2. Add Appointment");
                System.out.println("3. Display Patients");
                System.out.println("4. Display Doctors");
                System.out.println("5. Display Appointments");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int option = Scan.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Enter patient full name: ");
                        String fullName = Scan.next();

                        System.out.print("Enter patient phone number: ");
                        String phone = Scan.next();

                        Client patient = new Client(fullName, phone);
                        office.addClient(patient);
                        System.out.println("Patient added.");
                        break;

                    case 2:
                        System.out.println("Choose a patient:");
                        for (int i = 0; i < office.Clients.size(); i++) {
                            System.out.println((i + 1) + ". " + office.Clients.get(i).FullName);
                        }

                        System.out.print("Enter patient number: ");
                        int patientIndex = Scan.nextInt() - 1;

                        System.out.println("Choose a doctor:");
                        for (int i = 0; i < office.doctors.size(); i++) {
                            System.out.println((i + 1) + ". " + office.doctors.get(i).FullName);
                        }

                        System.out.print("Enter doctor number: ");
                        int doctorIndex = Scan.nextInt() - 1;

                        System.out.print("Enter appointment date (yyyy-MM-dd): ");
                        String date = Scan.next();

                        VisitDates appointment = new VisitDates(
                                office.Clients.get(patientIndex),
                                office.doctors.get(doctorIndex),
                                date
                        );
                        office.addAppointment(appointment);
                        System.out.println("Appointment added.");
                        break;

                    case 3:
                        office.displayClients();
                        break;

                    case 4:
                        office.displayDoctors();
                        break;

                    case 5:
                        office.displayAppointments();
                        break;

                    case 6:
                        return;  // Exit
                    default:
                        System.out.println("Please only enter a valid number.");
                }
            }

        } else if (loginType == 2) {  // Secretary login
            do {
                loggedIn = handleLogin("Secretary");

                if (loggedIn) {
                    System.out.println("Welcome, Secretary!");
                } else {
                    System.out.println("Invalid login credentials.");
                }
            } while (!loggedIn);

            // Implement Secretary-specific menu here if needed
        } else {
            System.out.println("Please enter the value 1 or 2 only.");
        }

        Scan.close();
    }
}
