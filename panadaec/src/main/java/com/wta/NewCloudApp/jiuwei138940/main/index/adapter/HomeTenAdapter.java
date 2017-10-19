package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexBean;

import java.util.List;

/**
 * Created by zzc on 2017/10/16.
 */

class HomeTenAdapter extends BaseQuickAdapter<IndexBean.InfoBean.DataBean.CellDataBean,BaseViewHolder> {

    public HomeTenAdapter(@Nullable List<IndexBean.InfoBean.DataBean.CellDataBean> data) {
        super(R.layout.item_home_ten,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexBean.InfoBean.DataBean.CellDataBean item) {
        ImageView imageView=helper.getView(R.id.item_home_ten_img);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width= (DensityUtils.getScreenW(mContext)-60)/3;
        layoutParams.height=layoutParams.width/11*6;
        helper.setText(R.id.item_home_ten_text,item.getText());
        Glide.with(mContext).load(item.getImgurl()).into(imageView);
    }
}
