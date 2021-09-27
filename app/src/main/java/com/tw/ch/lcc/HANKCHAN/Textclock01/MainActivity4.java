package com.tw.ch.lcc.HANKCHAN.Textclock01;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity4 extends AppCompatActivity {

    private VideoView mView;
    private ImageView IView;
    private GestureDetector mGesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mView = findViewById(R.id.videoview);
        // 獲取播放文件的絕對路徑
        String url = ("android.resource://" + getPackageName() + "/" + R.raw.videoplayback);
        mView.setVideoURI(Uri.parse(url));
        // 設置播放控制器
        mView.setMediaController(new MediaController(this));
        // 獲取焦點
        mView.requestFocus();
        // 此需要手動點擊控制播放棄播放,自動播放需要添加監聽
        mView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                // 除邊框
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                mView.setLayoutParams(layoutParams);
                mView.start();//準備完播放
            }
        });
    }
    /*
      重寫 onTouchEvent 事件
    */
    public boolean onTouchEvent (MotionEvent  event){
        mGesture.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            IView.setVisibility(View.GONE);
        }
        return super.onTouchEvent(event);
    }
}

