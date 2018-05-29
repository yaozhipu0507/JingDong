package com.example.jingdong.ZhuCe;

import com.example.jingdong.Api.ApiServer;

import java.util.HashMap;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUpModel implements SignUpJieKou.ISignUpModel {
    @Override
    public void RequestData(String url, String username, String password, String password_confirm, final SignUpJieKou.OnRequestListener onRequestListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",username);
        map.put("password",password);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable getlg = apiServer.getsup("user/reg", map);
        getlg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SignUpBean signUpBean) {

                        if(signUpBean.getCode()==""+0){
                            onRequestListener.OnSuccess();
                        }else{
                            onRequestListener.OnError(signUpBean.getMsg());
                        }
                    }
                });
    }
}
