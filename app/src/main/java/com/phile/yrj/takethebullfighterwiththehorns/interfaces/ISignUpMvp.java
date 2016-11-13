package com.phile.yrj.takethebullfighterwiththehorns.interfaces;

import java.util.Date;

/**
 * Created by yeray697 on 5/11/16.
 */

public interface ISignUpMvp {
    int CORRECT = 0;
    int USER_EMPTY = 1;
    int EMAIL_EMPTY = 2;
    int PASS1_EMPTY = 3;
    int PASS2_EMPTY = 4;
    int BIRTHDAY_EMPTY = 5;
    int GENDER_EMPTY = 6;
    int DIFFERENT_PASS = 7;
    int USER_LENGTH = 8;
    int PASS_LENGTH = 9;
    int CONNECTIONERROR = 10;
    int USER_USED = 11;
    int EMAIL_USED = 12;
    int USER_CHARS = 13;

    int MINUSER_LENGTH = 5;
    int MAXUSER_LENGTH = 16;
    int MINPASS_LENGTH = 5;
    int MAXPASS_LENGTH = 20;

    String USER_REGEX = "^[a-z0-9_-]{"+MINUSER_LENGTH+","+MAXUSER_LENGTH+"}$";

    interface View{
        void setMessageError(String messageError, int idView);
        void finish();
    }
    interface Presenter{
        void validateCredentials(String user, String email, String pass, String passAgain,String birthday,char gender);
        int databaseSignUp(String user, String email, String pass, String passAgain,String birthday,char gender);
    }
}
