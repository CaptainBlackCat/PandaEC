package com.wta.NewCloudApp.jiuwei138940.main.index;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomItemDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.index.adapter.IndexDetailFragmentAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.bean.IndexTabBean;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.util.PreferencesUtility;
import java.util.List;
import butterknife.BindView;

;

/**
 * Created by Lenovo on 2017/8/29.
 */

public class HomeDelegate extends BottomItemDelegate {
    private static String token=null;
    @BindView(R2.id.home_main_tablayout)
    TabLayout homeMainTablayout;
    @BindView(R2.id.home_main_viewpager)
    ViewPager homeMainViewpager;
    @BindView(R2.id.search_left)
    TextView searchLeft;
    @BindView(R2.id.search_center)
    EditText searchCenter;
    @BindView(R2.id.search_right)
    TextView searchRight;
    private List<LatteDelegate> list;
    private EcBottomDelegate ecBottomDelegate;
    public HomeDelegate(EcBottomDelegate ecBottomDelegate) {
        this.ecBottomDelegate=ecBottomDelegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        if(token==null){
           token=PreferencesUtility.getInstance(_mActivity).getToken();
        }


        initView(rootView);
    }

    protected void initView(View view) {
            downLoadTabData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
//        ecBottomDelegate.setOnClickPostionListener(new BaseBottomDelegate.OnClickPostionListener() {
//            @Override
//            public void clickPostion(int oldPostion, int newPostion) {
//                if(newPostion==2 || newPostion==3 || newPostion==4){
//                    if(PreferencesUtility.getInstance(_mActivity).getToken()==null){
//                        HomeDelegate.this.getParentDelegate().start(LoginDelegate.newInstance(oldPostion,newPostion));
//                    }
//                }
//                Toast.makeText(_mActivity,"oldPostion="+oldPostion+",newPostion="+newPostion, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void downLoadTabData(){
        OkGo.<BaseResponse<IndexTabBean>>get(Constants.URL_GETINDEXDATA)
                .execute(new JsonCallback<BaseResponse<IndexTabBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<IndexTabBean>> response) {
                        List<IndexTabBean.InfoBean> info = response.body().data.getInfo();
                        searchCenter.setClickable(false);
                        searchCenter.setFocusable(false);
                        searchCenter.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(event.getAction()==MotionEvent.ACTION_UP){
                                    getParentDelegate().start(SearchDelegate.newInstance());
                                }
                                return true;
                            }
                        });

                        homeMainViewpager.setOffscreenPageLimit(info.size());
//                        homeMainViewpager.setAdapter(new IndexDetailFragmentAdapter(getChildFragmentManager(), info, list));
                        homeMainViewpager.setAdapter(new IndexDetailFragmentAdapter(getChildFragmentManager(), info));
                        homeMainTablayout.setupWithViewPager(homeMainViewpager);
                    }
                });
    }
}
