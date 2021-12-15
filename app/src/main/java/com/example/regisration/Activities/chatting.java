package com.example.regisration.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.example.regisration.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class chatting extends AppCompatActivity {

     ArrayList<com.example.regisration.Message>messages;
     String senderRoom,receiverRoom;
     FirebaseDatabase database;
     EditText typemessageS;
     RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        recyclerView=findViewById(R.id.recyclev);
        database=FirebaseDatabase.getInstance();
         messages = new ArrayList<>();



        String name=getIntent().getStringExtra("name");
        String receiveUid=getIntent().getStringExtra("uid");
        String senderUid= FirebaseAuth.getInstance().getUid();
        senderRoom=senderUid+receiveUid;
        receiverRoom=receiveUid+senderUid;
        database=FirebaseDatabase.getInstance();
         typemessageS=findViewById(R.id.typemessage);
     /*    database.getReference()
                 .child("chats")
                 .child(senderRoom)
                .child(messages)
                 .addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         messages.clear();
                         for(DataSnapshot snapshot1:snapshot.getChildren()) {
                             Message message = snapshot1.getValue(Message.class);
                             messages.add(message);
                         }
                         Adapter.notifyDataSetChanged();
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });
         findViewById(R.id.imagesent_btn).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String messagetxt=typemessageS.getText().toString();
                 Date date = new Date();
                 Message message;
                 message = new Message(messagetxt,senderUid,date.getTime());
                 database.getReference().child("Chats")
                                   .child(senderRoom)
                                //   .child("messages")
                                   .push()
                                    .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {
                         database.getReference().child("Chats")
                                 .child(receiverRoom)
                                 .child("messages")
                                 .push()
                                 .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {

                             }
                         });
                     }
                 });
             }
         });

      */
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}