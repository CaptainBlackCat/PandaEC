package com.wta.NewCloudApp.jiuwei138940.main.me.setting;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.util.DateTimeUtils;
import com.wta.NewCloudApp.jiuwei138940.main.Constants;
import com.wta.NewCloudApp.jiuwei138940.main.callback.BaseResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.GaodeResponse;
import com.wta.NewCloudApp.jiuwei138940.main.callback.JsonCallback;
import com.wta.NewCloudApp.jiuwei138940.main.me.address.AddressDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.MyInfo;
import com.wta.NewCloudApp.jiuwei138940.main.me.bean.ProvinceBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;

/**
 * Created by zzc on 2017/9/15.
 */

public class MyInfoDelegate extends LatteDelegate {
    @BindView(R2.id.myinfo_address)
    LinearLayout mAddress;
    @BindView(R2.id.myinfo_editaddress)
    LinearLayout editaddress;
    @BindView(R2.id.mysetting_sex)
    LinearLayout selectsex;
    @BindView(R2.id.myinfo_date)
    LinearLayout mydate;
    @BindView(R2.id.mysetting_ziliao)
    LinearLayout mysettingZiliao;
    @BindView(R2.id.myinfo_tv_nickname)
    TextView myinfoTvNickname;
    @BindView(R2.id.mysetting_connectwx)
    LinearLayout mysettingConnectwx;
    @BindView(R2.id.myinfo_tv_birthday)
    TextView myinfoTvBirthday;
    @BindView(R2.id.myinfo_tv_sex)
    TextView myinfoTvSex;
    @BindView(R2.id.myinfo_tv_address)
    TextView myinfoTvAddress;
    @BindView(R2.id.myinfo_tv_area)
    TextView myinfoTvArea;
    @BindView(R2.id.myinfo_headview)
    ImageView myinfoHeadView;

    @BindView(R2.id.myinfo_save)
    TextView myinfoSave;
    private int i=1;
    private ProvinceBean mProvinceBean;
    private List<ProvinceBean.DistrictsBeanXX> options1Items = null;
    private List<List<ProvinceBean.DistrictsBeanXX.DistrictsBeanX>> options2Items = null;
    private List<ArrayList<ArrayList<ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean>>> options3Items = null;
    private ArrayList<String> strings;

    public static MyInfoDelegate newInstance() {

        Bundle args = new Bundle();

        MyInfoDelegate fragment = new MyInfoDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_myinfo;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initData();
    }

    protected void initData() {
        strings = new ArrayList<>();
        strings.add("男");
        strings.add("女");

        showMyInfo(i);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mActivity, "点击了", Toast.LENGTH_SHORT).show();
                loadCityData();

            }
        });
        editaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(AddressDelegate.newInstance());
            }
        });

        selectsex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSex();
            }
        });
        mydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateInfo();
            }
        });
        myinfoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                showMyInfo(i);
            }
        });
    }

    private void showSelectSex() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(_mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
              myinfoTvSex.setText(strings.get(options1));
            }
        }).setTitleText("性别选择")
                .setSubmitColor(0xFFDE3129)
                .setCancelColor(0xFFADADAD)
                .setDividerColor(0xFFADADAD)
                .setTextColorOut(0xFFADADAD)
                .setTitleColor(0xFF646464)
                .setTextColorCenter(0xFF646464)//设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(strings);
        pvOptions.show();
    }




    private void showMyInfo(int id) {
        Log.i("123", "showMyInfo: "+id);
        OkGo.<BaseResponse<MyInfo>>get(Constants.URL_MY_GETMYINFO)
                .params("id",id)
                .execute(new JsonCallback<BaseResponse<MyInfo>>() {
            @Override
            public void onSuccess(Response<BaseResponse<MyInfo>> response) {
                List<MyInfo.InfoBean> info = response.body().data.getInfo();
                if (info != null && info.size() == 1) {
                    MyInfo.InfoBean infoBean = info.get(0);
                    String address = infoBean.getProvince().trim() + infoBean.getCity().trim();
                    myinfoTvAddress.setText(address.length()<=0?"未填写":address);
                    String date = infoBean.getBirthyear() +"-" +infoBean.getBirthmonth() +"-" +infoBean.getBirthday();
                    myinfoTvBirthday.setText(date.length()<=2?"未填写":date);
                    myinfoTvNickname.setText(infoBean.getNickname()==null|| TextUtils.isEmpty(infoBean.getNickname())?"未填写":infoBean.getNickname());
                    myinfoTvSex.setText(infoBean.getGender()==null||TextUtils.isEmpty(infoBean.getGender())?"未填写":infoBean.getGender().equals("1")?"男":"女");
                    Glide.with(_mActivity).load(infoBean.getAvatar()).into(myinfoHeadView);

                } else {
                    Toast.makeText(_mActivity, "数据异常，请重新登录!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showSelectAddress() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(_mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getName() +
                        options2Items.get(options1).get(options2).getName() +
                        options3Items.get(options1).get(options2).get(options3).getName();
                myinfoTvAddress.setText(tx);

            }
        })
                .setTitleText("城市选择")
                .setSubmitColor(0xFFDE3129)
                .setCancelColor(0xFFADADAD)
                .setDividerColor(0xFFADADAD)
                .setTextColorOut(0xFFADADAD)
                .setTitleColor(0xFF646464)
                .setTextColorCenter(0xFF646464)//设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void loadCityData() {
        if (options1Items != null & options2Items != null & options3Items != null) {
            showSelectAddress();
        } else {
            OkGo.<GaodeResponse<ProvinceBean>>get(Constants.URL_GAODE_CITY)
                    .tag(ProvinceBean.class.getName())
                    .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                    .cacheTime(60 * 60 * 60 * 24 * 15)//缓存15天
                    .params("key", Constants.URL_GAODE_KEY)
                    .params("subdistrict", 3)
                    .execute(new JsonCallback<GaodeResponse<ProvinceBean>>() {
                        @Override
                        public void onSuccess(Response<GaodeResponse<ProvinceBean>> response) {
                            mProvinceBean = response.body().districts.get(0);
                            initOptionsItem(mProvinceBean);
                            showSelectAddress();
                        }

                        @Override
                        public void onCacheSuccess(Response<GaodeResponse<ProvinceBean>> response) {
                            super.onCacheSuccess(response);
                            mProvinceBean = response.body().districts.get(0);
                            initOptionsItem(mProvinceBean);
                            showSelectAddress();
                        }
                    });
        }

    }

    private void initOptionsItem(ProvinceBean mProvinceBean) {
        options1Items = new ArrayList<>();
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();
        if (mProvinceBean != null) {
            List<ProvinceBean.DistrictsBeanXX> provinces = mProvinceBean.getDistricts();
            options1Items = provinces;
            for (int i = 0; i < provinces.size(); i++) {
                //遍历所有省份
                ArrayList<ProvinceBean.DistrictsBeanXX.DistrictsBeanX> cityList = new ArrayList<>();//市集合
                ArrayList<ArrayList<ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean>> areaList = new ArrayList<>();//区集合
                if (provinces.get(i).getDistricts().size() == 0 || provinces.get(i).getDistricts() == null) {
                    ProvinceBean.DistrictsBeanXX.DistrictsBeanX e = new ProvinceBean.DistrictsBeanXX.DistrictsBeanX();
                    e.setName("");
                    cityList.add(e);
                    ArrayList<ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean> area = new ArrayList<>();//区集合
                    ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean a = new ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean();
                    a.setName("");
                    area.add(a);
                    areaList.add(area);
                } else {
                    for (int i1 = 0; i1 < provinces.get(i).getDistricts().size(); i1++) {
                        //遍历所有市
                        ArrayList<ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean> area = new ArrayList<>();//区集合
                        cityList.add(provinces.get(i).getDistricts().get(i1));
                        if (provinces.get(i).getDistricts().get(i1).getDistricts() == null || provinces.get(i).getDistricts().get(i1).getDistricts().size() == 0) {
                            ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean e = new ProvinceBean.DistrictsBeanXX.DistrictsBeanX.DistrictsBean();
                            e.setName("");
                            area.add(e);
                        } else {
                            for (int i2 = 0; i2 < provinces.get(i).getDistricts().get(i1).getDistricts().size(); i2++) {
                                //遍历所有区
                                area.add(provinces.get(i).getDistricts().get(i1).getDistricts().get(i2));
                            }
                        }
                        areaList.add(area);
                    }
                }
                options2Items.add(cityList);
                options3Items.add(areaList);
            }
        }
    }

    public void showDateInfo() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        TimePickerView timePickerView = new TimePickerView.Builder(_mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                myinfoTvBirthday.setText(DateTimeUtils.getFormatDateStr(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setTitleText("日期选择")
                .isCenterLabel(false)
                .setSubmitColor(0xFFDE3129)
                .setCancelColor(0xFFADADAD)
                .setDividerColor(0xFFADADAD)
                .setTextColorOut(0xFFADADAD)
                .setTitleColor(0xFF646464)
                .setRangDate(startDate, endDate)
                .setDate(endDate)
                .setTextColorCenter(0xFF646464)//设置选中项文字颜色
                .setContentSize(20).build();

        timePickerView.show();
    }

}
