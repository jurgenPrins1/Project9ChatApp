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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText username, password, email;
    Button register;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.editTextUserNameSignup);
        password = findViewById(R.id.editTextPasswordSignup);
        email = findViewById(R.id.editTextEmailSignup);
        register = findViewById(R.id.buttonRegister);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userMail = email.getText().toString();
                String userPassword = password.getText().toString();
                String userUserName = username.getText().toString();

                if (!userMail.equals("")&& !userPassword.equals("")&& !userUserName.equals("")){
                    signup(userMail,userPassword,userUserName);


                }


            }
        });

    }

    private void signup(String userMail, String userPassword, String userUserName) {

        auth.createUserWithEmailAndPassword(userMail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    reference.child("Users").child(auth.getUid()).child("userName").setValue(userUserName);
                    Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }else {

                    Toast.makeText(SignUpActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}