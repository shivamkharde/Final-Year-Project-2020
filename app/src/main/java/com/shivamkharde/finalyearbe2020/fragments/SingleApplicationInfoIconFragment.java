package com.shivamkharde.finalyearbe2020.fragments;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shivamkharde.finalyearbe2020.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleApplicationInfoIconFragment extends Fragment {

//    Variables
    private TextView appName;
    private TextView appVersion;
    private TextView apkName;
    private TextView lastUpdateTime;
    private TextView firstInstallTime;


    public SingleApplicationInfoIconFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View singleApplicationInfoIconFragmentView = inflater.inflate(R.layout.fragment_single_application_info_icon, container, false);

//        getting Bundle Values
        String packageName = getArguments().getString("packageName");

//        add listener to uninstall the application
        singleApplicationInfoIconFragmentView.findViewById(R.id.single_uninstall_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+packageName));
                startActivity(intent);
            }
        });


//        initialize Components
        initializeComponents(singleApplicationInfoIconFragmentView);

        try {
//            get package info by passing package name
            PackageInfo  packageMetaData = getActivity().getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA);

//            getting AppMetaData;
            Map<String,String> appMetaData = getAppMetaData(packageName);

//            setting app metadata to ui components
            setMetaDataToComponents(appMetaData);

            System.out.println("SHIVAM :"+packageMetaData.lastUpdateTime);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return singleApplicationInfoIconFragmentView;
    }

//    this method is to set the appMetaData to UI Components
    private void setMetaDataToComponents(Map<String, String> appMetaData) {

//        setting metadata
        appName.setText(appMetaData.get("appName"));
        appVersion.setText(appMetaData.get("appVersion"));
        apkName.setText(appMetaData.get("apkName"));
        firstInstallTime.setText(appMetaData.get("firstInstallTime"));
        lastUpdateTime.setText(appMetaData.get("lastUpdateTime"));
    }

//    this function is used to get the package metadata using package name
    public Map<String,String> getAppMetaData(String packageName)throws Exception{
        Map<String,String> appMetaData = new HashMap<>();

//        getting packageInfo using package name
        PackageInfo appInfo = getActivity().getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA);
        
//        extracting metadata
        String appName = (String) appInfo.applicationInfo.loadLabel(getActivity().getPackageManager());
        String appVersion = "V"+appInfo.versionName;
        String apkName = appInfo.packageName;
        long firstInstall = appInfo.firstInstallTime;
        long lastUpdate = appInfo.lastUpdateTime;

//        converting millisecond time to date and time format
        DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy hh:mm a z");

//        making calendar instance to format firstInstallTime
        Calendar calendarForFirstInstall = Calendar.getInstance();
        calendarForFirstInstall.setTimeInMillis(firstInstall);
        String firstInstallTime = dateFormat.format(calendarForFirstInstall.getTime());

//        making calender instance to format lastUpdateTime
        Calendar calendarForLastUpdate = Calendar.getInstance();
        calendarForLastUpdate.setTimeInMillis(lastUpdate);
        String lastUpdateTime = dateFormat.format(calendarForLastUpdate.getTime());

//        adding app metadata to list
        appMetaData.put("appName",appName);
        appMetaData.put("appVersion",appVersion);
        appMetaData.put("apkName",apkName);
        appMetaData.put("firstInstallTime",firstInstallTime);
        appMetaData.put("lastUpdateTime",lastUpdateTime);

//        returning map of metadata
        return appMetaData;
    }

    //    this function is to initialize the components
    public void initializeComponents(View singleApplicationIconFragmentView){

//        initializing all text views
        appName = singleApplicationIconFragmentView.findViewById(R.id.app_name_info_icon);
        appVersion = singleApplicationIconFragmentView.findViewById(R.id.app_version_info_icon);
        apkName = singleApplicationIconFragmentView.findViewById(R.id.apk_name_info_icon);
        firstInstallTime = singleApplicationIconFragmentView.findViewById(R.id.first_install_time_info_icon);
        lastUpdateTime = singleApplicationIconFragmentView.findViewById(R.id.last_update_time_info_icon);

    }


}