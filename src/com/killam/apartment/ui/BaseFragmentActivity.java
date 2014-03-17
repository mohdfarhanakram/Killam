/**
 * 
 */
package com.killam.apartment.ui;


import java.util.ArrayList;

import com.killam.apartment.R;
import com.killam.apartment.model.detail.Coordinate;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author m.farhan
 *
 */
public abstract class BaseFragmentActivity extends FragmentActivity{

	/**
	 * 
	 */
	
	protected ImageView mapBtn;
	protected ImageView searchBtn;
	protected ImageView shareBtn;
	protected ImageView settingBtn;
	protected ImageView backBtn;

	protected TextView titleView;
	
	ProgressDialog mProgressDialog;

	public BaseFragmentActivity() {
		// TODO Auto-generated constructor stub
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	private void init(){

		titleView = (TextView)findViewById(R.id.titleTxt);

		mapBtn     = (ImageView)findViewById(R.id.mapBtn);
		mapBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				onClickMapButton();
				
				

			}
		});
		searchBtn  = (ImageView)findViewById(R.id.searchBtn);
		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(BaseFragmentActivity.this,SearchActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}
		});
		shareBtn   = (ImageView)findViewById(R.id.shareBtn);
		shareBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openShare();
			}
		});
		settingBtn = (ImageView)findViewById(R.id.settingBtn);
		settingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openContact();
			}
		});

		backBtn = (ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}



	private void setMapButtonEnabled(boolean visibility){
		mapBtn.setVisibility(visibility==true?View.VISIBLE : View.GONE);
	}

	private void setSearchButtonEnabled(boolean visibility){
		searchBtn.setVisibility(visibility==true?View.VISIBLE : View.GONE);
	}

	private void setShareButtonEnabled(boolean visibility){
		shareBtn.setVisibility(visibility==true?View.VISIBLE : View.GONE);
	}

	private void setbackButtonEnabled(boolean visibility){
		shareBtn.setVisibility(visibility==true?View.VISIBLE : View.GONE);
	}

	private void setActivityTitle(String title){
		titleView.setText(title);
	}



	public void setActionBar(String title,boolean isBackButtonVisible,boolean isMapButtonVisible,boolean isSharedButtonVisible,boolean isSearchButtonVisible){
		init();
		setActivityTitle(title);
		setbackButtonEnabled(isBackButtonVisible);
		setMapButtonEnabled(isMapButtonVisible);
		setShareButtonEnabled(isSharedButtonVisible);
		setSearchButtonEnabled(isSearchButtonVisible);

	}
	
	//Open Contact Screen
		private void openContact()
		{

			Intent i = new Intent(BaseFragmentActivity.this, ContactActivity.class);
			startActivity(i);
		}
		//Open Contact Screen
		private void openShare()
		{
			String shareBody = "Hi,Killam Properties is now Mobile";
			// This method will be executed once the timer is over
			// Start your app main activity
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Killam Properties");
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
			startActivity(Intent.createChooser(sharingIntent, "Share via"));
		}
	
	protected void onClickMapButton(){}
	
	
	 public void startSppiner()
		{
	    	//lockScreenOrientation();
	    	startSppiner(null, "", "Loading...", false, null );}

		public void startSppiner(View view, String titleTxt, String bodyText, boolean isCancelable, OnKeyListener keyListener )
		{
			try
			{
				if (null == mProgressDialog)
				{
					mProgressDialog = new ProgressDialog(this);
					mProgressDialog.setCancelable(false);
					mProgressDialog.setMessage("Loading...");
					mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

				}
				if (!mProgressDialog.isShowing()) mProgressDialog.show();

				if( keyListener == null )
				{
					keyListener = new OnKeyListener()
					{
						public boolean onKey(DialogInterface dialog,int keyCode, KeyEvent event)
						{
							if( event.getKeyCode() == KeyEvent.KEYCODE_SEARCH ) { return true; }

							if( event.getKeyCode() == KeyEvent.KEYCODE_BACK )
							{
								stopSppiner();
								
							}
							return false;
						}

					};
				}
				mProgressDialog.setOnKeyListener(keyListener);

			}
			catch (Exception ex)
			{
				Log.e("Base activity start spinner", ex.toString());
			}
		}
		
		public void stopSppiner()
		{
			//unlockScreenOrientation();
			try
			{
				if (null != mProgressDialog) // && mProgressDialog.isShowing())
					mProgressDialog.dismiss();
			}
			catch (Exception ex)
			{
				Log.e("BaseFragment stop spinner: ", ex.toString());
			}
		}
		
	

}
