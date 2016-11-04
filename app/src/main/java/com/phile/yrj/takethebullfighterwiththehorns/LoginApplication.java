package com.phile.yrj.takethebullfighterwiththehorns;

import android.app.Application;

import com.phile.yrj.takethebullfighterwiththehorns.model.User;

/**
 * Created by usuario on 4/11/16.
 */

public class LoginApplication extends Application {
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
}
