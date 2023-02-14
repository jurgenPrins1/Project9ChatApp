package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputEditText editTextEmail;
    private Button forget;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextForgotPasswordEmail);
        forget = findViewById(R.id.buttonForget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mail = editTextEmail.getText().toString();

                if(!mail.equals("")) {
                    passwordReset(mail);
                    Intent i = new Intent(ResetPasswordActivity.this,MyLoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    public void passwordReset(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetPasswordActivity.this, "mail has been sent", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResetPasswordActivity.this, "woops mail not sent for some reason", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}