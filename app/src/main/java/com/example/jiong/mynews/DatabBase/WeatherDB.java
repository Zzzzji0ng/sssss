package com.example.jiong.mynews.DatabBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jiong.mynews.domain.City;
import com.example.jiong.mynews.domain.County;
import com.example.jiong.mynews.domain.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiong on 2017/3/2.
 */
/*/*将对数据库的一些操作封装起来 便于使用*/

public class WeatherDB {
    public static final String DB_NAME = "weather";
    /*数据库的名称*/
    public static final int DB_VERSION = 1;
    /*数据库版本*/
    private static WeatherDB weatherDB;
    private SQLiteDatabase database;

    private WeatherDB(Context context){
        /*构建数据库 完成建表语句*/
        WeatherOpenHelper openHelper=new WeatherOpenHelper(context,DB_NAME,null,DB_VERSION);
        database=openHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context){
        if (weatherDB==null){
            weatherDB=new WeatherDB(context);
        }
        return weatherDB;
    }/*将构造方法私有化 保证全局只有一个实例*/

    /*省份信息的存储*/
    public void SaveProvince (Province province){
        if (province!=null){
            ContentValues values=new ContentValues();
            values.put("Province_name",province.getProvince_name());
            values.put("Province_code",province.getProvince_code());
            database.insert("Province",null,values);
        }
    }
    /*城市信息的存储*/
    public void SaveCity (City city){
        if (city!=null){
            ContentValues values=new ContentValues();
            values.put("City_name",city.getCity_name());
            values.put("City_code",city.getCity_code());
            values.put("Province_id",city.getProvince_id());
            database.insert("City",null,values);
        }
    }
    /*县城信息的存储*/
    public void SaveCounty(County county){
        if (county!=null){
            ContentValues values =new ContentValues();
            values.put("County_code",county.getCounty_code());
            values.put("County_name",county.getCounty_name());
            values.put("City_id",county.getCity_id());
            database.insert("County",null,values);
        }
    }

    /*读取省份信息*/
    public List<Province> loadProvince(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor =database.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){/*当返回的cursor 为空的时候  会返回false*/
            do {
                Province province =new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvince_code(cursor.getString(cursor.getColumnIndex("Province_code")));
                province.setProvince_name(cursor.getString(cursor.getColumnIndex("Province_name")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();/*记得关闭*/
        }
        return list;
    }

    /*从数据库中读取城市的信息*/
    public List<City> loadCity(int ProvinceId){
        List<City> list =new ArrayList<City>();
        Cursor cursor =database.query("City",null,"Province_id=?",new String[]{String.valueOf(ProvinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCity_code(cursor.getString(cursor.getColumnIndex("City_code")));
                city.setCity_name(cursor.getString(cursor.getColumnIndex("City_name")));
                city.setProvince_id(ProvinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }

    /*县的信息的读取*/
    public List<County> loadCounty(int CityId){
        List<County> list =new ArrayList<County>();
        Cursor cursor = database.query("County",null,"City_id=?",new String[]{String.valueOf(CityId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCity_id(CityId);
                county.setCounty_code(cursor.getString(cursor.getColumnIndex("County_code")));
                county.setCounty_name(cursor.getString(cursor.getColumnIndex("County_name")));
                list.add(county);
            }while (cursor.moveToNext());

        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }
}
