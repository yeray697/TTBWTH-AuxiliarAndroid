package com.phile.yrj.takethebullfighterwiththehorns.model;

import java.util.Date;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */

public class New {
    private String title;
    private String body;
    private Date date;

    public New(String title, String body, Date date, Language language){
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (this.title != title)
            this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        if (this.body != body)
            this.body = body;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        if (this.date != date)
            this.date = date;
    }
}
