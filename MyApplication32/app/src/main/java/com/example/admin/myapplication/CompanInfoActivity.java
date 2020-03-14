package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class CompanInfoActivity extends AppCompatActivity {


    private final String TAG="CompanyInfoActivity";
    ArrayList<String> mCompanyName=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: in");
        setContentView(R.layout.activity_compan_info);
        Log.d(TAG, "onCreate: in");

        Log.d(TAG, "onCreate: out");

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("CompanyInformation");
        Query mQuery = databaseReference.orderByKey();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator=iterable.iterator();

                while (iterator.hasNext()){
                    DataSnapshot notification=iterator.next();
                    String cn=notification.child("companyname").getValue().toString();
                    Log.d(TAG, "onDataChange: "+ cn);
                   mCompanyName.add(cn);
                }
                if (!iterator.hasNext()){
                    initList();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mQuery.addListenerForSingleValueEvent(valueEventListener);
        //initList();
    }

    private void initList() {

        Log.d(TAG, "initList: ");
        mCompanyName.add("sudhir");
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: ");
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.company_info_recycle_view);
        CompanyRecyclerViewAdapter recyclerViewAdapter=new CompanyRecyclerViewAdapter(mCompanyName,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
