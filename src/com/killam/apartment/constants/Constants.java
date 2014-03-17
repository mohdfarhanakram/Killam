/**
 * 
 */
package com.killam.apartment.constants;


/**
 * @author Farhan
 *
 */
public class Constants {
	
	public static final String DirectoryStructure                   = "appartments";
	public static final String NETWORK_ERROR                        = "Network error occured.";
	public static final String SOME_ERROR                           = "Some error occured.";
	public static final String NO_RESULT_FOUND                      = "No result is found.";
	public static final String GEO_CODE_ERROR                       = "Address is not valid. Please enter valid address.";
	public static final int SEARCH_EVENT                            = 1;
	public static final int VIEW_EVENT                              = 2;


	//public static String[] provinceList = {"Alberta","British Columbia","Manitoba","New Brunswick","Newfoundland and Labrador","Nova Scotia","Nunavut","Ontario","Prince Edward Island","Quebec","Saskatchewan","Northwest Territories","Yukon"};
	public static String[] provinceList                        = {"Any","New Brunswick","Newfoundland","Nova Scotia","Ontario","Prince Edward Island"};
	
	public static String[] provinceNewBrunswickCityList        = {"Any","Dieppe","Fredericton","Miramichi","Moncton","Riverview","Saint John"};
	public static String[] provinceNewfoundlandCityList        = {"Any","Grand Falls Windsor","St. John's"};
	public static String[] provinceNovaScotiaCityList          = {"Any","Bedford","Dartmouth","Halifax","Sydney"};
	public static String[] provinceOntarioCityList             = {"Any","Cambridge","Kanata Ottawa","London","Mississauga","Ottawa","Toronto"};
	public static String[] provincePrinceEdwardIslandCityList  = {"Any","Charlottetown","Stratford","Summerside"};
	public static String[] provinceAnyCityList                 = {"Any"};
	
	public static String BASE_URL       = "http://killamproperties.com/api/v1/";
	public static String SEARCH_URL     =  BASE_URL + "apartment-search/?do-search=%221%22&search.results-limit=%229999%22&search.results-offset=%220%22&";
	public static String BED_ROOM_KEY   = "search.bedroom-number=%22";
	
	public static String VIEW_URL       = BASE_URL + "apartment-view/?do-load=%221%22&";
	
	public static String PDF_URL        ="http://www.killamproperties.com/files/application/rental_application.pdf";
	public static String LOCAL_PDF_URL        = "file:///android_asset/rental_application.pdf";
	
	
	public static final int MAX_RESULT                            = 10;
	
	//Certificate fingerprint (SHA1): DE:D5:D5:03:69:4C:EB:2B:A1:33:1F:4E:9B:E6:CA:0A:DF:33:F4:F6
	
	public static String[] nearMeList                 = {"1 KM","2 KM","5 KM","10 KM"};
	public static Double[] nearMeValues               = {1.00,2.00,5.00,10.00};
	
	public static final int CALL_EVENT  = 0;
	public static final int EMAIL_EVENT = 1;
	
	public static final String REMOVE_STRING = "http://www.killamproperties.com/tours/";
	
}
 