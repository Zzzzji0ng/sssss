package com.example.jiong.mynews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Jiong on 2017/2/25.
 */
/*水平方向的Viewpager*/

public class HorizontalScrollViewpager extends ViewPager {
    private float Start_X;
    private float Start_Y;/*记录起始坐标*/

    public HorizontalScrollViewpager(Context context) {
        this(context,null);/*之所以改变这个 当构造实例的时候需要传入数据的时候，只需要重写下面的方法就可以实现，
        无需再次调用这个放法*/
    }

    public HorizontalScrollViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                /*要先把事件给自己*/
                getParent().requestDisallowInterceptTouchEvent(true);
                Start_X=ev.getX();
                Start_Y=ev.getY();

            break;
            case MotionEvent.ACTION_MOVE:
                /*计算偏移量 来确定是横向滑动或者是纵向滑动 已确定是否拦截事件 */
                float endX=ev.getX();
                float endY=ev.getY();
                float distanceX=endX-Start_X;
                float distanceY=endY-Start_Y;
                /*判断滑动方向*/
                if (Math.abs(distanceX)<Math.abs(distanceY)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }/*如果是数值滑动方向  不拦截事件*/
            break;
            case MotionEvent.ACTION_UP:
            break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
