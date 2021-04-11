package com.shivamkharde.finalyearbe2020.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.ApplicationListAdapter;
import com.shivamkharde.finalyearbe2020.adapters.NotificationApplicationGroupListAdapter;
import com.shivamkharde.finalyearbe2020.utilities.NotificationServiceUtility;

import java.util.List;

public class NotificationLogActivity extends AppCompatActivity {

//    variable declaration
    private RecyclerView myNotificationApplicationGroupRecycleView;
    private RecyclerView.Adapter myNotificationApplicationGroupRecycleViewAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private List<ResolveInfo> packageList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_log);

        getSupportActionBar().show();

        packageList = getInstalledApplications();

        initializeComponents();

        myNotificationApplicationGroupRecycleView.setHasFixedSize(true);
        myNotificationApplicationGroupRecycleView.setLayoutManager(myLayoutManager);
        myNotificationApplicationGroupRecycleView.setAdapter(myNotificationApplicationGroupRecycleViewAdapter);

    }

//    this function is to get the installed applications from the device
    private List<ResolveInfo> getInstalledApplications() {
//        set Action main for intent
        Intent app = new Intent(Intent.ACTION_MAIN,null);
//        add category launcher to get all the info about the app
        app.addCategory(Intent.CATEGORY_LAUNCHER);
//        call getPackageManager function to return all the installed applications
        return getApplicationContext().getPackageManager().queryIntentActivities(app,0);
    }

//    initializing all the components
    public void initializeComponents(){
//        initializing RecycleView by finding the id
        myNotificationApplicationGroupRecycleView = findViewById(R.id.notification_application_group_recycleview);
//        initializing LinearLayoutManager to add list items to the recycle view
        myLayoutManager = new GridLayoutManager(this,3);
//        initializing ApplicationListAdapter by passing package list and current context
        myNotificationApplicationGroupRecycleViewAdapter = new NotificationApplicationGroupListAdapter(packageList,this);
    }

}