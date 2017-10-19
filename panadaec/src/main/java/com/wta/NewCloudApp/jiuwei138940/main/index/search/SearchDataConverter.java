package com.wta.NewCloudApp.jiuwei138940.main.index.search;
import com.alibaba.fastjson.JSONArray;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.DataConverter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.storage.LattePreference;
import java.util.ArrayList;


public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";


    @Override
    public ArrayList<MultipleItemEmity> convert() {
        final String jsonStr =
                LattePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++){
                final String historyItemText  = array.getString(i);
                final MultipleItemEmity entity =  MultipleItemEmity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT,historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
