package Cabinet.Management;

import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;

public class VisitDates {
    public String Date;
    public Client patient;
    public Doctor doctor; // doctor who log in is the Handler doctor

    public VisitDates(String Date, Client patient, Doctor doctor) {
        this.Date = Date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public VisitDates(Client patient, Doctor doctor, String Date) {
        this.Date = Date;
        this.patient = patient;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Date: " + Date + ", Patient: " + patient.FullName + ", Doctor: " + doctor.FullName + ", Phone: "
                + patient.PhoneNum;
    }
}
