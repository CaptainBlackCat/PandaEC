package com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList.list;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import java.util.ArrayList;


public class Search_LeftAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<MultipleItemEmity> list;
    private int mResourceId;

    public Search_LeftAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<MultipleItemEmity> list) {
        super(context, resource);
        this.context = context;
        this.mResourceId = resource;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(mResourceId, null);
            holder = new ViewHolder();
            holder.nameTV = (TextView) convertView.findViewById(R.id.search_sort_left);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //选中和没选中时，设置不同颜色
        if (position == selectedPosition) {
            holder.nameTV.setTextColor(Color.rgb(252, 252, 252));
            convertView.setBackgroundResource(R.color.tab_color);
        } else {
            holder.nameTV.setTextColor(Color.rgb(27, 27, 27));
            convertView.setBackgroundResource(R.color.goods_detailbk);
        }
        holder.nameTV.setText((CharSequence) list.get(position).getField(MultipleFields.TEXT));
        holder.nameTV.setHeight(70);
        return convertView;
    }

    private class ViewHolder {
        TextView nameTV;
    }

    private int selectedPosition = -1;

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

}
