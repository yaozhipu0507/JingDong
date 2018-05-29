package com.example.jingdong;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements OnClickListener{

    private TextView text;
    private TextView tiaozhuan;
    private int count=3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            text.setText(count+"秒跳转");
            sendEmptyMessageDelayed(0,1000);
            count--;
            if (count==0){
                removeMessages(0);
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = (TextView) findViewById(R.id.text1);
        tiaozhuan =findViewById(R.id.tiaozhuan);
        tiaozhuan.setOnClickListener(this);
        handler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }
}
