package com.example.jingdong;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.jingdong.fragment.Fragment_FaXian;
import com.example.jingdong.fragment.Fragment_FenLei;
import com.example.jingdong.fragment.Fragment_Geren;
import com.example.jingdong.fragment.Fragment_GouWu;
import com.example.jingdong.fragment.Fragment_ShouYe;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mBottomTabBar;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //创建SharedPreferences对象
        sharedPreferences=getSharedPreferences("user",0);
        //拿到后返回的用户id

        mBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(70, 70)
                .setFontSize(15)
                .setTabPadding(2, 5, 5)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem(getResources().getString(R.string.shouye), R.mipmap.shouye2, R.mipmap.shouye, Fragment_ShouYe.class)
                .addTabItem(getResources().getString(R.string.fenlei), R.mipmap.fenlei2, R.mipmap.fenlei, Fragment_FenLei.class)
                .addTabItem(getResources().getString(R.string.faxian), R.mipmap.faxian2, R.mipmap.faxian, Fragment_FaXian.class)
                .addTabItem(getResources().getString(R.string.gouwu), R.mipmap.gouwu2, R.mipmap.gouwu, Fragment_GouWu.class)
                .addTabItem(getResources().getString(R.string.wode), R.mipmap.geren2, R.mipmap.geren, Fragment_Geren.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
    }

    private void initView() {
        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
    }

}
