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

import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.imgUtils.ImageLoader;
import com.killam.apartment.model.detail.Photo;


/**
 * @author m.farhan
 *
 */
public class PhotoAdapter extends BaseAdapter{

	/**
	 *  Adapter: this is used to display photo
	 */
	private ArrayList<Photo> photoList;
	private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ImageLoader mImageDownloader;
	
	public PhotoAdapter(Context context,ArrayList<Photo> photoList) {
		// TODO Auto-generated constructor stub
		this.photoList = photoList;
		mContext  = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageDownloader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photoList.size();
	}

	@Override
	public Object getItem(int arg0) {
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
			convertView = (RelativeLayout) mLayoutInflater.inflate(R.layout.layout_photo, parent, false);
		
		ImageView photoImageView = (ImageView)convertView.findViewById(R.id.photoImageView);
		mImageDownloader.DisplayImage(Constants.BASE_URL+photoList.get(position).getFullUrl(), photoImageView);
		return convertView;
	}

}
