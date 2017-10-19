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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/10/16.
 */

public class VOnePlusTwoAdapter extends DelegateAdapter.Adapter<VOnePlusTwoAdapter.ViewHolder> {
    private Context mContext;
    private IndexBean.InfoBean.DataBean infoBean;
    private int type;
    public VOnePlusTwoAdapter(Context mContext, IndexBean.InfoBean.DataBean infoBean,int type) {
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
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_one_plus_two, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position)==type){
            ViewGroup.LayoutParams layoutParams = holder.img1.getLayoutParams();
            layoutParams.width=DensityUtils.getScreenW(mContext)/2;
            layoutParams.height=layoutParams.width/15*8;
            holder.img1.setLayoutParams(layoutParams);
            holder.img2.setLayoutParams(layoutParams);
            List<IndexBean.InfoBean.DataBean.CellDataBean> cellData = infoBean.getCellData();
            Glide.with(mContext).load(cellData.get(0).getImgurl()).into(holder.img1);
            Glide.with(mContext).load(cellData.get(1).getImgurl()).into(holder.img2);
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
        @BindView(R2.id.one_plus_two_img1)
        ImageView img1;

        @BindView(R2.id.one_plus_two_img2)
        ImageView img2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
