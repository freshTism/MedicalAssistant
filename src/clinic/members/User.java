package clinic.members;

import clinic.customer.Insurance;
import clinic.customer.Patient;
import utility.Gender;
import utility.Listable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User implements Listable {

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

    protected abstract void saveInfo();

    //Create and reference a list of users information in a text file
    static {
        //Reference the text file
        createInfoFile("infoList.txt", fwExMessage);

        BufferedReader bufferedReader = null;
        String[] userInfo = new String[3];
        String line;

        //Add clerk ad doctors to ArrayList
        try {
            bufferedReader = new BufferedReader(new FileReader(path + "\\infoList.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                userInfo[0] = line;
                userInfo[1] = bufferedReader.readLine();
                userInfo[2] = bufferedReader.readLine();
                bufferedReader.readLine();
                if (userInfo[0].equals("منشی")) {
                    users.add(new Clerk(userInfo[1], userInfo[2]));
                } else if (userInfo[0].equals("پزشک")) {
                    users.add(new Doctor(userInfo[1], userInfo[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private static void createInfoFile(String fileName, String exceptionMessage) {
        infoFile = new File(path + fileName);
        try {
            fileWriter = new FileWriter(infoFile, true);
        } catch (IOException e) {
            System.out.println(exceptionMessage);
            e.printStackTrace();
        }
        bufferedWriter = new BufferedWriter(fileWriter);
        outFile = new PrintWriter(bufferedWriter);
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
