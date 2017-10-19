package com.wta.NewCloudApp.jiuwei138940.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.loading.LatteLoader;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.SiginRecordAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.SignRecordBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/10/11.
 */

public class SignRecordDelegate extends LatteDelegate{
    @BindView(R2.id.signrecord_rv)
    RecyclerView mRv;
    @BindView(R2.id.back)
    ImageView back;
    private SiginRecordAdapter mAdapter;
    private int page=1;
    private int id=1;

    public static SignRecordDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        SignRecordDelegate fragment = new SignRecordDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_signrecord;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
        initData();
    }

    private void initData() {
        LatteLoader.showLoading(_mActivity);
        loadSignData(id,page);
    }

    private void loadSignData(int id, int page) {
        OkGo.<BaseResponse<SignRecordBean>>get(Constants.URL_GETSIGNINDAY)
                .params("id",id)
                .params("page",page)
                .execute(new JsonCallback<BaseResponse<SignRecordBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SignRecordBean>> response) {
                        List<SignRecordBean.InfoBean.ListBean> list = response.body().data.getInfo().getList();

                        if(list!=null){
                            mAdapter.addData(list);

                            if(list.size()<response.body().data.getInfo().getPagesize()){
                                mAdapter.loadMoreEnd();
                            }else{
                                mAdapter.loadMoreComplete();
                            }

                        }
                        LatteLoader.stopLoading();

                    }
                });
    }

    private void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter=new SiginRecordAdapter();
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(_mActivity)
                .size(DensityUtils.dip2px(_mActivity,0.5f))
                .color(ContextCompat.getColor(_mActivity,R.color.list_line))
                .build()
        );
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadSignData(id,page);
            }
        },mRv);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
