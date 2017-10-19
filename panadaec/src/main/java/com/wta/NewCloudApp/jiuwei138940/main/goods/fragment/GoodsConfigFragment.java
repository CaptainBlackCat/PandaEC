package com.wta.NewCloudApp.jiuwei138940.main.goods.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.wta.NewCloudApp.jiuwei138940.R;


public class GoodsConfigFragment extends BaseFragment {
    public WebView wv_config;
    private WebSettings webSettings;

    private String webdata;

    public String getWebdata() {
        return webdata;
    }

    public void setWebdata(String webdata) {
        this.webdata = webdata;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_item_config;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initWebView(rootView);
    }

    public void initWebView(View rootView) {
        wv_config = (WebView) rootView.findViewById(R.id.wv_config);
        wv_config.setFocusable(false);
        if (getWebdata() != null)
            wv_config.loadDataWithBaseURL(null, getWebdata(), "text/html", "utf-8", null);


        webSettings = wv_config.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setBlockNetworkImage(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_config.setWebViewClient(new GoodsConfigWebViewClient());
    }

    private class GoodsConfigWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webSettings.setBlockNetworkImage(false);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
    }


}
