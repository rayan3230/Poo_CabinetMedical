package Cabinet.Management;

public class Bill {
    public String PatientName;
    public String OfficeName = "PooProject_Office";
    public String DoctorName;
    public int Price;
    public int AppId;
    public int[] MedId;

    public Bill(String PatientName, int Price, int AppId, int[] MedId) {
        this.PatientName = PatientName;
        this.AppId = AppId;
        this.MedId = MedId;
    }

    public Bill(String PatientName, int AppId, int[] MedId) {
        this.PatientName = PatientName;
        this.AppId = AppId;
        this.MedId = MedId;
    }

    public Bill() {

    }

    public int calculateBill(int appPrice, int MedPrice) {

        this.Price = appPrice + MedPrice;
        return (appPrice + MedPrice);
    }

}
