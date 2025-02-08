package Cabinet.Management;

import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;

public class VisitDates {
    public String Date;
    public Client patient;
    public Doctor doctor; // doctor who log in is the Handler doctor
    public int duration;
    public String StartTime;
    public String EndTime;

    public VisitDates(String Date, Client patient, Doctor doctor) {
        this.Date = Date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public VisitDates(String Date, String StartTime, String EndTime, Client patient, Doctor doctor, int duration) {
        this.Date = Date;
        this.patient = patient;
        this.doctor = doctor;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.duration = duration;
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
