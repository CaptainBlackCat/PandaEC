package com.wta.NewCloudApp.jiuwei138940.main.sort.content;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.ItemType;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleViewHolder;
import java.util.List;


public class SectionAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    private static final RequestOptions DEFAULT = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    protected SectionAdapter(List<MultipleItemEmity> data) {
        super(data);
        addItemType(ItemType.SECTION_LIST, R.layout.item_section_content);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEmity entity) {

        final int SECTION_LIST_TOP = entity.getField(MultipleFields.ID);

        if (SECTION_LIST_TOP == -1) {
            final AppCompatImageView img = holder.getView(R.id.iv);
            final AppCompatTextView text = holder.getView(R.id.tv);
            final String name = entity.getField(MultipleFields.TEXT);
            text.setText(name);
            int thumb = entity.getField(MultipleFields.IMAGE_URL);
            img.setScaleType(ImageView.ScaleType.CENTER);
            Glide.with(mContext)
                    .load(thumb)
                    .apply(DEFAULT)
                    .into(img);
        } else {
            holder.setText(R.id.tv, entity.getField(MultipleFields.TEXT).toString());
            final String thumb = entity.getField(MultipleFields.IMAGE_URL);
            final AppCompatImageView img = holder.getView(R.id.iv);
            Glide.with(mContext)
                    .load(thumb)
                    .apply(OPTIONS)
                    .into(img);
        }
    }
}
