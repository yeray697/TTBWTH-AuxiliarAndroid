package com.phile.yrj.takethebullfighterwiththehorns;

import android.app.Application;

import com.phile.yrj.takethebullfighterwiththehorns.model.New;
import com.phile.yrj.takethebullfighterwiththehorns.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by usuario on 4/11/16.
 */

public class Login_Application extends Application {
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (this.user != user)
            this.user = user;
    }

    public ArrayList<New> getNews() {
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
        ArrayList<New> values = new ArrayList<New>();
        values.add(noticia1);
        values.add(noticia2);
        values.add(noticia3);
        return values;
    }
}
