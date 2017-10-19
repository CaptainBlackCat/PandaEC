package com.wta.NewCloudApp.jiuwei138940.latte_core.ui.window;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import com.wta.NewCloudApp.jiuwei138940.latte_core.R;


public class Lattepopup {

    private static int width = WindowManager.LayoutParams.MATCH_PARENT;
    private static int Height = WindowManager.LayoutParams.WRAP_CONTENT;
    private View view;
    private static LattePopupWindow popupWindow = null;


    public static LattePopupWindow create(Context context, View contentView) {
        popupWindow = new LattePopupWindow(context);
        popupWindow.setContentView(contentView);
        initPopup();
        return popupWindow;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        Height = height;
    }


    private static void initPopup() {
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.setHeight(Height);
        popupWindow.setWidth(width);
        popupWindow.setOutsideTouchable(true);  //默认设置outside点击无响应
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
    }


}
