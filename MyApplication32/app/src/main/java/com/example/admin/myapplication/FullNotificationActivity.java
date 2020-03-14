package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class FullNotificationActivity extends AppCompatActivity {

    private TextView fTitle,fDescrpt,fTime,fDate,fCompanyName,fCollegeName;
    private Button interested;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_notification);

        fTitle=(TextView)findViewById(R.id.fullNTitle);
        fDescrpt=(TextView)findViewById(R.id.fullNDescrpt);
        fTime=(TextView)findViewById(R.id.fullNTime);
        fDate=(TextView)findViewById(R.id.fullNDate);
        fCompanyName=(TextView)findViewById(R.id.fullNCompanyName);
        fCollegeName=(TextView)findViewById(R.id.fullNCollegeName);

        interested=(Button)findViewById(R.id.interested);

        if (getIntent().hasExtra("notification_title")){
            final String title=getIntent().getStringExtra("notification_title");
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference("Notification");
            Query mQuery = databaseReference.orderByKey();
            ValueEventListener valueEventListener=new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator=iterable.iterator();
                    while (iterator.hasNext()){
                        DataSnapshot next=iterator.next();
                        String checkTitle=next.child("title").getValue().toString();
                        if (title.equals(checkTitle)){
                            fTitle.setText(next.child("title").getValue().toString());
                            fDescrpt.setText(next.child("description").getValue().toString());
                            fTime.setText(next.child("time").getValue().toString());
                            fDate.setText(next.child("date").getValue().toString());
                            fCompanyName.setText(next.child("companyName").getValue().toString());
                            fCollegeName.setText(next.child("collegeName").getValue().toString());
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(FullNotificationActivity.this,"Failed", Toast.LENGTH_LONG).show();
                }
            };
            mQuery.addListenerForSingleValueEvent(valueEventListener);


        }
    }
}
