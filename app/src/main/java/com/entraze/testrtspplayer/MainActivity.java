package com.entraze.testrtspplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.entraze.testrtspplayer.databinding.ActivityMainBinding;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.DisplayManager;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    Context context;



    private static final boolean USE_TEXTURE_VIEW = false;
    private static final boolean ENABLE_SUBTITLES = true;

    private VLCVideoLayout mVideoLayout = null;

    private LibVLC mLibVLC = null;
    private MediaPlayer mMediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        context = this;

        final ArrayList<String> args = new ArrayList<>();
        args.add("-vvv");
        mLibVLC = new LibVLC(this, args);
        mMediaPlayer = new MediaPlayer(mLibVLC);

        mVideoLayout = binding.videoView;


        mMediaPlayer.attachViews(mVideoLayout, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);

        mMediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_FILL);



        binding.btnStop.setVisibility(View.GONE);

        binding.edtInput.setText("rtsp://144.91.79.136:8554/live.sdp");

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String url = binding.edtInput.getText().toString().trim();

                 url = url.replaceAll(" ","");

                if (TextUtils.isEmpty(url)){

                    Toast.makeText(context, "Enter a valid video source", Toast.LENGTH_SHORT).show();

                }else {

                    binding.btnStart.setVisibility(View.GONE);
                    binding.btnStop.setVisibility(View.VISIBLE);

                    try {

                        Uri uri = Uri.parse(url);

                        final Media media = new Media(mLibVLC,uri);

                        mMediaPlayer.setMedia(media);


                        media.release();

                    } catch (Exception e) {

                        Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                    mMediaPlayer.play();


                }


            }
        });




        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.btnStop.setVisibility(View.GONE);

                binding.btnStart.setVisibility(View.VISIBLE);

                mMediaPlayer.stop();
               // mMediaPlayer.detachViews();


            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mLibVLC.release();
    }


}
