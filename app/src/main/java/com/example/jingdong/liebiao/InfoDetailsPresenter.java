package com.example.jingdong.liebiao;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

public class InfoDetailsPresenter {
    private IInfoDetailsJieKou.IInfoDetailsModel infoDetailsModel;
    private IInfoDetailsJieKou.IInfoDetailsActivity iInfoDetailsActivity;

    public InfoDetailsPresenter(IInfoDetailsJieKou.IInfoDetailsActivity iInfoDetailsActivity) {
        this.iInfoDetailsActivity = iInfoDetailsActivity;
        infoDetailsModel = new InfoDetailsModel();
    }
    public void getProductDetail(final Context context, String pscid, String page, String sort){
        HashMap<String, String> params = new HashMap<>();
        params.put("pscid",pscid);
        params.put("page",page);
        params.put("sort",sort);
        params.put("source","android");
        infoDetailsModel.getInfoDetails(context,params, new IInfoDetailsJieKou.OnNetListener<InfoDetailsBean>() {
            @Override
            public void onSuccess(InfoDetailsBean infoDetailsBean) {
                if (iInfoDetailsActivity != null) {
                    iInfoDetailsActivity.showList(infoDetailsBean.getData());
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
        iInfoDetailsActivity = null;
    }
}
