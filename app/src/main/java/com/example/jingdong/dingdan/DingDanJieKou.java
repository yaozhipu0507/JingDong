package com.example.jingdong.dingdan;

import android.content.Context;

import java.util.List;

public interface DingDanJieKou {
    interface IOrderListActivity {
        //显示订单数据
        void showOrder(List<GetOrdersBean.DataBean> beanList);
    }

    interface IOrderModel {
        //创建订单
        void createOrder(Context context, String uid, String price, OnNetListener<BaseBean> onNetListener);
        //显示订单
        void getOrders(Context context,String uid, String status,OnNetListener<GetOrdersBean> onNetWorkListener);
    }
    interface OnNetListener <T>{
        //成功回调
        void onSuccess(T t);
        //失败回调
        void onFailure(Exception e);
    }
}
