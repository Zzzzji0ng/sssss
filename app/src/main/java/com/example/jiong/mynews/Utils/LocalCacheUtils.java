package com.example.jiong.mynews.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Jiong on 2017/3/20.
 * <p/>
 * <p/>
 * 本地缓存工具类
 */
public class LocalCacheUtils {


    private final MemoryCacheUtils memoryCacheUtils;

    public LocalCacheUtils(MemoryCacheUtils memoryCacheUtils) {
        this.memoryCacheUtils=memoryCacheUtils;
    }

    /*根据图片的url 保存图片*/
    public void putBitmap(String url, Bitmap bitmap) {

        //1.根据图片路径加密  文件名
        //2 创建文件
        //3 保存
        try {
            String filename = MD5Encoder.encode(url);
            File file = new File(Environment.getExternalStorageDirectory() + "/mynews", filename);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            OutputStream os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Bitmap getBitmapFromUrl(String url) {

        String filename = null;
        try {
            filename = MD5Encoder.encode(url);
            File file = new File(Environment.getExternalStorageDirectory() + "/mynews", filename);
            if (file.exists()){
                FileInputStream fis=new FileInputStream(file);
                Bitmap bitmap=BitmapFactory.decodeStream(fis);
                if (bitmap!=null){
                    memoryCacheUtils.putBitmap(url,bitmap); /*缓存到内存中*/
                }
                fis.close();
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
