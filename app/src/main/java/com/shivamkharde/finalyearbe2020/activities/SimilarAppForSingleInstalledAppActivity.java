package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;

import org.json.*;
import com.loopj.android.http.*;
import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.models.SimilarAppHTTPClient;

import java.text.NumberFormat;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SimilarAppForSingleInstalledAppActivity extends AppCompatActivity {

    private ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_app_for_single_installed_app);


        try {

            Bundle b = getIntent().getExtras();
            JSONObject p = new JSONObject(b.getString("response"));
            System.out.println(p);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}