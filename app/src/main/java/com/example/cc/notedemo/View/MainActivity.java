package com.example.cc.notedemo.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cc.notedemo.R;

public class MainActivity extends AppCompatActivity {


    private TextView textView,textView1;
    private View view,view1;
    private ListView listView_MainActivity;
    SharedPreferences preferences,preferences1; //用于接收持久化对象的声明
    SharedPreferences.Editor editor1;           //用于创建持久化对象的声明
    private String DealString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView(); //初始化控件
        onCLick();  //控件的点击事件
        dealArray();//初始化listView
    }
    /**
     * 初始化控件函数
     */
    private void initView() {
        textView = (TextView) findViewById(R.id.noteButton);

        view = findViewById(R.id.activity_main);
        listView_MainActivity = (ListView) findViewById(R.id.listView_Main);
    }
    /**
     * 初始化点击事件函数
     */
    private void onCLick() {
        textView.setOnClickListener(listener);
        listView_MainActivity.setOnItemClickListener(listenerLV);
    }
    /**
     *  控件点击事件
     *  mainActivity 中'笔记'的点击事件
     *  会跳转到edit_massage中进行笔记 编辑
     */
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Activity_Chose.class); //点击跳转
            startActivity(intent);
            finish();
        }
    };
    /**
     * listView item 的点击事件
     * 2017年1月16日09:41:54
     * 任务:跳转到查看信息界面 并且将值传过去（Intent 传值）
     */
    AdapterView.OnItemClickListener listenerLV = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            String data = arg0.getItemAtPosition(arg2).toString();
            Intent intent = new Intent(MainActivity.this,Activity_show.class);
            intent.putExtra("ShowTitle",data);
            startActivity(intent);
        }
    };
    /**
     * 2017年1月12日07:45:14
     * 需要一个数组来实现对listView适配器的动态调整，每次新的日记标题将会动态添加到listView中(失败)
     * 可能需要研究ViewGroup来调整listView的Item
     *
     * 2017年1月13日08:57:09
     * 这里只需要接受数据，并提供Adapter所用的数组
     */
        public  void dealArray()
        {
            String[] array= new String[]{};
            array = Change(array);
            String[] array1 = new String[(array.length)-1];
            array1 = Change2(array,array1);
            ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,R.layout.array_adapter,R.id.Before,array1);
            listView_MainActivity.setAdapter(adapter);
        }
    /**
     * 注：每次再Aciivity_Chose中点击保存后，都会传来一个相同或不同的String类型的字符串
     *     要考虑所有传递，持久化之间的先后时间问题。
     * 另：此处的接收的数据考虑持久化
     * @return
     */
    private String getSharedString()
    {
        /**
         *  接收初始数据
         */
        preferences = getSharedPreferences("Title",MODE_PRIVATE);
        String UpReceiveString = preferences.getString("title","");
        /**
         * 加工数据
         * 此处加 ","，用于之后处理数组，然后上传到其他文件中
         */
        editor1 = getSharedPreferences("Title_Work",MODE_PRIVATE).edit();
        UpReceiveString = ","+UpReceiveString;
        editor1.putString("title_work",UpReceiveString);
        editor1.apply();
        /**
         * 此处下载新的文件中的数据，然后设置传递
         */
        preferences1 = getSharedPreferences("Title_Work",MODE_PRIVATE);
        DealString = preferences1.getString("title_work","");
        return DealString;
    }
    /**
     * 字符串转数组函数
     * 2017年1月14日09:21:16
     */
    public String[] Change(String[] resources){

        String receive = getSharedString();
             resources = receive.split(",");
        return  resources;
    }
    /**
     * 用于数组的二次优化
     * 2017年1月16日09:33:29
     */
    private String[] Change2(String[] resources,String[] resources1) {
        int length = resources.length;
        switch (length){
            case 0:
                Log.d("Length = 0","0");
                break;
            default:
                for(int i=1;i<length;i++)
                {
                    resources[i-1] = resources[i];
                }
                for(int i=0;i<length-1;i++)
                {
                    resources1[i] = resources[i];
                }
                break;
        }
        return resources1;
    }
    /**
     * 手机返回键的监听函数
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            simple(view);
        }
        return false;
    }
    /**
     *----------------------------------------------------------------------dialog
     */
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
    /**
     *----------------------------------------------------------------------dialog
     */


}
