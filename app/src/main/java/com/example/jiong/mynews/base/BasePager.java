package com.example.jiong.mynews.base;

        import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.example.jiong.mynews.R;

/**
 * Created by Jiong on 2017/2/12.
 * 公共类 积累 首页 第二页 第三页 设置页的父类
 */
public class BasePager {
    public  Context mContext;
    public View rootview;
    /*代表不同的页面*/
    /*公共部分的空间实例化*/
    public FrameLayout fl_content;
    /*这个帧布局用于加载孩子的布局*/

    /*1.构造方法*/
    public BasePager (Context context){
        this.mContext=context;
        /*初始化视图*/
        rootview =iniView();
    }

    /*初始化公共的页面部分*/
    public View iniView() {
        View view=View.inflate(mContext, R.layout.basepager,null);
        fl_content=(FrameLayout)view.findViewById(R.id.fl_content);
        return view;
    }
    public void iniData(){
        /*当子类需要添加数据的时候  或者需要动态显示数据的时候 需要调用改方法*/
        /*添加到fl_content 这个布局中区*/
        /*fl_content.addView(textView);*/
        /*这一步由他的子类来实现*/
    }
}
