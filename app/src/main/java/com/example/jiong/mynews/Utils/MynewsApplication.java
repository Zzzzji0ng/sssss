package com.example.jiong.mynews.Utils;

import android.app.Activity;
import android.app.Application;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiong on 2017/2/12. 初始化xutils
 */
public class MynewsApplication extends Application {
/*
    public Context Mcontext ;*/
/*全局上下文*//*

    /*若类名为灰色的 则应在清单文件中配置  name 标签*/

    public static List<Activity> activityList ;
    private static MynewsApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        /*Mcontext =getApplicationContext();*/
        x.Ext.init(this);
        x.Ext.setDebug(true);
        /*初始化xutils*/

    }
    public synchronized static MynewsApplication getInstance() {
        if (null == instance) {
            instance = new MynewsApplication();
            activityList=new ArrayList<Activity>();
        }
        return instance;
    }
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }



}
