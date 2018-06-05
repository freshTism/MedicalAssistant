package clinic;

import utility.Gender;
import utility.Insurance;
import utility.Listable;

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient implements Listable{

    private ArrayList<Patient> patients = new ArrayList<Patient>();

    private String name;
    private String fatherName;
    private int age;
    private int nationalNumber;
    private Gender gender;
    private Insurance insurance;
    private int insuranceCode;
    private LocalDate expirationDate;

    //Without insurance(free insurance)
    public Patient(String name, String fatherName, int age, int nationalNumber, Gender gender) {
        this.name = name;
        this.fatherName = fatherName;
        this.age = age;
        this.nationalNumber = nationalNumber;
        this.gender = gender;
        this.insurance = Insurance.AZAD;
    }

    //With insurance
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
    }


}
