package com.entraze.testrtspplayer;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AsyncPlayer;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCResizeTextureView;

import static android.media.MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING;

public class FragJCVidPlayer extends Fragment implements TextureView.SurfaceTextureListener{

    JCResizeTextureView jcVideoTexture;

    JCMediaManager jcMediaManager;

    Uri media_uri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jcMediaManager = new JCMediaManager();

        media_uri = Uri.parse("rtsp://144.91.79.136:8554/live.sdp");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_frag,container,false);

        jcVideoTexture = (JCResizeTextureView)view.findViewById(R.id.jc_video_player);



        jcVideoTexture.setSurfaceTextureListener(this);



        return view;
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {


        Surface s = new Surface(surfaceTexture);

        jcMediaManager.mediaPlayer.setSurface(s);

        try {
            jcMediaManager.mediaPlayer.setDataSource(getContext(),media_uri);

            jcMediaManager.mediaPlayer.prepareAsync();

            jcMediaManager.mediaPlayer.setVideoScalingMode(VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);


            jcMediaManager.mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {

                    try {
                       jcMediaManager.mediaPlayer.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return true;
                }
            });

            jcMediaManager.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    mediaPlayer.start();

                }
            });


            jcMediaManager.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                    try {
                        jcMediaManager.mediaPlayer.reset();
                        jcMediaManager.mediaPlayer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }




}
