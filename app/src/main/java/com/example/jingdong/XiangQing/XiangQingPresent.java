package com.example.jingdong.XiangQing;

public class XiangQingPresent {
    XiangQingJieKou.Idetailsview idetailsview;
    XiangQingJieKou.Idetailsmodel idetailsmodel;

    public XiangQingPresent(XiangQingJieKou.Idetailsview idetailsview) {
        this.idetailsview=idetailsview;
        idetailsmodel=new XiangQingMoudle();
    }

    public void getOkDetails(String url, int pid) {
        idetailsmodel.RequestDetails(url, pid, new XiangQingJieKou.OnNetListener() {

            @Override
            public void onSuccess(XiangQingBean.DataBean list) {
                idetailsview.shouDetails(list);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
