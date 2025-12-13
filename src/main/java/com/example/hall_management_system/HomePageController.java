package com.example.hall_management_system;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private BorderPane rootpane;

    @FXML
    private AnchorPane contentArea;

    @FXML
    private Button gotoAdminPageBtn;

    @FXML
    private Button gotoRegisterPageBtn;

    @FXML
    private Button loginBtnStudent;

    private void loadPage(String page) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource(page));
            contentArea.getChildren().setAll(pane);

            if (pane instanceof AnchorPane) {
                AnchorPane.setTopAnchor(pane, 0.0);
                AnchorPane.setBottomAnchor(pane, 0.0);
                AnchorPane.setLeftAnchor(pane, 0.0);
                AnchorPane.setRightAnchor(pane, 0.0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoAdminPage(MouseEvent event) throws IOException {
        loadPage("AdminLoginPage.fxml");
//        Stage stage=(Stage) gotoAdminPageBtn.getScene().getWindow();
//
//        FXMLLoader fxmlLoader=new FXMLLoader(HomePageController.class.getResource("AdminLoginPage.fxml"));
//        Scene scene =new Scene (fxmlLoader.load());
//        stage.setTitle("Admin Login Page");
//        stage.setScene(scene);
//        stage.show();
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
