package clinic.services;

import utility.Listable;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class Visit implements Listable{

    private String nosography;
    private String doctorsDiagnosis;
    private ArrayList<Drug> prescribe = new ArrayList<>();
    private int visitCost;
    private LocalDate visitDate;

    public static Visit workingVisit;

    public Visit(String nosography, String doctorsDiagnosis, ArrayList<Drug> prescribe, int visitCost, LocalDate visitDate) {
        this.nosography = nosography;
        this.doctorsDiagnosis = doctorsDiagnosis;
        this.prescribe = prescribe;
        this.visitCost = visitCost;
        this.visitDate = visitDate;
    }

    //    private Path path

    @Override
    public void addToList() {

    }
}
