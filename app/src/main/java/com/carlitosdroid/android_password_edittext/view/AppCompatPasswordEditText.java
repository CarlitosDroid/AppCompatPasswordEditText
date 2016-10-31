package com.carlitosdroid.android_password_edittext.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;

import com.carlitosdroid.android_password_edittext.MainActivity;
import com.carlitosdroid.android_password_edittext.R;
import com.carlitosdroid.android_password_edittext.util.Util;

/**
 * Created by Carlos Vargas on 10/30/16.
 * CarlitosDroid
 */

public class AppCompatPasswordEditText extends FrameLayout implements View.OnClickListener{

    TextInputLayout tilPassword;
    AppCompatEditText editTextPassword;
    AppCompatImageView imgEye;
    private Context context;
    private boolean isPasswordVisible = true;
    Drawable icon;

    private int showPasswordIcon;
    private int hidePasswordIcon;
    private int bgTextInputLayout;
    private int hintColor;
    private int inputType;

    public AppCompatPasswordEditText(Context context) {
        super(context);
        init(context, null);
    }

    public AppCompatPasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppCompatPasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs){
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View labelView = inflater.inflate(R.layout.password_edittext_view, this, true);

        imgEye = (AppCompatImageView) labelView.findViewById(R.id.imgEye);
        editTextPassword = (AppCompatEditText) labelView.findViewById(R.id.editTextPassword);
        tilPassword = (TextInputLayout) labelView.findViewById(R.id.tilPassword);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextPassword);
            this.showPasswordIcon = typedArray.getResourceId(R.styleable.EditTextPassword_showPasswordIcon, R.drawable.ic_visibility_white_24dp);
            this.hidePasswordIcon = typedArray.getResourceId(R.styleable.EditTextPassword_hidePasswordIcon, R.drawable.ic_visibility_off_white_24dp);
            this.bgTextInputLayout = typedArray.getResourceId(R.styleable.EditTextPassword_bgTextInputLayout, android.R.color.transparent);
            this.hintColor = typedArray.getResourceId(R.styleable.EditTextPassword_hintColor, R.color.md_grey_500);
            typedArray.recycle();
        }

        tilPassword.setHintTextAppearance(hintColor);
        tilPassword.setBackgroundResource(bgTextInputLayout);
        this.inputType = editTextPassword.getInputType();
        togglePassword();

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validPassword();
            }
        });

        imgEye.setOnClickListener(this);
    }

    public boolean validPassword(){
        return Util.validPasswordField(tilPassword, editTextPassword, context.getString(R.string.error_enter_password));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgEye:
                togglePassword();
                break;
        }

    }

    private void togglePassword() {
        if (this.isPasswordVisible) {
            editTextPassword.setInputType(this.inputType);
        } else {

            int TEXT_PASSWORD = 129;
            if (this.inputType == TEXT_PASSWORD) {
                editTextPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                editTextPassword.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            }
        }

        this.isPasswordVisible = !this.isPasswordVisible;
        int textLength = editTextPassword.getText().length();
        editTextPassword.setSelection(textLength, textLength);
        setupIcon();
    }


    private void setupIcon() {
        this.icon = this.isPasswordVisible ? ContextCompat.getDrawable(context, this.showPasswordIcon) : ContextCompat.getDrawable(context, hidePasswordIcon);
       imgEye.setImageDrawable(icon);
    }

    public String getText(){
        return editTextPassword.getText().toString();
    }





}
