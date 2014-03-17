/**
 * 
 */
package com.killam.apartment.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.killam.apartment.R;
import com.killam.apartment.model.detail.Unit;
import com.killam.apartment.util.Utill;

/**
 * @author Farhan
 *
 */
public class AvailabilityAdapter extends BaseAdapter{

	/**
	 * 
	 */
	
	private ArrayList<Unit> unitList;
	private Context mContext;
    private LayoutInflater mLayoutInflater;
    
	public AvailabilityAdapter(Context context, ArrayList<Unit> unitList) {
		// TODO Auto-generated constructor stub
		this.unitList = unitList;
		mContext  = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return unitList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
			convertView = (LinearLayout) mLayoutInflater.inflate(R.layout.availablity_list_item, parent, false);
		
		
		TextView bedrom    = (TextView)convertView.findViewById(R.id.bedRoom);
		TextView price     = (TextView)convertView.findViewById(R.id.price);
		TextView available = (TextView)convertView.findViewById(R.id.available);
		
		Unit unit = unitList.get(position);
		
		bedrom.setText(unit.getBedrooms());
		//price.setText("from $"+unit.getRent()+".00");
		price.setText("from $"+String.format("%.2f",unit.getRent()));

		if(unit.getAvailabilityLabel().equalsIgnoreCase("Now")){
			available.setText("Available Now");

		}else{
			available.setText(Utill.getMonth(unit.getAvailabilityLabel()));
		}
		
		
		/*TextView availablityText = (TextView)convertView.findViewById(R.id.availablityText);
		TextView availablityNowText = (TextView)convertView.findViewById(R.id.availablityNowText);
		
		String displayText = "";
		int noOfBedrooms = 0;
		
		Unit unit = unitList.get(position);
		if(!unit.getBedrooms().equalsIgnoreCase(""))
		     noOfBedrooms = Integer.parseInt(unit.getBedrooms());
		
		displayText = unit.getBedrooms() + (noOfBedrooms>1?" Bedrooms" : " Bedroom") + " from $"+unit.getRent()+".00 - ";
		
		if(unit.getAvailabilityLabel().equalsIgnoreCase("Now")){
			availablityNowText.setText("Available Now");
			availablityNowText.setTextColor(mContext.getResources().getColor(R.color.availablity_color2));
		}else{
			availablityNowText.setText(Utill.getMonth(unit.getAvailabilityLabel()));
			availablityNowText.setTextColor(mContext.getResources().getColor(R.color.availablity_color1));
		}
		
		availablityText.setText(displayText);*/
		
		return convertView;
	}

}
