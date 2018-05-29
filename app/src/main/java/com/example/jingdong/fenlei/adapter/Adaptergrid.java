package com.example.jingdong.fenlei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.fenlei.RightBean;
import com.example.jingdong.liebiao.InfoDetailsActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class Adaptergrid extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<RightBean.DataBean.ListBean> list;
    Context context;
    public Adaptergrid(List<RightBean.DataBean.ListBean> list, Context context) {
        this.context=context;
        this.list=list;
    }

    //2、定义一个属性
    private OnItemClickListener onItemClickListener;

    //1、接口回调第一步，先定义一个接口
    public interface OnItemClickListener {
        public void onItemClick(RightBean.DataBean.ListBean listBean);
    }

    //3、定义一个方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fenlei_grid_item, parent, false);
        MyGridViewHolder gridViewHolder = new MyGridViewHolder(view);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyGridViewHolder gridViewHolder = (MyGridViewHolder) holder;

        gridViewHolder.gridtext.setText(list.get(position).getName());
        String url = list.get(position).getIcon().split("\\|")[0];
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build();

        gridViewHolder.gridsimple.setController(controller);


        gridViewHolder.grid_fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //就是跳转
                Intent intent = new Intent(context, InfoDetailsActivity.class);
                intent.putExtra("pscid", list.get(position).getPscid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyGridViewHolder extends RecyclerView.ViewHolder{

        private TextView gridtext;
        private SimpleDraweeView gridsimple;
        private final LinearLayout grid_fenlei;

        public MyGridViewHolder(View itemView) {
            super(itemView);

            gridtext = itemView.findViewById(R.id.gridtext);
            gridsimple=itemView.findViewById(R.id.gridsimple);
            grid_fenlei = itemView.findViewById(R.id.grid_fenlei);
        }
    }
}
