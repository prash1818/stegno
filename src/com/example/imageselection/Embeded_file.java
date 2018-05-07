package com.example.imageselection;


import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
//import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Embeded_file extends Activity 
{
    /** Called when the activity is first created. */
	private final String LOGTAG = "AndroidFileBrowserExampleActivity";
	private static final int HALF = 2;
	private final int REQUEST_CODE_PICK_DIR = 1;
	private final int REQUEST_CODE_PICK_FILE = 2;
	private final int REQUEST_CODE_PICK_FILEn = 3;
	TextView t1,t2,t3;
	
String s,directory,password="";
int a;
File direct,img,other;
	Button submit;
	final Context context = this;
	//Arbitrary constant to discriminate against values returned to onActivityResult
	// as requestCode
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_embeded_file);
        
        final Activity activityForButton = this;
        t1=(TextView)findViewById(R.id.direct);
        t2=(TextView)findViewById(R.id.imgfile);
        t3=(TextView)findViewById(R.id.otherfile);
        
        submit=(Button)findViewById(R.id.next);
        
        submit.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
			      
				a=9;
				s="1234567890";
			    directory=t1.getText().toString();
				
				String imgfile=t2.getText().toString();
				String otherfile=t3.getText().toString();
				
				 direct=new File(directory);
				 img=new File(imgfile);
				 other=new File(otherfile);
				
				//start
				
/*
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.prompts, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText userInput = (EditText) promptsView.findViewById(R.id.userInput);

				// set dialog message
				alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// get user input and set it to result
										// edit text
										result.setText(userInput.getText());
										
										s="1234567890";
										//start
										if(img.exists())
										{
											Toast.makeText(getApplicationContext(), "image available", Toast.LENGTH_LONG).show();
										}
										//File directory=new File(prefs.getString("direct", "not available"));
										if(direct.exists())
										{
											Toast.makeText(getApplicationContext(), "directory available", Toast.LENGTH_LONG).show();
										}
										if(img.exists())
										{
											Toast.makeText(getApplicationContext(), "image available", Toast.LENGTH_LONG).show();
										}
										//File directory=new File(prefs.getString("direct", "not available"));
										if(direct.exists())
										{
											Toast.makeText(getApplicationContext(), "directory available", Toast.LENGTH_LONG).show();
										}
										
										
										
										if(Steganograph.embedFile(img,direct,other,a,s))
							             {
									        
											//start
											
										         	AlertDialog alertDialog = new AlertDialog.Builder(Embeded_file.this).create();
													alertDialog.setTitle("Alert Dialog");
													alertDialog.setMessage("Welcome to AndroidHive.info");
													alertDialog.setIcon(R.drawable.asd);
													 alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
													 {
													public void onClick(DialogInterface dialog, int which) 
													{
													Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
													}
													});
													
											//end
											Toast.makeText(getApplicationContext(), "successfully converted", Toast.LENGTH_LONG).show(); 
											
											
									     }
									     else
									     {
									    	   Toast.makeText(getApplicationContext(), "Terminated",Toast.LENGTH_LONG).show();
									     }
										
										//end
										
										
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

				
				//end
*/				
				
				if(password.length()>=8)
				{
				
				if(Steganograph.embedFile(img,direct,other,a,password))
	             {
			          Toast.makeText(getApplicationContext(), "successfully converted", Toast.LENGTH_LONG).show(); 
			          Intent i=new Intent(Embeded_file.this,Index.class);
			          startActivity(i);
			     }
			     else
			     {
			    	   Toast.makeText(getApplicationContext(), "Terminated",Toast.LENGTH_LONG).show();
			     }
				
				Toast.makeText(getApplicationContext(), "Converted", Toast.LENGTH_LONG).show();
				
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Enter the Valid password", Toast.LENGTH_LONG).show();
				}
				
			}
		});
        final Button startBrowserButton = (Button) findViewById(R.id.startFileBrowserButtonID);
        
        startBrowserButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.d(LOGTAG, "StartFileBrowser4File button pressed");
    			Intent fileExploreIntent = new Intent(
    					com.example.imageselection.FileBrowserActivity.INTENT_ACTION_SELECT_FILE,
        				null,
        				activityForButton,
        				com.example.imageselection.FileBrowserActivity.class
        				);

        		startActivityForResult(
        				fileExploreIntent,
        				REQUEST_CODE_PICK_DIR
        				);
    		}
    	});
        
        final Button startBrowser4FileButton = (Button) findViewById(R.id.startFileBrowser4FileButtonID);
        startBrowser4FileButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.d(LOGTAG, "StartFileBrowser4File button pressed");
    			Intent fileExploreIntent = new Intent(
    					com.example.imageselection.FileBrowserActivity.INTENT_ACTION_SELECT_FILE,
        				null,
        				activityForButton,
        				com.example.imageselection.FileBrowserActivity.class
        				);

        		startActivityForResult(
        				fileExploreIntent,
        				REQUEST_CODE_PICK_FILE
        				);
    		}
        });
        
        final Button start4FileHideNonReadButton = 
        		(Button) findViewById(R.id.startBrowse4FileHideNonReadButtonID);
        start4FileHideNonReadButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.d(LOGTAG, "StartFileBrowser4File button pressed");
    			Intent fileExploreIntent = new Intent(
    					com.example.imageselection.FileBrowserActivity.INTENT_ACTION_SELECT_FILE,
        				null,
        				activityForButton,   				com.example.imageselection.FileBrowserActivity.class
        				);

        		startActivityForResult(fileExploreIntent,REQUEST_CODE_PICK_FILEn);
    		}
        });
        
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_PICK_DIR) {
        	if(resultCode == RESULT_OK) 
        	{
       /* 		
        String newDir = data.getStringExtra(com.example.imageselection.FileBrowserActivity.returnDirectoryParameter);
        Toast.makeText(this,"Received DIRECTORY path from file browser:\n"+newDir,Toast.LENGTH_LONG).show(); 
       */
        		String newDir = data.getStringExtra(
        				com.example.imageselection.FileBrowserActivity.returnFileParameter);
        		Toast.makeText(this,"Received FILE path from file browser123456:\n"+newDir, Toast.LENGTH_LONG).show(); 
        		//t2.setText(newFile);
       Log.d("FILE : ",newDir);
       Log.e("FILE : ",newDir);
       Log.i("FILE : ",newDir);
       t1.setText(newDir);
            
        	} 
        	else
        	{
        		Toast.makeText(
        				this, 
        				"Received NO result from file browser",
        				Toast.LENGTH_LONG).show(); 
        	}
        }
		
		if (requestCode == REQUEST_CODE_PICK_FILE){
        	if(resultCode == RESULT_OK) {
        		String newFile = data.getStringExtra(
        				com.example.imageselection.FileBrowserActivity.returnFileParameter);
        		Toast.makeText(this,"Received FILE path from file browser123:\n"+newFile, Toast.LENGTH_LONG).show(); 
        		t2.setText(newFile);
        		Bitmap bitmap = BitmapFactory.decodeFile(newFile);
        	    ImageView img=(ImageView)findViewById(R.id.image_holder);
        	    img.setImageBitmap(bitmap);
        		
	        	
        	} 
        	else 
        	{
        		Toast.makeText(this,"Received NO result from file browser",Toast.LENGTH_LONG).show(); 
        	}
        }
		

		if (requestCode == REQUEST_CODE_PICK_FILEn)
		{
        	if(resultCode == RESULT_OK) {
        		String newFile = data.getStringExtra(
        				com.example.imageselection.FileBrowserActivity.returnFileParameter);
        		Toast.makeText(this,"Received FILE path from file browser123:\n"+newFile, Toast.LENGTH_LONG).show(); 
        		t3.setText(newFile);
        		/*Bitmap bitmap = BitmapFactory.decodeFile(newFile);
        	    ImageView img=(ImageView)findViewById(R.id.image_holder);
        	    img.setImageBitmap(bitmap);
        		*/
	        	
        	} 
        	else 
        	{
        		Toast.makeText(this,"Received NO result from file browser",Toast.LENGTH_LONG).show(); 
        	}
        }
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.embeded_file, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.action_settings:
			
			AlertDialog.Builder alert = new AlertDialog.Builder(Embeded_file.this);
			alert.setTitle("Set the Encryption Key");
			
			final EditText edit = new EditText(Embeded_file.this);
			edit.setText("");
			edit.setBackgroundResource(R.drawable.edit_bg);
			edit.setTextColor(Color.BLACK);
			edit.setInputType(InputType.TYPE_CLASS_NUMBER);
			
			alert.setView(edit);
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if (TextUtils.isEmpty(edit.getText().toString())) {
						Toast.makeText(Embeded_file.this, "Given radius is empty and and so radius is set to 1000 metres.", Toast.LENGTH_SHORT).show();
					}
					else {
						 password=edit.getText().toString();
						Toast.makeText(getApplicationContext(), password, Toast.LENGTH_LONG).show();
					}
				}
			});
			alert.show();
			
			break;

		}
		return true;
	}
	
}