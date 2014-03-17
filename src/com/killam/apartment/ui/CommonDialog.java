/**
 * 
 */
package com.killam.apartment.ui;




import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.killam.apartment.R;
import com.killam.apartment.constants.Constants;

/**
 * @author FARHAN
 *
 */
public class CommonDialog extends DialogFragment{
	
	private RelativeLayout callButton,emailButton;
	private DialogEventListner listener;
	
	public void setListener(DialogEventListner listener){
		this.listener = listener;
	}

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.Theme_Dialog_100);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_dialog, container, false);
        callButton = (RelativeLayout)view.findViewById(R.id.callButtonLayout);
		callButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				listener.onDialogEvent(Constants.CALL_EVENT);
				dismiss();
			}
		});

		emailButton = (RelativeLayout)view.findViewById(R.id.emailButtonLayout);
		emailButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.onDialogEvent(Constants.EMAIL_EVENT);
				dismiss();

			}
		});
        return view;
	}
}
