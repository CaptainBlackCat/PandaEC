package com.wta.NewCloudApp.jiuwei138940.main.sort.list;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.ItemType;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleViewHolder;
import com.wta.NewCloudApp.jiuwei138940.main.sort.SortDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;


public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    public SortRecyclerAdapter(List<MultipleItemEmity> data, SortDelegate DELEGATE) {
        super(data);
        this.DELEGATE = DELEGATE;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }



    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEmity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final String contentUrl = getData().get(currentPosition).getField(MultipleFields.URL);
                            showContent(contentUrl);
                        }
                    }
                });

                if (!isClicked) {
                    itemView.setBackgroundColor(Color.WHITE);
                } else {
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                }
                holder.setText(R.id.tv_vertical_item_name, text);
        }
    }

    private void showContent(String contentUrl) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentUrl);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate) {
        final LatteDelegate contentDelegate = SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
