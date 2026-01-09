package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.YearMonth;

public class AdminCalculateHallDueController {

    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private TextField amountField;

    @FXML
    private Label statusLabel;

    private DbManager dbManager = new DbManager();

    @FXML
    public void initialize() {
        YearMonth current = YearMonth.now();
        for (int i = 0; i < 12; i++) {
            monthCombo.getItems().add(current.plusMonths(i).toString());
        }
    }

    @FXML
    void generateBill() {
        String month = monthCombo.getValue();
        String amountText = amountField.getText();

        if (month == null || amountText.isEmpty()) {
            statusLabel.setText("Please select month and enter amount.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        int amount;
        try {
            amount = Integer.parseInt(amountText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Amount must be a number.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        dbManager.generateMonthlyHallBills(month, amount);

        statusLabel.setText("Hall bills generated successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    void viewHallBills(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AdminViewHallBills.fxml"));
        Parent pane = fxmlLoader.load();
        AppContext.adminHome.getCenterPane().getChildren().setAll(pane);

    }
}
