package com.example.jingdong.liebiao;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.jingdong.Api.Api;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class InfoDetailsModel implements IInfoDetailsJieKou.IInfoDetailsModel {
    protected Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void getInfoDetails(Context context, Map<String, String> params, final IInfoDetailsJieKou.OnNetListener<InfoDetailsBean> onNetListener) {
        HttpUtils.getHttpUtils(context).doPost(Api.PRODUCT_CATAGORY_LIST, params, new Callback() {
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
                final InfoDetailsBean detailsBean = new Gson().fromJson(str, InfoDetailsBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(detailsBean);
                    }
                });
            }
        });
    }
}
