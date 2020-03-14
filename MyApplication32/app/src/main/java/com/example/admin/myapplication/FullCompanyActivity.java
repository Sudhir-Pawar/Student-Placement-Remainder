package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class FullCompanyActivity extends AppCompatActivity {

    TextView logo,companyName,companyDescpt,companyreq,year1,year2,year3,year4;
    ImageView companyImageLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_company);

        logo=(TextView)findViewById(R.id.company_logo);
        companyName=(TextView)findViewById(R.id.company_name);
        companyDescpt=(TextView)findViewById(R.id.company_description);
        companyreq=(TextView)findViewById(R.id.company_req);
        year1=(TextView)findViewById(R.id.company_year1);
        year2=(TextView)findViewById(R.id.company_year2);
        year3=(TextView)findViewById(R.id.company_year3);
        year4=(TextView)findViewById(R.id.company_year4);
        companyImageLogo=(ImageView)findViewById(R.id.company_logo_image);
        if (getIntent().hasExtra("companyname")){
            final String companyname=getIntent().getStringExtra("companyname");
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=firebaseDatabase.getReference("CompanyInformation");

            Query mQuery=databaseReference.orderByKey();
            ValueEventListener valueEventListener=new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator=iterable.iterator();
                    while (iterator.hasNext()){
                        DataSnapshot company=iterator.next();
                        String checkName=company.child("companyname").getValue().toString();
                        if (companyname.equals(checkName)){
                            logo.setText(company.child("logo").getValue().toString());
                            companyName.setText(company.child("companyname").getValue().toString());
                            companyDescpt.setText(company.child("companydescription").getValue().toString());
                            companyreq.setText(company.child("currentreq").getValue().toString());
                            year1.setText(company.child("year1").getValue().toString());
                            year2.setText(company.child("year2").getValue().toString());
                            year3.setText(company.child("year3").getValue().toString());
                            year4.setText(company.child("year4").getValue().toString());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mQuery.addListenerForSingleValueEvent(valueEventListener);
        }
    }
}
