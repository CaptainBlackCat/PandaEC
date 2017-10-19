package com.wta.NewCloudApp.jiuwei138940.main.goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.flyco.tablayout.SlidingTabLayout;
import com.le.customview.SlideDetailsLayout;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.banner.BannerCreator;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.share.ShareEntity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.share.ShareHandler;
import com.wta.NewCloudApp.jiuwei138940.main.goods.GoodsDetail_normal;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.GoodsDetailEntity;
import com.wta.NewCloudApp.jiuwei138940.main.goods.dialog.detaildispatch;
import com.wta.NewCloudApp.jiuwei138940.main.goods.listener.OnSetSpecListener;

import java.util.ArrayList;
import java.util.List;


public class GoodsInfoFragment extends BaseFragment implements View.OnClickListener, SlideDetailsLayout.OnSlideDetailsListener, OnItemClickListener, OnSetSpecListener {
    private SlidingTabLayout psts_tabs;
    private SlideDetailsLayout sv_switch;
    private ScrollView sv_goods_info;
    private FloatingActionButton fab_up_slide;
    private ConvenientBanner<String> vp_item_goods_img;
    private LinearLayout ll_goods_detail, ll_goods_config, ll_dispatch;
    private TextView tv_goods_detail, tv_goods_config;
    private View v_tab_cursor;
    public FrameLayout fl_content;
    private ImageView goods_share;
    public LinearLayout ll_comment, ll_pull_up;
    public TextView tv_goods_title, tv_new_price, tv_old_price, tv_comment_count,
            tv_goods_sale, tv_places, tv_isSendfree, tv_dispatch;
    private GoodsDetail_normal parentFragment;


    /**
     * 当前商品详情数据页的索引分别是图文详情、规格参数
     */
    private int nowIndex = 0;
    private Fragment nowFragment = null;
    private float fromX;
    public GoodsConfigFragment goodsConfigFragment;
    public GoodsInfoWebFragment goodsInfoWebFragment;
    private List<TextView> tabTextList;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private LayoutInflater inflater;

    private TextView tv_choice;
    private String ChoiceId = null;
    /**
     * 分享内容实体
     */
    private ShareEntity shareEntity = new ShareEntity();


    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        vp_item_goods_img.startTurning(4000);
    }


    @Override
    public void onPause() {
        super.onPause();
        //停止自动翻页
        vp_item_goods_img.stopTurning();
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_goods_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        initListener();
        initData();
        scrollCursor();
        Log.d("goodINfo", "create");
    }


    private void initListener() {
        fab_up_slide.setOnClickListener(this);
        //ll_comment.setOnClickListener(this);
        ll_pull_up.setOnClickListener(this);
        ll_goods_detail.setOnClickListener(this);
        ll_goods_config.setOnClickListener(this);
        ll_dispatch.setOnClickListener(this);
        sv_switch.setOnSlideDetailsListener(this);
        tv_choice.setOnClickListener(this);
        goods_share.setOnClickListener(this);
    }

    private void initView(View rootView) {
        psts_tabs = (SlidingTabLayout) rootView.findViewById(R.id.psts_tabs);
        fab_up_slide = (FloatingActionButton) rootView.findViewById(R.id.fab_up_slide);
        sv_switch = (SlideDetailsLayout) rootView.findViewById(R.id.sv_switch);
        sv_goods_info = (ScrollView) rootView.findViewById(R.id.sv_goods_info);
        v_tab_cursor = rootView.findViewById(R.id.v_tab_cursor);
        vp_item_goods_img = (ConvenientBanner<String>) rootView.findViewById(R.id.vp_item_goods_img);

        fl_content = (FrameLayout) rootView.findViewById(R.id.fl_content);
        ll_dispatch = (LinearLayout) rootView.findViewById(R.id.ll_dispatch);
        ll_pull_up = (LinearLayout) rootView.findViewById(R.id.ll_pull_up);
        ll_goods_detail = (LinearLayout) rootView.findViewById(R.id.ll_goods_detail);
        ll_goods_config = (LinearLayout) rootView.findViewById(R.id.ll_goods_config);

        tv_goods_detail = (TextView) rootView.findViewById(R.id.tv_goods_detail);
        tv_goods_config = (TextView) rootView.findViewById(R.id.tv_goods_config);
        tv_goods_title = (TextView) rootView.findViewById(R.id.tv_goods_title);
        tv_new_price = (TextView) rootView.findViewById(R.id.tv_new_price);
        tv_goods_sale = (TextView) rootView.findViewById(R.id.detail_sales);
        tv_dispatch = (TextView) rootView.findViewById(R.id.detial_dispatch);
        tv_places = (TextView) rootView.findViewById(R.id.product_countary);
        tv_choice = (TextView) rootView.findViewById(R.id.detail_choice);
        tv_isSendfree = (TextView) rootView.findViewById(R.id.detail_issendfree);
        goods_share = (ImageView) rootView.findViewById(R.id.detail_share);
        setDetailData();
        setLoopView();
        fab_up_slide.hide();


    }


    /**
     * 初始化banner
     */
    private void setLoopView() {
        BannerCreator.setDefault(vp_item_goods_img, getData().getInfo().getThumb_url(), this);
    }

    @Override
    public GoodsDetailEntity.DataBean getData() {
        return super.getData();
    }

    @Override
    public void setData(GoodsDetailEntity data) {
        super.setData(data);
    }


    private void initData() {
        fragmentList = new ArrayList<>();
        tabTextList = new ArrayList<>();
        tabTextList.add(tv_goods_detail);
        tabTextList.add(tv_goods_config);
        tv_goods_title.setText(getData().getInfo().getTitle());
        tv_new_price.setText(getData().getInfo().getMarketprice());
        tv_goods_sale.setText("销量: " + getData().getInfo().getSales() + "件");
        tv_places.setText(getData().getInfo().getProvince() + " " + getData().getInfo().getCity());
        tv_isSendfree.setText(getData().getInfo().getIssendfree() == "1" ? "快递: 包邮" : "快递: ");
        String dispatchText = getData().getInfo().getDispatch().getNodispatchareas().toString();
        dispatchText = dispatchText.substring(1, dispatchText.length() - 1);
        dispatchText = "不配送区域: " + dispatchText;
        tv_dispatch.setText(dispatchText);

        /**
         * 初始化分享实体内容
         */
        shareEntity.setThumb(getData().getInfo().getThumb());
        shareEntity.setTitle(getData().getInfo().getTitle());
        shareEntity.setDescription(getData().getInfo().getDescription());
        shareEntity.setUrl(getData().getInfo().getShareUrl());
    }

    /**
     * 加载完商品详情执行
     */
    public void setDetailData() {
        goodsConfigFragment = new GoodsConfigFragment();
        goodsInfoWebFragment = new GoodsInfoWebFragment();
        fragmentList.add(goodsConfigFragment);
        fragmentList.add(goodsInfoWebFragment);
        goodsInfoWebFragment.setWebdata(getData().getInfo().getContent());
        goodsConfigFragment.setWebdata(getData().getInfo().getParam());


        nowFragment = goodsInfoWebFragment;
        fragmentManager = getChildFragmentManager();
        //默认显示商品详情
        fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
    }


    //筛选fragment
    private ChoiceDialogFragment choiceDialog = new ChoiceDialogFragment();

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_pull_up) {
            sv_switch.smoothOpen(true);
        } else if (v.getId() == R.id.fab_up_slide) {
            //点击滑动到顶部
            sv_goods_info.smoothScrollTo(0, 0);
            sv_switch.smoothClose(true);
        } else if (v.getId() == R.id.detail_choice) {
            choiceDialog.setChoiceData(getChoiceData());
            choiceDialog.setDetailData(getData());
            choiceDialog.setGoodsInfofrag(GoodsInfoFragment.this);
            choiceDialog.show(getFragmentManager(), "choice");
        } else if (v.getId() == R.id.ll_goods_detail) {
            //商品详情tab
            nowIndex = 0;
            scrollCursor();
            switchFragment(nowFragment, goodsInfoWebFragment);
            nowFragment = goodsInfoWebFragment;
        } else if (v.getId() == R.id.ll_goods_config) {
            //规格参数tab
            nowIndex = 1;
            scrollCursor();
            switchFragment(nowFragment, goodsConfigFragment);
            nowFragment = goodsConfigFragment;
        } else if (v.getId() == R.id.ll_dispatch) {
            detaildispatch detaildispatch = new detaildispatch(GoodsInfoFragment.this, getData().
                    getInfo().getDispatch().getNodispatchareas());
            detaildispatch.beginShareDialog();
        } else if (v.getId() == R.id.detail_share) {
            ShareHandler shareHandler = new ShareHandler(GoodsInfoFragment.this);
            shareHandler.setShareEntity(shareEntity);
            shareHandler.beginShareDialog();
        }
    }

    @Override
    public void onStatucChanged(SlideDetailsLayout.Status status) {
        if (status == SlideDetailsLayout.Status.OPEN) {
            //当前为图文详情页
            fab_up_slide.show();
            goodsDetail.vp_content.setNoScroll(true);
            goodsDetail.title.setVisibility(View.VISIBLE);
            goodsDetail.psts_tabs.setVisibility(View.GONE);
        } else {
            //当前为商品详情页
            fab_up_slide.hide();
            goodsDetail.getVp_content().setNoScroll(false);
            goodsDetail.title.setVisibility(View.GONE);
            goodsDetail.psts_tabs.setVisibility(View.VISIBLE);
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
    private void switchFragment(Fragment fromFragment, BaseFragment toFragment) {
        if (toFragment != nowFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {// 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void onSpecSet(String title, String productId) {
        tv_choice.setText(title);
    }
}
