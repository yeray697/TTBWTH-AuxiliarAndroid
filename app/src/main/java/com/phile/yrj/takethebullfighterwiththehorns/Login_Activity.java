package com.phile.yrj.takethebullfighterwiththehorns;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ILoginMvp;
import com.phile.yrj.takethebullfighterwiththehorns.model.CustomTextWatcher;
import com.phile.yrj.takethebullfighterwiththehorns.model.ErrorClass;
import com.phile.yrj.takethebullfighterwiththehorns.presenter.LoginPresenter;
import com.phile.yrj.takethebullfighterwiththehorns.utils.Utils;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.1
 * Created on 04/11/16
 */
public class Login_Activity extends AppCompatActivity implements ILoginMvp.View{

    ILoginMvp.Presenter presenter;
    private TextInputLayout tilEmail, tilPass;
    private EditText etEmail, etPass;
    private TextView tvForgotPass,tvNonRegister;
    private Button btSubmit, btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Setting viewsç
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail_Login);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass_Login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btSubmit = (Button) findViewById(R.id.btLogin_Login);
        btSignUp = (Button) findViewById(R.id.btSignUp_Login);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass_Login);
        tvNonRegister = (TextView) findViewById(R.id.tvNonRegister2_Login);
        //Setting view's additional configuration
        etPass.setTransformationMethod(new PasswordTransformationMethod());
        //Setting listeners
        etEmail.addTextChangedListener(new CustomTextWatcher(tilEmail));
        etPass.addTextChangedListener(new CustomTextWatcher(tilPass));
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(true);
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPass();
            }
        });
        tvNonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(false);
            }
        });
        etPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login(true);
                }
                return false;
            }
        });
        //Setting presenter
        presenter = new LoginPresenter(this);
        presenter.isUserSet();
    }

    /**
     * Show an error on the device
     * @param messageError ErrorClass that will be showed
     * @param idView View where error will be showed. idView = ErrorClass.VIEW_TOAST will show the error on a Toast
     */
    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView){
            case ErrorClass.VIEW_TOAST:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
            default:
                ((TextInputLayout)findViewById(idView)).setError(messageError);
                break;
        }
    }

    /**
     * Start the main activity
     */
    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this,Home_Activity.class);
        this.startActivity(intent);
        finish();
    }

    /**
     * Set credentials passed as variable.
     * That credentials are set if user was logged the last time he used the app
     * @param email User's email
     * @param pass User's pass
     */
    @Override
    public void setCredentials(String email, String pass) {
        etEmail.setText(email);
        etPass.setText(pass);
    }

    /**
     * Try to log in. If it is right, we will access to the next Activity
     * @param logged If it is false, you will not be logged, you will be a non registered user
     */
    public void login(boolean logged){
        Utils.hideKeyboard(Login_Activity.this);
        if (logged) {
            String email = etEmail.getText().toString(),
                    pass = etPass.getText().toString();
            presenter.login(email, pass);
        } else {
            presenter.nonPassLogin();
        }
    }

    /**
     * Start the activity to sign up a new user
     */
    public void signUp(){
        Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
        startActivity(intent);
    }

    /**
     * Method that redirect to a web to recover an account
     */
    private void forgotPass() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }
}
