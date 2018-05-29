package com.example.jingdong.GouWuChe;

import com.example.jingdong.GouWuChe.bean.MessageBean;

public interface GouWuJieKou {

    interface IGouWuView {
        void onSuccess(Object o);
        void delSuccess(MessageBean listMessageBean);
    }

    interface BasePresenter {
        void getData(String uid, String pid);
    }

    interface IGouWuMoudle {
        void getData(String uid, String pid);
    }

}
