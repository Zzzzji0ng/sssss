package com.example.jiong.mynews.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jiong.mynews.Service.NewsPushService;

/**
 * Created by Jiong on 2017/3/26.
 */
public class NewsPushReceiver  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2=new Intent(context, NewsPushService.class);
        context.startService(intent2);
    }
}
