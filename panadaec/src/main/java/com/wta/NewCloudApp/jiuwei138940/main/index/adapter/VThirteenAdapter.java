package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
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

public class VThirteenAdapter extends DelegateAdapter.Adapter<VThirteenAdapter.ViewHolder> {

    private IndexBean.InfoBean.DataBean infoBean;
    private Context mContext;
    private int type;

    public VThirteenAdapter(Context mContext, IndexBean.InfoBean.DataBean infoBean, int type) {
        this.infoBean = infoBean;
        this.mContext = mContext;
        this.type=type;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper linearLayoutHelper = new GridLayoutHelper(2,infoBean.getCellData().size());
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
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_integral_shop, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position)==type) {
            List<IndexBean.InfoBean.DataBean.CellDataBean> cellData = infoBean.getCellData();
            holder.price.setText("￥"+cellData.get(position).getPrice());
            holder.title.setText(cellData.get(position).getGoodstitle());
            Glide.with(mContext).load(cellData.get(position).getThumb()).into(holder.thumb);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        return infoBean.getCellData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.item_integralshop_credit)
        TextView price;
        @BindView(R2.id.item_integralshop_thumb)
        ImageView thumb;
        @BindView(R2.id.item_integralshop_title)
        TextView title;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
