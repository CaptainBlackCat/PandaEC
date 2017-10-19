package com.wta.NewCloudApp.jiuwei138940.main.index.search;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.DataConverter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.NormalUtils;
import java.util.ArrayList;

/**
 * 搜索列表数据转换器
 */

public class SearchListDataConverter extends DataConverter {

    public int spansize = 2;
    public int type = 20;
    public int pageSize = 0;

    @Override
    public ArrayList<MultipleItemEmity> convert() {
        JSONObject dataObject = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONObject("info");
        final int pageSize = dataObject.getIntValue("pagesize");
        setPageSize(pageSize);
        JSONArray dataArray = dataObject.getJSONArray("list");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            int id = data.getInteger("id");
            String title = NormalUtils.checkVal(data.getString("title"));
            String thumb = NormalUtils.checkVal(data.getString("thumb"));
            String marketprice = NormalUtils.checkVal(data.getString("marketprice"));
            String url = data.getString("url");

            MultipleItemEmity entity = MultipleItemEmity.builder()
                    .setField(SearchItemType.ID, id)
                    .setField(SearchItemType.TITLE, title)
                    .setField(SearchItemType.THUMB, thumb)
                    .setField(SearchItemType.MARKET_PRICE, marketprice)
                    .setField(SearchItemType.URL, url)
                    .setField(MultipleFields.SPAN_SIZE, spansize)
                    .setItemType(type)
                    .build();
            ENTITIES.add(entity);
        }
        Log.d("SearchList", String.valueOf(ENTITIES.size()));
        return ENTITIES;
    }



    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSpansize(int spansize) {
        this.spansize = spansize;
    }
}
