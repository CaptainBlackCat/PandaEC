package com.wta.NewCloudApp.jiuwei138940.main.me.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import java.util.List;

/**
 * Created by Lenovo on 2017/9/5.
 */

public class ShopDetailFragmentAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<LatteDelegate> list;
    public ShopDetailFragmentAdapter(FragmentManager fm, String[] titles, List<LatteDelegate> list) {
        super(fm);
        this.titles = titles;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
      return list.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
