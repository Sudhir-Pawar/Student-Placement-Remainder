package com.example.admin.splacement;

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

public class AddCollegeInfoActivity extends AppCompatActivity {

    private EditText addStudRegister,addStudPlaced;
    private Button addRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_college_info);

        addStudRegister=(EditText)findViewById(R.id.noodstdregister);
        addStudPlaced=(EditText)findViewById(R.id.noofstdplaced);
        addRecord=(Button)findViewById(R.id.addstdrecord);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("CollegeInformation");
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mStdReg=addStudRegister.getText().toString();
                String mStdPlaced=addStudPlaced.getText().toString();

                AddCollegeInfo addCollegeInfo=new AddCollegeInfo(mStdReg,mStdPlaced);
                String key=databaseReference.push().getKey();
                databaseReference.child(key).setValue(addCollegeInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddCollegeInfoActivity.this,"Success",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCollegeInfoActivity.this,"Failure",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
