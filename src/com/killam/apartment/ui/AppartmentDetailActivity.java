/**
 * 
 */
package com.killam.apartment.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.model.detail.Building;
import com.killam.apartment.model.detail.Coordinate;
import com.killam.apartment.util.Utill;
import com.viewpagerindicator.TabPageIndicator;





/**
 * @author Farhan
 *
 */


public class AppartmentDetailActivity extends BaseFragmentActivity implements DialogEventListner{
	private static final String[] CONTENT = new String[] { "Overview", "Photos", "Availability", "Feature" };

	
	private Building building;

	private RelativeLayout callButton,emailButton;

    private boolean isFromListScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		isFromListScreen = getIntent().getBooleanExtra("GO_DETAIL_SCREEN_FROM_LIST", false);

		callButton = (RelativeLayout)findViewById(R.id.callButtonLayout);
		callButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//makeCall(building.getContactPhone());
				Utill.showKillamAlert(AppartmentDetailActivity.this, building.getLeasingPhone(), 0);
			}
		});

		emailButton = (RelativeLayout)findViewById(R.id.emailButtonLayout);
		emailButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//sendEmail(building.getContactEmail());
				Utill.showKillamAlert(AppartmentDetailActivity.this, building.getLeasingEmail(), 1);

			}
		});

		String title = "";
		//setActionBar(title,true,true,true,false);
        if(isFromListScreen){
		   building = AppartmentListActivity.GET_BUILDING.getBuilding();
            title = AppartmentListActivity.GET_BUILDING.getBuilding().getPropertyName()!=null ? AppartmentListActivity.GET_BUILDING.getBuilding().getPropertyName() : "";
		
        }else{
           building = ShowMapActivity.GET_BUILDING.getBuilding();
           title = ShowMapActivity.GET_BUILDING.getBuilding().getPropertyName()!=null ? ShowMapActivity.GET_BUILDING.getBuilding().getPropertyName() : "";
        }
        setActionBar(title,true,true,true,false);
		FragmentPagerAdapter adapter = new TabsPageAdapter(getSupportFragmentManager());
		ViewPager pager = (ViewPager)findViewById(R.id.mypager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.myindicator);
		indicator.setViewPager(pager);
	}

	class TabsPageAdapter extends FragmentPagerAdapter {
		public TabsPageAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			switch (position) {
			case 0:
				// Overview fragment activity
				return OverviewFragmentActivity.newInstance(AppartmentDetailActivity.this,building);
			case 1:
				// Photos activity
				return PhotosFragmentActivity.newInstance(AppartmentDetailActivity.this,building);
			case 2:
				// Availability activity
				return AvailabilityFragmentActivity.newInstance(AppartmentDetailActivity.this,building);
			case 3:
				// Featurs activity
				return FeaturesFragmentActivity.newInstance(AppartmentDetailActivity.this,building);
			}

			return TestFragment.newInstance(CONTENT[position % CONTENT.length]);


		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}
	}

	

	
	
	@Override
	protected void onClickMapButton() {
		// TODO Auto-generated method stub
		super.onClickMapButton();
		
		ArrayList<Coordinate> returnList = new ArrayList<Coordinate>();
	    returnList.add(new Coordinate(building.getId(),building.getPropertyName(),String.valueOf(building.getLat()), String.valueOf(building.getLon()),building.getAddressLine1()));

		Intent intent = new Intent(AppartmentDetailActivity.this, ShowMapActivity.class);
		intent.putExtra("COORDINATES", returnList);
		intent.putExtra("IS_FROM_LIST_SCREEN", false);
		startActivity(intent);
	}

	

	/*private void sendEmail(String emailId){

		String emailBody = "Hi,Killam Properties is now Mobile";

		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		 Fill it with Data 
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailId});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Killam Properties");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);

		 Send it off to the Activity-Chooser 
		startActivity(Intent.createChooser(emailIntent, "Send Mail"));



	}
	
	private void makeCall(String phoneNo){
		
		try {
			
			Intent intent = new Intent(Intent.ACTION_CALL);

			intent.setData(Uri.parse("tel:" + phoneNo));
			startActivity(intent);
		   
		} catch (Exception e) {
		    Toast.makeText(getApplicationContext(), "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}*/
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(AppartmentListActivity.GET_BUILDING!=null)
			AppartmentListActivity.GET_BUILDING = null;
		super.onBackPressed();
	}



	@Override
	public void onDialogEvent(int event) {
		// TODO Auto-generated method stub
		
		if(event==Constants.CALL_EVENT){
			Utill.showKillamAlert(AppartmentDetailActivity.this, building.getContactPhone(), 0);
		}else{
			Utill.showKillamAlert(AppartmentDetailActivity.this, building.getContactEmail(), 1);
		}
		
	}
	
	public void openDialog(){
		CommonDialog commonDialog = new CommonDialog();
		commonDialog.setListener(this);
		commonDialog.show(getSupportFragmentManager(), "killam");
	}
	
	


}

