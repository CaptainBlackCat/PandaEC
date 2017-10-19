package com.wta.NewCloudApp.jiuwei138940.main.me;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.ShopDetailFragmentAdapter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/14.
 */

public class MyOrderDelegate extends LatteDelegate {
    @BindView(R2.id.myorder_tab)
    TabLayout myorderTab;
    @BindView(R2.id.myorder_viewpager)
    ViewPager myorderViewpager;
    private String[] titles;
    private List<LatteDelegate> list;

    public static MyOrderDelegate newInstance(int position) {
        
        Bundle args = new Bundle();
        args.putInt("position",position);
        MyOrderDelegate fragment = new MyOrderDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_myorder;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        titles = new String[]{"全部", "待付款", "待发货", "待收货", "已完成"};
        myorderViewpager.setOffscreenPageLimit(titles.length);
        list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(MyOrderChildDelegate.newInstance(i));
        }
        myorderTab.addTab(myorderTab.newTab());
        myorderTab.addTab(myorderTab.newTab());
        myorderTab.addTab(myorderTab.newTab());
        myorderTab.addTab(myorderTab.newTab());
        myorderTab.addTab(myorderTab.newTab());
        myorderViewpager.setOffscreenPageLimit(titles.length);
        ShopDetailFragmentAdapter shopDetailFragmentAdapter = new ShopDetailFragmentAdapter(this.getChildFragmentManager(), titles, list);
        myorderViewpager.setAdapter(shopDetailFragmentAdapter);
        myorderTab.setupWithViewPager(myorderViewpager);

        myorderViewpager.setCurrentItem(getArguments().getInt("position"),false);
    }

}
