package com.example.jingdong.liebiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jingdong.GouWuChe.MyAdapter;
import com.example.jingdong.R;
import com.example.jingdong.Sou.SouActivity;

import java.util.ArrayList;
import java.util.List;

public class InfoDetailsActivity extends AppCompatActivity implements View.OnClickListener,IInfoDetailsJieKou.IInfoDetailsActivity {

    private ImageView mDetailsReturn;
    private EditText mSelectEtName;
    private ImageView mInfoShowType;
    /**
     * 综合
     */
    private TextView mInfoTvZhonghe;
    /**
     * 销量
     */
    private TextView mInfoTvXiaoliang;
    /**
     * 价格
     */
    private TextView mInfoTvPrice;
    /**
     * 筛选
     */
    private TextView mInfoTvShaixuan;
    private RecyclerView mInfoRv;
    private SwipeRefreshLayout mInfoSrl;
    private List<InfoDetailsBean.DataBean> dataBeanList = new ArrayList<>();
    private InfoDetailsPresenter infoDetailsPresenter;
    private SelectGoodsPresenter selectGoodsPresenter;
    private InfoDetailsAdapter infoDetailsAdapter;
    private String page = "1";
    private String sort = "0";
    private boolean type = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);

        String pscid = getIntent().getStringExtra("pscid");
        String goodsName = getIntent().getStringExtra("goodsName");
        if (!TextUtils.isEmpty(pscid)) {
            infoDetailsPresenter = new InfoDetailsPresenter(this);
            infoDetailsPresenter.getProductDetail(this,pscid, page, sort);
        } else if (!TextUtils.isEmpty(goodsName)) {
            selectGoodsPresenter = new SelectGoodsPresenter(this);
            selectGoodsPresenter.SelectGoods(this,goodsName, "", "");
        }
        initView();
    }

    private void initView() {
        mDetailsReturn = (ImageView) findViewById(R.id.details_return);
        mDetailsReturn.setOnClickListener(this);
        mSelectEtName = (EditText) findViewById(R.id.selectEtName);
        mSelectEtName.setOnClickListener(this);
        mInfoShowType = (ImageView) findViewById(R.id.info_show_type);
        mInfoShowType.setOnClickListener(this);
        mInfoTvZhonghe = (TextView) findViewById(R.id.info_tvZhonghe);
        mInfoTvZhonghe.setOnClickListener(this);
        mInfoTvXiaoliang = (TextView) findViewById(R.id.info_tvXiaoliang);
        mInfoTvXiaoliang.setOnClickListener(this);
        mInfoTvPrice = (TextView) findViewById(R.id.info_tvPrice);
        mInfoTvPrice.setOnClickListener(this);
        mInfoTvShaixuan = (TextView) findViewById(R.id.info_tvShaixuan);
        mInfoTvShaixuan.setOnClickListener(this);
        mInfoRv = (RecyclerView) findViewById(R.id.info_rv);
        mInfoRv.setOnClickListener(this);
        mInfoSrl = (SwipeRefreshLayout) findViewById(R.id.info_srl);
        mInfoSrl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.details_return:
                this.finish();
                break;
            case R.id.selectEtName:
                Intent intent = new Intent(InfoDetailsActivity.this,SouActivity.class);
                startActivity(intent);
                break;
            case R.id.info_show_type:
                type = !type;
                if (type) {
                    LinearLayoutManager manager = new LinearLayoutManager(this);
                    Toast.makeText(this, "线性的!!!", Toast.LENGTH_SHORT).show();
                    Glide.with(this).load(R.drawable.grid_icon).into(mInfoShowType);
                    mInfoRv.setLayoutManager(manager);
                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                    Toast.makeText(this, "网格的!!!", Toast.LENGTH_SHORT).show();
                    Glide.with(this).load(R.drawable.lv_icon).into(mInfoShowType);
                    mInfoRv.setLayoutManager(gridLayoutManager);
                }
                setAdapter();
                break;
            case R.id.info_tvZhonghe:
                break;
            case R.id.info_tvXiaoliang:
                break;
            case R.id.info_tvPrice:
                break;
            case R.id.info_tvShaixuan:
                break;
            case R.id.info_rv:
                break;
            case R.id.info_srl:
                break;
        }
    }

    @Override
    public void showList(List<InfoDetailsBean.DataBean> list) {
        dataBeanList.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mInfoRv.setLayoutManager(manager);
        Glide.with(this).load(R.drawable.grid_icon).into(mInfoShowType);
        setAdapter();
    }

    @Override
    public void showSelectList(List<InfoDetailsBean.DataBean> dataBeanList) {
        if (dataBeanList.size() < 1) {
            Toast.makeText(InfoDetailsActivity.this, "亲,很抱歉,没有搜索到相关产品呢!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        dataBeanList.addAll(dataBeanList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mInfoRv.setLayoutManager(manager);
        Glide.with(this).load(R.drawable.grid_icon).into(mInfoShowType);
        setAdapter();
    }

    public void setAdapter() {
        infoDetailsAdapter = new InfoDetailsAdapter(InfoDetailsActivity.this, dataBeanList);
        mInfoRv.setAdapter(infoDetailsAdapter);
        infoDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        infoDetailsPresenter.Dettach();
        selectGoodsPresenter.Dettach();
    }
}
