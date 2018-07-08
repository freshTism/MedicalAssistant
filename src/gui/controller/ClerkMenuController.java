package gui.controller;

import clinic.customer.Insurance;
import clinic.members.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.Gender;
import utility.Utility;

import java.io.IOException;
import java.time.LocalDate;

public class ClerkMenuController {

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
    private Label successfulRegisterPatientLabel;
    @FXML
    private Label unsuccessfulRegisterPatientLabel;
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
    private void goEditAndSearchPatient(MouseEvent event) throws IOException {
        AnchorPane editAndSearchPatientPage = FXMLLoader.load(getClass().getResource("/gui/fxml/editAndSearchPatient.fxml"));
        Scene newScene = new Scene(editAndSearchPatientPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(newScene);
    }


    @FXML
    private void goSearchAndVisitPatient(MouseEvent event) throws IOException {
        AnchorPane searchAndVisitPatientPage = FXMLLoader.load(getClass().getResource("/gui/fxml/searchAndVisitPatient.fxml"));
        Scene newScene = new Scene(searchAndVisitPatientPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(newScene);
    }


    @FXML
    private void registerPatient(MouseEvent event) {
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

        successfulRegisterPatientLabel.setVisible(false);
        patientInformationIncompleteLabel.setVisible(false);
        unsuccessfulRegisterPatientLabel.setVisible(false);

        if (!isInsuranceFree) {
            String enteredInsuranceCode = insuranceCodeTF.getText();
            LocalDate enteredExpirationDate = expirationDateDP.getValue();

                if (Utility.isParametersNull(enteredName, enteredFatherName, enteredAge, enteredNationalNumber,
                        enteredInsurance, enteredInsuranceCode) || enteredExpirationDate == null) {
                    patientInformationIncompleteLabel.setVisible(true);
                } else {
                    User.registerNewPatient(enteredName, enteredFatherName, Integer.parseInt(enteredAge),
                            Integer.parseInt(enteredNationalNumber), enteredGender,
                            Insurance.searchWithName(enteredInsurance), Integer.parseInt(enteredInsuranceCode),
                            enteredExpirationDate);
                            successfulRegisterPatientLabel.setVisible(true);
                }
        } else {
            if (Utility.isParametersNull(enteredName, enteredFatherName, enteredAge, enteredNationalNumber)) {
                patientInformationIncompleteLabel.setVisible(true);
            } else {
                User.registerNewPatient(enteredName, enteredFatherName, Integer.parseInt(enteredAge),
                        Integer.parseInt(enteredNationalNumber), enteredGender);
                successfulRegisterPatientLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void insuranceCBChoice() {
        if (insuranceCB.getValue().equals(Insurance.AZAD.getInsuranceName())) {
            insuranceCodeTF.setDisable(true);
            expirationDateDP.setDisable(true);
        } else {
            insuranceCodeTF.setDisable(false);
            expirationDateDP.setDisable(false);
            isInsuranceFree = false;
        }
    }

    @FXML
    void initialize() {
        ControllerManager.setIconImage(".\\images\\icons\\cancel.png", exitIcon);

        roleLabel.setText(User.roledLogedIn.getRole());
        usernameLabel.setText(User.usernameLogedIn);

        insuranceCB.setValue(Insurance.AZAD.getInsuranceName());
        insuranceCB.setItems(insurances);
    }
}
