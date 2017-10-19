package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.MatrixBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/9/27.
 */

public class VNavigationMatrixAdapter extends DelegateAdapter.Adapter<VNavigationMatrixAdapter.ViewHolder> {
    private Context mContext;
    private List<MatrixBean> list;

    public VNavigationMatrixAdapter(Context mContext, List<MatrixBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2, list.size() / 2);
        gridLayoutHelper.setMarginBottom(DensityUtils.dip2px(mContext,10));
        return gridLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_maxtrix, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position)==4) {
            holder.itemHomeMaxtrixThumb.setImageResource(list.get(position).getId());
            holder.itemHomeMaxtrixTitle.setText(list.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 4;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.item_home_maxtrix_title)
        TextView itemHomeMaxtrixTitle;
        @BindView(R2.id.item_home_maxtrix_thumb)
        ImageView itemHomeMaxtrixThumb;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
