package com.example.jiong.mynews.pager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.jiong.mynews.Activity.AboutActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.CacheUtils;
import com.example.jiong.mynews.Utils.DataCleanManager;
import com.example.jiong.mynews.base.BasePager;

/**
 * Created by Jiong on 2017/2/12.
 */
public class SettingPager extends BasePager {
    private ListView listview_setting;
    private BaseAdapter adapter;
    private String[] array = {"手动清除缓存", "是否开启要闻推送?","关于"};
    private String s;
    private Boolean PushState;

    public SettingPager(Context context) {
        super(context);
        PushState=CacheUtils.getBoolean(mContext,"Push_State");
    }

    @Override
    public View iniView() {
        View view = View.inflate(mContext, R.layout.settinglayout, null);
        listview_setting = (ListView) view.findViewById(R.id.listview_setting);
        return view;
    }

    @Override
    public void iniData() {
        super.iniData();
        getCacheSize();
        adapter = new MyBaseAdapter();
        listview_setting.setAdapter(adapter);
        listview_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                switch (position) {
                    case 0:
                        /*清除数据缓存*/
                        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                        builder.setMessage("确定要清除缓存?");
                        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataCleanManager.clearAllCache(mContext);
                                getCacheSize();
                                listview_setting.setAdapter(adapter);
                                Toast.makeText(mContext,"清除成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setPositiveButton("取消",null);
                        builder.setCancelable(true);
                        builder.show();
                        break;
                    case 2:
                        Intent intent = new Intent(mContext, AboutActivity.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });
    }

    private void getCacheSize() {
        try {
            s = DataCleanManager.getTotalCacheSize(mContext);
            Log.d("TAG",s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyBaseAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return array.length;
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
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.simpleitem, null);
                TextView simple_list_item = (TextView) convertView.findViewById(R.id.simple_list_item);
                TextView size= (TextView) convertView.findViewById(R.id.size);
                ToggleButton push_button= (ToggleButton) convertView.findViewById(R.id.push_button);
                switch (position) {
                    case 0:
                        if (s!=null){
                            simple_list_item.setText(array[position]);
                            size.setText(s);
                        }
                        break;
                    case 1:
                        if (PushState==true){
                            push_button.setChecked(true);
                        }else {
                            push_button.setChecked(false);
                        }
                        simple_list_item.setText("是否开启推送?");
                        push_button.setVisibility(View.VISIBLE);
                        push_button.setBackgroundResource(R.drawable.toggle_button);
                        push_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked){
                                    //点击状态
                                    Toast.makeText(mContext,"要闻推送已开启",Toast.LENGTH_SHORT).show();
                                    CacheUtils.putBoolean(mContext,"Push_State",true);
                                }else {
                                    //微电机状态
                                    Toast.makeText(mContext,"要闻推送已关闭",Toast.LENGTH_SHORT).show();
                                    CacheUtils.putBoolean(mContext,"Push_State",false);
                                }
                            }
                        });

                    case 2:
                        simple_list_item.setText(array[position]);
                        break;
                }
            }
            return convertView;
        }
    }

}
