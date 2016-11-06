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
    int PASS_CHAR = 10;
    int CONNECTIONERROR = 11;
    int USER_USED = 12;
    int EMAIL_USED = 13;

    int MINUSER_LENGTH = 5;
    int MAXUSER_LENGTH = 16;
    int MINPASS_LENGTH = 5;
    int MAXPASS_LENGTH = 20;
    //TODO password regular expesion
    String PASS_REGEX = "~";

    interface View{
        void setMessageError(String messageError, int idView);
    }
    interface Presenter{
        void validateCredentials(String user, String email, String pass, String passAgain,String birthday,char gender);
        int databaseSignUp(String user, String email, String pass, String passAgain,String birthday,char gender);
    }
}
