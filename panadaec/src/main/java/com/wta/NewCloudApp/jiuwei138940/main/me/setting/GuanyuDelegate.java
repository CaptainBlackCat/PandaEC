package com.wta.NewCloudApp.jiuwei138940.main.me.setting;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

/**
 * Created by zzc on 2017/9/15.
 */

public class GuanyuDelegate extends LatteDelegate {
    public static GuanyuDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        GuanyuDelegate fragment = new GuanyuDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_guanyu;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
