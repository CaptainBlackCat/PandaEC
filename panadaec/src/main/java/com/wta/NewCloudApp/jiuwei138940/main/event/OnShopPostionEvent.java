package com.wta.NewCloudApp.jiuwei138940.main.event;

/**
 * Created by Lenovo on 2017/9/9.
 */

public class OnShopPostionEvent {
    private int postion;

    public OnShopPostionEvent(int postion) {
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
