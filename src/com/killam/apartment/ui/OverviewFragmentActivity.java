package com.killam.apartment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.imgUtils.ImageLoader;
import com.killam.apartment.model.detail.Building;




public class OverviewFragmentActivity extends Fragment {
	
	private Building building;
	private static final String KEY_CONTENT = "TestFragment:Content";
	
	private static ImageLoader mImageDownloader;
	
	public static OverviewFragmentActivity newInstance(Context context,Building building) {
		OverviewFragmentActivity fragment = new OverviewFragmentActivity();
		mImageDownloader = new ImageLoader(context);
        fragment.building = building;

        return fragment;
    }
	
	/* @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
	        	building = savedInstanceState.get(KEY_CONTENT);
	        }
	    }*/

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        
        ((TextView)rootView.findViewById(R.id.overViewTitle)).setText(building.getPropertyName());
        ((TextView)rootView.findViewById(R.id.overAddress)).setText(building.getAddressLine1());
        ((TextView)rootView.findViewById(R.id.overViewDescription)).setText(filterString(building.getMetaDescription()));
        ImageView bannerImageView = (ImageView)rootView.findViewById(R.id.overviewBannerImage);
        
        if(building.getPhotos()!=null && building.getPhotos().size()>0){
        	
        	
        	mImageDownloader.DisplayImage(Constants.BASE_URL+building.getPhotos().get(0).getFullUrl(), bannerImageView);
        }
        
        
        
         
        return rootView;
    }
	
	private String filterString(String string){
		
		String buildingDescription = "";
		
		Log.e("Before Filter :", string);
		
		if(string.contains(Constants.REMOVE_STRING)){
			
			int firstindex = string.indexOf("<a");
			int lastIndex = string.indexOf("</a>");
			
			if(firstindex!= -1 && lastIndex != -1){
				buildingDescription  = string.replace(string.subSequence(firstindex, lastIndex+1), "");
				Log.e("After Filter :", buildingDescription);
			}else{
				buildingDescription = string;
			}
			
		}else{
			buildingDescription = string;
		}
		
		return buildingDescription;
		
	}

}
