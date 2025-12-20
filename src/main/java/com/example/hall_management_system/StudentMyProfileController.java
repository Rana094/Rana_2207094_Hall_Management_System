package com.example.hall_management_system;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private DbManager dbManager=new DbManager();

    private int roll;

    @FXML
    public  void initialize()
    {
        roll=AppContext.loggedInRoll;

        Student student=dbManager.getStudentByRoll(roll);

       rollTxt.setText(String.valueOf(student.getRoll()));
       nameTxt.setText(student.getName());
       addressTxt.setText(student.getAddress());
       emailTxt.setText(student.getEmail());
       departmentTxt.setText(student.getDept());
       cgpaTxt.setText(student.getCgpa());
       birthDateTxt.setText(student.getBirthdate());
       imageView.setImage(new Image(new ByteArrayInputStream(student.getImage())));

    }

}
