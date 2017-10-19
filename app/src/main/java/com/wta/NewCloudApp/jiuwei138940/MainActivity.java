package com.wta.NewCloudApp.jiuwei138940;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.wta.NewCloudApp.jiuwei138940.latte_core.activies.ProxyActivity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.launcher.ScrollLauncherTag;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.storage.LattePreference;
import com.wta.NewCloudApp.jiuwei138940.launcher.launcherScrollDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;

public class MainActivity extends ProxyActivity{
    @Override
    public LatteDelegate setRootDelegate() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            return new launcherScrollDelegate();
        }
        return new EcBottomDelegate();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }


}
