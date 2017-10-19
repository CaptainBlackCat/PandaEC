package com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.wta.NewCloudApp.jiuwei138940.latte_core.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.RxBus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.ISupportFragment;


public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final List<View> list=new ArrayList<>();
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;
    private OnClickPostionListener onClickPostionListener;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    public abstract LinkedHashMap<BottomTabBean,BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor()!=0){
            mClickedColor = setClickedColor();
        }


        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean,BottomItemDelegate> item:ITEMS.entrySet()){
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0;i<size;i++){
            //加载底部导航图标到父布局容器Bottombar里
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setImageResource(bean.getICON()[0]);
            itemTitle.setText(bean.getTITLE());
            if (i == mIndexDelegate){
                itemIcon.setImageResource(bean.getICON()[1]);
                itemTitle.setTextColor(mClickedColor);
            }
            list.add(item);
        }
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,delegateArray);

    }


    @Override
    public void onResume() {
        super.onResume();
        setOnLoginPostionEventListener();
    }

    private void setOnLoginPostionEventListener() {
        RxBus.getDefault().toObservable(LoginPostionEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginPostionEvent>() {
                    @Override
                    public void accept(LoginPostionEvent loginPostionEvent) throws Exception {
                        if(loginPostionEvent.isLogin){
                            onClick(list.get(loginPostionEvent.getNewPostion()));

                        }else{
                            onClick(list.get(loginPostionEvent.getOldPostion()));
                        }
                    }
                });
    }


    private void resetColor(){
            final int count = mBottomBar.getChildCount();
            for (int i = 0; i < count; i++) {
                final BottomTabBean bean = TAB_BEANS.get(i);
                final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
                final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
                itemIcon.setImageResource(bean.getICON()[0]);
                final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
                itemTitle.setTextColor(Color.GRAY);
            }
}


    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        if(mCurrentDelegate!=tag) {
            resetColor();
            final BottomTabBean bean = TAB_BEANS.get(tag);
            final RelativeLayout item = (RelativeLayout) v;
            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            itemIcon.setImageResource(bean.getICON()[1]);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(mClickedColor);
            itemTitle.setTag(mCurrentDelegate);
            getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
            //点击接口回调
            if (onClickPostionListener != null) {
                onClickPostionListener.clickPostion(mCurrentDelegate, tag);
            }
            //注意先后顺序
            mCurrentDelegate = tag;
        }
    }

    public interface OnClickPostionListener{
        void clickPostion(int oldPostion,int newPostion);
    }

    public void setOnClickPostionListener(OnClickPostionListener onClickPostionListener){
        this.onClickPostionListener=onClickPostionListener;
    }

    public static class LoginPostionEvent{
        private int oldPostion;
        private int newPostion;
        private boolean isLogin;

        public LoginPostionEvent() {
        }

        public LoginPostionEvent(int oldPostion, int newPostion, boolean isLogin) {
            this.oldPostion = oldPostion;
            this.newPostion = newPostion;
            this.isLogin = isLogin;
        }

        public int getOldPostion() {
            return oldPostion;
        }

        public void setOldPostion(int oldPostion) {
            this.oldPostion = oldPostion;
        }

        public int getNewPostion() {
            return newPostion;
        }

        public void setNewPostion(int newPostion) {
            this.newPostion = newPostion;
        }

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }
    }
}
