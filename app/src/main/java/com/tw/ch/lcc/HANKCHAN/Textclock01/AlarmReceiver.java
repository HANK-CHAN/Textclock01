package com.tw.ch.lcc.HANKCHAN.Textclock01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle.get("msg").equals("lccnet")){
            Intent it = new Intent();
            it.setClass(context,MainActivity4.class);
            it.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
            context.startActivity(it);
            Toast.makeText(context,"鬧鐘響瞜~!",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(arg0,"鬧鐘響瞜~!",Toast.LENGTH_SHORT).show();
    }
}
