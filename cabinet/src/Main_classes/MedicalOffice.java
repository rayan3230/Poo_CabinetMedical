package Main_classes;

import Cabinet.Management.Bill;
import Cabinet.Management.Medicines;
import Cabinet.Management.PatientSheet;
import Cabinet.Management.Prescription;
import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import java.util.ArrayList;
import java.util.List;

public class MedicalOffice {
    public List<Client> Clients;
    public List<VisitDates> Appointments;
    public List<Doctor> doctors;

    public String[] Services = { "cardiologist", "physical therapist", "dentist", "veterinarian", "radilogic",
            "audiologist" };
    public int NumServices = 6;

    public List<Medicines> medicines;
    public List<Bill> bills;
    public List<Prescription> Prescription;
    public ArrayList<PatientSheet> PatientSheets = new ArrayList<>();

    public MedicalOffice() {
        Clients = new ArrayList<>();
        Appointments = new ArrayList<>();
        doctors = new ArrayList<>();
        medicines = new ArrayList<>();
        bills = new ArrayList<>();
        Prescription = new ArrayList<>();

        Doctor doc1 = new Doctor("rayan", "Cardio", "rayan@email.com", "123456789", "momo");
        Doctor doc2 = new Doctor("fahd", "Dentist", "mohamed@email.com", "987654321", "momo");
        addDoctor(doc2);
        addDoctor(doc1);

        Client client1 = new Client("Mouzali Rayane", "055030545");
        Client client2 = new Client("Fahd Djedi", "0465847854");
        Client client3 = new Client("Stambouli Eliesse", "04656378");
        Client client4 = new Client("Benazza Mehdi", "0588353455");
        Client client5 = new Client("Wassim Mouhouche", "0567890456");

        addClient(client1);
        addClient(client2);
        addClient(client3);
        addClient(client4);
        addClient(client5);

        VisitDates app1 = new VisitDates("2021-05-12", "10:00", "11:00", client1, doc2, 1);
        VisitDates app2 = new VisitDates("2021-05-12", "11:00", "12:00", client2, doc1, 1);
        VisitDates app3 = new VisitDates("2021-05-12", "12:00", "13:00", client3, doc2, 1);
        VisitDates app4 = new VisitDates("2021-05-12", "13:00", "14:00", client4, doc1, 1);

        addAppointment(app1);
        addAppointment(app2);
        addAppointment(app3);
        addAppointment(app4);

        Medicines med1 = new Medicines("Doliprane", 10, 50, 3);
        Medicines med2 = new Medicines("Aspirine", 20, 30, 2);
        Medicines med3 = new Medicines("Paracetamol", 15, 40, 2);
        Medicines med4 = new Medicines("Ibuprofen", 25, 60, 3);

        addMadicines(med1);
        addMadicines(med2);
        addMadicines(med3);
        addMadicines(med4);

        Bill bill1 = new Bill("Mouzali Rayane", 1, new int[] { 1, 2 });
        Bill bill2 = new Bill("Fahd Djedi", 2, new int[] { 3, 4 });
        Bill bill3 = new Bill("Stambouli Eliesse", 3, new int[] { 1, 3 });
        Bill bill4 = new Bill("Benazza Mehdi", 4, new int[] { 2, 4 });

        bill1.calculateBill(app1.CalculatePrice(doc2, app1.duration), 80);
        bill2.calculateBill(app2.CalculatePrice(doc1, app2.duration), 100);
        bill3.calculateBill(app3.CalculatePrice(doc2, app3.duration), 120);
        bill4.calculateBill(app4.CalculatePrice(doc1, app4.duration), 140);

        addBill(bill1);
        addBill(bill2);
        addBill(bill3);
        addBill(bill4);

        Prescription pre1 = new Prescription("rayan", "Mouzali_Rayane", "Doliprane_Aspirine_Paracetamol");
        Prescription pre2 = new Prescription("fahd", "Fahd_Djedi", "Paracetamol_Ibuprofen");
        Prescription pre3 = new Prescription("rayan", "Stambouli_Eliesse", "Doliprane_Paracetamol");
        Prescription pre4 = new Prescription("fahd", "Benazza_Mehdi", "Aspirine_Ibuprofen");

        addPreScript(pre1);
        addPreScript(pre2);
        addPreScript(pre3);
        addPreScript(pre4);

    }

    public void addClient(Client client) {
        Clients.add(client);
    }

    public void addAppointment(VisitDates appointment) {
        Appointments.add(appointment);
        System.out.println("Appointment added: " + appointment);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void displayClients() {
        if (Clients.isEmpty()) {
            System.out.println("No clients in the database.");
        } else {
            for (int i = 0; i < Clients.size(); i++) {
                System.out.println((i + 1) + "- " + Clients.get(i).FullName + ", Phone: " + Clients.get(i).PhoneNum);
            }
        }
    }

    public void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors in the database.");
        } else {
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println(
                        (i + 1) + "- " + doctors.get(i).FullName + ", Profession: " + doctors.get(i).Profession +
                                ", Email: " + doctors.get(i).Mail + ", Phone: " + doctors.get(i).PhoneNum);
            }
        }
    }

    public void displayAppointments() {
        if (Appointments.isEmpty()) {
            System.out.println("No appointments in the database.");
        } else {
            for (int i = 0; i < Appointments.size(); i++) {
                System.out.println((i + 1) + "- " + Appointments.get(i).toString());
            }
        }
    }

    public void displayServices() {
        for (int i = 0; i < NumServices; i++) {
            System.out.println((i + 1) + "- " + Services[i]);
        }
    }

    public void addMadicines(Medicines Med) {
        medicines.add(Med);
    }

    public void displayMedicines() {
        if (medicines.isEmpty()) {
            System.out.println("no medicines in stock");
        } else {
            for (int i = 0; i < medicines.size(); i++) {
                System.out.println("name  : " + medicines.get(i).Name + "    in stock  :  " + medicines.get(i).Quantity
                        + "    price  :  " + medicines.get(i).Price + "     take " + medicines.get(i).TimesPerDay
                        + "a day ");
            }
        }
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void displayBill() {
        if (bills.isEmpty()) {
            System.out.println("no medicines in stock");
        } else {
            for (int i = 0; i < bills.size(); i++) {
                System.out.println("patient  :  " + bills.get(i).PatientName + "    for appointement number  :  "
                        + bills.get(i).AppId + "     price  :  " + bills.get(i).Price);
            }
        }
    }

    public void addPreScript(Prescription PreScript) {
        Prescription.add(PreScript);
    }

    public void DisplayPrescription(String PatientName) {
        if (Prescription.isEmpty()) {
            System.out.println("there are no prescription in the system");
        } else {
            for (int i = 0; i < Prescription.size(); i++) {
                if (Prescription.get(i).PatientName.equals(PatientName)) {

                    System.out.println("//-------// Poo-Project Office //-----------------//");
                    System.out.println(
                            "------------- THE PRESCRIPTION THAT ALLOWS THE PATIENT TO BUY MEDECINES ----------------- ");

                    System.out.print("I, doctor  :  " + Prescription.get(i).DocName);
                    System.out.print("allows my patient  :  " + Prescription.get(i).PatientName);
                    System.out.print("to buy the medicines (use _ for space)  :  " + Prescription.get(i).MedList);
                    System.out.println("");
                    System.out.println("");
                    System.out.println("                              signed : " + Prescription.get(i).DocName);
                    System.out.println(
                            "//--------------------------------------------------------------------------------//");

                }
            }

        }

    }

    public void addPatientSheet(PatientSheet sheet) {
        PatientSheets.add(sheet);
    }
}
