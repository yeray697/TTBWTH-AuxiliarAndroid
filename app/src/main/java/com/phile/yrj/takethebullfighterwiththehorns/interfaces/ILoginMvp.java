package com.phile.yrj.takethebullfighterwiththehorns.interfaces;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.1
 * Created on 04/11/16
 */
public interface ILoginMvp {

    interface View{
        /**
         * Show an error on the device
         * @param messageError ErrorClass that will be showed
         * @param idView View where error will be showed. idView = ErrorClass.VIEW_TOAST will show the error on a Toast
         */
        void setMessageError(String messageError, int idView);
        /**
         * Start the main activity
         */
        void startLoginActivity();
        /**
         * Set credentials passed as variable.
         * That credentials are set if user was logged the last time he used the app
         * @param email User's email
         * @param pass User's pass
         */
        void setCredentials(String email, String pass);
    }
    interface Presenter{
        /**
         * Method used to log in with credentials passed
         * @param email User's email
         * @param pass User's pass
         */
        void login(String email, String pass);
        /**
         * Method that log in without credentials
         */
        void nonPassLogin();
        /**
         * Check if the user was logged the last time he used the app
         * If true, it tries to log in as usual
         */
        void isUserSet();
    }
}