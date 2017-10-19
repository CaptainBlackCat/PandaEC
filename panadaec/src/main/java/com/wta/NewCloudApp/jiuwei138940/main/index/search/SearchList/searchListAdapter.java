package com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleViewHolder;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchItemType;
import java.util.List;


public class searchListAdapter extends MultipleRecyclerAdapter {

    public final int TYPE_THUMB = 10;
    public final int TYPE_NORMAL = 20;


    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public searchListAdapter(List<MultipleItemEmity> data) {
        super(data);
        addItemType(TYPE_NORMAL, R.layout.item_search_list);
        addItemType(TYPE_THUMB, R.layout.item_search_list_thumb);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEmity entity) {
        switch (entity.getItemType()) {
            case TYPE_NORMAL:
                final AppCompatTextView listTitle = holder.getView(R.id.listTitle_search);
                final AppCompatImageView listImage = holder.getView(R.id.image_searchList);
                final AppCompatTextView listPrice = holder.getView(R.id.listPrice_search);
                final String price = entity.getField(SearchItemType.getMarketPrice());
                final int id = entity.getField(SearchItemType.getID());
                final String thumb = entity.getField(SearchItemType.getTHUMB());
                final String url = entity.getField(SearchItemType.getURL());
                final String title = entity.getField(SearchItemType.getTITLE());
                listTitle.setText(title);
                listPrice.setText("¥" + price);
                Glide.with(mContext)
                        .load(thumb)
                        .apply(RECYCLER_OPTIONS)
                        .into(listImage);
                Log.d("cureentType", String.valueOf(entity.getItemType()));
                break;
            case TYPE_THUMB:
                Log.d("ri", "dfawd");
                final AppCompatTextView Thumb_Title = holder.getView(R.id.thumb_title);
                final AppCompatImageView Thumb_Image = holder.getView(R.id.thumb_img_search);
                final AppCompatTextView Thumb_Price = holder.getView(R.id.thumb_price_search);
                final String Thumb_price = entity.getField(SearchItemType.getMarketPrice());
                final int Thumb_id = entity.getField(SearchItemType.getID());
                final String Thumb_thumb = entity.getField(SearchItemType.getTHUMB());
                final String Thumb_url = entity.getField(SearchItemType.getURL());
                final String Thumb_title = entity.getField(SearchItemType.getTITLE());
                Thumb_Title.setText(Thumb_title);
                Thumb_Price.setText("¥" + Thumb_price);
                Glide.with(mContext)
                        .load(Thumb_thumb)
                        .apply(RECYCLER_OPTIONS)
                        .into(Thumb_Image);
                break;
            default:
                break;
        }
    }




}
