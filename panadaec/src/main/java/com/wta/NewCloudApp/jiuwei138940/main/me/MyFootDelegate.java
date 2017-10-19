package com.wta.NewCloudApp.jiuwei138940.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.MyFootGoodsAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.FootBean;
import com.wta.NewCloudApp.jiuwei138940.main.widget.CustomLoadMoreView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/10/7.
 */

public class MyFootDelegate extends LatteDelegate {
    @BindView(R2.id.myfoot_back)
    ImageView myfootBack;
    @BindView(R2.id.myfoot_all)
    TextView myfootAll;
    @BindView(R2.id.myfoot_gou)
    ImageView myfootGou;
    @BindView(R2.id.myfoot_clear)
    ImageView myfootClear;
    @BindView(R2.id.myfoot_rv)
    RecyclerView myfootRv;
    @BindView(R2.id.pull_to_refresh)
    QMUIPullRefreshLayout pullToRefresh;
    @BindView(R2.id.myfoot_edit)
    ImageView myfootEdit;
    private boolean isAll=false;

    private MyFootGoodsAdapter adapter;

    private int id=5;
    private int page=1;
    private int pagesize=10;

    public static MyFootDelegate newInstance() {

        Bundle args = new Bundle();

        MyFootDelegate fragment = new MyFootDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_myfoot;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
        initData();

    }

    private void initData() {
        getFootGoods(id,page);
//        loadFootGoods(id,page);
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
                deleteFootGoods(id,adapter.getDeleteStr());
            }
        });
        fzDialog.show();
    }

    public void deleteFootGoods(final int id, String s){
        OkGo.<String>post(Constants.URL_DELETERECORD)
                .params("id",id)
                .params("goodsid",s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        page=1;
                        getFootGoods(id,page);
                    }
                });
    }

    private void getFootGoods(int id, final int page) {
        if(page==1){
            LatteLoader.showLoading(_mActivity);
        }
        OkGo.<BaseResponse<FootBean>>get(Constants.URL_GETMYRECORD)
                .params("page",page)
                .params("id",id)
                .execute(new JsonCallback<BaseResponse<FootBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<FootBean>> response) {
                        List<FootBean.InfoBean.ListBean> info = response.body().data.getInfo().getList();
                        if(info==null)
                            return;
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

                        LatteLoader.stopLoading();
                        isAll=false;
                        adapter.selectAll(isAll);
                    }
                });
    }

//    private void loadFootGoods(int id,final int page){
//        OkGo.<BaseResponse<FootBean>>get(Constants.URL_GETMYRECORD)
//                .params("id",id)
//                .params("page",page)
//                .converter(new JsonCallback<BaseResponse<FootBean>>() {
//                    @Override
//                    public void onSuccess(Response<BaseResponse<FootBean>> response) {
//
//                    }
//                })
//                .adapt(new ObservableBody<BaseResponse<FootBean>>())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Function<BaseResponse<FootBean>, ObservableSource<FootBean.InfoBean.ListBean>>() {
//
//                    @Override
//                    public ObservableSource<FootBean.InfoBean.ListBean> apply(@NonNull BaseResponse<FootBean> footBeanBaseResponse) throws Exception {
//                        return Observable.fromIterable(footBeanBaseResponse.data.getInfo().getList());
//                    }
//                })
//                .filter(new Predicate<FootBean.InfoBean.ListBean>() {
//                    @Override
//                    public boolean test(@NonNull FootBean.InfoBean.ListBean listBean) throws Exception {
//                        return !TextUtils.isEmpty(listBean.getTitle());
//                    }
//                })
//                .toList()
//                .subscribe(new Consumer<List<FootBean.InfoBean.ListBean>>() {
//                    @Override
//                    public void accept(List<FootBean.InfoBean.ListBean> listBeen) throws Exception {
//                        if(listBeen==null)
//                            return;
//                        if(page==1){
//                            adapter.setNewData(listBeen);
//                        }else{
//                            adapter.addData(listBeen);
//                        }
//                        if(listBeen.size()<pagesize){
//                            adapter.loadMoreEnd();
//                        }else{
//                            adapter.loadMoreComplete();
//                        }
//                    }
//                });
//    }

    private void initView() {
        pullToRefresh.setRefreshOffsetCalculator(new QMUICenterGravityRefreshOffsetCalculator());
        myfootRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        pullToRefresh.setRefreshOffsetCalculator(new QMUICenterGravityRefreshOffsetCalculator());
        myfootRv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(_mActivity)
                .size(DensityUtils.dip2px(_mActivity,1))
                .color(ContextCompat.getColor(_mActivity,R.color.list_line))
                .build());
        adapter = new MyFootGoodsAdapter();
        myfootRv.setAdapter(adapter);
    }


    @Override
    protected void initEvent() {
        super.initEvent();

        myfootBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        myfootGou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfootBack.setVisibility(View.VISIBLE);
                myfootClear.setVisibility(View.GONE);
                myfootAll.setVisibility(View.GONE);
                myfootEdit.setVisibility(View.VISIBLE);
                myfootGou.setVisibility(View.GONE);
                adapter.setItemEdit(false);
            }
        });

        myfootEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfootBack.setVisibility(View.GONE);
                myfootClear.setVisibility(View.VISIBLE);
                adapter.setItemEdit(true);
                myfootAll.setVisibility(View.VISIBLE);
                myfootEdit.setVisibility(View.GONE);
                myfootGou.setVisibility(View.VISIBLE);
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
                getFootGoods(id,page);
            }
        }, myfootRv);

        myfootClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearDialog();
            }
        });

        myfootAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.selectAll(!isAll);
                isAll=!isAll;
            }
        });

        pullToRefresh.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                pullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefresh.finishRefresh();
                    }
                }, 2000);
            }
        });
    }
}
