package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentHallDuesController {

    @FXML
    private TableView<HallBillMonth> billTable;

    @FXML
    private TableColumn<HallBillMonth, String> monthCol;

    @FXML
    private TableColumn<HallBillMonth, Integer> amountCol;

    @FXML
    private Label totalDueLabel;

    private DbManager dbManager = new DbManager();
    private int roll;

    @FXML
    public void initialize() {
        roll = AppContext.loggedInRoll;

        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        loadBills();
    }

    private void loadBills() {
        billTable.getItems().clear();
        billTable.getItems().addAll(dbManager.getUnpaidBillsForStudent(roll));

        int totalDue = dbManager.getTotalHallDue(roll);
        totalDueLabel.setText(totalDue + " Tk");
    }

    @FXML
    void paySelectedBill() {
        HallBillMonth selected =
                billTable.getSelectionModel().getSelectedItem();

        if (selected == null) return;

        dbManager.markBillAsPaid(selected.getId());
        loadBills();
    }
}
