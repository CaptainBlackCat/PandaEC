package com.wta.NewCloudApp.jiuwei138940.main.event;

/**
 * Created by zzc on 2017/9/13.
 */

public class OnGoodsParamEvent {
    private String content;

    public OnGoodsParamEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
