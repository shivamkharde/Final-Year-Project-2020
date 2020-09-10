package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.shivamkharde.finalyearbe2020.adapters.ApplicationListAdapter;
import com.shivamkharde.finalyearbe2020.R;

import java.util.List;

public class ApplicationListActivity extends AppCompatActivity {

//    variable declaration
    private RecyclerView myApplicationListRecycleView;
    private RecyclerView.Adapter  myApplicationListAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private List<ResolveInfo> packageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);

//        showing the supported Action bar
        getSupportActionBar().show();

//        getting all the installed applications from the device
        packageList = getInstalledApplications();


//        initializing components
        initializeComponents();

//        setting recycle view to fixed size
        myApplicationListRecycleView.setHasFixedSize(true);
//        setting LayoutManager to the recycle view
        myApplicationListRecycleView.setLayoutManager(myLayoutManager);
//        setting ApplicationListAdapter to the recycle view
        myApplicationListRecycleView.setAdapter(myApplicationListAdapter);


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
        myApplicationListRecycleView = findViewById(R.id.app_permission_list_recycle_view);
//        initializing LinearLayoutManager to add list items to the recycle view
        myLayoutManager = new LinearLayoutManager(this);
//        initializing ApplicationListAdapter by passing package list and current context
        myApplicationListAdapter = new ApplicationListAdapter(packageList,this);
    }
}