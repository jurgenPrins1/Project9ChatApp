package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyLoginActivity extends AppCompatActivity {

    private TextInputEditText editTextMail, editTextPassword;
    private Button buttonSignup, buttonSignIn;
    private TextView forgotPassword;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){
            Intent i = new Intent(MyLoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);

        editTextMail = findViewById(R.id.EditTextEmail);
        editTextPassword = findViewById(R.id.EditTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignup = findViewById(R.id.buttonSignUp);
        forgotPassword = findViewById(R.id.textViewForgotPassword);

        auth = FirebaseAuth.getInstance();

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MyLoginActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();

            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editTextMail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!email.equals("") && !password.equals("")) {

                    signin(email, password);
                }else {
                    Toast.makeText(MyLoginActivity.this, "you need to give a email and password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MyLoginActivity.this,ResetPasswordActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

    public void signin(String email, String password){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(MyLoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }else {

                    Toast.makeText(MyLoginActivity.this, "something went wrong with login", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


}