package com.shivamkharde.finalyearbe2020.adapters;

import android.app.usage.UsageStats;
import android.content.Context;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.activities.SingleApplicationInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class DeviceUsageAppLastTimeUsedListAdapter extends RecyclerView.Adapter<DeviceUsageAppLastTimeUsedListAdapter.DeviceUsageAppLastTimeUsedListViewHolder> {

    //    variable Declaration for list of packages and context
    private List<UsageStats> usageStats;
    private Context applicationContext;

    //    constructor to initialize the private fields with list of packageInfo and context
    public DeviceUsageAppLastTimeUsedListAdapter(List<UsageStats> usageStats, Context c){
//        assigning package info to the private field
        this.usageStats = usageStats;
//        assigning context to the private field
        this.applicationContext = c;
    }

    @NonNull
    @Override
//    implementing the abstract method to create/inflate the ApplicationListItem xml layout and
//    passing it to the view holder
    public DeviceUsageAppLastTimeUsedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        creating the view instance with the ApplicationListItem Layout File
        View applicationListInflate =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_usage_last_time_used_list_item,parent,false);
//        returning the ApplicationListViewHolder instance
        return new DeviceUsageAppLastTimeUsedListViewHolder(applicationListInflate);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
//    binding the view holder props/components  with the data available in packageInfo List
    public void onBindViewHolder(@NonNull DeviceUsageAppLastTimeUsedListViewHolder holder, final int position) {

        try{
            String packageName = usageStats.get(position).getPackageName();
            ApplicationInfo appinfo = applicationContext.getPackageManager().getApplicationInfo(packageName,PackageManager.GET_META_DATA);

            Drawable applicationIcon = appinfo.loadIcon(applicationContext.getPackageManager());
            CharSequence applicationName =appinfo.loadLabel(applicationContext.getPackageManager());

            holder.applicationIcon.setImageDrawable(applicationIcon);
            holder.applicationName.setText(applicationName);

            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.setTimeZone(TimeZone.getDefault());
            holder.lastUsage.setText("Last Usage : "+sdf.format(new Date(usageStats.get(position).getLastTimeUsed())));

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
//    getting item count of list
    public int getItemCount() {
//        returning size of package info list
        return usageStats.size();
    }

    //  creating the class view holder to hold the view from recycle view
//  and to change the view according to the scroll position
    public class DeviceUsageAppLastTimeUsedListViewHolder extends RecyclerView.ViewHolder{

        //        declaring all the necessary views
        public ImageView applicationIcon;
        public TextView applicationName;
        public TextView lastUsage;


        //        constructor to get the necessary views from layout file with is assigned by onCreateViewHolder function
        public DeviceUsageAppLastTimeUsedListViewHolder(@NonNull View itemView) {
            super(itemView);

//            assigning applicationIcon image view
            applicationIcon = itemView.findViewById(R.id.application_icon);
//            assigning applicationName text view
            applicationName = itemView.findViewById(R.id.permission_lable);
//            assigning applicationPackageName text view
            lastUsage = itemView.findViewById(R.id.device_usage_last_time_use_text);

        }
    }

}
