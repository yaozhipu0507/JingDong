package com.example.jingdong.dingdan;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdong.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<GetOrdersBean.DataBean> beanList;
    public OrderAdapter(Context context, List<GetOrdersBean.DataBean> beanList) {
        this.context=context;
        this.beanList=beanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dingdan_order_item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final GetOrdersBean.DataBean dataBean = beanList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv_title.setText(dataBean.getTitle());
        int status = dataBean.getStatus();
        if (status == 0) {
            myViewHolder.tv_type.setText("待支付");
            myViewHolder.tv_action.setText("取消订单");
            myViewHolder.tv_type.setTextColor(Color.RED);
        } else if (status == 1) {
            myViewHolder.tv_type.setText("已支付");
            myViewHolder.tv_action.setText("查看订单");
            myViewHolder.tv_type.setTextColor(Color.BLACK);
        } else {
            myViewHolder.tv_type.setText("已取消");
            myViewHolder.tv_action.setText("查看订单");
            myViewHolder.tv_type.setTextColor(Color.BLACK);
        }

        myViewHolder.tv_price.setText("" + dataBean.getPrice());
        myViewHolder.tv_time.setText("" + dataBean.getCreatetime());

        myViewHolder.tv_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("确定取消订单吗？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // mainPresenter.updateOrder(context,dataBean.getStatus()+"", dataBean.getOrderid() + "");
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final TextView tv_type;
        private final TextView tv_price;
        private final TextView tv_time;
        private final TextView tv_action;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.order_tvTitle);
            tv_type = itemView.findViewById(R.id.order_tvType);
            tv_price = itemView.findViewById(R.id.order_tvPrice);
            tv_time = itemView.findViewById(R.id.order_tvTime);
            tv_action = itemView.findViewById(R.id.order_tvAction);
        }
    }
}
