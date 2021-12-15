package com.example.regisration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class verify extends AppCompatActivity {
     EditText verifyotp;
     String getbackendotp;
     Button button;
     TextView textView;
     FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        getbackendotp=getIntent().getStringExtra("backendotp");
        verifyotp=findViewById(R.id.verify_otp);
        button=findViewById(R.id.verify_otp_btn);
        textView=findViewById(R.id.textViewnumber);
        Auth=FirebaseAuth.getInstance();
        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("Mobilename")
        ));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verifyotp.getText().toString().isEmpty()){
                    if(!getbackendotp.isEmpty()){
                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                getbackendotp,verifyotp.getText().toString()
                        );
                        Auth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                      if(task.isSuccessful()){
                                          Intent intent=new Intent(verify.this,mainchat.class);
                                          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                          startActivity(intent);
                                          finishAffinity();
                                      }
                                      else{
                                          Toast.makeText(verify.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                                      }
                                    }
                                });
                    }
                    else{
                        Toast.makeText(verify.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}