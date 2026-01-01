package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class AdminRemoveRequestsController {

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, Integer> rollCol;

    @FXML
    private TableColumn<Student, String> nameCol;

    @FXML
    private TableColumn<Student, String> deptCol;

    @FXML
    private TableColumn<Student, Integer> hallDueCol;

    private DbManager dbManager = new DbManager();
    private Student selectedStudent;

    @FXML
    public void initialize() {

        rollCol.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));

        hallDueCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleIntegerProperty(
                        dbManager.getTotalHallDue(cell.getValue().getRoll())
                ).asObject()
        );

        studentTable.getItems()
                .addAll(dbManager.readRemovalRequests());

        studentTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> selectedStudent = newVal);
    }

    @FXML
    void approveRemoval() throws SQLException {
        if (selectedStudent == null) return;

        dbManager.deleteStudent(selectedStudent.getRoll());
        studentTable.getItems().remove(selectedStudent);
    }

    @FXML
    void rejectRemoval() {
        if (selectedStudent == null) return;

        dbManager.updateStudentStatus(
                selectedStudent.getRoll(),
                dbManager.getStudentStatus(selectedStudent.getRoll()),
                "false"
        );

        studentTable.getItems().remove(selectedStudent);
    }
}
