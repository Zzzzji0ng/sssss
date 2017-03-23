package com.example.jiong.mynews.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

/**
 * Created by Jiong on 2017/3/22.
 *
 *
 * 视频缩略图获取工具
 */
public class VideoBitmapUtils {


    private videoimageNetCacheUtils videoimageNetCacheUtils;  /*网络缓存工具类*/

    private LocalCacheUtils localCacheUtils;/*本地缓存工具类*/

    private MemoryCacheUtils memoryCacheUtils; /*内存缓存工具类*/
    /*
    * 图片三级缓存原理
    * 1.从内存中取出图片 若不存在  从本地获取
    * 2.从本地中取出图片  同时在内存中保存一份  若不存在  从网络获取
    * 3.从网络中取出图片  同时在本地保存一份  在内存中再存一份
    *
    *
    * */

    public VideoBitmapUtils(Context context, Handler handler) {
        memoryCacheUtils = new MemoryCacheUtils();
        localCacheUtils = new LocalCacheUtils(memoryCacheUtils);/*要在网络缓存之前构造  将实例传入NetCacheUtils 中*/
        videoimageNetCacheUtils = new videoimageNetCacheUtils(context,handler, localCacheUtils, memoryCacheUtils);  /*在构造方法中将网络缓存工具类构造出来*/
    }

    public Bitmap getBitmap(Context context, String url, int position, String type) {
        // 1. 从内存中获取
        if (memoryCacheUtils != null) {
            Bitmap bitmap = memoryCacheUtils.getBitmap(url);
            if (bitmap != null) {
                Log.d("TAG", "从内存中获取图片" + position);
                return bitmap;
            }
        }
        // 2. 从本地中获取
        if (localCacheUtils != null) {
            Bitmap bitmap = localCacheUtils.getBitmapFromUrl(url);
            if (bitmap != null) {
                Log.d("TAG", "从本地获取图片" + position);
                return bitmap;
            }
        }

        // 3. 从网络中获取

        if (videoimageNetCacheUtils != null) {
            videoimageNetCacheUtils.getBitmapFromNet(url, position,type);
        }

        return null;
    }

}
