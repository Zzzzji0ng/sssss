package com.example.jiong.mynews.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiong.mynews.DatabBase.WeatherDB;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.CacheUtils;
import com.example.jiong.mynews.Utils.HttpCallBackListener;
import com.example.jiong.mynews.Utils.HttpUtil;
import com.example.jiong.mynews.Utils.MynewsApplication;
import com.example.jiong.mynews.domain.City;
import com.example.jiong.mynews.domain.County;
import com.example.jiong.mynews.domain.Province;

import java.util.ArrayList;
import java.util.List;

public class CitySelectorActivity extends Activity {
    private ListView listView;
    private TextView textView;
    private static final int LEVER_PROVINCE = 1;
    private static final int LEVER_CITY = 2;
    private static final int LEVER_COUNTY = 3;
    private int CURRENT_LEVER = 0;
    private WeatherDB weatherDB;
    private ArrayAdapter adapter;
    private List<String> datalist = new ArrayList<String>();/*列表数据*/
    private List<Province> ProvinceList; /*省份信息集合*/
    private List<City> CityList;/*省份信息集合*/
    private List<County> CountyList;/*县城信息集合*/
    private Province SelectedProvince;/*被选中的省份*/
    private City SelectedCity;
    private County SelectCounty;
    private ProgressDialog progress;
    private String WeatherCodeurl;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        MynewsApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_city_selector);
        listView = (ListView) findViewById(R.id.Weather_list);
        textView = (TextView) findViewById(R.id.Weather_list_title);
        weatherDB = WeatherDB.getInstance(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datalist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (CURRENT_LEVER == LEVER_PROVINCE) {
                    SelectedProvince = ProvinceList.get(position);
                    QueryCity();
                } else if (CURRENT_LEVER == LEVER_CITY) {
                    SelectedCity = CityList.get(position);
                    QueryCounty();
                } else if (CURRENT_LEVER == LEVER_COUNTY) {
                    SelectCounty = CountyList.get(position);
                    QueryFromNet(SelectCounty.getCounty_code(), "WeatherCode");
                }
            }
        });
        QueryProvince();
    }


    private void QueryProvince() {
        /*先从数据库中寻找*/
        ProvinceList = weatherDB.loadProvince();
        if (ProvinceList.size() > 0) {
            datalist.clear();
            for (Province province : ProvinceList) {
                datalist.add(province.getProvince_name());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            textView.setText("中国");
            CURRENT_LEVER = LEVER_PROVINCE;
            /*将第一个子项放在第一位*/
        } else {
            QueryFromNet("", "PROVINCE");
        }
    }


    private void QueryCity() {
        /*先从数据库中寻找*/
        CityList = weatherDB.loadCity(SelectedProvince.getId());
        if (CityList.size() > 0) {
            datalist.clear();
            for (City city : CityList) {
                datalist.add(city.getCity_name());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            textView.setText(SelectedProvince.getProvince_name());
            CURRENT_LEVER = LEVER_CITY;
            /*将第一个子项放在第一位*/
        } else {
            QueryFromNet(SelectedProvince.getProvince_code(), "CITY");
        }
    }

    private void QueryCounty() {
        CountyList = weatherDB.loadCounty(SelectedCity.getId());
        if (CountyList.size() > 0) {
            datalist.clear();
            for (County county : CountyList) {
                datalist.add(county.getCounty_name());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            textView.setText(SelectedCity.getCity_name());
            CURRENT_LEVER = LEVER_COUNTY;
        } else {
            QueryFromNet(SelectedCity.getCity_code(), "COUNTY");
        }
    }

    private void QueryFromNet(String code, final String type) {
        String address;
        if (!TextUtils.isEmpty(code)) {/*如果为空 说明当前是查询城市或者县城信息*/
            address = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
            Log.d("Tag", address);
        } else {
            /*这是查询省份信息*/
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }
        HttpUtil.SendHttpRequest(address, new HttpCallBackListener() {
            @Override
            public void OnFinish(String responce) {
                boolean result = false;
                if ("PROVINCE".equals(type)) {
                    result = HttpUtil.handleProvinceResponse(weatherDB, responce);
                } else if ("CITY".equals(type)) {
                    result = HttpUtil.handleCityResponse(weatherDB, responce, SelectedProvince.getId());
                } else if ("COUNTY".equals(type)) {
                    result = HttpUtil.handleCountyResponse(weatherDB, responce, SelectedCity.getId());
                } else if ("WeatherCode".equals(type)) {
                    HttpUtil.WeatherState state = HttpUtil.handleWeatherCodeResponse(responce);
                    result = state.isSuccess;
                    WeatherCodeurl = state.WeatherCode;
                }
                if (result) {/*表示数据处理成功  重新返回主线程去获取省市县信息 不过是从数据库中*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ("PROVINCE".equals(type)) {
                                QueryProvince();
                            } else if ("CITY".equals(type)) {
                                QueryCity();
                            } else if ("COUNTY".equals(type)) {
                                QueryCounty();
                            } else if ("WeatherCode".equals(type)) {
                                Intent intent = new Intent(CitySelectorActivity.this, WeatherDetailActivity.class);
                                /*intent.putExtra("WeatherCode",WeatherCodeurl);*/
                                startActivity(intent);
                                CacheUtils.putString(CitySelectorActivity.this, "isSelectedCity", WeatherCodeurl);
                                finish();
                            }
                        }
                    });
                }
            }

            @Override
            public void OnError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CitySelectorActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (CURRENT_LEVER == LEVER_PROVINCE) {
            finish();
        } else if (CURRENT_LEVER == LEVER_CITY) {
            QueryProvince();
        } else if (CURRENT_LEVER == LEVER_COUNTY) {
            QueryCity();
        }
    }
}
