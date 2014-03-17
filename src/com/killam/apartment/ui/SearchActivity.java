/**
 * 
 */
package com.killam.apartment.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.killam.apartment.R;
import com.killam.apartment.adapter.SuggestorAdapter;
import com.killam.apartment.constants.Constants;
import com.killam.apartment.location.GPSTracker;
import com.killam.apartment.location.geocode.Address;
import com.killam.apartment.location.geocode.GeocodeListener;
import com.killam.apartment.location.geocode.GeocodeTask;
import com.killam.apartment.util.Utill;



/**
 * @author Farhan
 *
 */
public class SearchActivity extends BaseActivity implements GeocodeListener{

	/**
	 *  Search Activity
	 */

	private RadioButton rbNearme, rbProvince, rbAddress;
	private Button spProvince, spCity, spNearMe;
	private Button searchButton;
	private SeekBar sbMinBed, sbMaxBed, sbMinPrice, sbMaxPrice;
	private EditText txtMinBedRoom, txtMaxBedRoom, txtMinPrice, txtMaxPrice;
	private AutoCompleteTextView txtAddress;
	private AlertDialog.Builder provinceBuilder;
	private AlertDialog.Builder cityBuilder;
	private AlertDialog.Builder nearMeBuilder;
	private Double selectedNearMe = 0.0;
	double latitude, longitude;

	private String selectedProvince ="";
	
	private String selectedCity ="";
	
	private CharSequence[] cityItems;

	private boolean isValidated = false;
	
	private SuggestorAdapter suggestorAdapter;
	
	private Intent i;
	
	private int mMaxBedRoom = 5;
	private int mMaxPrice = 2000;

	public SearchActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setActionBar(R.string.search_activity_title, false, false, false, false);
		init();

	}
	
	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		getLatitudeLogitude();
	}
	
	

	private void init(){

		//Get Components reference

		searchButton = (Button)findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(isvalidate()){
					
					getLatitudeLogitude();

					i = new Intent(SearchActivity.this, AppartmentListActivity.class);
					//i.putExtra("search.bedroom-number", Integer.parseInt(txtMinBedRoom.getText().toString())==0 ? 1 : Integer.parseInt(txtMinBedRoom.getText().toString()));
					i.putExtra("search.bedroom-number", Integer.parseInt(txtMinBedRoom.getText().toString()));
					if(rbNearme.isChecked())
					{
						i.putExtra("search.loc", 1);
						i.putExtra("search.nearme", selectedNearMe);
						
						navigateToApartmentListScreen();
					}
					else if(rbProvince.isChecked())
					{
						i.putExtra("search.loc", 2);
						if(spProvince.getText().toString().equalsIgnoreCase("Any") || spProvince.getText().toString().equalsIgnoreCase("Province"))
						   i.putExtra("search.province", "");
						else
							i.putExtra("search.province", spProvince.getText());
						if(spCity.getText().toString().equalsIgnoreCase("Any") || spCity.getText().toString().equalsIgnoreCase("City"))
						   i.putExtra("search.city", "");
						else
							i.putExtra("search.city", Utill.getCityCode(spCity.getText().toString()));
						
						navigateToApartmentListScreen();
					}
					else if(rbAddress.isChecked())
					{
						//startSppiner();
						//i.putExtra("search.loc", 1);
						i.putExtra("search.loc", 3);
						i.putExtra("search.address", Utill.urlEncode(txtAddress.getText().toString()));
						navigateToApartmentListScreen();
						/*GeocodeTask geocodeTask = new GeocodeTask(SearchActivity.this);
						geocodeTask.execute(txtAddress.getText().toString());*/
					}else{
						i.putExtra("search.loc", 2);
						i.putExtra("search.province","");
						i.putExtra("search.city", "");
						navigateToApartmentListScreen();
					}
					
					
				}

			}
		});
		
		
		spNearMe  =  (Button) findViewById(R.id.nearMeSpinner);
		spNearMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog alert = nearMeBuilder.create();
				alert.show();

			}
		});

		spProvince      = (Button) findViewById(R.id.provinceSpinner);
		spProvince.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog alert = provinceBuilder.create();
				alert.show();

			}
		});
		spCity          = (Button) findViewById(R.id.citySpinner);
		spCity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog alert = cityBuilder.create();
				alert.show();

			}
		});
		txtAddress      = (AutoCompleteTextView)findViewById(R.id.addressTxt);
		txtMinBedRoom   = (EditText) findViewById(R.id.minBedTxt);
		txtMaxBedRoom   = (EditText) findViewById(R.id.maxBedTxt);
		txtMinPrice     = (EditText) findViewById(R.id.minPriceTXt);
		txtMaxPrice     = (EditText) findViewById(R.id.maxPriceTXT);
		rbNearme        = (RadioButton) findViewById(R.id.nearMeRb);
		rbProvince      = (RadioButton) findViewById(R.id.provinceRb);
		rbAddress       = (RadioButton) findViewById(R.id.addressRb);
		sbMinBed        = (SeekBar)findViewById(R.id.minBedSeekbar);
		sbMaxBed        = (SeekBar)findViewById(R.id.maxBedSeekbar);
		sbMinPrice      = (SeekBar)findViewById(R.id.minPriceSeekbar);
		sbMaxPrice      = (SeekBar)findViewById(R.id.maxPriceSeekbar);

		
		//MinBedRoom SeekBar updating the textbox
		sbMinBed.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onStopTrackingTouch(SeekBar seekBar){}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar){}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser){
				//---change the font size of the EditText---

				txtMinBedRoom.setText(String.valueOf(progress));
				//txtMinPrice.setCursorVisible(false);
			}
		});  

		//MaxBedRoom SeekBar updating the textbox
		sbMaxBed.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onStopTrackingTouch(SeekBar seekBar){}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar){}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser)
			{
				//---change the font size of the EditText---

				txtMaxBedRoom.setText(String.valueOf(progress));
				//txtMinPrice.setCursorVisible(false);
			}
		}); 

		//MinPrice SeekBar updating the textbox
		sbMinPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onStopTrackingTouch(SeekBar seekBar){}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar){}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser)
			{
				//---change the font size of the EditText---

				txtMinPrice.setText(String.valueOf(progress));
				//txtMinPrice.setCursorVisible(false);
			}
		}); 

		//MaxPrice SeekBar updating the textbox
		sbMaxPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onStopTrackingTouch(SeekBar seekBar){}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar){}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser)
			{
				//---change the font size of the EditText---

				txtMaxPrice.setText(String.valueOf(progress));
				//txtMinPrice.setCursorVisible(false);
				
			}
		}); 

		//textbox updating the MinBedRoom SeekBar
		/*txtMinBedRoom.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			@Override
			public void afterTextChanged(Editable s) {
				try{
					//Update Seekbar value after entering a number
					sbMinBed.setProgress(Integer.parseInt(s.toString()));
				} catch(Exception ex) {}
			}
		});*/
		
		txtMinBedRoom.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!((EditText)v).getText().toString().equalsIgnoreCase("")){
					
					
					if(Integer.parseInt(((EditText)v).getText().toString())>mMaxBedRoom){
						sbMinBed.setProgress(mMaxBedRoom);
						txtMinBedRoom.setText(mMaxBedRoom+"");
					}else{
						sbMinBed.setProgress(Integer.parseInt(((EditText)v).getText().toString()));
					}
				}else{
					txtMinBedRoom.setText("0");
					sbMinBed.setProgress(0);
				}
			}
		});

		//textbox updating the MaxBedRoom SeekBar
		/*txtMaxBedRoom.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void afterTextChanged(Editable s) {
				try{
					//Update Seekbar value after entering a number
					sbMaxBed.setProgress(Integer.parseInt(s.toString()));
				} catch(Exception ex) {}
			}
		});*/
		
		txtMaxBedRoom.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!((EditText)v).getText().toString().equalsIgnoreCase("")){
					if(Integer.parseInt(((EditText)v).getText().toString())>mMaxBedRoom){
						sbMaxBed.setProgress(mMaxBedRoom);
						txtMaxBedRoom.setText(mMaxBedRoom+"");
					}else{
						sbMaxBed.setProgress(Integer.parseInt(((EditText)v).getText().toString()));
					}
				}else{
					sbMaxBed.setProgress(mMaxBedRoom);
					txtMaxBedRoom.setText(mMaxBedRoom+"");
				}
			}
		});

		//textbox updating the MinPrice SeekBar
		/*txtMinPrice.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void afterTextChanged(Editable s) {
				try{
					//Update Seekbar value after entering a number
					sbMinPrice.setProgress(Integer.parseInt(s.toString()));
				} catch(Exception ex) {}
			}
		});*/
		
		
		txtMinPrice.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!((EditText)v).getText().toString().equalsIgnoreCase("")){
					if(Integer.parseInt(((EditText)v).getText().toString())>mMaxPrice){
						sbMinPrice.setProgress(mMaxPrice);
						txtMinPrice.setText(mMaxPrice+"");
					}else{
						sbMinPrice.setProgress(Integer.parseInt(((EditText)v).getText().toString()));
						
					}
				}else{
					sbMinPrice.setProgress(0);
					txtMinPrice.setText("0");
				}
			}
		});

		//textbox updating the MaxPrice SeekBar
		/*txtMaxPrice.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void afterTextChanged(Editable s) {
				try{
					//Update Seekbar value after entering a number
					sbMaxPrice.setProgress(Integer.parseInt(s.toString()));
				} catch(Exception ex) {}
			}
		});*/
		
		txtMaxPrice.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!((EditText)v).getText().toString().equalsIgnoreCase("")){
					if(Integer.parseInt(((EditText)v).getText().toString())>mMaxPrice){
						sbMaxPrice.setProgress(mMaxPrice);
						txtMaxPrice.setText(mMaxPrice+"");
					}else{
						sbMaxPrice.setProgress(Integer.parseInt(((EditText)v).getText().toString()));
					}
				}else{
					sbMaxPrice.setProgress(mMaxPrice);
					txtMaxPrice.setText(mMaxPrice+"");
				}
			}
		});



		//Listener for Radio button Near Me
		OnClickListener frbNearMe = new OnClickListener(){
			public void onClick(View v) {
				
				rbAddress.setChecked(false);
				rbProvince.setChecked(false);
				spProvince.setEnabled(false);
				txtAddress.setEnabled(false);
				spNearMe.setEnabled(true);
				txtAddress.setText("");
				spCity.setEnabled(false);
				spCity.setText("City");
				spProvince.setText("Province");
				
				getLatitudeLogitude();
				
			}
		};

		//Listener for Radio button Province
		OnClickListener frbProvince = new OnClickListener(){
			public void onClick(View v) {
				rbAddress.setChecked(false);
				rbNearme.setChecked(false);
				spProvince.setEnabled(true);
				txtAddress.setEnabled(false);
				txtAddress.setText("");
				
				spNearMe.setEnabled(false);
				spNearMe.setText("Near Me");
				selectedNearMe = 0.0;
			}
		};

		//Listener for Radio button Address
		OnClickListener frbAddress = new OnClickListener(){
			public void onClick(View v) {
				rbNearme.setChecked(false);
				rbProvince.setChecked(false);
				txtAddress.setEnabled(true);
				txtAddress.setFocusable(true);
				spProvince.setEnabled(false);
				spCity.setEnabled(false);
				
				spNearMe.setEnabled(false);
				spNearMe.setText("Near Me");
				selectedNearMe = 0.0;
				
				spCity.setText("City");
				spProvince.setText("Province");
				
				
			}
		};

		//Set litner for all radiobutton
		rbNearme.setOnClickListener(frbNearMe);
		rbProvince.setOnClickListener(frbProvince);
		rbAddress.setOnClickListener(frbAddress);
		
		
		final CharSequence[] nearMeItems=new CharSequence[Constants.nearMeList.length];
		for(int index=0;index<Constants.nearMeList.length;index++)
			nearMeItems[index]=Constants.nearMeList[index].toString();

		nearMeBuilder = new AlertDialog.Builder(this);
		nearMeBuilder.setTitle("Near Me");
		nearMeBuilder.setSingleChoiceItems(nearMeItems, -1, new  DialogInterface.OnClickListener(){
			public void  onClick(DialogInterface dialog, int item){

				dialog.dismiss();
				try
				{
					selectedNearMe = Constants.nearMeValues[item];
					spNearMe.setText(Constants.nearMeList[item].toString());

				} catch(Exception e) { Log.e("ChooseNearMeActivity", "" + e); }
			}
		});


		final CharSequence[] items=new CharSequence[Constants.provinceList.length];
		for(int index=0;index<Constants.provinceList.length;index++)
			items[index]=Constants.provinceList[index].toString();

		provinceBuilder = new AlertDialog.Builder(this);
		provinceBuilder.setTitle("Province");
		provinceBuilder.setSingleChoiceItems(items, -1, new  DialogInterface.OnClickListener(){
			public void  onClick(DialogInterface dialog, int item){

				dialog.dismiss();
				try
				{
					selectedProvince = items[item].toString();
					setProvinceValue();

				} catch(Exception e) { Log.e("ChooseCityActivity", "" + e); }
			}
		});


		cityBuilder = new AlertDialog.Builder(this);
		cityBuilder.setTitle("City");
		
		/*
		 * By default near me is checked
		 */
		rbNearme.toggle();
		 
		//setAutoSearchAdapetr();
		
	}

	private void setProvinceValue(){
		
		spCity.setText("City");
		
		spProvince.setText(selectedProvince);
		String province = selectedProvince.trim();
		String[] cityList = Utill.getCityListBasedOnProvince(province);
		
		cityItems=new CharSequence[cityList.length];
		for(int index=0;index<cityList.length;index++)
			cityItems[index]=cityList[index].toString();
		
		spCity.setEnabled(true);
		
		cityBuilder.setSingleChoiceItems(cityItems, -1, new  DialogInterface.OnClickListener(){
			public void  onClick(DialogInterface dialog, int item){

				dialog.dismiss();
				try
				{
					selectedCity = cityItems[item].toString();
					setCityValue();

				} catch(Exception e) { Log.e("ChooseCityActivity", "" + e); }
			}
		});
	}

	private void setCityValue(){
		
		spCity.setText(selectedCity);
	}


	private boolean isvalidate(){

		if(rbNearme.isChecked() && selectedNearMe == 0){
			
			Toast.makeText(getApplicationContext(), "Please select a location" , Toast.LENGTH_LONG).show();
			return false;
			
		}
		
		
		
		if(rbAddress.isChecked() && txtAddress.getText().toString().equalsIgnoreCase("")){
			Toast.makeText(getApplicationContext(), "Please enter the address." , Toast.LENGTH_LONG).show();
			return false;
		}
		
		if((Integer.parseInt(txtMinBedRoom.getText().toString().trim())>Integer.parseInt(txtMaxBedRoom.getText().toString()))|| Integer.parseInt(txtMaxBedRoom.getText().toString())==0)
		{
			
			if(Integer.parseInt(txtMaxBedRoom.getText().toString())==0)
			{
				Toast.makeText(getApplicationContext(), "Maximum bedroom \n can't be zero" , Toast.LENGTH_LONG).show();
				return false;
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Minimum bedroom \n can't be greater than \n maximum bedroom" , Toast.LENGTH_LONG).show();
				return false;
			}

		}
		
		if((Integer.parseInt(txtMinPrice.getText().toString())>Integer.parseInt(txtMaxPrice.getText().toString()))||Integer.parseInt(txtMaxPrice.getText().toString())==0)
		{
			
			if(Integer.parseInt(txtMaxPrice.getText().toString())==0)
			{
				Toast.makeText(getApplicationContext(), "Maximum price \n can't be zero" , Toast.LENGTH_LONG).show();
				return false;
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Minimum price \n can't be greater than \n maximum price" , Toast.LENGTH_LONG).show();
				return false;
			}

		}
		
		return true;
	}
	
	
	
	private void getLatitudeLogitude(){
		/*if(!rbNearme.isChecked()){
			return;
		}*/
		
		GPSTracker gps = new GPSTracker(SearchActivity.this);

        // check if GPS enabled      
        if(gps.canGetLocation())
        {                 
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();                  
            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();  
        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    	
	}
	
	
	private void setAutoSearchAdapetr()
	{
		// TODO Auto-generated method stub

		suggestorAdapter = new SuggestorAdapter(this);
		txtAddress.setAdapter(suggestorAdapter);
		//txtAddress.setHint("Enter address here");

		txtAddress.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

				Address address = (Address) suggestorAdapter.getItem(pos);
				txtAddress.setText(address.getFormattedAddress());
				
			}

		});

	}
	
	private void navigateToApartmentListScreen(){
		i.putExtra("search.geo-lat", latitude);
		i.putExtra("search.geo-lon", longitude);
		i.putExtra("search.price-min", Integer.parseInt(txtMinPrice.getText().toString()));
		i.putExtra("search.price-max", Integer.parseInt(txtMaxPrice.getText().toString()));
		startActivity(i);
	}

	@Override
	public void onGetGeoSuccessResult(Address address) {
		// TODO Auto-generated method stub
		stopSppiner();
		if(address!=null){
			latitude = address.getLatitude();
			longitude = address.getLongitude();
			navigateToApartmentListScreen();
		}else{
			Toast.makeText(this, Constants.GEO_CODE_ERROR,Toast.LENGTH_LONG).show();
		}
		
	}


}
