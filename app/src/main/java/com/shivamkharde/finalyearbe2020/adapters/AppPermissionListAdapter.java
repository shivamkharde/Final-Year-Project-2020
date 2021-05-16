package com.shivamkharde.finalyearbe2020.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shivamkharde.finalyearbe2020.R;

import java.util.List;
import java.util.Scanner;

public class AppPermissionListAdapter extends RecyclerView.Adapter<AppPermissionListAdapter.AppPermissionListViewHolder> {

//    variable declaration for list of permissions, labels, description, package name , context
    private String packageName;
    private Context applicationContext;
    private List<String> permissionList;
    private List<String> permissionLabels;
    private List<String> permissionDescription;
    private List<Integer> permissionGranted;

//    constructor to initialize the private fields with list of permission list,labels, description, package name and context
    public AppPermissionListAdapter(List<String> permissionLabels, List<String> permissionDescription, List<String> permissionList, List<Integer> permissionGranted, String packageName, Context applicationContext) {
//        assigning package name
        this.packageName = packageName;
//        assigning application context
        this.applicationContext = applicationContext;
//        assigning normal permission list
        this.permissionList = permissionList;
//        assigning permission labels
        this.permissionLabels = permissionLabels;
//        assigning permission description
        this.permissionDescription = permissionDescription;
//        assigning permission granted
        this.permissionGranted = permissionGranted;
    }

    @NonNull
    @Override
    public AppPermissionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        creating the view instance with the AppPermissionListItem Layout File
        View appPermissionInflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.permission_list_item,parent,false);
//        passing the created view instance to the AppPermissionListViewHolder
        AppPermissionListViewHolder appPermissionListViewHolder = new AppPermissionListViewHolder(appPermissionInflate);
//        returning the AppPermissionListViewHolder instance
        return appPermissionListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppPermissionListViewHolder holder, int position) {

//        assigning data from permissionLabel, permission description to local variables depending on position
         String permissionLabelName = permissionLabels.get(position);
         String permissionDescriptionName = permissionDescription.get(position);
         boolean isPermissionGranted = permissionGranted.get(position)!=0;
//         if permission is granted by user then switch is on
         if(isPermissionGranted){
             holder.isPermissionGrantedSwitch.setChecked(true);
         }

//        setting permission label name data to the holder.permissionLabel text view
        holder.permissionLabel.setText(permissionLabelName);
//        setting permission description name data to the holder.permissionDescription text view
        holder.permissionDescription.setText(permissionDescriptionName);

//        onclick listener on isPermission granted switch
        holder.isPermissionGrantedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try{
                    if(isChecked){
                    }else{
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", packageName, null);
                        intent.setData(uri);
                        applicationContext.startActivity(intent);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return permissionLabels.size();
    }

    public class AppPermissionListViewHolder extends RecyclerView.ViewHolder{

//        declaring all necessary views
        public TextView permissionLabel;
        public TextView permissionDescription;
        public Switch isPermissionGrantedSwitch;

        public AppPermissionListViewHolder(@NonNull View itemView) {
            super(itemView);

//            assigning permission label text view
            permissionLabel = itemView.findViewById(R.id.permission_lable);
//            assigning permission description text view
            permissionDescription = itemView.findViewById(R.id.permission_description);
//            assigning is permission granted switch
            isPermissionGrantedSwitch = itemView.findViewById(R.id.permission_granted_switch);

        }
    }
}
