package com.example.jiong.mynews.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.example.jiong.mynews.Receiver.MyReceiver;
import com.example.jiong.mynews.Utils.CacheUtils;
import com.example.jiong.mynews.Utils.HttpCallBackListener;
import com.example.jiong.mynews.Utils.HttpUtil;
import com.example.jiong.mynews.domain.WeatherDataBean;
import com.google.gson.Gson;

public class MyService extends Service {
    private String weatherCode;
    private String city;
    private String type;
    private String wendu;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String values = CacheUtils.getString(this, "isSelectedCity");
        if (!TextUtils.isEmpty(values)) {
            weatherCode = values;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getWeatherDataFromNet(weatherCode);
                }
            }).start();
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            int time = 3 * 60 * 60 * 1000;/*更新周期*/
            Long ReTime = (time + SystemClock.elapsedRealtime());
            Intent i = new Intent(MyService.this, MyReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, ReTime, pi);
            return super.onStartCommand(intent, flags, startId);
        }
        return 0;
    }

    private void getWeatherDataFromNet(String WeatherCode) {
        String url = "http://wthrcdn.etouch.cn/weather_mini?citykey=" + WeatherCode;
        Log.d("RUU", url);
        HttpUtil.SendHttpRequest(url, new HttpCallBackListener() {
            @Override
            public void OnFinish(String responce) {
                Log.d("RUU", responce);
                progressData(responce);
                CacheUtils.putString(MyService.this, "weatherState", city + "," + wendu + "," + type);
            }

            @Override
            public void OnError(Exception e) {
            }
        });
    }

    private void progressData(String responce) {
        WeatherDataBean bean = parseJson(responce);
        type = bean.getData().getForecast().get(0).getType();
        city = bean.getData().getCity();
        wendu = bean.getData().getWendu();
    }

    private WeatherDataBean parseJson(String s) {
        return new Gson().fromJson(s, WeatherDataBean.class);
    }
}