package com.example.jingdong.Sou;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdong.R;

import java.util.List;

public class ReSouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> horizontallist;
    public ReSouAdapter(Context context, List<String> horizontallist) {
        this.context=context;
        this.horizontallist=horizontallist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sou_resou_item, null);
        ReViewHolder holder = new ReViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ReViewHolder reViewHolder= (ReViewHolder) holder;
        String s = horizontallist.get(position);
        reViewHolder.tv.setText(s);

    }

    @Override
    public int getItemCount() {
        return horizontallist.size();
    }

    public class ReViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public ReViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.sousuohorizontaltv);
        }
    }
}
