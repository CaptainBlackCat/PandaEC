package com.wta.NewCloudApp.jiuwei138940.main.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DensityUtils;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.WarnSettingBean;
import com.wta.NewCloudApp.jiuwei138940.main.member.adapter.SelfSelectCateAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.member.adapter.SelfSelectContentAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.CateBean;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.SelfShopBean;
import com.wta.NewCloudApp.jiuwei138940.main.widget.CustomLoadMoreView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zzc on 2017/10/12.
 */

public class SelectChildDelegate extends LatteDelegate{
    @BindView(R2.id.select_child_list)
    RecyclerView mRv_list;
    @BindView(R2.id.select_child_content)
    RecyclerView mRv_content;
    private int id=10;
    private int contentPage=1;
    private SelfSelectCateAdapter cateAdapter;
    private SelfSelectContentAdapter contentAdapter;
    public static SelectChildDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        SelectChildDelegate fragment = new SelectChildDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_selectchild;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
        initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        contentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                contentPage++;
                CateBean.InfoBean infoBean = cateAdapter.getData().get(cateAdapter.getCurrent());
                loadContentData(infoBean.getPcate(),infoBean.getCcate(),contentPage,cateAdapter.getCurrent());
            }
        },mRv_content);

        contentAdapter.setOnChangStatusListener(new SelfSelectContentAdapter.ShopStatusListener() {
            @Override
            public void setOnChangStatus(SelfShopBean.InfoBean goodsid, int s) {
                if(s==0){
                    deleteShop(goodsid);
                }else{
                    insertShop(goodsid);
                }
            }
        });

    }

    private void initData() {
        loadCateData();
    }

    private void initView() {
        mRv_list.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRv_content.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRv_list.addItemDecoration(new HorizontalDividerItemDecoration.Builder(_mActivity)
                .color(ContextCompat.getColor(_mActivity,R.color.list_line))
                .size(DensityUtils.dip2px(_mActivity,0.5f))
        .build());
        mRv_content.addItemDecoration(new HorizontalDividerItemDecoration.Builder(_mActivity)
                .color(ContextCompat.getColor(_mActivity,R.color.list_line))
                .size(DensityUtils.dip2px(_mActivity,0.5f))
                .build());
        cateAdapter=new SelfSelectCateAdapter();
        contentAdapter=new SelfSelectContentAdapter();

        contentAdapter.setLoadMoreView(new CustomLoadMoreView());

        mRv_list.setAdapter(cateAdapter);
        mRv_content.setAdapter(contentAdapter);

        cateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CateBean.InfoBean infoBean= (CateBean.InfoBean) adapter.getData().get(position);
                contentPage=1;
                loadContentData(infoBean.getPcate(),infoBean.getCcate(),contentPage,position);
            }
        });
    }

    public void deleteShop(final SelfShopBean.InfoBean goodsid){
        OkGo.<BaseResponse<WarnSettingBean>>get(Constants.URL_DELETEDOPTIONALGOODS)
                .params("id",id)
                .params("goodsid",goodsid.getId()).execute(new JsonCallback<BaseResponse<WarnSettingBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<WarnSettingBean>> response) {
                goodsid.setStatus(0);
            }
        });
    }

    public void insertShop(final SelfShopBean.InfoBean goodsid){

        OkGo.<BaseResponse<WarnSettingBean>>get(Constants.URL_INSERTOPTIONALGOODS)
        .params("id",id)
        .params("goodsid",goodsid.getId()).execute(new JsonCallback<BaseResponse<WarnSettingBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<WarnSettingBean>> response) {
                goodsid.setStatus(1);
            }
        });
    }


    public void loadCateData(){
        OkGo.<BaseResponse<CateBean>>get(Constants.URL_ALLOPTIONALGOODS)
                .params("id",id)
                .params("pcate",0)
                .execute(new JsonCallback<BaseResponse<CateBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<CateBean>> response) {
                        List<CateBean.InfoBean> info = response.body().data.getInfo();
                        if(info !=null){
                            cateAdapter.addData(info);
                            CateBean.InfoBean infoBean = info.get(0);
                            loadContentData(infoBean.getPcate(),infoBean.getCcate(),contentPage,0);
                        }
                    }
                });
    }

    public void loadContentData(String pcate, String ccate, final int page, final int postion){
        OkGo.<BaseResponse<SelfShopBean>>get(Constants.URL_ALLOPTIONALGOODS)
                .params("id",id)
        .params("pcate",pcate)
        .params("ccate",ccate)
        .params("page",page)
        .execute(new JsonCallback<BaseResponse<SelfShopBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<SelfShopBean>> response) {
                if(response==null)
                    return;
                List<SelfShopBean.InfoBean> info = response.body().data.getInfo();
                if(info!=null){
                    if(page==1){
                        contentAdapter.setNewData(info);
                    }else {
                        contentAdapter.addData(info);
                    }
                    cateAdapter.setCurrent(postion);

                    if(info.size()<10){
                        contentAdapter.loadMoreEnd();
                    }else{
                        contentAdapter.loadMoreComplete();
                    }
                }

            }
        });

    }
}
