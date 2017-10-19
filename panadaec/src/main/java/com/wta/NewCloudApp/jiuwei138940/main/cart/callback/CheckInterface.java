package com.wta.NewCloudApp.jiuwei138940.main.cart.callback;



public interface CheckInterface {

    /**
     * 组选框状态改变时触发的事件
     *
     * @param groupPosition 组元素位置
     * @param isChecked     组元素选中与否
     */
    void CheckGroup(int groupPosition, boolean isChecked);

    /**
     * 子选框状态改变时触发的事件
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    void CheckChild(int groupPosition, int childPosition, boolean isChecked);
}
