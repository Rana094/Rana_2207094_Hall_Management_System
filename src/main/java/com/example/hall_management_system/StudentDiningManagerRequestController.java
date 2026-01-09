package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.YearMonth;

public class StudentDiningManagerRequestController {

    @FXML
    private Button requestBtn;

    @FXML
    private Label statusLabel;

    private DbManager dbManager = new DbManager();
    private int roll;

    @FXML
    public void initialize() {
        roll = AppContext.loggedInRoll;

        String month = YearMonth.now().toString();

        if (dbManager.isDiningManagerSelected(month)) {
            requestBtn.setDisable(true);
            statusLabel.setText("Dining Manager already selected for this month.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
        else if (dbManager.hasAppliedForDiningManager(roll, month)) {
            requestBtn.setDisable(true);
            statusLabel.setText("You have already applied this month.");
            statusLabel.setStyle("-fx-text-fill: orange;");
        }
    }

    @FXML
    void requestDiningManager() {
        String month = YearMonth.now().toString();

        dbManager.requestDiningManager(roll, month);

        requestBtn.setDisable(true);
        statusLabel.setText("Request submitted successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
