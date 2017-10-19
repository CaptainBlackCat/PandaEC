package com.wta.NewCloudApp.jiuwei138940.main.me;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

/**
 * Created by zzc on 2017/9/16.
 */

public class ContactKefuDelegate extends LatteDelegate {
    public static ContactKefuDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        ContactKefuDelegate fragment = new ContactKefuDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_contactkefu;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
