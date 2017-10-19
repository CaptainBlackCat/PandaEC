package com.wta.NewCloudApp.jiuwei138940.main.me;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.dialog.FzDialog;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.loading.LatteLoader;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.pullRefreshLayout.QMUICenterGravityRefreshOffsetCalculator;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.pullRefreshLayout.QMUIPullRefreshLayout;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.MyCollectGoodsAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.CollectGoodsBean;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.WarnSettingBean;
import com.wta.NewCloudApp.jiuwei138940.main.widget.CustomLoadMoreView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/16.
 */

public class MyCollectDelegate extends LatteDelegate {


    @BindView(R2.id.mycollect_back)
    ImageView mycollectBack;
    @BindView(R2.id.mycollect_all)
    TextView mycollectAll;
    @BindView(R2.id.mycollect_gou)
    ImageView mycollectGou;
    @BindView(R2.id.mycollect_clear)
    ImageView mycollectClear;
    @BindView(R2.id.mycollect_rv)
    RecyclerView mRecyclerView;
    @BindView(R2.id.pull_to_refresh)
    QMUIPullRefreshLayout mPullRefreshLayout;
    @BindView(R2.id.mycollect_empty)
    LinearLayout mEmptyView;
    @BindView(R2.id.mycollect_edit)
    ImageView mycollectEdit;
    private int id=40835;
    private boolean isAll=false;
    private int page=1;
    private MyCollectGoodsAdapter adapter;
    private int pagesize=10;

    public static MyCollectDelegate newInstance() {

        Bundle args = new Bundle();

        MyCollectDelegate fragment = new MyCollectDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_mycollect;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mPullRefreshLayout.setRefreshOffsetCalculator(new QMUICenterGravityRefreshOffsetCalculator());
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(_mActivity)
                .size(DensityUtils.dip2px(_mActivity,1))
                .color(ContextCompat.getColor(_mActivity,R.color.list_line))
                .build());
        adapter = new MyCollectGoodsAdapter();
        mRecyclerView.setAdapter(adapter);
        getCollectGoods(id,page);
        LatteLoader.showLoading(_mActivity);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mycollectGou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycollectBack.setVisibility(View.VISIBLE);
                mycollectClear.setVisibility(View.GONE);
                mycollectAll.setVisibility(View.GONE);
                mycollectEdit.setVisibility(View.VISIBLE);
                mycollectGou.setVisibility(View.GONE);
                adapter.setItemEdit(false);
                adapter.selectAll(false);
            }
        });

        mycollectEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycollectBack.setVisibility(View.GONE);
                mycollectClear.setVisibility(View.VISIBLE);
                mycollectAll.setVisibility(View.VISIBLE);
                mycollectEdit.setVisibility(View.GONE);
                mycollectGou.setVisibility(View.VISIBLE);
                adapter.setItemEdit(true);
                adapter.selectAll(false);
            }
        });

        mycollectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.selectAll(!isAll);
                isAll=!isAll;
            }
        });

        mycollectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        mycollectClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearDialog();
            }
        });

        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if(adapter.isEdit()){
                    Toast.makeText(_mActivity, "现在处于编辑状态，不能刷新", Toast.LENGTH_SHORT).show();
                    adapter.loadMoreComplete();
                    return;
                }
                page++;
                getCollectGoods(id,page);
            }
        }, mRecyclerView);


        mPullRefreshLayout.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                mPullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });

    }

    private void showClearDialog() {
        final FzDialog fzDialog=new FzDialog(_mActivity);
        fzDialog.setMessage("确认删除所选商品吗?");
        fzDialog.setCancleText("取消");
        fzDialog.setCommitText("确定");
        fzDialog.setOnButtonClickListener(new FzDialog.OnButtonClickListener() {
            @Override
            public void onCancleClick() {
                fzDialog.dismiss();
            }

            @Override
            public void onCommitClick(String inputtext) {
                LatteLoader.showLoading(_mActivity);
                deleteCollectGoods(id,adapter.getDeleteStr());
            }
        });
        fzDialog.show();
    }

    public void deleteCollectGoods(final int id, String s){
        OkGo.<BaseResponse<WarnSettingBean>>post(Constants.URLDELETECOLLECTGOODS)
                .params("id",id)
                .params("goodsid",s)
                .execute(new JsonCallback<BaseResponse<WarnSettingBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<WarnSettingBean>> response) {
                        page=1;
                        getCollectGoods(id,page);
                    }
                });
    }
//
    public void getCollectGoods(final int id, final int page){
        OkGo.<BaseResponse<CollectGoodsBean>>get(Constants.URL_GETCOLLECTGOODS)
                .params("page",page)
                .params("id",id)
                .execute(new JsonCallback<BaseResponse<CollectGoodsBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<CollectGoodsBean>> response) {
                        LatteLoader.stopLoading();
                        List<CollectGoodsBean.InfoBean.ListBean> info = response.body().data.getInfo().getList();
                        if(info==null) {
                            return;
                        }
                        if(page==1){
                            adapter.setNewData(info);
                        }else{
                            adapter.addData(info);
                        }
                        if(info.size()<pagesize){
                            adapter.loadMoreEnd();
                        }else{
                            adapter.loadMoreComplete();
                        }


                        if(page==1 && adapter.getData().size()==0){
                            mEmptyView.setVisibility(View.VISIBLE);
                            return;
                        }else{
                            mEmptyView.setVisibility(View.GONE);
                        }

                        isAll=false;
                        adapter.selectAll(isAll);
                    }
                });
    }


}
