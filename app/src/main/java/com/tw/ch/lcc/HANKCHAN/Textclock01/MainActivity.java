package com.tw.ch.lcc.HANKCHAN.Textclock01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextClock textClock;
    private Button    btnAlm,btnChr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inview();

    }

    private void inview() {

        textClock = findViewById(R.id.my_tc);
        btnAlm    = findViewById(R.id.btnAlm);
        btnChr    = findViewById(R.id.btnChr);

        textClock.is24HourModeEnabled();

        textClock.getFormat12Hour();

        textClock.getFormat24Hour();

        textClock.getTimeZone();

        btnChr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });
        btnAlm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });

    }



}