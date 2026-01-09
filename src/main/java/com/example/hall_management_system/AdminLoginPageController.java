package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginPageController {

    @FXML
    private Button homeBtn;

    @FXML
    private Button loginBtnAdmin;

    @FXML
    private TextField passwordTxt;

    @FXML
    private PasswordField passwordTxtAdmin;

    @FXML
    private TextField usernameTxtAdmin;

    @FXML
    private CheckBox checkBox;

    @FXML
    void changevisibility(MouseEvent event)
    {
        if(checkBox.isSelected())
        {
            passwordTxt.setText(passwordTxtAdmin.getText());
            passwordTxt.setVisible(true);
            passwordTxtAdmin.setVisible(false);
            return;
        }
        passwordTxtAdmin.setText(passwordTxt.getText());
        passwordTxtAdmin.setVisible(true);
        passwordTxt.setVisible(false);
    }

    String pass="admin";
    String username="admin";

    @FXML
    void gotoAdminProfile(MouseEvent event) throws IOException {
        if(checkBox.isSelected())
        {
            passwordTxtAdmin.setText(passwordTxt.getText());
        }

        if (!passwordTxtAdmin.getText().equals(pass) || !usernameTxtAdmin.getText().equals(username)) {
            showAlert(Alert.AlertType.ERROR, "Passwords do not match");
            return;
        }

        Stage stage=(Stage) loginBtnAdmin.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("AdminHomePage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Admin Profile ");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoHome(MouseEvent event) throws IOException {

        Stage stage=(Stage) homeBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("HomePage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Home Page ");
        stage.setScene(scene);
        stage.show();

    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
