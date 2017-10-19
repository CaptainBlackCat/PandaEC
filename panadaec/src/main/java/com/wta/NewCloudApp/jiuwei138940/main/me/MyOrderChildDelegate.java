package com.wta.NewCloudApp.jiuwei138940.main.me;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.OrderParentAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.OrderListBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zzc on 2017/9/14.
 */

public class MyOrderChildDelegate extends LatteDelegate {

    @BindView(R2.id.myorderchild_rv)
    RecyclerView mRecyclerView;
    private OrderParentAdapter mAdapter;
    public static MyOrderChildDelegate newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position",position);
        MyOrderChildDelegate fragment = new MyOrderChildDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_myorderchild;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(_mActivity)
                .color(ContextCompat.getColor(_mActivity,R.color.list_line))
                .size(DensityUtils.dip2px(_mActivity,10.0f))
        .build());
        mAdapter=new OrderParentAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        downLoadData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<OrderListBean>() {
                    @Override
                    public void accept(OrderListBean orderListBean) throws Exception {
                        mAdapter.addData(orderListBean.getList());
                    }
                });
    }

    private Observable<OrderListBean> downLoadData() {

      return   Observable.create(new ObservableOnSubscribe<OrderListBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<OrderListBean> sub) throws Exception {
                OrderListBean  orderListBean=new OrderListBean();
                List<OrderListBean.OrderData> orderList=new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    OrderListBean.OrderData e = new OrderListBean.OrderData();
                    e.setOrderNumber("abcdefghigk"+i);
                    List<OrderListBean.OrderData.OrderGoodsData> orderGoodsDatas=new ArrayList<>();

                    for (int j = 0; j < 5; j++) {
                        OrderListBean.OrderData.OrderGoodsData goodsData=new OrderListBean.OrderData.OrderGoodsData();
                        goodsData.setShopName("熊猫公社"+i+"号");
                        List<OrderListBean.OrderData.OrderGoodsData.Goods> goodsList=new ArrayList<>();

                        for (int k = 0; k < 5; k++) {
                            OrderListBean.OrderData.OrderGoodsData.Goods goods=new OrderListBean.OrderData.OrderGoodsData.Goods();
                            goods.setNum(k+"");
                            goods.setPrice("200.00");
                            goods.setTitle("韩国·美容养颜美白肌肤强力补水活力肌肤美容活力补水美容养颜美白肌肤强力补水活力肌肤美容活力补水");
                            goodsList.add(goods);
                        }
                        orderGoodsDatas.add(goodsData);
                        goodsData.setList(goodsList);
                    }
                    orderList.add(e);
                    e.setList(orderGoodsDatas);
                }
                orderListBean.setList(orderList);

                sub.onNext(orderListBean);
            }
        });


    }
}
