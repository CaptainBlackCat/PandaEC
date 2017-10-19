package com.wta.NewCloudApp.jiuwei138940.main.member.myclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.LatteLoader;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.ShopDetailFragmentAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.MyClientBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/20.
 */

public class MyClientDelegate extends LatteDelegate {
    @BindView(R2.id.myclient_tab)
    TabLayout mTabLayout;
    @BindView(R2.id.myclient_viewpage)
    ViewPager mViewPager;
    @BindView(R2.id.myclient_title_sum)
    TextView mTitles;
    private String[] titles;
    private List<LatteDelegate> list;

    public static MyClientDelegate newInstance() {

        Bundle args = new Bundle();

        MyClientDelegate fragment = new MyClientDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_myclient;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        showTabData(1,1);
//        titles=new String[]{"社员(0)","粉丝(0)"};
//        list=new ArrayList<>();
//        for (int i = 0; i < titles.length; i++) {
//            mTabLayout.addTab(mTabLayout.newTab());
//            list.add(MyClientChildDelegate.newInstance(i));
//        }
//        mViewPager.setAdapter(new ShopDetailFragmentAdapter(getChildFragmentManager(),titles,list));
//        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void showTabData(int id,int page) {
        LatteLoader.showLoading(_mActivity);
        OkGo.<BaseResponse<MyClientBean>>get(Constants.URL_MEMBER_GETCLIENT)
                .params("id",id)
                .params("page",page)
                .execute(new JsonCallback<BaseResponse<MyClientBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<MyClientBean>> response) {
                        MyClientBean.InfoBean info = response.body().data.getInfo();
                        titles=new String[]{"社员("+info.getSum()+")","粉丝("+info.getFans()+")"};
                        list=new ArrayList<>();
                        for (int i = 0; i < titles.length; i++) {
                            mTabLayout.addTab(mTabLayout.newTab());
                            list.add(MyClientChildDelegate.newInstance(i));
                        }
                        mViewPager.setAdapter(new ShopDetailFragmentAdapter(getChildFragmentManager(),titles,list));
                        mTabLayout.setupWithViewPager(mViewPager);
                        mTitles.setText("我的客户("+(Integer.parseInt(info.getSum())+Integer.parseInt(info.getFans()))+")");
                    }


                });
    }


}
