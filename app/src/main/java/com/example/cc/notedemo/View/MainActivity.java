package com.example.cc.notedemo.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cc.notedemo.R;

public class MainActivity extends AppCompatActivity {


    private TextView textView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        //初始化控件
        initView();
        //控件的点击事件
        onCLick();

    }
    //初始化控件函数
    private void initView() {
        textView = (TextView) findViewById(R.id.noteButton);
        view = findViewById(R.id.activity_main);
    }
    //初始化点击事件函数
    private void onCLick() {
        textView.setOnClickListener(listener);
    }

    //控件点击事件
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //点击跳转
            Intent intent = new Intent(MainActivity.this,Activity_Chose.class);
            startActivity(intent);
            finish();
        }
    };
    //手机返回键的监听函数
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            simple(view);
        }
        return false;
    }

    //dialog
    public void simple(View source){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("是否退出？");
        setPositiveButton(builder);
        setNegativeButton(builder).create().show();

    }
    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
    {
        return  builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder)
    {
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
