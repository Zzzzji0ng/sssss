package com.example.jiong.mynews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.CacheUtils;
import com.example.jiong.mynews.Utils.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity implements View.OnClickListener {
    private ViewPager viewpager;
    private Button ButtonStart;
    private LinearLayout linearlayoutPoint;
    private ArrayList<ImageView> arrayList;
    private ImageView redpoint;
    private int MarginLeft;
    private int widthdp;
    public static String START_MAIN = "start_main";


    private void findViews() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ButtonStart = (Button) findViewById(R.id.Button_start);
        linearlayoutPoint = (LinearLayout) findViewById(R.id.linearlayout_point_L);
        redpoint = (ImageView) findViewById(R.id.red_point);
        /*红点华东的距离 等于 间距乘以屏幕滑动百分比
        * 间距 = 第一个点减掉地0个点的距离
        * 最终的坐标等于起始位置加上改变的位置*/
        ButtonStart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == ButtonStart) {
            /*保存已经引导过的标志*/
            CacheUtils.putBoolean(this, START_MAIN, true);
            CacheUtils.putBoolean(this,"Push_State",true);
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findViews();
        iniDate();

        //求间距
        //视图渲染-测量-指定位置-绘制
        redpoint.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());/*全局监听器*/

        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());/*页面改变监听器*/
    }

    private void iniDate() {
        /*调用单位转换工具*/
        widthdp = DensityUtil.dip2px(this, 10);


        /*数据的初始化*/
        int[] ids = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        arrayList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);/*设置背景*/
            imageView.setBackgroundResource(ids[i]);
            arrayList.add(imageView);

            /*形态创建灰色的点*/
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /*下面的单位是像素*/
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdp, widthdp);/*设置间距  先打下面的一行 再构建上面的二行*/
            if (i != 0) {
                params.leftMargin = 30;
            } /*间距*/
            point.setLayoutParams(params);
            linearlayoutPoint.addView(point);
        }
        /*用一个集合来装imageview  用for循环来遍历*/
        viewpager.setAdapter(new MyPagerAdapter());
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return arrayList.size();/*集合的大小*/
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = arrayList.get(position);
            container.addView(imageView);
            return imageView;
            /*构建一个图片空间 来显示当前位置所对应的图片  container将这控件添加进去*/
            /*最后将这个图片控件返回回去*/
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);  这个方法一定要注释掉
            container.removeView((View) object);
            /*在容器中将这个视图删除  视图用object强制装换为 view*/
        }


    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            /*间距 = 第一个点减掉地0个点的距离*/
            MarginLeft = linearlayoutPoint.getChildAt(1).getLeft() - linearlayoutPoint.getChildAt(0).getLeft();
            /*取消注册监听*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                redpoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }/*版本原因 需要加上if*/
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        /*滑动的时候调用  三个参数分别为 当前页面位置 滚动百分比 华东的像素*/
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            /*红点华东的距离 等于 间距乘以屏幕滑动百分比*/
            float leftmargin = MarginLeft * (positionOffset + position);/*还要加上起始位置*/
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redpoint.getLayoutParams();
            params.leftMargin = (int) leftmargin;
            redpoint.setLayoutParams(params);
        }

        @Override/*当前页面被选中是回调*/
        public void onPageSelected(int position) {
            if (position == arrayList.size() - 1) {
                ButtonStart.setVisibility(View.VISIBLE);
            } else {
                ButtonStart.setVisibility(View.INVISIBLE);
            }
        }

        @Override/*状态变化回调*/
        public void onPageScrollStateChanged(int state) {

        }
    }
}
