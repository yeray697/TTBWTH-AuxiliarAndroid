package com.phile.yrj.takethebullfighterwiththehorns.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.phile.yrj.takethebullfighterwiththehorns.R;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ISignUpMvp;
import com.phile.yrj.takethebullfighterwiththehorns.model.ErrorClass;
import com.phile.yrj.takethebullfighterwiththehorns.utils.Utils;


/**
 * @author Yeray Ruiz Juárez
 * @version 1.1
 * Created on 04/11/16
 */
public class SignUpPresenter implements ISignUpMvp.Presenter{
    ISignUpMvp.View view;
    Context context;
    private int idViewUser;
    private int idViewEmail;
    private int idViewPass;
    private int idViewPass2;
    private int idViewBirthday;
    private int idViewGender;

    /**
     * Constructor
     * @param view MVP's View
     */
    public SignUpPresenter(ISignUpMvp.View view) {
        this.view = view;
        this.idViewUser = R.id.tilUser_SignUp;
        this.idViewEmail = R.id.tilEmail_SignUp;
        this.idViewPass = R.id.tilPass_SignUp;
        this.idViewPass2 = R.id.tilPassAgain_SignUp;
        this.idViewBirthday = R.id.tilBirthday_SignUp;
        this.idViewGender = ErrorClass.VIEW_TOAST;
        this.context = (Context) view;
    }

    /**
     * Method that sign up a new user if it is possible
     * @param user User's username
     * @param email User's email
     * @param pass User's password
     * @param passAgain User's password
     * @param birthday User's birthday
     * @param gender User's gender
     */
    @Override
    public void signUp(String user, String email, String pass, String passAgain, String birthday, char gender) {
        ErrorClass error = new ErrorClass();
        if (Utils.isNetworkAvailable(context)) {
            error = validateCredentials(user, email, pass, passAgain, birthday, gender);
        } else {
            error.setCode(ErrorClass.USER_CONNECTION_ERROR);
            error.setIdView(ErrorClass.VIEW_TOAST);
        }

        if (!error.isThereAnError()) {
            view.finish();
        } else  {
            view.setMessageError(ErrorClass.getMessageError(context, error.getCode()), error.getIdView());
        }
    }
    /**
     * Method that sign up a new user if it is possible
     * @param user User's username
     * @param email User's email
     * @param pass User's password
     * @param passAgain User's password
     * @param birthday User's birthday
     * @param gender User's gender
     */
    private ErrorClass validateCredentials(String user, String email, String pass, String passAgain, String birthday, char gender) {
        ErrorClass error = new ErrorClass();
        if (TextUtils.isEmpty(user)){
            error.setCode(ErrorClass.USER_EMPTY);
            error.setIdView(idViewUser);
        } else if (user.length() < ISignUpMvp.MINUSER_LENGTH || user.length() > ISignUpMvp.MAXUSER_LENGTH){
            error.setCode(ErrorClass.USER_LENGTH);
            error.setIdView(idViewUser);
        } else if (!user.matches(ISignUpMvp.USER_REGEX)){
            error.setCode(ErrorClass.USER_CHARS);
            error.setIdView(idViewUser);
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            error.setCode(ErrorClass.INVALID_EMAIL);
            error.setIdView(idViewEmail);
        } else if (TextUtils.isEmpty(pass)){
            error.setCode(ErrorClass.INVALID_PASS);
            error.setIdView(idViewPass);
        } else if (pass.length() < ISignUpMvp.MINPASS_LENGTH || pass.length() > ISignUpMvp.MAXPASS_LENGTH){
            error.setCode(ErrorClass.PASS_LENGTH);
            error.setIdView(idViewPass);
        } else if (TextUtils.isEmpty(passAgain)){
            error.setCode(ErrorClass.INVALID_PASS);
            error.setIdView(idViewPass2);
        } else if (!pass.equals(passAgain)){
            error.setCode(ErrorClass.DIFFERENT_PASS);
            error.setIdView(idViewPass2);
        } else if (TextUtils.isEmpty(birthday)){
            error.setCode(ErrorClass.BIRTHDAY_EMPTY);
            error.setIdView(idViewBirthday);
        } else if (TextUtils.isEmpty(String.valueOf(gender))){
            error.setCode(ErrorClass.GENDER_EMPTY);
            error.setIdView(idViewGender);
        } else {
            error.setCode(databaseSignUp(user,email,pass,passAgain,birthday,gender));
            if (error.getCode() == ErrorClass.USER_USED){
                error.setIdView(idViewUser);
            } else if (error.getCode() == ErrorClass.EMAIL_USED){
                error.setIdView(idViewEmail);
            } else if (error.getCode() == ErrorClass.SERVER_CONNECTION_ERROR){
                error.setIdView(ErrorClass.VIEW_TOAST);
            } else {
                error.setIfThereIsAnError(false);
                view.finish();
            }
        }
        return error;
    }

    /**
     * Tries to sign up the user
     * @param user User's username
     * @param email User's email
     * @param pass User's password
     * @param passAgain User's password
     * @param birthday User's birthday
     * @param gender User's gender
     * @return Return an int with an ErrorClass code
     */
    public int databaseSignUp(String user, String email, String pass, String passAgain, String birthday, char gender) {
        int result = ErrorClass.CORRECT;
        boolean correct = true;
        //TODO Database connection
        if (!correct) { //Incorrect and/or error
            //TODO: check with database if email or username is already in use
            result = ErrorClass.SERVER_CONNECTION_ERROR;
            result = ErrorClass.USER_USED;
            result = ErrorClass.EMAIL_USED;
        }
        return result;
    }
}
