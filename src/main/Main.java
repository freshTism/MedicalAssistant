package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            AnchorPane loginPage = FXMLLoader.load(getClass().getResource("/gui/fxml/login.fxml"));
            Scene scene = new Scene(loginPage);
            stage.setTitle("Medical Assistant");
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(args); }
}
