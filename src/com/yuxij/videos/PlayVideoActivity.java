package com.yuxij.videos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.yuxij.vocalartist.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.*;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideoActivity extends Activity {
	VideoView myVideoView;
	private String videoName;
	private String TargetVideoPath = "/sdcard/video1.mp4";
	private String SrcPath;
	File file;
	int length;
	//private static final String tag="error";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);
		myVideoView = (VideoView)findViewById(R.id.videoView);
		videoName=getIntent().getExtras().getString("videoName");
		SrcPath=videoName+".mp4";
		AssetManager am = getAssets();
		InputStream inputStream;
		file = null;
		try {
			inputStream = am.open(SrcPath);
			file = createFileFromInputStream(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		myVideoView.setMediaController(new MediaController(this));
		myVideoView.setVideoPath(TargetVideoPath );
		myVideoView.requestFocus();
		myVideoView.start();

	}

	private File createFileFromInputStream(InputStream inputStream) {

		try{
			File f = new File(this.TargetVideoPath);
			OutputStream outputStream = new FileOutputStream(f);
			byte buffer[] = new byte[1024];
			length = 0;
			while((length=inputStream.read(buffer)) > 0) {
				outputStream.write(buffer);
			}

			outputStream.close();
			inputStream.close();

			return f;
		}catch (IOException e) {
			Log.i("createFile", e.getMessage());
		}

		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_video, menu);
		return true;
	}
}
