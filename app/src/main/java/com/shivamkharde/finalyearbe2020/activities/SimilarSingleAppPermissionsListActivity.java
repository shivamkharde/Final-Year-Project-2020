package com.shivamkharde.finalyearbe2020.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.SimilarSingleAppPermissionsListAdapter;
import com.shivamkharde.finalyearbe2020.models.SimilarAppPermissions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SimilarSingleAppPermissionsListActivity extends AppCompatActivity {

    //    for permissions popup recycle view

    private RecyclerView permissionPopupRecycleView;
    private RecyclerView.Adapter  similarSingleAppPermissionsListAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<SimilarAppPermissions> similarAppPermissions;

    private JSONObject response;
    private JSONArray permissionArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_single_app_permissions_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try{
//            get data from previous activity
            Bundle bundle = getIntent().getExtras();

             response = new JSONObject(bundle.getString("response"));
//            get permission list as well
             permissionArray = new JSONArray(bundle.getString("permissions_list"));

//            pass array to model and get json object
            similarAppPermissions  = SimilarAppPermissions.fromJson(permissionArray);

//            initialize recycle view , adapter and layout manager
            permissionPopupRecycleView = findViewById(R.id.similar_single_app_permissions_recycle_view);
            myLayoutManager = new LinearLayoutManager(this);
            similarSingleAppPermissionsListAdapter = new SimilarSingleAppPermissionsListAdapter(similarAppPermissions,getApplicationContext());

//            configure recycle view
            permissionPopupRecycleView.setHasFixedSize(true);
            permissionPopupRecycleView.setLayoutManager(myLayoutManager);
            permissionPopupRecycleView.setAdapter(similarSingleAppPermissionsListAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,SimilarSingleAppInfoActivity.class);
        i.putExtra("response",response.toString());
        i.putExtra("permissions_list",permissionArray.toString());
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            Intent i = new Intent(this,SimilarSingleAppInfoActivity.class);
            i.putExtra("response",response.toString());
            i.putExtra("permissions_list",permissionArray.toString());
            startActivity(i);
            finish();
            return true;
        }
            return  super.onOptionsItemSelected(item);
    }
}