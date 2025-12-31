package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class UpdateInfoController {

    private final Logger logger = Logger.getLogger(UpdateInfoController.class.getName());

    @FXML
    private TextArea addressTxt;

    @FXML
    private DatePicker birthDateTxt;

    @FXML
    private TextField cgpaTxt;

    @FXML
    private TextField departmentTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField nameTxt;

    @FXML
    private PasswordField passwordTxt;
    @FXML
    private PasswordField confirmpasswordTxt;

    @FXML
    private Button updateBtn;

    private Student student;
    private byte[] imageBytes;
    private final DbManager dbManager = new DbManager();


    public void setStudent(Student student) {
        this.student = student;
        preloadData();
    }

    private void preloadData() {

        nameTxt.setText(student.getName());
        emailTxt.setText(student.getEmail());
        addressTxt.setText(student.getAddress());
        departmentTxt.setText(student.getDept());
        cgpaTxt.setText(student.getCgpa());

        if (student.getBirthdate() != null) {
            birthDateTxt.setValue(LocalDate.parse(student.getBirthdate()));
        }

        imageBytes = student.getImage();
        if (imageBytes != null) {
            imageView.setImage(new Image(new ByteArrayInputStream(imageBytes)));
        }
    }

    @FXML
    void chooseImage(MouseEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Image");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File file = chooser.showOpenDialog(null);
        if (file != null) {
            try {
                imageBytes = Files.readAllBytes(file.toPath());
                imageView.setImage(new Image(file.toURI().toString()));
            } catch (IOException e) {
                logger.severe(e.getMessage());
            }
        }
    }


    @FXML
    void updateInfo(MouseEvent event) throws SQLException, IOException {

        if (student == null) return;

        String name = nameTxt.getText();
        String email = emailTxt.getText();
        String address = addressTxt.getText();
        String dept = departmentTxt.getText();
        String cgpa = cgpaTxt.getText();

        LocalDate birthDate = birthDateTxt.getValue();
        if (birthDate == null) {
            showAlert("Please select birthdate");
            return;
        }

        String newPassword = passwordTxt.getText();
        String confirmPassword = confirmpasswordTxt.getText();


        String passwordToSave;

        if (newPassword.isEmpty() && confirmPassword.isEmpty()) {
            passwordToSave = student.getPassword();
        }
        else {
            if (!newPassword.equals(confirmPassword)) {
                showAlert("Passwords do not match");
                return;
            }
            passwordToSave = newPassword;
        }

        dbManager.updateStudent(student.getRoll(), name, email, address, dept, cgpa, birthDate.toString(), imageBytes, passwordToSave);

//        Stage stage=(Stage) updateBtn.getScene().getWindow();
//        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("AdminHomePage.fxml"));
//        Scene scene =new Scene (fxmlLoader.load());
//
//        AdminHomePageController controller = fxmlLoader.getController();
//        controller.loadPage("ViewAllStudents.fxml");
//
//        stage.setTitle("Admin Home Page");
//        stage.setScene(scene);
//        stage.show();
        AppContext.adminHome.loadPage("ViewAllStudents.fxml");

    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
//        Stage stage=(Stage) updateBtn.getScene().getWindow();
//        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("AdminHomePage.fxml"));
//        Scene scene =new Scene (fxmlLoader.load());
//
//        AdminHomePageController controller = fxmlLoader.getController();
//        controller.loadPage("ViewAllStudents.fxml");
//
//        stage.setTitle("Admin Home Page");
//        stage.setScene(scene);
//        stage.show();
        AppContext.adminHome.loadPage("ViewAllStudents.fxml");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
