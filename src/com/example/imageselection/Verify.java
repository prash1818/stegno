package com.example.imageselection;
import java.io.File;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Verify extends Activity 
{
  TextView so,de,mes,pass;
  Button convert;
  private SharedPreferences prefs;
  private String prefName = "report";
	String password="adminadmin";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify);		
        Bundle b=getIntent().getExtras();
		final String message=b.getString("message");
		final String password=b.getString("key");
		Toast.makeText(getApplicationContext(), "Successfully Received"+message+password, Toast.LENGTH_LONG).show();
		convert=(Button)findViewById(R.id.convertion);
		so=(TextView)findViewById(R.id.textView1);
		de=(TextView)findViewById(R.id.textView2);
		mes=(TextView)findViewById(R.id.textView3);
		pass=(TextView)findViewById(R.id.textView4);
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		so.setText(prefs.getString("source", "not available"));
		de.setText(prefs.getString("destination", "not available"));
		
		final int comp=5;
		convert.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v)
			{						
				
				File source=new File(prefs.getString("source", "not available"));
				if(source.exists())
				{
					Toast.makeText(getApplicationContext(), "source available", Toast.LENGTH_LONG).show();
				}
				File destination=new File(prefs.getString("destination", "not available"));
				if(destination.exists())
				{
					Toast.makeText(getApplicationContext(), "destination available", Toast.LENGTH_LONG).show();
				}
				
			  	if(Steganograph.embedMessage(source,destination,message,comp,password))
	             {
			          Toast.makeText(getApplicationContext(), "successfully converted", Toast.LENGTH_LONG).show(); 
			     }
			     else
			     {
			    	   Toast.makeText(getApplicationContext(), "Terminated",Toast.LENGTH_LONG).show();
			     }
				
				Toast.makeText(getApplicationContext(), "Converted", Toast.LENGTH_LONG).show();
				Intent i=new Intent(Verify.this,Mail_Attachment.class);
				startActivity(i);
			}
		});
	
		
		
		
		
	}

}
