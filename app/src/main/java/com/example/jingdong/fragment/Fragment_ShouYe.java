package com.example.jingdong.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jingdong.Api.Api;
import com.example.jingdong.ObservableScrollView;
import com.example.jingdong.R;
import com.example.jingdong.ShouYe.ShouYeBean;
import com.example.jingdong.ShouYe.ShouYeJieKou;
import com.example.jingdong.ShouYe.ShouYePresent;
import com.example.jingdong.ShouYe.adapter.ShouYeAdapter;
import com.example.jingdong.Sou.SouActivity;
import com.example.jingdong.zxing.activity.CaptureActivity;

public class Fragment_ShouYe extends Fragment implements ShouYeJieKou.IShouYeView, View.OnClickListener {

    private RecyclerView mRecy;
    private ShouYePresent shouYePresent;
    private ObservableScrollView mScrollView;
    private int imageHeight = 300; //设置渐变高度，一般为导航图片高度，自己控制
    private View view;
    private ImageView mErClick;
    private LinearLayout mSou;
    private ImageView mXiao;
    private LinearLayout mLine;
    private EditText mAtEv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_shouye, container, false);
        initView(view);

        shouYePresent = new ShouYePresent(this);
        shouYePresent.onShup(Api.BASEURL);

        //搜索框在布局最上面
        mLine.bringToFront();
        mScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    mLine.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    mLine.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
                } else {
                    mLine.setBackgroundColor(Color.argb((int) 200, 0, 0, 0));
                }
            }

        });


        return view;
    }

    private void initView(View view) {

        mErClick = (ImageView) view.findViewById(R.id.ErClick);
        mSou = (LinearLayout) view.findViewById(R.id.sou);
        mXiao = (ImageView) view.findViewById(R.id.xiao);
        mLine = (LinearLayout) view.findViewById(R.id.line);
        mRecy =(RecyclerView) view.findViewById(R.id.recy);
        mAtEv =(EditText) view.findViewById(R.id.at_ev);
        mRecy.setOnClickListener(this);
        mAtEv.setOnClickListener(this);
        mErClick.setOnClickListener(this);
        mAtEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent souat = new Intent(getActivity(), SouActivity.class);
                startActivity(souat);
            }
        });
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        mScrollView.setOnClickListener(this);
    }

    @Override
    public void onSuess(ShouYeBean shouYeBean) {

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecy.setLayoutManager(manager);
        ShouYeAdapter shouYeAdapter = new ShouYeAdapter(getActivity(), shouYeBean);
        mRecy.setAdapter(shouYeAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.recy:
                break;
            case R.id.scrollView:
                break;

            case R.id.ErClick:
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;

        }
    }

}
