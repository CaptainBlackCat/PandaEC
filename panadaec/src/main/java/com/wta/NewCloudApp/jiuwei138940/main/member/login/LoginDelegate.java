package com.wta.NewCloudApp.jiuwei138940.main.member.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BaseBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.RxBus;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.LoadingJsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.util.PreferencesUtility;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.TokenBean;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/19.
 */

public class LoginDelegate extends LatteDelegate {
    public static LoginDelegate newInstance(int oldPostion,int newPostion) {
        Bundle args = new Bundle();
        args.putInt("oldPostion",oldPostion);
        args.putInt("newPostion",newPostion);
        LoginDelegate fragment = new LoginDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R2.id.login_loginbtn)
    TextView loginLoginbtn;
    @BindView(R2.id.login_headview)
    ImageView loginHeadview;
    @BindView(R2.id.login_regist)
    TextView loginRegist;
    @BindView(R2.id.login_weixin)
    TextView loginWeixin;
    @BindView(R2.id.login_back)
    ImageView loginBack;

    @Override
    public Object setLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        loginByToken(PreferencesUtility.getInstance(_mActivity).getToken());
        testByToken(PreferencesUtility.getInstance(_mActivity).getToken());
    }

    private void testByToken(String token) {
        OkGo.<String>post("http://192.168.3.59/Public/")
                .params("service","App.Userhandle.PutUserLogin")
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("123456", "onSuccess: "+response.body());
                    }
                });
    }

    private void loginByToken(String token) {
        Toast.makeText(_mActivity, ""+token, Toast.LENGTH_SHORT).show();
        OkGo.<BaseResponse<TokenBean>>post("http://192.168.3.59/Public/")
                .params("service","App.Userhandle.PutUserLogin")
                .params("token",token)
                .params("time",System.currentTimeMillis()/1000)
                .execute(new LoadingJsonCallback<BaseResponse<TokenBean>>(_mActivity) {
                    @Override
                    public void onSuccess(Response<BaseResponse<TokenBean>> response) {
                        super.onSuccess(response);

                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        start(UserLoginDelegate.newInstance());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseBottomDelegate.LoginPostionEvent o = new BaseBottomDelegate.LoginPostionEvent();
        o.setNewPostion(getArguments().getInt("newPostion"));
        o.setOldPostion(getArguments().getInt("oldPostion"));
        o.setLogin(false);
        RxBus.getDefault().post(o);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        //登录按钮
        loginLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startAnimation(UserLoginDelegate.newInstance());
            }
        });
        //注册
        loginRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(RegistDelegate.newInstance());
            }
        });

        //调用第三方微信登录
        loginWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mActivity, "点击了", Toast.LENGTH_SHORT).show();
//                login(Wechat.NAME);
            }
        });
    }

    public void startAnimation(LatteDelegate latteDelegate){
        extraTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_pop_exit,
                        R.anim.h_fragment_pop_enter, R.anim.h_fragment_exit).start(latteDelegate);

    }
//    private void login(String platformName) {
//        LoginApi api = new LoginApi();
//        //设置登陆的平台后执行登陆的方法
//        api.setPlatform(platformName);
//        api.setOnLoginListener(new OnLoginListener() {
//            @Override
//            public boolean onLogin(String platform, HashMap<String, Object> res) {
//                String openid = (String) res.get("openid");
//                Toast.makeText(_mActivity, ""+openid, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onRegister(Usebean info) {
//                Toast.makeText(_mActivity, "触发注册", Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
//        api.login(_mActivity);
//    }

}
