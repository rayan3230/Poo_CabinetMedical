package Cabinet.Management;

import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;

public class VisitDates {
    public String date;
    public Client patient;
    public Doctor doctor; // doctor who log in is the Handler doctor

    public VisitDates(String date, Client patient, Doctor doctor) {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Patient: " + patient.FullName + ", Doctor: " + doctor.FullName + ", Phone: " + patient.PhoneNum;
    }
}
