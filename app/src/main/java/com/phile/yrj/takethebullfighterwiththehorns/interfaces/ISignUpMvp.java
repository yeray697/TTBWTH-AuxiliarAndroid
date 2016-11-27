package com.phile.yrj.takethebullfighterwiththehorns.interfaces;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.1
 * Created on 04/11/16
 */
public interface ISignUpMvp {
    int MINUSER_LENGTH = 5;
    int MAXUSER_LENGTH = 16;
    int MINPASS_LENGTH = 5;
    int MAXPASS_LENGTH = 20;

    String USER_REGEX = "^[a-z0-9_-]{"+MINUSER_LENGTH+","+MAXUSER_LENGTH+"}$";

    interface View{
        /**
         * Show an error on the device
         * @param messageError ErrorClass that will be showed
         * @param idView View where error will be showed. idView = ErrorClass.VIEW_TOAST will show the error on a Toast
         */
        void setMessageError(String messageError, int idView);

        /**
         * Finish the activity
         */
        void finish();
    }
    interface Presenter{
        /**
         * Method that sign up a new user if it is possible
         * @param user User's username
         * @param email User's email
         * @param pass User's password
         * @param passAgain User's password
         * @param birthday User's birthday
         * @param gender User's gender
         */
        void signUp(String user, String email, String pass, String passAgain, String birthday, char gender);
    }
}
