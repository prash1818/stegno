package com.example.imageselection;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import java.io.File;
import java.io.InputStream;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Retrieve_message extends Activity {
    /** Called when the activity is first created. */
	private final String LOGTAG = "AndroidFileBrowserExampleActivity";
	private static final int HALF = 2;
	private final int REQUEST_CODE_PICK_DIR = 1;
	private final int REQUEST_CODE_PICK_FILE = 2;
	final Context context = this;
	//Arbitrary constant to discriminate against values returned to onActivityResult
	// as requestCode
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_retrieve_message);
        
        final Activity activityForButton = this;
        
      
        final Button startBrowser4FileButton = (Button) findViewById(R.id.startFileBrowser4FileButtonID);
        startBrowser4FileButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			Log.d(LOGTAG, "StartFileBrowser4File button pressed");
    			Intent fileExploreIntent = new Intent(com.example.imageselection.FileBrowserActivity.INTENT_ACTION_SELECT_FILE,
        				null,
        				activityForButton,
        				com.example.imageselection.FileBrowserActivity.class
        				);
//        		fileExploreIntent.putExtra(
//        				ua.com.vassiliev.androidfilebrowser.FileBrowserActivity.startDirectoryParameter, 
//        				"/sdcard"
//        				);
        		startActivityForResult(
        				fileExploreIntent,
        				REQUEST_CODE_PICK_FILE
        				);
    		}//public void onClick(View v) {
        });
        
       
    }//public void onCreate(Bundle savedInstanceState) {
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_PICK_DIR) {
        	if(resultCode == RESULT_OK) {
        		String newDir = data.getStringExtra(
        				com.example.imageselection.FileBrowserActivity.returnDirectoryParameter);
       Toast.makeText(this,"Received DIRECTORY path from file browser:\n"+newDir,Toast.LENGTH_LONG).show(); 
       File imageFile = new File(newDir);
       Bitmap bitmap = BitmapFactory.decodeFile(newDir);
       ImageView img=(ImageView)findViewById(R.id.image_holder);
       img.setImageBitmap(bitmap);
       
      // setContentView(R.layout.main);
       
       InputStream stream = null;
       try {
   		stream = getContentResolver().openInputStream(data.getData());
   		Bitmap original = BitmapFactory.decodeStream(stream);
   		((ImageView)findViewById(R.id.image_holder)).setImageBitmap(Bitmap.createScaledBitmap(original, 
   				original.getWidth()/HALF, original.getHeight()/HALF, true));
   	} 
       catch (Exception e) 
       {
   		e.printStackTrace();
   	   }
       
       
        	} else {//if(resultCode == this.RESULT_OK) {
        		Toast.makeText(
        				this, 
        				"Received NO result from file browser",
        				Toast.LENGTH_LONG).show(); 
        	}//END } else {//if(resultCode == this.RESULT_OK) {
        }//if (requestCode == REQUEST_CODE_PICK_DIR) {
		
		if (requestCode == REQUEST_CODE_PICK_FILE) {
        	if(resultCode == RESULT_OK) {
        		String newFile = data.getStringExtra(
        				com.example.imageselection.FileBrowserActivity.returnFileParameter);
        		Toast.makeText(
        				this, 
        				"Received FILE path from file browser123:\n"+newFile, 
        				Toast.LENGTH_LONG).show(); 
        		File d=new File(newFile);
        		 final SteganoInformation steg= new SteganoInformation(d);
        	        if(steg.isValid())
        	        {
        	           Bitmap bitmap = BitmapFactory.decodeFile(newFile);
             	       ImageView img=(ImageView)findViewById(R.id.image_holder);
             	       img.setImageBitmap(bitmap);
             	       
             	      LayoutInflater layoutInflater = LayoutInflater.from(context);
             	 	View promptView = layoutInflater.inflate(R.layout.prompts, null);

    				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

    				// set prompts.xml to be the layout file of the alertdialog builder
    				alertDialogBuilder.setView(promptView);

    				final EditText input = (EditText) promptView.findViewById(R.id.userInput);

    				// setup a dialog window
    				alertDialogBuilder
    						.setCancelable(false)
    						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    									public void onClick(DialogInterface dialog, int id) {
    										// get user input and set it to result
    										String s=input.getText().toString();
    										String message= Steganograph.retrieveMessage(steg, s);
    									     
    										
    										
    										AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    												context);
    								 
    											// set title
    											alertDialogBuilder.setTitle("Your Secret Message");
    								 
    											// set dialog message
    											alertDialogBuilder
    												.setMessage("Secret Message : "+message)
    												.setCancelable(false)
    												.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    													public void onClick(DialogInterface dialog,int id) {
    														// if this button is clicked, close
    														// current activity
    														Retrieve_message.this.finish();
    													}
    												  })
    												.setNegativeButton("No",new DialogInterface.OnClickListener() {
    													public void onClick(DialogInterface dialog,int id) {
    														// if this button is clicked, just close
    														// the dialog box and do nothing
    														dialog.cancel();
    													}
    												});
    								 
    												// create alert dialog
    												AlertDialog alertDialog = alertDialogBuilder.create();
    								 
    												// show it
    												alertDialog.show();
    										
    										
    										Toast.makeText(getApplicationContext(), message+"  "+s, Toast.LENGTH_LONG).show();
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

             	      
             	      
        	           System.out.println("Data available");
        	        }
        	        else
        	        {
        	            
        	        	System.out.println("Data not available");
        	        }
        		
        		
        		
        		
        		
	        	
        	} else {//if(resultCode == this.RESULT_OK) {
        		Toast.makeText(this, 
        				"Received NO result from file browser",
        				Toast.LENGTH_LONG).show(); 
        	}//END } else {//if(resultCode == this.RESULT_OK) {
        }//if (requestCode == REQUEST_CODE_PICK_FILE) {
		
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
