package com.example.hall_management_system;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button gotoAdminPageBtn;

    @FXML
    private Button gotoRegisterPageBtn;

    @FXML
    private Button loginBtnStudent;

    @FXML
    void gotoAdminPage(MouseEvent event) throws IOException {
        Stage stage=(Stage) gotoAdminPageBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("AdminLoginPage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Admin Login Page");
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void gotoRegisterPage(MouseEvent event) throws IOException {

        Stage stage=(Stage) gotoRegisterPageBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("RegisterPage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Register Page");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoStudentProfile(MouseEvent event) throws IOException {

        Stage stage=(Stage) loginBtnStudent.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("RegisterPage.fxml"));
        Scene scene =new Scene (fxmlLoader.load());
        stage.setTitle("Student Profile ");
        stage.setScene(scene);
        stage.show();

    }

}
