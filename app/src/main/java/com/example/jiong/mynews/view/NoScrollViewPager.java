package com.example.jiong.mynews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Jiong on 2017/2/15.
 */
/*用来消耗滑动事件  是contentFragmeng 中的viewpager不能滑动切换视图*/
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context,null);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*这两个构造方法必须都要实现  上面的那个参数中还记得要加入null*/

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;/*将滑动事件消耗掉*/
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;

        /*将这个事件返回false  Tabviewpager的操作不会影响到外层的viewpager  */
    }
}
