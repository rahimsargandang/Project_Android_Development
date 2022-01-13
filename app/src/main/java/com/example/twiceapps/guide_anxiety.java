package com.example.twiceapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class guide_anxiety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_anxiety);
        VideoView videoView = findViewById(R.id.vid_stress);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.anxiety);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }
}