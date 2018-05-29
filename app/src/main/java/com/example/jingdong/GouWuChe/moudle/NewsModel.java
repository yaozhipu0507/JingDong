package com.example.jingdong.GouWuChe.moudle;

import com.example.jingdong.GouWuChe.GouWuJieKou;
import com.example.jingdong.GouWuChe.bean.MessageBean;
import com.example.jingdong.GouWuChe.RetrofitUtils;
import com.example.jingdong.GouWuChe.bean.SecletBean;
import com.example.jingdong.GouWuChe.present.NewsPresenter;

import java.util.List;
import io.reactivex.Flowable;

public class NewsModel implements GouWuJieKou.IGouWuMoudle{
    private NewsPresenter presenter;

    public NewsModel(NewsPresenter presenter) {
        this.presenter = (NewsPresenter) presenter;

    }

    @Override
    public void getData(String uid, String pid) {
        Flowable<MessageBean<List<SecletBean>>> flowable = RetrofitUtils.getInstance().getApiServer().getDatas(uid);
        presenter.getNews(flowable);

    }
}
