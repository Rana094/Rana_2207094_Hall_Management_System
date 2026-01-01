package com.example.hall_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class StudentHomePageController {

    private static final Logger logger = LoggerFactory.getLogger(StudentHomePageController.class);
    @FXML
    private Button changePasswordBtn;

    @FXML
    private Button myProfileBtn;

    @FXML
    private Label nameLabel;

    @FXML
    private Button reqDiningManagerBtn;

    @FXML
    private Button reqToRemoveBtn;

    @FXML
    private Button signOutBtn;

    @FXML
    private Button viewNoticesBtn;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private Button homeBtn;


    private DbManager dbManager = new DbManager();

    @FXML
    void changePassword(MouseEvent event) throws IOException {
        loadPage("StudentChangePasswordPage.fxml");

    }

    @FXML
    void checkHallDue(MouseEvent event) throws IOException {
        loadPage("StudentHallDues.fxml");

    }

    @FXML
    void checkHallDueBtn(ActionEvent event) {


    }

    @FXML
    void myProfile(MouseEvent event) throws IOException {
        AppContext.StudentMyProfileControl="student";
        loadPage("StudentMyProfile.fxml");
    }

    @FXML
    void reqDiningManager(MouseEvent event) {

    }

    @FXML
    void reqToRemove(MouseEvent event) {

    }

    @FXML
    void signOut(MouseEvent event) throws IOException {

        Stage stage=(Stage) signOutBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("HomePage.fxml"));

        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Student Login");

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void viewNotices(MouseEvent event) throws IOException {
        loadPage("StudentViewNotices.fxml");
    }

    int roll;

    @FXML
    public void initialize()
    {
        AppContext.studentHome=this;
        logger.info("student Home");
       roll=AppContext.loggedInRoll;
        Student student=dbManager.getStudentByRoll(roll);
        if(student!=null)
        {
            String name=student.getName();
            nameLabel.setVisible(true);
            nameLabel.setText(name);
        }
    }

    private void loadPage(String page) throws IOException {
        Parent pane=FXMLLoader.load(getClass().getResource(page));
        centerPane.getChildren().setAll(pane);
    }

    public void gotoHome(MouseEvent mouseEvent) throws IOException {
        Stage stage=(Stage) homeBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("StudentHomePage.fxml"));

        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Student Home Page");

        stage.setScene(scene);
        stage.show();
    }
}
