package com.example.admin.splacement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Button signup;
    private String regName,regPassword;
    private int uniId;
    private EditText Id,name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Admins");
        Id=(EditText)findViewById(R.id.uniqueId);
        name=(EditText)findViewById(R.id.rename);
        password=(EditText)findViewById(R.id.repassword);
        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uniId=Integer.parseInt(Id.getText().toString());
                regName=name.getText().toString();
                regPassword=password.getText().toString();

                if(uniId==0||regName==null||regPassword==null) {
                    Toast.makeText(RegisterActivity.this,"PLZ Enter all the Fields", Toast.LENGTH_LONG).show();

                }
                else {
                    Admins admins=new Admins(uniId,regName,regPassword);
                    String uniqueID=databaseReference.push().getKey();
                    databaseReference.child(uniqueID).setValue(admins).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this,"Bad Internet Connection",Toast.LENGTH_LONG).show();
                        }
                    });
                    Id.setText(Integer.parseInt(null));
                    name.setText("");
                    password.setText("");
                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }



            }
        });
    }


}

