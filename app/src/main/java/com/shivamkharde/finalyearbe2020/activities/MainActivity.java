package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shivamkharde.finalyearbe2020.R;

public class MainActivity extends AppCompatActivity {

//    declaring all the variables
    CardView myApplicationCardView;
    CardView myNotificationLogCardView;
    CardView myDeviceUsageCardView;
    CardView mySimilarAppsCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        hiding supported action bar
        getSupportActionBar().hide();

//        this method will initialize all the components from main activity layout
        initializeComponents();

//        add event listener to the components
        myApplicationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                creating the intent for application list activity
                Intent myApplicationListIntent = new Intent(MainActivity.this, ApplicationListActivity.class);
//                starting ApplicationListActivity
                startActivity(myApplicationListIntent);
            }
        });

        myNotificationLogCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                create an intent for notification log activity
                Intent myNotificationLogIntent = new Intent(MainActivity.this,NotificationLogActivity.class);

//                starting NotificationLogActivity
                startActivity(myNotificationLogIntent);
            }
        });
    }

//initializing all the components
    private void initializeComponents() {
//        initializing card view as a button for application list activity
        myApplicationCardView = findViewById(R.id.applications_card_view);
//        initializing card view as a button for notification log activity
        myNotificationLogCardView = findViewById(R.id.notification_log_card_view);
//        initializing card view as a button for device usage activity
        myDeviceUsageCardView = findViewById(R.id.device_usage_card_view);
//        initializing card view as a button for similar apps activity
        mySimilarAppsCardView = findViewById(R.id.similar_apps_card_view);
    }


}