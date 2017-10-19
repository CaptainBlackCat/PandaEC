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
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/10/16.
 */

public class VImageNineAdapter extends DelegateAdapter.Adapter<VImageNineAdapter.ViewHolder>  {

    private IndexBean.InfoBean.DataBean infoBean;
    private Context mContext;
    private int type;
    public VImageNineAdapter(Context mContext, IndexBean.InfoBean.DataBean infoBean, int type) {
        this.infoBean = infoBean;
        this.mContext = mContext;
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
    public VImageNineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_first_img_nine, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(VImageNineAdapter.ViewHolder holder, int position) {
        if(getItemViewType(position)==type){
            ViewGroup.LayoutParams layoutParams = holder.homeImgTitle.getLayoutParams();
            layoutParams.width=DensityUtils.getScreenW(mContext);
            layoutParams.height=layoutParams.width/75*28;
            holder.homeImgTitle.setLayoutParams(layoutParams);
            Glide.with(mContext).load(infoBean.getCellData().get(0).getImgurl()).into(holder.homeImgTitle);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.home_img_nine)
        ImageView homeImgTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
