package com.example.jiong.mynews.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiong.mynews.Activity.MainActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.PhoneNetStates;
import com.example.jiong.mynews.base.BaseFragment;
import com.example.jiong.mynews.base.BasePager;
import com.example.jiong.mynews.pager.NewsMain;
import com.example.jiong.mynews.pager.SettingPager;
import com.example.jiong.mynews.pager.ThridNews;
import com.example.jiong.mynews.pager.secondNews;
import com.example.jiong.mynews.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Jiong on 2017/2/11.
 */
public class ContentFragment extends BaseFragment {
    private  Boolean netstate;
    @ViewInject(R.id.RG_mian)
    private RadioGroup radioGroup;
    /*实例化ridioGroup*/

    @ViewInject(R.id.ContentViewpager)
    private NoScrollViewPager viewPager;  /*消耗滑动事件  这里也改成NoScrollViewPager*/
    /*实例化  viewpager*/

    private TextView tv_titletext;
    private ImageButton im_menu;

    /*装继承basepager 的四个页面的集合*/
    private ArrayList<BasePager> basePagerArrayList;

    @Override
    public View intView() {
        View view = View.inflate(mContext, R.layout.contentfragmenu, null);
        x.view().inject(this, view);/*导入第三方包  先写出这一行 再实例化viewpager*/
        tv_titletext = (TextView) view.findViewById(R.id.tv_titletext);
        im_menu = (ImageButton) view.findViewById(R.id.im_menu);
        netstate = PhoneNetStates.isNetworkConnected(mContext);
        if (netstate){
            int netype =PhoneNetStates.GetNetype(mContext);
            if (netype==1){
                Toast.makeText(mContext,"当前使用wifi网络",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(mContext,"当前使用移动蜂窝网络",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext,"请检查网络开关",Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override/*添加数据*/
    protected void iniData() {
        super.iniData();
        basePagerArrayList = new ArrayList<>();
        basePagerArrayList.add(new NewsMain(mContext));
        basePagerArrayList.add(new secondNews(mContext));
        basePagerArrayList.add(new ThridNews(mContext));
        basePagerArrayList.add(new SettingPager(mContext));

        /*设置viewpager 的适配器*/
        viewPager.setAdapter(new ContentFragmentAdapter());
        /*监听radiogroup的点击事件*/
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        basePagerArrayList.get(0).iniData();
        /*进入应用后加载第一个视图*/

        /*默认选中的是首页按钮*/
        radioGroup.check(R.id.Button_Shouye);
        im_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                /*mActivity 实际上就是mainActivity 通过强壮得到他  才能得到侧滑菜单的实例*/
                SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
                slidingMenu.showMenu();
                /*设置左上角按钮的监听*/
            }
        });

        /*Xutil 3 这个包使用来提供  ridiobutton 和 viewpager 的关联的*/
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            /*点击那个页面加载对应的数据   这里用的是装放viewpager的数组的get（）方法*/
            /*这种情况在启动页面后不会加载数据  应在上面初始化第一页面的数据*/
            basePagerArrayList.get(position).iniData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.Button_Shouye:
                    viewPager.setCurrentItem(0, false);/*选择页面  后面的参数代表没有切换的效果*/
                    isEnableSlidingmenu(true);/*是否可以划出左侧菜单*/
                    tv_titletext.setText("新闻");
                    break;
                case R.id.Button_secondPage:
                    viewPager.setCurrentItem(1, false);
                    isEnableSlidingmenu(false);
                    tv_titletext.setText("图组");
                    /*basePagerArrayList.get(1).iniData();*/
                    break;
                case R.id.Button_thridPage:
                    viewPager.setCurrentItem(2, false);
                    isEnableSlidingmenu(false);
                    tv_titletext.setText("视频");
                    break;
                case R.id.Button_Setting:
                    viewPager.setCurrentItem(3, false);
                    isEnableSlidingmenu(false);
                    tv_titletext.setText("设置");
                    break;

            }
        }
    }

    private void isEnableSlidingmenu(Boolean isEnableSlidingmenu) {
        /*Ctrl alt t 快速将选定的代码加上if else*/
        MainActivity mainActivity = (MainActivity) mContext;
        /*mActivity 实际上就是mainActivity 通过强壮得到他  才能得到侧滑菜单的实例*/
        SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
        if (isEnableSlidingmenu) {
           /*可以从左侧滑动调出菜单*/
            slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
        /*通过 Ctrl alt M 快速提取目标代码变为方法*/
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    class ContentFragmentAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return basePagerArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = basePagerArrayList.get(position);
            /*basepager 本身不是视图  但是他的内部有视图 通过basepager.rootview 来获取一个使视图
            * 将他返回*/
            View view = basePager.rootview;
            /*basePager.iniData();*//*若不在这里初始化数据 则不会更新数据*/
            /*viewpager 具有预加载功能  将初始化数据移动到当每个页面被选中的时候在调用  这样能够节省用户的流量*/
            /*需要设置viewpager的页面状态监听*/
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            /*super.destroyItem(container, position, object);*/
            container.removeView((View) object);
        }
    }
}
