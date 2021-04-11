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
import com.shivamkharde.finalyearbe2020.activities.SingleAppNotificationLogActivity;
import com.shivamkharde.finalyearbe2020.activities.SingleApplicationInfoActivity;

import java.util.List;

public class NotificationApplicationGroupListAdapter extends RecyclerView.Adapter<NotificationApplicationGroupListAdapter.NotificationApplicationGroupViewHolder> {

//    variable declaration
    private List<ResolveInfo> packageInfo;
    private Context applicationContext;

    public NotificationApplicationGroupListAdapter(List<ResolveInfo> packageInfo, Context applicationContext) {
        this.packageInfo = packageInfo;
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public NotificationApplicationGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myNotificationApplicationGroupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_application_group_list_item , parent,false);
        return new NotificationApplicationGroupViewHolder(myNotificationApplicationGroupView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationApplicationGroupViewHolder holder, int position) {

        //        assigning data from package info list to local variables depending on position
        final Drawable applicationIcon = packageInfo.get(position).loadIcon(applicationContext.getPackageManager());
        final CharSequence applicationName =packageInfo.get(position).loadLabel(applicationContext.getPackageManager());
        final String applicationPackageName = packageInfo.get(position).activityInfo.packageName;

//        binding application icon data to the holder.applicationIcon image view
        holder.notificationApplicationIcon.setImageDrawable(applicationIcon);
//        binding application name data to the holder.ApplicationName text view
        holder.notificationApplicationName.setText(applicationName);

//        binding click listener on applicationListItemCardView to open particular application info
        holder.notificationApplicationListItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                following code is for testing purpose remove after implementing above
                Intent singleNotificationApplicationLogIntent = new Intent(applicationContext, SingleAppNotificationLogActivity.class);
//                passing single application info details
                singleNotificationApplicationLogIntent.putExtra("packageName",applicationPackageName);
                singleNotificationApplicationLogIntent.putExtra("applicationName",applicationName);
//                singleApplicationIntent.putExtra("applicationIcon",img_id);
                applicationContext.startActivity(singleNotificationApplicationLogIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packageInfo.size();
    }

    public class NotificationApplicationGroupViewHolder extends RecyclerView.ViewHolder{

        public ImageView notificationApplicationIcon;
        public TextView notificationApplicationName;
        public CardView notificationApplicationListItemCardView;

        public NotificationApplicationGroupViewHolder(@NonNull View itemView) {
            super(itemView);

//            assigning applicationIcon image view
            notificationApplicationIcon = itemView.findViewById(R.id.notification_application_image_id);
//            assigning applicationName text view
            notificationApplicationName = itemView.findViewById(R.id.notification_application_name_id);
//            assigning applicationListItemCardView card view
//            (this is for event listener to click on particular list item to open info about that application)
            notificationApplicationListItemCardView = itemView.findViewById(R.id.notification_application_group_card_view);
        }
    }
}
