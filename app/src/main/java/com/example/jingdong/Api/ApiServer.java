package com.example.jingdong.Api;

import com.example.jingdong.DengLu.LoginBean;
import com.example.jingdong.GouWuChe.bean.MessageBean;
import com.example.jingdong.GouWuChe.bean.SecletBean;
import com.example.jingdong.XiangQing.XiangQingBean;
import com.example.jingdong.ZhuCe.SignUpBean;
import com.example.jingdong.ShouYe.ShouYeBean;
import com.example.jingdong.dingdan.GetOrdersBean;
import com.example.jingdong.fenlei.LeftBean;
import com.example.jingdong.fenlei.RightBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiServer {
    @POST
    Observable<LoginBean> getlg(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<SignUpBean> getsup(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<ShouYeBean> getShou(@Url String url);

    //分类

    @GET("product/getCatagory")
    Observable<LeftBean> getDaesleft();

    @POST
    Observable<RightBean> getDassright(@Url String url, @QueryMap Map<String, Integer> map);

    //购物车
    @GET("product/getCarts")
    Flowable<MessageBean<List<SecletBean>>> getDatas(@Query("uid") String uid);
    @GET("product/deleteCart")
    Flowable<MessageBean> deleteData(@Query("uid") String uid, @Query("pid") String pid);

    //查看订单
    @POST
    Observable<GetOrdersBean> getorder(@Url String url, @QueryMap Map<String, String> map);


    //详情
    @POST
    Observable<XiangQingBean> getliebiao(@Url String url, @QueryMap HashMap<String, Integer> map);

}
