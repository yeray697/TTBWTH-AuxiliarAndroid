package com.phile.yrj.takethebullfighterwiththehorns.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.phile.yrj.takethebullfighterwiththehorns.R;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ILoginMvp;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */

public class LoginPresenter implements ILoginMvp.Presenter{
    ILoginMvp.View view;
    private int idViewEmail;
    private int idViewPass;
    public static final int IDVIEWCORRECT = -1;
    public static final int IDVIEWTOAST = -2;

    public LoginPresenter(ILoginMvp.View view){
        this.view = view;
        idViewEmail = R.id.tilEmail_Login;
        idViewPass = R.id.tilPass_Login;
    }
    @Override
    public void validateCredentials(String email, String pass) {
        String _email = email,
                _pass = pass;
        String error;
        int idView;
        int result;

        if (TextUtils.isEmpty(_email)){
            result = ILoginMvp.EMAIL_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_email_login);
            idView = idViewEmail;
        } else if (TextUtils.isEmpty(_pass)){
            result = ILoginMvp.PASS_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_pass_login);
            idView = idViewPass;
        } else {
            //Fields are not empty, so now we check with database
            result = databaseLogin(email,pass);
            if (result == ILoginMvp.INCORRECT){
                error = ((Context)view).getResources().getString(R.string.incorrect_login);
                idView = IDVIEWTOAST;
            } else if (result == ILoginMvp.CONNECTIONERROR){
                //There is not connection
                error = ((Context)view).getResources().getString(R.string.no_connection_login);
                idView = IDVIEWTOAST;
            } else { //If there is no error
                error = "";
                idView = IDVIEWCORRECT;
                //TODO Open a new activity
                //((LoginApplication)((Context)view).getApplicationContext()).setUser(new User(_email, _pass));
                //Intent intent = new Intent((Context)view,Product_Activity.class);
                //((Context)view).startActivity(intent);
            }
        }
        //If there is an error, we set it on the view
        if (result != ILoginMvp.CORRECT) {
            view.setMessageError(error, idView);
        }
    }

    @Override
    public int databaseLogin(String email, String pass) {
        int result = ILoginMvp.CORRECT;
        boolean correct = true;
        boolean error = false;
        //TODO Database connection
        if (correct){
            //TODO set user in context
            //((LoginApplication)((Context)view).getApplicationContext()).setUser(new User(_email, _pass));
        } else { //Incorrect and/or error
            if (error) { //Connection error
                result = ILoginMvp.CONNECTIONERROR;
            } else {
                result = ILoginMvp.INCORRECT;
            }
        }
        return result;
    }
}
