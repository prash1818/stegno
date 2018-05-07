
package com.example.imageselection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity1 extends Activity 
{
    ImageView viewImage;
    Button b,ne;
    private SharedPreferences prefs;
	private String prefName = "report";
	File file111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.btnSelectPhoto);
        viewImage=(ImageView)findViewById(R.id.viewImage);
        ne=(Button)findViewById(R.id.but);
        ne.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity1.this,Message_Content.class);
				startActivity(i);
			}
		});
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                selectImage();
            }
        });
    }



      private void selectImage() {
    	  
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity1.this);
        builder.setTitle("Select Image!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  //  prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            	//	SharedPreferences.Editor editor = prefs.edit();
            		
                    File f = new File(Environment.getExternalStorageDirectory()+"/datahidden/", "source.jpg");
                  //  editor.putString("source",f.toString());
                    
                    //	editor.commit();
                    Toast.makeText(getApplicationContext(), "source file"+f.toString(), Toast.LENGTH_LONG).show();
                    Log.d("hello",f.toString());
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
//                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
//                    startActivityForResult(Intent.createChooser(intent, "Select File"),2);

                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
//
//                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                    photoPickerIntent.setType("image/*");
//                    startActivityForResult(photoPickerIntent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
       // boolean success = (new File(Environment.getExternalStorageDirectory()+"/datahidden")).mkdir(); 
        File folder = new File(Environment.getExternalStorageDirectory() + "/datahidden");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure 
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
            	
                File f = new File(Environment.getExternalStorageDirectory()+"/datahidden/");
                Toast.makeText(getApplicationContext(), "Destination"+f, Toast.LENGTH_LONG).show();
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("source.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    // bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
                    viewImage.setImageBitmap(bitmap);

                    String path = Environment.getExternalStorageDirectory()+ "/datahidden/";
                    f.delete();
                    OutputStream outFile = null;
                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            		SharedPreferences.Editor editor = prefs.edit();
                     file111 = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                   Toast.makeText(getApplicationContext(), "File12"+file111.toString(), Toast.LENGTH_LONG).show();
                   editor.putString("source",file111.toString());
                  // File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                   editor.putString("destination",file111.toString());
                   
                 	editor.commit();
                    try {
                        outFile = new FileOutputStream(file111);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        
                        outFile.flush();
                        outFile.close();
                    } 
                    catch (FileNotFoundException e) 
                    {
                        e.printStackTrace();
                    } catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if(file111.exists())
                {
              Toast.makeText(getApplicationContext(), "File Available", Toast.LENGTH_LONG).show();
              BitmapFactory.Options options = new BitmapFactory.Options();
              options.inSampleSize = 2;
              Toast.makeText(getApplicationContext(), "File is :"+file111, Toast.LENGTH_LONG).show();
              Bitmap bm = BitmapFactory.decodeFile(file111.toString(), options);
              viewImage.setImageBitmap(bm); 
              
              /*
               Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());            
               viewImage.setImageBitmap(myBitmap);*/
                	/*
                	 Bitmap bitmap;
                     BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                     bitmap = BitmapFactory.decodeFile(file111.getAbsolutePath(),bitmapOptions);

                     // bitmap = Bitmap.createScaledBitmap(bitmap, 70, 70, true);
                     viewImage.setImageBitmap(bitmap);*/

                }
                else
                {
                	Toast.makeText(getApplicationContext(), "File not Available", Toast.LENGTH_LONG).show();	
                }
                
            } else if (requestCode == 2) {
//                Uri selectedImageUri = data.getData();
//                String p=selectedImageUri.getPath();
//
//                String tempPath = data.getDataString();
//                Log.i("path of image from gallery......******************.........", p);
//                Bitmap bitmap;
//                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                bitmap = BitmapFactory.decodeFile(tempPath, bitmapOptions);
//                viewImage.setImageBitmap(bitmap);
            	 prefs = getSharedPreferences(prefName, MODE_PRIVATE);
         		 SharedPreferences.Editor editor = prefs.edit();
                 Uri selectedImage = data.getData();
                 String[] filePath = { MediaStore.Images.Media.DATA };
                 Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                 c.moveToFirst();
                 int columnIndex = c.getColumnIndex(filePath[0]);
                 String picturePath = c.getString(columnIndex);
                 File file = new File(picturePath, String.valueOf(System.currentTimeMillis()) + ".jpg");
                 Toast.makeText(getApplicationContext(), "File1"+file.toString(), Toast.LENGTH_LONG).show();
                 editor.putString("source from gallery",file.toString());
                 editor.commit();
                 c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath+"");
                viewImage.setImageBitmap(thumbnail);
                                
            }
        }
    }
    
}
