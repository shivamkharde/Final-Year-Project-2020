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
import com.shivamkharde.finalyearbe2020.models.SimilarApps;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


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

                String packageName = similarApps.get(position).getPackageName();
                String permissionsUrl = similarApps.get(position).getPermissionsUrl();

                try{
//                get detail info of app by detail app url
                    getDetailSimilarAppInfoAndNavigate(packageName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

//    this function is to get detail info about the app
    private void getDetailSimilarAppInfoAndNavigate(String packageName) {

//        show progress dialog
        ProgressDialog p = new ProgressDialog(applicationContext);
        p.setMessage("Please wait while we are fetching details for this app!!!");
        p.setCancelable(false);
        p.setMax(100);
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        p.show();

//        request an API
        SimilarAppHTTPClient.get("api/apps/"+packageName, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                p.dismiss();
                try {
//                    get permissions of current package and also pass other details
                    getSimilarAppPermissionsAndNavigate(packageName,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                p.dismiss();
//                create a dialog to show the error
                AlertDialog.Builder al = new AlertDialog.Builder(applicationContext);
                al.setMessage("something went wrong !!! check your internet connection");
                al.setCancelable(false);
                al.setTitle("Ahh Ohh!!!");
                al.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        do nothing and hide the progress dialog and alert dialog
                        p.dismiss();
//                        do nothing
                    }
                });
                al.show();
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int progress = (int) ((bytesWritten*100)/totalSize);
                p.setProgress(progress);
            }
        });
    }

//    this method is to get all the permissions for the app and navigate to single similar app
    private void getSimilarAppPermissionsAndNavigate(String packageName, JSONObject otherAppInfo) {

//        show progress dialog
        ProgressDialog p = new ProgressDialog(applicationContext);
        p.setMessage("Please wait while we are fetching permission details for this app!!!");
        p.setCancelable(false);
        p.setMax(100);
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        p.show();

//        request an API
        SimilarAppHTTPClient.get("api/apps/"+packageName+"/permissions", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                p.dismiss();
                try {
//                    get permissions object and get array from it
                    JSONArray responseArray = response.getJSONArray("results");

//                    on success get all the necessary data and passit through intent to show the list of similar apps to the user on next page
//                    create an intent to switch the window with response data
                    Intent similarSingleAppInfoIntent = new Intent(applicationContext, SimilarSingleAppInfoActivity.class);
//                    passing response data as a intent data to the next activity
                    similarSingleAppInfoIntent.putExtra("response", otherAppInfo.toString());
                    similarSingleAppInfoIntent.putExtra("permissions_list", responseArray.toString());
//                passing single application info details
                    applicationContext.startActivity(similarSingleAppInfoIntent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                p.dismiss();
//                create a dialog to show the error
                AlertDialog.Builder al = new AlertDialog.Builder(applicationContext);
                al.setMessage("something went wrong !!! check your internet connection");
                al.setCancelable(false);
                al.setTitle("Ahh Ohh!!!");
                al.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        do nothing and hide the progress dialog and alert dialog
                        p.dismiss();
//                        do nothing
                    }
                });
                al.show();
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int progress = (int) ((bytesWritten*100)/totalSize);
                p.setProgress(progress);
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
