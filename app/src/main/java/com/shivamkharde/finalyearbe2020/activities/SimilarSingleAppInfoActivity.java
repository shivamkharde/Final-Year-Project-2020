package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.adapters.SimilarSingleAppPermissionsListAdapter;
import com.shivamkharde.finalyearbe2020.models.SimilarAppPermissions;
import com.shivamkharde.finalyearbe2020.models.SimilarApps;

import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SimilarSingleAppInfoActivity extends AppCompatActivity {

//    declare all the ui components
    private ImageView similarSingleAppInfoApplicationIcon;
    private TextView similarSingleAppInfoApplicationName;
    private TextView similarSingleAppInfoApplicationPackageName;
    private TextView similarSingleAppInfoApplicationVersion;
    private TextView similarSingleAppInfoApplicationDescription;
    private TextView similarSingleAppInfoApplicationRecentChanges;
    private TextView similarSingleAppInfoApplicationRatingText;
    private TextView similarSingleAppInfoApplicationReleasedDateText;
    private TextView similarSingleAppInfoApplicationReviewsText;
    private TextView similarSingleAppInfoApplicationGenreText;
    private TextView similarSingleAppInfoApplicationSizeText;
    private TextView similarSingleAppInfoApplicationInstallsText;
    private TextView similarSingleAppInfoApplicationAdsText;
    private TextView similarSingleAppInfoApplicationPriceText;
    private TextView similarSingleAppInfoApplicationIAPText;
    private TextView similarSingleAppInfoApplicationPolicyText;
    private TextView similarSingleAppInfoApplicationDeveloperName;
    private TextView similarSingleAppInfoApplicationDeveloperWebsiteText;
    private TextView similarSingleAppInfoApplicationDeveloperEmailText;
    private TextView similarSingleAppInfoApplicationDeveloperAddress;

    private TextView similarSingleAppInfoApplicationEditorsChoiceText;

    private Button playStoreBtn;
    private Button viewPermissionsBtn;
    private ImageView showMoreOrLessBtnForApplicationDescription;
    private ImageView showMoreOrLessBtnForApplicationRecentChanges;

//    data
    private JSONObject response;
    private JSONArray permissionArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_single_app_info);

        System.out.println("from on create");
        try {

//                get data from previous activity
                Bundle bundle = getIntent().getExtras();
                response = new JSONObject(bundle.getString("response"));
//            get permission list as well
                permissionArray = new JSONArray(bundle.getString("permissions_list"));


//            set action bar title to application title
            getSupportActionBar().setTitle(response.getString("title"));

//            initialize all the ui components
            initializeComponents();

//            add info to initialize ui components
            addInfoToUIComponents(response);

//            add click on show more application description
            showMoreOrLessBtnForApplicationDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(showMoreOrLessBtnForApplicationDescription.getTag().toString() == "show_more"){
                        similarSingleAppInfoApplicationDescription.setMaxLines(Integer.MAX_VALUE);
                        showMoreOrLessBtnForApplicationDescription.setTag("show_less");
                        showMoreOrLessBtnForApplicationDescription.setImageResource(R.drawable.ic_baseline_expand_less_24);
                    }else{
                        similarSingleAppInfoApplicationDescription.setMaxLines(4);
                        showMoreOrLessBtnForApplicationDescription.setTag("show_more");
                        showMoreOrLessBtnForApplicationDescription.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    }
                }
            });

//            add click on show more application recent changes
            showMoreOrLessBtnForApplicationRecentChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(showMoreOrLessBtnForApplicationRecentChanges.getTag().toString().equalsIgnoreCase("show_more")){
                        similarSingleAppInfoApplicationRecentChanges.setMaxLines(Integer.MAX_VALUE);
                        showMoreOrLessBtnForApplicationRecentChanges.setTag("show_less");
                        showMoreOrLessBtnForApplicationRecentChanges.setImageResource(R.drawable.ic_baseline_expand_less_24);
                    }else{
                        similarSingleAppInfoApplicationRecentChanges.setMaxLines(4);
                        showMoreOrLessBtnForApplicationRecentChanges.setTag("show_more");
                        showMoreOrLessBtnForApplicationRecentChanges.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    }
                }
            });

//            playstore btn
            playStoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent playStoreIntent = null;
                    try {
                        playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+response.getString("appId")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(playStoreIntent);
                }
            });

//            view permissions Btn
            viewPermissionsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    pass data to permission list activity
                    Intent similarSingleAppPermissionListActivityIntent = new Intent(SimilarSingleAppInfoActivity.this,SimilarSingleAppPermissionsListActivity.class);
                    similarSingleAppPermissionListActivityIntent.putExtra("response", response.toString());
                    similarSingleAppPermissionListActivityIntent.putExtra("permissions_list", permissionArray.toString());
                    startActivity(similarSingleAppPermissionListActivityIntent);
                    finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



//    this function is to add info to ui components
    private void addInfoToUIComponents(JSONObject response)throws Exception {

//        check if editors choice is true then show the text view otherwise set visibility to gone
        if(!response.getBoolean("editorsChoice")){
            similarSingleAppInfoApplicationEditorsChoiceText.setVisibility(View.GONE);
        }

//        add application icon with glide
        Glide.with(getApplicationContext()).load(response.getString("icon")).into(similarSingleAppInfoApplicationIcon);
//        add application name
        similarSingleAppInfoApplicationName.setText(response.getString("title"));
//        add application package name
        similarSingleAppInfoApplicationPackageName.setText(response.getString("appId"));

//        check if android version available and set accordingly
        if(response.getString("androidVersion") == "VARY"){
//        add application version text
            similarSingleAppInfoApplicationVersion.setText(response.getString("androidVersionText"));
        }else{
//        add application version text
            similarSingleAppInfoApplicationVersion.setText("V"+response.getString("androidVersionText"));
        }

//        add application description
        similarSingleAppInfoApplicationDescription.setText(Html.fromHtml(response.getString("descriptionHTML")));
//        check if it has recent changes object
        if(response.has("recentChanges")){
//        add application recent text
            similarSingleAppInfoApplicationRecentChanges.setText(Html.fromHtml(response.getString("recentChanges")));
        }else{
            similarSingleAppInfoApplicationRecentChanges.setText("No New Changes");
            similarSingleAppInfoApplicationRecentChanges.setGravity(Gravity.CENTER);
            showMoreOrLessBtnForApplicationRecentChanges.setVisibility(View.GONE);
        }
//        add rating text
        similarSingleAppInfoApplicationRatingText.append(response.getString("scoreText"));
        if(response.has("released")){
//        add released date
            similarSingleAppInfoApplicationReleasedDateText.setText("Released : "+response.getString("released"));
        }else{
            similarSingleAppInfoApplicationReleasedDateText.setText("Released : UNKNOWN");
        }
//        add reviews text
        similarSingleAppInfoApplicationReviewsText.setText("Reviews : "+response.getInt("ratings"));
//        add genre text
        similarSingleAppInfoApplicationGenreText.setText("Genre : "+response.getString("genre"));
//        add size text
        similarSingleAppInfoApplicationSizeText.setText("Size : "+response.getString("size"));
//        add installs text
        similarSingleAppInfoApplicationInstallsText.setText("Installs : "+response.getString("installs"));

//        check if add supported and add yes or no accordingly
        if(response.getBoolean("adSupported")){
            similarSingleAppInfoApplicationAdsText.setText("Ads Supported : Yes");
        }else {
            similarSingleAppInfoApplicationAdsText.setText("Ads Supported : No");
        }

//        check if app offers IAP
        if(response.getBoolean("offersIAP")){
//            set IAP
            similarSingleAppInfoApplicationIAPText.setText("In App Purchases : "+response.getString("IAPRange"));
        }else {
//            otherwise set IAP textview gone
            similarSingleAppInfoApplicationIAPText.setVisibility(View.GONE);
        }

//        add app privacy text
        similarSingleAppInfoApplicationPolicyText.setText("Privacy Policy : "+response.getString("privacyPolicy"));
//        add developer name
        similarSingleAppInfoApplicationDeveloperName.setText("ID/Name : "+response.getString("developerId"));

//        check if developer website available
        if (response.has("developerWebsite")) {
            similarSingleAppInfoApplicationDeveloperWebsiteText.setText("Website : " + response.getString("developerWebsite"));
        } else {
            similarSingleAppInfoApplicationDeveloperWebsiteText.setVisibility(View.GONE);
        }

//        check if dev email available
        if (response.has("developerEmail")) {
            similarSingleAppInfoApplicationDeveloperEmailText.setText("Email : " + response.getString("developerEmail"));
        } else {
            similarSingleAppInfoApplicationDeveloperEmailText.setVisibility(View.GONE);
        }

//        check if developer address available
        if (response.has("developerAddress")) {
            similarSingleAppInfoApplicationDeveloperAddress.setText("Address : " + response.getString("developerAddress"));
        } else {
            similarSingleAppInfoApplicationDeveloperAddress.setVisibility(View.GONE);
        }

    }

    //    initializing all the UI components
    private void initializeComponents() {
//        for application Icon
        similarSingleAppInfoApplicationIcon = findViewById(R.id.similar_app_single_info_application_icon);
//        for application name
        similarSingleAppInfoApplicationName = findViewById(R.id.similar_app_single_info_application_name);
//        for package name
        similarSingleAppInfoApplicationPackageName = findViewById(R.id.similar_app_single_info_application_package_name);
//        for version text
        similarSingleAppInfoApplicationVersion = findViewById(R.id.similar_app_single_info_application_version);
//        for application description
        similarSingleAppInfoApplicationDescription = findViewById(R.id.similar_app_single_info_application_description);
//        for recent changes
        similarSingleAppInfoApplicationRecentChanges = findViewById(R.id.similar_app_single_info_application_recent_changes);
//        for ratings text
        similarSingleAppInfoApplicationRatingText = findViewById(R.id.similar_app_single_info_application_ratings_text);
//        for released text
        similarSingleAppInfoApplicationReleasedDateText = findViewById(R.id.similar_app_single_info_application_released_date_text);
//        for reviews text
        similarSingleAppInfoApplicationReviewsText = findViewById(R.id.similar_app_single_info_application_reviews_text);
//        for genre text
        similarSingleAppInfoApplicationGenreText = findViewById(R.id.similar_app_single_info_application_genre_text);
//        for size text
        similarSingleAppInfoApplicationSizeText = findViewById(R.id.similar_app_single_info_application_size_text);
//        for installs text
        similarSingleAppInfoApplicationInstallsText = findViewById(R.id.similar_app_single_info_application_installs_text);
//        for ads supported text
        similarSingleAppInfoApplicationAdsText = findViewById(R.id.similar_app_single_info_application_ads_text);
//        for price
        similarSingleAppInfoApplicationPriceText = findViewById(R.id.similar_app_single_info_application_price_text);
//        for IAP
        similarSingleAppInfoApplicationIAPText = findViewById(R.id.similar_app_single_info_application_IAP_text);
//        for privacy policy
        similarSingleAppInfoApplicationPolicyText = findViewById(R.id.similar_app_single_info_application_policy_text);
//        for dev name
        similarSingleAppInfoApplicationDeveloperName = findViewById(R.id.similar_app_single_info_application_developer_name);
//        developer website
        similarSingleAppInfoApplicationDeveloperWebsiteText = findViewById(R.id.similar_app_single_info_application_developer_website_text);
//        for dev email
        similarSingleAppInfoApplicationDeveloperEmailText = findViewById(R.id.similar_app_single_info_application_developer_email_text);
//        for dev address
        similarSingleAppInfoApplicationDeveloperAddress = findViewById(R.id.similar_app_single_info_application_developer_address);

//        editors choice text view
        similarSingleAppInfoApplicationEditorsChoiceText = findViewById(R.id.similar_app_single_info_application_editors_choice_text);

//        show more application description btn
        showMoreOrLessBtnForApplicationDescription = findViewById(R.id.similar_app_single_info_application_description_show_more_btn);
//        show more application recent changes
        showMoreOrLessBtnForApplicationRecentChanges = findViewById(R.id.similar_app_single_info_application_recent_changes_show_more_btn);

//        playstore btn
        playStoreBtn = findViewById(R.id.similar_app_single_info_application_open_in_playstore_btn);
//        view permissions btn
        viewPermissionsBtn = findViewById(R.id.similar_app_single_info_application_see_permissions);


    }

}