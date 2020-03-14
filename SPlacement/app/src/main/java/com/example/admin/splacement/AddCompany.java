package com.example.admin.splacement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddCompany extends AppCompatActivity {

    private EditText cname,cdescripion,ccurentreq,y1,y2,y3,y4;
    private String name,descrip,current;
    private String year1,year2,year3,year4;
    private ImageView img;
    private Button selectImg,upload,register;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private Uri filepath,uploadedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        cname=(EditText)findViewById(R.id.companyName);
        cdescripion=(EditText)findViewById(R.id.companyDescription);
        ccurentreq=(EditText)findViewById(R.id.currentRequirements);
        y1=(EditText)findViewById(R.id.sry2016);
        y2=(EditText)findViewById(R.id.sry2017);
        y3=(EditText)findViewById(R.id.sry2018);
        y4=(EditText)findViewById(R.id.sry2019);
        img=(ImageView)findViewById(R.id.logo);
        selectImg=(Button)findViewById(R.id.selectlogo);
        upload=(Button)findViewById(R.id.upload);
        register=(Button)findViewById(R.id.cregister);
         FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
         storageReference=firebaseStorage.getReference();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("CompanyInformation");

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();



                }

        });


    }

    private void uploadFile() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("uploading ");
        progressDialog.show();
        StorageReference reference=storageReference.child("images/"+ UUID.randomUUID().toString());
        reference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                uploadedImage=taskSnapshot.getDownloadUrl();
                String up=String.valueOf(uploadedImage);
               // Log.d("upload", "upload" + uploadedImage.toString());
                AddCompanies addCompanies = new AddCompanies(up,cname.getText().toString(), cdescripion.getText().toString(), ccurentreq.getText().toString(), y1.getText().toString(), y2.getText().toString(), y3.getText().toString(), y4.getText().toString());
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(addCompanies).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddCompany.this, "Success", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCompany.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                });
                Toast.makeText(AddCompany.this,"succes",Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("uploaded"+(int)progress+"%");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(AddCompany.this,"Failed to upload data",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void chooseImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select image"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        filepath=data.getData();
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
            img.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
