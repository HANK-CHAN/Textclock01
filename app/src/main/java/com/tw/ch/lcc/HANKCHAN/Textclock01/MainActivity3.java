package com.tw.ch.lcc.HANKCHAN.Textclock01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity3 extends Activity {


        private VideoView mView ;
        private Button    platbtn,stopbtn;
//        private boolean isPlaying;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main3);

        mView = new VideoView(this);
        mView = findViewById(R.id.videoview);
        platbtn = findViewById(R.id.playbtn);
        stopbtn = findViewById(R.id.stopbtn);
        platbtn.setOnClickListener( new mClick());
        stopbtn.setOnClickListener(new mClick());


    }
    class mClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String url = "android.resource://"+getPackageName()+"/"+ R.raw.videoplayback;
            mView.setVideoURI(Uri.parse(url));
            mView.start();
                if(v==platbtn){
                    mView.start();
                }else if(v==stopbtn) {
                    if (mView.isPlaying())
                        mView.resume();
                    mView.stopPlayback();
                }
            mView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(getApplication(), ""+mView.isPlaying(), Toast.LENGTH_SHORT).show();
                    if (mView.isPlaying()){
                        mView.pause();
                    }else{
                        mView.start();
                    }
                    return false;
                }
            });
                // 設定迴圈撥放
                mView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.start();
                        mp.setLooping(true);
                    }
                });
        }

    }

}