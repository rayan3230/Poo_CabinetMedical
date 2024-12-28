package Main_classes;

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

    public MedicalOffice() {
        Clients = new ArrayList<>();
        Appointments = new ArrayList<>();
        doctors = new ArrayList<>();
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
            System.out.println(Services[i]);
        }
    }
}
