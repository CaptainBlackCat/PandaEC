package com.wta.NewCloudApp.jiuwei138940.latte_core.ui.share;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mob.MobSDK;
import com.wta.NewCloudApp.jiuwei138940.latte_core.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class ShareHandler implements View.OnClickListener, PlatformActionListener {

    private final AlertDialog DIALOG;
    private final LatteDelegate DELEGATE;
    private ShareEntity shareEntity;

    public void setShareEntity(ShareEntity shareEntity) {
        this.shareEntity = shareEntity;
    }

    public ShareHandler(LatteDelegate delegate) {
        this.DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public final void beginShareDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_cart_share);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            //弹出一个窗口，让背后的窗口变暗一点
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            //dialog背景层
            params.dimAmount = 0.5f;
            window.setAttributes(params);
            window.findViewById(R.id.share_wechat).setOnClickListener(this);
            window.findViewById(R.id.share_moment).setOnClickListener(this);
            window.findViewById(R.id.share_qq).setOnClickListener(this);
            window.findViewById(R.id.share_qzone).setOnClickListener(this);
            window.findViewById(R.id.share_sina).setOnClickListener(this);
            window.findViewById(R.id.share_cancel).setOnClickListener(this);
            window.findViewById(R.id.share_url).setOnClickListener(this);
            window.findViewById(R.id.share_sina).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        MobSDK.init(DELEGATE.getContext(), "200c295e61a8c", "5f19f599a706a391dc73e22b5748a8aa");
        Platform.ShareParams sp = new Platform.ShareParams();
        if (shareEntity != null) {
            sp.setTitle(shareEntity.getTitle());
            sp.setUrl(shareEntity.getUrl());
            sp.setText(shareEntity.getDescription());
            sp.setImageUrl(shareEntity.getThumb());
            sp.setTitleUrl(shareEntity.getUrl());
            sp.setSite("panada公社");
            sp.setSiteUrl(shareEntity.getUrl());
        }
        if (v.getId() == R.id.share_wechat) {
            sp.setShareType(Platform.SHARE_WEBPAGE);
            Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
            wechat.setPlatformActionListener(this); // 设置分享事件回调
            // 执行分享
            wechat.share(sp);
        } else if (v.getId() == R.id.share_qq) {
            Platform qq = ShareSDK.getPlatform(QQ.NAME);
            qq.setPlatformActionListener(this); // 设置分享事件回调
            // 执行分享
            qq.share(sp);
        } else if (v.getId() == R.id.share_moment) {
            sp.setShareType(Platform.SHARE_WEBPAGE);
            Platform moment = ShareSDK.getPlatform(WechatMoments.NAME);
            moment.setPlatformActionListener(this); // 设置分享事件回调
            // 执行分享
            moment.share(sp);
        } else if (v.getId() == R.id.share_qzone) {
            Platform qzone = ShareSDK.getPlatform(QZone.NAME);
            qzone.setPlatformActionListener(this); // 设置分享事件回调
            // 执行分享
            qzone.share(sp);
        } else if (v.getId() == R.id.share_url) {
            ClipboardManager cmb = (ClipboardManager) DELEGATE.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("Label", shareEntity.getUrl());
            cmb.setPrimaryClip(mClipData);
            DIALOG.dismiss();
            Toast.makeText(DELEGATE.getContext(), "复制成功", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.share_sina) {
            Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
            weibo.setPlatformActionListener(this); // 设置分享事件回调
            weibo.share(sp);
        } else if (v.getId() == R.id.share_cancel) {
            DIALOG.dismiss();
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Toast.makeText(DELEGATE.getContext(), "分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(DELEGATE.getContext(), "取消分享", Toast.LENGTH_SHORT).show();
    }
}
