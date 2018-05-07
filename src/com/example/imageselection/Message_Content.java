package com.example.imageselection;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Message_Content extends Activity
{
EditText msg,pas;
Button b;
String key="";
final Context context = this;
private SharedPreferences prefs;
private String prefName = "report";
private SeekBar seekBar;
int comp=0;
private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message__content);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		textView = (TextView) findViewById(R.id.textView1);
		msg=(EditText)findViewById(R.id.editText1);
		b=(Button)findViewById(R.id.button1);
	
		  // Initialize the textview with '0'.
		textView.setText("Covered: " + seekBar.getProgress() + "/" + seekBar.getMax());
		  
		  seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		  int progress = 0;
			  
			  @Override
			  public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
				  progress = progresValue;
				  Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
			  }
			
			  @Override
			  public void onStartTrackingTouch(SeekBar seekBar) {
				  Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
			  }
			
			  @Override
			  public void onStopTrackingTouch(SeekBar seekBar) {
				  textView.setText("Covered: " + progress + "/" + seekBar.getMax());
				  comp=progress;
				  Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
			  }
		   });
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        		final SharedPreferences.Editor editor = prefs.edit();
			
                if(key.length()==0)
                {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
				View promptView = layoutInflater.inflate(R.layout.prompts, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setView(promptView);
				final EditText input = (EditText) promptView.findViewById(R.id.userInput);
                
                alertDialogBuilder.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							 
								key=input.getText().toString();
								 editor.putString("pass", key);
								Toast.makeText(getApplicationContext(), "Key"+key, Toast.LENGTH_LONG).show();
								//editTextMainScreen.setText(input.getText());
							}
						})
						
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int id) {
								dialog.cancel();
							}
						});

		// create an alert dialog
		AlertDialog alertD = alertDialogBuilder.create();

		 alertD.show();
		 key=input.getText().toString();
		
		 editor.commit();
                }
                
                 if((key.length()!=0)&&(!key.isEmpty()))
                {
                	String message=msg.getText().toString();
                    editor.putString("message", message);
                	Log.d("key",key);
                	Log.d("comp",String.valueOf(comp));
                Intent i=new Intent(Message_Content.this,Verify.class);
				Bundle b=new Bundle();
				//b.putString(key, value);
				b.putString("message", message);
				b.putString("key", key);
				
				i.putExtras(b);
                startActivity(i);
                }
				
			}
		});
		
	}
	

	

}
