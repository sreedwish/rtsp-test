package com.entraze.testrtspplayer;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.entraze.testrtspplayer.databinding.ActivityMainBinding;

import org.videolan.libvlc.Media;

import java.io.IOException;

public class MainActivity3 extends AppCompatActivity implements TextureView.SurfaceTextureListener{


    ActivityMainBinding binding;

    Context context;

    private MediaPlayer mMediaPlayer;

    private TextureView mPreview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        context = this;

        mPreview = binding.texture;


        mPreview.setSurfaceTextureListener(this);


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

                    Uri uri = Uri.parse(url);

                    binding.btnStart.setVisibility(View.GONE);
                    binding.btnStop.setVisibility(View.VISIBLE);

                    try {

                        mMediaPlayer.setDataSource(context,uri);

                        mMediaPlayer.prepare();
                        mMediaPlayer.start();


                    } catch (Exception e) {

                        e.printStackTrace();

                        Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }



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
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {

        Surface s = new Surface(surfaceTexture);


        try {
            mMediaPlayer= new MediaPlayer();
            mMediaPlayer.setSurface(s);



        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
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
