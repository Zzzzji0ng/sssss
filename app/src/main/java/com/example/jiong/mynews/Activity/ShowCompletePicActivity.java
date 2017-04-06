package com.example.jiong.mynews.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.MynewsApplication;

public class ShowCompletePicActivity extends Activity {
    private PhotoView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MynewsApplication.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_complete_pic);
        img= (PhotoView) findViewById(R.id.img);
        img.enable();
        intPicDate();
    }

    private void intPicDate() {
        Glide
                .with(ShowCompletePicActivity.this)
                .load(getIntent().getStringExtra("imageurl"))
                .centerCrop()
                .placeholder(R.drawable.home_scroll_default)//默认的图片
                .crossFade()
                .into(img); //图片实例
    }
}
