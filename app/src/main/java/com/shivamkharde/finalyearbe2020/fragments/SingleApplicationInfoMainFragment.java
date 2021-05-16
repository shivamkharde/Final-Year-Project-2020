package com.shivamkharde.finalyearbe2020.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shivamkharde.finalyearbe2020.R;
import com.shivamkharde.finalyearbe2020.activities.AppPermissionsActivity;
import com.shivamkharde.finalyearbe2020.activities.SingleApplicationInfoActivity;

import org.w3c.dom.Text;

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
        CardView otherPermissionsCardView = singleApplicationInfoFragmentView.findViewById(R.id.other_permissions_card_view);
        ImageView singleUninstallBtn = singleApplicationInfoFragmentView.findViewById(R.id.single_uninstall_btn);

//        set onclick listener on uninstall btn
        singleUninstallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = mainFragmentArguments.getString("packageName");
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+packageName));
                startActivity(intent);
            }
        });

//        set on click listener to go to the app Permissions activity
        appPermissionsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                create intent to navigate
                Intent appPermissionsIntent = new Intent(getActivity(), AppPermissionsActivity.class);
//                passing the arguments from single application activity to the app permissions activity to get all the permissions
                appPermissionsIntent.putExtra("singleApplicationPackageName",mainFragmentArguments);
                appPermissionsIntent.putExtra("normal",true);
                startActivity(appPermissionsIntent);
            }
        });


//        set listener on other permission view to get all other permissions(dangerous)
        otherPermissionsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                create intent to navigate
                Intent otherPermissionsIntent = new Intent(getActivity(), AppPermissionsActivity.class);
//                passing the arguments from single application activity to the app permissions activity to get all the permissions
                otherPermissionsIntent.putExtra("singleApplicationPackageName",mainFragmentArguments);
                otherPermissionsIntent.putExtra("normal",false);
                startActivity(otherPermissionsIntent);
            }
        });


        return singleApplicationInfoFragmentView;
    }
}