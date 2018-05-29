package com.example.jingdong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jingdong.Api.Api;
import com.example.jingdong.R;
import com.example.jingdong.fenlei.FenLeiJieKoe;
import com.example.jingdong.fenlei.LeftBean;
import com.example.jingdong.fenlei.MVP.LeftPrensent;
import com.example.jingdong.fenlei.Meventss;
import com.example.jingdong.fenlei.adapter.MyLeftAdapter;
import com.example.jingdong.fenlei.adapter.MyRightAdapter;
import com.example.jingdong.fenlei.RightBean;
import com.example.jingdong.fenlei.MVP.RightPresent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;

public class Fragment_FenLei extends Fragment implements FenLeiJieKoe.IFenLeiView {


    private RecyclerView mRecycleft;
    private RecyclerView mRecycright;
    private LeftPrensent leftPrensent;
    private int cid;
    private RightPresent rightPrensent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fenlei, container, false);
        EventBus.getDefault().register(this);

        initView(view);

        leftPrensent = new LeftPrensent(this);
        leftPrensent.getLeft(Api.BASEURL);

        rightPrensent = new RightPresent(this);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, sticky = true)
    public void noEvent(Meventss meventss) {
        cid = meventss.getCid();
        rightPrensent.getRight(Api.BASEURL, cid);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View view) {
        mRecycleft = (RecyclerView) view.findViewById(R.id.recycleft);
        mRecycright = (RecyclerView) view.findViewById(R.id.recycright);
    }

    @Override
    public void Leftview(List<LeftBean.DataBean> list) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycleft.setLayoutManager(manager);
        MyLeftAdapter leftAdapter = new MyLeftAdapter(getActivity(), list);
        mRecycleft.setAdapter(leftAdapter);
        rightPrensent.getRight(Api.BASEURL,1);
    }

    @Override
    public void Rightview(List<RightBean.DataBean> lists) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycright.setLayoutManager(manager);
        MyRightAdapter rightAdapter = new MyRightAdapter(getActivity(), lists);
        mRecycright.setAdapter(rightAdapter);
    }
}
