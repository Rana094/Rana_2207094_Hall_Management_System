package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class AdminPendingApprovalPageController {

    private Logger logger= Logger.getLogger(this.getClass().getName());


    private DbManager dbManager = new DbManager();

    private Student selectedStudent;

    @FXML
    private ListView<Student> viewAllPendingStudent;


    @FXML
    private Button viewStudentBtn;

    @FXML
    void viewStudent(MouseEvent event) {
        if (selectedStudent == null) return;

        try {
            AppContext.loggedInRoll=selectedStudent.getRoll();
            AppContext.StudentMyProfileControl="admin";
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("StudentMyProfile.fxml"));
            Parent pane = fxmlLoader.load();
            AppContext.adminHome.getCenterPane().getChildren().setAll(pane);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize()
    {
        viewAllPendingStudent.getItems().addAll(dbManager.readPendingStudents());
        logger.info("stuf on");

        viewAllPendingStudent.setCellFactory(list -> new ListCell<>() {

            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);

                if (empty || student == null) {
                    setText(null);
                    setGraphic(null);
                }
                else
                {
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
        viewAllPendingStudent.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedStudent = newVal;});
    }
}