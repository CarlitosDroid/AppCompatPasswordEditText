package com.carlitosdroid.android_password_edittext.util;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;

/**
 * Created by Carlos Vargas on 10/30/16.
 * CarlitosDroid
 */

public class Util {

    public static boolean validPasswordField(TextInputLayout til, AppCompatEditText txt, String emptyField){
        if(txt.getText().toString().trim().isEmpty()){
            til.setErrorEnabled(true);
            til.setError(emptyField);
            til.clearFocus();
            txt.requestFocus();
            return false;
        }else{
            til.setErrorEnabled(false);
            til.setError(null);
            return true;
        }
    }

}
