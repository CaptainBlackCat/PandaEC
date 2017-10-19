package com.wta.NewCloudApp.jiuwei138940.main.me.adapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.IntegralBean;
import java.util.ArrayList;

/**
 * Created by zzc on 2017/9/20.
 */

public class IntegralShopAdapter extends BaseQuickAdapter<IntegralBean.InfoBean.GoodslistBean,BaseViewHolder> {
    public IntegralShopAdapter() {
        super(R.layout.item_integral_shop,new ArrayList<IntegralBean.InfoBean.GoodslistBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralBean.InfoBean.GoodslistBean item) {
        helper.setText(R.id.item_integralshop_credit,item.getCredit()+"积分");
        helper.setText(R.id.item_integralshop_title,item.getTitle());
        Glide.with(mContext).load(item.getThumb()).into((ImageView) helper.getView(R.id.item_integralshop_thumb));
    }
}
