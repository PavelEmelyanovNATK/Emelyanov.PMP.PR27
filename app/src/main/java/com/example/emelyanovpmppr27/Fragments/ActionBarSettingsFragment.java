package com.example.emelyanovpmppr27.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.emelyanovpmppr27.MainActivity;
import com.example.emelyanovpmppr27.R;

public class ActionBarSettingsFragment extends Fragment {
    View buttonMenuBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.actionbar_header_settings, container, false);
        buttonMenuBack = view.findViewById(R.id.menuButtonOpen);
        buttonMenuBack.setOnClickListener((MainActivity)getActivity());
        return view;
    }

}
