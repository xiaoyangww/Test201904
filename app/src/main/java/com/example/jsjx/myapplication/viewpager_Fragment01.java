package com.example.jsjx.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class viewpager_Fragment01 extends Fragment {
    private VideoView videoView;
    private MediaController mediaController;
    int pos;
    boolean isPlay;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.viewpager_fragment01,null);
        videoView=view.findViewById(R.id.videoview1);
        videoView.setVideoURI(Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.videoviewdemo));
        mediaController=new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        videoView.start();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.seekTo(pos);
        if(isPlay==true){
            videoView.start();
        }else{

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        pos=videoView.getCurrentPosition();
        isPlay=videoView.isPlaying();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos",pos);
        outState.putBoolean("isplay",isPlay);
    }

/*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pos=savedInstanceState.getInt("pos");
        isPlay=savedInstanceState.getBoolean("isplay");
    }*/
}
