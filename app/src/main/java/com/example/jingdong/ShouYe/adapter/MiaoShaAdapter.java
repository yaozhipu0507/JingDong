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

public class MiaoShaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<ShouYeBean.MiaoshaBean.ListBeanX> beanXES;
    public MiaoShaAdapter(Context context, List<ShouYeBean.MiaoshaBean.ListBeanX> beanXES) {
        this.context=context;
        this.beanXES=beanXES;
    }

    private MiaoShaAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(ShouYeBean.MiaoshaBean.ListBeanX listBeanX);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_miao_sha, null);
        ViewHodlermiao vh1 = new ViewHodlermiao(view);
        return vh1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ViewHodlermiao mHodler = (ViewHodlermiao) holder;

        mHodler.text_title.setText(beanXES.get(position).getTitle());
        String url = beanXES.get(position).getImages().split("\\|")[0];
        final DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build();
        mHodler.simple.setController(controller);
        mHodler.mzhuyeshouji.setOnClickListener(new View.OnClickListener() {
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

    class ViewHodlermiao extends RecyclerView.ViewHolder {

        private SimpleDraweeView simple;
        private TextView text_title;
        private final LinearLayout mzhuyeshouji;

        public ViewHodlermiao(View itemView) {
            super(itemView);

            simple =itemView.findViewById(R.id.sdv);
            text_title=itemView.findViewById(R.id.tv);
            mzhuyeshouji = itemView.findViewById(R.id.zhuyeshouji);
        }
    }
}
