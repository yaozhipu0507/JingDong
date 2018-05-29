package com.example.jingdong.dingdan;

import android.content.Context;
import android.widget.Toast;

public class OrderPresenter {

    private DingDanJieKou.IOrderListActivity iOrderListActivity;
    private DingDanJieKou.IOrderModel iOrderModel;

    public OrderPresenter(DingDanJieKou.IOrderListActivity iOrderListActivity) {
        this.iOrderListActivity = iOrderListActivity;
        iOrderModel = new OrderMoudle();
    }

    /**
     * 订单数据展示
     */
    public void getOrders(final Context context, String status){

        iOrderModel.getOrders(context,"3981",status, new DingDanJieKou.OnNetListener<GetOrdersBean>() {
            @Override
            public void onSuccess(GetOrdersBean getOrdersBean) {
                if (iOrderListActivity != null) {
                    iOrderListActivity.showOrder(getOrdersBean.getData());
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(context, "对于请求失败这事,就不劳揭穿了!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 销毁
     */
    public void Dettach() {
        iOrderListActivity = null;
        //iGoodsCardFragment = null;
    }
}
