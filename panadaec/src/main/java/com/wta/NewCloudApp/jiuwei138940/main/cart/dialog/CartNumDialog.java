package com.wta.NewCloudApp.jiuwei138940.main.cart.dialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.le.customview.NumEditeText;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.IFailure;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.main.cart.adapter.ShopCartAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.cart.bean.GoodsInfo;


public class CartNumDialog {

    private GoodsInfo goodsInfo;
    private Context context;
    private int count;

    private TextView btadd, btreduce, cancle, sure;

    public CartNumDialog(Context context) {
        this.context = context;
    }

    public void showDialog(final GoodsInfo goodsInfo, final EditText editText, final ShopCartAdapter adapter) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        View alertDialogview = LayoutInflater.from(context).inflate(R.layout.dialog_change_num, null, false);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setView(alertDialogview);
        count = goodsInfo.getTotal();
        final NumEditeText et_num = (NumEditeText) alertDialogview.findViewById(R.id.et_num);
        final int startEdNum = goodsInfo.getTotal();
        et_num.setText("" + goodsInfo.getTotal());//设置dialog的数量初始值
        //自动弹出软键盘
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_num, InputMethodManager.RESULT_SHOWN);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                adapter.notifyDataSetChanged();
            }
        });
        initView(alertDialogview);
        et_num.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et_num.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!TextUtils.isEmpty(s.toString())) {//当输入的数字不为空时，更新数字
                                if (Integer.parseInt(s.toString()) == 0) {
                                    // goodsInfo.setTotal(goodsInfo.getTotal());
                                    et_num.setText(String.valueOf(startEdNum));
                                    Toast.makeText(context, "宝贝不能再少了哦", Toast.LENGTH_SHORT).show();
                                } else {
//                                    goodsInfo.setTotal(Integer.valueOf(s.toString().trim()));
                                }
                            }
                        }
                    });
                } else {
                    et_num.clearTextChangeListener();
                }
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                et_num.setText("" + count);
            }
        });
        btreduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    //数量大雨1时操作
                    count--; //点一下减1
                    et_num.setText("" + count);
                }
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                RestClient.builder().loader(context).
                        url("http://api.anmur.com/Public/?s=Cart.GetGoodsNumber")
                        .params("id", "40835")
                        .params("goodsid", goodsInfo.getId())
                        .params("num", et_num.getText().toString())
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                if (JSON.parseObject(response).getJSONObject("data").getInteger("info") == 1) {
                                    goodsInfo.setTotal(Integer.parseInt(et_num.getText().toString()));
                                    editText.setText(et_num.getText().toString() + "");
                                } else {
                                    Toast.makeText(context, "未知原因,操作失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
                                Toast.makeText(context, "网络问题,操作失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build().post();
            }
        });
        alertDialog.show();
    }

    private void initView(View view) {
        btadd = (TextView) view.findViewById(R.id.bt_add);
        btreduce = (TextView) view.findViewById(R.id.bt_reduce);
        cancle = (TextView) view.findViewById(R.id.tv_cancle);
        sure = (TextView) view.findViewById(R.id.tv_sure);
    }
}
