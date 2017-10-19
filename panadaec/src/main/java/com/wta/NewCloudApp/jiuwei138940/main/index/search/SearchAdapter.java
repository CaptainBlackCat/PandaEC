package com.wta.NewCloudApp.jiuwei138940.main.index.search;
import android.support.v7.widget.AppCompatTextView;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleViewHolder;
import java.util.List;


public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEmity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEmity entity) {
        switch (entity.getItemType()){
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }

}
