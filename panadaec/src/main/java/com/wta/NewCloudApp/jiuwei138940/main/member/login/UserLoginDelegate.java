package com.wta.NewCloudApp.jiuwei138940.main.member.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.MD5;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.LoadingJsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.util.PreferencesUtility;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.TokenBean;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/23.
 */

public class UserLoginDelegate extends LatteDelegate {
    @BindView(R2.id.login_back)
    ImageView loginBack;
    @BindView(R2.id.login_headview)
    ImageView loginHeadview;
    @BindView(R2.id.user_login_username)
    EditText userLoginUsername;
    @BindView(R2.id.user_login_passwrod)
    EditText userLoginPasswrod;
    @BindView(R2.id.user_login_loginbtn)
    TextView userLoginLoginbtn;
    @BindView(R2.id.user_login_clear)
    ImageView userLoginClear;
    @BindView(R2.id.user_login_see)
    ImageView userLoginSee;

    private boolean isSee=false;

    public static UserLoginDelegate newInstance() {

        Bundle args = new Bundle();

        UserLoginDelegate fragment = new UserLoginDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }



    @Override
    protected void initEvent() {
        super.initEvent();

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        userLoginClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginUsername.setText("");
            }
        });

        userLoginSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mActivity, "呵呵", Toast.LENGTH_SHORT).show();
                if(isSee){
                    userLoginSee.setImageResource(R.drawable.nosee);
                    userLoginPasswrod.setTransformationMethod(PasswordTransformationMethod.getInstance());


                    isSee=false;
                }else{
                    userLoginSee.setImageResource(R.drawable.see);
                    userLoginPasswrod.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    isSee=true;
                }
            }
        });

        userLoginLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uname=userLoginUsername.getText().toString().trim();
                String password=userLoginPasswrod.getText().toString().trim();
                String time=System.currentTimeMillis()/1000+"";
                String key="song!*)zai&@&jiang&@#gao)$)dong%*(xing";
                String signature= MD5.ToMD5(uname+password+key+time);
                OkGo.<BaseResponse<TokenBean>>post("http://192.168.3.59/Public/")
                        .params("service","App.Userhandle.PutUserLogin")
                        .params("uname",uname)
                        .params("pwd",password)
                        .params("time",time)
                        .params("signature",signature).execute(new LoadingJsonCallback<BaseResponse<TokenBean>>(_mActivity) {
                    @Override
                    public void onSuccess(Response<BaseResponse<TokenBean>> response) {
                        super.onSuccess(response);
                        TokenBean.InfoBean info = response.body().data.getInfo();
                        String token = info.getToken();
                        Toast.makeText(_mActivity, "登录成功"+token, Toast.LENGTH_SHORT).show();
                        PreferencesUtility.getInstance(_mActivity).saveToken(token);
                        PreferencesUtility.getInstance(_mActivity).saveSession(info.getKey()+"="+info.getCookie());
                        UserLoginDelegate.this.popTo(EcBottomDelegate.class,true);
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        userLoginLoginbtn.setClickable(true);
                        Toast.makeText(_mActivity, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
