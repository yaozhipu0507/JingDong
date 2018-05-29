package com.example.jingdong.ShouYe.adapter;

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
import com.example.jingdong.ShouYe.MessageDetails;
import com.example.jingdong.XiangQing.XiangQingActivity;
import com.example.jingdong.ShouYe.ShouYeBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class TuiJianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<ShouYeBean.TuijianBean.ListBean> beanXES;
    public TuiJianAdapter(Context context, List<ShouYeBean.TuijianBean.ListBean> beanXES) {
        this.context=context;
        this.beanXES=beanXES;
    }

    private TuiJianAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(ShouYeBean.TuijianBean.ListBean listBean);
    }

    public void setOnItemClickListener(TuiJianAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tui_jian, null);
        ViewHodlerjian vh1 = new ViewHodlerjian(view);
        return vh1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ViewHodlerjian mHodler = (ViewHodlerjian) holder;

        mHodler.mtitle.setText(beanXES.get(position).getTitle());
        String url = beanXES.get(position).getImages().split("\\|")[0];
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build();
        mHodler.msimple.setController(controller);
        mHodler.mshouyediannao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("pid",beanXES.get(position).getPid()+"");
                context.startActivity(intent);*/

                EventBus.getDefault().postSticky(new MessageDetails(beanXES.get(position).getPid()));
                Intent intent = new Intent(context, XiangQingActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return beanXES.size();
    }

    class ViewHodlerjian extends RecyclerView.ViewHolder {

        private SimpleDraweeView msimple;
        private TextView mtitle;
        private final LinearLayout mshouyediannao;

        public ViewHodlerjian(View itemView) {
            super(itemView);

            msimple =itemView.findViewById(R.id.simple);
            mtitle=itemView.findViewById(R.id.title1);
            mshouyediannao = itemView.findViewById(R.id.shouyediannao);

        }
    }
}
