package com.example.admin.splacement;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    private Button signIn,register;
    private EditText username,password;
    private String verifyUserName,verifyPassword,dataUsername,dataPassword;
    private boolean ifSuccessfull=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        signIn=(Button)findViewById(R.id.signIn);
        username =(EditText)findViewById(R.id.username);
        password =(EditText)findViewById(R.id.password);
        register=(Button)findViewById(R.id.register);
         FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Admins");

        final Query query=databaseReference.orderByKey();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifSuccessfull=false;
                verifyUserName=username.getText().toString();
                verifyPassword=password.getText().toString();
                final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Authenticating");
                progressDialog.setMessage("please wait");
                progressDialog.show();
                ValueEventListener eventListener=new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                        Iterator<DataSnapshot> iterator=iterable.iterator();
                        while (iterator.hasNext()){
                            DataSnapshot next=(DataSnapshot)iterator.next();
                            dataUsername = next.child("name").getValue().toString();
                            dataPassword =next.child("password").getValue().toString();
                            Log.d("username : "+dataUsername,dataPassword+" ");
                            if(dataUsername.equals(verifyUserName) && dataPassword.equals(verifyPassword)){
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"Successfully log in",Toast.LENGTH_LONG).show();
                                ifSuccessfull=true;
                                Intent intent=new Intent(MainActivity.this,AdminOptionsActivity.class);
                                startActivity(intent);
                            }

                        }

                        if(!ifSuccessfull){
                            Toast.makeText(MainActivity.this,"Failed wrong username or password",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Failure Bad Internet connectuon",Toast.LENGTH_LONG).show();
                    }
                };
                query.addListenerForSingleValueEvent(eventListener);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
