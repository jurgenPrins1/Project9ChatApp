package com.example.chatapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    List<ModelClass> list;
    String username;
    boolean status;
    int send;
    int receive;

    public MessageAdapter(List<ModelClass> list, String username) {
        this.list = list;
        this.username = username;
        status = false;
        send = 1;
        receive =2;
    }

    @NonNull
    
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == send){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_send,parent,false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_receive,parent, false);
        }


        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            if (status){
                textView = itemView.findViewById(R.id.textViewSend);
            }else {
                textView = itemView.findViewById(R.id.textViewReceive);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getFrom().equals(username)){
            status = true;
            return send;
        }else {
            status = false;
            return  receive;
        }
    }
}
