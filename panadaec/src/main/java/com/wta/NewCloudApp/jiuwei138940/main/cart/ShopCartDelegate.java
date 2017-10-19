package com.wta.NewCloudApp.jiuwei138940.main.cart;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.le.customview.SExpanListView;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.bottom.BottomItemDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.main.EcBottomDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.cart.adapter.ShopCartAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.CartEntity;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.GoodsInfo;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.StoreInfo;
import com.wta.NewCloudApp.jiuwei138940.main.cart.callback.CheckInterface;
import com.wta.NewCloudApp.jiuwei138940.main.cart.callback.ModifyCountInterface;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList.SearchListener;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList.SearchRefreshHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ShopCartDelegate extends BottomItemDelegate implements CheckInterface, ModifyCountInterface {
    @BindView(R2.id.message)
    ImageView message = null;
    @BindView(R2.id.edit)
    CheckBox edit = null;
    @BindView(R2.id.tv_total_price)
    TextView tv_total_price;
    @BindView(R2.id.tv_go_to_pay)
    TextView tv_go_to_pay;
    @BindView(R2.id.ll_info)
    LinearLayout cartInfo = null;
    @BindView(R2.id.ll_shar)
    LinearLayout cartMultiple = null;
    @BindView(R2.id.all_checkbox)
    CheckBox allCheckbox;
    @BindView(R2.id.cart_recommand)
    RecyclerView recommandRecycler;
    private EcBottomDelegate ecBottomDelegate;
    public ShopCartDelegate(EcBottomDelegate ecBottomDelegate) {
        this.ecBottomDelegate=ecBottomDelegate;
    }

    @OnClick(R2.id.ll_share)
    void onClick() {
//        ShareHandler shareHandler = new ShareHandler(ShopCartDelegate.this);
//        shareHandler.beginShareDialog();
    }

    private double totalPrice = 0.00;//购买得商品总价
    private int totalCount = 0;//购买的商品总数量
    private boolean editStatus = false;
    private ShopCartAdapter selva;
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
    private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();// 子元素数据列表
    private RecProductDataConverter dataConverter = new RecProductDataConverter();
    private SExpanListView exListView;//购物车商品列表

    private ShopCartDataConverter shopCartDataConverter = new ShopCartDataConverter();
    private SearchRefreshHandler searchRefreshHandler = null;
    private CartEntity cartEntity;
    private View cartExpandHeader = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(_mActivity, "sds", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cartInfo.setVisibility(View.GONE);
                    cartMultiple.setVisibility(View.VISIBLE);
                } else {
                    cartInfo.setVisibility(View.VISIBLE);
                    cartMultiple.setVisibility(View.GONE);
                }
            }
        });
        tv_go_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mActivity,"jiesuan",Toast.LENGTH_SHORT).show();
            }
        });

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recommandRecycler.setLayoutManager(manager);
        //屏蔽动画效果
        recommandRecycler.setItemAnimator(null);
    }


    private void initData() {
        RestClient.builder()
                .loader(getContext())
                .url("http://192.168.3.59/Public/?s=Cart.GetCartGoods&token=2729581e3c2aa7d5687b1c248fa47362")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        cartEntity = shopCartDataConverter.setJsonData(response).convert();
                        groups = shopCartDataConverter.getGroups();
                        children = shopCartDataConverter.getChildren();
                        initEvents();
                    }
                })
                .build()
                .get();
        recommandRecycler.addOnItemTouchListener(SearchListener.create(this));
        searchRefreshHandler = SearchRefreshHandler.create(null, recommandRecycler, dataConverter,
                getContext(), 1);
        cartExpandHeader = View.inflate(getContext(), R.layout.cart_expandlist, null);
        //设置为缩略图的显示模式
        dataConverter.setSpansize(1);
        dataConverter.setType(10);
        dataConverter.clearData();
        searchRefreshHandler.setLoadMoreDataConverter(new RecProductDataConverter());
        searchRefreshHandler.getLoadMoreDataConverter().setSpansize(1);
        searchRefreshHandler.getLoadMoreDataConverter().setType(10);
        searchRefreshHandler.setRefreshYield("http://192.168.3.59/Public/?s=Cart.GetCartTJ");
        searchRefreshHandler.putmParams("token", "2729581e3c2aa7d5687b1c248fa47362");
        searchRefreshHandler.addheaderView(cartExpandHeader);
        searchRefreshHandler.firstPage();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    private void initEvents() {
        exListView = (SExpanListView) cartExpandHeader.findViewById(R.id.cart_list);
        selva = new ShopCartAdapter(groups, children, getContext());
        selva.setCheckInterface(this);
        selva.setModifyCountInterface(this);
        exListView.setAdapter(selva);
        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }


    }

    private boolean isAllCheck() {

        for (StoreInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    @Override
    public void CheckGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allCheckbox.setChecked(true);
        else
            allCheckbox.setChecked(false);
        selva.notifyDataSetChanged();
        calcuate();
    }

    @Override
    public void CheckChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            //不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            allCheckbox.setChecked(true);
        } else {
            allCheckbox.setChecked(false);
        }
        selva.notifyDataSetChanged();
        calcuate();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, final View showCountView, boolean isChecked) {
        final GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getTotal();
        currentCount++;
        final int finalCurrentCount = currentCount;
        RestClient.builder()
                .loader(getContext())
                .url("http://192.168.3.59/Public/?s=Cart.GetGoodsNumber&token=2729581e3c2aa7d5687b1c248fa47362")
                .params("goodsid", product.getId())
                .params("num", currentCount)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if (JSON.parseObject(response).getJSONObject("data").getInteger("info") == 1) {
                            product.setTotal(finalCurrentCount);
                            ((TextView) showCountView).setText(finalCurrentCount + "");
                            selva.notifyDataSetChanged();
                            calcuate();
                        } else {
                            Toast.makeText(getContext(), "未知原因,操作失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build().post();

    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, final View showCountView, boolean isChecked) {
        final GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getTotal();
        if (currentCount == 1)
            return;
        currentCount--;
        Log.d("product", String.valueOf(currentCount));
        final int finalCurrentCount = currentCount;
        RestClient.builder().loader(getContext())
                .url("http://192.168.3.59/Public/?s=Cart.GetGoodsNumber")
                .params("token", "2729581e3c2aa7d5687b1c248fa47362")
                .params("goodsid", product.getId())
                .params("num", currentCount)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if (JSON.parseObject(response).getJSONObject("data").getInteger("info") == 1) {
                            product.setTotal(finalCurrentCount);
                            ((TextView) showCountView).setText(finalCurrentCount + "");
                            selva.notifyDataSetChanged();
                            calcuate();
                        } else {
                            Toast.makeText(getContext(), "未知原因,操作失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build().post();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {

    }


    private String url = "";
    public boolean isSuccessful = false;

    public void doDelete() {
        final List<StoreInfo> toBeDeleteGroups = new ArrayList<>();//待删除的组元素
        final WeakHashMap<String,Object> urlParams=new WeakHashMap<>();
        Observable<String> observable = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                for (int i = 0; i < groups.size(); i++) {
                    StoreInfo group = groups.get(i);
                    if (group.isChoosed()) {
                        toBeDeleteGroups.add(group);
                    }
                    List<GoodsInfo> toBeDeleteProducts = new ArrayList<>();// 待删除的子元素列表
                    List<GoodsInfo> childs = children.get(group.getId());
                    for (int j = 0; j < childs.size(); j++) {
                        if (childs.get(j).isChoosed()) {
                            toBeDeleteProducts.add(childs.get(j));
                            urlParams.put("goodsid",childs.get(j).getId());

                        }
                    }
                    if (urlParams.size()!= 0) {
                        String response = RestClient.builder().url("http://192.168.3.59/Public/?s=Cart.DeleteCartGoods")
                                .params("token","2729581e3c2aa7d5687b1c248fa47362")
                                .params(urlParams)
                                .build().postSync();
                        if (response == RestClient.REQUEST_ERROR) {
                            observer.onError(new Throwable(RestClient.REQUEST_ERROR));

                        } else if (JSON.parseObject(response).getJSONObject("data").getInteger("info") == 1) {
                            childs.removeAll(toBeDeleteProducts);
                            groups.removeAll(toBeDeleteGroups);
                            i--;
                            isSuccessful = true;
                        } else {
                            observer.onError(new Throwable("请求失败"));
                        }
                    }
                    urlParams.clear();
                }
                if (isSuccessful) {
                    observer.onComplete();
                }
            }
        };
        Observer<String> deleteobserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull String response) {
                onComplete();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e.getMessage().contains(RestClient.REQUEST_ERROR))
                    Toast.makeText(_mActivity, "因网络原因，删除失败", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(_mActivity, "未知原因删除失败，请稍后重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                // groups.removeAll(toBeDeleteGroups);
                //记得重新设置购物车
                selva.notifyDataSetChanged();
            }
        };
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(deleteobserver);


    }


    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calcuate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                GoodsInfo product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getTotal();
                }
            }
        }
        tv_total_price.setText("￥" + totalPrice);
        //计算购物车的金额为0时候清空购物车的视图
        tv_go_to_pay.setText("结算(" + totalCount + ")");
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allCheckbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(allCheckbox.isChecked());
            }
        }
        selva.notifyDataSetChanged();

        calcuate();
    }

    @OnClick({R2.id.all_checkbox, R2.id.ll_delete})
    public void onClick(View view) {
        AlertDialog alert;
        if (view.getId() == R.id.all_checkbox) {
            doCheckAll();
        } else if (view.getId() == R.id.ll_delete) {
            if (totalCount == 0) {
                Toast.makeText(getContext(), "请选择要移除的商品", Toast.LENGTH_SHORT).show();
                return;
            }
            alert = new AlertDialog.Builder(getContext()).create();
            alert.setTitle("操作提示");
            alert.setMessage("您确定要将这些商品从购物车中移除吗？");
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            doDelete();
                            tv_total_price.setText("￥" + "0.0");
                            //计算购物车的金额为0时候清空购物车的视图
                            tv_go_to_pay.setText("结算(0)");
                        }
                    });
            alert.show();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        selva = null;
        groups.clear();
        totalPrice = 0;
        totalCount = 0;
        children.clear();
    }
}
