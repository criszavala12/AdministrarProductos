package com.example.apppedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText, confirmPasswordEditText;
    Button createAccountBtn;
    ProgressBar progressBar;
    TextView loginBtnTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        createAccountBtn = findViewById(R.id.create_account_btn);
        progressBar = findViewById(R.id.progress_bar);
        loginBtnTextView = findViewById(R.id.login_text_view_btn);

        //loginBtnTextView.setOnClickListener(v -> finish());

        loginBtnTextView.setOnClickListener((v)->startActivity(new Intent(CreateAccountActivity.this,LoginActivity
                .class)) );
        createAccountBtn.setOnClickListener((v)->createAccount());

    }

    void createAccount(){
        String email  = emailEditText.getText().toString();
        String password  = passwordEditText.getText().toString();
        String confirmPassword  = confirmPasswordEditText.getText().toString();

        boolean isValidated = validateData(email,password,confirmPassword);
        if(!isValidated){
            return;
        }




    }

    boolean validateData(String email,String password,String confirmPassword){
        //validate the data that are input by user.

        if (email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()){
            emailEditText.setError("Email is empty");
            passwordEditText.setError("Password  is empty");
            confirmPasswordEditText.setError("Confirm Password is empty");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmPassword)){
            confirmPasswordEditText.setError("Password not matched");
            return false;
        }
        return true;
    }
}