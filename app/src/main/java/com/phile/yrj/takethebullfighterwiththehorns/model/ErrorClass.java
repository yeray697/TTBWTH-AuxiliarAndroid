package com.phile.yrj.takethebullfighterwiththehorns.model;

import android.content.Context;

import com.phile.yrj.takethebullfighterwiththehorns.utils.ErrorMapUtils;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
public class ErrorClass {
    public static final int CORRECT = 0;                    //Login or sign up done successfully
    //COMMON ERRORS
    public static final int INCORRECT = 1;                  //Login: Email and/or password is incorrect
    public static final int SERVER_CONNECTION_ERROR = 2;    //Unable to connect to server
    public static final int USER_CONNECTION_ERROR = 3;      //Unable to connect to server
    //Individual sign up errors
    public static final int USER_EMPTY = 10;                //Sign Up: User field is empty
    public static final int INVALID_EMAIL = 11;             //SignUp: The email is invalid
    public static final int INVALID_PASS = 12;              //SignUp: Pass and pass2 are not valid pass
    public static final int BIRTHDAY_EMPTY = 13;            //SignUp: Birthday field is empty
    public static final int GENDER_EMPTY = 14;              //SignUp: Gender field is empty
    public static final int DIFFERENT_PASS = 15;            //SignUp: Pass are differents
    public static final int USER_LENGTH = 16;               //SignUp: Username doesn't match with length range
    public static final int PASS_LENGTH = 17;               //SignUp: Password doesn't match with length range
    public static final int USER_USED = 18;                 //SignUp: Username in use by other user
    public static final int EMAIL_USED = 19;                //SignUp: Email in use by other user
    public static final int USER_CHARS = 20;                //SignUp: User cannot content special chars
    //Individual log in errors
    public static final int EMAIL_EMPTY = 30;               //LogIn: Email field is empty
    public static final int PASS_EMPTY = 31;                //LogIn: Email field is empty

    private int code;
    private int idView;
    private boolean isThereAnError;

    public static final int VIEW_TOAST = 1;

    public ErrorClass() {
        this.setIfThereIsAnError(true);
    }
    public ErrorClass(int idView, int codeError) {
        setIdView(idView);
        setCode(codeError);
        this.setIfThereIsAnError(true);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getIdView() {
        return idView;
    }

    public void setIdView(int idView) {
        this.idView = idView;
    }

    public static String getMessageError(Context context, int code) {
        String message = ErrorMapUtils.getErrorMap(context).get(String.valueOf(code));
        message = context.getResources().getString(context.getResources().getIdentifier(message, "string", context.getPackageName()));
        return message;
    }

    public boolean isThereAnError() {
        return this.isThereAnError;
    }

    public void setIfThereIsAnError(boolean thereAnError) {
        this.isThereAnError = thereAnError;
    }
}