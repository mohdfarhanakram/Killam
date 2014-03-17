package com.killam.apartment.location.geocode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.killam.apartment.constants.Constants;
import com.killam.apartment.controller.AppartmentsRequestHandler;
import com.killam.apartment.controller.AppartmentsRequestListener;



public class GeocodeTask extends AsyncTask<String, Void, List<Address>>
{
	
	private GeocodeListener listener;
	//private Context context;
	
	/**
	 * Construct a new {@link AppartmentsRequestHandler}.
	 * 
	 * @param context
	 * @param listener
	 */
	public GeocodeTask(GeocodeListener listener) {
		this.listener = listener;
		//this.context = context;
		
	}
	
	@Override
	protected void onPostExecute(List<Address> addresses)
	{
		super.onPostExecute(addresses);
	
		// Check the number of results
		if (addresses != null)
		{
			if (addresses.size() > 0)
			{
				// Determine which address to display
				listener.onGetGeoSuccessResult(addresses.get(0));
				
			}
			else
			{
				listener.onGetGeoSuccessResult(null);
				// Display address
				
			}
		}
		else
		{
			listener.onGetGeoSuccessResult(null);
		}
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		
	}
	
	@Override
	protected List<Address> doInBackground(String... args)
	{
		// Declare
		List<Address> listAddress = new ArrayList<Address>();
		
		// Extract parameters
		String locationName = args[0];
	
		Geocoder geocoder = new Geocoder();
		try {
			listAddress = geocoder.getFromLocationName(locationName, Constants.MAX_RESULT);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Return
		return listAddress;
	}
};
