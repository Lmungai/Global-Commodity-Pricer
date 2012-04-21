package com.commoditypricer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onCreateFoodItemClicked(View view){
    	startActivity(new Intent(HomeActivity.this, FoodInputActivity.class));
    }
    
    public void onViewFoodPricesButtonClicked(View view){
    	
    }
}
