package com.example.jiong.mynews.Utils;

/**
 * Created by Jiong on 2017/3/2.
 */
public interface HttpCallBackListener {
    void OnFinish(String responce);
    void OnError(Exception e);
}
