package com.example.jingdong.fenlei;

import java.util.List;

public interface FenLeiJieKoe {

    interface IFenLeiView{
        void Leftview(List<LeftBean.DataBean> list);
        void Rightview(List<RightBean.DataBean> lists);
    }

    interface Imodel {
        void RequestLeft(String url,IFenLeiLeft onselectleft);
        void RequestRight(String url,int cid,IFenLeiRight onsekectright);

    }

    interface IFenLeiLeft{
        void LeftPresent(List<LeftBean.DataBean> list);
        void OnEorres();
    }

    interface IFenLeiRight{
        void RightPresent(List<RightBean.DataBean> lists);
        void OnEorres();
    }
}
