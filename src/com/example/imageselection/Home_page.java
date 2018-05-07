package com.example.imageselection;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;


public class Home_page extends Activity {
	// Splash screen timer
		private static int SPLASH_TIME_OUT = 2000;

		
		@SuppressLint("NewApi")
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_home_page);
	       
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() 
				{			
					Intent i = new Intent(Home_page.this, Index.class);
					startActivity(i);
					finish();
				}
			}, SPLASH_TIME_OUT);
		}
}
