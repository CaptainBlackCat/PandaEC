package com.wta.NewCloudApp.jiuwei138940.main.sort.list;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.IFailure;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.main.sort.SortDelegate;
import java.util.List;

import butterknife.BindView;


public class VerticalListDelegate extends LatteDelegate implements View.OnClickListener {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    private SortDelegate parentDelegate;
    private List<MultipleItemEmity> data;
    private SortRecyclerAdapter adapter = null;
    private VerticalListDataConverter converter = new VerticalListDataConverter();
    private View emptyview;
    private Button restartBtn;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }


    private void initRecyclerview() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerview();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        parentDelegate = getParentDelegate();
        emptyview = View.inflate(getContext(), R.layout.sort_failure, null);
        restartBtn = (Button) emptyview.findViewById(R.id.button);
        restartBtn.setOnClickListener(this);
        setRecyclerviewData();
    }


    public void setRecyclerviewData() {
        RestClient.builder()
                .url("?s=category.getgoodssort&parentid=0")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if (response == null && response.isEmpty()) {
                            Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        } else {
                            data = converter.setJsonData(response).convert();
                            final SortDelegate delegate = getParentDelegate();
                            adapter = new SortRecyclerAdapter(data, delegate);
                            if (mRecyclerView != null) {
                                mRecyclerView.setAdapter(adapter);
                                parentDelegate.onSuccessLoading();
                            }
                        }
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
    public void onClick(View v) {
        setRecyclerviewData();
    }


}
