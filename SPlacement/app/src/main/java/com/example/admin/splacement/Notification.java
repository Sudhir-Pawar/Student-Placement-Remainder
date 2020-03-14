package com.example.admin.splacement;

/**
 * Created by Admin on 02-10-2019.
 */

public class Notification {
    private String title,description,time,date,companyName,collegeName;

    public Notification(String title, String description, String time, String date, String companyName, String collegeName) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.companyName = companyName;
        this.collegeName = collegeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
