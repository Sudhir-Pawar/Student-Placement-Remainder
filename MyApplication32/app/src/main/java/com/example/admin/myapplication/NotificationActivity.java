package com.example.admin.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class NotificationActivity extends AppCompatActivity {

    private final String TAG="Notification Activity";
    private Button companyInfo,collegeInfo;
    private ArrayList<String> titles=new ArrayList<>();
    private ArrayList<String> date=new ArrayList<>();
   private boolean successful=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        companyInfo=(Button)findViewById(R.id.notification_company_names);
        companyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotificationActivity.this,CompanInfoActivity.class);
                startActivity(intent);
            }
        });
        collegeInfo=(Button)findViewById(R.id.notification_college);
        collegeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotificationActivity.this,CollegeStudPlacedGraph.class);
                startActivity(intent);
            }
        });


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Notification");
        Query mQuery=databaseReference.orderByKey();
        final ProgressDialog progressDialog=new ProgressDialog(NotificationActivity.this);
        progressDialog.setTitle("getting notification");
        progressDialog.show();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator=iterable.iterator();
                while (iterator.hasNext()){
                    DataSnapshot notification=iterator.next();
                    String ntitle=notification.child("title").getValue().toString();
                    String ndate=notification.child("date").getValue().toString();
                    Log.d(TAG, "initList: called title"+ntitle );
                    Log.d(TAG, "initList: called date"+ ndate);
                    titles.add(ntitle);
                    date.add(ndate);
                    successful=true;
                }
                if (!iterator.hasNext()){
                    progressDialog.dismiss();
                    initList();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(NotificationActivity.this,"Failed to get data", Toast.LENGTH_LONG).show();
            }
        };
        mQuery.addListenerForSingleValueEvent(valueEventListener);
        mQuery.removeEventListener(valueEventListener);
        progressDialog.dismiss();


    }
    private void initList(){

        Log.d(TAG, "initList: called");
            initRecyclerView();


    }
    private void initRecyclerView(){
        Log.d(TAG, "initrecyclerview: called");
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.notify_recycle_layout);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this,titles,date);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}























