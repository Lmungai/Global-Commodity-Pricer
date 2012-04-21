package com.commoditypricer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String commoditiesResponse;
        
        RestClient client = new RestClient(getString(R.string.commodityunits_url));
        client.addHeader("AcceptHeaders", "application/json");
        
        try {
			client.execute(RequestMethod.GET);
			
			//retrieve commodity units in JSON format
			commoditiesResponse = client.getResponse();
			
			//pass the response as an Intent parameter to  FoodInputActivity
			Log.d("Commodities Response", commoditiesResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void onCreateFoodItemClicked(View view){
    	startActivity(new Intent(HomeActivity.this, FoodInputActivity.class));
    }
    
    public void onViewFoodPricesButtonClicked(View view){
    	
    }
}
