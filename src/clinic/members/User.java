package clinic.members;

import clinic.customer.Insurance;
import clinic.customer.Patient;
import utility.Gender;
import utility.Listable;
import utility.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User implements Listable{

    private static ArrayList<User> users = new ArrayList<>();

    private String username;
    private String password;
    private Role role;

    private static Path path = Paths.get(".\\data\\users");

    private static File infoFile;
    private static FileWriter fileWriter;
    private static String fwExMessage = "Can not create a FileWriter for Users.\n";
    private static BufferedWriter bufferedWriter;
    private static PrintWriter outFile;

    //Create and reference a list of users information in a text file
    static {
        Utility.createInfoFile(infoFile, path, "infoList.txt", fileWriter, fwExMessage, bufferedWriter, outFile);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Register new patient without insurance(free insurance)
    public static void registerNewPatient(String name, String fatherName, int age, int nationalNumber, Gender gender) {
        Patient newPatient = new Patient(name, fatherName, age, nationalNumber, gender);
        newPatient.saveInfo();
    }

    //Register new patient with insurance
    public static void registerNewPatient(String name, String fatherName, int age, int nationalNumber, Gender gender,
                                          Insurance insurance, int insuranceCode, LocalDate expirationDate) {
        Patient newPatient = new Patient(name, fatherName, age, nationalNumber, gender, insurance, insuranceCode, expirationDate);
        newPatient.saveInfo();
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
