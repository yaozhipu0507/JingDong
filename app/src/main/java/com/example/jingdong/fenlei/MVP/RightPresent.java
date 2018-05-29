package com.example.jingdong.fenlei.MVP;

import com.example.jingdong.fenlei.FenLeiJieKoe;
import com.example.jingdong.fenlei.RightBean;

import java.util.List;

public class RightPresent {
    FenLeiJieKoe.IFenLeiView iFenLeiView;
    FenLeiJieKoe.Imodel imodel;

    public RightPresent(FenLeiJieKoe.IFenLeiView iFenLeiView) {
        this.iFenLeiView=iFenLeiView;
        imodel=new Moudle();
    }

    public void getRight(String url, int cid) {
        imodel.RequestRight(url, cid, new FenLeiJieKoe.IFenLeiRight() {
            @Override
            public void RightPresent(List<RightBean.DataBean> lists) {
                iFenLeiView.Rightview(lists);
            }

            @Override
            public void OnEorres() {

            }
        });
    }

}
