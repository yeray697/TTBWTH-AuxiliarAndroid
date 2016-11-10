package com.phile.yrj.takethebullfighterwiththehorns;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ISignUpMvp;
import com.phile.yrj.takethebullfighterwiththehorns.presenter.LoginPresenter;
import com.phile.yrj.takethebullfighterwiththehorns.presenter.SignUpPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp_Activity extends AppCompatActivity implements ISignUpMvp.View {

    //Auxiliars (DatePicker)
    boolean isOkayClicked= false; //Used on datePickerDialog. If it is true, we set the date on the TextView
    DatePickerDialog.OnDateSetListener datePickerListener;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    //Fields
    TextInputLayout tilUser,tilPass,tilPassAgain,tilEmail,tilBirthday;
    EditText etUser,etPass,etPassAgain,etEmail, etBirthday;
    RadioGroup rgGender;
    Button btSignUp;
    //
    ISignUpMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        presenter = new SignUpPresenter(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        tilUser = (TextInputLayout) findViewById(R.id.tilUser_SignUp);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass_SignUp);
        tilPassAgain = (TextInputLayout) findViewById(R.id.tilPassAgain_SignUp);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail_SignUp);
        tilBirthday = (TextInputLayout) findViewById(R.id.tilBirthday_SignUp);
        etUser = (EditText) findViewById(R.id.etUser_SignUp);
        etPass = (EditText) findViewById(R.id.etPass_SignUp);
        etPassAgain = (EditText) findViewById(R.id.etPassAgain_SignUp);
        etEmail = (EditText) findViewById(R.id.etEmail_SignUp);
        etBirthday = (EditText) findViewById(R.id.etBirthday_SignUp);
        rgGender = (RadioGroup) findViewById(R.id.rgGender_SignUp);
        btSignUp = (Button) findViewById(R.id.btSignUp_SignUp);
        etBirthday.setInputType(InputType.TYPE_NULL);
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               datePickerDialog.show();
            }
        });
        tilUser.getEditText().addTextChangedListener(new MyTextWatcher(tilUser));
        tilPass.getEditText().addTextChangedListener(new MyTextWatcher(tilPass));
        tilPassAgain.getEditText().addTextChangedListener(new MyTextWatcher(tilPassAgain));
        tilEmail.getEditText().addTextChangedListener(new MyTextWatcher(tilEmail));
        tilBirthday.getEditText().addTextChangedListener(new MyTextWatcher(tilBirthday));
        etBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    datePickerDialog.show();
                }
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString()
                        ,email = etEmail.getText().toString()
                        ,pass1 = etPass.getText().toString()
                        ,pass2 = etPassAgain.getText().toString(),
                        birthday = etBirthday.getText().toString();
                char gender = ((RadioButton)findViewById(rgGender.getCheckedRadioButtonId())).getText().toString().charAt(0);
                presenter.validateCredentials(user,email,pass1,pass2,birthday,gender);
            }
        });
        setDateTimeDialog();
    }

    private void setDateTimeDialog() {
        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (isOkayClicked) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    etBirthday.setText(dateFormatter.format(newDate.getTime()));
                }
                isOkayClicked = false;
            }
        };
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, datePickerListener,newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                getString(R.string.datetimepicker_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            dialog.cancel();
                            isOkayClicked = false;
                        }
                    }
                });

        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                getString(R.string.datetimepicker_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            isOkayClicked = true;
                            DatePicker datePicker = datePickerDialog
                                    .getDatePicker();
                            datePickerListener.onDateSet(datePicker,
                                    datePicker.getYear(),
                                    datePicker.getMonth(),
                                    datePicker.getDayOfMonth());
                        }
                    }
                });
    }

    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView){
            case R.id.tilUser_SignUp:
                tilUser.setError(messageError);
                break;
            case R.id.tilEmail_SignUp:
                tilEmail.setError(messageError);
                break;
            case R.id.tilPass_SignUp:
                tilPass.setError(messageError);
                break;
            case R.id.tilPassAgain_SignUp:
                tilPassAgain.setError(messageError);
                break;
            case R.id.tilBirthday_SignUp:
                tilBirthday.setError(messageError);
                break;
            case LoginPresenter.IDVIEWTOAST:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class MyTextWatcher implements TextWatcher{

        TextInputLayout view;

        public MyTextWatcher(TextInputLayout view){
            this.view = view;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            view.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
