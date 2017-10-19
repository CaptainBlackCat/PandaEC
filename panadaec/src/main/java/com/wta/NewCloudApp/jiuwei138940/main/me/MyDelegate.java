package com.wta.NewCloudApp.jiuwei138940.main.me;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomItemDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.MySelfBean;
import com.wta.NewCloudApp.jiuwei138940.main.me.util.PreferencesUtility;
import com.wta.NewCloudApp.jiuwei138940.main.widget.BadgeView;

import butterknife.BindView;

/**
 * Created by Lenovo on 2017/8/29.
 */

public class MyDelegate extends BottomItemDelegate {

    @BindView(R2.id.my_integralsgin)
    LinearLayout mIntegralSgin;

    @BindView(R2.id.my_order)
    LinearLayout myOrder;

    @BindView(R2.id.my_setting_img)
    ImageView mySetting;

    @BindView(R2.id.my_integralshop)
    LinearLayout mIntegralshop;

    @BindView(R2.id.my_getdiscount)
    RelativeLayout myGetdiscount;

    @BindView(R2.id.my_discount)
    RelativeLayout myDiscount;

    @BindView(R2.id.my_tovip)
    LinearLayout myTovip;

    @BindView(R2.id.my_collect)
    LinearLayout myCollect;

    @BindView(R2.id.my_contact_kefu)
    LinearLayout myContactkefu;

    @BindView(R2.id.my_bv_dfh)
    TextView textView_dfh;
    @BindView(R2.id.my_bv_dfk)
    TextView textView_dfk;
    @BindView(R2.id.my_bv_dsh)
    TextView textView_dsh;
    @BindView(R2.id.my_bv_thh)
    TextView textView_thh;
    @BindView(R2.id.my_message)
    ImageView textView_message;
    @BindView(R2.id.my_tixing_setting)
    LinearLayout myWarnSetting;
    @BindView(R2.id.my_balance_count)
    TextView myBalanceCount;
    @BindView(R2.id.my_headview)
    ImageView myHeadview;
    @BindView(R2.id.my_level)
    TextView myLevel;
    @BindView(R2.id.my_integral_count)
    TextView myIntegralCount;
    @BindView(R2.id.my_nickname)
    TextView myNickname;
    @BindView(R2.id.my_foot)
    LinearLayout myFoot;
    @BindView(R2.id.pull_to_refresh)
    SmartRefreshLayout mPullRefreshLayout;
    private int id = 1;
    private EcBottomDelegate ecBottomDelegate;
    public MyDelegate(EcBottomDelegate ecBottomDelegate) {
    this.ecBottomDelegate=ecBottomDelegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_my;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //#27B443


    }


    @Override
    protected void initEvent() {
        super.initEvent();



        textView_dfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyOrderDelegate.newInstance(2));
            }
        });

        textView_dfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyOrderDelegate.newInstance(1));
            }
        });

        textView_dsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyOrderDelegate.newInstance(3));
            }
        });

        textView_thh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //跳到积分签到
        mIntegralSgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(IntegralDelegate.newInstance());
            }
        });

        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyOrderDelegate.newInstance(0));
            }
        });
        //我的设置
        mySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MySettingDelegate.newInstance());
            }
        });
        //积分商城
        mIntegralshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(IntegralShopDelegate.newInstance());
            }
        });
        //获取优惠券
        myGetdiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(GetdiscountDelegate.newInstance());
            }
        });
        //我的优惠券
        myDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyDiscountDelegate.newInstance());
            }
        });
        myTovip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RxBus.getDefault().post(new OnMainIntentEvent(2));

            }
        });
        myCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyCollectDelegate.newInstance());
            }
        });
        myContactkefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(ContactKefuDelegate.newInstance());
            }
        });
        //提醒设置
        myWarnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(WarnSettingDelegate.newInstance());
            }
        });
        myFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyFootDelegate.newInstance());
            }
        });
        textView_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startAnimation(LoginDelegate.newInstance());
            }
        });


    }

    public void startAnimation(LatteDelegate latteDelegate) {
        getParentDelegate().extraTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_pop_exit,
                        R.anim.h_fragment_pop_enter, R.anim.h_fragment_exit).start(latteDelegate);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(PreferencesUtility.getInstance(_mActivity)!=null) {

                getMyselfMessage(id);
            }
        }
    }

    public void getMyselfMessage(int id) {
        OkGo.<BaseResponse<MySelfBean>>get(Constants.URL_GETMYSELF_MESSGAE)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<MySelfBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<MySelfBean>> response) {
                        MySelfBean.InfoBean info = response.body().data.getInfo();
                        if (info != null) {
                            Glide.with(_mActivity).load(info.getAvatar()).into(myHeadview);

                            myNickname.setText(info.getNickname());
                            myBalanceCount.setText(info.getCredit1().getCredit2());
                            myIntegralCount.setText(info.getCredit1().getCredit1());
                            myLevel.setText(info.getLevelname());

                            BadgeView badgeView_dfk = new BadgeView(_mActivity, textView_dfk);
                            BadgeView badgeView_dfh = new BadgeView(_mActivity, textView_dfh);
                            BadgeView badgeView_dsh = new BadgeView(_mActivity, textView_dsh);
                            BadgeView badgeView_thh = new BadgeView(_mActivity, textView_thh);

                            badgeView_dfh.setBadgeMargin(20, 20);
                            badgeView_dfk.setBadgeMargin(20, 20);
                            badgeView_dsh.setBadgeMargin(20, 20);
                            badgeView_thh.setBadgeMargin(20, 20);

                            badgeView_dfk.setText(info.getDfk()+"");
                            badgeView_dfh.setText(info.getDfh()+"");
                            badgeView_dsh.setText(info.getSuccess()+"");
                            badgeView_thh.setText(info.getCg()+"");
                            if(info.getDfk()!=0){
                                badgeView_dfk.show();
                            }
                            if(info.getDfh()!=0){
                                badgeView_dfh.show();
                            }
                            if(info.getSuccess()!=0){
                                badgeView_dsh.show();
                            }
                            if(info.getCg()!=0){
                                badgeView_thh.show();
                            }


                        }
                    }

                });
    }


}
