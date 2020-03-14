package com.example.admin.splacement;

/**
 * Created by Admin on 20-09-2019.
 */

public class Admins {
    private int id;
    private String name;
    private String password;
    public Admins(int id,String name,String password){
        this.id=id;
        this.name=name;
        this.password=password;
    }
    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
