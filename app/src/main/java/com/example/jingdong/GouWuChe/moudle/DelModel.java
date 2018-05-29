package com.example.jingdong.GouWuChe.moudle;

import com.example.jingdong.GouWuChe.GouWuJieKou;
import com.example.jingdong.GouWuChe.bean.MessageBean;
import com.example.jingdong.GouWuChe.RetrofitUtils;
import com.example.jingdong.GouWuChe.present.DelPresenter;

import io.reactivex.Flowable;


public class DelModel implements GouWuJieKou.IGouWuMoudle{
    private DelPresenter presenter;

    public DelModel(DelPresenter presenter){
        this.presenter =  presenter;

    }
    @Override
    public void getData(String uid,String pid) {

        Flowable<MessageBean> delFlowable = RetrofitUtils.getInstance().getApiServer().deleteData(uid,pid);
        presenter.delData(delFlowable);
    }
}
