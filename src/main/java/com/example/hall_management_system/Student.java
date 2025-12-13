package com.example.hall_management_system;

public class Student {
    private int roll;
    private String name;
    private byte[] image;

    public Student(int roll,String name,byte[] image)
    {
        this.roll=roll;
        this.name=name;
        this.image=image;
    }

    private String email;
    private String address;
    private String dept;
    private String cgpa;
    private String birthdate;


    public int getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDept() {
        return dept;
    }

    public String getCgpa() {
        return cgpa;
    }

    public String getBirthdate() {
        return birthdate;
    }




}
