package com.example.jiong.mynews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.CacheUtils;
import com.example.jiong.mynews.Utils.HttpCallBackListener;
import com.example.jiong.mynews.Utils.HttpUtil;
import com.example.jiong.mynews.Utils.MynewsApplication;
import com.example.jiong.mynews.domain.WeatherDataBean;
import com.example.jiong.mynews.domain.weather;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WeatherDetailActivity extends Activity implements View.OnClickListener {
    private String wendu;
    private String city;
    private String ganmao;
    private String weatherCode;
    private weather weat;
    private ArrayList<weather> weatherlist;
    /*控件*/
    private ImageButton im_menu;
    private ImageButton changelocationmenu;
    private TextView viewtitle;/*标题*/
    private TextView LocationText;
    private ImageView weatherPic;
    private TextView TodayWendu;
    private TextView TodayTemperature;
    private TextView TodayType;
    private TextView TodayWind;
    private TextView ganmaoTip;
    private TextView dateYesterday;
    private TextView dateTomorrow;
    private TextView date3;
    private TextView date4;
    private TextView date5;
    private ImageView picYest;
    private ImageView picTomorr;
    private ImageView pic3;
    private ImageView pic4;
    private ImageView pic5;
    private TextView typeYester;
    private TextView typeTomorr;
    private TextView type3d;
    private TextView type4d;
    private TextView type5d;
    private TextView temperatureYest;
    private TextView temperatureTorrmor;
    private TextView temperature3;
    private TextView temperature4;
    private TextView temperature5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MynewsApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_weather_detail);
        initView();
        String values = CacheUtils.getString(this, "isSelectedCity");
        if (!TextUtils.isEmpty(values)) {
            weatherCode = values;
        }
        weatherlist = new ArrayList<>();
        getWeatherDataFromNet(weatherCode);
        changelocationmenu.setOnClickListener(this);
    }


    private void getWeatherDataFromNet(String WeatherCode) {
        String url = "http://wthrcdn.etouch.cn/weather_mini?citykey=" + WeatherCode;
        Log.d("RUU", url);
        HttpUtil.SendHttpRequest(url, new HttpCallBackListener() {
            @Override
            public void OnFinish(String responce) {
                Log.d("RUU", responce);
                progressData(responce);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initDate();
                    }
                });
            }

            @Override
            public void OnError(Exception e) {
                Toast.makeText(WeatherDetailActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void progressData(String result) {
        WeatherDataBean bean = parseJson(result);
        ganmao = bean.getData().getGanmao();
        city = bean.getData().getCity();
        wendu = bean.getData().getWendu();
        for (int i = 0; i < 5; i++) {
            weat = new weather();
            weat.setHightemperature(bean.getData().getForecast().get(i).getHigh());
            weat.setLowtemperature(bean.getData().getForecast().get(i).getLow());
            weat.setDate(bean.getData().getForecast().get(i).getDate());
            weat.setWeatherState(bean.getData().getForecast().get(i).getType());
            weat.setWinddirection(bean.getData().getForecast().get(i).getFengxiang());
            weat.setWindpower(bean.getData().getForecast().get(i).getFengli());
            Log.d("UUR", weat.toString());
            weatherlist.add(weat);
            /*排序  第一个为今天的信息  第六个为昨天的信息*/
        }
        weat = new weather();
        weat.setHightemperature(bean.getData().getYesterday().getHigh());
        weat.setLowtemperature(bean.getData().getYesterday().getLow());
        weat.setDate(bean.getData().getYesterday().getDate());
        weat.setWeatherState(bean.getData().getYesterday().getType());
        weat.setWinddirection(bean.getData().getYesterday().getFx());
        weat.setWindpower(bean.getData().getYesterday().getFl());
        weatherlist.add(weat);
        Log.d("RUU", weatherlist.get(5).toString());

    }

    private WeatherDataBean parseJson(String result) {
        return new Gson().fromJson(result, WeatherDataBean.class);
    }

    private void initView() {
        im_menu = (ImageButton) findViewById(R.id.im_menu);
        changelocationmenu = (ImageButton) findViewById(R.id.change_location_menu);
        viewtitle = (TextView) findViewById(R.id.tv_titletext);
        LocationText = (TextView) findViewById(R.id.Location_text);
        weatherPic = (ImageView) findViewById(R.id.weather_pic);
        TodayWendu = (TextView) findViewById(R.id.Today_wendu);
        TodayTemperature = (TextView) findViewById(R.id.Today_temperature);
        TodayType = (TextView) findViewById(R.id.Today_type);
        TodayWind = (TextView) findViewById(R.id.Today_wind);
        ganmaoTip = (TextView) findViewById(R.id.ganmao_tip);
        dateYesterday = (TextView) findViewById(R.id.date_yesterday);
        dateTomorrow = (TextView) findViewById(R.id.date_tomorrow);
        date3 = (TextView) findViewById(R.id.date_3);
        date4 = (TextView) findViewById(R.id.date_4);
        date5 = (TextView) findViewById(R.id.date_5);
        picYest = (ImageView) findViewById(R.id.pic_yest);
        picTomorr = (ImageView) findViewById(R.id.pic_tomorr);
        pic3 = (ImageView) findViewById(R.id.pic_3);
        pic4 = (ImageView) findViewById(R.id.pic_4);
        pic5 = (ImageView) findViewById(R.id.pic_5);
        typeYester = (TextView) findViewById(R.id.type_yester);
        typeTomorr = (TextView) findViewById(R.id.type_tomorr);
        type3d = (TextView) findViewById(R.id.type_3d);
        type4d = (TextView) findViewById(R.id.type_4d);
        type5d = (TextView) findViewById(R.id.type_5d);
        temperatureYest = (TextView) findViewById(R.id.temperature_yest);
        temperatureTorrmor = (TextView) findViewById(R.id.temperature_torrmor);
        temperature3 = (TextView) findViewById(R.id.temperature_3);
        temperature4 = (TextView) findViewById(R.id.temperature_4);
        temperature5 = (TextView) findViewById(R.id.temperature_5);

    }

    private void initDate() {
        CacheUtils.putString(WeatherDetailActivity.this, "weatherState", city + "," + wendu + "," + weatherlist.get(0).getWeatherState());
        /*将今天的天气信息保存，在LeftFragment中直接显示*/
        im_menu.setVisibility(View.GONE);
        changelocationmenu.setVisibility(View.VISIBLE);
        viewtitle.setText("天气");
        LocationText.setText(city);
        TodayWendu.setText(wendu + "℃");
        TodayTemperature.setText(weatherlist.get(0).getHightemperature() + "\n" + weatherlist.get(0).getLowtemperature());
        TodayType.setText(weatherlist.get(0).getWeatherState());
        TodayWind.setText(weatherlist.get(0).getWinddirection());
        ganmaoTip.setText(ganmao);
        dateYesterday.setText(weatherlist.get(5).getDate());
        dateTomorrow.setText(weatherlist.get(1).getDate());
        date3.setText(weatherlist.get(2).getDate());
        date4.setText(weatherlist.get(3).getDate());
        date5.setText(weatherlist.get(4).getDate());
        typeYester.setText(weatherlist.get(5).getWeatherState());
        typeTomorr.setText(weatherlist.get(1).getWeatherState());
        type3d.setText(weatherlist.get(2).getWeatherState());
        type4d.setText(weatherlist.get(3).getWeatherState());
        type5d.setText(weatherlist.get(4).getWeatherState());
        temperatureYest.setText(weatherlist.get(5).getHightemperature() + "\n" + weatherlist.get(5).getLowtemperature());
        temperatureTorrmor.setText(weatherlist.get(1).getHightemperature() + "\n" + weatherlist.get(1).getLowtemperature());
        temperature3.setText(weatherlist.get(2).getHightemperature() + "\n" + weatherlist.get(2).getLowtemperature());
        temperature4.setText(weatherlist.get(2).getHightemperature() + "\n" + weatherlist.get(2).getLowtemperature());
        temperature5.setText(weatherlist.get(3).getHightemperature() + "\n" + weatherlist.get(3).getLowtemperature());
        weatherPic.setImageResource(selectPic(weatherlist.get(0).getWeatherState()));
        picYest.setImageResource(selectPic(weatherlist.get(5).getWeatherState()));
        picTomorr.setImageResource(selectPic(weatherlist.get(1).getWeatherState()));
        pic3.setImageResource(selectPic(weatherlist.get(2).getWeatherState()));
        pic4.setImageResource(selectPic(weatherlist.get(3).getWeatherState()));
        pic5.setImageResource(selectPic(weatherlist.get(4).getWeatherState()));

    }

    private int selectPic(String s) {
        if ("晴".equals(s)) {
            return R.drawable.sunny;
        } else if ("阴".equals(s)) {
            return R.drawable.overcast;
        } else if ("多云".equals(s)) {
            return R.drawable.partlycloudy;
        } else if (s.contains("雷")) {
            return R.drawable.thundershower;
        } else if (s.contains("雨")) {
            return R.drawable.rain;
        } else if (s.contains("霾")) {
            return R.drawable.sandstorm;
        } else if (s.contains("雪")) {
            return R.drawable.snow;
        } else {
            return R.drawable.sunny;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_location_menu:
                Intent intent = new Intent(WeatherDetailActivity.this, CitySelectorActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
