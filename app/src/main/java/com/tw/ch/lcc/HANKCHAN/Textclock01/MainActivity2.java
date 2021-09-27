package com.tw.ch.lcc.HANKCHAN.Textclock01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Chronometer chronometer;
    private Button  btnStar,btnStop,btnbase;//btnformat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

    }
    private void initView(){
        chronometer = findViewById(R.id.chronometer);
        btnStar     = findViewById(R.id.btnStar);
        btnStop     = findViewById(R.id.btnStop);
        btnbase     = findViewById(R.id.btnReset);
      //  btnformat   = findViewById(R.id.btnformat);

        btnStar.setOnClickListener(this::onClick);
        btnStop.setOnClickListener(this::onClick);
        btnbase.setOnClickListener(this::onClick);
       // btnformat.setOnClickListener(this);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                //這裡毫秒轉換時:分:秒
                int h = (int)(time / 3600000);
                int m = (int)(time - h * 3600000)/60000;
                int s = (int)(time - h * 3600000 - m*60000)/1000;
                String hh = h < 10 ? "0"+h: h+"";
                String mm = m < 10 ? "0"+m: m+"";
                String ss = s < 10 ? "0"+s: s+"";
                // 更新文字
                chronometer.setText(hh+":"+ mm +":"+ ss);
            }
        });

//        new Chronometer.OnChronometerTickListener() {
//            @Override
//            public void onChronometerTick(Chronometer chronometer) {
//                if(SystemClock.elapsedRealtime()-chronometer.getBase()>60000){
//                    chronometer.stop();
//                    btnStop.setEnabled(false);
//                    btnStar.setEnabled(true);
//                }
//            }
//        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStar:
//            btnStar.setEnabled(false);
//            btnStop.setEnabled(true);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start(); // 開始
                break;
            case R.id.btnStop:
//                btnStar.setEnabled(true);
//                btnStop.setEnabled(false);
                chronometer.stop(); // 停止
                break;
            default:
            case R.id.btnReset:
                Log.d("resetBtn","onClick:微秒"+SystemClock.elapsedRealtime());
                chronometer.setBase(SystemClock.elapsedRealtime());// 復位
                chronometer.stop();
                //chronometer.setCountDown(false);
                //chronometer.start();
                break;

        }

    }
}