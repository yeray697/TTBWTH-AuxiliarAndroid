package com.phile.yrj.takethebullfighterwiththehorns.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.phile.yrj.takethebullfighterwiththehorns.R;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ILoginMvp;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ISignUpMvp;

/**
 * Created by yeray697 on 5/11/16.
 */

public class SignUpPresenter implements ISignUpMvp.Presenter{
    ISignUpMvp.View view;
    private int idViewUser;
    private int idViewEmail;
    private int idViewPass;
    private int idViewPass2;
    private int idViewBirthday;
    private int idViewGender;
    public static final int IDVIEWCORRECT = -1;
    public static final int IDVIEWTOAST = -2;

    public SignUpPresenter(ISignUpMvp.View view) {
        this.view = view;
        idViewUser = R.id.tilUser_SignUp;
        idViewEmail = R.id.tilEmail_SignUp;
        idViewPass = R.id.tilPass_SignUp;
        idViewPass2 = R.id.tilPassAgain_SignUp;
        idViewBirthday = R.id.tilBirthday_SignUp;
        idViewGender = IDVIEWTOAST;
    }

    @Override
    public void validateCredentials(String user, String email, String pass, String pass2, String birthday, char gender) {
        String _user = user,
                _email = email,
                _pass = pass,
                _pass2 = pass2,
                _birthday = birthday;
        char _gender = gender;
        String error;
        int idView;
        int result;

        if (TextUtils.isEmpty(_user)){
            result = ISignUpMvp.USER_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_signup);
            idView = idViewUser;
        } else if (_user.length() < ISignUpMvp.MINUSER_LENGTH || _user.length() > ISignUpMvp.MAXUSER_LENGTH){
            result = ISignUpMvp.USER_LENGTH;
            error = ((Context)view).getResources().getString(R.string.user_length_signup);
            idView = idViewUser;
        } else if (TextUtils.isEmpty(_email)){
            result = ISignUpMvp.EMAIL_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_signup);
            idView = idViewEmail;
        } else if (TextUtils.isEmpty(_pass)){
            result = ISignUpMvp.PASS1_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_signup);
            idView = idViewPass;
        } else if (_pass.length() < ISignUpMvp.MINPASS_LENGTH || _pass.length() > ISignUpMvp.MAXPASS_LENGTH){
            result = ISignUpMvp.PASS_LENGTH;
            error = ((Context)view).getResources().getString(R.string.pass_length_signup);
            idView = idViewPass;
        } else if (TextUtils.isEmpty(_pass2)){
            result = ISignUpMvp.PASS2_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_signup);
            idView = idViewPass2;
        } else if (!_pass.matches(ISignUpMvp.PASS_REGEX)){
            result = ISignUpMvp.PASS_CHAR;
            error = ((Context)view).getResources().getString(R.string.char_pass_signup);
            idView = idViewPass;
        } else if (!_pass.equals(_pass2)){
            result = ISignUpMvp.DIFFERENT_PASS;
            error = ((Context)view).getResources().getString(R.string.different_pass_signup);
            idView = idViewPass2;
        } else if (TextUtils.isEmpty(_birthday)){
            result = ISignUpMvp.BIRTHDAY_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_birthday_signup);
            idView = idViewBirthday;
        } else if (TextUtils.isEmpty(String.valueOf(_gender))){
            result = ISignUpMvp.GENDER_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_gender_signup);
            idView = idViewGender;
        } else {
            //Fields are not empty, so now we try to register in the database
            result = databaseSignUp(_user,_email,_pass,_pass2,_birthday,_gender);
            if (result == ISignUpMvp.USER_USED){
                error = ((Context)view).getResources().getString(R.string.user_used_signup);
                idView = idViewUser;
            } else if (result == ISignUpMvp.EMAIL_USED){
                error = ((Context)view).getResources().getString(R.string.email_used_signup);
                idView = idViewEmail;
            } else if (result == ISignUpMvp.CONNECTIONERROR){
                //There is not connection
                error = ((Context)view).getResources().getString(R.string.no_connection_signup);
                idView = IDVIEWTOAST;
            } else { //If there is no error
                error = "";
                idView = IDVIEWCORRECT;
                ((Activity)view).finish();
            }
        }
        //If there is an error, we set it on the view
        if (result != ILoginMvp.CORRECT) {
            view.setMessageError(error, idView);
        }
    }

    @Override
    public int databaseSignUp(String user, String email, String pass, String passAgain, String birthday, char gender) {
        int result = ISignUpMvp.CORRECT;
        boolean correct = true;
        //TODO Database connection
        if (!correct) { //Incorrect and/or error
            //TODO: check with database if email or username is already in use
            result = ISignUpMvp.CONNECTIONERROR;
            result = ISignUpMvp.USER_USED;
            result = ISignUpMvp.EMAIL_USED;
        }
        return result;
    }
}
