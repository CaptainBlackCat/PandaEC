package com.wta.NewCloudApp.jiuwei138940.main;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BaseBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomItemDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomTabBean;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.ItemBuilder;
import com.wta.NewCloudApp.jiuwei138940.main.cart.ShopCartDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.index.HomeDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.MyDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.member.MemberDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate{
    private final int HOME[] ={R.drawable.home_normal,R.drawable.home_check};
    private final int SORT[] ={R.drawable.category_normal,R.drawable.category_check};
    private final int SHARE[] ={R.drawable.share_normal,R.drawable.share_check};
    private final int SHOP_CART[] ={R.drawable.shop_normal,R.drawable.shop_check};
    private final int ME[] ={R.drawable.my_normal,R.drawable.my_check};
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean(HOME,"首页"),new HomeDelegate(this));
        items.put(new BottomTabBean(SORT,"分类"),new SortDelegate(this));
        items.put(new BottomTabBean(SHARE,"云公社"), new MemberDelegate(this));
        items.put(new BottomTabBean(SHOP_CART,"购物车"),new ShopCartDelegate(this));
        items.put(new BottomTabBean(ME,"我的"),new MyDelegate(this));
        return builder.addItem(items).build();
    }

    @Override
     public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return 0;
    }

}
