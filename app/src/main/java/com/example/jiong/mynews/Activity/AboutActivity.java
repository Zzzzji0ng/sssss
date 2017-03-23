package com.example.jiong.mynews.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jiong.mynews.R;

public class AboutActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        text= (TextView) findViewById(R.id.text);
        text.setText("此软件仅为学习使用"+"\n"+"                v1.0");
    }



}
