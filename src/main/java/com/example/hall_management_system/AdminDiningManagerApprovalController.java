package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.YearMonth;

public class AdminDiningManagerApprovalController {

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, Integer> rollCol;

    @FXML
    private TableColumn<Student, String> nameCol;

    @FXML
    private TableColumn<Student, String> deptCol;

    @FXML
    private TableColumn<Student, String> cgpaCol;

    @FXML
    private TableColumn<Student, Integer> hallDueCol;

    private DbManager dbManager = new DbManager();
    private Student selectedStudent;

    private final String month = YearMonth.now().toString();

    @FXML
    public void initialize() {

        rollCol.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));

        cgpaCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        dbManager.getStudentByRoll(
                                cell.getValue().getRoll()
                        ).getCgpa()
                )
        );

        hallDueCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleIntegerProperty(
                        dbManager.getTotalHallDue(
                                cell.getValue().getRoll()
                        )
                ).asObject()
        );

        studentTable.getItems()
                .addAll(dbManager.getDiningManagerRequests(month));

        studentTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> selectedStudent = newVal);
    }

    @FXML
    void approveManager() {
        if (selectedStudent == null) return;

        dbManager.approveDiningManager(selectedStudent.getRoll(), month);

        String title = "Dining Manager";

        String message =
                "This month dining manager is selected.\n\n" +
                        "Name: " + selectedStudent.getName() + "\n" +
                        "Department: " + selectedStudent.getDept() + "\n" +
                        "Roll: " + selectedStudent.getRoll();

        dbManager.insertNotice(title, message);

        studentTable.getItems().clear();
    }
}
