package com.example.jiong.mynews.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.example.jiong.mynews.Fragment.ContentFragment;
import com.example.jiong.mynews.Fragment.LeftFragment;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.DensityUtil;
import com.example.jiong.mynews.Utils.MynewsApplication;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final String LEFTMENU_TAG = "leftmenu_tag";
    public static final String CONTENTNEMU_TAG = "contentnemu_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MynewsApplication.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        /*虚拟键会将最后下面覆盖了 在oncreate（）中加入这句  就不会覆盖 */
        super.onCreate(savedInstanceState);
        initSlidingMenu();/*  选定代码 Ctrl alt M 快速提取方法*/
        iniFragment();/*更换碎片*/

    }

    private void iniFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.leftmenuFragment, new LeftFragment(), LEFTMENU_TAG);
        ft.replace(R.id.ContentLayout, new ContentFragment(), CONTENTNEMU_TAG);
        ft.commit();
    }


    private void initSlidingMenu() {
        /*设置主页面*/
        setContentView(R.layout.activity_main);
        /*设置左侧菜单*/
        setBehindContentView(R.layout.leftmenu);
        SlidingMenu slidingMenu = getSlidingMenu();/*获得实例*/
        /*设置滑动模式  当前为全屏滑动*/
        slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
        /*设置视图模式  左侧菜单加主页  主页加上右侧菜单  左加右 加菜单*/
        slidingMenu.setMode(SlidingMenu.LEFT);
        /*设置主页面占得宽度*/
        slidingMenu.setBehindOffset(DensityUtil.dip2px(this, 200));
    }
}
