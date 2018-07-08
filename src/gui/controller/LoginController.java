package gui.controller;

import clinic.members.Role;
import clinic.members.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import exceptions.loginExceptions.IncorrectPaswordException;
import exceptions.loginExceptions.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Manager;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXPasswordField passwordPF;
    @FXML
    private ImageView usernameIcon = new ImageView();
    @FXML
    private ImageView passwordIcon = new ImageView();
    @FXML
    private ImageView exitIcon = new ImageView();
    @FXML
    private Label invalidUsernameLable;
    @FXML
    private Label incorrectPassLable;

    double x, y;

    @FXML
    private void exitProgram(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void loginUser(MouseEvent event) throws IOException {
        String enteredUsername = usernameTF.getText();
        String enteredPassword = passwordPF.getText();
        User user;
        AnchorPane menuPage = null;
        try {
            user = Manager.login(enteredUsername,enteredPassword);
            if (user.getRole().equals(Role.CLERK)) {
                menuPage = FXMLLoader.load(getClass().getResource("/gui/fxml/clerkMenu.fxml"));
            } else if (user.getRole().equals(Role.DOCTOR)) {
                menuPage = FXMLLoader.load(getClass().getResource("/gui/fxml/doctorMenu.fxml"));
            }
        } catch (UserNotFoundException e) {
            incorrectPassLable.setVisible(false);
            invalidUsernameLable.setVisible(true);
        } catch (IncorrectPaswordException e) {
            invalidUsernameLable.setVisible(false);
            incorrectPassLable.setVisible(true);
        }

        Scene scene = new Scene(menuPage);
        Stage currentStage = (Stage) usernameTF.getScene().getWindow();
        currentStage.setScene(scene);
    }

    @FXML
    private void goRegisterUser(MouseEvent event) throws Exception {
        AnchorPane registerUserPage = FXMLLoader.load(getClass().getResource("/gui/fxml/registerUser.fxml"));
        Scene scene = new Scene(registerUserPage);
        Stage currentStage = (Stage) usernameTF.getScene().getWindow();
        currentStage.setScene(scene);
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
    private void initialize() {
        assert usernameTF != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordPF != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";

        ControllerManager.setIconImage(".\\images\\icons\\user.png", usernameIcon);
        ControllerManager.setIconImage(".\\images\\icons\\secure.png", passwordIcon);
        ControllerManager.setIconImage(".\\images\\icons\\cancel.png", exitIcon);
    }
}
