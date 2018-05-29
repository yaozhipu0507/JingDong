package com.example.jingdong.ShouYe;

import com.example.jingdong.fenlei.LeftBean;

import java.util.List;

public interface ShouYeJieKou {

    interface IShouYeView{
        void onSuess(ShouYeBean shouYeBean);
        //void onSuessGrid(ShouYeGridBean shouYeGridBean);
    }

    interface IShouYeMoudle{
        void Resque(String url, OnRequestListener onRequestListener);
        //void ResqueGrid(String url, OnGridListener onGridListener);
    }

    interface OnRequestListener{
        void OnSuccess(ShouYeBean shouYeBean);
        void OnError();
    }

    /*interface OnGridListener{
        void OnSuccessGrid(ShouYeGridBean shouYeGridBean);
        void OnError();
    }*/
}
