package com.example.jingdong.ShouYe;


import com.example.jingdong.Api.ApiServer;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShouYeMoudle implements ShouYeJieKou.IShouYeMoudle {
    @Override
    public void Resque(String url, final ShouYeJieKou.OnRequestListener onRequestListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable shou = apiServer.getShou("ad/getAd");
        shou.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShouYeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError();
                    }

                    @Override
                    public void onNext(ShouYeBean shouYeBean) {
                        onRequestListener.OnSuccess(shouYeBean);
                    }
                });

    }
}
