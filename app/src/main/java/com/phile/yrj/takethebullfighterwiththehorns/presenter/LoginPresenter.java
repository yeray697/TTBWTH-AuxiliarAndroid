package com.phile.yrj.takethebullfighterwiththehorns.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.phile.yrj.takethebullfighterwiththehorns.Login_Application;
import com.phile.yrj.takethebullfighterwiththehorns.R;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ILoginMvp;
import com.phile.yrj.takethebullfighterwiththehorns.model.ErrorClass;
import com.phile.yrj.takethebullfighterwiththehorns.model.User;
import com.phile.yrj.takethebullfighterwiththehorns.utils.Utils;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.1
 * Created on 04/11/16
 */

public class LoginPresenter implements ILoginMvp.Presenter{
    private ILoginMvp.View view;
    private Context context;
    private int idViewEmail;
    private int idViewPass;

    /**
     * Constructor
     * @param view MVP's View
     */
    public LoginPresenter(ILoginMvp.View view){
        this.view = view;
        this.context = (Context)view;
        this.idViewEmail = R.id.tilEmail_Login;
        this.idViewPass = R.id.tilPass_Login;

    }

    /**
     * Method used to log in with credentials passed
     * @param email User's email
     * @param pass User's pass
     */
    @Override
    public void login(String email, String pass) {
        ErrorClass error = new ErrorClass();
        if (Utils.isNetworkAvailable(context)) {
             error = validateCredentials(email, pass);
        } else {
            error.setCode(ErrorClass.USER_CONNECTION_ERROR);
            error.setIdView(ErrorClass.VIEW_TOAST);
        }

        if (!error.isThereAnError()) {
            view.startLoginActivity();
        } else  {
            view.setMessageError(ErrorClass.getMessageError(context, error.getCode()), error.getIdView());
        }
    }

    /**
     * Method that check if credentials are valid
     * @param email User's email
     * @param pass User's pass
     * @return Return an ErrorClass. If it is all right, return null
     */
    private ErrorClass validateCredentials(String email, String pass) {
        ErrorClass error = new ErrorClass();
        if (TextUtils.isEmpty(email)) {
            error.setCode(ErrorClass.EMAIL_EMPTY);
            error.setIdView(idViewEmail);
        } else if (TextUtils.isEmpty(pass)) {
            error.setCode(ErrorClass.PASS_EMPTY);
            error.setIdView(idViewPass);
        } else {
            error.setCode(databaseLogin(email, pass));
            if (error.getCode() == ErrorClass.INCORRECT) {
                error.setIdView(ErrorClass.VIEW_TOAST);
            } else if (error.getCode() == ErrorClass.SERVER_CONNECTION_ERROR) {
                error.setIdView(ErrorClass.VIEW_TOAST);
            } else {
                error.setIfThereIsAnError(false);
            }
        }
        return error;
    }

    /**
     * Attempt to connect with database
     * @param email User's email
     * @param pass User's pass
     * @return Return an int with an ErrorClass code
     */
    private int databaseLogin(String email, String pass) {
        int result = ErrorClass.CORRECT;
        int correct = ErrorClass.CORRECT;
        boolean error = false;
        //TODO Database connection
        if (correct == result){
            //TODO set user in context PROPERLY

            ((Login_Application)((Context)view).getApplicationContext()).setUser(new User(1, email,email, pass));
        } else { //TODO Incorrect and/or error
            if (error) { //Connection error
                result = ErrorClass.SERVER_CONNECTION_ERROR;
            } else {
                result = ErrorClass.INCORRECT;
            }
        }
        return result;
    }

    /**
     * Method that log in without credentials
     */
    @Override
    public void nonPassLogin() {
        ((Login_Application)((Context)view).getApplicationContext()).setUser(null);
        view.startLoginActivity();
    }

    /**
     * Check if the user was logged the last time he used the app
     * If true, it tries to log in as usual
     */
    @Override
    public void isUserSet() {
        String email = ((Login_Application)((Context)view).getApplicationContext()).getEmailIfExists();
        String pass = ((Login_Application)((Context)view).getApplicationContext()).getPassIfExists();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            view.setCredentials(email,pass);
            login(email,pass);
        }
    }
}
