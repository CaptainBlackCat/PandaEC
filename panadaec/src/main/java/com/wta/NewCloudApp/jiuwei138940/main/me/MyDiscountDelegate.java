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
 * Created by zzc on 2017/9/16.
 */

public class MyDiscountDelegate extends LatteDelegate {
    @BindView(R2.id.mydiscount_tab)
    TabLayout tabLayout;
    @BindView(R2.id.mydiscount_viewpager)
    ViewPager viewPager;
    private String[] titles;
    private List<LatteDelegate> list;
    private ShopDetailFragmentAdapter adapter;

    public static MyDiscountDelegate newInstance() {

        Bundle args = new Bundle();
        MyDiscountDelegate fragment = new MyDiscountDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_mydisccount;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        titles=new String[]{"未使用","已使用","已过期"};
        list=new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(DiscountChildDelegate.newInstance(i));
        }
        initView(rootView);
    }

    protected void initView(View view) {
        viewPager.setOffscreenPageLimit(titles.length);
        adapter = new ShopDetailFragmentAdapter(this.getChildFragmentManager(), titles, list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
