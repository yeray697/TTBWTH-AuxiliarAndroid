package com.phile.yrj.takethebullfighterwiththehorns.model;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
public class CustomTextWatcher implements TextWatcher {

    TextInputLayout view;

    public CustomTextWatcher(TextInputLayout view){
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
