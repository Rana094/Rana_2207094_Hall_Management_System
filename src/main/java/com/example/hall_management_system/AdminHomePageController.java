package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    }

    @FXML
    void diningManagerApprovalClicked(MouseEvent event) {

    }

    @FXML
    void homeClicked(MouseEvent event) {
        loadPage("AdminHomePage.fxml");

    }

    @FXML
    void pendingApprovalClicked(MouseEvent event) {

    }

    @FXML
    void sendNoticeClicked(MouseEvent event) {

    }

    @FXML
    void viewAllStudentClicked(MouseEvent event) {
        loadPage("ViewAllStudents.fxml");

    }
    public void loadPage(String page) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource(page));
            centerPane.getChildren().setAll(pane);

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

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public AnchorPane getCenterPane() {
        return centerPane;
    }

}
