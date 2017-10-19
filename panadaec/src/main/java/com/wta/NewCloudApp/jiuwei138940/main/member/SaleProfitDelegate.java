package com.wta.NewCloudApp.jiuwei138940.main.member;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/29.
 */

public class SaleProfitDelegate extends LatteDelegate {
    @BindView(R2.id.saleprofit_back)
    ImageView mBack;
    public static SaleProfitDelegate newInstance() {

        Bundle args = new Bundle();

        SaleProfitDelegate fragment = new SaleProfitDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_saleprofit;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
