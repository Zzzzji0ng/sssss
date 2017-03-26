package com.example.jiong.mynews.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.Constants;
import com.example.jiong.mynews.domain.PicsContentPagerBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class PictureDetailActivity extends Activity implements View.OnClickListener{
    private Button Change_Pic_type;
    private Button Refresh_pic;
    private ListView Pic_ListView;
    private String type;
    private String Url;/*请求地址*/
    private List<PicsContentPagerBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> PicDataBeanList;
    private int CONCURRENT_TYPE =0/*记录位置*/;
    private int temp=0; /*记录选定的位置*/
    /*private BitmapUtils bitmapUtils;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case NetCacheUtils.SUCCESS:  *//*请求图片成功*//*
                    Bitmap bitmap= (Bitmap) msg.obj;
                    int position =msg.arg1;
                    Log.d("TAG","图片请求成功"+position);
                    if (Pic_ListView!=null&&Pic_ListView.isShown()){
                        ImageView imageView= (ImageView) Pic_ListView.findViewWithTag(position);
                        if (imageView!=null && bitmap!=null){
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                    break;
                case NetCacheUtils.FAIL:   *//*请求图片失败*//*

                    break;
            }
        }
    };*/

    public PictureDetailActivity() {
        super();
        /*bitmapUtils=new BitmapUtils(handler);*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        type = intent.getStringExtra("TYPE");
        Log.d("textTag",type);
        initView();
        initPicDate(type);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Change_Pic_type:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("选择图片分类");
                String  [] items=new String []{"动漫壁纸","人物壁纸","写真艺术","自拍艺术","美食图片","动物图片","丝袜美女","纹身图片","影视剧照","美女车展"};
                builder.setSingleChoiceItems(items, CONCURRENT_TYPE, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        temp=which;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChangePicType(temp);
                    }
                }).show();
                break;
            case R.id.Refresh_pic:
                getPicDataFromNet(type);
                break;
        }
    }

    private void ChangePicType(int type) {
        String changeType=Constants.PictureCategory[type];
        initPicDate(changeType);
    }

    public View initView() {
        View view=View.inflate(this, R.layout.activity_picture_detail,null);
        Change_Pic_type= (Button) view.findViewById(R.id.Change_Pic_type);
        Refresh_pic= (Button) view.findViewById(R.id.Refresh_pic);
        Pic_ListView= (ListView) view.findViewById(R.id.Pic_ListView);
        setContentView(view);
        return view;
    }

    public void initPicDate(String type) {
        Change_Pic_type.setOnClickListener(this);
        Refresh_pic.setOnClickListener(this);
        getPicDataFromNet(type);
    }

    public void getPicDataFromNet(String type) {
        switch (type){
            case "动漫壁纸":
                Url= Constants.leixing1;
                break;
            case "人物壁纸":
                Url=Constants.leixing2;
                break;
            case "写真艺术":
                Url=Constants.leixing3;
                break;
            case "自拍艺术":
                Url=Constants.leixing4;
                break;
            case "美食图片":
                Url=Constants.leixing5;
                break;
            case "动物图片":
                Url=Constants.leixing6;
                break;
            case "丝袜美女":
                Url=Constants.leixing7;
                break;
            case "纹身图片":
                Url=Constants.leixing8;
                break;
            case "影视剧照":
                Url=Constants.leixing9;
                break;
            case "美女车展":
                Url=Constants.leixing10;
                break;
        }
        RequestParams params = new RequestParams(Url);
        params.setConnectTimeout(5000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processPicData(result);
                Log.d("pic_text","success pic");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.d("pic_text","finish pic");

            }
        });
    }

    private void processPicData(String Json) {
        /*在这里解析和展示数据*/
        PicsContentPagerBean bean = parsedJson(Json);
        PicDataBeanList=bean.getShowapi_res_body().getPagebean().getContentlist();
        Log.d("pic_text",PicDataBeanList.get(1).getTitle());
        Pic_ListView.setAdapter(new MyPicListAdapter());
        Pic_ListView.setOnItemClickListener(new MyOnItemClickListener());
    }

    private PicsContentPagerBean parsedJson(String json) {
        return new Gson().fromJson(json,PicsContentPagerBean.class);

    }


    private class MyPicListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return PicDataBeanList.size();
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
                convertView = View.inflate(PictureDetailActivity.this, R.layout.picture_item, null);
                viewholder = new ViewHolder();
                viewholder.image_view = (ImageView) convertView.findViewById(R.id.list_pic_image);
                viewholder.text_view = (TextView) convertView.findViewById(R.id.list_pic_title);
                convertView.setTag(viewholder);/*将对象缓存*/
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            PicsContentPagerBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistbean=PicDataBeanList.get(position);
            Log.d("pic_text",contentlistbean.getImg());
            viewholder.text_view.setText(contentlistbean.getTitle());

            //用 glide 获取图片
            Glide
                    .with(PictureDetailActivity.this)
                    .load(contentlistbean.getImg())
                    .centerCrop()
                    .placeholder(R.drawable.home_scroll_default)//默认的图片
                    .crossFade()
                    .into(viewholder.image_view); //图片实例

            /*自定义三级缓存图片*/
            /*viewholder.image_view.setTag(position);*//*设置tag  以便获得相应的实例*//*
            Bitmap bitmap=bitmapUtils.getBitmap(contentlistbean.getImg(),position); *//*这是在主线程*//*
            if (bitmap!=null){
                *//*因为在主线程  图片来源只能是内存或者是本地*//*
                viewholder.image_view.setImageBitmap(bitmap);
            }*/

            return convertView;
        }
    }
    static class ViewHolder{
        ImageView image_view;
        TextView text_view;
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(PictureDetailActivity.this,ShowCompletePicActivity.class);
            String title= PicDataBeanList.get(position).getTitle();
            String picurl=PicDataBeanList.get(position).getImg();
            intent.putExtra("TITLE",title);
            intent.putExtra("imageurl",picurl);
            startActivity(intent);
        }
    }
}
