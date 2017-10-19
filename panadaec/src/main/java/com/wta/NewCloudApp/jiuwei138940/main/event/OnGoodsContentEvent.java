package com.wta.NewCloudApp.jiuwei138940.main.event;

/**
 * Created by Lenovo on 2017/9/5.
 */

public class OnGoodsContentEvent {
    private String content;

    public OnGoodsContentEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
