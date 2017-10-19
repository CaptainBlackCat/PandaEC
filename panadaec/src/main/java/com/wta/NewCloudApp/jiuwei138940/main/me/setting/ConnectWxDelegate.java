package com.wta.NewCloudApp.jiuwei138940.main.me.setting;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

/**
 * Created by zzc on 2017/9/15.
 */

public class ConnectWxDelegate extends LatteDelegate {
    public static ConnectWxDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        ConnectWxDelegate fragment = new ConnectWxDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_connectwx;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
