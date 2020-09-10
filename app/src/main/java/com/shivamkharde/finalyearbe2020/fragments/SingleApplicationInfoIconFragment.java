package com.shivamkharde.finalyearbe2020.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shivamkharde.finalyearbe2020.R;

public class SingleApplicationInfoIconFragment extends Fragment {

    public SingleApplicationInfoIconFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_single_application_info_icon, container, false);
    }
}