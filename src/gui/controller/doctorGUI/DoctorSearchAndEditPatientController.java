package gui.controller.doctorGUI;

import clinic.members.User;
import com.jfoenix.controls.JFXTextField;
import exceptions.patientExcetions.PatientNotFoundException;
import gui.controller.ControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorSearchAndEditPatientController {

    @FXML
    private ImageView exitIcon = new ImageView();
    @FXML
    private Label roleLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private JFXTextField searchingNationalNumberTF;
    @FXML
    private Label patientNotFindLabel;

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
    private void searchPatient(MouseEvent event) throws IOException {
        patientNotFindLabel.setVisible(false);

        int searchingNationalNumber = Integer.parseInt(searchingNationalNumberTF.getText());

        try {
            User.searchPatient(searchingNationalNumber);
        } catch (PatientNotFoundException e) {
            patientNotFindLabel.setVisible(true);
            e.printStackTrace();
        }

        AnchorPane showAndEditPatientInformationPage = FXMLLoader.load(getClass()
                .getResource("/gui/fxml/doctorGUI/doctorShowAndEditPatient.fxml"));
        Scene newScene = new Scene(showAndEditPatientInformationPage);
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
    void initialize() {
        ControllerManager.setIconImage(".\\images\\icons\\cancel.png", exitIcon);

        roleLabel.setText(User.workingUser.getRole().getRole());
        usernameLabel.setText(User.workingUser.getUsername());
    }
}
