package com.example.jingdong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdong.GouWuChe.GouWuJieKou;
import com.example.jingdong.GouWuChe.bean.MessageBean;
import com.example.jingdong.GouWuChe.utils.MessageEvent;
import com.example.jingdong.GouWuChe.MyAdapter;
import com.example.jingdong.GouWuChe.utils.PriceAndCountEvent;
import com.example.jingdong.GouWuChe.bean.SecletBean;
import com.example.jingdong.GouWuChe.utils.SomeId;
import com.example.jingdong.GouWuChe.present.DelPresenter;
import com.example.jingdong.GouWuChe.present.NewsPresenter;
import com.example.jingdong.R;
import com.example.jingdong.dingdan.OrderListActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;

public class Fragment_GouWu extends Fragment implements GouWuJieKou.IGouWuView {

    private String uid = "3981";
    private NewsPresenter presenter;
    private View view;
    private ExpandableListView mElv;
    private CheckBox mCheckbox2;
    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private MyAdapter adapter;
    private List<SecletBean> groupList = new ArrayList<>();
    private List<List<SecletBean.ListBean>> childList = new ArrayList<>();
    private String pid;
    private DelPresenter delPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_gouwu, container, false);
        initView(view);

        EventBus.getDefault().register(this);

        presenter = new NewsPresenter();
        presenter.attachView(this);
        delPresenter = new DelPresenter();
        delPresenter.attachView(this);
        adapter = new MyAdapter(getActivity(), groupList, childList);
        mElv.setAdapter(adapter);


        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });
        return view;
    }

    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
        mTvNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(Object o) {
        if (o != null) {
            List<SecletBean> list = (List<SecletBean>) o;
            if (list != null) {
                groupList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    List<SecletBean.ListBean> datas = list.get(i).getList();
                    childList.add(datas);
                }

                adapter.notifyDataSetChanged();
                mCheckbox2.setChecked(true);

                adapter.changeAllListCbState(true);
                mElv.setGroupIndicator(null);
                for (int i = 0; i < groupList.size(); i++) {
                    mElv.expandGroup(i);
                }

            }
        }
    }

    @Override
    public void delSuccess(MessageBean listMessageBean) {
        Toast.makeText(getActivity(), listMessageBean.getMsg(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(uid, pid);

    }
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText("￥"+event.getPrice() );
    }
    @Subscribe
    public void onMessageEvent(SomeId event) {
        pid = event.getPid();
        Log.e("zxz","我得到了pid:"+pid);
        delPresenter.getData(uid,pid);


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView();
        }
    }

}
