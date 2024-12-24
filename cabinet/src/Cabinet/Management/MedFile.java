package Cabinet.Management;

import java.util.ArrayList;
import java.util.List;

import Cabinet.Personnels.Client;

public class MedFile {
    public Client Patient;

    private ArrayList<String> Allergies;
    private List<String> history;
    private List<String> diseases;
    public List<PatientSheet> Sheet;

    public MedFile(Client Patient) {
        this.Patient = Patient;
        Allergies = new ArrayList<>();
        diseases = new ArrayList<>();
        history = new ArrayList<>();
        Sheet = new ArrayList<>();
    }

    public void AddSheet(PatientSheet pSheet) {
        Sheet.add(pSheet);
    }

    public void addAllergy(String allergy) {
        Allergies.add(allergy);
    }

    public void addHistory(String record) {
        history.add(record);
    }

    public void addDisease(String disease) {
        diseases.add(disease);
    }
}
