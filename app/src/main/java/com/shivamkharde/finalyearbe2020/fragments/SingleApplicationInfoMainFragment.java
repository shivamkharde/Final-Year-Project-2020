package com.shivamkharde.finalyearbe2020.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.activities.AppPermissionsActivity;
import com.shivamkharde.finalyearbe2020.activities.SingleApplicationInfoActivity;

public class SingleApplicationInfoMainFragment extends Fragment {


    public SingleApplicationInfoMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View singleApplicationInfoFragmentView = inflater.inflate(R.layout.fragment_single_application_info_main, container, false);

//        getting package name of single application passed as an argument
        final Bundle mainFragmentArguments = getArguments();

//        get all the ui elements of fragments here
        CardView appPermissionsCardView = singleApplicationInfoFragmentView.findViewById(R.id.app_permissions_card_view);

//        set on click listener to go to the app Permissions activity
        appPermissionsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                create intent to navigate
                Intent appPermissionsIntent = new Intent(getActivity(), AppPermissionsActivity.class);
//                passing the arguments from single application activity to the app permissions activity to get all the permissions
                appPermissionsIntent.putExtra("singleApplicationPackageName",mainFragmentArguments);
                startActivity(appPermissionsIntent);
            }
        });

        return singleApplicationInfoFragmentView;
    }
}