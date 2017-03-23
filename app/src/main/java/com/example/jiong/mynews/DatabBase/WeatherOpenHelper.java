package com.example.jiong.mynews.DatabBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jiong on 2017/3/2.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {
    /*省市县的建表语句*/
    public static final String CREATE_PORVINCE="create table Province("
            +"id integer primary key autoincrement,"
            +"Province_name text,"
            +"Province_code text)";
    public static final String CREATE_CITY="create table City("
            +"id integer primary key autoincrement,"
            +"Province_id integer,"
            +"City_code text,"
            +"City_name text)";
    private static final String CREATE_COUNTY="create table County("
            +"id integer primary key autoincrement,"
            +"City_id integer,"
            +"County_code text,"
            +"County_name text)";


    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*在这个方法中完成建表语句创建*/
        db.execSQL(CREATE_PORVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
