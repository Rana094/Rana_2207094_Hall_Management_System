package com.example.hall_management_system;

public class Student {

    private int roll;
    private String name;
    private byte[] image;

    private String email;
    private String address;
    private String dept;
    private String cgpa;
    private String birthdate;

    public Student(int roll, String name, byte[] image,String dept) {
        this.roll = roll;
        this.name = name;
        this.image = image;
        this.dept=dept;
    }

    public Student(int roll, String name, String email, String address,
                   String dept, String cgpa, String birthdate, byte[] image,String password) {

        this.roll = roll;
        this.name = name;
        this.email = email;
        this.address = address;
        this.dept = dept;
        this.cgpa = cgpa;
        this.birthdate = birthdate;
        this.image = image;
        this.password=password;
    }

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
    private String password;

    public String getPassword() {
        return password;
    }


}
