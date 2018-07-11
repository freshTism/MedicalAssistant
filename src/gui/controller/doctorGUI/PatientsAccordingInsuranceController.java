package gui.controller.doctorGUI;

import clinic.customer.Insurance;
import clinic.customer.Patient;
import clinic.members.Doctor;
import clinic.members.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import exceptions.patientExcetions.PatientNotFoundException;
import gui.controller.ControllerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Gender;

import java.io.IOException;
import java.util.ArrayList;

public class PatientsAccordingInsuranceController {

    ObservableList<String> insurances = FXCollections.observableArrayList(Insurance.AZAD.getInsuranceName(),
            Insurance.ALBORZ.getInsuranceName(), Insurance.ARMAN.getInsuranceName(), Insurance.ASIA.getInsuranceName(),
            Insurance.BAHMAN.getInsuranceName(), Insurance.DANA.getInsuranceName(), Insurance.DEY.getInsuranceName(),
            Insurance.HAFEZ.getInsuranceName(), Insurance.IRAN.getInsuranceName(), Insurance.KARAFARIN.getInsuranceName(),
            Insurance.KOSAR.getInsuranceName(), Insurance.MA.getInsuranceName(), Insurance.MELLAT.getInsuranceName(),
            Insurance.MIHAN.getInsuranceName(), Insurance.MOALLEM.getInsuranceName(), Insurance.NOVIN.getInsuranceName(),
            Insurance.OMID.getInsuranceName(), Insurance.PARSIAN.getInsuranceName(), Insurance.PASARGAD.getInsuranceName(),
            Insurance.RAZI.getInsuranceName(), Insurance.SAMAN.getInsuranceName(), Insurance.SARMAD.getInsuranceName(),
            Insurance.SINA.getInsuranceName(), Insurance.TAAVON.getInsuranceName());

    @FXML
    private ImageView exitIcon = new ImageView();
    @FXML
    private Label roleLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label patientNotFoundLabel;
    @FXML
    private TableView<Patient> resultPatientsTableView;
    @FXML
    private TableColumn<Patient, Insurance> insuranceColumn;
    @FXML
    private TableColumn<Patient, Gender> genderColumn;
    @FXML
    private TableColumn<Patient, Integer> nationalNumberColumn;
    @FXML
    private TableColumn<Patient, Integer> ageColumn;
    @FXML
    private TableColumn<Patient, String> fatherNameColumn;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    @FXML
    private JFXComboBox<String> searchingInsuranceCB;

    private double x, y;

    @FXML
    private void exitProgram(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void moveStageOnMouseDragged(MouseEvent event) {
        exitIcon.getScene().getWindow().setX(event.getScreenX() + x);
        exitIcon.getScene().getWindow().setY(event.getScreenY() + y);
    }

    @FXML
    private void moveStageOnMousePressed(MouseEvent event) {
        x = exitIcon.getScene().getWindow().getX() - event.getScreenX();
        y = exitIcon.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    private void goRegisterPatient(MouseEvent event) throws IOException {
        AnchorPane registerPatientPage = FXMLLoader.load(getClass().getResource("/gui/fxml/doctorGUI/doctorMenu.fxml"));
        Scene newScene = new Scene(registerPatientPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(newScene);
    }

    @FXML
    private void goEditAndSearchPatient(MouseEvent event) throws IOException {
        AnchorPane editAndSearchPatientPage = FXMLLoader.load(getClass().getResource("/gui/fxml/doctorGUI/doctorSearchAndEditPatient.fxml"));
        Scene newScene = new Scene(editAndSearchPatientPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(newScene);
    }

    @FXML
    private void goSearchAndVisitPatient(MouseEvent event) throws IOException {
        AnchorPane searchAndVisitPatientPage = FXMLLoader.load(getClass().getResource("/gui/fxml/doctorGUI/doctorSearchAndVisitPatient.fxml"));
        Scene newScene = new Scene(searchAndVisitPatientPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(newScene);
    }

    @FXML
    private void goDrugList(MouseEvent event) throws IOException {
        AnchorPane drugListPage = FXMLLoader.load(getClass().getResource("/gui/fxml/doctorGUI/drugList.fxml"));
        Scene newScene = new Scene(drugListPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(newScene);
    }

    @FXML
    private void searchPatient(MouseEvent event) {

        patientNotFoundLabel.setVisible(false);
        resultPatientsTableView.setVisible(false);

        ArrayList<Patient> result = null;
        String enteredInsurance = searchingInsuranceCB.getValue();

        try {
            result = Doctor.searchWithInsurance(Insurance.searchWithName(enteredInsurance));
        } catch (PatientNotFoundException e) {
            patientNotFoundLabel.setVisible(true);
        }

        ObservableList<Patient> resultPatients = FXCollections.observableArrayList();
        for (Patient patient : result) {
            resultPatients.add(patient);
        }

        resultPatientsTableView.setVisible(true);
        resultPatientsTableView.setItems(resultPatients);
    }

    @FXML
    private void initialize() {
        ControllerManager.setIconImage(".\\images\\icons\\cancel.png", exitIcon);

        roleLabel.setText(User.workingUser.getRole().getRole());
        usernameLabel.setText(User.workingUser.getUsername());

        nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        fatherNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("fatherName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        nationalNumberColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("nationalNumber"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Patient, Gender>("gender"));
        insuranceColumn.setCellValueFactory(new PropertyValueFactory<Patient, Insurance>("insurance"));

        searchingInsuranceCB.setValue(Insurance.AZAD.getInsuranceName());
        searchingInsuranceCB.setItems(insurances);
    }
}
