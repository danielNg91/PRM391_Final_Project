package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.models.RegisterUserRequest;
import com.ngoctin.intuitionmobile.services.RegisterService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btnSignUp = this.findViewById(R.id.btnSignUp);
        Button btnForwardSignIn = this.findViewById(R.id.btnForwardSignIn);
        EditText editTextUsername = this.findViewById(R.id.etUsername);
        EditText editTextPassword = this.findViewById(R.id.etPassword);
        EditText editTextConfirmPassword = this.findViewById(R.id.etConfirmPassword);
        EditText editTextPhoneNumber = this.findViewById(R.id.etPhoneNumber);
        EditText editTextFullname = this.findViewById(R.id.etFullname);
        List<EditText> editTextList = new ArrayList<>();
        editTextList.add(editTextUsername);
        editTextList.add(editTextPassword);
        editTextList.add(editTextConfirmPassword);
        editTextList.add(editTextFullname);
        editTextList.add(editTextPhoneNumber);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String fullname = editTextFullname.getText().toString();
                RegisterUserRequest registerUserRequest
                        = new RegisterUserRequest(username,password,fullname,phoneNumber);
                int validateResult = RegisterService.validate(registerUserRequest,confirmPassword);
                String message = "Register Successfully ! Please back to Login ";
                switch (validateResult){
                    case 0 :
                        ApplicationUtils.clearAllEditTexts(editTextList);
                        RegisterService.register(registerUserRequest,message,SignUpActivity.this);
                        break;
                    case 1 :
                        message = "Username can not contains special keys , " +
                                "except for '.' and '_' ! ";
                        ApplicationUtils.clearEditTextWithToast(editTextUsername,message,SignUpActivity.this);
                        break;
                    case 2 :
                        message = "Password must be from 8 to 20 characters, includes at least : " +
                                "1 Uppercase Letter - 1 Lower Case Letter - 1 Special Key - 1 Number !";
                        ApplicationUtils.clearEditTextWithToast(editTextPassword,message,SignUpActivity.this);
                        break;
                    case 3 :
                        message = "Confirm Password must match with Password !";
                        ApplicationUtils.clearEditTextWithToast(editTextConfirmPassword,message,SignUpActivity.this);
                        break;
                    case 4 :
                        message = "Full name can only contain letters ! ";
                        ApplicationUtils.clearEditTextWithToast(editTextFullname,message,SignUpActivity.this);
                        break;
                    case 5 :
                        message = "Phone Number must be from 10 - 12 numbers !";
                        ApplicationUtils.clearEditTextWithToast(editTextPhoneNumber,message,SignUpActivity.this);
                        break;
                }
            }
        });

        btnForwardSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}