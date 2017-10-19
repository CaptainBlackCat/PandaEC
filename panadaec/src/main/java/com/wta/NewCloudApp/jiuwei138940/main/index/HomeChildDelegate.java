package com.wta.NewCloudApp.jiuwei138940.main.index;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VBannerAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VHotBarAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VImageAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VImageNineAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VNavigationAcitivtyAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VNavigationAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VNineAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VOnePlusFourAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VOnePlusThreeAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VOnePlusTwoAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VTenAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VThirteenAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.VTwelveAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/26.
 */

public class HomeChildDelegate extends LatteDelegate {

    private VirtualLayoutManager layoutManager;
    private int page=1;
    public static HomeChildDelegate newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id",id);
        HomeChildDelegate fragment = new HomeChildDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R2.id.home_child_rv)
    RecyclerView homeFirstRv;
    @BindView(R2.id.pull_to_refresh)
    SmartRefreshLayout mPullRefreshLayout;
    private List<DelegateAdapter.Adapter> adapters;
    private DelegateAdapter delegateAdapter;


    @Override
    public Object setLayout() {
        return R.layout.delegate_home_child;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }

    @Override
    protected void initEvent() {
        mPullRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                downLoadData(getArguments().getString("id"),page);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                adapters=new ArrayList<>();
                mPullRefreshLayout.setLoadmoreFinished(false);
                downLoadData(getArguments().getString("id"),page);
                mPullRefreshLayout.setEnableLoadmore(true);
            }
        });
    }

    protected void initView() {
        layoutManager = new VirtualLayoutManager(_mActivity);
        homeFirstRv.setLayoutManager(layoutManager);

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        homeFirstRv.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(6, 20);

        delegateAdapter = new DelegateAdapter(layoutManager, true);
        homeFirstRv.setAdapter(delegateAdapter);
        adapters = new LinkedList<>();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mPullRefreshLayout.autoRefresh();
    }

    /**
     * @param id 首页样式id
     */
    public void downLoadData(String id, final int page){
        Toast.makeText(_mActivity, ""+id, Toast.LENGTH_SHORT).show();
        OkGo.<BaseResponse<IndexBean>>post(Constants.URL_GETINDEXDATA)
                .params("id",id)
                .params("page",page)
                .execute(new JsonCallback<BaseResponse<IndexBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<IndexBean>> response) {

                        if(response.body().data.getInfo().getData().size()<response.body().data.getInfo().getPagesize()){
                            mPullRefreshLayout.setEnableLoadmore(false);
                        }

                        List<IndexBean.InfoBean.DataBean> info = response.body().data.getInfo().getData();
                        adapterData(info);
                        if(page==1){
                            mPullRefreshLayout.finishRefresh();
                            return;
                        }
                        mPullRefreshLayout.finishLoadmore();
                    }
                });
    }

    private void adapterData(List<IndexBean.InfoBean.DataBean> info) {


        for (int i = 0; i < info.size(); i++) {
            IndexBean.InfoBean.DataBean infoBean = info.get(i);
            if(TextUtils.isEmpty(infoBean.getCellType()) || !TextUtils.isDigitsOnly(infoBean.getCellType()))
                continue;
            switch (Integer.parseInt(infoBean.getCellType())) {

                case 1:
                    adapters.add(new VBannerAdapter(_mActivity, infoBean,1));
                    break;
                case 2:
                    adapters.add(new VNavigationAcitivtyAdapter(_mActivity,infoBean,2));
                    break;
                case 3:
                    adapters.add(new VNavigationAdapter(_mActivity,infoBean,3));
                    break;
                case 4:
                    adapters.add(new VHotBarAdapter(_mActivity,infoBean,4));
                    break;
                case 5:
                    adapters.add(new VOnePlusTwoAdapter(_mActivity,infoBean,5));
                    break;
                case 6:
                    adapters.add(new VOnePlusThreeAdapter(_mActivity,infoBean,6));
                    break;
                case 7:
                    adapters.add(new VOnePlusFourAdapter(_mActivity,infoBean,7));
                    break;
                case 8:
                    adapters.add(new VImageAdapter(_mActivity,infoBean,8));
                    break;
                case 9:
                    adapters.add(new VImageNineAdapter(_mActivity,infoBean,9));
                    break;
                case 10:
                    adapters.add(new VNineAdapter(_mActivity,infoBean,10));
                    break;
                case 11:
                    adapters.add(new VTenAdapter(_mActivity,infoBean,11));
                    break;
                case 12:
                    adapters.add(new VTwelveAdapter(_mActivity,infoBean,12));
                    break;
                case 13:
                    adapters.add(new VThirteenAdapter(_mActivity,infoBean,13));
                    break;
                default:
                    break;
            }
        }
        delegateAdapter.setAdapters(adapters);
    }
}
