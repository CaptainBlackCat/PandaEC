package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.bumptech.glide.Glide;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexBean;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/9/28.
 */

public class VOnePlusAdapter extends DelegateAdapter.Adapter<VOnePlusAdapter.ViewHolder> {
    private Context mContext;
    private IndexBean.InfoBean.DataBean infoBean;
    private Random random=new Random();
    public VOnePlusAdapter(Context mContext,IndexBean.InfoBean.DataBean infoBean) {
        this.mContext = mContext;
        this.infoBean = infoBean;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper();
        onePlusNLayoutHelper.setMarginBottom(DensityUtils.dip2px(mContext,10));
        onePlusNLayoutHelper.setColWeights(new float[]{40f});
        String s = infoBean.getBottomMargin();
        int bottomMargin=0;
        if(s!=null && TextUtils.isDigitsOnly(s)) {
            bottomMargin = Integer.parseInt(s);
        }
        onePlusNLayoutHelper.setMarginBottom(DensityUtils.px2dip(mContext,bottomMargin));
        return onePlusNLayoutHelper;
    }

    @Override
    public VOnePlusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_oneplus, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(VOnePlusAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position)==5){
            Glide.with(mContext).load(infoBean.getCellData().get(position).getImgurl()).into(holder.itemHomeOneplusThumb);
        }
    }

    @Override
    public int getItemCount() {
        return infoBean.getCellData().size();
    }

    @Override
    public int getItemViewType(int position) {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R2.id.item_home_oneplus_thumb)
        ImageView itemHomeOneplusThumb;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
