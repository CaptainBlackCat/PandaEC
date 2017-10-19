package com.wta.NewCloudApp.jiuwei138940.latte_core.util;
import android.content.Context;
import android.widget.Toast;
public class ToastUtil {

    public static boolean ResponseCheck(Context context, String response) {
        if (response == null && response.isEmpty()) {
            Toast.makeText(context,"数据获取失败",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
