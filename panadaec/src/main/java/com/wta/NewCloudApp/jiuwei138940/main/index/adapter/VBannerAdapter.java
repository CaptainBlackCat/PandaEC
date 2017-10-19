package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexBean;
import com.wta.NewCloudApp.jiuwei138940.main.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/9/27.
 */

public class VBannerAdapter extends DelegateAdapter.Adapter<VBannerAdapter.ViewHolder> {
    private Context mContext;
    private IndexBean.InfoBean.DataBean infoBean;
    private int type;
    public VBannerAdapter(Context mContext,IndexBean.InfoBean.DataBean infoBean,int type) {
        this.mContext = mContext;
        this.infoBean = infoBean;
        this.type=type;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        String s = infoBean.getBottomMargin();
        int bottomMargin=0;
        if(s!=null && TextUtils.isDigitsOnly(s)) {
            bottomMargin = Integer.parseInt(s);
        }
        linearLayoutHelper.setMarginBottom(DensityUtils.px2dip(mContext,bottomMargin));
        return linearLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_first_banner, parent, false);

            return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("qwe", "onBindViewHolder0: "+position);
        if(getItemViewType(position)==type) {
            holder.homeFirstBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            ViewGroup.LayoutParams layoutParams = holder.homeFirstBanner.getLayoutParams();
            layoutParams.width=DensityUtils.getScreenW(mContext);
            layoutParams.height=layoutParams.width/15*7;
            holder.homeFirstBanner.setLayoutParams(layoutParams);
            //        //设置图片加载器
            holder.homeFirstBanner.setImageLoader(new GlideImageLoader());
            //        //设置banner动画效果
            holder.homeFirstBanner.setBannerAnimation(Transformer.DepthPage);
            //        //设置自动轮播，默认为true
            //        mBanner.isAutoPlay(true);
            //        //设置轮播时间
            holder.homeFirstBanner.setDelayTime(3000);
            List<String> list=new ArrayList<>();
            for (int i = 0; i < infoBean.getCellData().size(); i++) {
                list.add(infoBean.getCellData().get(i).getImgurl());
            }
            holder.homeFirstBanner.setImages(list);
            holder.homeFirstBanner.start();
            holder.homeFirstBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R2.id.home_first_banner)
        Banner homeFirstBanner;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
