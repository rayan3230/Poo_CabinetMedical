package Cabinet.Personnels;

import java.util.Scanner;

public class Doctor {
    public String FullName;
    public String Profession;
    public String Mail;
    public String PhoneNum;
    public String PassWord;
    public int PricePerHour;

    public Doctor(String FullName, String Profession, String Mail, String PhoneNum, String PassWord) {
        this.FullName = FullName;
        this.Profession = Profession;
        this.Mail = Mail;
        this.PhoneNum = PhoneNum;
        this.PassWord = PassWord;
    }

    public Doctor(String FullName, String PassWord) {
        this.FullName = FullName;
        this.PassWord = PassWord;
    }

    public Doctor(String FullName, int PricePerHour) {
        this.FullName = FullName;
        this.PricePerHour = PricePerHour;
    }

    public boolean[] Schedule() {
        boolean[] schedule = new boolean[7];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Doctor, please indicate your availability (yes/no) for each day (1=Monday, 7=Sunday):");
        for (int i = 0; i < 7; i++) {
            System.out.print((i + 1) + ")- ");
            String response = scanner.next().toLowerCase();
            if ("yes".equals(response)) {
                schedule[i] = true;
            } else if ("no".equals(response)) {
                schedule[i] = false;
            } else {
                System.out.println("Please respond with 'yes' or 'no'.");
                i--; // Repeat the input
            }
        }
        scanner.close();
        return schedule;
    }
}
