package com.wta.NewCloudApp.jiuwei138940.main.member;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomItemDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.RxBus;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.event.OnMainIntentEvent;
import com.wta.NewCloudApp.jiuwei138940.main.me.MySettingDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.member.myclient.MyClientDelegate;

import butterknife.BindView;

/**
 * Created by Lenovo on 2017/8/29.
 */

public class MemberDelegate extends BottomItemDelegate {

    @BindView(R2.id.my_setting_img)
    ImageView mSetting;
    @BindView(R2.id.member_mycommune)
    TextView mCommune;
    @BindView(R2.id.back)
    ImageView mBack;
    @BindView(R2.id.member_myclient)
    TextView mClient;
    @BindView(R2.id.member_profit)
    TextView mProfit;
    @BindView(R2.id.pull_to_refresh)
    SmartRefreshLayout mPullRefreshLayout;
    @BindView(R2.id.member_select)
    TextView mSeletShop;
    private EcBottomDelegate ecBottomDelegate;
    public MemberDelegate(EcBottomDelegate ecBottomDelegate) {
        this.ecBottomDelegate=ecBottomDelegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_member;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }




    @Override
    protected void initEvent() {
        super.initEvent();

        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MySettingDelegate.newInstance());
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(new OnMainIntentEvent(0));
            }
        });
        //我的公社
        mCommune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyCommuneDelegate.newInstance());
//                startAnimation(CookieDelegate.newInstance());
            }
        });
        //我的客户
        mClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyClientDelegate.newInstance());
            }
        });

        //销售利润
        mProfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(SaleProfitDelegate.newInstance());
            }
        });

        //自选商品
        mSeletShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(SelfSelectShopDelegate.newInstance());
            }
        });
    }

    public void startAnimation(LatteDelegate latteDelegate) {
        getParentDelegate().extraTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_pop_exit,
                        R.anim.h_fragment_pop_enter, R.anim.h_fragment_exit).start(latteDelegate);
    }

}
