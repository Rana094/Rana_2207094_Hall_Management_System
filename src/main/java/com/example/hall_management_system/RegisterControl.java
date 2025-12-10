package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class RegisterControl {

    @FXML
    private TextArea addressTxt;

    @FXML
    private DatePicker birthDateTxt;

    @FXML
    private TextField cgpaTxt;

    @FXML
    private Button chooseImageBtn;

    @FXML
    private TextField departmentTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private Button gotoHomeBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private AnchorPane registerAnchorPane;

    @FXML
    private TextField rollTxt;

    @FXML
    private Button signupBtn;

    @FXML
    private ImageView imageView;

    @FXML
    void chooseImage(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");


        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }

    }

    @FXML
    void gotoHome(MouseEvent event) throws IOException {
        Stage stage=(Stage) gotoHomeBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("HomePage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Student Profile ");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void submitClcik(MouseEvent event) throws IOException {
        Stage stage=(Stage) signupBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("HomePage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Student Profile ");
        stage.setScene(scene);
        stage.show();

    }

}
