package clinic.members;

import clinic.customer.Insurance;
import clinic.customer.Patient;
import utility.Gender;
import utility.Listable;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User implements Listable{

    private static ArrayList<User> users = new ArrayList<>();

    private String username;
    private String password;
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Register new patient without insurance(free insurance)
    public static void registerNewPatient(String name, String fatherName, int age, int nationalNumber, Gender gender) {
        new Patient(name, fatherName, age, nationalNumber, gender);
    }

    //Register new patient with insurance
    public static void registerNewPatient(String name, String fatherName, int age, int nationalNumber, Gender gender,
                                          Insurance insurance, int insuranceCode, LocalDate expirationDate) {
        new Patient(name, fatherName, age, nationalNumber, gender, insurance, insuranceCode, expirationDate);
    }

    public static void searchPatient() {



    }

    public static ArrayList<User> getUsers() { return users; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

}
