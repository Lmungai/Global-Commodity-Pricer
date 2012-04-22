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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class FoodInputActivity extends Activity {
	Spinner mUnitMeasureSpinner;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foodinput);
		String unitMeasuresJSON = null;
		JSONArray unitMeasureJSONArray = null;

		// retrieve the commodity units from Intent
		unitMeasuresJSON = getIntent().getExtras().getString("UnitMeasures");

		// parse the string into JSON Array
		try {
			// unitMeasureJSONArray = (JSONArray) new
			// JSONTokener(unitMeasuresJSON).nextValue();
			unitMeasureJSONArray = new JSONArray(unitMeasuresJSON);

			if (unitMeasureJSONArray != null) {
				// Get access to the Spinner Object
				mUnitMeasureSpinner = (Spinner) findViewById(R.id.spFoodCategories);

				List<UnitMeasure> measureList = new ArrayList<UnitMeasure>();
				// loop through the JSON Array and create entries in the
				// Spinner"s Adapter
				for (int i = 0; i < unitMeasureJSONArray.length(); i++) {
					JSONObject measureObject = unitMeasureJSONArray.getJSONObject(i);
					UnitMeasure measure = new UnitMeasure(measureObject.getString("ShortForm"), Integer.valueOf(measureObject.getString("UnitMeasureID")));
					measureList.add(measure);
				}
				
				// Set the Spinners Adapter
				ArrayAdapter<UnitMeasure> measuresListAdapter = new ArrayAdapter<UnitMeasure>(FoodInputActivity.this,android.R.layout.simple_spinner_item, measureList);
				measuresListAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				mUnitMeasureSpinner.setAdapter(measuresListAdapter);
				
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		


	}

	public void onSubmitButtonClicked(View view) {
		
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpPost httppost = new HttpPost(
//				"http://41.206.34.244:44044/api/commodity");

		EditText eTextFoodName = (EditText) findViewById(R.id.eTextFoodName);
		String foodName = eTextFoodName.getText().toString().trim();

		EditText eTextQuantity = (EditText) findViewById(R.id.eTextQuantity);
		String foodQuantity = eTextQuantity.getText().toString();

		EditText eTextPrice = (EditText) findViewById(R.id.eTextPrice);
		String foodPrice = eTextPrice.getText().toString();
		
		UnitMeasure selectedUnitMeasure = (UnitMeasure) mUnitMeasureSpinner.getSelectedItem();

		
        RestClient client = new RestClient(getString(R.string.commodity_url));
        client.addParam("CommodityName", foodName);
        client.addParam("CommodityPrice", foodPrice);
        client.addParam("UnitMeasureID", String.valueOf(selectedUnitMeasure.getValue()));
        client.addParam("Latitude", "0");
        client.addParam("Longitude", "0");
        client.addParam("User", "AndroidFarmer");
        
        try {
			client.execute(RequestMethod.POST);
			
			//retrieve commodity units in JSON format
			String response = client.getResponse();
			
			Log.d("Commodities Response", response);
			
			if(response.contains("CommodityID")){
		    	startActivity(new Intent(FoodInputActivity.this, HomeActivity.class));
			}
						

		} catch (Exception e) {
			e.printStackTrace();
		}        
	}

	class UnitMeasure {
		public UnitMeasure(String spinnerText, int value) {
			this.spinnerText = spinnerText;
			this.value = value;
		}

		public String getSpinnerText() {
			return spinnerText;
		}

		public int getValue() {
			return value;
		}

		public String toString() {
			return spinnerText;
		}

		String spinnerText;
		int value;
	}
}
