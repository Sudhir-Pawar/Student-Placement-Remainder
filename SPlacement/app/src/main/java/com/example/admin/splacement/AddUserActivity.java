package com.example.admin.splacement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {

    private EditText addUsername,addPassword,addid;
    private String username,password;
    private int id;
    private Button addUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        addid=(EditText)findViewById(R.id.addID);
        addUsername=(EditText)findViewById(R.id.addUserName);
        addPassword=(EditText)findViewById(R.id.addPassword);
        addUser=(Button)findViewById(R.id.addAUser);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Users");

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=addUsername.getText().toString();
                password=addPassword.getText().toString();

                id=Integer.parseInt(addid.getText().toString());
                Users users=new Users(id,username,password);
                String key=databaseReference.push().getKey();
                String string=String.valueOf(id);
                if (id==0) {
                    Log.d("// Else //", "In the Elsee");
                    addid.setError("Enter Uid ");
                    Toast.makeText(AddUserActivity.this,"Enter All The Fields",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(username)){
                    addUsername.setError("Enter Name");
                }
                else if(TextUtils.isEmpty(password)){
                    addPassword.setError("Enter password");
                }
                else if (TextUtils.isEmpty(string)&&TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                    Toast.makeText(AddUserActivity.this,"Enter All The Fields",Toast.LENGTH_LONG).show();
                }
                else {
                    Log.d("// Else //", "In the Elsee");
                    databaseReference.child(key).setValue(users).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddUserActivity.this, "Bad Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    });
                    databaseReference.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Toast.makeText(AddUserActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

    }
}
