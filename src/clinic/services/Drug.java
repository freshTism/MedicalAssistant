package clinic.services;

import exceptions.drugException.DrugNotFoundException;
import utility.Listable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Drug implements Listable {

    private static ArrayList<Drug> drugs = new ArrayList<>();

    public static Drug workingDrug;

    private String persianName;
    private String englishName;
    private int dosage;
    private String uses;
    private String suggestedSchedule;

    private Path path = Paths.get(".\\data\\drugs");

    private File informationFile;
    private FileWriter fileWriter;
    private String fwExMessage = "Can not create a FileWriter for Drug whit name: ";
    private BufferedWriter bufferedWriter;
    private PrintWriter outFile;

    //Read drugs information from their text file and add them to ArrayList
    static {
        Path folderPath = Paths.get(".\\data\\drugs");
        File folder = new File(folderPath.toString());

        File[] files = folder.listFiles();

        ArrayList<File> infoTextFiles = new ArrayList<>();
        BufferedReader bufferedReader = null;
        ArrayList<String> dataList = new ArrayList<>();
        String line;

        if (files.length == 0) {
            System.out.println("The directory is empty.");
        } else {
            //Filter and save text files
            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    infoTextFiles.add(file);
                }
            }

            for (File infoFile : infoTextFiles) {
                //Read drugs info
                try {
                    bufferedReader = new BufferedReader(new FileReader(infoFile.getPath()));
                    while ((line = bufferedReader.readLine()) != null) {
                        dataList.add(line);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

                //Add drug to ArrayList
                drugs.add(new Drug(dataList.get(0), dataList.get(1), Integer.parseInt(dataList.get(2)), dataList.get(3),
                        dataList.get(4)));
                dataList.clear();

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } //end static block

    public Drug(String persianName, String englishName, int dosage, String uses, String suggestedSchedule) {
        this.persianName = persianName;
        this.englishName = englishName;
        this.dosage = dosage;
        this.uses = uses;
        this.suggestedSchedule = suggestedSchedule;
    }

    //Create new "appendable" text file
    private void createInfoFile(String fileName, String exceptionMessage) {
        informationFile = new File(path + "\\" + persianName);
        try {
            fileWriter = new FileWriter(informationFile, true);
        } catch (IOException e) {
            System.out.println(exceptionMessage);
            e.printStackTrace();
        }
        bufferedWriter = new BufferedWriter(fileWriter);
        outFile = new PrintWriter(bufferedWriter);
    }

    //Write drugs information in file
    private void writeInfoToFile() {
        createInfoFile(persianName + ".txt", fwExMessage + persianName + "\n");

        outFile.println(persianName);
        outFile.println(englishName);
        outFile.println(dosage);
        outFile.println(uses);
        outFile.println(suggestedSchedule);

        outFile.close();
    }

    //Add a drug to ArrayList
    @Override
    public void addToList() {
        drugs.add(new Drug(persianName, englishName, dosage, uses, suggestedSchedule));
    }

    //Create a text file and save drug information to file and ArrayList
    public void saveInfo() {
        createInfoFile(persianName + ".txt", fwExMessage + persianName + "\n");
        writeInfoToFile();
        addToList();
    }

    public void updateInfo(String newPersianName, String newEnglishName, int newDosage, String newUses,
                           String newSsuggestedSchedule) throws IOException {
        int index = drugs.indexOf(workingDrug);
        String previousPersianName = workingDrug.persianName;

        drugs.get(index).setPersianName(newPersianName);
        drugs.get(index).setEnglishName(newEnglishName);
        drugs.get(index).setDosage(newDosage);
        drugs.get(index).setUses(newUses);
        drugs.get(index).setSuggestedSchedule(newSsuggestedSchedule);

        if (previousPersianName != newPersianName) {
            Path newPath = Paths.get(path.getParent()+ "\\" + newPersianName + ".txt");
            path = newPath;
            File newInfoFile = new File(path.getParent() + "\\" + newPersianName + ".txt");
            informationFile.renameTo(newInfoFile);
        }

        Files.delete(path.resolve(newPersianName + ".txt"));
        createInfoFile(newEnglishName + ".txt", fwExMessage + newPersianName + "\n");
        writeInfoToFile();
    }

    public static Drug searchDrug(String persianName) throws DrugNotFoundException {
        boolean find = false;
        Drug target = null;

        for (int i = 0; !find && i < drugs.size(); i++) {
            if (drugs.get(i).getPersianName().equals(persianName)) {
                target = drugs.get(i);
                find = true;
            }
        }
        if (target != null) {
            workingDrug = target;
            return target;
        } else {
            throw new DrugNotFoundException("Drug NOT Found!");
        }
    }

    @Override
    public String toString() {
        return persianName + "\n"
                + englishName + "\n"
                + dosage + "\n"
                + uses + "\n"
                + suggestedSchedule + "\n";
    }

    public String getPersianName() { return persianName; }
    public void setPersianName(String persianName) { this.persianName = persianName; }

    public String getEnglishName() { return englishName; }
    public void setEnglishName(String englishName) { this.englishName = englishName; }

    public int getDosage() { return dosage; }
    public void setDosage(int dosage) { this.dosage = dosage; }

    public String getUses() { return uses; }
    public void setUses(String uses) { this.uses = uses; }

    public String getSuggestedSchedule() { return suggestedSchedule; }
    public void setSuggestedSchedule(String suggestedSchedule) { this.suggestedSchedule = suggestedSchedule; }

    public static ArrayList<Drug> getDrugs() { return drugs; }
}
