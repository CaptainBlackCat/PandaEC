package com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.web.event;


import com.wta.NewCloudApp.jiuwei138940.latte_core.util.log.LatteLogger;

/**
 * Created by 傅令杰
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
