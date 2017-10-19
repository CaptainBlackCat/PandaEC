package com.wta.NewCloudApp.jiuwei138940.main.goods;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import com.flyco.tablayout.SlidingTabLayout;
import com.le.customview.NoScrollViewPager;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.main.goods.ViewPager.ItemTitlePagerAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.GoodsDetailEntity;
import com.wta.NewCloudApp.jiuwei138940.main.goods.fragment.GoodsDetailFragment;
import com.wta.NewCloudApp.jiuwei138940.main.goods.fragment.GoodsInfoFragment;
import com.wta.NewCloudApp.jiuwei138940.main.netException.bean.retError;
import com.wta.NewCloudApp.jiuwei138940.main.netException.dataConverter.retConverter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


public class GoodsDetail_normal extends LatteDelegate {
    @BindView(R2.id.psts_tabs)
    public SlidingTabLayout psts_tabs;
    @BindView(R2.id.tv_title)
    public TextView title;
    @BindView(R2.id.vp_content)
    public NoScrollViewPager vp_content;
    private goodsDataConverter dataConverter = new goodsDataConverter();
    private List<Fragment> fragmentList = new ArrayList<>();
    private GoodsInfoFragment goodsInfoFragment;
    private GoodsDetailFragment goodsDetailFragment;
    private GoodsDetailEntity detailData;
    private int goodsId = 3;

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int selectedTabTextColor = 0xFF666666;
    private int selectedPosition = 0;

    public static GoodsDetail_normal newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final GoodsDetail_normal delegate = new GoodsDetail_normal();
        delegate.setArguments(args);
        return delegate;
    }


    public int getGoodsId() {
        return goodsId;
    }

    public NoScrollViewPager getVp_content() {
        return vp_content;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("create");
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            goodsId = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.goods_detail;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    //检查请求是否错误的bean
    private retError retData;

    private void initData() {
        RestClient.builder().url("?s=Category.getGoodsDetail")
                .params("id", goodsId)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        retConverter errorConverter = new retConverter();
                        retData = errorConverter.setJsonData(response).Convert();
                        if (retData.getData().getRet() == 0) {
                            dataConverter.setJsonData(response);
                            detailData = dataConverter.convert();
                            goodsInfoFragment = new GoodsInfoFragment();
                            goodsDetailFragment = new GoodsDetailFragment();
                            goodsInfoFragment.setData(detailData);
                            goodsInfoFragment.setParentFragment(GoodsDetail_normal.this);
                            goodsDetailFragment.setData(detailData);
                            fragmentList.add(goodsInfoFragment);
                            fragmentList.add(goodsDetailFragment);
                            vp_content.setAdapter(new ItemTitlePagerAdapter(getChildFragmentManager(), fragmentList,
                                    new String[]{"商品", "详情"}));
                            psts_tabs.setViewPager(vp_content);
                        }
                    }
                })
                .build()
                .get();

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
