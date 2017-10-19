package com.wta.NewCloudApp.jiuwei138940.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.launcher.ILauncherListener;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.launcher.LauncherHolderCreator;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.launcher.ScrollLauncherTag;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.storage.LattePreference;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;

import java.util.ArrayList;
public class launcherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenitentBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;
    final int RIGHT = 0;
    final int LEFT = 1;

    private void initBanner(){
        INTEGERS.add(R.drawable.firststartimg01);
        INTEGERS.add(R.drawable.firststartimg02);
        INTEGERS.add(R.drawable.firststartimg03);
        INTEGERS.add(R.drawable.firststartimg04);
        mConvenitentBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if (position == INTEGERS.size() - 1){
                            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
                            getSupportDelegate().start(new EcBottomDelegate(),SINGLETASK);
                        }
                    }
                    @Override
                    public void onPageSelected(int position) {
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                })
                .setCanLoop(false);
    }
    @Override
    public Object setLayout() {
        mConvenitentBanner = new ConvenientBanner<>(getContext());
        return mConvenitentBanner;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }



    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            getSupportDelegate().start(new EcBottomDelegate(),SINGLETASK);
        }

    }

}
