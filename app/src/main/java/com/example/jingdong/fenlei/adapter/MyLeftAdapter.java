package com.example.jingdong.fenlei.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.fenlei.LeftBean;
import com.example.jingdong.fenlei.Meventss;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyLeftAdapter extends RecyclerView.Adapter{

    Context context;
    List<LeftBean.DataBean> list;
    public MyLeftAdapter(Context context, List<LeftBean.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fenlei_left_item, parent, false);
        MyLeftViewHolder leftViewHolder = new MyLeftViewHolder(view);
        return leftViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyLeftViewHolder leftViewHolder= (MyLeftViewHolder) holder;
        leftViewHolder.textitem.setText(list.get(position).getName());
        leftViewHolder.fenlei_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getIsfenleileft()){
                    leftViewHolder.fenlei_left.setBackgroundColor(Color.parseColor("#00ffee"));
                }else {
                    leftViewHolder.fenlei_left.setBackgroundColor(Color.WHITE);
                }
            }
        });
        leftViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new Meventss(list.get(position).getCid()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyLeftViewHolder extends RecyclerView.ViewHolder{

        private final TextView textitem;
        private final LinearLayout fenlei_left;

        public MyLeftViewHolder(View itemView) {
            super(itemView);

            textitem = itemView.findViewById(R.id.txt_left_item);
            fenlei_left = itemView.findViewById(R.id.fenlei_left);
        }
    }
}
