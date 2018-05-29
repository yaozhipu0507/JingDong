package com.example.jingdong.liebiao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jingdong.R;
import com.example.jingdong.XiangQing.XiangQingActivity;
import com.example.jingdong.fenlei.adapter.MyLeftAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class InfoDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<InfoDetailsBean.DataBean> dataBeanList;
    public InfoDetailsAdapter(Context context, List<InfoDetailsBean.DataBean> dataBeanList) {
        this.context=context;
        this.dataBeanList=dataBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liebiao_rv_item, parent, false);
        MyInfoViewHolder myInfoViewHolder = new MyInfoViewHolder(view);
        return myInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final InfoDetailsBean.DataBean dataBean = dataBeanList.get(position);
        MyInfoViewHolder myInfoViewHolder = (MyInfoViewHolder) holder;

        //fresco加载图片
        String url = dataBeanList.get(position).getImages().split("\\|")[0];
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build();

        myInfoViewHolder.iv.setController(controller);

        myInfoViewHolder.title.setText(dataBean.getTitle());
        myInfoViewHolder.pricelod.setText(dataBean.getSalenum()+"");
        myInfoViewHolder.pricenew.setText(dataBean.getPrice()+"");
        myInfoViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面
                Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("pid",dataBean.getPid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    class MyInfoViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;
        private final TextView title;
        private final TextView pricelod;
        private final TextView pricenew;
        private final LinearLayout ll;

        public MyInfoViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.rv_item_iv);
            title = itemView.findViewById(R.id.rv_item_title);
            pricelod = itemView.findViewById(R.id.rv_item_pricelod);
            pricelod.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            pricenew = itemView.findViewById(R.id.rv_item_pricenew);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
