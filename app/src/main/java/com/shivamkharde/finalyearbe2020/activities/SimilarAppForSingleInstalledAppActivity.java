package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import org.json.*;
import com.loopj.android.http.*;
import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.models.SimilarAppHTTPClient;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SimilarAppForSingleInstalledAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_app_for_single_installed_app);
//        get intent values
        Bundle extras = getIntent().getExtras();
        final String packageName = extras.getString("packageName");

//        call an api to print application related to this package name\
        try {
            getAllSimilarApplications(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllSimilarApplications(String packageName)throws Exception {
        SimilarAppHTTPClient.get("api/apps/"+packageName+"/similar/", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    JSONArray arr = response.getJSONArray("results");
                    JSONObject obj = arr.getJSONObject(1);
                    System.out.println(obj.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
//                // Pull out the first event on the public timeline
//                JSONObject firstEvent = timeline.get(0);
//                String tweetText = firstEvent.getString("text");
//
//                // Do something with the response
//                System.out.println(tweetText);
            }
        });
    }
}