package com.example.jingdong.Api;

public class Api {

    public static final String BASEURL="https://www.zhaoapi.cn/";

    //当前子分类下的商品列表（分页）
    public static final String PRODUCT_CATAGORY_LIST = "https://www.zhaoapi.cn/product/getProducts";

    //查询商品
    public static final String  SEARCHPRODUCTS = "https://www.zhaoapi.cn/product/searchProducts";

    //创建订单接口
    public static final String CREATEORDER = BASEURL + "product/createOrder?uid=%s&price=%s";

    // 获取订单列表接口
    public static final String  GETORDERS = BASEURL+"product/getOrders";
}
