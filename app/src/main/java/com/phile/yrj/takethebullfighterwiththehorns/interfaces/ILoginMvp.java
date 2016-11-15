package com.phile.yrj.takethebullfighterwiththehorns.interfaces;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
public interface ILoginMvp {
    int CORRECT = 0;
    int EMAIL_EMPTY = 1;
    int PASS_EMPTY = 2;
    int INCORRECT = 3;
    int CONNECTIONERROR = 4;

    interface View{
        void setMessageError(String messageError, int idView);
        void login();
    }
    interface Presenter{
        void validateCredentials(String email, String pass);
        int databaseLogin(String email, String pass);
        void nonPassLogin();

        void isUserSet();
    }
}