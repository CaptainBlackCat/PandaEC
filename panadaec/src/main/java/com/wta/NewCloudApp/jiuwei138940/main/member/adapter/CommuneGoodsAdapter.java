package com.wta.NewCloudApp.jiuwei138940.main.member.adapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.CommuneGoodsBean;
import java.util.ArrayList;

/**
 * Created by zzc on 2017/9/29.
 */

public class CommuneGoodsAdapter extends BaseQuickAdapter<CommuneGoodsBean.DataBean.InfoBean.ListBean,BaseViewHolder> {
    public CommuneGoodsAdapter() {
        super(R.layout.item_search_zzc,new ArrayList<CommuneGoodsBean.DataBean.InfoBean.ListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, CommuneGoodsBean.DataBean.InfoBean.ListBean item) {
        Glide.with(mContext).load(item.getThumb()).into((ImageView) helper.getView(R.id.item_search_thumb));
        helper.setText(R.id.item_search_title,item.getTitle())
                .setText(R.id.item_search_price,"￥"+item.getMarketprice());
    }
}
