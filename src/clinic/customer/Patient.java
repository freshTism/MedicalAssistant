package clinic.customer;

import clinic.services.Drug;
import clinic.services.Visit;
import exceptions.drugException.DrugNotFoundException;
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

//    private ArrayList<Visit> visits = new ArrayList<>();

    public static Patient workingPatient;

    private Path path = Paths.get(".\\data\\patients");
//    private Path visitsPath;

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

//        BufferedReader visitBufferedReader = null;
//        String visitLine;

//        ArrayList<Drug> visitDrugs = new ArrayList<>();

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
                    bufferedReader = new BufferedReader(new FileReader(directory.getPath()
                            + "\\information.txt"));
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
                            Integer.parseInt(dataList.get(3)), Gender.searchWithName(dataList.get(4))));
                } else {
                    patients.add(new Patient(dataList.get(0), dataList.get(1), Integer.parseInt(dataList.get(2)),
                            Integer.parseInt(dataList.get(3)), Gender.searchWithName(dataList.get(4)),
                            Insurance.searchWithName(dataList.get(5)), Integer.parseInt(dataList.get(6)),
                            LocalDate.parse(dataList.get(7))));
                }
                dataList.clear();

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                Path patientVisitsFolderPath = Paths.get(directories.get(directory).getPath() + "\\visits");
//                File patientVisitsFolderFile = new File(patientVisitsFolderPath.toString());
//
//                File[] visitsFiles = patientVisitsFolderFile.listFiles();
//
//                ArrayList<File> visitsTextFiles = new ArrayList<>();
//
//                if (visitsFiles.length == 0) {
//                    System.out.println("The directory is empty.");
//                } else {
//                    //Filter and save directory
//                    for (File file : visitsFiles) {
//                        if (file.getName().endsWith(".txt")) {
//                            visitsTextFiles.add(file);
//                            try {
//                                visitBufferedReader = new BufferedReader(new FileReader(file.getPath()));
//                                while ((visitLine = visitBufferedReader.readLine()) != null) {
//                                    dataList.add(visitLine);
//                                }
//                            } catch (FileNotFoundException e) {
//                                System.out.println(e.getMessage());
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                System.out.println(e.getMessage());
//                                e.printStackTrace();
//                            }
//
//                            for (int drug = 4; drug < dataList.size(); drug++) {
//                                try {
//                                    visitDrugs.add(Drug.searchDrug(dataList.get(drug)));
//                                } catch (DrugNotFoundException e) {
//                                    System.out.println(e.getMessage());
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            patients.get(directory).visits.add(new Visit(dataList.get(0), dataList.get(1), visitDrugs,
//                                    Integer.parseInt(dataList.get(2)), LocalDate.parse(dataList.get(3))));
//
//                            dataList.clear();
//                            visitDrugs.clear();
//                            try {
//                                bufferedReader.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//                }
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
            path = Files.createDirectories(path.resolve(String.valueOf(nationalNumber)));
//            visitsPath = Files.createDirectories(path.resolve("visits"));
        } catch (IOException e) {
            System.out.println("Can not make a directory for Patient whit national number: " + nationalNumber + "\n");
            e.printStackTrace();
        }
    }
    //Create new "appendable" text file

    private void createInfoFile(String fileName, String exceptionMessage) {
        informationFile = new File(path + "\\" + fileName);
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
        createInfoFile("information.txt", fwExMessage + nationalNumber + "\n");
        if (insurance.equals(Insurance.AZAD)) {
            outFile.println(name);
            outFile.println(fatherName);
            outFile.println(age);
            outFile.println(nationalNumber);
            outFile.println(gender.getGender());
            outFile.println(insurance.getInsuranceName());
            outFile.println(" ");
        } else {
            outFile.println(name);
            outFile.println(fatherName);
            outFile.println(age);
            outFile.println(nationalNumber);
            outFile.println(gender.getGender());
            outFile.println(insurance.getInsuranceName());
            outFile.println(insuranceCode);
            outFile.println(expirationDate);
            outFile.println(" ");
        }
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
    public void updateInfo(String newName, String newFatherName, int newAge, int newNationalNumber, Gender newGender)
            throws IOException {
        int index = patients.indexOf(workingPatient);
        int previousNationalNumber = workingPatient.nationalNumber;

        patients.get(index).setName(newName);
        patients.get(index).setFatherName(newFatherName);
        patients.get(index).setAge(newAge);
        patients.get(index).setNationalNumber(newNationalNumber);
        patients.get(index).setInsurance(Insurance.AZAD);

        if (previousNationalNumber != newNationalNumber) {
            Path newPath = Paths.get(path.getParent() + "\\" + newNationalNumber);
            path = newPath;
            File newDir = new File(newPath.toString());
            informationFile.renameTo(newDir);
        }
        createDirectory();
        Files.delete(path.resolve("information.txt"));
        createInfoFile("information.txt", fwExMessage + nationalNumber + "\n");
        writeInfoToFile();
    }

    public void updateInfo(String newName, String newFatherName, int newAge, int newNationalNumber, Gender newGender,
                           Insurance newInsurance, int newInsuranceCode, LocalDate newExpirationDate) throws IOException {
        int index = patients.indexOf(workingPatient);
        int previousNationalNumber = workingPatient.nationalNumber;

        patients.get(index).setName(newName);
        patients.get(index).setFatherName(newFatherName);
        patients.get(index).setAge(newAge);
        patients.get(index).setNationalNumber(newNationalNumber);
        patients.get(index).setInsurance(newInsurance);
        patients.get(index).setInsuranceCode(newInsuranceCode);
        patients.get(index).setExpirationDate(newExpirationDate);

        if (previousNationalNumber != newNationalNumber) {
            Path newPath = Paths.get(path.getParent() + "\\" + newNationalNumber);
            path = newPath;
            File newDir = new File(path.getParent() + "\\" + newNationalNumber);
            informationFile.renameTo(newDir);
        }

        createDirectory();
        Files.delete(path.resolve("information.txt"));
        createInfoFile("information.txt", fwExMessage + nationalNumber + "\n");
        writeInfoToFile();
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
    public PrintWriter getOutFile() { return outFile; }

//    public Path getVisitsPath() { return visitsPath; }
//    public void setVisitsPath(Path visitsPath) { this.visitsPath = visitsPath; }
}
