package com.wta.NewCloudApp.jiuwei138940.main.me;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

/**
 * Created by zzc on 2017/9/16.
 */

public class GetdiscountDelegate extends LatteDelegate {
    public static GetdiscountDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        GetdiscountDelegate fragment = new GetdiscountDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_getdiscount;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
