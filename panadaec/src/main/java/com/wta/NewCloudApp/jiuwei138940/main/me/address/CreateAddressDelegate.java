package com.wta.NewCloudApp.jiuwei138940.main.me.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

/**
 * Created by zzc on 2017/9/19.
 */

public class CreateAddressDelegate extends LatteDelegate {
    public static CreateAddressDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        CreateAddressDelegate fragment = new CreateAddressDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_createaddress;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
