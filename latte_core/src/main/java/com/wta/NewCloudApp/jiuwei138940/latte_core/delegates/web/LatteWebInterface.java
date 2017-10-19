package com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.web;
import android.webkit.JavascriptInterface;
import com.alibaba.fastjson.JSON;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.web.event.Event;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.web.event.EventManager;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.log.LatteLogger;


final class LatteWebInterface {
    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        LatteLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
