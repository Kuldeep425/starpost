package com.example.regisration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
   private EditText phonenumber,Name;
    private Button button;

    private  ImageView imageView,imageView2;
    int SELECT_IMAGE=1;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phonenumber=findViewById(R.id.editTextPhone);
        button=findViewById(R.id.button);
        imageView2=findViewById(R.id.imageView);
        imageView=findViewById(R.id.profileimageView);
        Name=findViewById(R.id.editTextTextPersonName);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

 if(auth.getCurrentUser()!=null){
          startActivity(new Intent(MainActivity.this,mainchat.class));
          finish();
        }




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(phonenumber.length()==10  ){
                                   PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                           "+91"+phonenumber.getText().toString(),
                                            60,
                                             TimeUnit.SECONDS,
                                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String backnedotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    Intent intent=new Intent(MainActivity.this,verify.class);
                                  intent.putExtra("backendotp",backnedotp);
                                  intent.putExtra("Name",Name.getText().toString());
                                  intent.putExtra("Mobilename",phonenumber.getText().toString());
                                  if(Name.getText().toString().isEmpty())
                                  {
                                      Toast.makeText(MainActivity.this,"Please Enter Name", Toast.LENGTH_SHORT).show();
                                  }
                                  else if(imageView==null) {
                                      Toast.makeText(MainActivity.this, "Please Select A Image", Toast.LENGTH_SHORT).show();
                                  }
                                  else {
                                       StorageReference reference= firebaseStorage.getReference().child("Profiles").child(auth.toString());
                                       reference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                              if(task.isSuccessful()){
                                                 reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                     @Override
                                                     public void onSuccess(Uri uri) {

                                                         String imageurl=uri.toString();
                                                         String uid= auth.getUid();
                                                         String phone=auth.getCurrentUser().getPhoneNumber();
                                                         String name=Name.getText().toString();
                                                         User users=new User(uid,name,phone,imageurl);
                                                          firebaseDatabase.getReference()
                                                                  .child("users").child(uid)
                                                                   .setValue(users)
                                                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                       @Override
                                                                       public void onSuccess(Void unused) {

                                                                       startActivity(intent);
                                                                       }
                                                                   });
                                                     }
                                                 });
                                              }
                                           }
                                       });

                                  }
                                }
                            }
                    );
                }
                else {
                    Toast.makeText(MainActivity.this,"Enter 10 digits Valid Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
             uri=data.getData();
            imageView.setImageURI(uri);
        }
    }
}