package com.wta.NewCloudApp.jiuwei138940.main.goods.dialog;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.goods.adapter.dispatchAdapter;
import java.util.List;


public class detaildispatch {
    private final AlertDialog DIALOG;
    private final LatteDelegate DELEGATE;
    private List<String> data = null;
    private Window window = null;

    public detaildispatch(LatteDelegate delegate, List<String> data) {
        this.DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
        this.data = data;
    }

    public final void beginShareDialog() {
        DIALOG.show();
        window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_detail_dispatch);
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
            final RecyclerView mRecyclerview = (RecyclerView) window.findViewById(R.id.dispatch_recycler);
            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(DELEGATE.getContext());
            layoutManager.setFlexWrap(FlexWrap.WRAP);
            mRecyclerview.setLayoutManager(layoutManager);
            final dispatchAdapter mAdapter = new dispatchAdapter(R.layout.recy_dispatch, data);
            mRecyclerview.setAdapter(mAdapter);
            window.findViewById(R.id.goods_select_confim).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DIALOG.dismiss();
                }
            });

        }
    }
}
