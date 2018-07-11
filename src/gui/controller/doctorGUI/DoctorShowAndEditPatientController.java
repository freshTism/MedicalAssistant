package gui.controller.doctorGUI;

import clinic.customer.Insurance;
import clinic.customer.Patient;
import clinic.members.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import gui.controller.ControllerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Gender;
import utility.Utility;

import java.io.IOException;
import java.time.LocalDate;

public class DoctorShowAndEditPatientController {

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
    private JFXTextField nameTF;
    @FXML
    private JFXTextField fatherNameTF;
    @FXML
    private JFXComboBox<String> insuranceCB;
    @FXML
    private JFXTextField ageTF;
    @FXML
    private JFXTextField nationalNumberTF;
    @FXML
    private JFXTextField insuranceCodeTF;
    @FXML
    private JFXRadioButton femaleRB;
    @FXML
    private ToggleGroup genderTG;
    @FXML
    private JFXRadioButton maleRB;
    @FXML
    private DatePicker expirationDateDP;
    @FXML
    private Label successfulEditPatientInformationLabel;
    @FXML
    private Label unsuccessfulEditPatientInformationLabel;
    @FXML
    private Label patientInformationIncompleteLabel;

    private double x, y;

    private boolean isInsuranceFree = true;

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
    private void goReports(MouseEvent event) throws IOException {
        AnchorPane reportSearchPage = FXMLLoader.load(getClass().getResource("/gui/fxml/doctorGUI/reportSearch.fxml"));
        Scene newScene = new Scene(reportSearchPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(newScene);
    }

    @FXML
    private void editPatientInformation(MouseEvent event) throws IOException {
        String enteredName = nameTF.getText();
        String enteredFatherName = fatherNameTF.getText();
        String enteredAge = ageTF.getText();
        String enteredNationalNumber = nationalNumberTF.getText();
        Gender enteredGender;
        if (genderTG.getSelectedToggle().equals(maleRB)) {
            enteredGender = Gender.MALE;
        } else {
            enteredGender = Gender.FEMALE;
        }
        String enteredInsurance = insuranceCB.getValue();

        successfulEditPatientInformationLabel.setVisible(false);
        patientInformationIncompleteLabel.setVisible(false);
        unsuccessfulEditPatientInformationLabel.setVisible(false);

        if (!isInsuranceFree) {
            String enteredInsuranceCode = insuranceCodeTF.getText();
            LocalDate enteredExpirationDate = expirationDateDP.getValue();

            if (Utility.isParametersNull(enteredName, enteredFatherName, enteredAge, enteredNationalNumber,
                    enteredInsurance, enteredInsuranceCode) || enteredExpirationDate == null) {
                patientInformationIncompleteLabel.setVisible(true);
            } else {
                Patient.workingPatient.updateInfo(enteredName, enteredFatherName, Integer.parseInt(enteredAge),
                        Integer.parseInt(enteredNationalNumber), enteredGender,
                        Insurance.searchWithName(enteredInsurance), Integer.parseInt(enteredInsuranceCode),
                        enteredExpirationDate);
                successfulEditPatientInformationLabel.setVisible(true);
            }
        } else {
            if (Utility.isParametersNull(enteredName, enteredFatherName, enteredAge, enteredNationalNumber)) {
                patientInformationIncompleteLabel.setVisible(true);
            } else {
                Patient.workingPatient.updateInfo(enteredName, enteredFatherName, Integer.parseInt(enteredAge),
                        Integer.parseInt(enteredNationalNumber), enteredGender);
                successfulEditPatientInformationLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void insuranceCBChoice() {
        if (insuranceCB.getValue().equals(Insurance.AZAD.getInsuranceName())) {
            insuranceCodeTF.setDisable(true);
            expirationDateDP.setDisable(true);
            isInsuranceFree = true;
        } else {
            insuranceCodeTF.setDisable(false);
            expirationDateDP.setDisable(false);
            isInsuranceFree = false;
        }
    }

    @FXML
    void initialize() {
        ControllerManager.setIconImage(".\\images\\icons\\cancel.png", exitIcon);

        roleLabel.setText(User.workingUser.getRole().getRole());
        usernameLabel.setText(User.workingUser.getUsername());

        insuranceCB.setValue(Insurance.AZAD.getInsuranceName());
        insuranceCB.setItems(insurances);

        nameTF.setText(Patient.workingPatient.getName());
        fatherNameTF.setText(Patient.workingPatient.getFatherName());
        ageTF.setText(Integer.toString(Patient.workingPatient.getAge()));
        nationalNumberTF.setText(Integer.toString(Patient.workingPatient.getNationalNumber()));
        if (Patient.workingPatient.getGender().equals(Gender.MALE)) {
            genderTG.selectToggle(maleRB);
        }
        insuranceCB.setValue(Patient.workingPatient.getInsurance().getInsuranceName());
        if (!Patient.workingPatient.getInsurance().equals(Insurance.AZAD)) {
            insuranceCodeTF.setDisable(false);
            expirationDateDP.setDisable(false);
            isInsuranceFree = false;
            insuranceCodeTF.setText(Integer.toString(Patient.workingPatient.getInsuranceCode()));
            expirationDateDP.setValue(Patient.workingPatient.getExpirationDate());
        }
    }
}
