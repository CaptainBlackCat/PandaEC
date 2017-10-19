package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class VOnePlusFourAdapter extends DelegateAdapter.Adapter<VOnePlusFourAdapter.ViewHolder> {
    private Context mContext;
    private IndexBean.InfoBean.DataBean infoBean;
    private int type;
    public VOnePlusFourAdapter(Context mContext, IndexBean.InfoBean.DataBean infoBean,int type) {
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
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_one_plus_four, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position)==type){
            ViewGroup.LayoutParams layoutParams = holder.layout.getLayoutParams();
            layoutParams.width=DensityUtils.getScreenW(mContext);
            layoutParams.height=DensityUtils.getScreenW(mContext)/7*3/3*4;
            holder.layout.setLayoutParams(layoutParams);
            List<IndexBean.InfoBean.DataBean.CellDataBean> cellData = infoBean.getCellData();
            Glide.with(mContext).load(cellData.get(0).getImgurl()).into(holder.img1);
            Glide.with(mContext).load(cellData.get(1).getImgurl()).into(holder.img2);
            Glide.with(mContext).load(cellData.get(2).getImgurl()).into(holder.img3);
            Glide.with(mContext).load(cellData.get(3).getImgurl()).into(holder.img4);
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
        @BindView(R2.id.one_plus_four_img1)
        ImageView img1;

        @BindView(R2.id.one_plus_four_img2)
        ImageView img2;

        @BindView(R2.id.one_plus_four_img3)
        ImageView img3;

        @BindView(R2.id.one_plus_four_img4)
        ImageView img4;

        @BindView(R2.id.one_plus_four_layout)
        LinearLayout layout;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
