package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.logging.Logger;

public class RegisterControl {

    private DbManager dbManager=new DbManager();

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
    private PasswordField passwordTxt;

    private byte[] imageBytes;

    private Logger logger= Logger.getLogger(this.getClass().getName());

    @FXML
    void chooseImage(MouseEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");


        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);

            imageBytes = Files.readAllBytes(file.toPath());
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

        String password = passwordTxt.getText();
        if (password == null || password.isEmpty()) {
            logger.warning("Password not entered");
            return;
        }



        LocalDate birthDate = birthDateTxt.getValue();

        if (birthDate == null) {
            logger.warning("Birthdate not selected");
            return;
        }

        if (imageBytes == null) {
            logger.warning("Image not selected");
            return;
        }

        String birthdate = birthDate.toString();

        dbManager.insertStudent(Integer.valueOf(rollTxt.getText()),nameTxt.getText(),emailTxt.getText(),addressTxt.getText(),departmentTxt.getText(),cgpaTxt.getText(),birthdate,imageBytes,password);

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("HomePage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Student Profile ");
        stage.setScene(scene);
        stage.show();
    }

}
