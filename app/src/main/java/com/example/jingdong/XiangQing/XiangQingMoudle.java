package com.example.jingdong.XiangQing;

import com.example.jingdong.Api.ApiServer;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class XiangQingMoudle implements XiangQingJieKou.Idetailsmodel {

    @Override
    public void RequestDetails(String url, int pid, final XiangQingJieKou.OnNetListener onNetListener) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("pid",pid);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable getliebiao = apiServer.getliebiao("product/getProductDetail", map);
        getliebiao.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<XiangQingBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure();
                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
                        XiangQingBean.DataBean data = xiangQingBean.getData();
                        onNetListener.onSuccess(data);
                    }
                });
    }
}
