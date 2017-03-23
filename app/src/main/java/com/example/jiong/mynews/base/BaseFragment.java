package com.example.jiong.mynews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jiong on 2017/2/10.
 * Fragment 的父类
 */
public abstract class BaseFragment extends Fragment /*需要继承的是V4 包中的fragment*/ {
    public Context mContext;

    @Override/*当创建的时候  回调获得一个context*/
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }


    /*当创建视图调用这个方法*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return intView();
    }

    /*让子类实现该方法 创建自己的布局*/
    public abstract View intView() ;

    /*当Activity 实例化好的时候调用 用来填充数据等等行为*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniData();
    }

    /*当子类需要初始化数据的时候  调用这个方法*/
    protected  void iniData() {
    }
}
