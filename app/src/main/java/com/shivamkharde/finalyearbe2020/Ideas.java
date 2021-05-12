package com.shivamkharde.finalyearbe2020;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.List;

public class Ideas {

    /* this code is used to open app info page
            Intent m = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            m.setData(Uri.parse("package:"+packageList.get(0).activityInfo.packageName));
            startActivity(m);
             */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void main(String args[]){
//        this is to get the application usage stats

//        if(checkForPermission()){
//
//        }else{
//            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
//        }
//
//        getApplicationContext();
//        UsageStatsManager um = (UsageStatsManager) getApplication()
//                .getSystemService(USAGE_STATS_SERVICE);
//
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.YEAR, -1);
//        List<UsageStats> queryUsageStats = um
//                .queryUsageStats(UsageStatsManager.INTERVAL_DAILY, cal.getTimeInMillis(),
//                        System.currentTimeMillis());
//        System.out.println(queryUsageStats.toString());
    }

//    this is to check if user already assigned usage access permission to the app
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    private static boolean checkForPermission() {
//        AppOpsManager appOps = (AppOpsManager) getApplicationContext().getSystemService(APP_OPS_SERVICE);
//        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getApplicationContext().getPackageName());
//        return mode == AppOpsManager.MODE_ALLOWED;
//    }
}
