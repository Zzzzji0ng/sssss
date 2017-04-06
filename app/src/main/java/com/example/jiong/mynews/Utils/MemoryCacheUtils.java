package com.example.jiong.mynews.Utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Jiong on 2017/3/20.
 */
public class MemoryCacheUtils {

    /*缓存图片的集合*/
    private LruCache<String,Bitmap> lruCache;
    public MemoryCacheUtils() {
        int maxSize= (int) (Runtime.getRuntime().maxMemory()/1024/8); /*获得最大内存的1/8 用来缓存图片*/
        lruCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return (value.getRowBytes() * value.getHeight())/1024; /*计算每张图片大小*/
            }
        };

    }

    /*根据url保存图片到内存中*/
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url,bitmap);
    }
    /*根据url 从内存中获取图片*/
    public Bitmap getBitmap(String url){
        return lruCache.get(url);
    }
}
