package com.example.imageselection;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mail_Attachment extends Activity implements OnClickListener
{
	private SharedPreferences prefs;
	private String prefName = "report";
	EditText et_address, et_subject, et_message;
	String address, subject, message, file_path;
	Button bt_send, bt_attach;
	TextView tv_attach;
static String path="";
	private static final int PICK_IMAGE = 100;
	Uri URI = null;
	int columnindex;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail__attachment);
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		path=prefs.getString("source", "not available");
		Log.d("Path",path);
		
		initializeViews();
		bt_send.setOnClickListener(this);
		bt_attach.setOnClickListener(this);

	}

	private void initializeViews() {
		et_address = (EditText) findViewById(R.id.et_address_id);
		et_subject = (EditText) findViewById(R.id.et_subject_id);
		et_message = (EditText) findViewById(R.id.et_message_id);
		bt_send = (Button) findViewById(R.id.bt_send_id);
		bt_attach = (Button) findViewById(R.id.bt_attach_id);
		tv_attach = (TextView) findViewById(R.id.tv_attach_id);
		openGallery();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_attach_id:
			openGallery();
			break;

		case R.id.bt_send_id:
			address = et_address.getText().toString();
			subject = et_subject.getText().toString();
			message = et_message.getText().toString();

			String emailAddresses[] = { address };

			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
					emailAddresses);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
			emailIntent.setType("plain/text");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
			if (URI != null)
				emailIntent.putExtra(Intent.EXTRA_STREAM, URI);

			startActivity(emailIntent);

			break;

		}

	}

	private void openGallery() {
		Intent intent = new Intent();
		intent.setType("/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		 // path = Environment.getExternalStorageDirectory()+ "/datahidden/1419252298772.jpg";
			//file_path = cursor.getString(columnindex);
			// Log.e("Attachment Path:", attachmentFile);
			tv_attach.setText(path);
			URI = Uri.parse("file://" + path);
		//startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {

			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
			columnindex = cursor.getColumnIndex(filePathColumn[0]);
			file_path = cursor.getString(columnindex);
			// Log.e("Attachment Path:", attachmentFile);
			tv_attach.setText(path);
			URI = Uri.parse("file://" + path);
			cursor.close();
		}
	}
}

