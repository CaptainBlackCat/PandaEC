package com.wta.NewCloudApp.jiuwei138940.main.member.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DateTimeUtils;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.MyClientBean;

import java.util.ArrayList;

/**
 * Created by zzc on 2017/9/23.
 */

public class MyClientAdapter extends BaseQuickAdapter<MyClientBean.InfoBean.ListBean,BaseViewHolder> {
    public MyClientAdapter() {
        super(R.layout.item_member_client,new ArrayList<MyClientBean.InfoBean.ListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, MyClientBean.InfoBean.ListBean item) {

        Glide.with(mContext).load(item.getAvatar()).into((ImageView) helper.getView(R.id.item_myclient_thumb));



        helper.setText(R.id.item_myclient_nickname,item.getNickname())
                .setText(R.id.item_myclient_pricenum,item.getPricenum()==null?"+0.00":"+"+item.getPricenum())
        .setText(R.id.item_myclient_agentnum,item.getAgentnum()+"个成员");
        if(item.getIsagent().equals("1")){
            helper.setText(R.id.item_myclient_createtime, "成为社长时间: "+ DateTimeUtils.getTime(item.getAgenttime()));
            helper.getView(R.id.item_myclient_xing).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.item_myclient_xing).setVisibility(View.GONE);
            helper.setText(R.id.item_myclient_createtime, "注册时间: "+ DateTimeUtils.getTime(item.getCreatetime()));

        }
    }
}
