package com.yuxij.songs;

import java.io.IOException;

import com.yuxij.vocalartist.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.res.AssetFileDescriptor;

public class PlayAudioActivity extends Activity {

	private MediaPlayer mediaPlayer;
	private int playbackPosition=0;
	private String songName;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_audio);

		songName=getIntent().getExtras().getString("songName");
		Button startPlayerBtn = (Button)findViewById(R.id.startAudioBtn);
		Button pausePlayerBtn = (Button)findViewById(R.id.pauseAudioBtn);
		Button restartPlayerBtn = (Button)findViewById(R.id.restartAudioBtn);


		startPlayerBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				try 
				{
					//playAudio(AUDIO_PATH);
					playLocalAudio(songName);
					//playLocalAudio_UsingDescriptor(songNum);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});

		pausePlayerBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if(mediaPlayer!=null)
				{
					playbackPosition = mediaPlayer.getCurrentPosition();
					mediaPlayer.pause();

				}
			}
		});

		restartPlayerBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if(mediaPlayer!=null && !mediaPlayer.isPlaying())
				{
					mediaPlayer.seekTo(playbackPosition);
					mediaPlayer.start();
				}
			}
		});
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		killMediaPlayer();
	}

	private void killMediaPlayer()
	{
		if(mediaPlayer!=null)
		{
			try
			{
				mediaPlayer.release();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	} 	

	private void playLocalAudio(String name)
	{ String songname=name+".mp3";
	try 
	{
		AssetFileDescriptor afd = getAssets().openFd(songname);
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		mediaPlayer.prepare();
		mediaPlayer.start();
	} 
	catch (IllegalArgumentException e) { } 
	catch (IllegalStateException e) { } 
	catch (IOException e) { } 
	}
}