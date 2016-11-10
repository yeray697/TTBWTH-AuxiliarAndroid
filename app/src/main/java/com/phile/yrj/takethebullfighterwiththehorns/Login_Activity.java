package com.phile.yrj.takethebullfighterwiththehorns;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ILoginMvp;
import com.phile.yrj.takethebullfighterwiththehorns.presenter.LoginPresenter;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
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
        //Setting presenter
        presenter = new LoginPresenter(this);
        //Setting controls
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail_Login);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass_Login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btSubmit = (Button) findViewById(R.id.btLogin_Login);
        btSignUp = (Button) findViewById(R.id.btSignUp_Login);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass_Login);
        tvNonRegister = (TextView) findViewById(R.id.tvNonRegister2_Login);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilEmail.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPass.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString(),
                        pass = etPass.getText().toString();
                presenter.validateCredentials(email,pass);
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvNonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nonPassLogin();
            }
        });
    }


    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView){
            case R.id.tilPass_Login:
                tilPass.setError(messageError);
                break;
            case R.id.tilEmail_Login:
                tilEmail.setError(messageError);
                break;
            case LoginPresenter.IDVIEWTOAST:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void login() {
        Intent intent = new Intent(this,Main_Activity.class);
        this.startActivity(intent);
    }
}
