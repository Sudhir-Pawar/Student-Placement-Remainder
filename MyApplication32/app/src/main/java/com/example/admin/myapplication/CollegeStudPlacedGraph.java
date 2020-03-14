package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollegeStudPlacedGraph extends AppCompatActivity {

    private final String TAG="CollegeStudPlacedGraph";
   // String studentPlaced,studentRegister;
    private int students[]={45};
    private String year[]={"j"};
    private int sample[]=new int[20];
    private String sSample[]=new String[20];
    private int record=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_stud_placed_graph);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("CollegeInformation");


        Query mQuery=databaseReference.orderByKey();

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                
                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator=iterable.iterator();
                while (iterator.hasNext()){
                    DataSnapshot collegeInfo=iterator.next();
                    String studentPlaced=collegeInfo.child("studentPlaced").getValue().toString();
                    int value= Integer.parseInt(studentPlaced);

                    Log.d(TAG, "onDataChange: value="+value+" record"+record);

                    sample[record]=value;
                    Log.d(TAG, "onDataChange: sample[record]"+sample[record]);
                    String studentRegister=collegeInfo.child("studentRegister").getValue().toString();
                    sSample[record]=studentRegister;
                    Log.d(TAG, "onDataChange: sr="+studentRegister +" sSample[record]"+sSample[record]);

                    Log.d(TAG, "onDataChange: sp="+studentPlaced+" sr="+studentRegister);
                    record++;
                    if (!iterator.hasNext()){
                        Log.d(TAG, "onDataChange: if condition");
                        setupPieChart();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mQuery.addListenerForSingleValueEvent(valueEventListener);

    }

    private void setupPieChart() {
        List<PieEntry> pieEntries=new ArrayList<>();


        for (int i=0;i<sample.length;i++){
            pieEntries.add(new PieEntry(sample[i],sSample[i]));
        }
        PieDataSet dataSet=new PieDataSet(pieEntries,"College Placed Students Graph");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData=new PieData(dataSet);

        PieChart chart=(PieChart)findViewById(R.id.chart);
        chart.setData(pieData);
        chart.animateY(1000);
        chart.invalidate();
    }
}
