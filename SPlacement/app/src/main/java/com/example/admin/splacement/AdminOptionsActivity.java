package com.example.admin.splacement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminOptionsActivity extends AppCompatActivity {

    private Button adminAddUser,adminAddCompany,adminNotification,adminCollegeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);

        adminAddCompany=(Button)findViewById(R.id.adminAddCompany);
        adminAddUser=(Button)findViewById(R.id.adminAddUser);
        adminNotification=(Button)findViewById(R.id.adminNotification);
        adminCollegeInfo=(Button)findViewById(R.id.adminAddCollegeInfo);
        adminAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminOptionsActivity.this,AddUserActivity.class);
                startActivity(intent);
            }
        });
        adminAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminOptionsActivity.this,AddCompany.class);
                startActivity(intent);
            }
        });
        adminNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminOptionsActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });
        adminCollegeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminOptionsActivity.this,AddCollegeInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}
