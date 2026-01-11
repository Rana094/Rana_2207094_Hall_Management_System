package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AdminViewHallBillsController {

    @FXML
    private TableView<HallBillMonth> billTable;

    @FXML
    private TableColumn<HallBillMonth, String> monthCol;

    @FXML
    private TableColumn<HallBillMonth, Integer> amountCol;

    private DbManager dbManager = new DbManager();

    @FXML
    public void initialize() {

        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        billTable.getItems().addAll(dbManager.getGeneratedHallBills());
    }
    @FXML
    private void goBack() {
        try {
            Parent pane = FXMLLoader.load(
                    getClass().getResource("AdminCalculateHallDue.fxml")
            );

            AppContext.adminHome
                    .getCenterPane()
                    .getChildren()
                    .setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
