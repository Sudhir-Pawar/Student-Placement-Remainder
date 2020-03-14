package com.example.admin.splacement;

/**
 * Created by Admin on 05-10-2019.
 */

public class AddCollegeInfo {

    private String StudentRegister,StudentPlaced;

    public AddCollegeInfo(String studentRegister, String studentPlaced) {
        StudentRegister = studentRegister;
        StudentPlaced = studentPlaced;
    }

    public String getStudentRegister() {
        return StudentRegister;
    }

    public void setStudentRegister(String studentRegister) {
        StudentRegister = studentRegister;
    }

    public String getStudentPlaced() {
        return StudentPlaced;
    }

    public void setStudentPlaced(String studentPlaced) {
        StudentPlaced = studentPlaced;
    }
}
