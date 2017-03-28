package com.example.jiong.mynews.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiong.mynews.Activity.MainActivity;
import com.example.jiong.mynews.DetailPager.TabDetailPager;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.Constants;
import com.example.jiong.mynews.base.BasePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiong on 2017/2/12.
 */
/*这是新闻主页面*/
public class NewsMain extends BasePager {

    private List<TabDetailPager> list; /*保存TabDetailPager的集合*/
    @ViewInject(R.id.news_Tab_viewpager)
    private ViewPager news_Tab_viewpager;
    /*实例化这个内部的viewpager*/
    @ViewInject(R.id.News_Tab_indicator)
    private TabPageIndicator tabPageIndicator;
    /*实例化tabPageIndicator*/

    public NewsMain(Context context) {
        super(context);
    }

    @Override
    public View iniView() {
        View view=View.inflate(mContext,R.layout.news_detail_pager,null);
        x.view().inject(this,view);
        /*将view 实例化*/
        return view;
    }

    @Override
    public void iniData() {
        super.iniData();
        /*初始化10个Tab页面 */
        list=new ArrayList<>();
        for (int i=0;i< Constants.NewsCategory.length;i++){
            TabDetailPager tabDetailPager=new TabDetailPager(mContext,Constants.NewsCategory[i]);
            list.add(tabDetailPager);
        }
        news_Tab_viewpager.setAdapter(new MyPagerAdapter());
        news_Tab_viewpager.setOffscreenPageLimit(10);
        /*设置TabDetailPager回收问题*/
        tabPageIndicator.setViewPager(news_Tab_viewpager);
        /*绑定viewpager  以后监听页面的变化  用TabPageindicator实现*/
        tabPageIndicator.setOnPageChangeListener(new MyOnPageChangeListener());
        /*设置页面改变监听*/

    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            /*只有在第一个页签的位置可以拉出左划菜单  提升体验*/
            if (position==0){
                isEnableSlidingmenu(true);
            }else {
                isEnableSlidingmenu(false);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private void isEnableSlidingmenu(Boolean isEnableSlidingmenu) {
        /*Ctrl alt t 快速将选定的代码加上if else*/
        MainActivity mainActivity = (MainActivity) mContext;
        /*mActivity 实际上就是mainActivity 通过强壮得到他  才能得到侧滑菜单的实例*/
        SlidingMenu slidingMenu=mainActivity.getSlidingMenu();
        if (isEnableSlidingmenu) {
           /*可以从左侧滑动调出菜单*/
            slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
        /*通过 Ctrl alt M 快速提取目标代码变为方法*/
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
    class MyPagerAdapter extends PagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            return  Constants.NewsCategory[position];
        }
        /*这个方法用于返回Tab的标题*/

        @Override
        public int getCount() {
            return Constants.NewsCategory.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /*return super.instantiateItem(container, position);*/
            TabDetailPager tabDetailPager=list.get(position);
            View rootview=tabDetailPager.rootView;
            container.addView(rootview);
            tabDetailPager.initData();
            /*一定要调用这个方法 不然没有初始化  无法显示数据  在这里调用方法能够在滑到相应的页签
            * */
            return rootview;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            /*super.destroyItem(container, position, object);*/
            container.removeView((View) object);
        }
    }

}
