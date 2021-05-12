package com.shivamkharde.finalyearbe2020.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.activities.SimilarAppForSingleInstalledAppActivity;
import com.shivamkharde.finalyearbe2020.models.SimilarAppHTTPClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

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
//                call an api to search for the similar app for this package name if not available then search by its name
                try{
                    getAllSimilarApplicationsByPackageName(applicationPackageName, (String) applicationName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

//    this method is to get all the similar applications by its package name and if not available then by its application name
    private void getAllSimilarApplicationsByPackageName(String packageName,String applicationName) {

//        show progress dialog
        ProgressDialog p = new ProgressDialog(applicationContext);
        p.setMessage("Please wait while we are fetching details!!!");
        p.setCancelable(false);
        p.setMax(100);
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        p.show();

//        request an API
        SimilarAppHTTPClient.get("api/apps/"+packageName+"/similar/", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                p.dismiss();
                try {
//                    get the json array from json object
                    JSONArray responseArray = response.getJSONArray("results");

//                    on success get all the necessary data and passit through intent to show the list of similar apps to the user on next page
//                    create an intent to switch the window with response data
                    Intent similarAppForSingleInstalledAppIntent = new Intent(applicationContext, SimilarAppForSingleInstalledAppActivity.class);
//                    passing response data as a intent data to the next activity
                    similarAppForSingleInstalledAppIntent.putExtra("response", responseArray.toString());
//                passing single application info details
                    applicationContext.startActivity(similarAppForSingleInstalledAppIntent);

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
                al.setMessage("Similar Data not available for this app!! Do you want to search the apps similar to this name (data might not be accurate)");
                al.setCancelable(false);
                al.setTitle("Not Found!!");
                al.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        call another function to search application by its name
                        getAllSimilarApplicationsByApplicationName(applicationName);
                    }
                });
                al.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        do nothing and hide the progress dialog and alert dialog
                        p.dismiss();
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

//    this method is to get all the similar apps by its application name
    private void getAllSimilarApplicationsByApplicationName(String applicationName) {
//        show progress dialog
        ProgressDialog p = new ProgressDialog(applicationContext);
        p.setMessage("Searching with application name...Please wait");
        p.setCancelable(false);
        p.setMax(100);
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        p.show();

//        request an API
        SimilarAppHTTPClient.get("api/apps/?q="+applicationName, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                p.dismiss();
                try {
//                    get the json array from json object
                    JSONArray responseArray = response.getJSONArray("results");

//                    on success get all the necessary data and pass it through intent to show the list of similar apps to the user on next page
//                    create an intent to switch the window with response data
                    Intent similarAppForSingleInstalledAppIntent = new Intent(applicationContext, SimilarAppForSingleInstalledAppActivity.class);
//                    passing response data as a intent data to the next activity
                    similarAppForSingleInstalledAppIntent.putExtra("response", responseArray.toString());
//                    passing single application info details
                    applicationContext.startActivity(similarAppForSingleInstalledAppIntent);

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
                al.setMessage("Similar Data not available for this app!! Do you want to search the apps similar to this name (data might not be accurate)");
                al.setCancelable(false);
                al.setTitle("Not Found!!");
                al.setPositiveButton("OK", null);
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
