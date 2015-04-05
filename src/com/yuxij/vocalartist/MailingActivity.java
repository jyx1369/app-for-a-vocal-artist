package com.yuxij.vocalartist;

import fansDB.FanInfo;
import fansDB.fanInfoSQLiteHelper;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MailingActivity extends Activity {
	Button addButton; 
	TextView txtTo; 
	EditText name; 
	EditText email; 
	fanInfoSQLiteHelper db=new fanInfoSQLiteHelper(this);
	String fanName;
	String fanEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mailing);
	
		addButton = (Button) findViewById(R.id.userButton); 
//		String email=getIntent().getExtras().getString("singerEmail");
		name = (EditText) findViewById(R.id.editName); 
		email = (EditText) findViewById(R.id.EditEmail); 
		addButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				fanName=name.getText().toString();
				fanEmail=email.getText().toString();
				if (fanName == null)
				{ 
					Toast.makeText(getApplicationContext(), 
							"You forgot to enter your name", 
							Toast.LENGTH_SHORT).show();
				}
				else if ( fanName!= null  && !isEmailValid(fanEmail))
				{ 
					Toast.makeText(getApplicationContext(), 
			               "Entered email address is not Valid",
			               Toast.LENGTH_SHORT).show(); 
				} 
				else 
				{
					Toast.makeText(getApplicationContext(), 
				               "Your info has added to the mailing list!",
				               Toast.LENGTH_SHORT).show(); 
					
				db.addFan(new FanInfo(fanName,fanEmail));
				db.getAllFans();
				sendEmail();
				}
			}
		});	
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mailing, menu);
		return true;
	}
	
	public boolean isEmailValid(CharSequence email){ 
		
	return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); 
	
	} 
	
	protected void sendEmail() {
	      Log.i("Send email", "");

	      String[] TO = {""};
	      String[] CC = {""};
	      String myemailAD="Please add my email: "+email.getText().toString();
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/plain");


	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	      emailIntent.putExtra(Intent.EXTRA_CC, CC);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Add Me On Mail List");
	      emailIntent.putExtra(Intent.EXTRA_TEXT, myemailAD);

	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         finish();
	         Log.i("Finished sending email...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(MailingActivity.this, 
	         "There is no email client installed.", Toast.LENGTH_SHORT).show();
	      }
	   }

}
