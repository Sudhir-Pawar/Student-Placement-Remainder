package com.example.admin.splacement;

/**
 * Created by Admin on 19-09-2019.
 */

public class Users {
    private int id;
    private String username;
    private String password;

    public Users(int id,String username,String password) {
        this.id=id;
        this.username = username;
        this.password=password;
    }
    public void setid(int id){
        this.id=id;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public int getid(){
        return id;
    }

}
