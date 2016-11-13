package com.phile.yrj.takethebullfighterwiththehorns.presenter;

import android.content.Context;

import com.phile.yrj.takethebullfighterwiththehorns.Login_Application;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ILoginMvp;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.INewMvp;
import com.phile.yrj.takethebullfighterwiththehorns.model.Comment;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by yeray697 on 7/11/16.
 */

public class NewPresenter implements INewMvp.Presenter {
    private INewMvp.View view;

    public NewPresenter(INewMvp.View view){
        this.view = view;
    }

    @Override
    public boolean publishComment(String comment, int idnew) {
        //TODO Comment should be introduced on database, so we can get the Id
        String user = ((Login_Application)((Context)view).getApplicationContext()).getUser().getUsername();
        Comment aux = new Comment(new Random().nextInt()*300,
                user,
                idnew,
                comment,
                Calendar.getInstance().getTime());
        ((Login_Application)((Context) view).getApplicationContext()).addComment(aux);
        return true;
    }
}
