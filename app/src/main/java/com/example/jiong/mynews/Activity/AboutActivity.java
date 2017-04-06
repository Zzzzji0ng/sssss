package com.example.jiong.mynews.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jiong.mynews.R;

public class AboutActivity extends AppCompatActivity {
    private TextView text;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        text = (TextView) findViewById(R.id.text);
        version= (TextView) findViewById(R.id.version);
        text.setText("此软件仅为学习使用");
        version.setText("V 1.0");
    }


}
