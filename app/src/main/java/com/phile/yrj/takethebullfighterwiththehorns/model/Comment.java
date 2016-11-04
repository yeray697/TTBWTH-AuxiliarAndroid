package com.phile.yrj.takethebullfighterwiththehorns.model;

import java.util.Date;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */

public class Comment {
    private int id;
    private String iduser;
    private String message;
    private Date date;

    public Comment(int id, String iduser, String message, Date date){
        this.id = id;
        this.iduser = iduser;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if(this.id != id)
            this.id = id;
    }

    public String getIdUser() {
        return this.iduser;
    }

    public void setIdUser(String iduser) {
        if(this.iduser != iduser)
            this.iduser = iduser;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String user) {
        if(this.message != user)
            this.message = user;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        if (this.date != date)
            this.date = date;
    }
}
