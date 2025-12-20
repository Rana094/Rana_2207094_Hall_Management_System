package com.example.hall_management_system;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class HomePageController extends Application {

    private  DbManager dbManager=new DbManager();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();

        dbManager.getConnection();
    }


    @FXML
    private TextField usernameTxtStudent;

    @FXML
    private PasswordField passwordTxtStudent;

    @FXML
    private BorderPane rootpane;

    @FXML
    private AnchorPane contentArea;

    @FXML
    private Button gotoAdminPageBtn;

    @FXML
    private Button gotoRegisterPageBtn;

    @FXML
    private Button loginBtnStudent;
    @FXML
    private TextField passwordTxt;
    @FXML
    private CheckBox checkBox;

    @FXML
    void changevisibility(MouseEvent event)
    {
        if(checkBox.isSelected())
        {
            passwordTxt.setText(passwordTxtStudent.getText());
            passwordTxt.setVisible(true);
            passwordTxtStudent.setVisible(false);
            return;
        }
        passwordTxtStudent.setText(passwordTxt.getText());
        passwordTxtStudent.setVisible(true);
        passwordTxt.setVisible(false);

    }

    private void loadPage(String page) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource(page));
            contentArea.getChildren().setAll(pane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoAdminPage(MouseEvent event) throws IOException {
        loadPage("AdminLoginPage.fxml");
    }

    @FXML
    void gotoRegisterPage(MouseEvent event) throws IOException {

        Stage stage=(Stage) gotoRegisterPageBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Register Page");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoStudentProfile(MouseEvent event) throws IOException {


        String rollText = usernameTxtStudent.getText();
        String password = passwordTxtStudent.getText();

        if(checkBox.isSelected())
        {
             password = passwordTxt.getText();

        }

        if (rollText.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Username and password are required");
            return;
        }

        int roll;
        try {
            roll = Integer.parseInt(rollText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Roll must be a number");
            return;
        }


        if (!dbManager.rollExists(roll)) {
            showAlert(Alert.AlertType.ERROR, "User doesn't exist");
            return;
        }


        if (!dbManager.passwordMatches(roll, password)) {
            showAlert(Alert.AlertType.ERROR, "Password doesn't match with username");
            return;
        }


        Stage stage = (Stage) loginBtnStudent.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(
                HomePageController.class.getResource("StudentHomePage.fxml")
        );

       AppContext.loggedInRoll=roll;
        Scene scene = new Scene(loader.load());

        stage.setTitle("Student Profile");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }


}
