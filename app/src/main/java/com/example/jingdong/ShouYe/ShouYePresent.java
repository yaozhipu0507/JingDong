package com.example.jingdong.ShouYe;

public class ShouYePresent {
    ShouYeJieKou.IShouYeView iShouYeView;
    ShouYeJieKou.IShouYeMoudle shouYeMoudle;
    public ShouYePresent(ShouYeJieKou.IShouYeView iShouYeView) {
        this.iShouYeView=iShouYeView;
        shouYeMoudle =new ShouYeMoudle();
    }

    public void onShup(String url) {
        shouYeMoudle.Resque(url, new ShouYeJieKou.OnRequestListener() {
            @Override
            public void OnSuccess(ShouYeBean shouYeBean) {
                iShouYeView.onSuess(shouYeBean);
            }

            @Override
            public void OnError() {

            }
        });
    }
}
