package com.wta.NewCloudApp.jiuwei138940.main.event;

/**
 * Created by zzc on 2017/9/30.
 */

public class OncCollectGoodsEvent {
    private int id;
    private int goodsid;

    public OncCollectGoodsEvent(int id, int goodsid) {
        this.id = id;
        this.goodsid = goodsid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }
}


