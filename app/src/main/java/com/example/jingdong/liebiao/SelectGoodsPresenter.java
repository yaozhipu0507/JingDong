package com.example.jingdong.liebiao;

import android.content.Context;
import android.widget.Toast;

public class SelectGoodsPresenter {
    private IInfoDetailsJieKou.IInfoDetailsActivity infoDetailsActivity;
    private IInfoDetailsJieKou.ISelectGoodsModel iSelectGoodsModel;

    public SelectGoodsPresenter(IInfoDetailsJieKou.IInfoDetailsActivity infoDetailsActivity) {
        this.infoDetailsActivity = infoDetailsActivity;
        iSelectGoodsModel = new SelectGoodsModel();
    }
    public void SelectGoods(final Context context, String keywords, String sort, String page){
        iSelectGoodsModel.getOrders(context,keywords, sort, page, new IInfoDetailsJieKou.OnNetListener<InfoDetailsBean>() {
            @Override
            public void onSuccess(InfoDetailsBean infoDetailsBean) {
                if (infoDetailsActivity != null){
                    infoDetailsActivity.showSelectList(infoDetailsBean.getData());
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(context,"对于请求失败这事,就不劳揭穿了!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 销毁
     */
    public void Dettach() {
        infoDetailsActivity = null;
    }
}
