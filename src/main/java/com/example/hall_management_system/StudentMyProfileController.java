package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.ByteArrayInputStream;


public class StudentMyProfileController {

    @FXML
    private TextArea addressTxt;

    @FXML
    private TextField birthDateTxt;

    @FXML
    private TextField cgpaTxt;

    @FXML
    private TextField departmentTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField rollTxt;

    @FXML
    private ImageView imageView;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button approveBtn;
    @FXML
    private Label textLabel;

    private DbManager dbManager=new DbManager();

    @FXML
    public  void initialize()
    {
        int roll = AppContext.loggedInRoll;

        Student student=dbManager.getStudentByRoll(roll);

        if (student == null) {
            System.err.println("No student found for roll: " + roll);
            return;
        }

        if(AppContext.StudentMyProfileControl.equals("admin"))
        {
            approveBtn.setVisible(true);
            goBackBtn.setVisible(true);
            textLabel.setText("Student Profile");

        }
       rollTxt.setText(String.valueOf(student.getRoll()));
       nameTxt.setText(student.getName());
       addressTxt.setText(student.getAddress());
       emailTxt.setText(student.getEmail());
       departmentTxt.setText(student.getDept());
       cgpaTxt.setText(student.getCgpa());
       birthDateTxt.setText(student.getBirthdate());
       imageView.setImage(new Image(new ByteArrayInputStream(student.getImage())));
    }

    @FXML
    void approve(MouseEvent event) {
        dbManager.updateStudentStatus(Integer.parseInt(rollTxt.getText()),"true","false");
        AppContext.adminHome.loadPage("AdminPendingApprovalPage.fxml");
    }

    @FXML
    void goBack(MouseEvent event) {
        AppContext.adminHome.loadPage("AdminPendingApprovalPage.fxml");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
