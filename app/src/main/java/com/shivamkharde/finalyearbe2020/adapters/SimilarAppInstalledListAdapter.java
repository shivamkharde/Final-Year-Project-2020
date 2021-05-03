package com.shivamkharde.finalyearbe2020.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.activities.SimilarAppForSingleInstalledAppActivity;


import java.util.List;

public class SimilarAppInstalledListAdapter extends RecyclerView.Adapter<SimilarAppInstalledListAdapter.SimilarAppsInstalledListViewHolder> {

    //    variable declaration
    private List<ResolveInfo> packageInfo;
    private Context applicationContext;

    public SimilarAppInstalledListAdapter(List<ResolveInfo> packageInfo, Context applicationContext) {
        this.packageInfo = packageInfo;
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public SimilarAppsInstalledListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mySimilarAppsInstalledListGroupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_apps_installed_list_item , parent,false);
        return new SimilarAppsInstalledListViewHolder(mySimilarAppsInstalledListGroupView);

    }

    @Override
    public void onBindViewHolder(@NonNull SimilarAppsInstalledListViewHolder holder, int position) {
        //        assigning data from package info list to local variables depending on position
        final Drawable applicationIcon = packageInfo.get(position).loadIcon(applicationContext.getPackageManager());
        final CharSequence applicationName =packageInfo.get(position).loadLabel(applicationContext.getPackageManager());
        final String applicationPackageName = packageInfo.get(position).activityInfo.packageName;

//        binding application icon data to the holder.applicationIcon image view
        holder.similarAppsInstalledApplicationIcon.setImageDrawable(applicationIcon);
//        binding application name data to the holder.ApplicationName text view
        holder.similarAppsInstalledApplicationName.setText(applicationName);

//        binding click listener on applicationListItemCardView to open particular application info
        holder.similarAppsInstalledApplicationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                following code is for testing purpose remove after implementing above
                Intent similarAppForSingleInstalledAppIntent = new Intent(applicationContext, SimilarAppForSingleInstalledAppActivity.class);
//                passing single application info details
                similarAppForSingleInstalledAppIntent.putExtra("packageName",applicationPackageName);
                similarAppForSingleInstalledAppIntent.putExtra("applicationName",applicationName);
                applicationContext.startActivity(similarAppForSingleInstalledAppIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packageInfo.size();
    }

    public class SimilarAppsInstalledListViewHolder extends RecyclerView.ViewHolder{

        public ImageView similarAppsInstalledApplicationIcon;
        public TextView similarAppsInstalledApplicationName;
        public CardView similarAppsInstalledApplicationCardView;

        public SimilarAppsInstalledListViewHolder(@NonNull View itemView) {
            super(itemView);

//            assigning applicationIcon image view
            similarAppsInstalledApplicationIcon = itemView.findViewById(R.id.similar_apps_installed_application_icon);
//            assigning applicationName text view
            similarAppsInstalledApplicationName = itemView.findViewById(R.id.similar_installed_application_label);
//            assigning applicationListItemCardView card view
//            (this is for event listener to click on particular list item to open similar apps activity)
            similarAppsInstalledApplicationCardView = itemView.findViewById(R.id.similar_apps_installed_list_item_card_view);
        }
    }
}
