package gui.controller;

import clinic.members.Role;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import exceptions.loginExceptions.DuplicateUsernameException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Manager;

import java.io.IOException;

public class registerUserController {

    ObservableList<String> roles = FXCollections.observableArrayList(Role.CLERK.getRole(), Role.DOCTOR.getRole());

    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXPasswordField passwordPF;
    @FXML
    private ChoiceBox<String> roleCB;
    @FXML
    private ImageView usernameIcon = new ImageView();
    @FXML
    private ImageView passwordIcon = new ImageView();
    @FXML
    private ImageView exitIcon = new ImageView();
    @FXML
    private ImageView jobIcon = new ImageView();
    @FXML
    private ImageView goBackIcon = new ImageView();
    @FXML
    private Label duplicateUsernameLabel;

    double x, y;

    @FXML
    private void exitProgram(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void registerUser(MouseEvent event) {
        String enteredUsername = usernameTF.getText();
        String enteredPassword = passwordPF.getText();

        Role enteredRole;
        if (roleCB.getValue().equals("منشی")) {
            enteredRole = Role.CLERK;
        } else {
            enteredRole = Role.DOCTOR;
        }

        try {
            Manager.registerNewUser(enteredRole, enteredUsername, enteredPassword);
        } catch (DuplicateUsernameException e) {
            duplicateUsernameLabel.setVisible(true);
        }
        //next page
    }

    @FXML
    void goToLoginPage(MouseEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("/gui/fxml/login.fxml"));
        Scene nextScene = new Scene(loginPage);
        Stage currentStage = (Stage) exitIcon.getScene().getWindow();
        currentStage.setScene(nextScene);
    }

    @FXML
    void moveStageOnMouseDragged(MouseEvent event) {
        exitIcon.getScene().getWindow().setX(event.getScreenX() + x);
        exitIcon.getScene().getWindow().setY(event.getScreenY() + y);
    }

    @FXML
    void moveStageOnMousePressed(MouseEvent event) {
        x = exitIcon.getScene().getWindow().getX() - event.getScreenX();
        y = exitIcon.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void initialize() {
        roleCB.setValue(Role.CLERK.getRole());
        roleCB.setItems(roles);

        ControllerManager.setIconImage(".\\images\\icons\\user.png", usernameIcon);
        ControllerManager.setIconImage(".\\images\\icons\\secure.png", passwordIcon);
        ControllerManager.setIconImage(".\\images\\icons\\cancel.png", exitIcon);
        ControllerManager.setIconImage(".\\images\\icons\\job.png", jobIcon);
        ControllerManager.setIconImage(".\\images\\icons\\goBack.png", goBackIcon);
    }

}