package com.example.jiong.mynews.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jiong.mynews.Service.MyService;

/**
 * Created by Jiong on 2017/3/4.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context, MyService.class);
        context.startService(intent1);
    }
}
