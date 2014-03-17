/**
 * 
 */
package com.killam.apartment.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.killam.apartment.R;




/**
 * @author Farhan
 *
 */

public class BaseActivity extends Activity {

	/**
	 * 
	 */
	private ImageView mapBtn;
	private ImageView searchBtn;
	private ImageView shareBtn;
	protected ImageView settingBtn;
	private ImageView backBtn;
	
	private TextView titleView;
	//protected Dialog	mProgressDialog;
	ProgressDialog mProgressDialog;

	public BaseActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	private void init(){
		
		titleView = (TextView)findViewById(R.id.titleTxt);

		mapBtn     = (ImageView)findViewById(R.id.mapBtn);
		mapBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startMapActivity();
				
				/*Intent intent = new Intent(BaseActivity.this,MapActivity.class);
				startActivity(intent);*/

			}
		});
		searchBtn  = (ImageView)findViewById(R.id.searchBtn);
		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//finish();
				 Intent i = new Intent(BaseActivity.this, SearchActivity.class);
	             startActivity(i);

			}
		});
		shareBtn   = (ImageView)findViewById(R.id.shareBtn);
		shareBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		settingBtn = (ImageView)findViewById(R.id.settingBtn);
		settingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(BaseActivity.this, ContactActivity.class);
                startActivity(i);

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
		backBtn.setVisibility(visibility==true?View.VISIBLE : View.GONE);
	}
	
	private void setActivityTitle(int title){
		titleView.setText(title);
	}
	
	/**
	 * This metod is used to set the ActionBar buttons and title;
	 * 
	 *
	 */
	
    public void setActionBar(int title,boolean isBackButtonVisible,boolean isMapButtonVisible,boolean isSharedButtonVisible,boolean isSearchButtonVisible){
    	init();
    	setActivityTitle(title);
    	setbackButtonEnabled(isBackButtonVisible);
    	setMapButtonEnabled(isMapButtonVisible);
    	setShareButtonEnabled(isSharedButtonVisible);
    	setSearchButtonEnabled(isSearchButtonVisible);
    	
    }
    
    
    public void startSppiner()
	{
    	lockScreenOrientation();
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

				/*mProgressDialog = new  Dialog(this, R.style.Theme_Dialog2);
				mProgressDialog.setCancelable(isCancelable);
				mProgressDialog.setContentView(R.layout.progress_dialog);
				TextView textView =(TextView)	mProgressDialog.findViewById(R.id.spinMessage);
				textView.setText(bodyText);*/
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
		unlockScreenOrientation();
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
	
	/*@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}*/
	
	private void lockScreenOrientation() {
	    int currentOrientation = getResources().getConfiguration().orientation;
	    if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    } else {
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    }
	}
	 
	private void unlockScreenOrientation() {
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}

	protected void startMapActivity(){}


}
