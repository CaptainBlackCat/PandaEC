package com.wta.NewCloudApp.jiuwei138940.main.callback;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.loading.LatteLoader;

/**
 * Created by zzc on 2017/9/22.
 */

public abstract class LoadingJsonCallback<T> extends JsonCallback<T> {

    private FragmentActivity mActivity;
    public LoadingJsonCallback(FragmentActivity activity){
        mActivity=activity;

    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        LatteLoader.showLoading(mActivity);
    }

    @Override
    public void onSuccess(Response<T> response) {
        LatteLoader.stopLoading();
    }


    @Override
    public void onFinish() {
        super.onFinish();
        LatteLoader.stopLoading();
    }

    @Override
    public void onError(Response response) {
        super.onError(response);
        Toast.makeText(mActivity, "数据加载错误！", Toast.LENGTH_SHORT).show();
    }
}
