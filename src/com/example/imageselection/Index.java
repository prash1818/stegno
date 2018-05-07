package com.example.imageselection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Index extends Activity
{
	Button em,ef,rm,rf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		em=(Button)findViewById(R.id.embedmessage);
		ef=(Button)findViewById(R.id.embedfile);
		rm=(Button)findViewById(R.id.retrievemessage);
		rf=(Button)findViewById(R.id.retrievefile);
		
		em.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) 
			{
				
			Intent i=new Intent(Index.this,MainActivity1.class);
			startActivity(i);
							
			}
		});
		ef.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{		
				Toast.makeText(getApplicationContext(), "Embed file not available", Toast.LENGTH_LONG).show();
				Intent i=new Intent(Index.this,Embeded_file.class);
				startActivity(i);
			}
			
		});
		
        rm.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) 
			{		
				
				Intent i=new Intent(Index.this,Retrieve_message.class);
				startActivity(i);
				
			}
			
		});
        rf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{		
				Intent i=new Intent(Index.this,Retrieve_file.class);
				startActivity(i);
				Toast.makeText(getApplicationContext(), "Retrieve file not available", Toast.LENGTH_LONG).show();
			}
			
			
		});
		
	}
}
