package com.entraze.testrtspplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.entraze.testrtspplayer.databinding.ActivityMain2Binding;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ActivityMain2Binding binding;

    Context context;



    private static final boolean USE_TEXTURE_VIEW = false;
    private static final boolean ENABLE_SUBTITLES = true;

    private VLCVideoLayout mVideoLayout1 = null;
    private VLCVideoLayout mVideoLayout2 = null;
    private VLCVideoLayout mVideoLayout3 = null;
    private VLCVideoLayout mVideoLayout4 = null;

    private LibVLC mLibVLC = null;
    private MediaPlayer mMediaPlayer1 = null;
    private MediaPlayer mMediaPlayer2 = null;
    private MediaPlayer mMediaPlayer3 = null;
    private MediaPlayer mMediaPlayer4 = null;

    String url = "rtsp://144.91.79.136:8554/live.sdp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);

        context = this;

        final ArrayList<String> args = new ArrayList<>();
        args.add("-vvv");
        mLibVLC = new LibVLC(this, args);
        mMediaPlayer1 = new MediaPlayer(mLibVLC);
        mMediaPlayer2 = new MediaPlayer(mLibVLC);
        mMediaPlayer3 = new MediaPlayer(mLibVLC);
        mMediaPlayer4 = new MediaPlayer(mLibVLC);


        mVideoLayout1 = binding.videoView1;
        mVideoLayout2 = binding.videoView2;
        mVideoLayout3 = binding.videoView3;
        mVideoLayout4 = binding.videoView4;


        mMediaPlayer1.attachViews(mVideoLayout1, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);
        mMediaPlayer2.attachViews(mVideoLayout2, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);
        mMediaPlayer3.attachViews(mVideoLayout3, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);
        mMediaPlayer4.attachViews(mVideoLayout4, null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);


        mMediaPlayer1.setVideoScale(MediaPlayer.ScaleType.SURFACE_FILL);
        mMediaPlayer2.setVideoScale(MediaPlayer.ScaleType.SURFACE_FILL);
        mMediaPlayer3.setVideoScale(MediaPlayer.ScaleType.SURFACE_FILL);
        mMediaPlayer4.setVideoScale(MediaPlayer.ScaleType.SURFACE_FILL);




        try {


            Uri uri = Uri.parse(url);

            final Media media = new Media(mLibVLC,uri);

            mMediaPlayer1.setMedia(media);

            media.release();

            mMediaPlayer1.play();


        } catch (Exception e) {

            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

        }


        try {


            Uri uri = Uri.parse(url);

            final Media media = new Media(mLibVLC,uri);

            mMediaPlayer2.setMedia(media);

            media.release();

            mMediaPlayer2.play();


        } catch (Exception e) {

            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

        }


        try {


            Uri uri = Uri.parse(url);

            final Media media = new Media(mLibVLC,uri);

            mMediaPlayer3.setMedia(media);

            media.release();

            mMediaPlayer3.play();


        } catch (Exception e) {

            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

        }


        try {


            Uri uri = Uri.parse(url);

            final Media media = new Media(mLibVLC,uri);

            mMediaPlayer4.setMedia(media);

            media.release();

            mMediaPlayer4.play();


        } catch (Exception e) {

            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

        }






    }
}
