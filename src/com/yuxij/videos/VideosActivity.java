package com.yuxij.videos;

import com.yuxij.vocalartist.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class VideosActivity extends Activity {
	ListView listView1 ;
    //private String tag="error";
    
    private static String[] values = new String[] { "wharf", 
            "on_board" 
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videos);

		// Get ListView object from xml
		listView1 = (ListView) findViewById(R.id.videoList);

		// Defined Array values to show in ListView
		

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
  
		// Assign adapter to ListView

		listView1.setAdapter(adapter1); 
		
		// ListView Item Click Listener
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				// int itemPosition = position;
				String  itemValue=(String) listView1.getItemAtPosition(position);
				Intent intent=new Intent(VideosActivity.this,PlayVideoActivity.class);
				intent.putExtra("videoName", itemValue);
				startActivity(intent);
			}
		}); 
	}
}
