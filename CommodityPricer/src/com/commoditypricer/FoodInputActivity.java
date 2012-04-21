package com.commoditypricer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FoodInputActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foodinput);
		
		//retreve the commodity units from Intent
		
		//parse the string into JSON Array
		
		//Get access to the Spinner Object
		
		//loop through the JSON Array and create entries in the Spinner"s Adapter
		
		//Set the Spinners Adapter
		
	
	}

	public void onSubmitButtonClicked(View view) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://41.206.34.244:44044/api/commodity");

		EditText eTextFoodName = (EditText) findViewById(R.id.eTextFoodName);
		String foodName = eTextFoodName.getText().toString().trim();
		
		EditText eTextQuantity = (EditText) findViewById(R.id.eTextQuantity);
		String foodQuantity = eTextQuantity.getText().toString();
		
		EditText eTextPrice = (EditText) findViewById(R.id.eTextPrice);
		String foodPrice = eTextPrice.getText().toString();
		
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
        nameValuePairs.add(new BasicNameValuePair("foodName", foodName));  
        nameValuePairs.add(new BasicNameValuePair("foodQuantity", foodQuantity));
        nameValuePairs.add(new BasicNameValuePair("foodPrice", foodPrice));
        nameValuePairs.add(new BasicNameValuePair("Latitude", foodPrice));
        nameValuePairs.add(new BasicNameValuePair("Longitude", foodPrice));
        
        try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
