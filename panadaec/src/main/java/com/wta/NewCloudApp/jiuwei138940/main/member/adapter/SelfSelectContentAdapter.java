package com.wta.NewCloudApp.jiuwei138940.main.member.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.togglebutton.ToggleButton;
import com.wta.NewCloudApp.jiuwei138940.main.member.bean.SelfShopBean;

import java.util.ArrayList;

/**
 * Created by zzc on 2017/10/12.
 */

public class SelfSelectContentAdapter extends BaseQuickAdapter<SelfShopBean.InfoBean,BaseViewHolder>{
   private ShopStatusListener mShopStatusListener;
    public SelfSelectContentAdapter() {
        super(R.layout.item_selfselect_content,new ArrayList<SelfShopBean.InfoBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelfShopBean.InfoBean item) {
        Glide.with(mContext).asBitmap().load(item.getThumb()).into((ImageView) helper.getView(R.id.item_selfselect_content_thumb));
        helper.setText(R.id.item_selfselect_content_price,"￥"+item.getMarketprice())
                .setText(R.id.item_selfselect_content_title,item.getTitle());
        ToggleButton toggleButton=helper.getView(R.id.item_selfselect_content_toggle);
        if(item.getStatus()==0){
            toggleButton.setToggleOff(true);
        }else{
            toggleButton.setToggleOn(true);
        }
        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(View view, boolean on) {
                if(on){
                    if (mShopStatusListener!=null){
                        mShopStatusListener.setOnChangStatus(item,1);
                    }
                }else{
                    if(mShopStatusListener!=null){
                        mShopStatusListener.setOnChangStatus(item,0);
                    }
                }
            }
        });
    }

    public interface ShopStatusListener{
        /**
         * @param goodsid 商品id
         * @param s 是添加还是删除 1添加  0删除
         */
        void setOnChangStatus(SelfShopBean.InfoBean goodsid,int s);
    }

    public void setOnChangStatusListener(ShopStatusListener mShopStatusListener){
        this.mShopStatusListener=mShopStatusListener;
    }
}
