package com.wta.NewCloudApp.jiuwei138940.main.cart;
import com.alibaba.fastjson.JSON;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.CartEntity;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.GoodsInfo;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.StoreInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车的数据转换器
 */

public class ShopCartDataConverter {
    private String mJsonData = null;
    private List<StoreInfo> groups = new ArrayList<>();
    private Map<String, List<GoodsInfo>> children = new HashMap<>();


    private List<GoodsInfo> mListGoodsInfo = new ArrayList<>();

    public ShopCartDataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("Data is NULL!");
        }
        return mJsonData;
    }

    public CartEntity convert() {
        CartEntity cartData = JSON.parseObject(getJsonData(), CartEntity.class);
        for (int i = 0; i < getCartJson(cartData).size(); i++) {
            groups.add(new StoreInfo(getCartJson(cartData).get(i).getMerch().getMerchname()
                    , getCartJson(cartData).get(i).getMerch().getUid(),
                    getCartJson(cartData).get(i).getMerch().getLogo()));
            List<GoodsInfo> mListGoodsInfo = new ArrayList<>();
            for (int j = 0; j < getCartJson(cartData).get(i).getGoodsList().size(); j++) {
                mListGoodsInfo.add(new GoodsInfo(getCartJson(cartData).get(i).getGoodsList().get(j).getGoodsid(),
                        getCartJson(cartData).get(i).getGoodsList().get(j).getTitle(),
                        Integer.parseInt(getCartJson(cartData).get(i).getGoodsList().get(j).getTotal()),
                        Double.parseDouble(getCartJson(cartData).get(i).getGoodsList().get(j).getMarketprice()),
                        Double.parseDouble(getCartJson(cartData).get(i).getGoodsList().get(j).getDispatchprice()),
                        getCartJson(cartData).get(i).getGoodsList().get(j).getThumb()));
            }
            children.put(getCartJson(cartData).get(i).getMerch().getUid(), mListGoodsInfo);
        }
        setGroups(groups);
        setChildren(children);
        return cartData;
    }

    public List<CartEntity.DataBean.InfoBean> getCartJson(CartEntity cartEntity) {
        return cartEntity.getData().getInfo();
    }

    public void setGroups(List<StoreInfo> groups) {
        this.groups = groups;
    }

    public void setChildren(Map<String, List<GoodsInfo>> children) {
        this.children = children;
    }

    public List<StoreInfo> getGroups() {
        return groups;
    }

    public Map<String, List<GoodsInfo>> getChildren() {
        return children;
    }
}
