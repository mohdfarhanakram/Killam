/**
 * 
 */
package com.killam.apartment.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.killam.apartment.constants.Constants;

/**
 * @author Farhan
 *
 */
public class Utill {

	/**
	 * 
	 */
	public Utill() {
		// TODO Auto-generated constructor stub
	}

	public static String getProvinceCode(String province){

		province = province.trim();


		if(province.equalsIgnoreCase("New Brunswick"))
		{
			return "NB";
		}
		else if(province.equalsIgnoreCase("Newfoundland"))
		{
			return "NL";
		}
		else if(province.equalsIgnoreCase("Nova Scotia"))
		{
			return "NS";
		}

		else if(province.equalsIgnoreCase("Ontario"))
		{
			return "ON";
		}
		else if(province.equalsIgnoreCase("Prince Edward Island"))
		{
			return "PE";
		}

		else
		{
			return "";
		}

	}


	public static String[] getCityListBasedOnProvince(String province){

		province = province.trim();


		if(province.equalsIgnoreCase("New Brunswick"))
		{
			return Constants.provinceNewBrunswickCityList;
		}
		else if(province.equalsIgnoreCase("Newfoundland"))
		{
			return Constants.provinceNewfoundlandCityList;
		}
		else if(province.equalsIgnoreCase("Nova Scotia"))
		{
			return Constants.provinceNovaScotiaCityList;
		}

		else if(province.equalsIgnoreCase("Ontario"))
		{
			return Constants.provinceOntarioCityList;
		}
		else if(province.equalsIgnoreCase("Prince Edward Island"))
		{
			return Constants.provincePrinceEdwardIslandCityList;
		}

		else
		{
			return Constants.provinceAnyCityList;
		}

	}
	
	
	public static String getCityCode(String city){

		city = city.trim();

		return urlEncode(city);

		/*if(city.equalsIgnoreCase("Saint John"))
		{
			return "Saint"+"%20"+"John";
		}
		else if(city.equalsIgnoreCase("Grand Falls Windsor"))
		{
			return "Grand"+"%20"+"Falls"+"%20"+"-"+"%20"+"Windsor";
		}
		else if(city.equalsIgnoreCase("St. John's"))
		{
			
			return "St."+"%20"+"John's";
		}

		else if(city.equalsIgnoreCase("Kanata Ottawa"))
		{
			return "Kanata"+"%20"+"Ottawa";
		}
		else
		{
			return city;
		}*/

	}
	
	
	
	/*public static Float distance(double lat1, double lng1, double lat2, double lng2) {
		
		Log.e("First latitude", lat1+"");
		Log.e("First longitude", lng1+"");
		Log.e("Second latitude", lat2+"");
		Log.e("Second longitude", lng2+"");
	    double earthRadius = 3958.75;
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    double meterConversion = 1.609;
	    
	    double km=valueResult/1;
    DecimalFormat newFormat = new DecimalFormat("####");
    kmInDec =  Integer.valueOf(newFormat.format(km));
    meter=valueResult%1000;
    meterInDec= Integer.valueOf(newFormat.format(meter));
    Log.i("Radius Value",""+valueResult+"   KM  "+kmInDec+" Meter   "+meterInDec);

    return Radius * c;

	    return new Float(dist * meterConversion).floatValue();
	   }*/

	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344; // to get dis in KM
		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public static String getMonth(String dateInString){

		String returnString = dateInString;

		try
		{
			SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd");
			String month = fDate.parse(dateInString).getMonth()+1+"";
			Log.e("FARHAN...", "FARHAN " +month);
			if(month.equalsIgnoreCase("01") || month.equalsIgnoreCase("1"))
				returnString = "January";
			else if(month.equalsIgnoreCase("02") || month.equalsIgnoreCase("2"))
				returnString = "February";
			else if(month.equalsIgnoreCase("03") || month.equalsIgnoreCase("3"))
				returnString = "March";
			else if(month.equalsIgnoreCase("04") || month.equalsIgnoreCase("4"))
				returnString = "April";
			else if(month.equalsIgnoreCase("05") || month.equalsIgnoreCase("5"))
				returnString = "May";
			else if(month.equalsIgnoreCase("06") || month.equalsIgnoreCase("6"))
				returnString = "June";
			else if(month.equalsIgnoreCase("07") || month.equalsIgnoreCase("7"))
				returnString = "July";
			else if(month.equalsIgnoreCase("08") || month.equalsIgnoreCase("8"))
				returnString = "August";
			else if(month.equalsIgnoreCase("09") || month.equalsIgnoreCase("9"))
				returnString = "September";
			else if(month.equalsIgnoreCase("10"))
				returnString = "October";
			else if(month.equalsIgnoreCase("11"))
				returnString = "November";
			else if(month.equalsIgnoreCase("12") )
				returnString = "December";
			else
				return returnString;
			

		}catch(Exception ex){

		}

		return returnString;
	}
	
	
	/**
     * Function to show settings alert dialog
     * 
     * */
    public static void showKillamAlert(final Context mContext, final String value, final int type){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        
        String title = ""; 
        String msg = "";
        if(type == 0){
        	title= "Call" ;
        	msg  = "Do you want to make a call at "+value+"?";
        }else{ 
        	title= "Email" ;
        	msg  = "Do you want to send mail at "+value+"?";
        }
       
        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(msg);

        // On pressing Email/Call button
        alertDialog.setPositiveButton(title, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	
            	if(type == 0){
            		makeCall(mContext,value);
                }else{ 
                	sendEmail(mContext,value);
                }
                
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    
    
    private static void sendEmail(Context mContext,String emailId){

		//String emailBody = "Hi,Killam Properties is now Mobile";
    	String emailBody = "";

		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		/* Fill it with Data */
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailId});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Killam Properties");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);

		/* Send it off to the Activity-Chooser */
		mContext.startActivity(Intent.createChooser(emailIntent, "Send Mail"));



	}
	
	private static void makeCall(Context mContext,String phoneNo){
		
		try {
			
			Intent intent = new Intent(Intent.ACTION_CALL);

			intent.setData(Uri.parse("tel:" + phoneNo));
			mContext.startActivity(intent);
		   
		} catch (Exception e) {
		    Toast.makeText(mContext, "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	
	public static int dpToPx(int dp)
	{
	    return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
	}
	
	public static int pxToDp(int px)
	{
	    return (int) (px / Resources.getSystem().getDisplayMetrics().density);
	}

	public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
        	//return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("failed to encode", e);
        }
    }


}
