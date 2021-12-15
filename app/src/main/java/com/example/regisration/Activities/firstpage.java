package com.example.regisration.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.regisration.MainActivity;
import com.example.regisration.R;


public class firstpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);


        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {


                Intent intent=new Intent(firstpage.this, MainActivity.class);
                startActivity(intent);

                // close this activity

                finish();

            }

        }, 1*2500);

    }
        }