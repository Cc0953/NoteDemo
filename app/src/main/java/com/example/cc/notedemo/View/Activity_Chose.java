package com.example.cc.notedemo.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.cc.notedemo.R;

/**
 * Created by Cc on 2017/1/8.
 */

public class Activity_Chose extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chose);

        initView();
        onClick();
    }

    private void initView() {
        button = (Button) findViewById(R.id.returnButton);
    }
    private void onClick() {
        button.setOnClickListener(listener);
    }

    //手机返回键的监听函数
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(Activity_Chose.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Activity_Chose.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
