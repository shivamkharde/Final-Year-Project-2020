package com.shivamkharde.finalyearbe2020.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.activities.SimilarAppForSingleInstalledAppActivity;
import com.shivamkharde.finalyearbe2020.activities.SimilarSingleAppInfoActivity;
import com.shivamkharde.finalyearbe2020.models.SimilarAppHTTPClient;
import com.shivamkharde.finalyearbe2020.models.SimilarApps;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SimilarAppForSingleInstalledAppListAdapter extends RecyclerView.Adapter<SimilarAppForSingleInstalledAppListAdapter.SimilarAppForSingleInstalledAppListViewHolder> {

    //    variable declaration
    private ArrayList<SimilarApps> similarApps;
    private Context applicationContext;

    public SimilarAppForSingleInstalledAppListAdapter(ArrayList<SimilarApps> similarApps, Context applicationContext) {
        this.similarApps = similarApps;
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public SimilarAppForSingleInstalledAppListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View SimilarAppForSingleInstalledAppListGroupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_app_api_list_item , parent,false);
        return new SimilarAppForSingleInstalledAppListViewHolder(SimilarAppForSingleInstalledAppListGroupView);

    }

    @Override
    public void onBindViewHolder(@NonNull SimilarAppForSingleInstalledAppListViewHolder holder, int position) {
//        get data and assign it to views using view holder references
        String applicationIcon = similarApps.get(position).getApplicationIcon();
        String applicationName = similarApps.get(position).getApplicationName();
        String applicationDescription = similarApps.get(position).getAppDescription();
        String ratingText = similarApps.get(position).getRating();
        String developer = similarApps.get(position).getDeveloper().toLowerCase();
        String priceText = similarApps.get(position).getPriceText();

//        show this in list with view holder object
        holder.applicationName.setText(applicationName);
        holder.applicationDescription.setText(applicationDescription);
        holder.developer.setText("-By "+developer);
        holder.ratingText.setText(ratingText);
        holder.priceText.setText(priceText);

//        check if app is paid then change the background of text to red
        if(!similarApps.get(position).isFree()){
            holder.priceText.setBackgroundColor(Color.RED);
        }

//        load application url using glide lib
        Glide.with(applicationContext).load(applicationIcon).into(holder.applicationIcon);

//        detect click listener on card view
        holder.similarAppApiItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                navigate to single similar app info page
                Intent similarSingleAppInfoIntent = new Intent(applicationContext, SimilarSingleAppInfoActivity.class);
                applicationContext.startActivity(similarSingleAppInfoIntent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return similarApps.size();
    }

    public class SimilarAppForSingleInstalledAppListViewHolder extends RecyclerView.ViewHolder{

        public ImageView applicationIcon;
        public TextView applicationName;
        public TextView applicationDescription;
        public TextView ratingText;
        public TextView developer;
        public TextView priceText;
        public CardView similarAppApiItemCardView;

        public SimilarAppForSingleInstalledAppListViewHolder(@NonNull View itemView) {
            super(itemView);

//            assigning all the view ref with actual view
            applicationIcon = itemView.findViewById(R.id.similar_app_api_icon);
            applicationName = itemView.findViewById(R.id.similar_app_api_application_name);
            applicationDescription = itemView.findViewById(R.id.similar_app_api_description);
            ratingText = itemView.findViewById(R.id.similar_app_api_rating_text);
            developer = itemView.findViewById(R.id.similar_app_api_developer);
            priceText = itemView.findViewById(R.id.similar_app_api_free_or_paid);
            similarAppApiItemCardView = itemView.findViewById(R.id.similar_app_api_card_view);
        }
    }
}
