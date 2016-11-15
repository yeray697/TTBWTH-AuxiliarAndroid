package com.phile.yrj.takethebullfighterwiththehorns.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */

public class Comment {
    private int id;
    private String username;
    private int idnew;
    private String message;
    private Date date;

    public static final Comparator<Comment> ASC_DATE_COMPARATOR = new Comparator<Comment>() {
        @Override
        public int compare(Comment o1, Comment o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };
    public static final Comparator<Comment> DESC_DATE_COMPARATOR = new Comparator<Comment>() {
        @Override
        public int compare(Comment o1, Comment o2) {
            return -1 * o1.getDate().compareTo(o2.getDate());
        }
    };

    public Comment(int id, String username, int idnew, String message, Date date){
        this.id = id;
        this.username = username;
        this.idnew = idnew;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String iduser) {
        if(this.username != iduser)
            this.username = iduser;
    }

    public int getIdnew() {
        return this.idnew;
    }

    public void setIdnew(int idnew) {
        if(this.idnew != idnew)
            this.idnew = idnew;
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

    public String getFormatedDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String result = df.format(this.date);
        return result;
    }
}
