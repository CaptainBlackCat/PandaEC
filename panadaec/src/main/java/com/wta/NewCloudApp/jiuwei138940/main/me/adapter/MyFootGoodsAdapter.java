package com.wta.NewCloudApp.jiuwei138940.main.me.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DateTimeUtils;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.FootBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzc on 2017/10/7.
 */

public class MyFootGoodsAdapter extends BaseQuickAdapter<FootBean.InfoBean.ListBean, BaseViewHolder> {
    private boolean isEdit=false;
    private HashMap<Integer,Boolean> state=new HashMap<>();



    public MyFootGoodsAdapter() {
        super(R.layout.item_myfoot, new ArrayList<FootBean.InfoBean.ListBean>());
    }


    @Override
    protected void convert(final BaseViewHolder helper, final FootBean.InfoBean.ListBean item) {
        final CheckBox checkBox = helper.getView(R.id.item_myfoot_carimg);

        if(isEdit){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.GONE);
        }


        if(state.containsKey(helper.getAdapterPosition())) {
            checkBox.setChecked(state.get(helper.getAdapterPosition()));
        }else{
            checkBox.setChecked(false);
        }


        helper.itemView.setTag(helper.getAdapterPosition());

        if(isEdit()) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer flag = (Integer) v.getTag();
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        state.put(flag, false);
                    } else {
                        checkBox.setChecked(true);
                        state.put(flag, true);
                    }
                }
            });
        }else{
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "一剑凌情缘", Toast.LENGTH_SHORT).show();
                }
            });
        }

        helper.setText(R.id.item_myfoot_title, item.getTitle())
                .setText(R.id.item_myfoot_realprice, "￥" + item.getMarketprice())
                .setText(R.id.item_myfoot_beforeprice, "￥" + item.getProductprice())
                .setText(R.id.item_myfoot_time, DateTimeUtils.getTime(item.getCreatetime()))
                .setText(R.id.item_myfoot_merchname, item.getMerchname());

        Glide.with(mContext).load(item.getThumb()).into((ImageView) helper.getView(R.id.item_myfoot_thumb));

    }



    public void setItemEdit(boolean isEdit) {
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

    public boolean isEdit() {
        return isEdit;
    }

    public String getDeleteStr(){
        StringBuilder stringBuilder=new StringBuilder();
        for (Map.Entry<Integer,Boolean> s:state.entrySet()
                ) {
            if(s.getValue()==true){
                String goodsid = mData.get(s.getKey()).getGoodsid();
                stringBuilder.append(goodsid+",");
            }
        }

        String s=stringBuilder.toString();
        if(!TextUtils.isEmpty(s)) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    public void selectAll(boolean isAll) {

        for (int i = 0; i < mData.size(); i++) {
            state.put(i,isAll);
        }
        notifyDataSetChanged();
    }
}
