package com.shivamkharde.finalyearbe2020.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.shivamkharde.finalyearbe2020.R;

import com.shivamkharde.finalyearbe2020.activities.SimilarSingleAppInfoActivity;
import com.shivamkharde.finalyearbe2020.models.SimilarAppHTTPClient;
import com.shivamkharde.finalyearbe2020.models.SimilarAppPermissions;
import com.shivamkharde.finalyearbe2020.models.SimilarApps;



import org.json.JSONObject;

import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;

public class SimilarSingleAppPermissionsListAdapter extends RecyclerView.Adapter<SimilarSingleAppPermissionsListAdapter.SimilarSingleAppPermissionsListAdapterViewHolder> {

    //    variable declaration
    private ArrayList<SimilarAppPermissions> similarAppsPermissions;
    private Context applicationContext;

    public SimilarSingleAppPermissionsListAdapter(ArrayList<SimilarAppPermissions> similarAppsPermissions, Context applicationContext) {
        this.similarAppsPermissions = similarAppsPermissions;
        this.applicationContext = applicationContext;
    }

    @NonNull
    @Override
    public SimilarSingleAppPermissionsListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View similarAppPermissionsListItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_single_app_permission_list_item , parent,false);
        return new SimilarSingleAppPermissionsListAdapterViewHolder(similarAppPermissionsListItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull SimilarSingleAppPermissionsListAdapterViewHolder holder, int position) {

//        assign data to views
        holder.applicationPermission.setText(similarAppsPermissions.get(position).getApplicationPermission());
        holder.permissionType.setText(similarAppsPermissions.get(position).getPermissionsType());
    }

    @Override
    public int getItemCount() {
        return similarAppsPermissions.size();
    }

    public class SimilarSingleAppPermissionsListAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView applicationPermission;
        public TextView permissionType;


        public SimilarSingleAppPermissionsListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

//            assigning all the view ref with actual view
            applicationPermission = itemView.findViewById(R.id.similar_single_app_permission_text);
            permissionType = itemView.findViewById(R.id.similar_single_app_permission_type_text);

        }
    }
}
