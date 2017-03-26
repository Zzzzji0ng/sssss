package com.example.jiong.mynews.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jiong.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jiong on 2017/2/25.
 */
/*自定义下拉刷新控件*/
public class RefreshListView extends ListView {
    /*只需要实现三个构造方法  第四个会报错*/

    private ImageView iv_red_arrow;
    private ProgressBar pb_status;
    private TextView pull_down_text;
    private TextView pull_down_date_text;
    private LinearLayout view; /*这是整个header 控件 包括下拉刷新控件和顶部的轮播图*/
    private LinearLayout ll_Pull_down;  /*下拉刷新的控件*/
    private View topView;/*顶部轮播图部分*/
    private int headerViewHeight;/*下来刷新控件的高度*//*用来判断是否显示完全*/
    private int ReFreshListViewHeight = -1;/*整个Refreshlistview 的高度*/
    private float startY = 0; /*记录起始位置*/
    private Context context;

    private static final int PULL_DOWN_REFRESH = 1;  /*下拉刷新状态*/
    private static final int RELEASE_REFRESH = 2; /*手松刷新状态*/
    private static final int REFRESHING = 3; /*正在刷新状态*/
    private int currentState = PULL_DOWN_REFRESH;

    private Animation animationDown;/*下拉刷新的动画*/
    private Animation animationUp;
    private View footview;/*加载更多的布局*/
    private int FooterViewHeightfoot;/*加载更多布局的高度*/
    private boolean isLoadMore = false;/*是否是加载更多新闻*/

    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderrView(context);
        initFootView(context);
        iniAnimation();/*下拉刷新的动画*/
        this.context=context;
    }

    private void initHeaderrView(Context context) {
        view = (LinearLayout) View.inflate(context, R.layout.refresh_header, null);/*整个视图 包括之后添加的图片轮播部分*/
        ll_Pull_down = (LinearLayout) view.findViewById(R.id.ll_pull_down);/*下拉刷新控件*/
        iv_red_arrow = (ImageView) view.findViewById(R.id.iv_red_arrow);
        pb_status = (ProgressBar) view.findViewById(R.id.pb_status);
        pull_down_text = (TextView) view.findViewById(R.id.pull_down_text);
        pull_down_date_text = (TextView) view.findViewById(R.id.pull_down_date_text);

        /*通过调用测量方法  得到高度  传入的值不会被作为数值*/
        ll_Pull_down.measure(0, 0);  /*要用下拉刷新控件来测量 不是用这个控件来测量*/
        headerViewHeight = ll_Pull_down.getMeasuredHeight();
        /*下拉刷新控件隐藏和显示的原理
        View.setPadding(0,-控件高，0,0）；//完全隐藏
        View.setPadding(0,0，0,0）；//完全显示
        View.setPadding(0,控件高，0,0）；//两倍完全显示*/
        ll_Pull_down.setPadding(0, -headerViewHeight, 0, 0);/*默认设置控件隐藏*/

        RefreshListView.this.addHeaderView(view);/*将整个控件作为头*/

    }


    private void initFootView(Context context) {
        footview = View.inflate(context, R.layout.refresh_footer, null);
        footview.measure(0, 0);
        FooterViewHeightfoot = footview.getMeasuredHeight();
        /*上拉加载更多控件隐藏和显示的原理和下拉控件的原理相同
        View.setPadding(0,-控件高，0,0）；//完全隐藏
        View.setPadding(0,0，0,0）；//完全显示
        View.setPadding(0,控件高，0,0）；//两倍完全显示*/
        footview.setPadding(0, -FooterViewHeightfoot, 0, 0);/*在下方也是用负数才能隐藏*/
        RefreshListView.this.addFooterView(footview);
        /*监听滑动到listview最后一条可见的item  滚动监听*/
        RefreshListView.this.setOnScrollListener(new MyOnScrollListener());
    }

    class MyOnScrollListener implements OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            /*静止或者是滚动状态  而且最后一个item可见的时候 */
            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE || scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                if (getLastVisiblePosition() == getAdapter().getCount() - 1 && getAdapter().getCount() <= 30) {
                    /*获得最后一个位置  与适配器-1 进行比较*/
                    /*1.显示控件*/
                    footview.setPadding(0, 0, 0, 0);/*显示控件*/
                    /*2.设置状态*/
                    isLoadMore = true;
                    /*3.回调接口*/
                    if (myOnRefreshListener != null) {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                myOnRefreshListener.onLoadMore();/*若接口的实例不为空 则调用这个加载更多的方法*/
                                //延迟一秒再执行回调 不然网速太快看不见加载控件
                            }
                        }, 1000);

                    }
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }

    /*初始化动画的样式*/
    private void iniAnimation() {

        /*逆时针旋转比较美观*/
        animationUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationUp.setDuration(500);
        animationUp.setFillAfter(true);

        animationDown = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationDown.setDuration(500);
        animationDown.setFillAfter(true);
    }


    @Override/*下拉事件的处理*/
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();  /*1.记录起始坐标*/
                break;
            case MotionEvent.ACTION_MOVE:
                if (startY == 0) {
                    startY = ev.getY();  /*有时候未记录起始坐标*/
                }
                Boolean isdisplaytopnews = isDisplayTopNews(); /*判断是否完全显示顶部轮播图部分*/
                if (!isdisplaytopnews) {
                    /*如果不是完全显示  之后的代码不用执行 若没有加入判断  之后的上拉加载更多会出现BUG*/
                    break;
                }
                float endY = ev.getY();  /*2.记录结束坐标*/
                float distance = endY - startY;
                if (distance > 0) {
                    /*说明向下滑动*/
                    int paddingTop = (int) (distance - headerViewHeight);


                    if (paddingTop < 0 && currentState != PULL_DOWN_REFRESH) {
                        //下拉刷新
                        currentState = PULL_DOWN_REFRESH;
                        //更新状态
                        refreshStatus();
                    } else if (paddingTop > 0 && currentState != RELEASE_REFRESH) {
                        //手势刷新
                        currentState = RELEASE_REFRESH;
                        //更新状态
                        refreshStatus();
                    }

                    ll_Pull_down.setPadding(0, paddingTop, 0, 0);

                }
                break;
            case MotionEvent.ACTION_UP:
                startY = 0;
                if (currentState == PULL_DOWN_REFRESH) {
                    /*如果没有拉到松开刷新的状态  将刷新控件隐藏*/
                    //View.setPadding(0,-控件高，0,0）；//完成隐藏
                    ll_Pull_down.setPadding(0, -headerViewHeight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {

                    currentState = REFRESHING;
                    //状态要更新
                    refreshStatus();
                    ll_Pull_down.setPadding(0, 0, 0, 0);/*将控件完全显示出来*/
                    //View.setPadding(0,0，0,0）；//完成显示
                    //回调接口
                    if (myOnRefreshListener != null) {
                        myOnRefreshListener.onPullDownlRefresh();
                    }
                }

                break;
        }
        return super.onTouchEvent(ev);
    }

    /*更新样式*/
    private void refreshStatus() {
        switch (currentState) {
            case PULL_DOWN_REFRESH:/*下拉刷新*/
                pull_down_text.setText("下拉刷新");
                iv_red_arrow.startAnimation(animationUp);
                break;
            case RELEASE_REFRESH:/*松开刷新*/
                pull_down_text.setText("松开刷新");
                iv_red_arrow.startAnimation(animationDown);
                break;
            case REFRESHING: /*正在刷新*/
                pb_status.setVisibility(VISIBLE);
                iv_red_arrow.setVisibility(GONE);
                iv_red_arrow.clearAnimation();/*将箭头的旋转动画清除  以免还在显示箭头*/
                pull_down_text.setText("正在刷新");
                break;
        }
    }

    private Boolean isDisplayTopNews() {
        /*RefreshListView的顶部在Y轴的坐标小于或者等于顶部轮播图部分的顶部在Y轴的坐标的时候  显示完全*/
        if (topView != null) {
            int[] location = new int[2];
            if (ReFreshListViewHeight == -1) {/*说明未测量RefreshListView的高度*/
                RefreshListView.this.getLocationOnScreen(location);/*将他的位置信息返回到location中*/
                ReFreshListViewHeight = location[1];/*Y轴的位置处于数组第二位*/
            }
            topView.getLocationOnScreen(location);
            int topnewsViewOnScreenY = location[1];
            return ReFreshListViewHeight <= topnewsViewOnScreenY; /*返回true 轮播图说明完全显示*/
        } else {
            return true;
        }
    }

    /*将轮播视图动态加入到下拉刷新控件的下面*/
    public void addTopView(View topView) {
        this.topView = topView;
        if (view != null && topView != null) {
            view.addView(topView);
        }
    }

    public void onRreshFinish(Boolean success) {
        if (isLoadMore) {
            footview.setPadding(0, -FooterViewHeightfoot, 0, 0);
            isLoadMore = false;
        } else {
            pull_down_text.setText("下拉刷新");
            iv_red_arrow.clearAnimation();
            iv_red_arrow.setVisibility(VISIBLE);
            pb_status.setVisibility(GONE);
            ll_Pull_down.setPadding(0, -headerViewHeight, 0, 0);
        }

        if (success) {
            pull_down_date_text.setText("更新时间:" + getRefreshTime());
            /*Toast.makeText(context,"新闻更新成功",Toast.LENGTH_SHORT).show();*/
        }
    }

    private String getRefreshTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /*监听视图刷新监听者*/
    public interface OnRefreshListener {
        /*当下拉刷新的时候回调这个方法*/
        public void onPullDownlRefresh();

        /*当加载更多的时候回调这方法*/
        public void onLoadMore();
    }

    private OnRefreshListener myOnRefreshListener;

    /*调用方设置视图刷新的监听器*/
    public void setOnRefreshListener(OnRefreshListener l) {
        this.myOnRefreshListener = l;
    }
}
