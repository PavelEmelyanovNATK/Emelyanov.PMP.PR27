package com.example.emelyanovpmppr27.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.emelyanovpmppr27.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MainFragment extends Fragment {

    MapView mapview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        MapKitFactory.setApiKey("cdd6b315-0d6f-4ba7-a1db-67e1df79a841");
        MapKitFactory.initialize(getActivity());

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mapview = view.findViewById(R.id.mapview);

        mapview.getMap().move(
                new CameraPosition(new Point(55.04409581161974, 82.91760313468653), 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        mapview.getMap().getMapObjects().addPlacemark(new Point(55.04409581161974, 82.91760313468653), ImageProvider.fromResource(getActivity(), R.drawable.avatar));

        mapview.getMap().getMapObjects().addPlacemark(new Point(55.043501, 82.917016), ImageProvider.fromResource(getActivity(), R.drawable.car));

        mapview.getMap().getMapObjects().addPlacemark(new Point(55.042144, 82.916282), ImageProvider.fromResource(getActivity(), R.drawable.car));

        mapview.getMap().getMapObjects().addPlacemark(new Point(55.042277, 82.919130), ImageProvider.fromResource(getActivity(), R.drawable.car));

        return view;
    }

    @Override
    public void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        mapview.onStart();
        MapKitFactory.getInstance().onStart();
        super.onStart();
    }
}
