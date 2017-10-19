package com.wta.NewCloudApp.jiuwei138940.main.cart.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.le.customview.NumEditeText;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.GoodsInfo;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.StoreInfo;
import com.wta.NewCloudApp.jiuwei138940.main.cart.callback.CheckInterface;
import com.wta.NewCloudApp.jiuwei138940.main.cart.callback.GroupEditorListener;
import com.wta.NewCloudApp.jiuwei138940.main.cart.callback.ModifyCountInterface;
import com.wta.NewCloudApp.jiuwei138940.main.cart.dialog.CartNumDialog;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ShopCartAdapter extends BaseExpandableListAdapter {
    private List<StoreInfo> groups;
    private Map<String, List<GoodsInfo>> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private boolean flag = false;
    private boolean ischange = true;
    private GroupEditorListener mListener;
    int count = 0;

    public ShopCartAdapter(List<StoreInfo> groups, Map<String, List<GoodsInfo>> children, Context context) {
        this.groups = groups;
        this.children = children;
        this.context = context;
    }

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }


    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = groups.get(groupPosition).getId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<GoodsInfo> childs = children.get(groups.get(groupPosition).getId());
        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder gholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder = new GroupViewHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
        final StoreInfo group = (StoreInfo) getGroup(groupPosition);
        gholder.tv_store.setText(group.getMerchName());
        gholder.group_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setChoosed(((CheckBox) v).isChecked());
                checkInterface.CheckGroup(groupPosition, ((CheckBox) v).isChecked());//暴露组接口
            }
        });
        gholder.group_check.setChecked(group.isChoosed());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopcart_child, null);
            cholder = new ChildViewHolder(convertView);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }
        final GoodsInfo goodsInfo = (GoodsInfo) getChild(groupPosition, childPosition);

        if (goodsInfo != null) {
            cholder.tvIntro.setText(goodsInfo.getTitle());
            cholder.tvPirce.setText(String.valueOf(goodsInfo.getPrice()));
            ischange = true;
            final NumEditeText edNum = (NumEditeText) cholder.etNum;
            edNum.setImeOptions(EditorInfo.IME_ACTION_DONE);
            //一开始的商品数量
            final int startEdNum = goodsInfo.getTotal();
            edNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        CartNumDialog cartNumDialog = new CartNumDialog(context);
                        cartNumDialog.showDialog(goodsInfo, edNum, ShopCartAdapter.this);
                        edNum.clearFocus();
                    } else {
                    //    edNum.clearTextChangeListener();
                    }
                }
            });

            cholder.etNum.setText(goodsInfo.getTotal() + "");
            ischange = false;
            Glide.with(context)
                    .load(goodsInfo.getThumb())
                    .apply(OPTIONS)
                    .into(cholder.ivAdapterListPic);
            cholder.tvSpec.setText("");
            cholder.checkBox.setChecked(goodsInfo.isChoosed());
            cholder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
                    cholder.checkBox.setChecked(((CheckBox) v).isChecked());
                    checkInterface.CheckChild(groupPosition, childPosition, ((CheckBox) v).isChecked());//暴露子选接口
                }
            });
            cholder.btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked());// 暴露增加接口
                }
            });
            cholder.btReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked());// 暴露删减接口
                }
            });

        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        @BindView(R2.id.cart_group_check)
        CheckBox group_check;
        @BindView(R2.id.cart_storename)
        TextView tv_store;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R2.id.cart_child_check)
        CheckBox checkBox;
        @BindView(R2.id.cart_thumb)
        ImageView ivAdapterListPic;
        @BindView(R2.id.cart_title)
        TextView tvIntro;
        @BindView(R2.id.cart_price)
        TextView tvPirce;
        @BindView(R2.id.cart_spec)
        TextView tvSpec;
        @BindView(R2.id.cart_reduce)
        TextView btReduce;
        @BindView(R2.id.cart_add)
        TextView btAdd;
        @BindView(R2.id.calcucate_num)
        EditText etNum;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
