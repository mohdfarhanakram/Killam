/**
 * 
 */
package com.killam.apartment.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.killam.apartment.network.HTTPPoster;



/**
 * @author Farhan
 *
 */


public class AppartmentsRequestHandler extends AsyncTask<Void, Void, Void> {

	private AppartmentsRequestListener listener;
	private Context context;
	private String url;
	private String result="";
	
	private int eventType;
	
	/**
	 * Construct a new {@link AppartmentsRequestHandler}.
	 * 
	 * @param context
	 * @param listener
	 */
	public AppartmentsRequestHandler(Context context, AppartmentsRequestListener listener,String url,int eventType) {
		this.listener = listener;
		//this.context = context;
		this.eventType = eventType;
		this.url = url;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			
			result = HTTPPoster.doGet(url);
			Log.e("Farhan", result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			result = "error";
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = "error";
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			result = "error";
			e.printStackTrace();
		}

			
		return null;
	}

	@Override
	protected void onPostExecute(Void res) {
		
		if(result.equalsIgnoreCase("error")){
			listener.onFailure();
		}else{
			listener.onSuccess(result,eventType);
		}
		
		
		super.onPostExecute(res);
	}

}

