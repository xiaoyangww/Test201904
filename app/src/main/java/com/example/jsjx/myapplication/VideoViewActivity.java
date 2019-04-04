package com.example.jsjx.myapplication;

import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {
private VideoView videoView;
private MediaController mediaController;
private int pos;
private boolean isplay;
private static final String TAG = "VideoViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        initView();
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videoviewdemo));
        mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        Log.e(TAG, "onCreate: ");
    }

    private void initView() {
      videoView=findViewById(R.id.videoview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        videoView.seekTo(pos);   //指定播放位置
           if(isplay==true){
               videoView.start();
           }else{

           }

    }

    @Override
    protected void onPause() {
        super.onPause();
        pos=videoView.getCurrentPosition();
        isplay=videoView.isPlaying();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: " );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",pos);
        outState.putBoolean("isplay",isplay);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pos=savedInstanceState.getInt("position");
        isplay=savedInstanceState.getBoolean("isplay");
    }

}
