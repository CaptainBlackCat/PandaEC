package com.wta.NewCloudApp.jiuwei138940.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.IFailure;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.ToastUtil;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList.SearchListDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;


public class ContentDelegate extends LatteDelegate implements BaseQuickAdapter.OnItemClickListener {

    private static final String ARG_CONTENT_URL = "CONTENT_URL";
    private static final int RECOMAND_SECTION = -1;
    private String mContentUrl = null;
    private SectionAdapter madapter = null;
    private SortDelegate parentDelegate;
    private List<MultipleItemEmity> data;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentUrl = args.getString(ARG_CONTENT_URL);
        }
    }

    public static ContentDelegate newInstance(String contentUrl) {
        final Bundle args = new Bundle();
        args.putString(ARG_CONTENT_URL, contentUrl);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    public void initData() {
        RestClient.builder()
                .url(mContentUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if (ToastUtil.ResponseCheck(getContext(), response)) {
                            Log.d("convert", response);
                            data = new SectionDataConverter().setJsonData(response).convert();
                            madapter = new SectionAdapter(data);
                            if (mRecyclerView != null) {
                                mRecyclerView.setAdapter(madapter);
                                parentDelegate.onSuccessLoading();
                            }
                        }
                        madapter.setOnItemClickListener(ContentDelegate.this);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        parentDelegate.onFailureLoading();
                    }
                })
                .build()
                .get();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        parentDelegate = getParentDelegate();
        initData();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEmity data = (MultipleItemEmity) adapter.getData().get(position);
        String url = data.getField(MultipleFields.URL);
        getParentDelegate().getParentDelegate().getSupportDelegate().start(SearchListDelegate.newInstance(url, 2));
    }
}
