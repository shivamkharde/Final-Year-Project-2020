package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.AppPermissionListAdapter;
import com.shivamkharde.finalyearbe2020.models.AppPermissionsInfo;

import java.util.ArrayList;
import java.util.List;

public class AppPermissionsActivity extends AppCompatActivity {

//    variable declaration
    private RecyclerView mAppPermissionListRecycleView;
    private RecyclerView.Adapter mAppPermissionListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> permissionList;
    private List<String> permissionLabels;
    private List<String> permissionDescription;
    private List<Integer> permissionGranted;
    private String packageName;
    private PackageInfo packageInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_permissions);

//        initializing list
        permissionList = new ArrayList<>();
        permissionLabels = new ArrayList<>();
        permissionDescription = new ArrayList<>();
        permissionGranted = new ArrayList<>();

//        showing action bar
        getSupportActionBar().show();

//        getting single application package name
        packageName = getIntent().getBundleExtra("singleApplicationPackageName").getString("packageName");

        System.out.println("SHIVAM :" +packageName);

        try {
//        getting package info by passing package name
            packageInfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
//            getting permission list,labels and description;
            getNormalPermissionLabelAndDescriptionWithList(packageInfo);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("SHIVAM :"+permissionLabels.size()+" : PERMISSION LABELS");
        System.out.println("SHIVAM :"+permissionDescription.size()+" : PERMISSION DESCRIPTION");
        System.out.println("SHIVAM :"+permissionList.size()+" : PERMISSIONS");
        int ih = (packageInfo.requestedPermissionsFlags[0]);
        System.out.println("SHIVAM :"+ih);
//        initializing all the components
        initializeComponents();
//        setting recycle view to the fixed size
        mAppPermissionListRecycleView.setHasFixedSize(true);
//        setting LayoutManager to the recycle view
        mAppPermissionListRecycleView.setLayoutManager(mLayoutManager);
//        setting AppPermissionListAdapter to the recycle view
        mAppPermissionListRecycleView.setAdapter(mAppPermissionListAdapter);

    }

//    initializing all the components
    public void initializeComponents(){
//        initializing recycle view by finding the id
        mAppPermissionListRecycleView = findViewById(R.id.app_permission_list_recycle_view);
//        initializing layout manager to add list items to the recycle view
        mLayoutManager = new LinearLayoutManager(this);
//        initializing AppPermissionListAdapter by passing normal and signature permissions label,description, package name ,permission string
        mAppPermissionListAdapter = new AppPermissionListAdapter(permissionLabels, permissionDescription, permissionList, permissionGranted, packageName,this);
    }

//    this function is to get the permission list, labels, description
    public void getNormalPermissionLabelAndDescriptionWithList(PackageInfo packageInfo)throws Exception{
//        looping over requestedPermission to get the info about each permission
        for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
//            getting permission info to get the detail info about permission
            PermissionInfo p = getPackageManager().getPermissionInfo(packageInfo.requestedPermissions[i], PackageManager.GET_META_DATA);
//            if permission protection level is less than 2 then permission is normal and signature (refer docs to know more about permission)
            if(p.getProtection() < 2 ){
//                getting label , description and permission name
                String label = (String) p.loadLabel(getPackageManager());
                String description = (String) p.loadDescription(getPackageManager());
                String permission = packageInfo.requestedPermissions[i];
//                if description is null then add "No Description in list" else add description about permission
                if(description == null)
                    permissionDescription.add("No description..");
                else
                    permissionDescription.add(description);
//                adding label and permission to the list
                permissionLabels.add(label);
                permissionList.add(permission);
//                add permission granted status in permissionGranted List
                permissionGranted.add((packageInfo.requestedPermissionsFlags[i] & packageInfo.REQUESTED_PERMISSION_GRANTED));
            }
        }
    }
}