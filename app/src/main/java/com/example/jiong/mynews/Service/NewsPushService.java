package com.example.jiong.mynews.Service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.jiong.mynews.Activity.NewsDetailActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Receiver.NewsPushReceiver;
import com.example.jiong.mynews.Utils.Constants;
import com.example.jiong.mynews.domain.NewsContentPagerBean;
import com.example.jiong.mynews.domain.NewsInfomation;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jiong on 2017/3/26.
 */
public class NewsPushService extends Service {
    public NewsPushService() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getDatefromNet();
                /*Log.d("TAG","广播测试");*/
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int time =5*60*60*1000;/*更新周期*/
        Long ReTime = (time + SystemClock.elapsedRealtime());
        Intent i = new Intent(NewsPushService.this, NewsPushReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, ReTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void getDatefromNet() {
        /*xutil 异步加载网络get的方法*/
        String url=Constants.TOP;
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(5000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onFinished() {
                Log.d("ceshi", "onFinished");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("ceshi", cex.getMessage());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("ceshi", ex.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                Log.d("ceshi", result);
                processData(result);
            }
        });

    }
    private void processData(String json) {
        NewsContentPagerBean bean = parsedJson(json);
        /*通过bean 中获取返回的数据   具体的数据填充在这里面执行*/
        String title=bean.getResult().getData().get(0).getTitle();
        String newsurl=bean.getResult().getData().get(0).getUrl();
        /*显示通知*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示的提示
        /*builder.setTicker("简单Notification");*/
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("要闻推送");
        //第二行内容 通常是通知正文
        builder.setContentText(title);
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        /*builder.setSubText("这里显示的是通知第三行内容！");*/
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        /*builder.setNumber(2);*/
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        Intent intent = new Intent(this,NewsDetailActivity.class);
        NewsInfomation newsInfomation =new NewsInfomation();
        newsInfomation.setFrom("notification");
        newsInfomation.setTitle(title);
        newsInfomation.setUrl(newsurl);
        intent.putExtra("newsInfomation",newsInfomation);
        Log.d("TAG",newsInfomation.toString());
        /*intent.putExtra("url",newsurl);
        intent.putExtra("From","notification");
        intent.putExtra("title",title);*/
        PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        Notification notification =builder.build();
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,notification);
    }
    private NewsContentPagerBean parsedJson(String json) {
        /*使用gson解析*/
        return new Gson().fromJson(json, NewsContentPagerBean.class);
    }


}
