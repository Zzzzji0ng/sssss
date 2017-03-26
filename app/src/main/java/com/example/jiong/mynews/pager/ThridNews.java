package com.example.jiong.mynews.pager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jiong.mynews.Activity.VideoPlayActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.Constants;
import com.example.jiong.mynews.base.BasePager;
import com.example.jiong.mynews.domain.VideoContentPagerBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Jiong on 2017/2/12.
 */
public class ThridNews extends BasePager implements View.OnClickListener{
    private LinearLayout viedo_gridview_layout; /*双排视图的父布局  默认显示*/
    private GridView video_gridView;
    private Button button_morevideo;
    private Button Button_Refresh_video;
    private LinearLayout viedo_listview_layout; /*单排视图的父部分 默认隐藏*/
    private ListView video_listView;
    private String questURL; /*请求地址*/
    private int page=1;/*页数计数器*/
    private int page2=50;/*listview 从第50 页开始获得数据*/
    public static final String TYPE1="GRIDLIST";
    public static final String TYPE2="LISTVIEW";
    private View Footview;
    private List<VideoContentPagerBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist;
    /*private VideoBitmapUtils videobitmapUtils;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (viedo_gridview_layout.getVisibility()==View.VISIBLE){
                switch (msg.what){
                    case NetCacheUtils.SUCCESS:  *//*请求图片成功*//*
                        Bitmap bitmap= (Bitmap) msg.obj;
                        int position =msg.arg1;
                        Log.d("TAG","图片请求成功"+position);
                        if (video_gridView!=null&&video_gridView.isShown()){
                            ImageView imageView= (ImageView) video_gridView.findViewWithTag(position);
                            if (imageView!=null && bitmap!=null){
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                        break;
                    case NetCacheUtils.FAIL:   *//*请求图片失败*//*

                        break;
                }

            }else if (viedo_listview_layout.getVisibility()==View.VISIBLE){
                switch (msg.what){
                    case NetCacheUtils.SUCCESS:  *//*请求图片成功*//*
                        Bitmap bitmap= (Bitmap) msg.obj;
                        int position =msg.arg1;
                        Log.d("TAG","图片请求成功"+position);
                        if (video_listView!=null&&video_listView.isShown()){
                            ImageView imageView= (ImageView) video_listView.findViewWithTag(position);
                            if (imageView!=null && bitmap!=null){
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                        break;
                    case NetCacheUtils.FAIL:   *//*请求图片失败*//*

                        break;
                }
            }
        }
    };*/
    public ThridNews(Context context) {
        super(context);
        /*videobitmapUtils=new VideoBitmapUtils(mContext,handler);*/
    }

    @Override
    public View iniView() {
        View view=View.inflate(mContext, R.layout.videopager,null);
        viedo_gridview_layout= (LinearLayout) view.findViewById(R.id.viedo_gridview_layout);
        video_gridView= (GridView) view.findViewById(R.id.video_gridView);
        button_morevideo= (Button) view.findViewById(R.id.button_morevideo);
        Button_Refresh_video= (Button) view.findViewById(R.id.Button_Refresh_video);
        viedo_listview_layout= (LinearLayout) view.findViewById(R.id.viedo_listview_layout);
        video_listView= (ListView) view.findViewById(R.id.video_listView);
        return view;
    }

    @Override
    public void iniData() {
        super.iniData();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        Log.d("TAG",dateString);
        questURL= Constants.VideoDataUrlFirst+Constants.VideoDataUrlSecond+dateString+Constants.VideoDataUrlThird+Constants.VideoDataUrlForce;
        Log.d("TAG",questURL);
        getVideoDataFromNet(questURL,TYPE1);
        video_gridView.setOnItemClickListener(new MyVideoGridviewListener());
        button_morevideo.setOnClickListener(this);
        Button_Refresh_video.setOnClickListener(this);
    }



    private void getVideoDataFromNet(String questURL,final String type) {
        RequestParams patams =new RequestParams(questURL);
        patams.setConnectTimeout(5000);
        x.http().get(patams, new Callback.CommonCallback<String>() {
            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(String result) {
                if (type==TYPE1){
                    if (result.contains("\"showapi_res_code\":-1005")){
                        Toast.makeText(mContext,"请检查是否为北京时间",Toast.LENGTH_SHORT).show();
                        onFinished();
                    }
                    processData(result);
                }else if (type==TYPE2){
                    processDatalist(result);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }

    private void processData(String result) {
        VideoContentPagerBean bean=parsedJson(result);
        contentlist=bean.getShowapi_res_body().getPagebean().getContentlist();
        video_gridView.setAdapter(new MyGirdviewAdapter());
    }
    private void processDatalist(String result){
        VideoContentPagerBean bean=parsedJson(result);
        contentlist=bean.getShowapi_res_body().getPagebean().getContentlist();
        Footview =View.inflate(mContext,R.layout.button_refresh_video,null);
        Button bigmore= (Button) Footview.findViewById(R.id.Button_more_video_big);
        video_listView.addFooterView(Footview);
        video_listView.setAdapter(new MyListviewAdapter());
        video_listView.setOnItemClickListener(new MyVideoGridviewListener());
        bigmore.setOnClickListener(this);
    }

    private VideoContentPagerBean parsedJson(String result) {
        return new Gson().fromJson(result,VideoContentPagerBean.class);
    }

    private class MyGirdviewAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return contentlist.size();
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
            ViewHolder viewHolder;
            if (convertView==null){
                convertView=View.inflate(mContext,R.layout.videogridviewresource,null);
                viewHolder=new ViewHolder();
                viewHolder.videoImage= (ImageView) convertView.findViewById(R.id.videoImage);
                viewHolder.headImage= (ImageView) convertView.findViewById(R.id.headImage);
                viewHolder.videoTitle= (TextView) convertView.findViewById(R.id.videoTitle);
                viewHolder.videoAuthor= (TextView) convertView.findViewById(R.id.videoAuthor);
                viewHolder.love= (TextView) convertView.findViewById(R.id.love);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            /*根据位置获得相应的数据*/
            VideoContentPagerBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentbean=contentlist.get(position);
            Glide
                    .with(mContext)
                    .load(contentbean.getProfile_image())
                    .centerCrop()
                    .placeholder(R.drawable.news_pic_default)//默认的图片
                    .crossFade()
                    .into(viewHolder.headImage); //图片实例
            /*视频缩略图还未解决*/
            viewHolder.videoImage.setImageResource(R.drawable.default_player);
            /*viewHolder.videoImage.setTag(position);*//*设置tag  以便获得相应的实例*/
            /*Bitmap bitmap= BitmapUtils.getBitmap(contentbean.getVideo_uri(),position); *//*这是在主线程*//*
            if (bitmap!=null){
                *//*因为在主线程  图片来源只能是内存或者是本地*//*
                viewHolder.videoImage.setImageBitmap(bitmap);
            }*/


            viewHolder.love.setText(contentbean.getLove()+"赞");
            String text=contentbean.getText().replace(" ","");
            viewHolder.videoTitle.setText(text);
            viewHolder.videoAuthor.setText(contentbean.getName());

            return convertView;
        }
    }
    static class ViewHolder{
        private ImageView videoImage;
        private TextView videoTitle;
        private TextView videoAuthor;
        private TextView love;
        private ImageView headImage;
    }


    private class MyVideoGridviewListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String text=contentlist.get(position).getVideo_uri();
            String title=contentlist.get(position).getText();
            Intent intent=new Intent(mContext, VideoPlayActivity.class);
            intent.putExtra("video_url",text);
            intent.putExtra("video_title",title);
            mContext.startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        switch (v.getId()){
            case R.id.button_morevideo:
                viedo_gridview_layout.setVisibility(View.GONE);
                button_morevideo.setVisibility(View.GONE);
                Button_Refresh_video.setVisibility(View.GONE);
                viedo_listview_layout.setVisibility(View.VISIBLE);
                video_listView.setVisibility(View.VISIBLE);
                int j=page2;
                questURL= Constants.VideoDataUrlFirst+j+Constants.VideoDataUrlSecond+dateString+Constants.VideoDataUrlThird+Constants.VideoDataUrlForce;
                getVideoDataFromNet(questURL,TYPE2);
                page2++;
                /*listview显示数据*/
                break;
            case R.id.Button_Refresh_video:
                int i=page+1;
                questURL= Constants.VideoDataUrlFirst+i+Constants.VideoDataUrlSecond+dateString+Constants.VideoDataUrlThird+Constants.VideoDataUrlForce;
                getVideoDataFromNet(questURL,TYPE1);
                /*video_gridView.setAdapter(new MyGirdviewAdapter());*/
                page++;
                break;
            case R.id.Button_more_video_big:
                int k=page2;
                questURL= Constants.VideoDataUrlFirst+k+Constants.VideoDataUrlSecond+dateString+Constants.VideoDataUrlThird+Constants.VideoDataUrlForce;
                getVideoDataFromNet(questURL,TYPE2);
                page2++;
                video_listView.removeFooterView(Footview);
                break;
        }
    }

    private class MyListviewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return contentlist.size();
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
            listviewViewHolder listviewViewHolder;
            if (convertView==null){
                listviewViewHolder=new listviewViewHolder();
                convertView=View.inflate(mContext,R.layout.videolistviewresource,null);
                listviewViewHolder.list_videoImage=(ImageView) convertView.findViewById(R.id.list_videoImage);
                listviewViewHolder.headImage= (ImageView) convertView.findViewById(R.id.headImage);
                listviewViewHolder.list_videoAuthor= (TextView) convertView.findViewById(R.id.list_videoAuthor);
                listviewViewHolder.list_videoTime= (TextView) convertView.findViewById(R.id.list_videoTime);
                listviewViewHolder.list_videoTitle= (TextView) convertView.findViewById(R.id.list_videoTitle);
                convertView.setTag(listviewViewHolder);
            }else {
                listviewViewHolder= (ThridNews.listviewViewHolder) convertView.getTag();
            }
            VideoContentPagerBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentbean=contentlist.get(position);
            Glide
                    .with(mContext)
                    .load(contentbean.getProfile_image())
                    .centerCrop()
                    .placeholder(R.drawable.news_pic_default)//默认的图片
                    .crossFade()
                    .into(listviewViewHolder.headImage); //图片实例
            /*视频缩略图还未解决*/
            listviewViewHolder.list_videoImage.setImageResource(R.drawable.default_player);
            /*listviewViewHolder.list_videoImage.setTag(position);*//*设置tag  以便获得相应的实例*//*
                Bitmap bitmap= videobitmapUtils.getBitmap(mContext,contentbean.getVideo_uri(),position,TYPE1); *//*这是在主线程*//*
                if (bitmap!=null){
                *//*因为在主线程  图片来源只能是内存或者是本地*//*
                listviewViewHolder.list_videoImage.setImageBitmap(bitmap);
            }*/



            /*缩略图部分   使用自定义的三级缓存*/
            String text=contentbean.getText().replace(" ","");
            listviewViewHolder.list_videoTitle.setText(text);
            listviewViewHolder.list_videoTime.setText(contentbean.getCreate_time());
            listviewViewHolder.list_videoAuthor.setText(contentbean.getName());
            return convertView;
        }
    }
    static class listviewViewHolder{
        private ImageView list_videoImage;
        private ImageView headImage;
        private TextView list_videoAuthor;
        private TextView list_videoTime;
        private TextView list_videoTitle;
    }
}
