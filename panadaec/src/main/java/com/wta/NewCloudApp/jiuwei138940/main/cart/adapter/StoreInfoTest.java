package com.wta.NewCloudApp.jiuwei138940.main.cart.adapter;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.GoodsInfo;

public class StoreInfoTest extends SectionEntity<GoodsInfo> {
    protected String merchName;
    protected String id;
    protected boolean isChoosed;
    protected String logo;
    private boolean isEditor;
    private GoodsInfo goodsInfo;

    public StoreInfoTest(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean isEditor() {
        return isEditor;
    }

    public void setEditor(boolean editor) {
        isEditor = editor;
    }



    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
}
