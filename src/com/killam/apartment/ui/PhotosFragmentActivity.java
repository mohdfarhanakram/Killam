package com.killam.apartment.ui;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.killam.apartment.R;
import com.killam.apartment.adapter.PhotoAdapter;
import com.killam.apartment.model.detail.Building;



public class PhotosFragmentActivity extends Fragment {
	
	private Building building;
	private ListView photoListView;
	private Context context;
	
	public static PhotosFragmentActivity newInstance(Context context,Building building) {
		PhotosFragmentActivity fragment = new PhotosFragmentActivity();

        fragment.building = building;
        fragment.context  = context;
        return fragment;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_photos, container, false);
        photoListView = (ListView)rootView.findViewById(R.id.photoListView);
        photoListView.setAdapter(new PhotoAdapter(context, building.getPhotos()));
         
        return rootView;
    }

}
