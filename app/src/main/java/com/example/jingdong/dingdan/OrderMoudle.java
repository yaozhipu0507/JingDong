package com.example.jingdong.dingdan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.jingdong.Api.Api;
import com.example.jingdong.Api.ApiServer;
import com.example.jingdong.liebiao.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OrderMoudle implements DingDanJieKou.IOrderModel {
    protected Handler handler = new Handler(Looper.getMainLooper());
    /**
     * 创建订单
     * @param uid
     * @param price
     * @param onNetListener
     */
    @Override
    public void createOrder(Context context, String uid, String price, final DingDanJieKou.OnNetListener<BaseBean> onNetListener) {
        String url = String.format(Api.CREATEORDER,uid,price);
        HttpUtils.getHttpUtils(context).doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                final BaseBean baseBean = new Gson().fromJson(str, BaseBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(baseBean);
                    }
                });
            }
        });
    }

    /**
     * 显示订单列表
     * @param uid
     * @param onNetWorkListener
     */
    @Override
    public void getOrders(Context context,String uid,String status, final DingDanJieKou.OnNetListener<GetOrdersBean> onNetWorkListener) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        if (status != null){
            params.put("status",status);
        }
        HttpUtils.getHttpUtils(context).doPost(Api.GETORDERS, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetWorkListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final GetOrdersBean getOrdersBean = new Gson().fromJson(string, GetOrdersBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetWorkListener.onSuccess(getOrdersBean);
                    }
                });

            }
        });
    }
}
