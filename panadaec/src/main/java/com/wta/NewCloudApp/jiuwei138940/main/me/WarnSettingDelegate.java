package com.wta.NewCloudApp.jiuwei138940.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.togglebutton.ToggleButton;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.WarnSettingBean;
import com.wta.NewCloudApp.jiuwei138940.main.widget.ReboundScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/19.
 */

public class WarnSettingDelegate extends LatteDelegate {
    @BindView(R2.id.warnsetting_rebound)
    ReboundScrollView reboundScrollView;
    @BindView(R2.id.warnsetting_submit)
    ToggleButton warnsettingSubmit;
    @BindView(R2.id.warnsetting_carrier)
    ToggleButton warnsettingCarrier;
    @BindView(R2.id.warnsetting_cancel)
    ToggleButton warnsettingCancel;
    @BindView(R2.id.warnsetting_pay)
    ToggleButton warnsettingPay;
    @BindView(R2.id.warnsetting_send)
    ToggleButton warnsettingSend;
    @BindView(R2.id.warnsetting_finis)
    ToggleButton warnsettingFinis;
    @BindView(R2.id.warnsetting_refund)
    ToggleButton warnsettingRefund;
    @BindView(R2.id.warnsetting_refunding)
    ToggleButton warnsettingRefunding;
    @BindView(R2.id.warnsetting_refund1)
    ToggleButton warnsettingRefund1;
    @BindView(R2.id.warnsetting_refund2)
    ToggleButton warnsettingRefund2;
    @BindView(R2.id.warnsetting_upgrade)
    ToggleButton warnsettingUpgrade;
    @BindView(R2.id.warnsetting_recharge_ok)
    ToggleButton warnsettingRechargeOk;
    @BindView(R2.id.warnsetting_recharge_refund)
    ToggleButton warnsettingRechargeRefund;
    @BindView(R2.id.warnsetting_withdraw)
    ToggleButton warnsettingWithdraw;
    @BindView(R2.id.warnsetting_withdraw_ok)
    ToggleButton warnsettingWithdrawOk;
    @BindView(R2.id.warnsetting_withdraw_fail)
    ToggleButton warnsettingWithdrawFail;
    @BindView(R2.id.warnsetting_commission_become)
    ToggleButton warnsettingCommissionBecome;
    @BindView(R2.id.warnsetting_commission_upgrade)
    ToggleButton warnsettingCommissionUpgrade;
    @BindView(R2.id.warnsetting_commission_agent_new)
    ToggleButton warnsettingCommissionAgentNew;
    @BindView(R2.id.warnsetting_commission_order_pay)
    ToggleButton warnsettingCommissionOrderPay;
    @BindView(R2.id.warnsetting_commission_order_finish)
    ToggleButton warnsettingCommissionOrderFinish;
    @BindView(R2.id.warnsetting_commission_apply)
    ToggleButton warnsettingCommissionApply;
    @BindView(R2.id.warnsetting_commission_check)
    ToggleButton warnsettingCommissionCheck;
    @BindView(R2.id.warnsetting_commission_pay)
    ToggleButton warnsettingCommissionPay;
    private List<String> list_current;
    private List<String> list_params;
    private List<ToggleButton> list_btn;
    private int id=34039;
    public static WarnSettingDelegate newInstance() {

        Bundle args = new Bundle();

        WarnSettingDelegate fragment = new WarnSettingDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_warnsetting;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        reboundScrollView.setOnTopRefreshListener(new ReboundScrollView.OnTopRefreshListener() {
            @Override
            public void onTopRefresh() {
                reboundScrollView.setBackgroundColor(ContextCompat.getColor(_mActivity, R.color.red_f7443e));
            }
        });
        reboundScrollView.setOnButtomRefreshListener(new ReboundScrollView.OnButtomRefreshListener() {
            @Override
            public void onButtomRefresh() {
                reboundScrollView.setBackgroundColor(ContextCompat.getColor(_mActivity, R.color.bg_all));
            }
        });
        initSetting(id);
    }

    protected void initView(View view) {
        list_params = new ArrayList<>();
        list_btn=new ArrayList<>();
        list_params.add("submit");
        list_btn.add(warnsettingSubmit);
        list_params.add("carrier");
        list_btn.add(warnsettingCarrier);
        list_params.add("cancel");
        list_btn.add(warnsettingCancel);
        list_params.add("pay");
        list_btn.add(warnsettingPay);
        list_params.add("send");
        list_btn.add(warnsettingSend);
        list_params.add("finish");
        list_btn.add(warnsettingFinis);
        list_params.add("refund");
        list_btn.add(warnsettingRefund);
        list_params.add("refunding");
        list_btn.add(warnsettingRefunding);
        list_params.add("refund1");
        list_btn.add(warnsettingRefund1);
        list_params.add("refund2");
        list_btn.add(warnsettingRefund2);
        list_params.add("upgrade");
        list_btn.add(warnsettingUpgrade);
        list_params.add("recharge_ok");
        list_btn.add(warnsettingRechargeOk);
        list_params.add("recharge_refund");
        list_btn.add(warnsettingRechargeRefund);
        list_params.add("withdraw");
        list_btn.add(warnsettingWithdraw);
        list_params.add("withdraw_ok");
        list_btn.add(warnsettingWithdrawOk);
        list_params.add("withdraw_fail");
        list_btn.add(warnsettingWithdrawFail);
        list_params.add("commission_become");
        list_btn.add(warnsettingCommissionBecome);
        list_params.add("commission_upgrade");
        list_btn.add(warnsettingCommissionUpgrade);
        list_params.add("commission_agent_new");
        list_btn.add(warnsettingCommissionAgentNew);
        list_params.add("commission_order_pay");
        list_btn.add(warnsettingCommissionOrderPay);
        list_params.add("commission_order_finish");
        list_btn.add(warnsettingCommissionOrderFinish);
        list_params.add("commission_apply");
        list_btn.add(warnsettingCommissionApply);
        list_params.add("commission_check");
        list_btn.add(warnsettingCommissionCheck);
        list_params.add("commission_pay");
        list_btn.add(warnsettingCommissionPay);
        for (int i = 0; i < list_btn.size(); i++) {
            list_btn.get(i).setClickable(false);
            list_btn.get(i).setTag(i);

            list_btn.get(i).setOnToggleChanged(new ToggleButton.OnToggleChanged() {
                @Override
                public void onToggle(View view, boolean on) {
                    Integer integer= (Integer) view.getTag();
                    if (on){
                        list_current.remove(list_params.get(integer));
                    }else{
                        list_current.add(list_params.get(integer));
                    }
//                    Log.i("123456", "onToggle: "+list_current.toString());
                    StringBuilder stringBuilder=new StringBuilder();
                    for (int i1 = 0; i1 < list_current.size(); i1++) {
                        stringBuilder.append(list_current.get(i1)+",");
                    }
                    String s = stringBuilder.toString();
                    if(TextUtils.isEmpty(s)){
                        s="1";
                    }else {
                        s = s.substring(0, s.length() - 1);
                    }
                    updataNotice(id,s);
                }
            });
        }

    }

    private void updataNotice(int id,String s){
        Log.i("aaa", "updataNotice: "+s);
        OkGo.<BaseResponse<WarnSettingBean>>post(Constants.URL_UPDATE_NOTICE)
                .params("id",id)
                .params("noticeset",s)
                .execute(new JsonCallback<BaseResponse<WarnSettingBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<WarnSettingBean>> response) {
                        if(response.body().data.getInfo() instanceof Integer){
                            Integer integer= (Integer) response.body().data.getInfo();
                            Toast.makeText(_mActivity, ""+integer, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initSetting(int id) {
        OkGo.<BaseResponse<WarnSettingBean>>get(Constants.URL_GETNOTICE)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<WarnSettingBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<WarnSettingBean>> response) {
                        if(response.body().data.getInfo() instanceof String) {
                            String info = (String) response.body().data.getInfo();
                            String[] split = info.split(",");
                            list_current = new ArrayList<String>();
                            for (int i = 0; i < split.length; i++) {
                                for (int i1 = 0; i1 < list_params.size(); i1++) {
                                    if (split[i].equals(list_params.get(i1))) {
                                        list_btn.get(i1).setToggleOff();
                                        list_current.add(list_params.get(i1));
                                        continue;
                                    }
                                }
                            }
                            for (int i = 0; i < list_btn.size(); i++) {
                                list_btn.get(i).setClickable(true);
                            }
                        }else{

                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<WarnSettingBean>> response) {
                        super.onError(response);
                        Toast.makeText(_mActivity, "数据加载失败，请重新加载！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });

    }


}
