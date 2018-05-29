package com.example.jingdong.liebiao;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.jingdong.Api.Api;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SelectGoodsModel implements IInfoDetailsJieKou.ISelectGoodsModel {
    protected Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void getOrders(Context context, String keywords, String status, String page, final IInfoDetailsJieKou.OnNetListener<InfoDetailsBean> onNetWorkListener) {
        Map<String, String> params = new HashMap<>();
        params.put("keywords", keywords);
        if (!status.equals("")){
            params.put("sort", status);
        }else if (!page.equals("")){
            params.put("page", page);
        }
        HttpUtils.getHttpUtils(context).doPost(Api.SEARCHPRODUCTS, params, new Callback() {
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
                final InfoDetailsBean infoDetailsBean = new Gson().fromJson(string, InfoDetailsBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetWorkListener.onSuccess(infoDetailsBean);
                    }
                });
            }
        });
    }
}
