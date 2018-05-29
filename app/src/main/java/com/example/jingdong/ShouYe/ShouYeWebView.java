package com.example.jingdong.ShouYe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.jingdong.R;

public class ShouYeWebView extends AppCompatActivity {

    private WebView mShouyeWebview;
    private String webviewURl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_webview);

        initView();
    }

    private void initView() {
        mShouyeWebview = (WebView) findViewById(R.id.shouye_webview);

        Intent intent = getIntent();
        webviewURl = intent.getStringExtra("webviewURl");
        mShouyeWebview.loadUrl(webviewURl);
    }
}
