package com.wta.NewCloudApp.jiuwei138940.main.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.roundwidget.QMUIRoundButton;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.togglebutton.ToggleButton;
import com.wta.NewCloudApp.jiuwei138940.main.member.login.LoginDelegate;

import butterknife.BindView;

/**
 * Created by zzc on 2017/10/12.
 */

public class SelfSelectShopDelegate extends LatteDelegate{
    @BindView(R2.id.selfselect_rl)
    RelativeLayout mRvLayout;
    @BindView(R2.id.selfselect_toggle_parent)
    ToggleButton mToggleParent;
    @BindView(R2.id.selfselect_sbt)
    QMUIRoundButton selectBtn;
    public static SelfSelectShopDelegate newInstance() {
        Bundle args = new Bundle();
        SelfSelectShopDelegate fragment = new SelfSelectShopDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_selfselect_shop;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mToggleParent.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(View view, boolean on) {
                if(on){
                    mRvLayout.setVisibility(View.VISIBLE);
                }else{
                    mRvLayout.setVisibility(View.GONE);
                }
            }
        });
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(SelectChildDelegate.newInstance());
            }
        });
    }
}
