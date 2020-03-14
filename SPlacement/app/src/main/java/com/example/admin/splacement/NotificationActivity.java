package com.example.admin.splacement;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class NotificationActivity extends AppCompatActivity {

    private EditText title,description,time,date,companyName,collegeName;
    private Button addNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Notification");

        title=(EditText)findViewById(R.id.addNotifyTitle);
        description=(EditText)findViewById(R.id.addNotifyDescription);
        time=(EditText)findViewById(R.id.addNotifyTime);
        date=(EditText)findViewById(R.id.addNotifyDate);
        companyName=(EditText)findViewById(R.id.addNotifyCompanyName);
        collegeName=(EditText)findViewById(R.id.addNotifyCollegeName);
        addNotification=(Button)findViewById(R.id.putNotification);

        addNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(NotificationActivity.this);
                progressDialog.setTitle("Uploading Notification");
                progressDialog.setMessage("plz wait for a moment");
                progressDialog.show();

                Notification notification=new Notification(title.getText().toString(),description.getText().toString(),time.getText().toString(),date.getText().toString(),companyName.getText().toString(),collegeName.getText().toString());
                String key=databaseReference.push().getKey();
                databaseReference.child(key).setValue(notification).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(NotificationActivity.this,"Succuss",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(NotificationActivity.this,"Failed!!",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
