package Cabinet.Personnels;

public class Secretary {
    public String FullName;
    public String Mail;
    public int PhoneNum;
    public String PassWord;

    public Secretary(String FullName, String Mail, int PhoneNum) {
        this.FullName = FullName;
        this.Mail = Mail;
        this.PhoneNum = PhoneNum;
    }

    public Secretary(String FullName, String PassWord) {
        this.FullName = FullName;
        this.PassWord = PassWord;
    }
}
