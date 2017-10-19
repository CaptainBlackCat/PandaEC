package com.wta.NewCloudApp.jiuwei138940.main.me.address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import butterknife.BindView;

/**
 * Created by zzc on 2017/9/19.
 */

public class AddressDelegate extends LatteDelegate {

    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.ads_new)
    RelativeLayout newAddress;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    public static AddressDelegate newInstance() {

        Bundle args = new Bundle();

        AddressDelegate fragment = new AddressDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        newAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extraTransaction()
                        .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_pop_exit,
                                R.anim.h_fragment_pop_enter, R.anim.h_fragment_exit).start(CreateAddressDelegate.newInstance());
            }
        });
    }
}
