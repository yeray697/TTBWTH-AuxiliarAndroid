package com.phile.yrj.takethebullfighterwiththehorns;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ISignUpMvp;
import com.phile.yrj.takethebullfighterwiththehorns.model.CustomTextWatcher;
import com.phile.yrj.takethebullfighterwiththehorns.model.ErrorClass;
import com.phile.yrj.takethebullfighterwiththehorns.presenter.SignUpPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.1
 * Created on 04/11/16
 */
public class SignUp_Activity extends AppCompatActivity implements ISignUpMvp.View {

    boolean isOkayClicked= false;                               //Used on datePickerDialog. If it is true, we set the date on the TextView
    DatePickerDialog.OnDateSetListener datePickerListener;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    TextInputLayout tilUser,tilPass,tilPassAgain,tilEmail,tilBirthday;
    EditText etUser,etPass,etPassAgain,etEmail, etBirthday;
    RadioGroup rgGender;
    Button btSignUp;
    ISignUpMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Setting presenter
        presenter = new SignUpPresenter(this);
        //Setting a button on the actionbar to return to previous activity
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        //Setting views
        tilUser = (TextInputLayout) findViewById(R.id.tilUser_SignUp);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass_SignUp);
        tilPassAgain = (TextInputLayout) findViewById(R.id.tilPassAgain_SignUp);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail_SignUp);
        tilBirthday = (TextInputLayout) findViewById(R.id.tilBirthday_SignUp);
        etUser = (EditText) findViewById(R.id.etUser_SignUp);
        etPass = (EditText) findViewById(R.id.etPass_SignUp);
        etPassAgain = (EditText) findViewById(R.id.etPassAgain_SignUp);
        etEmail = (EditText) findViewById(R.id.etEmail_SignUp);
        rgGender = (RadioGroup) findViewById(R.id.rgGender_SignUp);
        btSignUp = (Button) findViewById(R.id.btSignUp_SignUp);
        etBirthday = (EditText) findViewById(R.id.etBirthday_SignUp);
        setDatePickerDialog();
            //Data formatter to get the birthday
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        //Setting view's additional configuration
        etPass.setTransformationMethod(new PasswordTransformationMethod());
        etPassAgain.setTransformationMethod(new PasswordTransformationMethod());
        etBirthday.setFocusable(false);
        etBirthday.setClickable(true);
        etBirthday.setLongClickable(false);
        //Setting listeners
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               datePickerDialog.show();
            }
        });
        tilUser.getEditText().addTextChangedListener(new CustomTextWatcher(tilUser));
        tilPass.getEditText().addTextChangedListener(new CustomTextWatcher(tilPass));
        tilPassAgain.getEditText().addTextChangedListener(new CustomTextWatcher(tilPassAgain));
        tilEmail.getEditText().addTextChangedListener(new CustomTextWatcher(tilEmail));
        tilBirthday.getEditText().addTextChangedListener(new CustomTextWatcher(tilBirthday));
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
            public void onClick(View v) {signUp();
            }
        });
    }

    /**
     * Method that try to sign up the new user
     */
    private void signUp() {
        String user = etUser.getText().toString()
                ,email = etEmail.getText().toString()
                ,pass1 = etPass.getText().toString()
                ,pass2 = etPassAgain.getText().toString(),
                birthday = etBirthday.getText().toString();
        char gender = ((RadioButton)findViewById(rgGender.getCheckedRadioButtonId())).getText().toString().charAt(0);
        presenter.signUp(user,email,pass1,pass2,birthday,gender);
    }

    /**
     * Set all the configuration of a DatePickerDialog, showed when etBirthday gets the focus or is clicked
     */
    private void setDatePickerDialog() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
