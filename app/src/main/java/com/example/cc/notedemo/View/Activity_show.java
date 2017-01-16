package com.example.cc.notedemo.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cc.notedemo.R;

/**
 * Created by Cc on 2017/1/16.
 */

public class Activity_show extends Activity {

    private TextView textView,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_massage);
        initView();
        acceptTitle();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        textView = (TextView) findViewById(R.id.ShowTitle);
        textView1 = (TextView) findViewById(R.id.ShowContent);
    }

    /**
     * 接受Intent传递的数据
     * ShowTitle
     */
    private void acceptTitle() {
        Intent intent = getIntent();
        String acceptString = intent.getStringExtra("ShowTitle");
        textView.setText(acceptString);
    }
}
