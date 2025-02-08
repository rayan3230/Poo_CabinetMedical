package Main_classes;

import java.util.Scanner;

import Cabinet.Management.Bill;
import Cabinet.Management.Medicines;
import Cabinet.Management.PatientSheet;
import Cabinet.Management.Prescription;
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

            loginSuccess = Account.SearchSecAccount(accountName, accountPassword);

        }

        return loginSuccess;
    }

    public static void main(String[] args) {

        boolean loggedIn = false;
        boolean addingIn = false;
        int loggedType;

        do {
            System.out.println("\n");
            System.out.println("------WELCOME-------------");
            System.out.println("\n");
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
                        System.out.println("\n");
                        System.out.println("You have successfully logged in, welcome to the terminal.");

                    } else {
                        System.out.println("\n");
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
                } while (!loggedIn && HaveAccount == 1);

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
                    System.out.println("\n");
                    System.out.println("your account has been added, please log in now ");
                    addingIn = true;

                    loggedIn = handleLogin("Doctor");

                    if (loggedIn) {
                        System.out.println("\n");
                        System.out.println("You have successfully logged in, welcome to the terminal.");

                    } else {
                        System.out.println("\n");
                        System.out.println("Something went wrong, please check your login details.");

                    }

                } while (!addingIn && !loggedIn);

            }

            if (HaveAccount == 1 && loggedType == 2) {

                System.out.println("LOGGING IN :------------------------------:");

                do {
                    loggedIn = handleLogin("Secretary");

                    if (loggedIn) {
                        System.out.println("\n");
                        System.out.println("You have successfully logged in, welcome to the terminal.");
                    } else {
                        System.out.println("\n");
                        System.out.println("Something went wrong, please check your login details.");

                        System.out.println("1- try again \n2- creat an account ");
                        int t = Scan.nextInt();

                        if (t == 2) {

                            HaveAccount = 2;

                        } else if (t != 2 && t != 1) {

                            System.out.println(
                                    "please enter the value 1 or 2 only and not " + t + "or anything else");
                        }
                    }
                } while (!loggedIn && HaveAccount == 1);

            }

            if (HaveAccount == 2 && loggedType == 2) {

                do {
                    System.out.print("name  : ");
                    String accountName = Scan.next();

                    System.out.print("passe word  : ");
                    String accountpassword = Scan.next();

                    Secretary Sec = new Secretary(accountName, accountpassword);

                    Account.AddSecAccount(Sec);
                    addingIn = true;
                    System.out.println("\n");
                    System.out.println("your account has been added, please log in now ");

                    loggedIn = handleLogin("Secretary");

                    if (loggedIn) {
                        System.out.println("\n");
                        System.out.println("You have successfully logged in, welcome to the terminal.");

                    } else {
                        System.out.println("\n");
                        System.out.println("Something went wrong, please check your login details.");

                    }

                } while (!addingIn && !loggedIn);

            }

            if ((HaveAccount != 1 && HaveAccount != 2) || (loggedType != 1 && loggedType != 2)) {
                System.out.println("please only enter the values 1 or 2 for both question and not anything else ");
            }

        } while (!loggedIn && !addingIn);

        if (loggedType == 1) { // Doctor account
            // ------------------------------------------------------------------------------------------

            if (loggedIn) {

                while (true) {
                    System.out.println("\n");
                    System.out.println("\nMenu:");
                    System.out.println("1. Add Patient");
                    System.out.println("2. Add Doctor");
                    System.out.println("3. Add Appointment");
                    System.out.println("4. Display Patients");
                    System.out.println("5. Display Doctors");
                    System.out.println("6. Display Appointments");
                    System.out.println("7. add sheet for the patient");
                    System.out.println("8. add medical file to patient");
                    System.out.println("9. display available sevices ");
                    System.out.println("10. display in stock medicines and drugs");
                    System.out.println("11. add medicines");
                    System.out.println("12. price of the appointement");
                    System.out.println("13. write save and print a prescription");
                    System.out.println("14. calculate bill ");
                    System.out.println("15. display bills");
                    System.out.println("16. exit");
                    System.out.println("\n");
                    System.out.print("Choose an option: ");

                    int number = Scan.nextInt();
                    System.out.println("\n");

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
                            office.displayServices();
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
                            System.out.println("\n");
                            Account.AddDocAccount(doctor);

                            break;

                        // ------------------------- ADD APPOINTEMENT ----------------------
                        case 3:
                            System.out.println("Choose a patient:");

                            for (int i = 0; i < office.Clients.size(); i++) {
                                System.out.println(i + 1 + ". " + office.Clients.get(i));// tjib lmrid
                            }

                            System.out.print("Enter patient number: ");
                            int patientIndex = Scan.nextInt() - 1;

                            System.out.println("choose the type of service you want to book for  :  ");
                            office.displayServices();

                            String AppointementService = Scan.next();

                            System.out.println("Choose a doctor:");

                            for (int i = 0; i < office.doctors.size(); i++) {

                                if (office.doctors.get(i).Profession.equals(AppointementService)) {
                                    System.out.println(i + 1 + ". " + office.doctors.get(i));// tjib les mdoc
                                }

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

                        case 9:

                            office.displayServices();

                            break;

                        case 10:

                            office.displayMedicines();

                            break;

                        case 11:
                            System.out.print("name  :  ");
                            String MedName = Scan.next();

                            System.out.print("Quantity  :  ");
                            int Quantity = Scan.nextInt();

                            System.out.print("price  :  ");
                            int Price = Scan.nextInt();

                            System.out.print("times a day  :  ");
                            int TimesPerDay = Scan.nextInt();

                            Medicines Med = new Medicines(MedName, Quantity, Price, TimesPerDay);

                            office.addMadicines(Med);

                            break;

                        case 12:
                            System.out.println("what is your appointement  :  ");
                            office.displayAppointments();
                            int AppNum = Scan.nextInt();

                            System.out.println("how long was your appointement  (in hours):  ");
                            int time = Scan.nextInt();

                            System.out.println("which doctor :  ");
                            office.displayDoctors();
                            int DocNum = Scan.nextInt();

                            System.out.println(
                                    office.Appointments.get(AppNum).CalculatePrice(office.doctors.get(DocNum), time));
                            break;

                        case 13:
                            boolean exit = false;
                            do {
                                System.out.println(
                                        "do you want to     1-print     or     2-write the print a prescription   3-exit");
                                int x = Scan.nextInt();

                                if (x == 1) {
                                    System.out.println("patient name  :  ");
                                    String PatientName = Scan.next();
                                    office.DisplayPrescription(PatientName);
                                } else if (x == 2) {

                                    System.out.println("//-------// Poo-Project Office //-----------------//");
                                    System.out.println(
                                            "------------- THE PRESCRIPTION THAT ALLOWS THE PATIENT TO BUY MEDECINES ----------------- ");

                                    System.out.print("I, doctor  :  ");
                                    String DocName = Scan.next();
                                    System.out.print("allows my patient  :  ");
                                    String PatientName = Scan.next();
                                    System.out.print("to buy the medicines (use _ for space)  :  ");
                                    String MedList = Scan.next();
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("                              signed : " + DocName);
                                    System.out.println(
                                            "//--------------------------------------------------------------------------------//");

                                    Prescription PresCript = new Prescription(DocName, PatientName, MedList);

                                    office.Prescription.add(PresCript);

                                } else if (x == 3) {
                                    exit = true;
                                } else {
                                    System.out.println("only enter the values 1, 2 or 3 please ");
                                }

                            } while (!exit);

                            break;

                        case 14:
                            Bill bill = new Bill();

                            System.out.print("patient name  :  ");
                            bill.PatientName = Scan.next();

                            System.out.println("what is your appointement num and price:  ");
                            System.out.print("price  :  ");
                            int AppPrice = Scan.nextInt();
                            System.out.print("id  :  ");
                            bill.AppId = Scan.nextInt();

                            System.out.print("how many meds do you have  :  ");
                            int n = Scan.nextInt();

                            System.out.println("What medicines do you have  :  ");
                            office.displayMedicines();

                            int MedPrice = 0;

                            for (int i = 0; i < n; i++) {

                                int Id = Scan.nextInt();
                                MedPrice += office.medicines.get(Id).Price;
                                bill.MedId[i] = Id;

                            }

                            System.out
                                    .println("the price of the bill is  :  " + bill.calculateBill(AppPrice, MedPrice));

                            office.bills.add(bill);

                            break;
                        case 15:
                            office.displayBill();
                            break;
                        case 16:
                            return;

                        default:
                            System.out.println("please only enter the number on the screen ");
                    }

                }
            }
        } else if (loggedType == 2) { // secretary account
            // ---------------------------------------------------------------------------------

            if (loggedIn) {

                while (true) {
                    System.out.println("\nMenu:");
                    System.out.println("1. Add Patient");
                    System.out.println("2. Add Appointment");
                    System.out.println("3. Display Patients");
                    System.out.println("4. Display Doctors");
                    System.out.println("5. Display Appointments");
                    System.out.println("6. display available sevices ");
                    System.out.println("7. display in stock medicines and drugs");
                    System.out.println("8. add medicines");
                    System.out.println("9. price of the appointement");
                    System.out.println("10. print a prescription");
                    System.out.println("11. calculate bill ");
                    System.out.println("12. display bills");
                    System.out.println("13. exit");
                    System.out.println("\n");
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

                            System.out.println("choose the type of service you want to book for  :  ");
                            office.displayServices();

                            String AppointementService = Scan.next();

                            System.out.println("Choose a doctor:");

                            for (int i = 0; i < office.doctors.size(); i++) {

                                if (office.doctors.get(i).Profession.equals(AppointementService)) {
                                    System.out.println(i + 1 + ". " + office.doctors.get(i));// tjib les mdoc
                                }

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

                            office.displayServices();

                            break;
                        case 7:

                            office.displayMedicines();

                            break;

                        case 8:

                            System.out.print("name  :  ");
                            String MedName = Scan.next();

                            System.out.print("Quantity  :  ");
                            int Quantity = Scan.nextInt();

                            System.out.print("price  :  ");
                            int Price = Scan.nextInt();

                            System.out.print("times a day  :  ");
                            int TimesPerDay = Scan.nextInt();

                            Medicines Med = new Medicines(MedName, Quantity, Price, TimesPerDay);

                            office.addMadicines(Med);

                            break;

                        case 9:

                            System.out.println("what is your appointement  :  ");
                            office.displayAppointments();
                            int AppNum = Scan.nextInt();

                            System.out.println("how long was your appointement  (in hours):  ");
                            int time = Scan.nextInt();

                            System.out.println("which doctor :  ");
                            office.displayDoctors();
                            int DocNum = Scan.nextInt();

                            System.out.println(
                                    office.Appointments.get(AppNum).CalculatePrice(office.doctors.get(DocNum), time));

                            break;

                        case 10:
                            System.out.println("patient name  :  ");
                            String PatientName = Scan.next();
                            office.DisplayPrescription(PatientName);

                            break;
                        case 11:
                            Bill bill = new Bill();

                            System.out.print("patient name  :  ");
                            bill.PatientName = Scan.next();

                            System.out.println("what is your appointement num and price:  ");
                            System.out.print("price  :  ");
                            int AppPrice = Scan.nextInt();
                            System.out.print("id  :  ");
                            bill.AppId = Scan.nextInt();

                            System.out.print("how many meds do you have  :  ");
                            int n = Scan.nextInt();

                            System.out.println("What medicines do you have  :  ");
                            office.displayMedicines();

                            int MedPrice = 0;

                            for (int i = 0; i < n; i++) {

                                int Id = Scan.nextInt();
                                MedPrice += office.medicines.get(Id).Price;
                                bill.MedId[i] = Id;

                            }

                            System.out
                                    .println("the price of the bill is  :  " + bill.calculateBill(AppPrice, MedPrice));

                            office.bills.add(bill);

                            break;
                        case 12:
                            office.displayBill();
                            break;
                        case 13:
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