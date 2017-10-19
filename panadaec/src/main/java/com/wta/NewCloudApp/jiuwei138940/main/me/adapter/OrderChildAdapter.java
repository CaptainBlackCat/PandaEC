package com.wta.NewCloudApp.jiuwei138940.main.me.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.OrderListBean;

import java.util.List;

/**
 * Created by zzc on 2017/10/11.
 */

public class OrderChildAdapter extends BaseQuickAdapter<OrderListBean.OrderData.OrderGoodsData.Goods,BaseViewHolder>{

    public OrderChildAdapter(@Nullable List<OrderListBean.OrderData.OrderGoodsData.Goods> data) {
        super(R.layout.item_order_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.OrderData.OrderGoodsData.Goods item) {
        helper.setText(R.id.item_order_child_title,item.getTitle())
                .setText(R.id.item_order_child_price,"￥"+item.getPrice())
                .setText(R.id.item_order_child_count,"x"+item.getNum());
    }
}
