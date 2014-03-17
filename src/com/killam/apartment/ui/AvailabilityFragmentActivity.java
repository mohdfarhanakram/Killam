package com.killam.apartment.ui;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.killam.apartment.R;
import com.killam.apartment.model.detail.Building;
import com.killam.apartment.model.detail.Unit;
import com.killam.apartment.util.Utill;




public class AvailabilityFragmentActivity extends Fragment {
	
private Building building;
private ListView availabilityListView;
private Context context;
private static LayoutInflater mLayoutInflater;

private TableLayout tableLayout;
	
	public static AvailabilityFragmentActivity newInstance(Context context,Building building) {
		AvailabilityFragmentActivity fragment = new AvailabilityFragmentActivity();

		fragment.building = building;
        fragment.context  = context;
        
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return fragment;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_availability, container, false);
        tableLayout = (TableLayout)rootView.findViewById(R.id.tableLayout);
        ArrayList<Unit> unitList = building.getUnits();
        if(unitList.size()==0){
        	((TextView)rootView.findViewById(R.id.noDataAvailable)).setVisibility(View.VISIBLE);
        	tableLayout.setVisibility(View.GONE);
        }else{
        	((TextView)rootView.findViewById(R.id.noDataAvailable)).setVisibility(View.GONE);
        	tableLayout.setVisibility(View.VISIBLE);
        }
        for(int i =0 ; i< unitList.size(); i++){
        	Unit unit = unitList.get(i);
        	TableRow convertView = (TableRow) mLayoutInflater.inflate(R.layout.table_row_layout, null, false);
        	//convertView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, Utill.dpToPx(70)));
        	Log.e("Farhan...", unit.getAvailabilityLabel());
        	Log.e("Farhan.BED..", unit.getBedrooms());
        	Log.e("Farhan.Price..", unit.getRent());
        	TextView bedrom    = (TextView)convertView.findViewById(R.id.bedRoom);
    		TextView price     = (TextView)convertView.findViewById(R.id.price);
    		TextView available = (TextView)convertView.findViewById(R.id.available);
    		
    		TextView applyButton = (TextView)convertView.findViewById(R.id.applyButton);
    		applyButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					((AppartmentDetailActivity)getActivity()).openDialog();
					
					/*Intent i = new Intent(context, WebViewActivity.class);
					startActivity(i);*/

				}
			});
    		
    		bedrom.setText(unit.getBedrooms());
    		price.setText("from $"+unit.getRent()+".00");
    		
    		if(unit.getAvailabilityLabel().equalsIgnoreCase("Now")){
    			available.setText("Now");

    		}else{
    			available.setText(Utill.getMonth(unit.getAvailabilityLabel()));
    		}
    		
    		View v = new View(context);
        	v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
        	v.setBackgroundColor(Color.rgb(51, 51, 51));
        	tableLayout.addView(v);
        	
        	tableLayout.addView(convertView);
        	
        	if(i == unitList.size()-1){
        		View v1 = new View(context);
            	v1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
            	v1.setBackgroundColor(Color.rgb(51, 51, 51));
            	tableLayout.addView(v1);
        	}
        	
        	
        	
        }
        /*availabilityListView = (ListView)rootView.findViewById(R.id.availableListView);
        availabilityListView.setAdapter(new AvailabilityAdapter(context, building.getUnits()));*/
         
        return rootView;
    }

}
