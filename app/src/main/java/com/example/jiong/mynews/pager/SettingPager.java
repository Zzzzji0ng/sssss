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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiong.mynews.Activity.AboutActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.DataCleanManager;
import com.example.jiong.mynews.base.BasePager;

/**
 * Created by Jiong on 2017/2/12.
 */
public class SettingPager extends BasePager {
    private ListView listview_setting;
    private BaseAdapter adapter;
    private String[] array = {"手动清除缓存", "关于"};
    private String s;

    public SettingPager(Context context) {
        super(context);
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
                    case 1:
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
                switch (position) {
                    case 0:
                        if (s!=null){
                            simple_list_item.setText(array[position]);
                            size.setText(s);
                        }
                        break;
                    case 1:
                        simple_list_item.setText(array[position]);
                        break;
                }
            }
            return convertView;
        }
    }

}
