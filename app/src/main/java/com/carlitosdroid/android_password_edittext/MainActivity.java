package com.carlitosdroid.android_password_edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.carlitosdroid.android_password_edittext.view.AppCompatPasswordEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    AppCompatPasswordEditText txtPassword;
    AppCompatButton btnShowValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPassword = (AppCompatPasswordEditText) findViewById(R.id.txtPassword);
        btnShowValue = (AppCompatButton) findViewById(R.id.btnShowValue);
        btnShowValue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(!txtPassword.validPassword()){
            return;
        }
        Toast.makeText(this, txtPassword.getText(), Toast.LENGTH_SHORT).show();
    }

}
