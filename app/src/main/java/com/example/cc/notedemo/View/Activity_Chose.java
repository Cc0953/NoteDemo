package com.example.cc.notedemo.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.cc.notedemo.R;

/**
 * Created by Cc on 2017/1/8.
 */

public class Activity_Chose extends Activity {

    private Button button1,button2;
    private EditText editText1,editText2;
    private SharedPreferences.Editor editor,editor1;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_massage);

        initView();
        onClick();
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.returnButton);
        button2 = (Button) findViewById(R.id.saveButton);
        editText1 = (EditText) findViewById(R.id.titleEditTest);
        editText2 = (EditText) findViewById(R.id.contentEditTest);
    }
    private void onClick() {
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener1);
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
            Intent(Activity_Chose.this,MainActivity.class);
            finish();
        }
    };
    /**
     * Intent 跳转函数
     */
    private void Intent(Context a, Class<?> b)
    {
        Intent intent = new Intent(a,b);
        startActivity(intent);
    }
    /**
     * 保存键的点击事件
     * 功能：保存标题的内容并在手机中生成一个文件
     * sharePerfences
     * Intent
     */
    View.OnClickListener listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        if (v.getId()==R.id.saveButton){
            /**
             * 标题的sharedPreferences
             */
            preferences = getSharedPreferences("Title_Work",MODE_PRIVATE);
            String LastString = preferences.getString("title_work","WelcomeNote");
            String content = editText1.getText().toString()+LastString;
            editor = getSharedPreferences("Title",MODE_PRIVATE).edit();
            editor.putString("title",content);
            editor.apply();
            /**
             * 内容的sharedPerferences
             */

            /**
             * 结束吐丝，跳转返回。
             */
            Toast.makeText(Activity_Chose.this,"保存成功",Toast.LENGTH_SHORT).show();
            Intent(Activity_Chose.this,MainActivity.class);
            finish();
        }
        }
    };

}
