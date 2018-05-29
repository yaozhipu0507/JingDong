package com.example.jingdong.XiangQing;

public interface XiangQingJieKou {

    interface Idetailsview {
        void shouDetails(XiangQingBean.DataBean list);

    }

    interface Idetailsmodel {
        void RequestDetails(String url,int pid, OnNetListener onNetListener);
    }

    interface OnNetListener {
        //成功回调
        public void onSuccess(XiangQingBean.DataBean list);
        //失败回调
        public void onFailure();
    }

}
