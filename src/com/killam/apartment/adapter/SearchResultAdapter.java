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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.imgUtils.ImageLoader;
import com.killam.apartment.model.list.Building;
import com.killam.apartment.util.Utill;




/**
 * @author Farhan
 *
 */
public class SearchResultAdapter extends BaseAdapter{

	/**
	 * 
	 */
	
	private Context mContext;
    private LayoutInflater mLayoutInflater;
    public static ArrayList<Building> mEntries = new ArrayList<Building>();
    private ImageLoader mImageDownloader;
    
    private double currentLat,currentLon;
    
	public SearchResultAdapter(Context context,ArrayList<Building> mEntries,double currentLat,double currentLon) {
		// TODO Auto-generated constructor stub
		mContext  = context;
		this.mEntries = mEntries;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageDownloader = new ImageLoader(context);
		
		this.currentLat = currentLat;
		this.currentLon = currentLon;
	}
	
	public class ViewHolder {
		ImageView imageView;
        TextView titleText;
        TextView descriptionText;
        TextView listDistanceText;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mEntries.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mEntries.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
	        if (convertView == null){
	        	holder = new ViewHolder();
	        	convertView = (RelativeLayout) mLayoutInflater.inflate(R.layout.list_item, parent, false);
	            holder.imageView = (ImageView) convertView.findViewById(R.id.listImage);
	            holder.titleText = (TextView) convertView.findViewById(R.id.listTitle);
	            holder.descriptionText = (TextView) convertView.findViewById(R.id.listDescription);
	            holder.listDistanceText = (TextView) convertView.findViewById(R.id.listDistance);
	            convertView.setTag(holder);
	        }else{
	        	holder = (ViewHolder) convertView.getTag();
	        }

	        
	        
	        String imageUrl = Constants.BASE_URL+mEntries.get(position).getphoto().getthumbnailUrl().toString();
	        mImageDownloader.DisplayImage(imageUrl, holder.imageView);
	        
	        String title = mEntries.get(position).getpropertyName().toString().trim();
	        String description = mEntries.get(position).getaddressLine1().toString().trim();
	        holder.titleText.setText(title);
	        holder.descriptionText.setText(description);
	        
	        if(title.equalsIgnoreCase(description)){
	        	holder.descriptionText.setVisibility(View.GONE);
	        }else{
	        	holder.descriptionText.setVisibility(View.VISIBLE);
	        }
	        
	        
	        holder.listDistanceText.setText( String.format("%.2f",Utill.distance(currentLat, currentLon,mEntries.get(position).getlat(), mEntries.get(position).getlon()))+" km");
	        
	        
	        return convertView;
	}

}
