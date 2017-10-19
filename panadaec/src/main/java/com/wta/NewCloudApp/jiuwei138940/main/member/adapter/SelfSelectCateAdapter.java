package com.wta.NewCloudApp.jiuwei138940.main.member.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.CateBean;

/**
 * Created by zzc on 2017/10/12.
 */

public class SelfSelectCateAdapter extends BaseQuickAdapter<CateBean.InfoBean,BaseViewHolder> {

    private int current=0;

    public SelfSelectCateAdapter() {
        super(R.layout.item_selfselect_cate);
    }

    @Override
    protected void convert(BaseViewHolder helper, CateBean.InfoBean item) {
        TextView textView=helper.getView(R.id.item_selfselect_title);

        if(current==helper.getAdapterPosition()){
            textView.setTextColor(ContextCompat.getColor(mContext,R.color.red_f7443e));
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
        }else{
            textView.setTextColor(ContextCompat.getColor(mContext,R.color.grey_96));
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.grey_f5));
        }



        helper.setText(R.id.item_selfselect_title,item.getName());
    }

    public void setCurrent(int current){
        this.current=current;
        notifyDataSetChanged();
    }
    public int getCurrent(){
        return current;
    }
}
