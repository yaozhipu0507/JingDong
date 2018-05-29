package com.example.jingdong.fenlei.MVP;


import com.example.jingdong.fenlei.FenLeiJieKoe;
import com.example.jingdong.fenlei.LeftBean;

import java.util.List;


public class LeftPrensent {
    FenLeiJieKoe.IFenLeiView iFenLeiView;
    FenLeiJieKoe.Imodel imodel;
    public LeftPrensent(FenLeiJieKoe.IFenLeiView iFenLeiView) {
        this.iFenLeiView=iFenLeiView;
        imodel=new Moudle();
    }

    public void getLeft(String url) {
        imodel.RequestLeft(url, new FenLeiJieKoe.IFenLeiLeft() {
            @Override
            public void LeftPresent(List<LeftBean.DataBean> list) {
                iFenLeiView.Leftview(list);
            }

            @Override
            public void OnEorres() {

            }

        });
    }


}
