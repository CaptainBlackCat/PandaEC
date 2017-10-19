package com.wta.NewCloudApp.jiuwei138940.main.cart;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.NormalUtils;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchItemType;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchListDataConverter;
import java.util.ArrayList;

/**
 * 数据转换器模板参考SearchLIstDataConverter
 */

public class RecProductDataConverter extends SearchListDataConverter {


    @Override
    public ArrayList<MultipleItemEmity> convert() {
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONObject("data").
                getJSONArray("info");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            int id = data.getInteger("goodsid");
            String title = NormalUtils.checkVal(data.getString("title"));
            String thumb = NormalUtils.checkVal(data.getString("thumb"));
            String marketprice = NormalUtils.checkVal(data.getString("marketprice"));
            String url = data.getString("url");

            MultipleItemEmity entity = MultipleItemEmity.builder()
                    .setField(SearchItemType.getID(), id)
                    .setField(SearchItemType.getTITLE(), title)
                    .setField(SearchItemType.getTHUMB(), thumb)
                    .setField(SearchItemType.getMarketPrice(), marketprice)
                    .setField(SearchItemType.getURL(), url)
                    .setField(MultipleFields.SPAN_SIZE, spansize)
                    .setItemType(type)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }


}
