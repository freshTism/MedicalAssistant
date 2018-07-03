package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Medical Assistant");
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        showMainView();
    }

    private void showMainView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(gui.Login);
    }

    public static void main(String[] args) { launch(args); }
}
