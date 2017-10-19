package com.wta.NewCloudApp.jiuwei138940.latte_core.ui.banner;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.image.GlideApp;



public class ImageHolder implements Holder<String>{

    private AppCompatImageView mImageview = null;

    @Override
    public View createView(Context context) {
        mImageview = new AppCompatImageView(context);
        mImageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return mImageview;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideApp.with(context)
                .load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .into(mImageview);

    }
}
