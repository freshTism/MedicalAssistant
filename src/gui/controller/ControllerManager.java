package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControllerManager {


    //Set icon image
    protected static Path iconPath;
    protected static File iconFile;
    protected static Image iconImage;

    protected static void setIconImage(String imagePath, ImageView iconImageView) {
        iconPath = Paths.get(imagePath);
        iconFile = new File(iconPath.toString());
        iconImage = new Image(iconFile.toURI().toString());
        iconImageView.setImage(iconImage);
    }

}
