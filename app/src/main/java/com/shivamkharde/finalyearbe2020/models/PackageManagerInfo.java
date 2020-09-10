package com.shivamkharde.finalyearbe2020.models;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import java.util.List;

public class PackageManagerInfo {
//    declaring variables to store the package name
    private String packageName;
    private Context applicationContext;

    public PackageManagerInfo(String packageName, Context applicationContext) {
        this.packageName = packageName;
        this.applicationContext = applicationContext;
    }

//    this method is to get all the installed apps
    public List<ResolveInfo> getInstalledApplicationList(){

//        set Action main for intent
        Intent app = new Intent(Intent.ACTION_MAIN,null);
//        add category launcher to get all the info about the app
        app.addCategory(Intent.CATEGORY_LAUNCHER);
//        call getPackageManager function to return all the installed applications
        return applicationContext.getPackageManager().queryIntentActivities(app,0);
    }

    public static boolean isSystemApp(int flag){
        return (1 & flag) !=0;
    }
}
