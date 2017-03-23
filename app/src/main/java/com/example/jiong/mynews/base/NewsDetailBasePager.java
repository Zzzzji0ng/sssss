package com.example.jiong.mynews.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Jiong on 2017/2/22.
 */

/*不同新闻或者图片中的父布局*/
public abstract class NewsDetailBasePager {
    public Context mContext;

    /**
     * 代表不同子页面的详情页面
     */
    public View rootView;

    public NewsDetailBasePager(Context context) {
        this.mContext = context;
        rootView = initView();
        /*初始化视图*/
    }

    public abstract View initView();
    /*根据各自的需求构造不同的视图*/

    /**
     * 当孩子需要请求数据，或者需要动态显示数据的时候重新该方法
     */
    public void initData() {

    }
    /*新闻调用这个*/

    public void initPicDate(String type){

    }
    /*图片调用这个*/
}
