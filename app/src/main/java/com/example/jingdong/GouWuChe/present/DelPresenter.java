package com.example.jingdong.GouWuChe.present;

import com.example.jingdong.GouWuChe.GouWuJieKou;
import com.example.jingdong.GouWuChe.bean.MessageBean;
import com.example.jingdong.GouWuChe.moudle.DelModel;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DelPresenter implements GouWuJieKou.BasePresenter{
    private GouWuJieKou.IGouWuView iGouWuView;
    private DisposableSubscriber subscriber2;

    public void attachView(GouWuJieKou.IGouWuView iGouWuView) {
        this.iGouWuView = iGouWuView;
    }

    public void detachView() {
        if (iGouWuView != null) {
            iGouWuView = null;
        }

        if (!subscriber2.isDisposed()){
            subscriber2.dispose();
        }
    }

    @Override
    public void getData(String uid,String pid) {
        DelModel model = new DelModel(this);
        model.getData(uid,pid);
    }



    public void delData(Flowable<MessageBean> delFlowable) {
        subscriber2 = delFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean>() {
                    @Override
                    public void onNext(MessageBean listMessageBean) {
                        if (listMessageBean != null) {
                            iGouWuView.delSuccess(listMessageBean);

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        //iGouWuView.onFailed((Exception) t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
