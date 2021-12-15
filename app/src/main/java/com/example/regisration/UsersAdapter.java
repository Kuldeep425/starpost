package com.example.regisration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.regisration.Activities.chatting;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>
{
    ArrayList<User>list;
    Context context;
    public UsersAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_conversation,parent,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
      User users=list.get(position);
      holder.username.setText(users.getName());
        Glide.with(context).load(users.getUprofileimage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, chatting.class);
                intent.putExtra("name",users.getName());
                intent.putExtra("uid",users.getUid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{
          ImageView image;
          TextView username,lastmessage,messagetime;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.user_image);
            username=itemView.findViewById(R.id.textViewusername);
            lastmessage=itemView.findViewById(R.id.textViewlastmessage);
            messagetime=itemView.findViewById(R.id.texttime);
        }
    }




}
