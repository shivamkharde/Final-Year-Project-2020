package com.shivamkharde.finalyearbe2020.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.ConfigurationStats;
import android.app.usage.EventStats;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.health.ProcessHealthStats;
import android.provider.Settings;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.ApplicationListAdapter;
import com.shivamkharde.finalyearbe2020.adapters.DeviceUsageAppLastTimeUsedListAdapter;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DeviceUsageActivity extends AppCompatActivity {


    //    variable declaration
    private RecyclerView myApplicationListRecycleView;
    private RecyclerView.Adapter  myApplicationListAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private List<UsageStats> queryUsageStats;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_usage);

//        this is to get the application usage stats
        if(checkForPermission()){
//
        }else{
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            finish();
        }

//        get Usage Stats
        queryUsageStats = getUsageStats();

        initializeComponents();


//        configure adapter
//        setting recycle view to fixed size
        myApplicationListRecycleView.setHasFixedSize(true);
//        setting LayoutManager to the recycle view
        myApplicationListRecycleView.setLayoutManager(myLayoutManager);
//        setting ApplicationListAdapter to the recycle view
        myApplicationListRecycleView.setAdapter(myApplicationListAdapter);

    }

//    this function is to get the usage stats
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private List<UsageStats> getUsageStats() {
        UsageStatsManager um = (UsageStatsManager) getApplication().getSystemService(USAGE_STATS_SERVICE);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.add(Calendar.YEAR,-1);

        cal.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        queryUsageStats = um.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, cal.getTimeInMillis(), Calendar.getInstance().getTimeInMillis());

        return queryUsageStats;
    }

    //    this is to check if user already assigned usage access permission to the app
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean checkForPermission() {
        AppOpsManager appOps = (AppOpsManager) getApplicationContext().getSystemService(APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getApplicationContext().getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    //    initializing all the components
    public void initializeComponents(){
//        initializing RecycleView by finding the id
        myApplicationListRecycleView = findViewById(R.id.device_usage_last_time_used_recycle_view);
//        initializing LinearLayoutManager to add list items to the recycle view
        myLayoutManager = new LinearLayoutManager(this);
//        initializing queryStats adapter by passing package list and current context
        myApplicationListAdapter = new DeviceUsageAppLastTimeUsedListAdapter(queryUsageStats,this);
    }
}