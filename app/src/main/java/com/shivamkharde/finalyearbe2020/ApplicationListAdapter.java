package com.shivamkharde.finalyearbe2020;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationListAdapter.ApplicationListViewHolder> {

//    variable Declaration for list of packages and context
    private List<ResolveInfo> packageInfo;
    private Context applicationContext;

//    constructor to initialize the private fields with list of packageInfo and context
    public ApplicationListAdapter(List<ResolveInfo> packageinfo, Context c){
//        assigning package info to the private field
        packageInfo = packageinfo;
//        assigning context to the private field
        applicationContext = c;
    }

    @NonNull
    @Override
//    implementing the abstract method to create/inflate the ApplicationListItem xml layout and
//    passing it to the view holder
    public ApplicationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        creating the view instance with the ApplicationListItem Layout File
        View applicationListInflate =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.application_list_item,parent,false);
//        passing the created view instance to the ApplicationListViewHolder
        ApplicationListViewHolder appViewHolder = new ApplicationListViewHolder(applicationListInflate);
//        returning the ApplicationListViewHolder instance
        return appViewHolder;
    }

    @Override
//    binding the view holder props/components  with the data available in packageInfo List
    public void onBindViewHolder(@NonNull ApplicationListViewHolder holder, int position) {

//        assigning data from package info list to local variables depending on position
        final Drawable applicationIcon = packageInfo.get(position).loadIcon(applicationContext.getPackageManager());
        String applicationPackageName = packageInfo.get(position).activityInfo.packageName;
        CharSequence applicationName =packageInfo.get(position).loadLabel(applicationContext.getPackageManager());

//        binding application icon data to the holder.applicationIcon image view
        holder.applicationIcon.setImageDrawable(applicationIcon);
//        binding application package name data to the holder.ApplicationPackageName text view
        holder.applicationPackageName.setText(applicationPackageName);
//        binding application name data to the holder.ApplicationName text view
        holder.applicationName.setText(applicationName);

//        binding click listener on applicationListItemCardView to open particular application info
        holder.applicationListItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO: implement this later
//                following code is for testing purpose remove after implementing above TODO:
                Intent testIntent = new Intent(applicationContext,MainActivity.class);
                applicationContext.startActivity(testIntent);

            }
        });

    }

    @Override
//    getting item count of list
    public int getItemCount() {
//        returning size of package info list
        return packageInfo.size();
    }

//  creating the class view holder to hold the view from recycle view
//  and to change the view according to the scroll position
    public class ApplicationListViewHolder extends RecyclerView.ViewHolder{

//        declaring all the necessary views
        public ImageView applicationIcon;
        public TextView applicationName;
        public TextView applicationPackageName;
        public CardView applicationListItemCardView;

//        constructor to get the necessary views from layout file with is assigned by onCreateViewHolder function
        public ApplicationListViewHolder(@NonNull View itemView) {
            super(itemView);

//            assigning applicationIcon image view
            applicationIcon = itemView.findViewById(R.id.application_icon);
//            assigning applicationName text view
            applicationName = itemView.findViewById(R.id.application_name);
//            assigning applicationPackageName text view
            applicationPackageName = itemView.findViewById(R.id.application_package_name);
//            assigning applicationListItemCardView card view
//            (this is for event listener to click on particular list item to open info about that application)
            applicationListItemCardView = itemView.findViewById(R.id.application_list_item_card_view);
        }
    }

}
