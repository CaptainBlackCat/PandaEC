package com.wta.NewCloudApp.jiuwei138940.latte_core.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.app.Latte;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.DataConverter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleRecyclerAdapter;

import java.util.WeakHashMap;


/**
 * 待完善
 */

public class RefreshHandler<T extends MultipleRecyclerAdapter> implements SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;
    private final Context mcontext;
    private WeakHashMap<String, Object> mparams = null;

    private String url;
    private int startPage = 0;
    private RefreshYield refreshYield = null;


    private RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT,
                           RecyclerView recyclerView,
                           DataConverter converter,
                           PagingBean bean, Context context, int startPage) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        this.mcontext = context;
        if (REFRESH_LAYOUT != null) {
            REFRESH_LAYOUT.setOnRefreshListener(this);
        }
        this.startPage = startPage;
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter, Context context, int startPage) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean(), context, startPage);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        firstPage();
    }

    /**
     * 代码长到不能忍，后续有空要再次优化
     */
    public void firstPage() {
        if (refreshYield == null) {
            throw new NullPointerException("refreshYield is null");
        }
        if (mparams == null) {
            RestClient.builder()
                    .url(refreshYield.getUrl())
                    .loader(mcontext)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            final JSONObject object = JSON.parseObject(response);
                            if (!StringUtils.isEmpty(refreshYield.getTotal()))
                                BEAN.setTotal(object.getInteger(refreshYield.getTotal()));
                            if (!StringUtils.isEmpty(refreshYield.getPageSize()))
                                BEAN.setPageSize(object.getInteger(refreshYield.getPageSize()));
                            //设置Adapter
                            mAdapter = T.create(CONVERTER.setJsonData(response));
                            mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                            RECYCLERVIEW.setAdapter(mAdapter);
                            BEAN.addIndex();
                        }
                    })
                    .build()
                    .get();
        } else {
            RestClient.builder()
                    .url(refreshYield.getUrl())
                    .loader(mcontext)
                    .params(mparams)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            final JSONObject object = JSON.parseObject(response);
                            if (!StringUtils.isEmpty(refreshYield.getTotal()))
                                BEAN.setTotal(object.getInteger(refreshYield.getTotal()));
                            if (!StringUtils.isEmpty(refreshYield.getPageSize()))
                                BEAN.setPageSize(object.getInteger(refreshYield.getPageSize()));
                            //设置Adapter
                            mAdapter = T.create(CONVERTER.setJsonData(response));
                            mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                            RECYCLERVIEW.setAdapter(mAdapter);
                            BEAN.addIndex();
                        }
                    })
                    .build()
                    .get();
        }
        if (REFRESH_LAYOUT != null) {
            REFRESH_LAYOUT.setRefreshing(false);
        }
    }

    private void paging() {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();
        if (index >= pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(refreshYield.getUrl() + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mparams == null) {
            paging();
        } else {
            paging(mparams);
        }
    }

    private void paging(final WeakHashMap<String, Object> params) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();
        if (index >= pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(refreshYield.getUrl())
                            .params(params)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }
    }


    public void setMparams(WeakHashMap<String, Object> mparams) {
        this.mparams = mparams;
    }

    public void putmParams(String key, Object value) {
        if (mparams == null) {
            throw new NullPointerException("RefreshHandler's params is null");
        }
        this.mparams.put(key, value);
    }

    /**
     * 设置下拉刷新初始值
     *
     * @param url
     * @param pageSize 获取pageSize要用到的key
     * @param total    获取total的要用的key
     */
    public void setRefreshYield(String url, String pageSize, String total) {
        this.refreshYield = new RefreshYield(url, pageSize, total);
    }
}
