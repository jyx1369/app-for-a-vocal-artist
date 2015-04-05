package com.yuxij.wallpapers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.yuxij.vocalartist.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class WallpapersActivity extends Activity {

	private Gallery mGallery;
	private ArrayList<Bitmap> mBitArray;
	private WallpaperManager myWallpaperManager;
	private static ImageView imageView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wallpapers);

		mGallery = (Gallery) findViewById(R.id.Gallery01);

		//load images into memory
		mBitArray = new ArrayList<Bitmap>();
		try
		{
			//these images are stored in the root of "assets"
			mBitArray.add(getBitmapFromAsset("sunyanzi1.jpg"));
			mBitArray.add(getBitmapFromAsset("sunyanzi2.jpg"));
			mBitArray.add(getBitmapFromAsset("sunyanzi3.jpg"));
			mBitArray.add(getBitmapFromAsset("sunyanzi4.jpg"));
			mBitArray.add(getBitmapFromAsset("sunyanzi5.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		mGallery.setAdapter(new GalleryAdapter(this, mBitArray));
		imageView = (ImageView)findViewById(R.id.ImageView01);
		mGallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), 
						"You have selected picture " + (arg2+1) + " of Stefanie Sun As Wallpaper", 
						Toast.LENGTH_SHORT).show();
				imageView.setImageBitmap(mBitArray.get(arg2));
				
				myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
				try {
					myWallpaperManager.setBitmap(mBitArray.get(arg2));;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Helper Functions
	 * @throws IOException 
	 */
	private Bitmap getBitmapFromAsset(String strName) throws IOException
	{
		AssetManager assetManager = getAssets();

		InputStream istr = assetManager.open(strName);
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		istr.close();

		return bitmap;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wallpapers, menu);
		return true;
	}

}
