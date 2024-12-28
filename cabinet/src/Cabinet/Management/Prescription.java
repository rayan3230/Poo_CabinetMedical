package Cabinet.Management;

public class Prescription {
    public String DocName;
    public String PatientName;
    public String MedList;

    public Prescription(String DocName, String PatientName, String MedList) {
        this.DocName = DocName;
        this.PatientName = PatientName;
        this.MedList = MedList;
    }

}
