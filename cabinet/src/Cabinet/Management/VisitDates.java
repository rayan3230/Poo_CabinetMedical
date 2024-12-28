package Cabinet.Management;

import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;

public class VisitDates {
    public String Date;
    public Client patient;
    public Doctor doctor; // doctor who log in is the Handler doctor
    public int duration;

    public VisitDates(String Date, Client patient, Doctor doctor) {
        this.Date = Date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int CalculatePrice(Doctor Doc, int Duration) {
        return (Doc.PricePerHour * Duration);
    }

    @Override
    public String toString() {
        return "Date: " + Date + ", Patient: " + patient.FullName + ", Doctor: " + doctor.FullName + ", Phone: "
                + patient.PhoneNum;
    }
}
