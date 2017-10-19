package com.wta.NewCloudApp.jiuwei138940.main.sort.list;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.DataConverter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.ItemType;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import java.util.ArrayList;


public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEmity> convert() {
        final ArrayList<MultipleItemEmity> dataList = new ArrayList<>();


        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("info");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            String url = data.getString("url");
            url = url.replace("api.anmur.com", "192.168.3.59");
            final MultipleItemEmity entity = MultipleItemEmity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)
                    .setField(MultipleFields.URL, url)
                    .build();

            dataList.add(entity);
        }
        dataList.get(0).setField(MultipleFields.TAG, true);

        return dataList;
    }


}
