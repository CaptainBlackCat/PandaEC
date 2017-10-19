package com.wta.NewCloudApp.jiuwei138940.main.me;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.loading.LatteLoader;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.IntegralShopAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.IntegralBean;
import com.wta.NewCloudApp.jiuwei138940.main.me.util.PreferencesUtility;

import java.util.List;

import butterknife.BindView;

import static com.wta.NewCloudApp.jiuwei138940.main.Constants.URL_INTEGRAL_SHOPINFO;


/**
 * Created by zzc on 2017/9/16.
 */

public class IntegralShopDelegate extends LatteDelegate {
    private int id=161;
    private int page=1;
    @BindView(R2.id.integralshop_myintegral)
    TextView myIntegral;
    @BindView(R2.id.integralshop_rv)
    RecyclerView myRv;
    private IntegralShopAdapter adapter;

    public static IntegralShopDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        IntegralShopDelegate fragment = new IntegralShopDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_integral_shop;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        initData();
    }


    protected void initView(View view) {
        myRv.setLayoutManager(new GridLayoutManager(_mActivity,2));
        adapter = new IntegralShopAdapter();
        myRv.setAdapter(adapter);
    }

    protected void initData() {
        LatteLoader.showLoading(_mActivity);
        showIntegralShop(page,id);
    }

    private void showIntegralShop(int page, int id){

        OkGo.<BaseResponse<IntegralBean>>get(URL_INTEGRAL_SHOPINFO)
                .params("page",page)
        .execute(new JsonCallback<BaseResponse<IntegralBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<IntegralBean>> response) {
                IntegralBean.InfoBean info = response.body().data.getInfo();
                if(info!=null){
                    myIntegral.setText("我的积分:"+info.getCredit1());
                    List<IntegralBean.InfoBean.GoodslistBean> goodslist = info.getGoodslist();
                    if(goodslist !=null){
                        adapter.addData(goodslist);
                    }
                }
                LatteLoader.stopLoading();
            }
        });
    }
}
