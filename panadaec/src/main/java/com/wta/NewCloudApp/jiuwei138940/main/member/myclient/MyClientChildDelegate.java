package com.wta.NewCloudApp.jiuwei138940.main.member.myclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.LatteLoader;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.DividerItemDecoration;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.member.adapter.MyClientAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.member.adapter.MyFansAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.MyClientBean;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.MyFansBean;
import com.wta.NewCloudApp.jiuwei138940.main.widget.CustomLoadMoreView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/20.
 */

public class MyClientChildDelegate extends LatteDelegate {

    @BindView(R2.id.myclient_rv)
    RecyclerView mRecyclerView;
    private MyClientAdapter myClientAdapter;

    private MyFansAdapter myFansAdapter;
    private int id=1;
    private int page=1;
    private int pagesize=10;
    public static MyClientChildDelegate newInstance(int postion) {

        Bundle args = new Bundle();
        args.putInt("postion",postion);
        MyClientChildDelegate fragment = new MyClientChildDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_myclient_child;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        switch (getArguments().getInt("postion")){
            case 0:
                myClientAdapter=new MyClientAdapter();
                mRecyclerView.addItemDecoration(new DividerItemDecoration(_mActivity, LinearLayoutManager.HORIZONTAL, 3, ContextCompat.getColor(_mActivity, R.color.list_line)));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                mRecyclerView.setAdapter(myClientAdapter);
                myClientAdapter.setLoadMoreView(new CustomLoadMoreView());
                myClientAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        page++;
                        loadClientData(id,page);
                    }
                }, mRecyclerView);
                break;
            case 1:
                myFansAdapter=new MyFansAdapter();
                mRecyclerView.addItemDecoration(new DividerItemDecoration(_mActivity, LinearLayoutManager.HORIZONTAL, 3, ContextCompat.getColor(_mActivity, R.color.list_line)));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                mRecyclerView.setAdapter(myFansAdapter);
                myFansAdapter.setLoadMoreView(new CustomLoadMoreView());
                myFansAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        page++;
                        loadFansData(id,page);
                    }
                }, mRecyclerView);
                break;
            default:
                break;
        }

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        switch (getArguments().getInt("postion")) {
            case 0:
                loadClientData(id,page);
                break;
            case 1:
                LatteLoader.showLoading(_mActivity);
                loadFansData(id,page);
                break;
            default:
                break;
        }
    }

    private void loadFansData(int id, int page) {
        OkGo.<BaseResponse<MyFansBean>>get(Constants.URL_MEMBER_GETMYFANS)
                .params("id",id)
                .params("page",page)
                .execute(new JsonCallback<BaseResponse<MyFansBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<MyFansBean>> response) {
                        List<MyFansBean.InfoBean.ListBean> list=response.body().data.getInfo().getList();
                        myFansAdapter.addData(list);
                        if (response.body().data.getInfo().getList().size() < pagesize) {
                            myFansAdapter.loadMoreEnd();
                        } else {
                            myFansAdapter.loadMoreComplete();
                        }
                        LatteLoader.stopLoading();
                    }
                });

    }

    private void loadClientData(int id, int page) {

        OkGo.<BaseResponse<MyClientBean>>get(Constants.URL_MEMBER_GETCLIENT)
                .params("id",id)
                .params("page",page)
                .execute(new JsonCallback<BaseResponse<MyClientBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<MyClientBean>> response) {
                        MyClientBean.InfoBean info = response.body().data.getInfo();
                        myClientAdapter.addData(info.getList());
                        if (response.body().data.getInfo().getList().size() < pagesize) {
                            myClientAdapter.loadMoreEnd();
                        } else {
                            myClientAdapter.loadMoreComplete();
                        }
                        LatteLoader.stopLoading();
                    }
                });

    }
}
