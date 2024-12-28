package Main_classes;

import java.util.Scanner;

import Cabinet.Management.PatientSheet;
import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;

public class MainFonction {

    static Scanner Scan = new Scanner(System.in);
    static MedicalOffice office = new MedicalOffice(); // Managing office-related operations
    static Accounts Account = new Accounts(); // Managing accounts

    private static boolean handleLogin(String accountType) { // method for handle the type of the login
        System.out.print("Name: ");
        String accountName = Scan.next(); // Use nextLine for full input

        System.out.print("Password: ");
        String accountPassword = Scan.next();

        boolean loginSuccess = false;
        if (accountType.equals("Doctor")) {

            loginSuccess = Account.SearchDocAccount(accountName, accountPassword);

        } else if (accountType.equals("Secretary")) {

            Secretary tempSec = new Secretary(accountName, accountPassword);
            loginSuccess = Account.SearchSecAccount(accountName, accountPassword);
        }

        return loginSuccess;
    }

    public static void main(String[] args) {

        boolean loggedIn = false;
        boolean addingIn = false;
        int loggedType;

        do {

            System.out.print("what type of account do you have 1-doctor or 2-secretary  : ");
            loggedType = Scan.nextInt();

            System.out.println(
                    "do you have an account in this cabinet already ? : \n 1- yes, and would like to log in \n 2- no, add account");

            int HaveAccount = Scan.nextInt();

            if (HaveAccount == 1 && loggedType == 1) {
                System.out.println("LOGGING IN :------------------------------:");

                do {
                    loggedIn = handleLogin("Doctor");

                    if (loggedIn) {
                        System.out.println("You have successfully logged in, welcome to the terminal.");

                    } else {
                        System.out.println("Something went wrong, please check your login details.");

                        System.out.println("1- try again \n2- creat an account ");
                        int t = Scan.nextInt();

                        if (t == 2) {

                            HaveAccount = 2;

                            System.out.println("account added, log in plz : ");

                        } else if (t != 2 && t != 1) {

                            System.out.println(
                                    "please enter the value 1 or 2 only and not " + t + "or anything else");
                        }
                    }
                } while (!loggedIn || HaveAccount == 1);

            }

            if (HaveAccount == 2 && loggedType == 1) {
                System.out.println("ADDING ACCOUNT : -------------------- :");

                do {
                    System.out.print("name  : ");
                    String accountName = Scan.next();

                    System.out.print("passe word  : ");
                    String accountpassword = Scan.next();

                    Doctor Doc = new Doctor(accountName, accountpassword);

                    Account.AddDocAccount(Doc);

                    System.out.println("your account has been added, please log in now ");
                    addingIn = true;

                    Account.AddDocAccount(Doc);
                } while (!addingIn);

            }

            if (HaveAccount == 1 && loggedType == 2) {

                System.out.println("LOGGING IN :------------------------------:");

                do {
                    loggedIn = handleLogin("Secretary");

                    if (loggedIn) {
                        System.out.println("Welcome, Secretary!");
                    } else {
                        System.out.println("Something went wrong, please check your login details.");

                        System.out.println("1- try again \n2- creat an account ");
                        int t = Scan.nextInt();

                        if (t == 2) {

                            HaveAccount = 2;

                            System.out.println("account added, log in plz : ");

                        } else if (t != 2 && t != 1) {

                            System.out.println(
                                    "please enter the value 1 or 2 only and not " + t + "or anything else");
                        }
                    }
                } while (!loggedIn || HaveAccount == 1);

            }

            if (HaveAccount == 2 && loggedType == 2) {

                do {
                    System.out.print("name  : ");
                    String accountName = Scan.next();

                    System.out.print("passe word  : ");
                    String accountpassword = Scan.next();

                    addingIn = Account.SearchSecAccount(accountName, accountpassword);

                    if (addingIn) {
                        System.out.println("you have been successfuly loged in, welcome to the terminal ");
                    } else {
                        System.out.println(
                                "something went wrong, please check your loging infos or creat an account if you dont have one");
                    }
                } while (addingIn == false);

            }

            if ((HaveAccount != 1 || HaveAccount != 2) && (loggedType != 1 || loggedType != 2)) {
                System.out.println("please only enter the values 1 or 2 for both question and not anything else ");
            }

        } while (!loggedIn || !addingIn);

        if (loggedType == 1) { // Doctor account
            // ------------------------------------------------------------------------------------------

            if (loggedIn) {

                while (true) {
                    System.out.println("\nMenu:");
                    System.out.println("1. Add Patient");
                    System.out.println("2. Add Doctor");
                    System.out.println("3. Add Appointment");
                    System.out.println("4. Display Patients");
                    System.out.println("5. Display Doctors");
                    System.out.println("6. Display Appointments");
                    System.out.println("7. add sheet for the patient");
                    System.out.println("8. add medical file to patient");
                    System.out.println("");
                    System.out.println("9. exit");
                    System.out.print("Choose an option: ");

                    int number = Scan.nextInt();

                    switch (number) {
                        // ----------------------- ADD PATIENT TO TERMINAL----------------------
                        case 1:
                            System.out.print("Enter patient full name: ");
                            String FullName = Scan.next();

                            System.out.print("Enter patient phone number: ");
                            String pPhone = Scan.next();

                            Client patient = new Client(FullName, pPhone);

                            office.addClient(patient);
                            System.out.println("Patient added.");

                            break;

                        // ------------------------- ADD DOCTOR TO TERMINAL----------------------
                        case 2:
                            System.out.print("Enter doctor full name (comma-separated): ");
                            String dFullName = Scan.next();

                            System.out.print("Enter doctor specialization: ");
                            String specialization = Scan.next();

                            System.out.print("Enter doctor e-mail : ");
                            String dMail = Scan.next();

                            System.out.print("Enter doctor phone number: ");
                            String dPhone = Scan.next();

                            System.out.println("enter the doctors passe word  :  ");
                            String dpasseword = Scan.next();

                            Doctor doctor = new Doctor(dFullName, specialization, dMail, dPhone, dpasseword);

                            office.addDoctor(doctor);
                            System.out.println("Doctor added.");

                            break;

                        // ------------------------- ADD APPOINTEMENT ----------------------
                        case 3:
                            System.out.println("Choose a patient:");

                            for (int i = 0; i < office.Clients.size(); i++) {
                                System.out.println(i + 1 + ". " + office.Clients.get(i));// tjib lmrid
                            }

                            System.out.print("Enter patient number: ");
                            int patientIndex = Scan.nextInt() - 1;

                            System.out.println("Choose a doctor:");

                            for (int i = 0; i < office.doctors.size(); i++) {
                                System.out.println(i + 1 + ". " + office.doctors.get(i));// tjib les mdoc
                            }

                            System.out.print("Enter doctor number: ");
                            int doctorIndex = Scan.nextInt() - 1;

                            System.out.print("Enter appointment date (yyyy-MM-dd): ");
                            String Date = Scan.next();

                            VisitDates appointment = new VisitDates(Date, office.Clients.get(patientIndex),
                                    office.doctors.get(doctorIndex));

                            office.addAppointment(appointment);
                            System.out.println("Appointment added.");

                            break;
                        // ------------------------- DESPLAY PATIENT ----------------------
                        case 4:
                            System.out.println("\nPatients:");
                            office.displayClients();

                            break;
                        // ------------------------- DISPLAY DOCTORS----------------------
                        case 5:
                            System.out.println("\nDoctors:");
                            office.displayDoctors();

                            break;
                        // ------------------------- DISPLAY APPOINTEMENT----------------------
                        case 6:
                            System.out.println("\nAppointments:");
                            office.displayAppointments();

                            break;
                        // ---------------------ADD SHEET TO PATIENTS RECORD ----------------------
                        case 7:
                            String MedicalObservations = "none this appointement";
                            String ChirurgicalObservations = "none this appointement";

                            System.out.println("Choose a patient:");

                            for (int i = 0; i < office.Clients.size(); i++) {
                                System.out.println(i + 1 + ". " + office.Clients.get(i));// tjib lmrid
                            }

                            System.out.print("Enter patient number: ");
                            int patientIndex2 = Scan.nextInt() - 1;

                            System.out.print(
                                    "What Medical Observation Do you Want To Recall (1- none 2-i do have )  : ");
                            int HaveObservation = Scan.nextInt();
                            if (HaveObservation == 2) {
                                System.out.print("Medical Observation  : ");
                                MedicalObservations = Scan.next();
                            }

                            System.out.print(
                                    "What Chirurgical Observation Do you Want To Recall (1- none 2-i do have )  : ");
                            HaveObservation = Scan.nextInt();
                            if (HaveObservation == 2) {
                                System.out.print("Chirurgical Observation  : ");
                                ChirurgicalObservations = Scan.next();
                            }

                            System.out.print("weight  : ");
                            int Weight = Scan.nextInt();

                            System.out.print("height  : ");
                            int Hight = Scan.nextInt();

                            PatientSheet Sheet = new PatientSheet(office.Clients.get(patientIndex2).FullName,
                                    office.Clients.get(patientIndex2).PhoneNum,
                                    MedicalObservations, ChirurgicalObservations, Weight, Hight);

                            office.Clients.get(patientIndex2).medicalRecord.AddSheet(Sheet);
                            break;
                        // -------------- ADD MEDICAL RECORD TO PATIENT -----------------------
                        case 8:

                            System.out.println("Choose a patient:");

                            for (int i = 0; i < office.Clients.size(); i++) {
                                System.out.println(i + 1 + ". " + office.Clients.get(i));// tjib lmrid
                            }

                            System.out.print("Enter patient number: ");
                            int patientIndex3 = Scan.nextInt() - 1;

                            System.out.print("Enter medical history (!??add , to seperate dieases): ");
                            String[] histories = Scan.next().split(",");

                            for (String history : histories) {
                                office.Clients.get(patientIndex3).addMedicalHistory(history.trim());
                            }

                            System.out.print("Enter dieases (comma-separated): ");
                            String[] dieases = Scan.next().split(",");// to separt btw diesese

                            for (String allergy : dieases) {
                                office.Clients.get(patientIndex3).addDisease(allergy.trim());
                            }

                            System.out.print("Enter allergies (comma-separated): ");
                            String[] Allergies = Scan.next().split(",");// to separt btw diesese

                            for (String allergy : Allergies) {
                                office.Clients.get(patientIndex3).addAllergy(allergy.trim());
                            }
                            break;
                        // ----------------EXIT---------------------
                        case 9:
                            return;

                        default:
                            System.out.println("please only enter the number on the screen ");
                    }

                }
            }
        } else if (loggedType == 2) { // secretary account
            // ---------------------------------------------------------------------------------
            do {
                loggedIn = handleLogin("Secretary");

                if (loggedIn) {
                    System.out.println("Welcome, Secretary!");
                } else {
                    System.out.println("Invalid login credentials.");
                }
            } while (!loggedIn);

            if (loggedIn) {

                while (true) {
                    System.out.println("\nMenu:");
                    System.out.println("1. Add Patient");
                    System.out.println("2. Add Appointment");
                    System.out.println("3. Display Patients");
                    System.out.println("4. Display Doctors");
                    System.out.println("5. Display Appointments");
                    System.out.println("6. exit");
                    System.out.print("Choose an option: ");

                    int number = Scan.nextInt();

                    switch (number) {
                        // ----------------------- ADD PATIENT TO TERMINAL----------------------
                        case 1:
                            System.out.print("Enter patient full name: ");
                            String FullName = Scan.next();

                            System.out.print("Enter patient phone number: ");
                            String pPhone = Scan.next();

                            Client patient = new Client(FullName, pPhone);

                            office.addClient(patient);
                            System.out.println("Patient added.");

                            break;

                        // ------------------------- ADD APPOINTEMENT----------------------
                        case 2:
                            System.out.println("Choose a patient:");

                            for (int i = 0; i < office.Clients.size(); i++) {
                                System.out.println(i + 1 + ". " + office.Clients.get(i));// tjib lmrid
                            }

                            System.out.print("Enter patient number: ");
                            int patientIndex = Scan.nextInt() - 1;

                            System.out.println("Choose a doctor:");

                            for (int i = 0; i < office.doctors.size(); i++) {
                                System.out.println(i + 1 + ". " + office.doctors.get(i));// tjib les mdoc
                            }

                            System.out.print("Enter doctor number: ");
                            int doctorIndex = Scan.nextInt() - 1;

                            System.out.print("Enter appointment date (yyyy-MM-dd): ");
                            String Date = Scan.next();

                            VisitDates appointment = new VisitDates(Date, office.Clients.get(patientIndex),
                                    office.doctors.get(doctorIndex));

                            office.addAppointment(appointment);
                            System.out.println("Appointment added.");

                            break;
                        // ------------------------- DESPLAY PATIENT ----------------------
                        case 3:
                            System.out.println("\nPatients:");
                            office.displayClients();

                            break;
                        // ------------------------- DISPLAY DOCTORS----------------------
                        case 4:
                            System.out.println("\nDoctors:");
                            office.displayDoctors();

                            break;
                        // ------------------------- DISPLAY APPOINTEMENT----------------------
                        case 5:
                            System.out.println("\nAppointments:");
                            office.displayAppointments();

                            break;
                        // ---------------------ADD SHEET TO PATIENTS RECORD ----------------------
                        case 6:
                            System.out.print("Enter doctor full name (comma-separated): ");
                            String dFullName = Scan.next();

                            System.out.print("Enter doctor specialization: ");
                            String specialization = Scan.next();

                            System.out.print("Enter doctor e-mail : ");
                            String dMail = Scan.next();

                            System.out.print("Enter doctor phone number: ");
                            String dPhone = Scan.next();

                            System.out.println("enter the doctors passe word  :  ");
                            String dpasseword = Scan.next();

                            Doctor doctor = new Doctor(dFullName, specialization, dMail, dPhone, dpasseword);

                            office.addDoctor(doctor);
                            System.out.println("Doctor added.");
                            break;

                        case 7:

                            return;

                        default:
                            System.out.println("please only enter the number on the screen ");
                    }
                    Scan.close();

                }
            }

        }
        Scan.close();
    }
}