package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class StudentViewNoticesController {

    @FXML
    private ListView<String> noticeList;

    private DbManager dbManager = new DbManager();

    @FXML
    public void initialize() {
        List<Notice> notices = dbManager.readNotices();
        for (Notice n : notices) {
            noticeList.getItems().add(
                            "Title: " + n.getTitle() + "\n" +
                            "Description: " + n.getMessage() + "\n" +
                            "Date: " + n.getCreatedAt()
            );
        }
    }
}

