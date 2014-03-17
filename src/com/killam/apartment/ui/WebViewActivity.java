package com.killam.apartment.ui;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;



public class WebViewActivity extends BaseActivity
{
	WebView webView;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_webview);
		
		setActionBar(R.string.app_name, true, false, false, false);

		webView = (WebView) findViewById(R.id.webview);
		
		webView.getSettings().setJavaScriptEnabled(true);
		//webView.getSettings().setPluginsEnabled(true);
		webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+ Constants.PDF_URL);
		//webView.loadUrl(Constants.PDF_URL);

		Log.e("farhan", "url: " + Constants.LOCAL_PDF_URL);
		

	}
}
	
	
	/*private class PsvWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (url.contains(".pdf")) {
                Uri path = Uri.parse(url); 
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                try
                {
                    startActivity(pdfIntent);
                }
                catch(ActivityNotFoundException e)
                {
                    Toast.makeText(WebViewActivity.this, "No PDF application found", Toast.LENGTH_SHORT).show();
                }
                catch(Exception otherException)
                {
                    Toast.makeText(WebViewActivity.this, "Unknown error", Toast.LENGTH_SHORT).show();
                }

            }

            return true;
        }   } }
*/

