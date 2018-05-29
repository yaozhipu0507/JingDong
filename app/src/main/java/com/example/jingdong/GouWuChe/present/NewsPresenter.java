package com.example.jingdong.GouWuChe.present;

import com.example.jingdong.GouWuChe.GouWuJieKou;
import com.example.jingdong.GouWuChe.bean.MessageBean;
import com.example.jingdong.GouWuChe.bean.SecletBean;
import com.example.jingdong.GouWuChe.moudle.NewsModel;

import io.reactivex.subscribers.DisposableSubscriber;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter implements GouWuJieKou.BasePresenter{
    private GouWuJieKou.IGouWuView iGouWuView;
    private DisposableSubscriber subscriber1;

    public void attachView( GouWuJieKou.IGouWuView iGouWuView) {
        this.iGouWuView = iGouWuView;
    }

    public void detachView() {
        if (iGouWuView != null) {
            iGouWuView = null;
        }
        if (!subscriber1.isDisposed()){
            subscriber1.dispose();
        }

    }

    @Override
    public void getData(String uid,String pid) {
        NewsModel model = new NewsModel(this);
        model.getData(uid,pid);
    }

    public void getNews(Flowable<MessageBean<List<SecletBean>>> flowable) {
        subscriber1 = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean<List<SecletBean>>>() {
                    @Override
                    public void onNext(MessageBean<List<SecletBean>> listMessageBean) {
                        if (listMessageBean != null) {
                            List<SecletBean> list = listMessageBean.getData();
                            if (list != null) {
                                iGouWuView.onSuccess(list);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                       // iv.onFailed((Exception) t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
