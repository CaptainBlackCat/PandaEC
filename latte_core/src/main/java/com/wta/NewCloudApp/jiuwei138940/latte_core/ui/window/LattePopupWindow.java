package com.wta.NewCloudApp.jiuwei138940.latte_core.ui.window;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.PopupWindow;
public class LattePopupWindow extends PopupWindow {


    private Context mContext;
    private float mShowAlpha = 0.88f;
    private Drawable mBackgroundDrawable;
    //调用popupwindow的父view的返回监听
    private View.OnKeyListener onBackListener = null;

    public LattePopupWindow(Context context) {
        this.mContext = context;
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        super.setOutsideTouchable(touchable);
        if (touchable) {
            if (mBackgroundDrawable == null) {
                mBackgroundDrawable = new ColorDrawable(0x00000000);
            }
            super.setBackgroundDrawable(mBackgroundDrawable);
        } else {
            super.setBackgroundDrawable(null);
        }
    }

    public View.OnKeyListener getOnBackListener() {
        return onBackListener;
    }

    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);
    }
}

