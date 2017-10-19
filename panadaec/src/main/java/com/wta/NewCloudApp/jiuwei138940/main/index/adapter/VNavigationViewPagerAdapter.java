package com.wta.NewCloudApp.jiuwei138940.main.index.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.index.NavigationChildDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.NavigationBean;
import com.wta.NewCloudApp.jiuwei138940.main.me.adapter.ShopDetailFragmentAdapter;
import com.youth.banner.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/10/5.
 */

public class VNavigationViewPagerAdapter extends DelegateAdapter.Adapter<VNavigationViewPagerAdapter.ViewHolder> {
   private Context mContext;
    private List<NavigationBean> mList;
    public VNavigationViewPagerAdapter(Context mContext,List<NavigationBean> list) {
        this.mContext = mContext;
        this.mList=list;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==6) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.view_pager, parent, false);

            return new ViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position)==6){
            List<String> titles=new ArrayList<>();
            List<LatteDelegate> list=new ArrayList<>();
            for (int i = 0; i < mList.size()/10; i++) {
                titles.add(""+position);
                NavigationChildDelegate e = NavigationChildDelegate.newInstance();
                e.setList(mList.subList(i*10,(i+1)*10));
                list.add(e);
            }
            holder.viewPager.setAdapter(new ShopDetailFragmentAdapter(((FragmentActivity)mContext).getSupportFragmentManager(),titles.toArray(new String[]{}),list));
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 6;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.view_pager_vp)
        BannerViewPager viewPager;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
