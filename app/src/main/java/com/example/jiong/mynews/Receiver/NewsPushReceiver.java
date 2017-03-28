package com.example.jiong.mynews.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.jiong.mynews.Service.NewsPushService;
import com.example.jiong.mynews.Utils.CacheUtils;

/**
 * Created by Jiong on 2017/3/26.
 */
public class NewsPushReceiver  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (CacheUtils.getBoolean(context,"Push_State")){
            Toast.makeText(context,"充电中",Toast.LENGTH_SHORT).show();
            Intent intent2=new Intent(context, NewsPushService.class);
            context.startService(intent2);
        }
    }
}
