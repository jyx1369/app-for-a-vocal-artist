package com.yuxij.songs;

import com.yuxij.vocalartist.R;
import android.os.Bundle;
import android.app.*;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class SongsActivity extends Activity {
	ListView listView ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_songs);

		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.songsList);

		// Defined Array values to show in ListView
		String[] values = new String[] { "Hey Jude", 
				"Peace" 
		};

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);


		// Assign adapter to ListView
		listView.setAdapter(adapter); 

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				// int itemPosition = position;
				String  itemValue=(String) listView.getItemAtPosition(position);
				Intent intent=new Intent(SongsActivity.this,PlayAudioActivity.class);
				intent.putExtra("songName", itemValue);
				startActivity(intent);
			}
		}); 
	}
}
