package com.wta.NewCloudApp.jiuwei138940.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.setting.ConnectWxDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.setting.GuanyuDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.setting.MyInfoDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.setting.RealNameDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.util.PreferencesUtility;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/15.
 */

public class MySettingDelegate extends LatteDelegate {
    @BindView(R2.id.mysetting_ziliao)
    LinearLayout mysettingZiliao;
    @BindView(R2.id.mysetting_connectwx)
    LinearLayout mysettingConnectwx;
    @BindView(R2.id.mysetting_smrz)
    LinearLayout mysettingSmrz;
    @BindView(R2.id.mysetting_guanyu)
    LinearLayout mysettingGuanyu;
    @BindView(R2.id.mysetting_dqbb)
    LinearLayout mysettingDqbb;
@BindView(R2.id.mysetting_exit_login)
    TextView mysettingExitLogin;
    @Override
    public Object setLayout() {
        return R.layout.delegate_mysetting;
    }

    public static MySettingDelegate newInstance() {

        Bundle args = new Bundle();

        MySettingDelegate fragment = new MySettingDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mysettingZiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(MyInfoDelegate.newInstance());
            }
        });
        mysettingConnectwx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(ConnectWxDelegate.newInstance());
            }
        });
        mysettingDqbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCurrentVerstion();
            }
        });
        mysettingSmrz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(RealNameDelegate.newInstance());
            }
        });
        mysettingGuanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(GuanyuDelegate.newInstance());
            }
        });

        mysettingExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token= PreferencesUtility.getInstance(_mActivity).getToken();
                Log.i("asdf", "onClick: "+token);
                OkGo.<String>post("http://192.168.3.59/Public/")
                        .params("service","App.Userhandle.PutUserLogout")
                        .params("token",token).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("asdf", "onSuccess: "+response.body());
                        PreferencesUtility.getInstance(_mActivity).saveSession(null);
                        PreferencesUtility.getInstance(_mActivity).saveToken(null);
                    }
                });
            }
        });
    }

    private void showCurrentVerstion() {
        NiceDialog.init().setLayoutId(R.layout.dialog_current_verstion)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                        viewHolder.getView(R.id.dialog_cv_content).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                return true;
                            }
                        });
                        viewHolder.getView(R.id.dialog_cv_bg).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            baseNiceDialog.dismiss();
                        }
                    });


                    }
                })
                .setDimAmount(0.3f)     //调节灰色背景透明度[0-1]，默认0.5f
                .setOutCancel(true)     //点击dialog外是否可取消，默认true
                .show(_mActivity.getSupportFragmentManager());     //显示dialog
    }

    public void startAnimation(LatteDelegate latteDelegate) {
        extraTransaction()
                .setCustomAnimations(R.anim.v_fragment_enter, R.anim.v_fragment_pop_exit,
                        R.anim.v_fragment_pop_enter, R.anim.v_fragment_exit).start(latteDelegate);
    }


}
