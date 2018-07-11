package gui.controller.doctorGUI;

import clinic.members.User;
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

public class DoctorVisitListAndAddNewController {

    @FXML
    private ImageView exitIcon = new ImageView();
    @FXML
    private Label roleLabel;
    @FXML
    private Label usernameLabel;

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
