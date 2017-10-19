package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/9/27.
 */

public class VNavigationAcitivtyAdapter extends DelegateAdapter.Adapter<VNavigationAcitivtyAdapter.ViewHolder> {
    private Context mContext;
    private IndexBean.InfoBean.DataBean infoBean;
    private int type;
    public VNavigationAcitivtyAdapter(Context mContext,IndexBean.InfoBean.DataBean infoBean,int type) {
        this.mContext = mContext;
        this.infoBean = infoBean;
        this.type=type;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(infoBean.getCellData().size(),1,0,0);
        String s = infoBean.getBottomMargin();
        int bottomMargin=0;
        if(s!=null && TextUtils.isDigitsOnly(s)) {
            bottomMargin = Integer.parseInt(s);
        }
        gridLayoutHelper.setMarginBottom(DensityUtils.px2dip(mContext,bottomMargin));
        return gridLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_navigation_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(getItemViewType(position)==type) {
            ViewGroup.LayoutParams layoutParams = holder.itemHomeNavigationActivityImg.getLayoutParams();
            layoutParams.width=DensityUtils.getScreenW(mContext)/4;
            layoutParams.height=layoutParams.width/15*12;
            holder.itemHomeNavigationActivityImg.setLayoutParams(layoutParams);
            Glide.with(mContext).load(infoBean.getCellData().get(position).getImgurl()).into(holder.itemHomeNavigationActivityImg);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                }
            });
        }}

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        return infoBean.getCellData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.item_home_navigation_activity_img)
        ImageView itemHomeNavigationActivityImg;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
