package com.example.jiong.mynews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.CacheUtils;

public class SplashActivity extends Activity {
    private RelativeLayout SplashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        SplashLayout = (RelativeLayout) findViewById(R.id.SplashLayout);
        /*旋转动画*/
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);/*动画完成后的状态*/
        /*渐变动画*/
        AlphaAnimation aa = new AlphaAnimation(0, 1);/*从0 到看得见*/
        aa.setFillAfter(true);
        /*缩放动画*/
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setFillAfter(true);
        /*一起播放*/
        AnimationSet set = new AnimationSet(false);/*不用函数控制*/
        set.addAnimation(sa);
        set.addAnimation(aa);
        set.addAnimation(ra);
        set.setDuration(3000);
        set.setFillAfter(true);
        SplashLayout.startAnimation(set);

        /*监听动画播放状态*/
        set.setAnimationListener(new MyAnimationlisener());
    }

    class MyAnimationlisener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Boolean iscomingMain = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
            /*获取进入状态*/
            if (!iscomingMain) {
                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
            /*关闭当前页面*/
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            /*关闭当前页面*/
                finish();
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
