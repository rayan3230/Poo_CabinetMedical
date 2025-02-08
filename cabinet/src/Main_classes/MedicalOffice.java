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

        Doctor doc = new Doctor("rayan", "Cardio", "rayan@email.com", "123456789", "momo");
        addDoctor(doc);
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
            System.out.println("there qre no prescription in the system");
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
