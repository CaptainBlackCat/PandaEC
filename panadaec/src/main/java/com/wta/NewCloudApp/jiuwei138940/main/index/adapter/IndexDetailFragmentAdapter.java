package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wta.NewCloudApp.jiuwei138940.main.index.HomeChildDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexTabBean;

import java.util.List;

/**
 * Created by zzc on 2017/10/16.
 */

public class IndexDetailFragmentAdapter extends FragmentPagerAdapter {

    List<IndexTabBean.InfoBean> info;

    public IndexDetailFragmentAdapter(FragmentManager childFragmentManager, List<IndexTabBean.InfoBean> info) {
        super(childFragmentManager);
        this.info=info;
    }

    @Override
    public Fragment getItem(int position) {

        return HomeChildDelegate.newInstance(info.get(position).getId());
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return info.get(position).getName();
    }
}
