package com.wta.NewCloudApp.jiuwei138940.main.member;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.member.adapter.CommuneGoodsAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.CommuneGoodsBean;

import butterknife.BindView;

/**
 * Created by zzc on 2017/9/20.
 */

public class MyCommuneDelegate extends LatteDelegate {
    @BindView(R2.id.member_mycommune_rv)
    RecyclerView mRecyclerView;
    private int id=5;
    private int page=1;
    private CommuneGoodsAdapter adapter;
    private TextView goodsCount;
    private ImageView logoThumb;

    public static MyCommuneDelegate newInstance() {
        
        Bundle args = new Bundle();
        
        MyCommuneDelegate fragment = new MyCommuneDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_mycommune;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(_mActivity,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CommuneGoodsAdapter();

        mRecyclerView.setAdapter(adapter);
        View inflate = LayoutInflater.from(_mActivity).inflate(R.layout.head_commune, mRecyclerView, false);
        goodsCount = (TextView) inflate.findViewById(R.id.head_commune_count);
        logoThumb = (ImageView) inflate.findViewById(R.id.head_commune_thumb);
        adapter.addHeaderView(inflate);
        getMystore(id,page);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getMystore(id,page);
            }
        },mRecyclerView);
    }

    public void getMystore(int id,int page){
        OkGo.<String>get(Constants.URL_GETMYSTORE)
        .params("id",id)
        .params("page",page)
        .execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
               if(response.body().length()>70){
                   Gson gson=new Gson();
                   CommuneGoodsBean communeGoodsBean = gson.fromJson(response.body(), CommuneGoodsBean.class);
                   CommuneGoodsBean.DataBean.InfoBean info = communeGoodsBean.getData().getInfo();
                   goodsCount.setText(info.getCount());
                   if(!TextUtils.isEmpty(info.getLogo())){
                       Glide.with(_mActivity).load(info.getLogo()).into(logoThumb);
                   }

                   adapter.addData(info.getList());

                   if (info.getList().size() < info.getPagesize()) {
                       adapter.loadMoreEnd();
                   } else {
                       adapter.loadMoreComplete();
                   }
               }
            }
        });
    }


}
