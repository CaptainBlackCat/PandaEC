package com.wta.NewCloudApp.jiuwei138940.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomItemDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.listener.SortListener;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.sort.content.ContentDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.sort.list.VerticalListDelegate;

import butterknife.BindView;


public class SortDelegate extends BottomItemDelegate implements View.OnFocusChangeListener, SortListener, View.OnClickListener {

    @BindView(R2.id.layout)
    LinearLayoutCompat layout = null;
    @BindView(R2.id.search_center)
    AppCompatEditText mSearchView = null;
    @BindView(R2.id.sort_fail)
    LinearLayout sort_fail_ll = null;
    @BindView(R2.id.button)
    Button failBtn = null;
    private VerticalListDelegate listDelegate;
    private ContentDelegate contentDelegate;
    private View view = null;
    private LinearLayout failLayout = null;
    private EcBottomDelegate ecBottomDelegate;

    public SortDelegate(EcBottomDelegate ecBottomDelegate) {
        this.ecBottomDelegate=ecBottomDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        view = rootView;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        failBtn.setOnClickListener(this);

        listDelegate = new VerticalListDelegate();
        contentDelegate = ContentDelegate.newInstance("http://192.168.3.59/Public/?s=Category.getGoodsSort&parentid=1");
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示,默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, contentDelegate);
        mSearchView.setOnFocusChangeListener(this);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }

    @Override
    public void onFailureLoading() {
        if (layout != null && sort_fail_ll != null) {
            layout.setVisibility(View.GONE);
            sort_fail_ll.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccessLoading() {
        if (layout != null && sort_fail_ll != null) {
            layout.setVisibility(View.VISIBLE);
            sort_fail_ll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        listDelegate.setRecyclerviewData();
        contentDelegate.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();

    }
}
