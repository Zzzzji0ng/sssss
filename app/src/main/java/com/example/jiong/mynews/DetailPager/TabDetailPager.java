package com.example.jiong.mynews.DetailPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jiong.mynews.Activity.NewsDetailActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.CacheUtils;
import com.example.jiong.mynews.Utils.Constants;
import com.example.jiong.mynews.base.NewsDetailBasePager;
import com.example.jiong.mynews.domain.NewsContentPagerBean;
import com.example.jiong.mynews.view.HorizontalScrollViewpager;
import com.example.jiong.mynews.view.RefreshListView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiong on 2017/2/17.
 */
/*这是主新闻界面下的FrameLayout中的页面  实现Tab 和Viewpager 相结合的页签页面*/
public class TabDetailPager extends NewsDetailBasePager {
    public static final String READ_ARRAY_UNIQUEKEY = "Read_array_uniquekey";/*保存已经阅读过得新闻的uniquekey*/
    private List<NewsContentPagerBean.ResultBean.DataBean> dataBeanList; /*图片轮播的两个新闻数据*/
    private List<NewsContentPagerBean.ResultBean.DataBean> dataBeanAllList; /*第一次刷新得到的新闻数据*/
    private List<NewsContentPagerBean.ResultBean.DataBean> MoredataBeanList;/*加载更多得到的新闻数据*/

    private MyNewsListAdapter listadapter;
    private HorizontalScrollViewpager pic_viewPager; /*图片轮播部分的viewpager*/
    private TextView title_text;
    private LinearLayout layout; /*轮播控件*/
    private RefreshListView listView;
    private MyViewPagerHander hander;
    private String text;/*保存当前页签的类型 如国内，社会*/
    private String url;
    private Boolean isDragging = false;/*是否拖拽*/
    private int PreSelectPosition;/*之前红点所在的位置*/
    private int LargeSize = 0;  /*用来记录加载的新闻的数量*/
    private int cishu=0;


    public TabDetailPager(Context context, String text) {
        super(context);
        this.text = text;

    }

    @Override
    public View initView() {
        /*返回初始化的Rootview*/
        View view = View.inflate(mContext, R.layout.newstabviewpagerdetail, null);
        listView = (RefreshListView) view.findViewById(R.id.List_view);
        /*将viewpager的布局提取出来 在list实例化之后动态加载布局  记得将 View 改为 topView 实例化其中的控件*/
        View topView = View.inflate(mContext, R.layout.news_top_viewpager, null);
        pic_viewPager = (HorizontalScrollViewpager) topView.findViewById(R.id.pic_viewpager);
        layout = (LinearLayout) topView.findViewById(R.id.LinearLayout_Viewpager);
        title_text = (TextView) topView.findViewById(R.id.Title_text);
        listView.addTopView(topView); /*在 refreshKistView 中将轮播图动态加入到视图*/
        /*这些布局是在View中的  所以不能在上面用@ViewInject(R.id.pic_viewpager) 这种方式实例化  */
        return view;
    }  /*加载布局*/

    @Override
    public void initData() {
        super.initData();
        getDatefromNet();
        pic_viewPager.addOnPageChangeListener(new MyVallOnPageChangeListener());
        /*监听下拉刷新控件的事件*/
        listView.setOnRefreshListener(new MyOnRefreshListener());
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }  /*加载数据*/

    private void getDatefromNet() {
        /*xutil 异步加载网络get的方法*/
        switch (text) {
            case "头条":
                url = Constants.TOP;
                break;
            case "社会":
                url = Constants.SHEHUI;
                break;
            case "国内":
                url = Constants.GUONEI;
                break;
            case "国际":
                url = Constants.GUOJI;
                break;
            case "娱乐":
                url = Constants.YULE;
                break;
            case "体育":
                url = Constants.TIYU;
                break;
            case "军事":
                url = Constants.JUNSHI;
                break;
            case "科技":
                url = Constants.KEJI;
                break;
            case "财经":
                url = Constants.CAIJING;
                break;
            case "时尚":
                url = Constants.SHISHANG;
                break;
        }

        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(5000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onFinished() {
                Log.d("ceshi", "onFinished");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("ceshi", cex.getMessage());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("ceshi", ex.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                Log.d("ceshi", result);
                /*成功获取数据 要把下拉刷新控件恢复初始化  并且隐藏*/
                listView.onRreshFinish(true);
                processData(result);
                /*解析并显示数据   具体显示方法 在这个方法里面执行  。。*/
            }
        });

    }  /*从网络获取部分数据*/

    private void getMoreDatefromNet() {
        /*xutil 异步加载网络get的方法*/
        switch (text) {
            case "头条":
                url = Constants.TOP;
                break;
            case "社会":
                url = Constants.SHEHUI;
                break;
            case "国内":
                url = Constants.GUONEI;
                break;
            case "国际":
                url = Constants.GUOJI;
                break;
            case "娱乐":
                url = Constants.YULE;
                break;
            case "体育":
                url = Constants.TIYU;
                break;
            case "军事":
                url = Constants.JUNSHI;
                break;
            case "科技":
                url = Constants.KEJI;
                break;
            case "财经":
                url = Constants.CAIJING;
                break;
            case "时尚":
                url = Constants.SHISHANG;
                break;
        }

        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(5000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onFinished() {
                Log.d("ceshi", "onFinished");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("ceshi", cex.getMessage());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("ceshi", ex.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                Log.d("MoreData", result);
                /*成功获取数据 要把上拉加载更多控件恢复初始化  并且隐藏*/
                processMoreData(result);
                /*解析并显示数据   具体显示方法 在这个方法里面执行  。。*/
                listView.onRreshFinish(false);
                Toast.makeText(mContext, "加载更多", Toast.LENGTH_SHORT).show();
                cishu++;
            }
        });
    }  /*从网络获取更多数据*/

    private void processData(String json) {
        NewsContentPagerBean bean = parsedJson(json);
        /*通过bean 中获取返回的数据   具体的数据填充在这里面执行*/

        dataBeanList = new ArrayList<>();/*用于轮播的新闻的数据*/
        for (int i = 0; i < 2; i++) {
            dataBeanList.add(bean.getResult().getData().get(i));
        }
        dataBeanAllList = new ArrayList<>();/*完全的新闻数据*/
        for (int k = 2; k < 12; k++) {
            dataBeanAllList.add(bean.getResult().getData().get(k));
        }
        pic_viewPager.setAdapter(new MyVallPagerAdapter());
        listadapter = new MyNewsListAdapter();
        listView.setAdapter(listadapter);
        addRedPoint();

    }  /*解析从网络获取的部分数据*/

    private void processMoreData(String json) {
        NewsContentPagerBean bean = parsedJson(json);
        /*通过bean 中获取返回的数据   具体的数据填充在这里面执行*/

        MoredataBeanList = new ArrayList<>();/*用于加载更多的新闻的数据*/
        for (int i = 13; i < 30; i++) {
            MoredataBeanList.add(bean.getResult().getData().get(i));
        }
        LargeSize = 30;
        dataBeanAllList.addAll(MoredataBeanList);
        listadapter.notifyDataSetChanged();

        if (hander == null) {
            hander = new MyViewPagerHander();
        }
        hander.removeCallbacksAndMessages(null);/*移除所有的消息和回调*/
        hander.postDelayed(new MyRunnable(), 3000);

    }  /*解析从网络获取的更多数据*/

    private void addRedPoint() {
    /*构建红点*/
        layout.removeAllViews();/*移除所有的点*/
        for (int j = 0; j < 2; j++) {
            ImageView imagePoint = new ImageView(mContext);
            imagePoint.setBackgroundResource(R.drawable.point_selector);
            imagePoint.setEnabled(false);/*都先设置为false 的图片*/
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(5), DensityUtil.dip2px(5));
            if (j != 0) {
                params.leftMargin = DensityUtil.dip2px(10);
            }
            /*设置间距  等等属性*/
            if (j == 0) {
                imagePoint.setEnabled(true);
            } else {
                imagePoint.setEnabled(false);
            }
            imagePoint.setLayoutParams(params);
            layout.addView(imagePoint);
        }
    }  /*增加轮播图片中的红点*/

    private class MyViewPagerHander extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = (pic_viewPager.getCurrentItem() + 1) % dataBeanList.size();/*为了图片轮播循环起来 0 +1 % 2 =1  1+1 %2 =0*/
            pic_viewPager.setCurrentItem(item);
            hander.removeCallbacksAndMessages(null);/*移除所有的消息和回调*/
            hander.postDelayed(new MyRunnable(), 3000);/*再次启动任务*/
        }
    }  /*自定义hander 实现图片轮播*/

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            hander.sendEmptyMessage(0);/*调用MyViewPagerHander 中的handleMessage()方法*/
        }
    } /*自定义Runnable 配合 自定义hander使用*/

    private NewsContentPagerBean parsedJson(String json) {
        /*使用gson解析*/
        return new Gson().fromJson(json, NewsContentPagerBean.class);
    } /*Gson解析Json 数据*/

    private class MyVallPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(R.drawable.home_scroll_default);
            Log.d("轮播图片", dataBeanList.get(position).getThumbnail_pic_s());
            Glide
                    .with(mContext)
                    .load(dataBeanList.get(position).getThumbnail_pic_s())
                    .centerCrop()
                    .placeholder(R.drawable.home_scroll_default)//默认的图片
                    .crossFade()
                    .into(imageView); //图片实例

            container.addView(imageView);
            if (position == 0) {
                title_text.setText(dataBeanList.get(0).getTitle());/*初始化画廊中的小标题*/
                hander = new MyViewPagerHander();/*先启动任务 防止空指针  未测试*/
            }
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:/*按下*/
                            hander.removeCallbacksAndMessages(null);/*移除轮播事件*/
                            break;
                        case MotionEvent.ACTION_MOVE:/*移动*/
                            break;
                        case MotionEvent.ACTION_UP:/*离开*/
                            hander.removeCallbacksAndMessages(null);
                            hander.postDelayed(new MyRunnable(), 3000);/*重新开始轮播事件*/
                            break;
                    }
                    return true;/*设置为true时候 取消点击事件*/
                }
            });
            return imageView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    } /*轮播viewpager 的适配器*/

    private class MyNewsListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataBeanAllList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_tab_detial_pager, null);
                viewholder = new ViewHolder();
                viewholder.Item_Image = (ImageView) convertView.findViewById(R.id.Item_Image);
                viewholder.Item_Time_text = (TextView) convertView.findViewById(R.id.Item_Time_text);
                viewholder.Item_Title_text = (TextView) convertView.findViewById(R.id.Item_Title_text);
                convertView.setTag(viewholder);/*将对象缓存*/
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            /*根据位置获取数据*/
            NewsContentPagerBean.ResultBean.DataBean dataBean = dataBeanAllList.get(position);
            Glide
                    .with(mContext)
                    .load(dataBean.getThumbnail_pic_s())
                    .centerCrop()
                    .placeholder(R.drawable.news_pic_default)//默认的图片
                    .crossFade()
                    .into(viewholder.Item_Image); //图片实例

            viewholder.Item_Time_text.setText(dataBean.getDate());
            viewholder.Item_Title_text.setText(dataBean.getTitle());


            String readArrayId = CacheUtils.getString(mContext, READ_ARRAY_UNIQUEKEY);//""->35111,dddd,
            if (readArrayId.contains(dataBean.getUniquekey() + "")) {
                //变成灰色
                viewholder.Item_Title_text.setTextColor(Color.LTGRAY);
            } else {
                //默认设置黑色
                viewholder.Item_Title_text.setTextColor(Color.BLACK);
            }

            return convertView;
        }
    } /*新闻列表 listview 的适配器*/

    private class MyVallOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            title_text.setText(dataBeanList.get(position).getTitle());
            /*标题的初始化   由于未在TabDetailpager 的构造方法中获取到新闻信息  于是在Viewpager 的
            * 适配器中通过判断当前当前的视图的位置 得到相应的标题*/

            /*设置红点的移动 */
            layout.getChildAt(PreSelectPosition).setEnabled(false);/*红点之前所在的位置  只需要将他int 出来*/
            layout.getChildAt(position).setEnabled(true);
            PreSelectPosition = position;/*将当前的位置设置为之前的位置  便于下一次的颜色改变*/
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            /*当轮播图片的touch 之后 定时任务会失效  在viewpager 的页面滚动状态改变之后  重新开启定时任务*/
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                hander.removeCallbacksAndMessages(null); /*拖拽状态  图片任务移除*/
                isDragging = true;/*拖拽状态改变*/
            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging == true) { /*空闲状态*/
                isDragging = false; /*防止频繁的调用轮播任务*/
                hander.removeCallbacksAndMessages(null);
                hander.postDelayed(new MyRunnable(), 3000);
            } else if (state == ViewPager.SCROLL_STATE_SETTLING && isDragging == true) { /*惯性状态*/
                isDragging = false; /*防止频繁的调用轮播任务*/
                hander.removeCallbacksAndMessages(null);
                hander.postDelayed(new MyRunnable(), 3000);
            }

        }
    } /*轮播Viewpager的页面改变监听器  用于红点的移动*/

    private class MyOnRefreshListener implements RefreshListView.OnRefreshListener {
        @Override
        public void onPullDownlRefresh() {
            getDatefromNet();/*下拉刷新的时候通过接口调用这个网络获取数据的放法*/
        }

        @Override
        public void onLoadMore() {
            if (cishu == 2) {
                Toast.makeText(mContext, "没有更多新闻", Toast.LENGTH_SHORT).show();
                listView.onRreshFinish(false);
            } else {
                getMoreDatefromNet();
            }
        }
    }  /*自定义下拉刷新的监听接口*/

    private class MyOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int RealPosition = position - 1;/*加了一个头  要减去1*/
            NewsContentPagerBean.ResultBean.DataBean bean = dataBeanAllList.get(RealPosition);
            String readArrayId = CacheUtils.getString(mContext, READ_ARRAY_UNIQUEKEY);
            if (!readArrayId.contains(bean.getUniquekey() + "")) {

                //1.保持
                CacheUtils.putString(mContext, READ_ARRAY_UNIQUEKEY, readArrayId + bean.getUniquekey() + ",");

                //2.刷新适配器
                listadapter.notifyDataSetChanged();
            }
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            String url = bean.getUrl();
            String title= bean.getTitle();
            intent.putExtra("url", url);
            intent.putExtra("title",title);
            mContext.startActivity(intent);
        }
    } /*新闻列表点击事件监听器*/

    static class ViewHolder {
        ImageView Item_Image;
        TextView Item_Title_text;
        TextView Item_Time_text;
    } /*RefreshListView 的子项缓存类*/


}
