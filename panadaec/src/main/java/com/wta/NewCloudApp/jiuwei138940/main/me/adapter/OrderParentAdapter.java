package com.wta.NewCloudApp.jiuwei138940.main.me.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.OrderListBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by zzc on 2017/10/11.
 */

public class OrderParentAdapter extends BaseQuickAdapter<OrderListBean.OrderData,BaseViewHolder>{
    public OrderParentAdapter() {
        super(R.layout.item_order_parent,new ArrayList<OrderListBean.OrderData>());
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.OrderData item) {
        helper.setText(R.id.item_order_parent_number,item.getOrderNumber());
        RecyclerView rv=helper.getView(R.id.item_order_parent_rv);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                .color(ContextCompat.getColor(mContext,R.color.list_line))
                .size(DensityUtils.dip2px(mContext,0.5f))
        .build());

        OrderShopAdapter adapter=new OrderShopAdapter(item.getList());

//        OrderListBean.OrderData.OrderGoodsData goodsData = item.getList();
//        OrderChildAdapter adapter = new OrderChildAdapter(goodsData.getList());
//        View inflate = LayoutInflater.from(mContext).inflate(R.layout.head_item_order_child, rv, false);
//        TextView head_title= (TextView) inflate.findViewById(R.id.head_item_order_title);
//        head_title.setText(goodsData.getShopName());
//        adapter.addHeaderView(inflate);
        rv.setAdapter(adapter);
    }
}
