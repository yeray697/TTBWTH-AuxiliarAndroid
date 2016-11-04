package com.phile.yrj.takethebullfighterwiththehorns;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Setting presenter
        presenter = new LoginPresenter(this);
        //Setting controls
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btSubmit = (Button) findViewById(R.id.btSubmit);

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
    }

    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView){
            case R.id.tilPass:
                tilPass.setError(messageError);
                break;
            case R.id.tilEmail:
                tilEmail.setError(messageError);
                break;
            case LoginPresenter.IDVIEWTOAST:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
