package com.wta.NewCloudApp.jiuwei138940.main.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

/**
 * 项目名： PandaEC
 * 包名：   com.wta.NewCloudApp.jiuwei138940.main.goods
 * 文件名:  GoodsDetail_cuxiao
 * 作者：qxf on 2017/10/18 13:47
 * 邮箱：lorderike@gmail.com
 * 描述：TODO
 */

public class GoodsDetail_xianshicuxiao extends LatteDelegate{
    private static final String ARG_CONTENT_ID = "CONTENT_ID";

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
    public static GoodsDetail_xianshicuxiao newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final GoodsDetail_xianshicuxiao delegate = new GoodsDetail_xianshicuxiao();
        delegate.setArguments(args);
        return delegate;
    }
}
