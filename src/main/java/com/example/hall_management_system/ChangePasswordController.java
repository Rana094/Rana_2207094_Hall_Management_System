package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class ChangePasswordController {

    @FXML
    private AnchorPane centerPane;

    @FXML
    private Button changePasswordBtn;

    @FXML
    private TextField confirmPasswordTxt;

    @FXML
    private TextField currentPasswordTxt;

    @FXML
    private TextField newPasswordTxt;

    private int roll;

    private DbManager dbManager=new DbManager();
    private  Student student;

    private String password;
    @FXML
    public void initialize()
    {
        roll=AppContext.loggedInRoll;
        student=dbManager.getStudentByRoll(roll);
        password=student.getPassword();
    }

    @FXML
    void changePassword(MouseEvent event) throws SQLException {
        String currentPassword= currentPasswordTxt.getText();
        String newPassword= newPasswordTxt.getText();
        String confirmPassword= confirmPasswordTxt.getText();
        if(currentPassword.isEmpty() || newPassword.isEmpty()||confirmPassword.isEmpty())

        {
            showAlert(Alert.AlertType.WARNING,"All fields are required");
            return;
        }
        if(!dbManager.passwordMatches(roll,currentPassword))
        {
            showAlert(Alert.AlertType.ERROR,"Password didnt match with the current password");
            return;
        }

        if(!newPassword.equals(confirmPassword))
        {
            showAlert(Alert.AlertType.ERROR, "New Passwords do not match");
            return;
        }
        if(dbManager.passwordMatches(roll,newPassword))
        {
            showAlert(Alert.AlertType.ERROR,"New Password is same as prev password");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Password changed successfully!");

        dbManager.updateStudent(roll,student.getName(),student.getEmail(),student.getAddress(),student.getDept(),student.getCgpa(),student.getBirthdate(),student.getImage(),newPassword);

    }

    private void showAlert(Alert.AlertType type,String msg)
    {
        Alert alert=new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
