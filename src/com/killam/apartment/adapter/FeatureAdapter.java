/**
 * 
 */
package com.killam.apartment.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.killam.apartment.R;
import com.killam.apartment.model.detail.Feature;

/**
 * @author Farhan
 *
 */
public class FeatureAdapter extends BaseAdapter{

	/**
	 * 
	 */
	
	private ArrayList<Feature> featureList;
	private Context mContext;
    private LayoutInflater mLayoutInflater;
    
    private static final String BULLET_SYMBOL = "&#8226";
   
	public FeatureAdapter(Context context, ArrayList<Feature> featureList) {
		// TODO Auto-generated constructor stub
		
		this.featureList = featureList;
		mContext  = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return featureList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
			convertView = (LinearLayout) mLayoutInflater.inflate(R.layout.feature_list_item, parent, false);
		
		TextView featureTextView = (TextView)convertView.findViewById(R.id.featureText);
		featureTextView.setText(Html.fromHtml(BULLET_SYMBOL + " "+featureList.get(position).getName()));
		return convertView;
	}

}
