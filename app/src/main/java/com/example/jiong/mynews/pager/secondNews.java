package com.example.jiong.mynews.pager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.jiong.mynews.Activity.PictureDetailActivity;
import com.example.jiong.mynews.R;
import com.example.jiong.mynews.Utils.Constants;
import com.example.jiong.mynews.base.BasePager;

/**
 * Created by Jiong on 2017/2/12.
 */
public class secondNews extends BasePager{
    private GridView gridView;
    private int[] pictureArray = {R.drawable.button_1, R.drawable.button_2, R.drawable.button_3, R.drawable.button_4, R.drawable.button_5,
            R.drawable.button_6, R.drawable.button_7, R.drawable.button_8, R.drawable.button_9, R.drawable.button_10};


    public secondNews(Context context) {
        super(context);
    }

    @Override
    public View iniView() {
        View view = View.inflate(mContext, R.layout.pic_select_pager, null);
        gridView = (GridView) view.findViewById(R.id.gridView);
        return view;
    }

    @Override
    public void iniData() {
        super.iniData();
        gridView.setAdapter(new MyGridAdapter());
        gridView.setOnItemClickListener(new MyOnClickListener());
    }


    private class MyGridAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return pictureArray.length;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                convertView=View.inflate(mContext,R.layout.convertvier_picture,null);
                viewHolder=new ViewHolder();
                viewHolder.imageView= (ImageView) convertView.findViewById(R.id.picture_select_item);
                convertView.setTag(viewHolder);
            }else {
                viewHolder=(ViewHolder)convertView.getTag();
            }
            viewHolder.imageView.setImageResource(pictureArray[position]);

            return convertView;
        }
    }


    static class ViewHolder{
        private ImageView imageView;
    }


    private class MyOnClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(mContext,PictureDetailActivity.class);
            intent.putExtra("TYPE", Constants.PictureCategory[position]);
            mContext.startActivity(intent);
        }
    }


}
