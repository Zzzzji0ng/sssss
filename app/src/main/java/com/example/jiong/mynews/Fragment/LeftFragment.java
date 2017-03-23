package com.example.jiong.mynews.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jiong.mynews.Activity.CitySelectorActivity;
import com.example.jiong.mynews.Activity.WeatherDetailActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Service.MyService;
import com.example.jiong.mynews.Utils.CacheUtils;
import com.example.jiong.mynews.Utils.MynewsApplication;
import com.example.jiong.mynews.base.BaseFragment;

/**
 * Created by Jiong on 2017/2/11.
 */

/*左侧菜单*/
public class LeftFragment extends BaseFragment implements View.OnClickListener{
    private ImageView left_weather_image;
    private TextView left_weather_city;
    private TextView left_weather_wendu;
    private Button left_finish_button;
    private LinearLayout left_weather_layout;
    private AlertDialog alertDialog;
    private RelativeLayout leftmenuFragment;
    @Override
    public View intView() {
        View view=View.inflate(mContext, R.layout.leftmenu,null);
        leftmenuFragment= (RelativeLayout) view.findViewById(R.id.leftmenuFragment);
        left_weather_image= (ImageView) view.findViewById(R.id.left_weather_image);
        left_weather_layout= (LinearLayout) view.findViewById(R.id.left_weather_layout);
        left_weather_city= (TextView) view.findViewById(R.id.left_weather_city);
        left_weather_wendu= (TextView) view.findViewById(R.id.left_weather_wendu);
        left_finish_button= (Button) view.findViewById(R.id.left_finish_button);
        return view ;
    }

    @Override
    protected void iniData() {
        Intent intent=new Intent(mContext, MyService.class);
        mContext.startService(intent);
        super.iniData();
        /* 选择 地区后台查询天气  返回数据填充  */
        getTodayWeather();
        /*设置监听器*/
        left_finish_button.setOnClickListener(this);
        left_weather_layout.setOnClickListener(this);
    }

    private void getTodayWeather() {
        String weatherInfo = CacheUtils.getString(mContext,"weatherState");
        if (!TextUtils.isEmpty(weatherInfo)){
            String [] array=weatherInfo.split(",");
            left_weather_city.setText(array[0]);
            left_weather_wendu.setText(array[1]);
            left_weather_image.setImageResource(selectPic(array[2]));
            changeTextColor(left_weather_city,array[2]);
            changeTextColor(left_weather_wendu,array[2]);
            leftmenuFragment.setBackgroundResource(Picbackground(array[2]));
        }else {
            left_weather_city.setText("请选择城市");
        }
    }

    private void changeTextColor(TextView textWhere, String s) {

        if ("晴".equals(s)||"多云".equals(s)){
            textWhere.setTextColor(Color.WHITE);
        }else if ("阴".equals(s)|s.contains("雨")|s.contains("雷")|s.contains("霾")){
            textWhere.setTextColor(Color.WHITE);
        }else if (s.contains("雪")){
            textWhere.setTextColor(Color.BLACK);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_finish_button:
                showAlertDialog ();
                break;
            case R.id.left_weather_layout:
                /*应在这里加入判断  是否添加过城市 */
                String values= CacheUtils.getString(mContext,"isSelectedCity");
                Intent intent=null;
                if (!TextUtils.isEmpty(values)){
                    intent=new Intent(mContext, WeatherDetailActivity.class);
                }else {
                    intent=new Intent(mContext, CitySelectorActivity.class);
                }
                if (intent!=null){
                    mContext.startActivity(intent);
                }

                break;
        }
    }
    private void showAlertDialog (){
        if (alertDialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
            builder.setMessage("确定退出?");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MynewsApplication.getInstance().exit();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }

    private int selectPic(String s){
        if ("晴".equals(s)){
            return R.drawable.sunny;
        }else if ("阴".equals(s)){
            return R.drawable.overcast;
        }else if ("多云".equals(s)){
            return R.drawable.partlycloudy;
        }else if (s.contains("雷")){
            return R.drawable.thundershower;
        }else if (s.contains("雨")){
            return R.drawable.rain;
        }else if (s.contains("霾")){
            return R.drawable.sandstorm;
        }else if (s.contains("雪")){
            return R.drawable.snow;
        }else {
            return R.drawable.sunny;
        }
    }

    private int Picbackground(String s){
        if ("晴".equals(s)||"多云".equals(s)){
            return R.drawable.qing_leftback;
        }else if ("阴".equals(s)||s.contains("雨")||s.contains("雷")||s.contains("霾")){
            return R.drawable.left_back;
        }else if (s.contains("雪")){
            return R.drawable.snow_leftback;
        }else {
            return R.drawable.left_back;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getTodayWeather();
    }
}
