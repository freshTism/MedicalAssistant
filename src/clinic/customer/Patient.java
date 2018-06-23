package clinic.customer;

import utility.Gender;
import utility.Listable;
import utility.Utility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Patient implements Listable {

    private static ArrayList<Patient> patients = new ArrayList<>();

    private String name;
    private String fatherName;
    private int age;
    private int nationalNumber;
    private Gender gender;
    private Insurance insurance;
    private int insuranceCode;
    private LocalDate expirationDate;

    private Path path = Paths.get(".\\data\\patients");

    private File informationFile;
    private FileWriter fileWriter;
    private String fwExMessage = "Can not create a FileWriter for Patient whit national number: ";
    private BufferedWriter bufferedWriter;
    private PrintWriter outFile;

    //Constructor without insurance(free insurance)
    public Patient(String name, String fatherName, int age, int nationalNumber, Gender gender) {
        this.name = name;
        this.fatherName = fatherName;
        this.age = age;
        this.nationalNumber = nationalNumber;
        this.gender = gender;
        this.insurance = Insurance.AZAD;

        //Create a directory for this patient
        try {
            Files.createDirectories(path.resolve(String.valueOf(nationalNumber)));
        } catch (IOException e) {
            System.out.println("Can not make a directory for Patient whit national number: " + nationalNumber);
            e.printStackTrace();
        }

        //Create an information file for this patient
        Utility.createInfoFile(informationFile, path, "information.txt", fileWriter,
                fwExMessage + nationalNumber, bufferedWriter, outFile);

        //Write patients information in file
        outFile.print(toString());

        addToList();
    }

    //Constructor with insurance
    public Patient(String name, String fatherName, int age, int nationalNumber, Gender gender,
                   Insurance insurance, int insuranceCode, LocalDate expirationDate) {
        this.name = name;
        this.fatherName = fatherName;
        this.age = age;
        this.nationalNumber = nationalNumber;
        this.gender = gender;
        this.insurance = insurance;
        this.insuranceCode = insuranceCode;
        this.expirationDate = expirationDate;

        //Create a directory for this patient
        try {
            Files.createDirectories(path.resolve(String.valueOf(nationalNumber)));
        } catch (IOException e) {
            System.out.println("Can not make a directory for Patient whit national number: " + nationalNumber);
            e.printStackTrace();
        }

        //Create an information file for this patient
        Utility.createInfoFile(informationFile, path, "information.txt", fileWriter,
                fwExMessage + nationalNumber, bufferedWriter, outFile);

        //Write patients information in file
        outFile.print(toString());

        addToList();
    }

    //Add a patient to ArrayList
    @Override
    public void addToList() {
        patients.add(new Patient(this.name, this.fatherName, this.age, this.nationalNumber, this.gender,
                this.insurance, this.insuranceCode, this.expirationDate));
    }

    @Override
    public String toString() {
        return name + "\n"
                + fatherName + "\n"
                + age + "\n"
                + nationalNumber + "\n"
                + gender.getGender() + "\n"
                + insurance.getInsuranceName() + "\n"
                + insuranceCode + "\n"
                + expirationDate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getNationalNumber() { return nationalNumber; }
    public void setNationalNumber(int nationalNumber) { this.nationalNumber = nationalNumber; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Insurance getInsurance() { return insurance; }
    public void setInsurance(Insurance insurance) { this.insurance = insurance; }

    public int getInsuranceCode() { return insuranceCode; }
    public void setInsuranceCode(int insuranceCode) { this.insuranceCode = insuranceCode; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public static ArrayList<Patient> getPatients() { return patients; }
}
