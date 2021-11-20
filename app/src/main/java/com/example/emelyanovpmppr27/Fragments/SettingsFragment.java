package com.example.emelyanovpmppr27.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.emelyanovpmppr27.MainActivity;
import com.example.emelyanovpmppr27.R;

public class SettingsFragment extends Fragment {
    View btnLogOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        btnLogOut = view.findViewById(R.id.lblExit);
        btnLogOut.setOnClickListener((MainActivity)getActivity());
        return view;
    }

}
