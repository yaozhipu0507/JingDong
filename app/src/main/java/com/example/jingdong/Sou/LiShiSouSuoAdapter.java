package com.example.jingdong.Sou;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdong.R;

import java.util.List;

public class LiShiSouSuoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> strlist;
    public LiShiSouSuoAdapter(Context context, List<String> strlist) {
        this.context=context;
        this.strlist=strlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.lishisousuoitem, null);
        LiShiViewHolder holder = new LiShiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LiShiViewHolder liShiViewHolder= (LiShiViewHolder) holder;
        String s = strlist.get(position);
        liShiViewHolder.textView.setText(s);
    }

    @Override
    public int getItemCount() {
        return strlist.size();
    }

    public class LiShiViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public LiShiViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.sousuoverticaltv);
        }
    }
}
