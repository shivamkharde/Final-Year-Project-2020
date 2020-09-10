package com.shivamkharde.finalyearbe2020.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.fragments.SingleApplicationInfoIconFragment;
import com.shivamkharde.finalyearbe2020.fragments.SingleApplicationInfoMainFragment;

public class SingleApplicationInfoActivity extends AppCompatActivity {


//    variable declaration
    private ImageView singleAppIcon;
    private TextView singleAppName;
    private Drawable singleAppIconFromIntent;
    private FrameLayout singleAppInfoFragmentContainer;
    private ImageView applicationInfoIcon;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_application_info);

        try {

//            getting extras passed by application list activity to get the info about single application
            Bundle extras = getIntent().getExtras();
//            getting all the values of clicked application
            String packageName  = extras.getString("packageName");
            String applicationName = extras.getString("applicationName");

//            initializing all the components
            initializeComponents();

//            showing the supported action bar and setting label as applicationName
            ActionBar m = getSupportActionBar();
            m.show();
            m.setTitle(applicationName);

//            getting application icon from package name using PackageManager
            singleAppIconFromIntent = getApplicationContext().getPackageManager().getApplicationIcon(packageName);
            Log.d("SHIVAMM:", "package Name : "+packageName);
            Log.d("SHIVAMM:", "application Name : "+applicationName);


//            setting appIcon,name
            singleAppName.setText(applicationName);
            singleAppIcon.setImageDrawable(singleAppIconFromIntent);

//            setting the default fragment in frame layout
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
//            fragment class
            SingleApplicationInfoMainFragment mainFragment = new SingleApplicationInfoMainFragment();
//            passing package name to the fragment by creating bundle
            Bundle mainFragmentBundle = new Bundle();
            mainFragmentBundle.putString("packageName",packageName);
//            setting bundle as argument in main fragment
            mainFragment.setArguments(mainFragmentBundle);
            t.replace(R.id.single_application_info_fragment_container,mainFragment);
            t.commit();

//            setting on click listener on application info icon
            applicationInfoIcon.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
//                    replace single_application_info_fragment_container with info icon fragment
                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                    t.replace(R.id.single_application_info_fragment_container,new SingleApplicationInfoIconFragment());
                    t.addToBackStack(null);
                    t.commit();
                    applicationInfoIcon.setVisibility(View.INVISIBLE);
                }
            });



        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        applicationInfoIcon.setVisibility(View.VISIBLE);
    }

    public void initializeComponents(){
//        getting application icon view
        singleAppIcon = findViewById(R.id.single_application_icon);
//        getting application text view
        singleAppName = findViewById(R.id.single_application_name);
//        getting the framelayout(fragment container)
        singleAppInfoFragmentContainer = findViewById(R.id.single_application_info_fragment_container);
//        getting application info icon
        applicationInfoIcon = findViewById(R.id.single_application_info_icon);
    }
}