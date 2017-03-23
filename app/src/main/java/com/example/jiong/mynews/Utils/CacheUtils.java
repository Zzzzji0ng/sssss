package com.example.jiong.mynews.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jiong on 2017/2/12.
 */
public class CacheUtils {
    public static void putBoolean(Context context,String key,boolean values){
        SharedPreferences sharedPreferences=context.getSharedPreferences("guideMessage",Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,values).commit();
    }
    public static boolean getBoolean(Context context,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences("guideMessage",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    public static void putString(Context mContext, String key, String values) {
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("guideMessage",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,values).commit();
    }

    public static String getString(Context mContext, String key) {
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("guideMessage",Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");/*默认返回空*/
    }
}
