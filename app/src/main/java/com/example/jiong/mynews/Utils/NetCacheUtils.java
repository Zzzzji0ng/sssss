package com.example.jiong.mynews.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jiong on 2017/3/20.
 *
 *
 *
 * 网络缓存图片工具
 */
public class NetCacheUtils {

    private final Handler handler;
    public static final int SUCCESS=1;
    public static final int FAIL=2;
    private final LocalCacheUtils localCacheUtils; /*本地缓存工具类*/
    private final MemoryCacheUtils memoryCacheUtils; /*内存缓存工具类*/
    private ExecutorService service;

    public NetCacheUtils(Handler handler, LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        this.handler=handler;
        this.localCacheUtils =localCacheUtils;
        this.memoryCacheUtils=memoryCacheUtils;
        service=Executors.newFixedThreadPool(10);
    }

    public void getBitmapFromNet(String url, int position) {
        service.execute(new MyRunnable(url,position));
    }

    class MyRunnable implements Runnable{
        private final String url;
        private final int position;

        public MyRunnable(String url, int position) {
            this.url =url;
            this.position=position;
        }

        @Override
        public void run() {
            /*联网请求图片*/
            HttpURLConnection connection= null;
            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");/*大写*/
                connection.setReadTimeout(8000);
                connection.setConnectTimeout(8000);
                connection.connect();
                int code=connection.getResponseCode();
                if (code==200){
                    InputStream inputStream=connection.getInputStream();
                    Bitmap bitmap=BitmapFactory.decodeStream(inputStream);

                    /*在内存中保存一份  */

                    memoryCacheUtils.putBitmap(url,bitmap);

                    /*在本地中保存一份*/
                    localCacheUtils.putBitmap(url,bitmap);

                    /*记得关流*/
                    inputStream.close();

                    //发送成功消息
                    Message message=new Message();
                    message.obj=bitmap;
                    message.arg1=position;
                    message.what=SUCCESS;
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Message message=new Message();
                message.what=FAIL;
                message.arg1=position;
                handler.sendMessage(message);
            }

        }
    }
}
