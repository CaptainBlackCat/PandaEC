package com.wta.NewCloudApp.jiuwei138940.main.goods.adapter;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;

import java.util.List;


public class dispatchAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<String> data;

    public dispatchAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        if (holder.getLayoutPosition() == data.size() - 1)
            holder.getView(R.id.dispatch_line).setVisibility(View.GONE);

        holder.setText(R.id.tv_dispatch_Dialog, item);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
