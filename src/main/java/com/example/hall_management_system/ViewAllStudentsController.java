package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ViewAllStudentsController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, Integer> rollCol;

    @FXML
    private TableColumn<Student, String> nameCol;

    @FXML
    private TableColumn<Student, String> deptCol;

    @FXML
    private TableColumn<Student, String> statusCol;

    @FXML
    private TableColumn<Student, Integer> hallDueCol;

    @FXML
    private Button deleteStudentBtn;

    @FXML
    private Button updateStudentBtn;

    private DbManager dbManager = new DbManager();
    private Student selectedStudent;

    @FXML
    public void initialize() {


        rollCol.setCellValueFactory(new PropertyValueFactory<>("roll"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));


        hallDueCol.setCellValueFactory(cellData -> {
            int due = dbManager.getTotalHallDue(cellData.getValue().getRoll());
            return new javafx.beans.property.SimpleIntegerProperty(due).asObject();
        });

        studentTable.getItems().addAll(dbManager.readStudents());

        studentTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> selectedStudent = newVal);

        logger.info("All students loaded in table");
    }

    @FXML
    void deleteStudent() throws SQLException {
        if (selectedStudent == null) return;

        dbManager.deleteStudent(selectedStudent.getRoll());
        studentTable.getItems().remove(selectedStudent);
    }

    @FXML
    void updateStudent() {
        if (selectedStudent == null) return;

        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("AdminUpdateInfoPage.fxml"));
            Parent pane = loader.load();

            UpdateInfoController controller = loader.getController();
            Student fullStudent =
                    dbManager.getStudentByRoll(selectedStudent.getRoll());
            controller.setStudent(fullStudent);

            AppContext.adminHome.getCenterPane().getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

