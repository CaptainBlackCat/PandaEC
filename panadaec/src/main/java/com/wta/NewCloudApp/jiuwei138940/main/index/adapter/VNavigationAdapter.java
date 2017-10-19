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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/9/27.
 */

public class VNavigationAdapter extends DelegateAdapter.Adapter<VNavigationAdapter.ViewHolder> {
    private Context mContext;
    private IndexBean.InfoBean.DataBean infoBean;
    private int type;
    public VNavigationAdapter(Context context,IndexBean.InfoBean.DataBean infoBean,int type) {
        this.mContext = context;
        this.infoBean = infoBean;
        this.type = type;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(5,infoBean.getCellData().size());
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
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_navigation, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position)==type) {
            holder.title.setText(infoBean.getCellData().get(position).getText());
            Glide.with(mContext).load(infoBean.getCellData().get(position).getImgurl()).into(holder.thumb);
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
        @BindView(R2.id.item_home_navigation_title)
        TextView title;
        @BindView(R2.id.item_home_navigation_thumb)
        ImageView thumb;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
