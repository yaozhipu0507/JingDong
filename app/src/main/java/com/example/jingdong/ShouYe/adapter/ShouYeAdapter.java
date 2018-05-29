package com.example.jingdong.ShouYe.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdong.LooperTextView;
import com.example.jingdong.R;
import com.example.jingdong.ShouYe.GlideImageLoader;
import com.example.jingdong.ShouYe.ShouYeBean;
import com.example.jingdong.ShouYe.ShouYeWebView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShouYeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ShouYeBean shouYeBean;
    private TextView tv_time;
    private SimpleDateFormat format;
    private Handler handler = new Handler();
    private long recLen;
    private LooperTextView mLtv;
    private List<String> list = new ArrayList<>();
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_MIAO = 1;
    public static final int TYPE_TUI = 2;

    public ShouYeAdapter(Context context, ShouYeBean shouYeBean) {
        this.context=context;
        this.shouYeBean=shouYeBean;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
            ViewHodlerone vh1 = new ViewHodlerone(view);
            //view.setOnClickListener(this);
            return vh1;

        } else if (viewType == TYPE_MIAO) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_miao, null);
            ViewHodlerMiao vh2 = new ViewHodlerMiao(view);
            //view.setOnClickListener(this);
            return vh2;
        } else if (viewType == TYPE_TUI) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_tui, null);
            ViewHodlerTui vh3 = new ViewHodlerTui(view);
            //view.setOnClickListener(this);
            return vh3;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHodlerone) {
            ViewHodlerone holders = (ViewHodlerone) holder;
            final Banner mbanner=holders.mbanner;
            final List<ShouYeBean.DataBean> bannerdata = shouYeBean.getData();
            for (int i = 0; i < bannerdata.size(); i++) {
                String icon = bannerdata.get(position).getIcon();
                list.add(icon);
            }
            mbanner.setImages(list);
            mbanner.setImageLoader(new GlideImageLoader());
            mbanner.setBannerAnimation(Transformer.DepthPage);
            mbanner.setDelayTime(2000);
            mbanner.start();

            mbanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (list.get(position).length()<1){
                        return;
                    }
                    Toast.makeText(context,"准备跳转到subview界面",Toast.LENGTH_LONG).show();
                    Intent webview = new Intent(context, ShouYeWebView.class);
                    webview.putExtra("webviewURl",bannerdata.get(position).getIcon());
                    context.startActivity(webview);
                }
            });

        } else if (holder instanceof ViewHodlerMiao) {
            ViewHodlerMiao mHodler = (ViewHodlerMiao) holder;
            List<ShouYeBean.MiaoshaBean.ListBeanX> beanXES = shouYeBean.getMiaosha().getList();
            GridLayoutManager manager = new GridLayoutManager(context, 1,GridLayoutManager.HORIZONTAL, false);
            mHodler.miaorecy.setLayoutManager(manager);
            MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(context, beanXES);
            mHodler.miaorecy.setAdapter(miaoShaAdapter);

            /**
             * 设置秒杀倒计时
             */
            recLen = shouYeBean.getMiaosha().getTime();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    recLen -= 1000;
                    format = new SimpleDateFormat("HH:mm:ss");
                    final String str = format.format(recLen);
                    tv_time.setText(str);
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(runnable, 100);

            mLtv.setTipList(generateTips());
        }else if (holder instanceof ViewHodlerTui) {
            ViewHodlerTui mHodler = (ViewHodlerTui) holder;

            List<ShouYeBean.TuijianBean.ListBean> beanXES = shouYeBean.getTuijian().getList();
            GridLayoutManager manager = new GridLayoutManager(context, 2, OrientationHelper.VERTICAL, false);
            mHodler.tuirecy.setLayoutManager(manager);
            TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(context, beanXES);
            mHodler.tuirecy.setAdapter(tuiJianAdapter);

        }
    }

    @Override
    public int getItemCount () {

        if (TYPE_BANNER == 0) {
            return shouYeBean.getData().size();
        } else if (TYPE_MIAO == 1) {
            return shouYeBean.getMiaosha().getList().size();
        }else if (TYPE_TUI == 2) {
            return shouYeBean.getTuijian().getList().size();
        }
        return 3;

    }

    @Override
    public int getItemViewType ( int position){

        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_MIAO;
        }else  {
            return TYPE_TUI;
        }
    }

    /**
     * 首页跑马灯效果
     *
     * @return
     */
    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        tips.add("AI就要掌控世界了？");
        tips.add("衣服大一号,人就瘦一圈?");
        tips.add("闪瞎:全球最贵五辆摩托车");
        tips.add("一半受访者会被机器人吓跑!");
        tips.add("深度学习索引速度更快");
        tips.add("轻量级生成对抗网络工具库?");
        tips.add("谷歌团队越狱苹果系统");
        return tips;
    }
    class ViewHodlerone extends RecyclerView.ViewHolder {

        private Banner mbanner;

        public ViewHodlerone(View itemView) {
            super(itemView);

            mbanner=itemView.findViewById(R.id.banner_item);
        }
    }
    class ViewHodlerMiao extends RecyclerView.ViewHolder {

        private final RecyclerView miaorecy;

        public ViewHodlerMiao(View itemView) {
            super(itemView);

            miaorecy = itemView.findViewById(R.id.miao_recy);
            tv_time = (TextView) itemView.findViewById(R.id.shouye_time);
            mLtv = (LooperTextView) itemView.findViewById(R.id.ltv);

        }
    }

    class ViewHodlerTui extends RecyclerView.ViewHolder {

        private final RecyclerView tuirecy;

        public ViewHodlerTui(View itemView) {
            super(itemView);

            tuirecy = itemView.findViewById(R.id.recy);
        }
    }
}
