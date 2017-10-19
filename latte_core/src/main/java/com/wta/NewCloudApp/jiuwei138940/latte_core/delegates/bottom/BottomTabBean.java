package com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom;



public final class BottomTabBean {
    private final int[] ICON;
    private final CharSequence TITLE;

    public BottomTabBean(int[] icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public int[] getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
