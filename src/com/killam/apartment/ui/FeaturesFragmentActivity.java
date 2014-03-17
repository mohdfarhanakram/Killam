package com.killam.apartment.ui;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.killam.apartment.R;
import com.killam.apartment.adapter.FeatureAdapter;
import com.killam.apartment.model.detail.Building;

public class FeaturesFragmentActivity extends Fragment {
	
	private Building building;
	private ListView featureListView;
	private Context context;
	
	public static FeaturesFragmentActivity newInstance(Context context,Building building) {
		FeaturesFragmentActivity fragment = new FeaturesFragmentActivity();

		fragment.building = building;
        fragment.context  = context;

        return fragment;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_feature, container, false); 
        
        featureListView = (ListView)rootView.findViewById(R.id.featureListView);
        featureListView.setAdapter(new FeatureAdapter(context, building.getFeatures()));
         
        return rootView;
    }

}
