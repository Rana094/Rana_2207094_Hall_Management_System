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

    @FXML
    private PasswordField confirmpasswordTxt;

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

        if (rollTxt.getText().isEmpty() ||
                nameTxt.getText().isEmpty() ||
                emailTxt.getText().isEmpty() ||
                addressTxt.getText().isEmpty() ||
                departmentTxt.getText().isEmpty() ||
                cgpaTxt.getText().isEmpty() ||
                passwordTxt.getText().isEmpty() ||
                confirmpasswordTxt.getText().isEmpty() ||
                birthDateTxt.getValue() == null ||
                imageBytes == null) {

            showAlert(Alert.AlertType.WARNING, "All fields are required");
            return;
        }

        int roll;

        try {
            roll = Integer.parseInt(rollTxt.getText());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Roll must be a number");
            return;
        }
        if (dbManager.rollExists(roll)) {
            showAlert(Alert.AlertType.ERROR, "Roll already exists");
            return;
        }

        String password = passwordTxt.getText();
        String confirmPassword = confirmpasswordTxt.getText();

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Passwords do not match");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION, "Registration successful!");

        Stage stage=(Stage) signupBtn.getScene().getWindow();

        LocalDate birthDate = birthDateTxt.getValue();

        String birthdate = birthDate.toString();

        dbManager.insertStudent(Integer.valueOf(rollTxt.getText()),nameTxt.getText(),emailTxt.getText(),addressTxt.getText(),departmentTxt.getText(),cgpaTxt.getText(),birthdate,imageBytes,password);

        dbManager.insertStudentStatus(Integer.valueOf(rollTxt.getText()),"false","false");

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("HomePage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Student Profile ");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }


}
