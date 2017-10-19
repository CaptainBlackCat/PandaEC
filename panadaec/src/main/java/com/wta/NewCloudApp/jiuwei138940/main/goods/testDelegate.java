package com.wta.NewCloudApp.jiuwei138940.main.goods;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomItemDelegate;
import butterknife.BindView;


public class testDelegate extends BottomItemDelegate implements View.OnClickListener {
    @BindView(R2.id.goods_start)
    Button buttons;
    private String data;

    @Override
    public Object setLayout() {
        return R.layout.test_goods;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        buttons.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        GoodsDetail_normal goodsDetail = GoodsDetail_normal.newInstance(3);
        getParentDelegate().getSupportDelegate().start(goodsDetail);
    }
}
