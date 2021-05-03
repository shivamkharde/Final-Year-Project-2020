package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.ApplicationListAdapter;
import com.shivamkharde.finalyearbe2020.adapters.SimilarAppInstalledListAdapter;

import java.util.List;

public class SimilarAppsActivity extends AppCompatActivity {

    //    variable declaration
    private RecyclerView mySimilarAppsInstalledListRecycleView;
    private RecyclerView.Adapter  mySimilarAppsInstalledListAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private List<ResolveInfo> packageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_apps);


//        showing the supported Action bar
        getSupportActionBar().show();

//        getting all the installed applications from the device
        packageList = getInstalledApplications();


//        initializing components
        initializeComponents();

//        setting recycle view to fixed size
        mySimilarAppsInstalledListRecycleView.setHasFixedSize(true);
//        setting LayoutManager to the recycle view
        mySimilarAppsInstalledListRecycleView.setLayoutManager(myLayoutManager);
//        setting ApplicationListAdapter to the recycle view
        mySimilarAppsInstalledListRecycleView.setAdapter(mySimilarAppsInstalledListAdapter);


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
        mySimilarAppsInstalledListRecycleView = findViewById(R.id.similar_apps_installed_list);
//        initializing LinearLayoutManager to add list items to the recycle view
        myLayoutManager = new LinearLayoutManager(this);
//        initializing SimilarAppsInstalledListAdapter by passing package list and current context
        mySimilarAppsInstalledListAdapter = new SimilarAppInstalledListAdapter(packageList,this);
    }
}