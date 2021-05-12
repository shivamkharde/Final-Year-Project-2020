package com.shivamkharde.finalyearbe2020.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ResolveInfo;
import android.os.Bundle;


import org.json.*;
import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.SimilarAppForSingleInstalledAppListAdapter;
import com.shivamkharde.finalyearbe2020.adapters.SimilarAppInstalledListAdapter;
import com.shivamkharde.finalyearbe2020.models.SimilarApps;

import java.util.ArrayList;
import java.util.List;


public class SimilarAppForSingleInstalledAppActivity extends AppCompatActivity {

//    variable declaration
    private RecyclerView mySimilarAppForSingleInstalledAppRecycleView;
    private RecyclerView.Adapter  mySimilarAppForSingleInstalledAppListAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<SimilarApps> similarAppsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_app_for_single_installed_app);
        try {

//            get the api data from previous activity
            Bundle bundle = getIntent().getExtras();
            JSONArray similarAppsArray = new JSONArray(bundle.getString("response"));
//            pass an json data to create an list array
            similarAppsArrayList  = SimilarApps.fromJson(similarAppsArray);

//            call a function to initialize all the components
            initializeComponents();

//        setting recycle view to fixed size
            mySimilarAppForSingleInstalledAppRecycleView.setHasFixedSize(true);
//        setting LayoutManager to the recycle view
            mySimilarAppForSingleInstalledAppRecycleView.setLayoutManager(myLayoutManager);
//        setting mySimilarAppForSingleInstalledAppListAdapter to the recycle view
            mySimilarAppForSingleInstalledAppRecycleView.setAdapter(mySimilarAppForSingleInstalledAppListAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    initialize all the components
    public void initializeComponents(){

//        initializing recycle view, layout manager and recycle view adapter
        mySimilarAppForSingleInstalledAppRecycleView = findViewById(R.id.similar_apps_list);
        myLayoutManager = new LinearLayoutManager(this);
        mySimilarAppForSingleInstalledAppListAdapter = new SimilarAppForSingleInstalledAppListAdapter(similarAppsArrayList,this);
    }

}