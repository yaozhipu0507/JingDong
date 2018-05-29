package com.example.jingdong.XiangQing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.jingdong.Api.Api;
import com.example.jingdong.R;
import com.example.jingdong.fragment.Fragment_GouWu;
import com.stx.xhb.xbanner.XBanner;
import java.util.ArrayList;
import java.util.List;

public class XiangQingActivity extends AppCompatActivity implements View.OnClickListener,XiangQingJieKou.Idetailsview {


    private XBanner mDetailBanner;
    private TextView mDetailsTitle;
    private TextView mDetailsText;
    private TextView mDetailsPrice;
    /**
     * 立即购买
     */
    private Button mBuyNow;
    /**
     * 添加到购物车
     */
    private Button mAddCart;
    private XiangQingPresent xiangQingPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);

        xiangQingPresent = new XiangQingPresent(this);
        xiangQingPresent.getOkDetails(Api.BASEURL, 5);

        initView();

    }

    private void initView() {
        mDetailBanner = (XBanner) findViewById(R.id.detail_banner);
        mDetailsTitle = (TextView) findViewById(R.id.details_title);
        mDetailsText = (TextView) findViewById(R.id.details_text);
        mDetailsPrice = (TextView) findViewById(R.id.details_price);
        mBuyNow = (Button) findViewById(R.id.buy_now);
        mBuyNow.setOnClickListener(this);
        mAddCart = (Button) findViewById(R.id.add_cart);
        mAddCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.buy_now:
                Intent intent = new Intent(XiangQingActivity.this, Fragment_GouWu.class);
                startActivity(intent);
                
                break;
            case R.id.add_cart:
                Toast.makeText(XiangQingActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void shouDetails(XiangQingBean.DataBean list) {

        final List<String> img_list = new ArrayList<>();
        String images = list.getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            img_list.add(split[i]);
        }
        mDetailBanner.setData(img_list, null);
        mDetailBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XiangQingActivity.this).load(img_list.get(position)).into((ImageView) view);
            }
        });

        mDetailsTitle.setText(list.getTitle());
        mDetailsText.setText(list.getSubhead());
        mDetailsPrice.setText(list.getPrice());

    }
}
