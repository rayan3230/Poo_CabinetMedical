package Cabinet.Personnels;

import java.util.ArrayList;
import java.util.List;

public class Accounts {
    public List<Doctor> Doctors;
    List<Secretary> Secretaries;

    public Accounts() {
        Doctors = new ArrayList<>();
        Secretaries = new ArrayList<>();
        Doctor doc = new Doctor("rayan", "Cardio", "rayan@email.com", "123456789", "momo");
        Secretary sec = new Secretary("rayan", "m");
        AddSecAccount(sec);
        AddDocAccount(doc);
    }

    public boolean SearchDocAccount(String name, String password) {
        for (Doctor doctor : Doctors) {
            if (doctor.FullName.equals(name) && doctor.PassWord.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void AddDocAccount(Doctor doctor) {
        Doctors.add(doctor);
        System.out.print("the doc named " + doctor.FullName + " is added");
    }

    public boolean SearchSecAccount(String name, String password) {
        for (Secretary sec : Secretaries) {
            if (name.equals(sec.FullName) && password.equals(sec.PassWord)) {
                return true;
            }
        }
        return false;
    }

    public void AddSecAccount(Secretary secretary) {
        Secretaries.add(secretary);
        System.out.print("the doc named " + secretary.FullName + " is added");
    }

    public Doctor getDoctorByCredentials(String name, String password) {
        for (Doctor doctor : Doctors) {
            if (doctor.FullName.equals(name) && doctor.PassWord.equals(password)) {
                return doctor;
            }
        }
        return null;
    }

    public Secretary getSecretaryByCredentials(String name, String password) {
        for (Secretary sec : Secretaries) {
            if (sec.FullName.equals(name) && sec.PassWord.equals(password)) {
                return sec;
            }
        }
        return null;
    }
}
