package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextInputEditText editTextUserName;
    private Button update;

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        editTextUserName = findViewById(R.id.editTextUserNameProfile);
        update = findViewById(R.id.buttonUpdate);

        getUserInfo();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    public void updateProfile()
    {
        String userName = editTextUserName.getText().toString();
        reference.child("Users").child(firebaseUser.getUid()).child("userName").setValue(userName);
    }

    public void getUserInfo(){
        reference.child("Users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String name;
                try {


                   name = snapshot.child("userName").getValue().toString();
                } catch (Exception e ){
                    name = "undefined";
                }
                editTextUserName.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}