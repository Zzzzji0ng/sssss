package com.example.jiong.mynews.Utils;


import android.text.TextUtils;

import com.example.jiong.mynews.DatabBase.WeatherDB;
import com.example.jiong.mynews.domain.City;
import com.example.jiong.mynews.domain.County;
import com.example.jiong.mynews.domain.Province;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jiong on 2017/3/2.
 */
public class HttpUtil {
    public static void SendHttpRequest(final String address,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url=new URL(address);
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setRequestMethod("GET");
                    InputStream inputStream=connection.getInputStream();
                    Reader reader=new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader bufferedReader=new BufferedReader(reader);
                    StringBuilder stringBuilder=new StringBuilder();
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }if (listener!=null){
                        listener.OnFinish(stringBuilder.toString());
                    }
                } catch (IOException e) {
                    listener.OnError(e);
                } finally {
                    if (connection!=null){
                        connection.disconnect();/*记得关流*/
                    }
                }
            }
        }).start();
    }


    /*处理返回的省份信息*/
    public synchronized static boolean handleProvinceResponse(WeatherDB weatherDB, String responce){
        if (!TextUtils.isEmpty(responce)){/*判断返回的数据是否为空*/
            /*01|北京,02|上海,03|天津,04|重庆,05|黑龙江,06|吉林,07|*/
            /*查询省份返回的是这种格式的数据  先用，将他们拆开保存*/
            String [] allProvince =responce.split(",");
            if (allProvince.length>0){
                for (int i=0;i<allProvince.length;i++){
                    String []array=allProvince[i].split("\\|");
                    Province province=new Province();
                    province.setProvince_code(array[0]);
                    province.setProvince_name(array[1]);
                    weatherDB.SaveProvince(province);
                }
                return true;
            }
        }
        return false;/*若为空 表示获取省份信息失败*/
    }

    /*处理返回的城市信息*/
    public  static boolean handleCityResponse (WeatherDB weatherDB,String responce,int Province_id){
        if (!TextUtils.isEmpty(responce)){
             /*2101|杭州,2102|湖州,2103*/
            String [] allCity=responce.split(",");
            if (allCity.length>0){
                for (int i=0;i<allCity.length;i++){
                    String [] array=allCity[i].split("\\|");
                    City city=new City();
                    city.setCity_code(array[0]);
                    city.setCity_name(array[1]);
                    city.setProvince_id(Province_id);
                    weatherDB.SaveCity(city);
                }
                return true;
            }
        }return false;
    }

    /*处理返回的县城的信息*/
    public static boolean handleCountyResponse(WeatherDB weatherDB,String Responce,int City_id){
        if (!TextUtils.isEmpty(Responce)){
            /*210101|杭州,210102|萧山,210*/
            String [] allCounty =Responce.split(",");
            if (allCounty.length>0){
                for (int i=0;i<allCounty.length;i++){
                    County county=new County();
                    String [] array=allCounty[i].split("\\|");
                    county.setCity_id(City_id);
                    county.setCounty_code(array[0]);
                    county.setCounty_name(array[1]);
                    weatherDB.SaveCounty(county);
                }
                return true;
            }

        }
        return false;
    }
    public static WeatherState handleWeatherCodeResponse(String Responce){
        WeatherState weatherState= new WeatherState();
        if (!TextUtils.isEmpty(Responce)){
            String [] WeatherCodeArray =Responce.split("\\|");
            weatherState.WeatherCode=WeatherCodeArray[1];
            weatherState.isSuccess=true;
        }
        return weatherState;
    }

    public static class WeatherState{
        public Boolean isSuccess;
        public String WeatherCode;
    }
}
