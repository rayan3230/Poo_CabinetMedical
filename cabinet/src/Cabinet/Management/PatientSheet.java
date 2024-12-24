package Cabinet.Management;

import Cabinet.Personnels.Client;

public class PatientSheet extends Client {
    public String MedicalObservations;
    public String SurgicalObservations;
    public int Weight;
    public int Height;

    public PatientSheet(String FullName, String PhoneNum, String MedicalObservations,
                        String SurgicalObservations, int Weight, int Height) {
        super(FullName, PhoneNum);
        this.MedicalObservations = MedicalObservations;
        this.SurgicalObservations = SurgicalObservations;
        this.Weight = Weight;
        this.Height = Height;
    }
}
