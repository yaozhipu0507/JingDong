package com.example.jingdong.fenlei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.fenlei.RightBean;
import com.example.jingdong.liebiao.InfoDetailsActivity;

import java.util.List;

public class MyRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<RightBean.DataBean> lists;
    public MyRightAdapter(Context context, List<RightBean.DataBean> lists) {
        this.context=context;
        this.lists=lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fenlei_right_item, parent, false);
        MyRightViewHolder rightViewHolder = new MyRightViewHolder(view);
        return rightViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyRightViewHolder rightViewHolder = (MyRightViewHolder) holder;

        rightViewHolder.textright.setText(lists.get(position).getName());
        List<RightBean.DataBean.ListBean> list = lists.get(position).getList();
        rightViewHolder.gridrecy.setLayoutManager(new GridLayoutManager(context, 3));
        Adaptergrid adaptergrid = new Adaptergrid(list, context);
        rightViewHolder.gridrecy.setAdapter(adaptergrid);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyRightViewHolder extends RecyclerView.ViewHolder{

        private TextView textright;
        private RecyclerView gridrecy;

        public MyRightViewHolder(View itemView) {
            super(itemView);

            textright = itemView.findViewById(R.id.textright);
            gridrecy=itemView.findViewById(R.id.recycler_grid);
        }
    }
}
