package com.example.jiong.mynews.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.example.jiong.mynews.pager.ThridNews;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jiong on 2017/3/22.
 */
public class videoimageNetCacheUtils {
    private final Handler handler;
    public static final int SUCCESS = 1;
    public static final int FAIL = 2;
    private final LocalCacheUtils localCacheUtils; /*本地缓存工具类*/
    private final MemoryCacheUtils memoryCacheUtils;
    private final Context context;
    private ExecutorService service;
    private int[] a;
    private int width;
    private int height;
    private int width1;
    private int width2;
    private int height1;
    private int height2;

    public videoimageNetCacheUtils(Context context, Handler handler, LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        this.context = context;
        this.handler =handler;
        this.localCacheUtils = localCacheUtils;
        this.memoryCacheUtils = memoryCacheUtils;
        service = Executors.newFixedThreadPool(10);
        a = DensityUtil.getScreenSize(context);
        width1 = a[0] * 4 / 10;
        height1 = a[1] / 6;
        width2=a[0];
        height2=a[1]/3;
        /*先获得当前设备的宽度0.4   高度*1/6*/
    }

    public void getBitmapFromNet(String url, int position, String type) {
        service.execute(new MyRunnable(url, position,type));
    }

    class MyRunnable implements Runnable {
        private final String url;
        private final int position;
        private final String type;

        public MyRunnable(String url, int position,String type) {
            this.url = url;
            this.position = position;
            this.type=type;
        }

        @Override
        public void run() {
            if (ThridNews.TYPE1.equals(type)){
                width=width1;
                height=height1;
            } else if (ThridNews.TYPE2.equals(type)){
                width=width2;
                height=height2;
            }

            try {
                Bitmap bitmap = GetVideoImage.createVideoThumbnail(url, width1, height1);;



                memoryCacheUtils.putBitmap(url, bitmap);

                    /*在本地中保存一份*/
                localCacheUtils.putBitmap(url, bitmap);


                //发送成功消息
                Message message = new Message();
                message.obj = bitmap;
                message.arg1 = position;
                message.what = SUCCESS;
                handler.sendMessage(message);


            }catch (Exception e) {
                e.printStackTrace();
                Message message = new Message();
                message.what = FAIL;
                message.arg1 = position;
                handler.sendMessage(message);
            }
        }


    }
}



