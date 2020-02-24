package com.entraze.testrtspplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.entraze.testrtspplayer.databinding.ActivityTestIjkfragBinding;

public class TestIJKFrag extends AppCompatActivity {

    ActivityTestIjkfragBinding binding;

    FragJCVidPlayer frag1 = new FragJCVidPlayer(),
            frag2 = new FragJCVidPlayer(),
            frag3 = new FragJCVidPlayer(),
            frag4 = new FragJCVidPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_ijkfrag);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(binding.frm1.getId(),frag1);
        transaction.add(binding.frm2.getId(),frag2);
        transaction.add(binding.frm3.getId(),frag3);
        transaction.add(binding.frm4.getId(),frag4);
        transaction.commit();

        binding.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.frm1.setVisibility(View.VISIBLE);
                binding.frm2.setVisibility(View.VISIBLE);
                binding.frm3.setVisibility(View.VISIBLE);
                binding.frm4.setVisibility(View.VISIBLE);

            }
        });

        binding.btnCam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableAllViews();

                binding.frm1.setVisibility(View.VISIBLE);
            }
        });

        binding.btnCam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                disableAllViews();

                binding.frm2.setVisibility(View.VISIBLE);
            }
        });


        binding.btnCam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                disableAllViews();

                binding.frm3.setVisibility(View.VISIBLE);
            }
        });

        binding.btnCam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                disableAllViews();

                binding.frm4.setVisibility(View.VISIBLE);
            }
        });

    }

    private void disableAllViews(){
        binding.frm1.setVisibility(View.GONE);
        binding.frm2.setVisibility(View.GONE);
        binding.frm3.setVisibility(View.GONE);
        binding.frm4.setVisibility(View.GONE);
    }

    private class CameraTask extends AsyncTask<String,Void,Boolean> {


        @Override
        protected Boolean doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }


    }

}
