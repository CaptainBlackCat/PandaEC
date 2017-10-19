package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.upmarqueeview.UPMarqueeView;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/9/27.
 */

public class VHotBarAdapter extends DelegateAdapter.Adapter<VHotBarAdapter.ViewHolder> {
    private Context mContext;
    private IndexBean.InfoBean.DataBean infoBean;
    private int type;
    public VHotBarAdapter(Context mContext, IndexBean.InfoBean.DataBean infoBean,int type) {
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
    public VHotBarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.hot_bar, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(VHotBarAdapter.ViewHolder holder, int position) {
        if(getItemViewType(position)==type) {
            List<View> list=new ArrayList<>();
            for (int i = 0; i < infoBean.getCellData().size(); i++) {
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_marquee, holder.hotbarContent, false);
                TextView textView= (TextView) inflate;
                textView.setText(infoBean.getCellData().get(i).getTitle());
                textView.setTextSize(14);
                list.add(textView);
            }
            holder.hotbarContent.setViews(list);
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
        @BindView(R2.id.hotbar_content)
        UPMarqueeView hotbarContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
