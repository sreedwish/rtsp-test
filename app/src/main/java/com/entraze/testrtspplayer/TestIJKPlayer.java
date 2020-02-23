package com.entraze.testrtspplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.entraze.testrtspplayer.databinding.ActivityTestIjkplayerBinding;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCResizeTextureView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class TestIJKPlayer extends AppCompatActivity {

    ActivityTestIjkplayerBinding binding;
    JCVideoPlayerStandard jcVideoPlayerStandard;

    String path = "rtsp://144.91.79.136:8554/live.sdp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_ijkplayer);

        jcVideoPlayerStandard = binding.customVideoplayerStandard;

        jcVideoPlayerStandard.setUp(path,JCVideoPlayer.SCREEN_LAYOUT_LIST," ");


        jcVideoPlayerStandard.bottomProgressBar.setVisibility(View.GONE);
        jcVideoPlayerStandard.fullscreenButton.setVisibility(View.GONE);
        jcVideoPlayerStandard.bottomContainer.setVisibility(View.GONE);
        jcVideoPlayerStandard.heightRatio = 2;

        jcVideoPlayerStandard.currentTimeTextView.setVisibility(View.GONE);
        jcVideoPlayerStandard.totalTimeTextView.setVisibility(View.GONE);
        jcVideoPlayerStandard.progressBar.setVisibility(View.GONE);





        Point point = new Point();
        point.set(250,250);


        Log.d("~~", "player height " + jcVideoPlayerStandard.textureViewContainer.getMeasuredHeight());


        JCMediaManager.instance().mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Log.d("~~", "play completed..");

                mediaPlayer.start();

            }
        });

        JCMediaManager.instance().mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {

                mediaPlayer.start();

                return true;
            }
        });



        if (jcVideoPlayerStandard.currentState != JCVideoPlayer.CURRENT_STATE_PLAYING) {
            jcVideoPlayerStandard.startButton.performClick();
            jcVideoPlayerStandard.startButton.setVisibility(View.GONE);
        }


    }


}
