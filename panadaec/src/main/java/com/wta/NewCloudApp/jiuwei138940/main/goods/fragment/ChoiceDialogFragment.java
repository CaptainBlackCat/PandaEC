package com.wta.NewCloudApp.jiuwei138940.main.goods.fragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.BigClassification;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.GoodsDetailEntity;
import com.wta.NewCloudApp.jiuwei138940.main.goods.listener.OnSelectedListener;
import com.wta.NewCloudApp.jiuwei138940.main.goods.widget.FlowLayout;
import com.wta.NewCloudApp.jiuwei138940.main.goods.widget.ShoppingSelectView;
import java.util.HashMap;
import java.util.List;


public class ChoiceDialogFragment extends android.support.v4.app.DialogFragment implements OnSelectedListener, View.OnClickListener {
    private View rootView;
    private ShoppingSelectView shopSelectview;
    private List<BigClassification> choiceData = null;
    private GoodsDetailEntity.DataBean detailData = null;
    private Dialog dialog = null;

    private GoodsInfoFragment GoodsInfofrag = null;
    /**
     * 全局变量使得增加和减少按钮的点击事件能获取到数字的大小
     */
    private EditText EditNum;
    /**
     * 先赋初始值
     */
    private HashMap<String, String> SelectedData = new HashMap<String, String>();
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflate = getActivity().getLayoutInflater();
        rootView = inflate.inflate(R.layout.dialog_detail_screen, null);
        if (dialog == null) {
            dialog = new Dialog(getActivity(), R.style.style_dialog);
            dialog.setContentView(rootView);
            dialog.show();
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
            window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            initListener();
            initData();
        }
        return dialog;
    }

    /**
     * 开放筛选图片更新方法
     *
     * @param thumbUrl
     */
    public void setDialogThumb(String thumbUrl) {
        if (thumbUrl != "" && !thumbUrl.isEmpty()) {
            Glide.with(getContext())
                    .load(thumbUrl)
                    .apply(OPTIONS)
                    .into((ImageView) rootView.findViewById(R.id.iv_shop_photo));
        }

    }

    private void initListener() {
        rootView.findViewById(R.id.goods_select_confim).setOnClickListener(this);
        ((ShoppingSelectView) rootView.findViewById(R.id.shopSelect)).setOnSelectedListener(this);
        rootView.findViewById(R.id.detail_add).setOnClickListener(this);
        rootView.findViewById(R.id.detail_reduce).setOnClickListener(this);
    }


    @Override
    public void onSelected(String title, String smallTitle, View view) {
        SelectedData.put(view.getTag(FlowLayout.POSITION_GROUP).toString(), view.getTag(FlowLayout.POSITION_CHILD).toString());
        SelectUpdateThumb();
    }


    /**
     * 筛选点击后更新筛选的图片
     */
    public void SelectUpdateThumb() {
        String data = "";
        if (SelectedData.size() == 2) {
            for (int i = 0; i < SelectedData.size(); i++) {
                data += detailData.getInfo().getSpeccon().get(i).getOptions().
                        get(Integer.parseInt(SelectedData.get(String.valueOf(i)))).getId();
                if (i != SelectedData.size() - 1) {
                    data += "_";
                }
            }
            for (int i = 0; i < detailData.getInfo().getOptions().size(); i++) {
                if (detailData.getInfo().getOptions().get(i).getSpecs().contains(data)) {
                    setDialogThumb(detailData.getInfo().getOptions().get(i).getThumb());
                    setDialogPrice(detailData.getInfo().getOptions().get(i).getMarketprice());
                    ((TextView) rootView.findViewById(R.id.detail_goods_name))
                            .setText(detailData.getInfo().getOptions().get(i).getTitle());
                }
            }
        }
    }

    private void setDialogPrice(String price) {
        rootView.findViewById(R.id.detail_goods_price).setTag("￥" + price);
    }

    public void setChoiceData(List<BigClassification> choiceData) {
        this.choiceData = choiceData;
    }

    public void setDetailData(GoodsDetailEntity.DataBean detailData) {
        this.detailData = detailData;
    }

    public void initData() {
        if (choiceData != null) {
            shopSelectview = (ShoppingSelectView) rootView.findViewById(R.id.shopSelect);
            shopSelectview.setData(choiceData);
            ((TextView) rootView.findViewById(R.id.detail_goods_price)).setText("￥" + detailData.getInfo().getMarketprice());
        }
        Glide.with(getContext())
                .load(detailData.getInfo().getThumb())
                .apply(OPTIONS)
                .into((ImageView) rootView.findViewById(R.id.iv_shop_photo));

        /**
         * 必须保证selecteddata不能为空
         */
        if (SelectedData.size() == 0) {
            for (int i = 0; i < choiceData.size(); i++) {
                SelectedData.put(String.valueOf(i), "0");
            }
        }
        EditNum = (EditText) rootView.findViewById(R.id.detail_num);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.goods_select_confim) {
            ChoiceDialogFragment.this.dismiss();
        } else if (view.getId() == R.id.detail_add) {
            int num = Integer.parseInt(String.valueOf(EditNum.getText()));
            EditNum.setText(String.valueOf(++num));
        } else if (view.getId() == R.id.detail_reduce) {
            int num = Integer.parseInt(String.valueOf(EditNum.getText()));
            if (num != 0)
                EditNum.setText(String.valueOf(--num));
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (GoodsInfofrag != null) {
            String title = null;
            String id = null;
            String data = "";
            for (int i = 0; i < SelectedData.size(); i++) {
                data += detailData.getInfo().getSpeccon().get(i).getOptions().
                        get(Integer.parseInt(SelectedData.get(String.valueOf(i)))).getId();
                if (i != SelectedData.size() - 1) {
                    data += "_";
                }
            }
            for (int i = 0; i < detailData.getInfo().getOptions().size(); i++) {
                if (detailData.getInfo().getOptions().get(i).getSpecs().contains(data)) {
                    title = detailData.getInfo().getOptions().get(i).getTitle();
                    id = detailData.getInfo().getOptions().get(i).getId();
                    break;
                }
            }
            title += " " + EditNum.getText() + "件";
            StringBuilder sb = new StringBuilder(title);
            sb.insert(0, "已选:");
            title = sb.toString();
            GoodsInfofrag.onSpecSet(title, id);
        }
        super.onDismiss(dialog);
    }

    public void setGoodsInfofrag(GoodsInfoFragment goodsInfofrag) {
        GoodsInfofrag = goodsInfofrag;
    }
}
