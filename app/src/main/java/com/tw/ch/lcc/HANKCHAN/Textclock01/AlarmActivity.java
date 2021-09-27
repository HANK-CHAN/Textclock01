package com.tw.ch.lcc.HANKCHAN.Textclock01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private Button datebtn,mbtn1, mbtn2;
    private TextView mTv,mTv2;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        intview();
    }

    private void intview() {
        calendar = Calendar.getInstance();
        mTv = findViewById(R.id.mTextview);
        mTv2 = findViewById(R.id.mTextview2);
        datebtn=findViewById(R.id.datebtn);
        mbtn1 = findViewById(R.id.mbtn1);
        mbtn2 = findViewById(R.id.mbtn2);



        datebtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth= c.get(Calendar.MONTH);
                int mDay  = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(AlarmActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                       c.set(Calendar.YEAR,year);
                       c.set(Calendar.MONTH,monthOfYear);
                       c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String format = "設定的日期:"+setDateFormat(year,monthOfYear,dayOfMonth);
                        mTv2.setText(format);
                    }
                },mYear,mMonth,mDay).show();

            }
        });

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setTimeInMillis(System.currentTimeMillis());
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                        intent.putExtra("msg","lccnet");
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 1, intent,PendingIntent.FLAG_ONE_SHOT );
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10 * 1000), (24 * 60 * 60 * 1000), pendingIntent);
                        String tmps = "設定鬧鐘時間為" + format(hourOfDay) + ":" + format(minute);
                        mTv.setText(tmps);
                    }
                }, hour, minute, true).show();
            }
        });
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this,AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast
                        (AlarmActivity.this,0,intent,0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
                mTv.setText("鬧鐘已取消");
            }
        });
    }
    private String format(int time){
        String str = ""+time;
        if(str.length()==1){
            str="0"+str;
        }
        return str;
    }
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year)+"-"
                +String.valueOf(monthOfYear+1)
                +"-"+String.valueOf(dayOfMonth);
    }
}





