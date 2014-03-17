package com.killam.apartment.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.controller.AppartmentsRequestHandler;
import com.killam.apartment.controller.AppartmentsRequestListener;
import com.killam.apartment.model.detail.Coordinate;
import com.killam.apartment.model.detail.GetBuilding;

public class ShowMapActivity extends BaseFragmentActivity implements AppartmentsRequestListener,OnCameraChangeListener{
	private GoogleMap mMap;
	String[] longitude;
	String[] latitude;
	
	private String VIEW_URL = "";
	public static GetBuilding GET_BUILDING;
	private ArrayList<Coordinate> coordinateArraList;
	
	private boolean isFromListScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		setActionBar("Apartments - Maps", true, false, true, true);
		shareBtn.setImageResource(R.drawable.list);
		
		shareBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(isFromListScreen){
					finish();
				}else{
					Intent intent = new Intent(ShowMapActivity.this,AppartmentListActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(intent);
					finish();
				}
				
			}
		});

		coordinateArraList = getIntent().getParcelableArrayListExtra("COORDINATES");
		Log.e("Farhan..size", coordinateArraList.size()+"");
		isFromListScreen = getIntent().getBooleanExtra("IS_FROM_LIST_SCREEN", false);
		

		setUpMapIfNeeded();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
				
				if(coordinateArraList.size()>0){
					final LatLng pos = new LatLng(Float.parseFloat(coordinateArraList.get(0).getLat()), Float.parseFloat(coordinateArraList.get(0).getLon()));
					mMap.setOnCameraChangeListener(new OnCameraChangeListener() {
					        public void onCameraChange(CameraPosition arg0) {
					        	mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 13));
					        	mMap.setOnCameraChangeListener(null);
					        	
					        }
					    });
				}
				
				
				
				
				
				mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
					
					@Override
					public void onInfoWindowClick(Marker marker) {
						// TODO Auto-generated method stub
						if(!isFromListScreen){
							finish();
						}else{
							hitRequestForDetial(getBuildingId(marker));
						}
						
					}
				});
				
				
			}
		}
	}

	private void setUpMap() {
		
		

		for (int i = 0; i < coordinateArraList.size(); i++) {
			LatLng pos = new LatLng(Float.parseFloat(coordinateArraList.get(i).getLat()), Float.parseFloat(coordinateArraList.get(i).getLon()));
			mMap.addMarker(new MarkerOptions()
					.position(pos)
					.title(coordinateArraList.get(i).getMarkerTitle().toString())
					.snippet(coordinateArraList.get(i).getAddressLine()));
					
			
		    
					
			
		}

	}
	
	protected void hitRequestForDetial(String buildingId) {
		// TODO Auto-generated method stub
		startSppiner();
		VIEW_URL = VIEW_URL + Constants.VIEW_URL + "building.id="
				+ buildingId;
		Log.e("Farhan ", VIEW_URL);
		AppartmentsRequestHandler handeler = new AppartmentsRequestHandler(this, this, VIEW_URL, Constants.VIEW_EVENT);
		handeler.execute();
	}

	@Override
	public void onSuccess(String result, int event) {
		// TODO Auto-generated method stub
		stopSppiner();
		Log.e("farhan", result);
		Gson gson = new Gson();
		try{
			GET_BUILDING = gson.fromJson(result, GetBuilding.class);
			Log.e("farhan", "Building Id : " + GET_BUILDING.getBuilding().getId());
			if(GET_BUILDING!= null){
				Intent intent = new Intent(ShowMapActivity.this,AppartmentDetailActivity.class);
				intent.putExtra("GO_DETAIL_SCREEN_FROM_LIST", false);
				startActivity(intent);
			}else{
				Toast.makeText(this, Constants.SOME_ERROR,Toast.LENGTH_LONG).show();
			}
			

		}catch(Exception ex){
			Toast.makeText(this, Constants.SOME_ERROR,Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public void onFailure() {
		// TODO Auto-generated method stub
		
	}
	
	private String getBuildingId(Marker marker){
		String id = "";
		for(int i=0; i<coordinateArraList.size();i++){
			
			if(marker.getTitle().equalsIgnoreCase(coordinateArraList.get(i).getMarkerTitle()) && marker.getSnippet().equalsIgnoreCase(coordinateArraList.get(i).getAddressLine())){
				id = coordinateArraList.get(i).getBuildingId();
				break;
			}
			
		}
		return id;
	}

	@Override
	public void onCameraChange(CameraPosition arg0) {
		// TODO Auto-generated method stub
		
	}


}
