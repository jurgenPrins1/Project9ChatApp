package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    List<String> userList;
    String userName;
    Context mContext;

    FirebaseDatabase database;
    DatabaseReference reference;

    public UsersAdapter(List<String> userList, String userName, Context mContext) {
        this.userList = userList;
        this.userName = userName;
        this.mContext = mContext;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        reference.child("Users").child(userList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String otherName = snapshot.child("userName").getValue().toString();

                holder.textViewUsers.setText(otherName);

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(mContext,MyChatActivity.class);
                        i.putExtra("userName", userName);
                        i.putExtra("otherName", otherName);
                        mContext.startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewUsers;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUsers = itemView.findViewById(R.id.textViewUsers);
        }
    }
}
