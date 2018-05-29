package com.example.jingdong.liebiao;

import android.content.Context;

import java.util.List;
import java.util.Map;

public interface IInfoDetailsJieKou {
    interface IInfoDetailsActivity {
        void showList(List<InfoDetailsBean.DataBean> dataBeanList);
        void showSelectList(List<InfoDetailsBean.DataBean> dataBeanList);
    }

    interface IInfoDetailsModel {
        public void getInfoDetails(Context context, Map<String,String> params, OnNetListener<InfoDetailsBean> onNetListener);
    }

    interface ISelectGoodsModel {
        void getOrders(Context context, String keywords, String status, String page, OnNetListener<InfoDetailsBean> onNetWorkListener);
    }

    interface OnNetListener <T>{
        //成功回调
        public void onSuccess(T t);
        //失败回调
        public void onFailure(Exception e);
    }
}
