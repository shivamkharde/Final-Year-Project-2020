package com.shivamkharde.finalyearbe2020.models;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

import java.util.ArrayList;
import java.util.List;

public class AppPermissionsInfo {

//    variable declaration
    public List<String> normalPermissionList = new ArrayList<>();
    private List<String> normalPermissionLabels = new ArrayList<>();
    private List<String> normalPermissionDescription = new ArrayList<>();

    private String packageName;
    private Context applicationContext;

    private PackageInfo packageInfo;
    private PermissionInfo permissionInfo;

    public AppPermissionsInfo(String packageName, PackageInfo packageinfo, Context c) throws PackageManager.NameNotFoundException {
//        assigning package name
        this.packageName = packageName;
//        assigning application context
        this.applicationContext = c;
//        getting permission info of passed package name and assign it to PermissionInfo ref
        this.packageInfo = packageinfo;
    }

    public void setNormalPermissionLabelAndDescriptionWithList()throws Exception{
        List<String> permissionLabels = new ArrayList<>();
        List<String> permissionDescription = new ArrayList<>();
        for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
            PermissionInfo p = applicationContext.getPackageManager().getPermissionInfo(packageInfo.requestedPermissions[i], PackageManager.GET_META_DATA);
            if(p.getProtection() < 2 ){
                String l = (String) p.loadLabel(applicationContext.getPackageManager());
                String d = (String) p.loadDescription(applicationContext.getPackageManager());
                normalPermissionLabels.add(l);
                normalPermissionDescription.add(d);
            }
        }
    }

    public List<String> getNormalPermissionLabels(){
       return normalPermissionLabels;
    }

    public List<String> getNormalPermissionDescription(){
        return normalPermissionDescription;
    }
//    this function is to get the protection level of particular permission
    public int getProtectionLevel(PermissionInfo permissionInfo){
        return permissionInfo.getProtection();
    }

//    this function is to get the permission info of particular permission
    public PermissionInfo getPermissionInfo(String permission) throws PackageManager.NameNotFoundException {
        return applicationContext.getPackageManager().getPermissionInfo(permission,PackageManager.GET_META_DATA);
    }

}
