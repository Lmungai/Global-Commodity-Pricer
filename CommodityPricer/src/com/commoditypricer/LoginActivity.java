package com.commoditypricer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    
    public void OnLoginButtonClicked(View view) {
    	//call login API
    	
    	startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
}