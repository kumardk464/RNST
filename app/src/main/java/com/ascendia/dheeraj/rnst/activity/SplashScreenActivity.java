package com.ascendia.dheeraj.rnst.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.ascendia.dheeraj.rnst.R;

public class SplashScreenActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rnst_splash_screen);
		
		
		  new Handler().postDelayed(new Runnable() {
              
	            // Using handler with postDelayed called runnable run method
	  
	            @Override
	            public void run() {
	                Intent i = new Intent(SplashScreenActivity.this, LoginPage.class);
	                startActivity(i);
	  
	                // close this activity
	                finish();
	            }
	        }, 5*1000); // wait for 5 seconds
	        
	    }
	     
	    @Override
	    protected void onDestroy() {
	         
	        super.onDestroy();
	         
	    }
			

		
		
		
		
	}


