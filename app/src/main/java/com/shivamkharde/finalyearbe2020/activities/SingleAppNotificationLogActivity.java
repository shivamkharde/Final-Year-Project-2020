package com.shivamkharde.finalyearbe2020.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.NotificationApplicationGroupListAdapter;

import java.util.List;

public class SingleAppNotificationLogActivity extends AppCompatActivity {


    //    variable declaration
    private RecyclerView myNotificationHistoryLogRecycleView;
    private RecyclerView.Adapter myNotificationHistoryLogRecycleViewAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_app_notification_log);
        Bundle extra = getIntent().getExtras();

        assert extra != null;
        String appName = extra.getString("applicationName");
        String appPackageName = extra.getString("applicationPackageName");

        ActionBar mActionBar= getSupportActionBar();
        
        mActionBar.setTitle(appName+" App Notification History");

//        initializeComponents(appPackageName);

        LocalBroadcastManager.getInstance(this).registerReceiver(onBroadcastReceived, new IntentFilter("Msg"));


    }


    //    initializing all the components
    public void initializeComponents(String packageName){
//        initializing RecycleView by finding the id
        myNotificationHistoryLogRecycleView = findViewById(R.id.notification_application_group_recycleview);
//        initializing LinearLayoutManager to add list items to the recycle view
        myLayoutManager = new LinearLayoutManager(this);
//        initializing ApplicationListAdapter by passing package list and current context
//        myNotificationHistoryLogRecycleViewAdapter = new NotificationApplicationGroupListAdapter(packageName,this);
    }

    private BroadcastReceiver onBroadcastReceived = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String ticker = intent.getStringExtra("ticker");
            String text = intent.getStringExtra("text");

            System.out.println("MSG Package: "+pack);
            Log.i("MSG","ticker: "+ticker);
            Log.i("MSG","Title: "+title);
            Log.i("MSG","Text: "+text);
        }
    };
}