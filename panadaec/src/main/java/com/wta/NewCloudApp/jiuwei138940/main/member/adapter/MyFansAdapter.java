package com.wta.NewCloudApp.jiuwei138940.main.member.adapter;

import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DateTimeUtils;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.MyFansBean;

import java.util.ArrayList;


/**
 * Created by zzc on 2017/9/25.
 */

public class MyFansAdapter extends BaseQuickAdapter<MyFansBean.InfoBean.ListBean,BaseViewHolder> {
    public MyFansAdapter() {
        super(R.layout.item_member_fans,new ArrayList<MyFansBean.InfoBean.ListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFansBean.InfoBean.ListBean item) {
        Glide.with(mContext).load(item.getAvatar()).into((ImageView) helper.getView(R.id.item_myfans_thumb));
        helper.setText(R.id.item_myfans_nickname,item.getNickname())
        .setText(R.id.item_myfansagentnum,item.getAgentnum()+"个成员")
        .setText(R.id.item_myfans_pricenum,item.getPricenum()==null?"+0.00":"+"+item.getPricenum());
        if(item.getIsagent().equals("1")){
            helper.getView(R.id.item_myfans_xing).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_myfans_createtime, "成为社长时间: "+ DateTimeUtils.getTime(item.getAgenttime()));
        }else{
            helper.getView(R.id.item_myfans_xing).setVisibility(View.GONE);
            helper.setText(R.id.item_myfans_createtime, "注册时间: "+ DateTimeUtils.getTime(item.getCreatetime()));

        }
    }
}
