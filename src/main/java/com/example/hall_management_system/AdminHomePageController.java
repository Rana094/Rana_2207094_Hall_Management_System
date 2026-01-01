package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHomePageController {
    public void initialize() {
        AppContext.adminHome = this;
    }

    @FXML
    private Button calculateHallDueBtn;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private Button diningManagerApprovalBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button pendingApprovalBtn;

    @FXML
    private Button sendNoticeBtn;

    @FXML
    private Button viewAllStudentBtn;

    @FXML
    void calculateHallDueClicked(MouseEvent event) {
        loadPage("AdminCalculateHallDue.fxml");

    }

    @FXML
    void diningManagerApprovalClicked(MouseEvent event) {

    }

    @FXML
    void homeClicked(MouseEvent event) throws IOException {
        Stage stage=(Stage) homeBtn.getScene().getWindow();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AdminHomePage.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Admin Home Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pendingApprovalClicked(MouseEvent event) {
        loadPage("AdminPendingApprovalPage.fxml");
    }

    @FXML
    void sendNoticeClicked(MouseEvent event) {
            loadPage("AdminSendNotice.fxml");
    }

    @FXML
    void viewAllStudentClicked(MouseEvent event) {
        loadPage("ViewAllStudents.fxml");

    }
    public void loadPage(String page) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource(page));
            centerPane.getChildren().setAll(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public AnchorPane getCenterPane() {
        return centerPane;
    }


    @FXML
    private  Button signOutBtn;

    @FXML
    void signOut(MouseEvent event) throws IOException {


        Stage stage=(Stage) signOutBtn.getScene().getWindow();

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("HomePage.fxml"));

        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Student Login");

        stage.setScene(scene);
        stage.show();

    }

    public void removeStudent(MouseEvent mouseEvent) {
        loadPage("AdminRemoveRequests.fxml");
    }
}
