package com.yuxij.vocalartist;

import java.io.IOException;
import java.io.InputStream;

import com.yuxij.songs.SongsActivity;
import com.yuxij.videos.VideosActivity;
import com.yuxij.wallpapers.WallpapersActivity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainPageActivity extends Activity {
TextView link;
TextView socialLink;
TextView email;
Button clickToMail;
ImageView mainImage;
private static final String imageName = "sunyanzi.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		
		mainImage=(ImageView)findViewById(R.id.MainImage);
		loadImageFromAsset();
		
		link=(TextView)findViewById(R.id.websiteLink);
		link.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		link.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent browseIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.inmusicgroup.com/concert/yanzi.php"));
				startActivity(browseIntent);
			}
			
		});
		socialLink=(TextView)findViewById(R.id.socialLink);
		socialLink.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		socialLink.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent browseIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.weibo.com/sunyanzi"));
				startActivity(browseIntent);
			}
			
		});
		clickToMail=(Button)findViewById(R.id.mailingButton);
		email=(TextView)findViewById(R.id.email);
		clickToMail.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent mailIntent=new Intent(MainPageActivity.this,MailingActivity.class);
//				mailIntent.putExtra("singerEmail", email.getText().toString());
				startActivity(mailIntent);
			}
			
		});
	
		
	}

	private void loadImageFromAsset(){
		try{
			InputStream is = getAssets().open(imageName);
			Drawable d = Drawable.createFromStream(is, null);
			mainImage.setImageDrawable(d);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()) {
        case R.id.Songs: 
            Intent intent1 = new Intent(MainPageActivity.this,SongsActivity.class);
            startActivity(intent1);
            break;
        case R.id.Videos:
        	Intent intent2 = new Intent(MainPageActivity.this,VideosActivity.class);
            startActivity(intent2);
            break; 
        case R.id.Wallpapers:
        	Intent intent3 = new Intent(MainPageActivity.this,WallpapersActivity.class);
            startActivity(intent3);
        	break;
        }
        return super.onOptionsItemSelected(item);
	
	}

}
