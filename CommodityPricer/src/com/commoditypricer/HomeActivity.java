package com.commoditypricer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HomeActivity extends Activity {
    String mResponse = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        
        RestClient client = new RestClient(getString(R.string.commodityunits_url));
        client.addHeader("AcceptHeaders", "application/json");
        
        try {
			client.execute(RequestMethod.GET);
			
			//retrieve commodity units in JSON format
			mResponse = client.getResponse();
						
			//pass the response as an Intent parameter to  FoodInputActivity
			Log.d("Commodities Response", mResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void onCreateFoodItemClicked(View view){
    	
		if (mResponse != null) {
			Intent commodityIntent = new Intent(HomeActivity.this, FoodInputActivity.class);
			commodityIntent.putExtra("UnitMeasures", mResponse);
	    	startActivity(commodityIntent);			
		}		
    }
    
    public void onViewFoodPricesButtonClicked(View view){
    	
    }
}
