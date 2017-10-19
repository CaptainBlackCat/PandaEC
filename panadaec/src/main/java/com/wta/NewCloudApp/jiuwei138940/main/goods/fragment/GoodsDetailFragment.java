package com.wta.NewCloudApp.jiuwei138940.main.goods.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.main.goods.GoodsDetail_normal;
import java.util.ArrayList;
import java.util.List;


/**
 * item页ViewPager里的详情Fragment
 */

public class GoodsDetailFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout ll_goods_detail, ll_goods_config;
    private TextView tv_goods_detail, tv_goods_config;
    private FrameLayout fl_content;
    private View v_tab_cursor;

    private int nowIndex = 0;
    private float fromX;
    private List<TextView> tabTextList;
    private GoodsDetail_normal activity;
    private GoodsConfigFragment goodsConfigFragment;
    private GoodsDetailWebFragment goodsDetailWebFragment;
    private Fragment nowFragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private View rootview;


    @Override
    public Object setLayout() {
        return R.layout.fragments_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        this.rootview = rootView;
        initView();
        initListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initListener() {
        ll_goods_detail.setOnClickListener(this);
        ll_goods_config.setOnClickListener(this);
    }

    private void initView() {
        ll_goods_detail = (LinearLayout) rootview.findViewById(R.id.ll_goods_detail);
        ll_goods_config = (LinearLayout) rootview.findViewById(R.id.ll_goods_config);
        tv_goods_detail = (TextView) rootview.findViewById(R.id.tv_goods_detail);
        tv_goods_config = (TextView) rootview.findViewById(R.id.tv_goods_config);
        fl_content = (FrameLayout) rootview.findViewById(R.id.fl_content);
        v_tab_cursor = rootview.findViewById(R.id.v_tab_cursor);

        tabTextList = new ArrayList<>();
        tabTextList.add(tv_goods_detail);
        tabTextList.add(tv_goods_config);
        setData();
    }

    /**
     * 商品信息Fragment页获取完数据执行
     */
    public void setData() {
        goodsConfigFragment = new GoodsConfigFragment();
        goodsConfigFragment.setWebdata(getData().getInfo().getParam());
        goodsDetailWebFragment = new GoodsDetailWebFragment();
        goodsDetailWebFragment.setWebdata(getData().getInfo().getContent());
        nowFragment = goodsDetailWebFragment;
        fragmentManager = getChildFragmentManager();
        scrollCursor();
        //默认显示商品详情tab
        fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ll_goods_detail) {
            //商品详情tab
            switchFragment(nowFragment, goodsDetailWebFragment);
            nowIndex = 0;
            nowFragment = goodsDetailWebFragment;
            scrollCursor();
        } else if (v.getId() == R.id.ll_goods_config) {
            //规格参数tab
            switchFragment(nowFragment, goodsConfigFragment);
            nowIndex = 1;
            nowFragment = goodsConfigFragment;
            scrollCursor();
        }

    }

    /**
     * 滑动游标
     */
    private void scrollCursor() {
        TranslateAnimation anim = new TranslateAnimation(fromX, nowIndex * v_tab_cursor.getWidth(), 0, 0);
        anim.setFillAfter(true);//设置动画结束时停在动画结束的位置
        anim.setDuration(50);
        //保存动画结束时游标的位置,作为下次滑动的起点
        fromX = nowIndex * v_tab_cursor.getWidth();
        v_tab_cursor.startAnimation(anim);

        //设置Tab切换颜色
        for (int i = 0; i < tabTextList.size(); i++) {
            tabTextList.get(i).setTextColor(i == nowIndex ? getResources().getColor(R.color.text_red) : getResources().getColor(R.color.text_black));
        }
    }

    /**
     * 切换Fragment
     * <p>(hide、show、add)
     *
     * @param fromFragment
     * @param toFragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (nowFragment != toFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {    // 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
