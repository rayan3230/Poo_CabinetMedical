package Cabinet.Personnels;

import Cabinet.Management.MedFile;

public class Client {
    public String FullName;
    public String PhoneNum;
    public MedFile medicalRecord;

    public Client(String FullName, String PhoneNum) {
        this.FullName = FullName;
        this.PhoneNum = PhoneNum;
        this.medicalRecord = new MedFile(this);
    }

    public void addMedicalHistory(String history) {
        medicalRecord.addHistory(history);
    }

    public void addDisease(String disease) {
        medicalRecord.addDisease(disease);
    }

    public void addAllergy(String allergy) {
        medicalRecord.addAllergy(allergy);
    }
}
