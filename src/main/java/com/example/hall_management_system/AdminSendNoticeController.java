package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminSendNoticeController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea messageArea;

    private DbManager dbManager = new DbManager();

    @FXML
    void sendNotice() {
        String title = titleField.getText();
        String message = messageArea.getText();

        if (title.isEmpty() || message.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "All fields required!");
            return;
        }

        dbManager.insertNotice(title, message);
        showAlert(Alert.AlertType.INFORMATION, "Notice Sent Successfully");

        titleField.clear();
        messageArea.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

