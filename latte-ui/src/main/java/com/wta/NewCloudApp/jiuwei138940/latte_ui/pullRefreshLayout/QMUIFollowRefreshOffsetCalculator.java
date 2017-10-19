package com.wta.NewCloudApp.jiuwei138940.latte_ui.pullRefreshLayout;


/**
 * RefreshView 永远和 TargetView 保持一定的距离(这个距离由刷新时RefreshView居中算出)
 *
 * @author cginechen
 * @date 2017-06-07
 */

public class QMUIFollowRefreshOffsetCalculator implements QMUIPullRefreshLayout.RefreshOffsetCalculator {

    @Override
    public int calculateRefreshOffset(int refreshInitOffset, int refreshEndOffset, int refreshViewHeight, int targetCurrentOffset, int targetInitOffset, int targetRefreshOffset) {
        int distance = targetRefreshOffset / 2 + refreshViewHeight / 2;
        int max = targetCurrentOffset - refreshViewHeight;
        return Math.min(max, targetCurrentOffset - distance);
    }
}
