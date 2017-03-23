package com.example.jiong.mynews.pager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    private AlertDialog alertDialog;
    private String[] array = {"手动清除缓存", "关于"};
    private String size;

    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public View iniView() {
        View view = View.inflate(mContext, R.layout.settinglayout, null);
        listview_setting = (ListView) view.findViewById(R.id.listview_setting);
        try {
             size =DataCleanManager.getCacheSize(mContext.getExternalCacheDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void iniData() {
        super.iniData();
        adapter = new BaseAdapter() {
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
                    switch (position) {
                        case 0:
                            simple_list_item.setText(array[position] + "           " + size+"M");
                            break;
                        case 1:
                            simple_list_item.setText(array[position]);
                            break;
                    }
                }
                return convertView;
            }
        };
        listview_setting.setAdapter(adapter);
        listview_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (alertDialog == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("确定要清除缓存吗？");
                            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "清除缓存", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setPositiveButton("取消", null);
                            builder.show();
                        }

                        break;
                    case 1:
                        Intent intent = new Intent(mContext, AboutActivity.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });
    }


}
