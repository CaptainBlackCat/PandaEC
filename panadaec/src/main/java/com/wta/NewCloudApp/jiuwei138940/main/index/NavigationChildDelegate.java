package com.wta.NewCloudApp.jiuwei138940.main.index;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.NavigationBean;
import java.util.List;
import butterknife.BindView;

/**
 * Created by zzc on 2017/10/5.
 */

public class NavigationChildDelegate extends LatteDelegate {
    @BindView(R2.id.navigation_child_rv)
    RecyclerView navigationChildRv;
    private List<NavigationBean> list;

    public static NavigationChildDelegate newInstance() {

        Bundle args = new Bundle();

        NavigationChildDelegate fragment = new NavigationChildDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_navigation_child;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        GridLayoutManager layout = new GridLayoutManager(_mActivity, 5);
        layout.setOrientation(GridLayoutManager.VERTICAL);
        navigationChildRv.setLayoutManager(layout);
        navigationChildRv.setAdapter(new NavigationAdapter(list));
    }

    public void setList(List<NavigationBean> list){
        this.list=list;
    }
}
