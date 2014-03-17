package com.killam.apartment.ui;



import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.killam.apartment.R;
import com.killam.apartment.util.Utill;

public class ContactActivity extends BaseActivity {
	
	private TextView call1,call2,call3,call4,call5,call6,call7;
	private TextView email1,email2,email3,email4,email5,email6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		setActionBar(R.string.contact_title, true, false, false, false);
		settingBtn.setVisibility(View.GONE);
		
		call1 = (TextView)findViewById(R.id.call1);
		call1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 0);
			}
		});
		
		call2 = (TextView)findViewById(R.id.call2);
		call2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 0);
			}
		});
		
		/*call3 = (TextView)findViewById(R.id.call3);
		call3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 0);
			}
		});*/
		
		call4 = (TextView)findViewById(R.id.call4);
		call4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 0);
			}
		});
		
		call5 = (TextView)findViewById(R.id.call5);
		call5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 0);
			}
		});
		
		call6 = (TextView)findViewById(R.id.call6);
		call6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 0);
			}
		});
		
		/*call7 = (TextView)findViewById(R.id.call7);
		call7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 0);
			}
		});*/
		

		email1 = (TextView)findViewById(R.id.email1);
		email1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 1);
			}
		});
		

		email2 = (TextView)findViewById(R.id.email2);
		email2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 1);
			}
		});
		

		email3 = (TextView)findViewById(R.id.email3);
		email3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 1);
			}
		});
		
		
		
		email4 = (TextView)findViewById(R.id.email4);
		email4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 1);
			}
		});
		
		email5 = (TextView)findViewById(R.id.email5);
		email5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 1);
			}
		});
		
		email6 = (TextView)findViewById(R.id.email6);
		email6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utill.showKillamAlert(ContactActivity.this, ((TextView)v).getText().toString(), 1);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
