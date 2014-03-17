/**
 * 
 */
package com.killam.apartment.adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.location.geocode.Address;
import com.killam.apartment.location.geocode.Geocoder;



/**
 * @author m.farhan
 *
 */
public class SuggestorAdapter extends ArrayAdapter<Address> implements Filterable{

	/**
	 * 
	 */
	private Context context;
	private List<Address> listAddress;
	private LayoutInflater mInflater;
	
	public SuggestorAdapter(final Context context) {
		// TODO Auto-generated constructor stub
		super(context, -1);
		this.context = context;
		listAddress = new ArrayList<Address>();
		
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final TextView tv;
		if (convertView == null) {
			tv = (TextView) mInflater.inflate(R.layout.auto_text_view, parent, false);

		} else {

			tv = (TextView) convertView;
		}

		tv.setTag(getItem(position));
		tv.setText(((Address)getItem(position)).getFormattedAddress());
		return tv;
		
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		
		Filter myFilter = new Filter(){

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				
				/*GeocodeTask geocodeTask = new GeocodeTask();
				geocodeTask.execute((String)constraint);*/
				
				Geocoder geocoder = new Geocoder();
				try {
					listAddress = geocoder.getFromLocationName((String)constraint, Constants.MAX_RESULT);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				final FilterResults filterResults = new FilterResults();
				filterResults.values = listAddress;
				filterResults.count = listAddress.size();

				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence constraint,FilterResults results) {
				// TODO Auto-generated method stub
				
				clear();
				if(results!=null && results.values!=null){
					for (Address data : (List<Address>) results.values) {
						
						if(data!=null)
						   add(data);
					}

					if (results.count > 0) {
						notifyDataSetChanged();
					} else {
						notifyDataSetInvalidated();
					}
				}
				
				
			}
			
		};
		return myFilter;
	}
	
	
	/*private class GeocodeTask extends AsyncTask<String, Void, List<Address>>
	{
		
		@Override
		protected void onPostExecute(List<Address> addresses)
		{
			super.onPostExecute(addresses);
		
		}

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			
		}
		
		@Override
		protected List<Address> doInBackground(String... args)
		{
			
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
*/

}
