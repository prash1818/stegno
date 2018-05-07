package com.example.imageselection;


import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Login extends Activity 
{

	LoginDataBaseAdapter loginDataBaseAdapter;
	Button login;
	Button registerr;
	EditText enterusername, enterpassword;

	private SharedPreferences prefs;
	private String prefName = "report";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		login = (Button) findViewById(R.id.login_btn);
		registerr = (Button) findViewById(R.id.register_btn);
		enterusername = (EditText) findViewById(R.id.username_edt);
		enterpassword = (EditText) findViewById(R.id.password_edt);

		loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
		loginDataBaseAdapter.open();

		registerr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(Login.this, Registration.class);
				startActivity(i);

			}
		});

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = enterusername.getText().toString();
				String Password = enterpassword.getText().toString();

				String storedPassword = loginDataBaseAdapter.getSinlgeEntry(
						username, Password);

				if (Password.equals(storedPassword)) {
					prefs = getSharedPreferences(prefName, MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();

					editor.putString("name", username);
					editor.commit();
					Toast.makeText(Login.this, "Congrats: Login Successfully",
							Toast.LENGTH_LONG).show();
					Intent ii = new Intent(Login.this, Index.class);
					startActivity(ii);
				} else if (Password.equals("")) {
					Toast.makeText(Login.this, "Please Enter Your Password",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(Login.this, "Password Incorrect",
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Close The Database
		loginDataBaseAdapter.close();
	}

}
