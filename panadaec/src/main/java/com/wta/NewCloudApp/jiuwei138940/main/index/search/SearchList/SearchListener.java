package com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.main.goods.GoodsDetail_normal;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchItemType;
import me.yokeyword.fragmentation.SupportHelper;


public class SearchListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;
    private GoodsDetail_normal goodsDetail = new GoodsDetail_normal();

    public SearchListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    public static SearchListener create(LatteDelegate delegate) {
        return new SearchListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleItemEmity data = (MultipleItemEmity) adapter.getData().get(position);
        GoodsDetail_normal goodsDetail = GoodsDetail_normal.newInstance((Integer) data.getField(SearchItemType.getID()));
        final LatteDelegate contentDelegate = SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), GoodsDetail_normal.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().start(goodsDetail);
        } else {
            DELEGATE.getSupportDelegate().start(goodsDetail);
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
