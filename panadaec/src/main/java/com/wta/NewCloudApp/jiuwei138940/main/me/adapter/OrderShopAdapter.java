package com.wta.NewCloudApp.jiuwei138940.main.me.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.OrderListBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

/**
 * Created by zzc on 2017/10/11.
 */

public class OrderShopAdapter extends BaseQuickAdapter<OrderListBean.OrderData.OrderGoodsData,BaseViewHolder> {


    public OrderShopAdapter(@Nullable List<OrderListBean.OrderData.OrderGoodsData> data) {
        super(R.layout.item_order_shop,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.OrderData.OrderGoodsData item) {
        helper.setText(R.id.head_item_order_title,item.getShopName());
        RecyclerView rv=helper.getView(R.id.item_order_shop_rv);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                .color(ContextCompat.getColor(mContext,R.color.list_line))
                .size(DensityUtils.dip2px(mContext,0.5f))
                .build());
        rv.setAdapter(new OrderChildAdapter(item.getList()));
    }
}
