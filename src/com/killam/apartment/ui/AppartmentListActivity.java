/**
 * 
 */
package com.killam.apartment.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.killam.apartment.R;
import com.killam.apartment.adapter.SearchResultAdapter;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.controller.AppartmentsRequestHandler;
import com.killam.apartment.controller.AppartmentsRequestListener;
import com.killam.apartment.model.detail.Coordinate;
import com.killam.apartment.model.detail.GetBuilding;
import com.killam.apartment.model.list.Building;
import com.killam.apartment.util.Utill;





/**
 * @author m.farhan
 * 
 */
public class AppartmentListActivity extends BaseActivity implements
AppartmentsRequestListener {

	/**
	 * 
	 */
	private ListView appartmentList;
	double geo_lat, geo_lon;
	private String SEARCH_URL = "";
	//private String VIEW_URL = "";
	
	private int search_loc;

	public static GetBuilding GET_BUILDING;
	
	ArrayList<Building> searchResult = new ArrayList<Building>();

	public AppartmentListActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		setActionBar(R.string.appartment_title, true, true, false, true);
		appartmentList = (ListView) findViewById(R.id.appartmentListView);
		appartmentList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View v,int position, long id) {
				Building building = SearchResultAdapter.mEntries.get(position);
				hitRequestForDetial(building);

			}
		});

		geo_lat = getIntent().getDoubleExtra("search.geo-lat", 0);
		geo_lon = getIntent().getDoubleExtra("search.geo-lon", 0);
		
		//For testing need to remove         
		/*geo_lat =  47.58349579999999;
		geo_lon = -52.69798609999998;*/
		int price_min = getIntent().getIntExtra("search.price-min", 0);
		int price_max = getIntent().getIntExtra("search.price-max", 2000);
		int bedroom_min = getIntent().getIntExtra("search.bedroom-number", 0);
		search_loc = getIntent().getIntExtra("search.loc", 0);

		SEARCH_URL = Constants.SEARCH_URL + "search.bedroom-number=%22"
				+ bedroom_min + "%22&search.price-min=" + price_min
				+ "&search.price-max=" + price_max;

		if (search_loc == 2) {

			SEARCH_URL = SEARCH_URL
					+ "&search.province="
					+ Utill.getProvinceCode(getIntent().getStringExtra("search.province"));
			  SEARCH_URL=SEARCH_URL+"&search.city="+
			  getIntent().getStringExtra("search.city");
		} else if (search_loc == 3) {
			SEARCH_URL = SEARCH_URL + "&search.address="
					+ getIntent().getStringExtra("search.address");
		} else if (search_loc == 1) {
			SEARCH_URL = SEARCH_URL + "&search.geo-lat=" + geo_lat
					+ "&search.geo-lon=" + geo_lon;
		}

		hitRequestForSearch();

	}

	protected void hitRequestForDetial(Building building) {
		// TODO Auto-generated method stub
		startSppiner();
		String url = Constants.VIEW_URL + "building.id="+ building.getId();
		Log.e("Detail", url);
		AppartmentsRequestHandler handeler = new AppartmentsRequestHandler(this, this, url, Constants.VIEW_EVENT);
		handeler.execute();
	}

	private void hitRequestForSearch() {

		startSppiner();

		Log.e("Farhan", SEARCH_URL);

		AppartmentsRequestHandler handeler = new AppartmentsRequestHandler(
				this, this, SEARCH_URL, Constants.SEARCH_EVENT);
		handeler.execute();
	}

	@Override
	public void onSuccess(String result, int event) {
		// TODO Auto-generated method stub
		stopSppiner();
		if (event == Constants.SEARCH_EVENT) {

			searchResult = new ArrayList<Building>();
			Gson gson = new Gson();
			JSONArray jArray;

			try {
				/*JSONObject jObj = new JSONObject(result.toString().replaceAll(
						",\"long\":", ",\"lon\":"));*/
				
				JSONObject jObj = new JSONObject(result);
				jArray = jObj.getJSONArray("results");
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject job = jArray.getJSONObject(i);
					searchResult.add(gson.fromJson(job
							.getJSONObject("building").toString(),
							Building.class));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				try {
					JSONObject jObj = new JSONObject(result);
					Toast.makeText(
							this,
							(jObj.getJSONObject("system").getJSONArray(
									"notifications").getJSONObject(0))
									.getString("body"), Toast.LENGTH_LONG)
									.show();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					Toast.makeText(this, Constants.SOME_ERROR,
							Toast.LENGTH_LONG).show();
					e1.printStackTrace();
				}

			}
			
			/*
			 * This is used to sort the distance 
			 * In case of near me filter the distance
			 */
			
			ArrayList<Building> sResList = setDistance(searchResult);
			
			Collections.sort(sResList, new DistanceComparator());

			if (sResList.size() > 0) {
				appartmentList.setAdapter(new SearchResultAdapter(this,sResList,geo_lat,geo_lon));
			} else {
				Toast.makeText(this, Constants.NO_RESULT_FOUND,
						Toast.LENGTH_LONG).show();
			}

		} else if (event == Constants.VIEW_EVENT) {
			Log.e("farhan", result);
			Gson gson = new Gson();
			try{
				GET_BUILDING = gson.fromJson(result, GetBuilding.class);
				Log.e("farhan", "Building Id : " + GET_BUILDING.getBuilding().getId());
				if(GET_BUILDING!= null){
					Intent intent = new Intent(AppartmentListActivity.this,AppartmentDetailActivity.class);
					intent.putExtra("GO_DETAIL_SCREEN_FROM_LIST", true);
					startActivity(intent);
				}else{
					Toast.makeText(this, Constants.SOME_ERROR,
							Toast.LENGTH_LONG).show();
				}
				

			}catch(Exception ex){
				Toast.makeText(this, Constants.SOME_ERROR,
						Toast.LENGTH_LONG).show();
			}


		}

	}

	@Override
	public void onFailure() {
		stopSppiner();
		// TODO Auto-generated method stub
		Toast.makeText(this, Constants.NETWORK_ERROR, Toast.LENGTH_LONG).show();
	}
	
  @Override
   protected void startMapActivity() {
	  
	 
	    Intent intent = new Intent(AppartmentListActivity.this, ShowMapActivity.class);
	  
		intent.putExtra("COORDINATES", getCoordinates());
		intent.putExtra("IS_FROM_LIST_SCREEN", true);
		startActivity(intent);
	  
  }
  
  
  private ArrayList<Coordinate> getCoordinates(){
	  
	  ArrayList<Coordinate> returnList = new ArrayList<Coordinate>();
	  
	  for(int i = 0; i < searchResult.size(); i++){
		  
		  returnList.add(new Coordinate(searchResult.get(i).getId(),searchResult.get(i).getpropertyName(),String.valueOf(searchResult.get(i).getlat()), String.valueOf(searchResult.get(i).getlon()),searchResult.get(i).getaddressLine1()));
	  }
	  
	  return returnList;
	  
  }
  
  /**
 * @author m.farhan
 *
 */
  
 private ArrayList<Building> setDistance(ArrayList<Building> buildList){
	 
	 ArrayList<Building> blist = new ArrayList<Building>();
	 for(int i = 0 ;i < buildList.size(); i++){
		 Building b = buildList.get(i);
		 b.setDistance(Utill.distance(geo_lat, geo_lon, b.getlat(),b.getlon()));
		 if (search_loc == 1) {
			 Double nearmevalue = getIntent().getDoubleExtra("search.nearme", 1.00);
			 if(Utill.distance(geo_lat, geo_lon, b.getlat(),b.getlon())<=nearmevalue){
				 b.setDistance(Utill.distance(geo_lat, geo_lon, b.getlat(),b.getlon()));
				 blist.add(b); 
			 }
				
		 }else{
			 b.setDistance(Utill.distance(geo_lat, geo_lon, b.getlat(),b.getlon()));
			 blist.add(b);
		 }
		 
	 }
	 
	 return blist;
 }
private class DistanceComparator implements Comparator<Building>
  {

	@Override
	public int compare(Building first, Building seconnd) {
		// TODO Auto-generated method stub
		 //double lhsDistance = Utill.distance(geo_lat, geo_lon, first.getlat(),seconnd.getlon());
   	     //double rhsDistance = Utill.distance(geo_lat, geo_lon, seconnd.getlat(),seconnd.getlon());
		
		double lhsDistance = first.getDistance();
  	    double rhsDistance = seconnd.getDistance();
   	  
   	  //Log.e("First Distance", lhsDistance+"");
   	  //Log.e("Second Distance", rhsDistance+"");
   	  
   	  if (lhsDistance < rhsDistance){
   		return -1;
   	  }else if (lhsDistance > rhsDistance) {
   		return 1; 
   	  }else{
   		return 0; 
   	  }
                 
		
	}
	 

   
  }
  
	
	/*public static GetBuilding getBuildingObj(){
		return GET_BUILDING;
	}*/

}
