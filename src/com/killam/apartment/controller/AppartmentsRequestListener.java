/**
 * 
 */
package com.killam.apartment.controller;

/**
 * @author Farhan
 *
 */

public interface AppartmentsRequestListener {

	public void onSuccess(String result,int event);
	public void onFailure();
}

