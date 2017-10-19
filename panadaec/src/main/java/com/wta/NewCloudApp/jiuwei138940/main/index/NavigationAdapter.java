package com.wta.NewCloudApp.jiuwei138940.main.index;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.NavigationBean;
import java.util.List;

/**
 * Created by zzc on 2017/10/5.
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationBean,BaseViewHolder> {
    public NavigationAdapter(List<NavigationBean> list) {
        super(R.layout.item_home_navigation, list);
    }



    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {
        helper.setText(R.id.item_home_navigation_title,item.getName())
                .setImageResource(R.id.item_home_navigation_thumb,item.getId());
    }
}
