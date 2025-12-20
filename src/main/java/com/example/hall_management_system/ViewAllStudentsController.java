package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ViewAllStudentsController  {
    private Logger logger= Logger.getLogger(this.getClass().getName());
    @FXML
    private ListView<Student> viewAllStudentRecords;

    private DbManager dbManager = new DbManager();

    private Student selectedStudent;

    @FXML
    private Button deleteStudentBtn;
    @FXML
    private Button updateStudentBtn;

    @FXML
    public void initialize()
    {
        viewAllStudentRecords.getItems().addAll(dbManager.readStudents());
        logger.info("stuf on");

        viewAllStudentRecords.setCellFactory(list -> new ListCell<>() {

            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);

                if (empty || student == null) {
                    setText(null);
                    setGraphic(null);
                }
                else {
                    ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(student.getImage())));
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    imageView.setPreserveRatio(true);
                    Label label = new Label(student.getName() + "  -  " + student.getRoll()+" - "+student.getDept());
                    HBox hBox = new HBox(10, imageView, label);
                    setGraphic(hBox);
                }
            }
        });
        viewAllStudentRecords.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                    selectedStudent = newVal;});
    }

    @FXML
    void deleteStudent(MouseEvent event) throws SQLException {
        if (selectedStudent == null) return;

        dbManager.deleteStudent(selectedStudent.getRoll());
        viewAllStudentRecords.getItems().remove(selectedStudent);

    }

    @FXML
    private void updateStudent() {
        if (selectedStudent == null) return;

        try {

            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AdminUpdateInfoPage.fxml"));
            Parent pane = fxmlLoader.load();

            UpdateInfoController controller = fxmlLoader.getController();
            Student fullStudent = dbManager.getStudentByRoll(selectedStudent.getRoll());
            controller.setStudent(fullStudent);

            AppContext.adminHome.getCenterPane().getChildren().setAll(pane);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
