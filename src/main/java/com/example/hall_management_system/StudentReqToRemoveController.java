package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class StudentReqToRemoveController {
    DbManager dbManager = new DbManager();

    @FXML
    void remove(MouseEvent event) {
        int due=dbManager.getTotalHallDue(AppContext.loggedInRoll);
        if(due>0){
            showAlert(Alert.AlertType.ERROR,"You haven't cleared your remaining due yet, clear it before you proceed");
        }
        else
        {
            dbManager.updateStudentStatus(AppContext.loggedInRoll, dbManager.getStudentStatus(AppContext.loggedInRoll),"true" );
            showAlert(Alert.AlertType.INFORMATION,"Request Submitted Successfully");
        }

    }

    void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

