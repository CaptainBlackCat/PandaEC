package com.wta.NewCloudApp.jiuwei138940.main.me.setting;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.PhotoUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by zzc on 2017/9/15.
 */

public class RealNameDelegate extends LatteDelegate {

    @BindView(R2.id.realname_identity_ok1)
    ImageView realnameIdentityOk1;

    @BindView(R2.id.realname_identity_ok2)
    ImageView realnameIdentityOk2;
    @BindView(R2.id.realname_rl1)
    RelativeLayout realnameRl1;
    @BindView(R2.id.realname_rl2)
    RelativeLayout realnameRl2;

    private int currentimg=0;
    private int width;
    private int height;
    private String path;
    private List<ImageView> list;

    public static RealNameDelegate newInstance() {

        Bundle args = new Bundle();

        RealNameDelegate fragment = new RealNameDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_realname;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        realnameRl1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                realnameIdentityOk1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = realnameIdentityOk1.getHeight();
                width = realnameIdentityOk1.getWidth();
            }
        });
        list=new ArrayList<>();
        list.add(realnameIdentityOk1);
        list.add(realnameIdentityOk2);

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        realnameRl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentimg=0;
                showNiceDialog();
            }
        });

        realnameRl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentimg=1;
                showNiceDialog();
            }
        });
    }

    private void showNiceDialog() {
        NiceDialog.init().setLayoutId(R.layout.getphoto)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                        viewHolder.getView(R.id.getphoto_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baseNiceDialog.dismiss();
                            }
                        });
                        viewHolder.getView(R.id.getphoto_bytake).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PhotoUtil.photograph(RealNameDelegate.this);
                                baseNiceDialog.dismiss();
                            }
                        });
                        viewHolder.getView(R.id.getphoto_byalbum).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PhotoUtil.selectPictureFromAlbum(RealNameDelegate.this);
                                baseNiceDialog.dismiss();
                            }
                        });

                    }
                })
                .setDimAmount(0.3f)     //调节灰色背景透明度[0-1]，默认0.5f
                .setShowBottom(true)     //是否在底部显示dialog，默认flase
                .setOutCancel(true)     //点击dialog外是否可取消，默认true
                .show(_mActivity.getSupportFragmentManager());     //显示dialog
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PhotoUtil.NONE)
            return;
        if (requestCode == PhotoUtil.PHOTOGRAPH) {
            // 设置文件保存路径这里放在跟目录下
            File picture = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                picture = new File(Environment.getExternalStorageDirectory() + PhotoUtil.imageName);
                if (!picture.exists()) {
                    picture = new File(Environment.getExternalStorageDirectory() + PhotoUtil.imageName);
                }
            } else {
                picture = new File(_mActivity.getFilesDir() + PhotoUtil.imageName);
                if (!picture.exists()) {
                    picture = new File(_mActivity.getFilesDir() + PhotoUtil.imageName);
                }
            }

            path = PhotoUtil.getPath(_mActivity);// 生成一个地址用于存放剪辑后的图片
            if (TextUtils.isEmpty(path)) {
                return;
            }
            Uri imageUri = PhotoUtil.getUri(_mActivity, path);
            PhotoUtil.startPhotoZoom(RealNameDelegate.this, Uri.fromFile(picture), PhotoUtil.PICTURE_HEIGHT, PhotoUtil.PICTURE_WIDTH, imageUri);
        }

        if(requestCode==PhotoUtil.PHOTOZOOM){
            Toast.makeText(_mActivity, "相册", Toast.LENGTH_SHORT).show();
            path = PhotoUtil.getPath(_mActivity);
            if (TextUtils.isEmpty(path)) {
                return;
            }
            Uri imageUri = PhotoUtil.getUri(_mActivity, path);
            PhotoUtil.startPhotoZoom(RealNameDelegate.this, data.getData(),height,width, imageUri);
        }

        if(requestCode==PhotoUtil.PHOTORESOULT){
            Toast.makeText(_mActivity, "获取剪切图", Toast.LENGTH_SHORT).show();
            Bitmap bitmap = PhotoUtil.convertToBitmap(path,width-10, height-10);
            if(bitmap!=null){
                list.get(currentimg).setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
