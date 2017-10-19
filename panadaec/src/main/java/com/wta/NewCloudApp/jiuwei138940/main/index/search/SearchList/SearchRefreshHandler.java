package com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.app.Latte;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.refresh.PagingBean;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchListDataConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.WeakHashMap;


/**
 * 目前adapter类已写死，后期待完善
 */

public class SearchRefreshHandler implements SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private searchListAdapter mAdapter;
    private final SearchListDataConverter CONVERTER;
    private final Context mcontext;
    private WeakHashMap<String, Object> mparams = new WeakHashMap<>();
    private SearchListDataConverter LoadMoreDataConverter = new SearchListDataConverter();

    private RefreshYield refreshYield = null;
    private int startpage = 0;

    public boolean isHasHeadView = false;
    public boolean isHasFooterView = false;
    private List<View> headViewList = new ArrayList<>();
    private List<View> footerViewList = new ArrayList<>();


    public SearchListDataConverter getLoadMoreDataConverter() {
        return LoadMoreDataConverter;
    }

    public void setLoadMoreDataConverter(SearchListDataConverter loadMoreDataConverter) {
        LoadMoreDataConverter = loadMoreDataConverter;
    }

    private SearchRefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT,
                                 RecyclerView recyclerView,
                                 SearchListDataConverter converter,
                                 PagingBean bean, Context context, int startPage) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        this.mcontext = context;
        if (REFRESH_LAYOUT != null) {
            REFRESH_LAYOUT.setOnRefreshListener(this);
        }
        this.startpage = startPage;
    }

    public static SearchRefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                              RecyclerView recyclerView,
                                              SearchListDataConverter converter, Context context, int startPage) {
        return new SearchRefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean(), context, startPage);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        firstPage();
    }

    /**
     * 代码长到不能忍，后续有空要再次优化
     */
    public void firstPage() {
        //重置页面位置
        BEAN.setPageIndex(startpage);
        if (refreshYield == null) {
            throw new NullPointerException("refreshYield is null");
        }
        RestClient.builder()
                .url(refreshYield.getUrl())
                .loader(mcontext)
                .params(mparams)
                .params("page", BEAN.getPageIndex())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //设置Adapter
                        CONVERTER.clearData();
                        mAdapter = new searchListAdapter(CONVERTER.setJsonData(response).convert());
                        mAdapter.setOnLoadMoreListener(SearchRefreshHandler.this, RECYCLERVIEW);
                        if (isHasHeadView) {
                            for (View headerView : headViewList)
                                mAdapter.addHeaderView(headerView);

                        }
                        if (isHasFooterView) {
                            for (View footerView : footerViewList)
                                mAdapter.addFooterView(footerView);
                        }
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                        BEAN.setCurrentCount(mAdapter.getData().size());
                    }
                })
                .build()
                .get();
        if (REFRESH_LAYOUT != null) {
            REFRESH_LAYOUT.setRefreshing(false);
        }
    }

    private void paging() {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();
        if (mAdapter.getData().size() % 10 != 0) {
            mAdapter.loadMoreEnd(true);
        } else {
            pageRequest();
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        paging();
    }


    public void setMparams(WeakHashMap<String, Object> mparams) {
        this.mparams = mparams;
    }

    public void putmParams(String key, Object value) {
        if (mparams == null) {
            throw new NullPointerException("SearchRefreshHandler's params is null");
        }
        this.mparams.put(key, value);
    }

    public void removeParams(String key) {
        if (mparams == null) {
            throw new NullPointerException("SearchRefreshHandler's params is null");
        }
        this.mparams.remove(key);
    }


    /**
     * 设置下拉刷新初始值
     *
     * @param url
     */
    public void setRefreshYield(String url) {
        this.refreshYield = new RefreshYield(url);
    }

    public RefreshYield getRefreshYield() {
        return refreshYield;
    }

    public void setUrl(String url) {
        refreshYield.setUrl(url);
    }


    public void pageRequest() {
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RestClient.builder()
                        .url(refreshYield.getUrl())
                        .params("page", BEAN.getPageIndex())
                        .params(mparams)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                LoadMoreDataConverter.clearData();
                                int size = mAdapter.getData().size();
                                mAdapter.addData(LoadMoreDataConverter.setJsonData(response).convert());
                                //累加数量
                                BEAN.setCurrentCount(mAdapter.getData().size());
                                mAdapter.loadMoreComplete();
                                BEAN.addIndex();
                            }
                        })
                        .build().get();
            }
        }, 200);

    }


    /**
     * 添加headVIew
     *
     * @param headerview
     */
    public void addheaderView(View headerview) {
        isHasHeadView = true;
        headViewList.add(headerview);
    }

    public void addAllheaderView(Collection<? extends View> viewAll) {
        isHasHeadView = true;
        headViewList.addAll(viewAll);
    }

    public void addFooterView(View footerView) {
        isHasFooterView = true;
        footerViewList.add(footerView);
    }

    public void addAllFooterView(Collection<? extends View> viewAll) {
        isHasFooterView = true;
        footerViewList.addAll(viewAll);
    }
}
