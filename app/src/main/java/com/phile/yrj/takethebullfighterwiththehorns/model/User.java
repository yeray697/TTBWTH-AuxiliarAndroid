package com.phile.yrj.takethebullfighterwiththehorns.model;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */

public class User {
    private int id;
    private String email;
    private String username;
    private String password;

    public User(int id, String email, String username, String password){
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if(this.id != id)
            this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if(this.email != email)
            this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String user) {
        if(this.username != user)
            this.username = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (this.password != password)
            this.password = password;
    }
}
