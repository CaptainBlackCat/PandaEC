package com.wta.NewCloudApp.jiuwei138940.main.goods;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.BigClassification;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.GoodsDetailEntity;
import java.util.ArrayList;
import java.util.List;


public class goodsDataConverter {

    private String mJsonData = null;
    private List<BigClassification> BigScreen = new ArrayList<>();

    public goodsDataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("Data is NULL!");
        }
        return mJsonData;
    }


    public GoodsDetailEntity convert() {
        GoodsDetailEntity detailData = JSON.parseObject(getJsonData(), GoodsDetailEntity.class);
        for (int i = 0; i < detailData.getData().getInfo().getSpeccon().size(); i++) {
            List<GoodsDetailEntity.DataBean.InfoBean.SpecconBean> BigChoiceData =
                    detailData.getData().getInfo().getSpeccon();
            BigClassification ShopScreen = new BigClassification();
            ShopScreen.setTitle(BigChoiceData.get(i).getTitle());
            Log.d("sizes", String.valueOf(detailData.getData().getInfo().getSpeccon().get(0).getTitle()));
            List<GoodsDetailEntity.DataBean.InfoBean.SpecconBean.OptionsBean> sData =
                    detailData.getData().getInfo().getSpeccon().get(i).getOptions();
            for (int j = 0; j < detailData.getData().getInfo().getSpeccon().get(i).getOptions().size(); j++) {
                BigClassification.SmallClassification smallScreen = new BigClassification.SmallClassification();
                smallScreen.setName(sData.get(j).getTitle());
                smallScreen.setId(sData.get(j).getId());
                ShopScreen.list.add(smallScreen);
            }
            BigScreen.add(ShopScreen);
        }
        detailData.setChoiceData(BigScreen);
        return detailData;
    }


}
