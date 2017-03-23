package com.example.jiong.mynews.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.MynewsApplication;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class NewsDetailActivity extends Activity implements View.OnClickListener {
    private ImageButton ib_back;
    private ImageButton ib_textsize;
    private ImageButton ib_share;
    private WebView news_webview;
    private ProgressBar pb_status_down;
    private WebSettings settings;
    private String url;
    private String title;
    private int temp = 1;/*用来记录dialog选择的位置  若没有确定不生效*/
    private int TEXTSIZE = 1;/*dialog默认的位置*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        MynewsApplication.getInstance().addActivity(this);
        initView();
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        Log.d("webUrl", url);
        settings = news_webview.getSettings();/*获得设置器*/
        /*settings.setJavaScriptEnabled(true);*//*支持JS*/
        /*settings.setUseWideViewPort(true);*//*双击变大*/
        settings.setTextZoom(100);/*设置字体大小  默认100*/
        /*settings.setBuiltInZoomControls(true);*//*增加缩放按钮*/
        news_webview.setWebViewClient(new WebViewClient() {
            @Override/*重写页面加载完成后进度条小时*/
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb_status_down.setVisibility(View.GONE);
            }
        });
        news_webview.loadUrl(url);
    }

    private void initView() {
        setContentView(R.layout.activity_news_detail);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_textsize = (ImageButton) findViewById(R.id.ib_textsize);
        ib_share = (ImageButton) findViewById(R.id.ib_share);
        news_webview = (WebView) findViewById(R.id.news_webview);
        pb_status_down = (ProgressBar) findViewById(R.id.pb_status_down);
        ib_back.setOnClickListener(this);
        ib_textsize.setOnClickListener(this);
        ib_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_textsize:
                ShowChangeTextSizeDialog();
                break;
            case R.id.ib_share:
                showShare(title, url);
                break;
        }
    }

    private void ShowChangeTextSizeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置文字大小");
        String[] items = new String[]{"大字体", "标准字体", "小字体"};
        /*TEXTSIZE 默认的位置*/
        builder.setSingleChoiceItems(items, TEXTSIZE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temp = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ChangeTextSize(temp);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();/*记得加上  不然不显示*/

    }

    private void ChangeTextSize(int size) {
        switch (size) {
            case 0:
                settings.setTextZoom(130);
                break;
            case 1:
                settings.setTextZoom(100);
                break;
            case 2:
                settings.setTextZoom(70);
                break;
        }
    }

    private void showShare(String title, String url) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("分享新闻-来自早读新闻");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(" ");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(url);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(this);
    }
}
