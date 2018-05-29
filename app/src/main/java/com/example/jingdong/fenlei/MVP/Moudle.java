package com.example.jingdong.fenlei.MVP;

import com.example.jingdong.Api.ApiServer;
import com.example.jingdong.fenlei.FenLeiJieKoe;
import com.example.jingdong.fenlei.LeftBean;
import com.example.jingdong.fenlei.RightBean;

import java.util.HashMap;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Moudle implements FenLeiJieKoe.Imodel {
    @Override
    public void RequestLeft(String url, final FenLeiJieKoe.IFenLeiLeft onselectleft) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<LeftBean> daesleft = apiServer.getDaesleft();
        daesleft.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LeftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onselectleft.OnEorres();
                    }

                    @Override
                    public void onNext(LeftBean leftBean) {
                        List<LeftBean.DataBean> data = leftBean.getData();
                        onselectleft.LeftPresent(data);
                    }
                });
    }

    @Override
    public void RequestRight(String url, int cid, final FenLeiJieKoe.IFenLeiRight onsekectright) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("cid", cid);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<RightBean> dassright = apiServer.getDassright("product/getProductCatagory", map);
        dassright.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onsekectright.OnEorres();
                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        List<RightBean.DataBean> data = rightBean.getData();
                        onsekectright.RightPresent(data);
                    }
                });
    }
}
