package com.wta.NewCloudApp.jiuwei138940.main.member.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/19.
 */

public class RegistDelegate extends LatteDelegate {
    @BindView(R2.id.regist_back)
    ImageView mBack;
    public static RegistDelegate newInstance() {

        Bundle args = new Bundle();

        RegistDelegate fragment = new RegistDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_regist;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
