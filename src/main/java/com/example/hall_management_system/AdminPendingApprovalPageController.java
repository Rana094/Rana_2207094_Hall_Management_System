package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.logging.Logger;

public class AdminPendingApprovalPageController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private DbManager dbManager = new DbManager();

    private Student selectedStudent;

    @FXML
    private TableView<Student> pendingStudentTable;

    @FXML
    private TableColumn<Student, Integer> rollCol;

    @FXML
    private TableColumn<Student, String> nameCol;

    @FXML
    private TableColumn<Student, String> deptCol;

    @FXML
    private Button viewStudentBtn;

    @FXML
    public void initialize() {

        rollCol.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));

        pendingStudentTable.getItems()
                .addAll(dbManager.readPendingStudents());

        pendingStudentTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> selectedStudent = newVal);

        logger.info("Pending students loaded in table view");
    }

    @FXML
    void viewStudent(MouseEvent event) {
        if (selectedStudent == null) return;

        try {
            AppContext.loggedInRoll = selectedStudent.getRoll();
            AppContext.StudentMyProfileControl = "admin";

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("StudentMyProfile.fxml"));
            Parent pane = loader.load();

            AppContext.adminHome
                    .getCenterPane()
                    .getChildren()
                    .setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
