package clinic.members;

import clinic.customer.Insurance;
import clinic.customer.Patient;
import clinic.services.Drug;
import exceptions.patientExcetions.PatientNotFoundException;

import java.util.ArrayList;

public class Doctor extends User {

    public Doctor(String username, String password) {
        super(username, password);
        this.setRole(Role.DOCTOR);
    }

    @Override
    public String toString() {
        String result;
        result = "پزشک\n";
        result += username + "\n";
        result += password + "\n";
        return result;
    }

    private void writeInfoToFile() {
        //Reference the text file
        createInfoFile("infoList.txt", fwExMessage);
        outFile.println("پزشک");
        outFile.println(username);
        outFile.println(password);
        outFile.println(" ");
        outFile.close();
    }

    @Override
    public void saveInfo() {
        writeInfoToFile();
        addToList();
    }

    @Override
    public void addToList() {
        users.add(new Doctor(username, password));
    }

    public static ArrayList<Patient> searchWithAge(int age) throws PatientNotFoundException {
        ArrayList<Patient> result = new ArrayList<>();

        for (Patient patient : Patient.getPatients()) {
            if (patient.getAge() == age) {
                result.add(patient);
            }
        }

        if (result.size() == 0) {
            throw new PatientNotFoundException("There is no patient with this age!\n");
        } else {
            return result;
        }
    }

    public static ArrayList<Patient> searchWithInsurance(Insurance insurance) throws PatientNotFoundException {
        ArrayList<Patient> result = new ArrayList<>();

        for (Patient patient : Patient.getPatients()) {
            if (patient.getInsurance().equals(insurance)) {
                result.add(patient);
            }
        }

        if (result.size() == 0) {
            throw new PatientNotFoundException("There is no patient with this insurance!\n");
        } else {
            return result;
        }
    }

    public static void addDrug(String persianName, String englishName, int dosage, String uses, String suggestedSchedule) {
        Drug newDrug = new Drug(persianName, englishName, dosage, uses, suggestedSchedule);
        newDrug.saveInfo();
    }
}
