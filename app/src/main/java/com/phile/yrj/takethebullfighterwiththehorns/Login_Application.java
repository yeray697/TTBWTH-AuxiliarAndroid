package com.phile.yrj.takethebullfighterwiththehorns;

import android.app.Application;
import android.content.SharedPreferences;

import com.phile.yrj.takethebullfighterwiththehorns.model.Comment;
import com.phile.yrj.takethebullfighterwiththehorns.model.New;
import com.phile.yrj.takethebullfighterwiththehorns.model.User;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
public class Login_Application extends Application {
    private User user;
    private ArrayList<New> news;
    private ArrayList<Comment> comments;
    SharedPreferences pref;
    boolean isClosing = false;
    @Override
    public void onCreate() {
        super.onCreate();
        pref = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        //3 sample news
        news = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 5);
        New noticia1 = new New(1,"Noticia 1","Cuerpo de la noticia 1", cal.getTime(),"http://jimenezsanjuan.es/wp-content/uploads/2015/01/icono-lupa.png");
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.OCTOBER);
        cal.set(Calendar.DAY_OF_MONTH, 5);
        New noticia2 = new New(2,"Noticia 2","Cuerpo de la noticia 2", cal.getTime(),"https://classicgrandtour.files.wordpress.com/2012/10/ojo-icono.png?w=75");
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 20);
        New noticia3 = new New(3,"Noticia 3","Cuerpo de la noticia 3", cal.getTime(),"https://cdn1.talenteca.com/assets/trucha-2.0.14/images/sesion/icono-whatsapp.png");
        news.add(noticia1);
        news.add(noticia2);
        news.add(noticia3);
        //Sample comments on news
        comments = new ArrayList<>();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 20);
        Comment comment1 = new Comment(1,"Juan05",1,"Peaso noticia",cal.getTime());
        Comment comment2 = new Comment(1,"Paco515",2,"Ya ves",cal.getTime());
        Comment comment3 = new Comment(1,"elbarto",3,"Opino lo mismo",cal.getTime());
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 20);
        Comment comment4 = new Comment(1,"Trump4President",1,"En verdad no",cal.getTime());
        Comment comment5 = new Comment(1,"MikeLitoris",2,"Claro que sí",cal.getTime());
        Comment comment6 = new Comment(1,"HitlerWasntABadGuy",3,"Yo no opino lo mismo",cal.getTime());

        this.comments.add(comment1);
        this.comments.add(comment2);
        this.comments.add(comment3);
        this.comments.add(comment4);
        this.comments.add(comment5);
        this.comments.add(comment6);
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (this.user != user)
            this.user = user;
        if (user != null) {
            pref.edit().putString("email", user.getEmail()).apply();
            pref.edit().putString("pass", user.getPassword()).apply();
            isClosing = false;
        } else {
            pref.edit().clear().apply();
            isClosing = true;
        }
    }

    public ArrayList<New> getNews() {
        return this.news;
    }
    public ArrayList<Comment> getComments(int idnew) {
        ArrayList<Comment> auxList = new ArrayList<>();
        for (Comment auxComment: this.comments) {
            if (auxComment.getIdnew() == idnew){
                auxList.add(auxComment);
            }
        }
        return auxList;
    }
    public boolean addComment(Comment comment){
        //TODO adding it to database
        this.comments.add(comment);
        return true;
    }

    public void closeSession() {
        setUser(null);
    }

    public String getEmailIfExists() {
        return pref.getString("email",null);
    }

    public String getPassIfExists() {
        return pref.getString("pass",null);
    }

    public boolean isClosing() {
        return isClosing;
    }
}
