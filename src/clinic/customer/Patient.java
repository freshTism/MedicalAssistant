package clinic.customer;

import utility.Gender;
import utility.Listable;

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

    //Read patients information from their text file and add them to ArrayList
    static {
        Path folderPath = Paths.get(".\\data\\patients");
        File folder = new File(folderPath.toString());

        File[] files = folder.listFiles();

        ArrayList<File> directories = new ArrayList<>();
        BufferedReader bufferedReader = null;
        ArrayList<String> dataList = new ArrayList<>();
        String line;

        if (files.length == 0) {
            System.out.println("The directory is empty.");
        } else {
            //Filter and save directories
            for (File file : files) {
                if (file.isDirectory()) {
                    directories.add(file);
                }
            }

            for (File directory : directories) {
                //Read Patients info
                try {
                    bufferedReader = new BufferedReader(new FileReader(directory.getPath() + "\\information.txt"));
                    while ((line = bufferedReader.readLine()) != null) {
                        dataList.add(line);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }

                //Add patient to ArrayList
                if (dataList.get(5).equals("آزاد")) {
                    patients.add(new Patient(dataList.get(0), dataList.get(1), Integer.parseInt(dataList.get(2)),
                            Integer.parseInt(dataList.get(3)), Gender.valueOf(dataList.get(4))));
                } else {
                    patients.add(new Patient(dataList.get(0), dataList.get(1), Integer.parseInt(dataList.get(2)),
                            Integer.parseInt(dataList.get(3)), Gender.valueOf(dataList.get(4)),
                            Insurance.valueOf(dataList.get(5)), Integer.parseInt(dataList.get(6)),
                            LocalDate.parse(dataList.get(7))));
                }
                dataList.clear();
            }
        }
    } //end static block

    //Constructor without insurance(free insurance)
    public Patient(String name, String fatherName, int age, int nationalNumber, Gender gender) {
        this.name = name;
        this.fatherName = fatherName;
        this.age = age;
        this.nationalNumber = nationalNumber;
        this.gender = gender;
        this.insurance = Insurance.AZAD;
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
    }

    //Create a directory for this patient
    private void createDirectory() {
        try {
            Files.createDirectories(path.resolve(String.valueOf(nationalNumber)));
        } catch (IOException e) {
            System.out.println("Can not make a directory for Patient whit national number: " + nationalNumber + "\n");
            e.printStackTrace();
        }
    }

    //Create new "appendable" text file
    private void createInfoFile(String fileName, String exceptionMessage) {
        informationFile = new File(path + fileName);
        try {
            fileWriter = new FileWriter(informationFile, true);
        } catch (IOException e) {
            System.out.println(exceptionMessage);
            e.printStackTrace();
        }
        bufferedWriter = new BufferedWriter(fileWriter);
        outFile = new PrintWriter(bufferedWriter);
    }

    //Write patients information in file
    private void writeInfoToFile() {
        outFile.print(toString());
        outFile.close();
    }

    //Add a patient to ArrayList
    @Override
    public void addToList() {
        patients.add(new Patient(this.name, this.fatherName, this.age, this.nationalNumber, this.gender,
                this.insurance, this.insuranceCode, this.expirationDate));
    }

    //Create a text file and save patient info to file and ArrayList
    public void saveInfo() {
        createDirectory();
        createInfoFile("information.txt", fwExMessage + nationalNumber + "\n");
        writeInfoToFile();
        addToList();
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
                + expirationDate + "\n";
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
