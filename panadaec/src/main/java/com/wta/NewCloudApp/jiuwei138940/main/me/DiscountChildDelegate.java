package com.wta.NewCloudApp.jiuwei138940.main.me;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/16.
 */

public class DiscountChildDelegate extends LatteDelegate {
    @BindView(com.wta.NewCloudApp.jiuwei138940.R2.id.postion)
    TextView postion;

    public static DiscountChildDelegate newInstance(int i) {

        Bundle args = new Bundle();
        args.putInt("postion", i);
        DiscountChildDelegate fragment = new DiscountChildDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_child_discount;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        postion.setText(getArguments().getInt("postion")+"");
    }
}
