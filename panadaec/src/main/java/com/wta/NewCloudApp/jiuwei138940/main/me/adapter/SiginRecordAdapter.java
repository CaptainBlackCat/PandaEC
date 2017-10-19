package com.wta.NewCloudApp.jiuwei138940.main.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DateTimeUtils;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.SignRecordBean;

import java.util.ArrayList;

/**
 * Created by zzc on 2017/10/11.
 */

public class SiginRecordAdapter extends BaseQuickAdapter<SignRecordBean.InfoBean.ListBean,BaseViewHolder>{
    public SiginRecordAdapter() {
        super(R.layout.item_sign_record,new ArrayList<SignRecordBean.InfoBean.ListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, SignRecordBean.InfoBean.ListBean item) {
        helper.setText(R.id.item_sign_record_log,item.getLog())
                .setText(R.id.item_sign_record_sum,"+"+item.getSum())
                .setText(R.id.item_sign_record_time, DateTimeUtils.getTime(item.getTime()));
    }
}
