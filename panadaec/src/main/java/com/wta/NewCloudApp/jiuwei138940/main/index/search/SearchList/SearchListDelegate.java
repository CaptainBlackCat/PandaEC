package com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.RestClient;
import com.wta.NewCloudApp.jiuwei138940.latte_core.net.callback.ISuccess;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleFields;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.refresh.PagingBean;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.window.LattePopupWindow;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.window.Lattepopup;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList.content.Search_RightAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList.list.Search_LeftAdapter;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchListDataConverter;
import com.wta.NewCloudApp.jiuwei138940.main.sort.content.SectionDataConverter;
import com.wta.NewCloudApp.jiuwei138940.main.sort.list.VerticalListDataConverter;
import com.wta.NewCloudApp.jiuwei138940.main.sort.list.VerticalListDelegate;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import butterknife.BindView;


public class SearchListDelegate extends LatteDelegate implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, View.OnKeyListener, PopupWindow.OnDismissListener {
    @BindView(R2.id.search_list)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.search_mode)
    CheckBox search_mode = null;
    @BindView(R2.id.search_sales)
    CheckBox search_sales = null;
    @BindView(R2.id.search_price)
    CheckBox search_price = null;
    @BindView(R2.id.s_pirce_ll)
    LinearLayout ll_price = null;
    @BindView(R2.id.ppp)
    TextView ll_price_tv = null;
    @BindView(R2.id.gone_price)
    CheckBox gone_price = null;
    @BindView(R2.id.search_screen)
    LinearLayout ll_screen = null;
    @BindView(R2.id.screening_tv)
    TextView ll_screen_tv = null;
    @BindView(R2.id.mask)
    View graymask = null;
    private String mContentUrl = null;
    private int mid = 0;
    private Integer type = 10;
    private Boolean priceTag = true;
    private Boolean screenTag = true;
    private static final String ARG_CONTENT_URL = "CONTENT_URL";
    private static final String TYPE = "LIST_TYPE";
    private static final String ARG_ID = "ID";
    private static final int KEYWORD_LIST = 1;
    private static final int SORT_LIST = 2;
    private int ListType = 20;
    private LayoutInflater mInflater = null;

    private SearchListDataConverter searchDataConverter = new SearchListDataConverter();
    private String keyword;
    private final PagingBean BEAN = new PagingBean();
    private searchListAdapter madapter = null;
    private SearchRefreshHandler searchRefreshHandler = null;
    private View Screenview = null;
    private Set<Integer> mulitpleScreen = null;

    //搜索关键词
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Object setLayout() {
        return R.layout.list_search;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View rootview = getView();
        if (rootview != null) {
            rootview.setFocusableInTouchMode(true);
            rootview.requestFocus();
            rootview.setOnKeyListener(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            type = args.getInt(TYPE);
            mContentUrl = args.getString(ARG_CONTENT_URL);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
        search_sales.setOnCheckedChangeListener(this);
        search_mode.setOnCheckedChangeListener(this);
        mRecyclerView.addOnItemTouchListener(SearchListener.create(this));
        ll_price.setOnClickListener(this);
        ll_screen.setOnClickListener(this);
    }

    public static SearchListDelegate newInstance(String url, int type) {
        final Bundle args = new Bundle();
        args.putString(ARG_CONTENT_URL, url);
        args.putInt(TYPE, type);
        final SearchListDelegate delegate = new SearchListDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
        Screenview = LayoutInflater.from(getContext()).inflate(R.layout.search_screening, null);
        initPopup();
    }

    private void initData() {
        //noinspection unchecked
        searchRefreshHandler = SearchRefreshHandler.create(null, mRecyclerView, searchDataConverter, getContext(), 1);
        WeakHashMap<String, Object> params = new WeakHashMap<>();
        searchRefreshHandler.setMparams(params);
        if (type == KEYWORD_LIST) {
            searchRefreshHandler.setRefreshYield("?s=Category.getGoodsList&keyword=" + keyword);
        } else if (type == SORT_LIST) {
            searchRefreshHandler.setRefreshYield(mContentUrl);
        }
        searchRefreshHandler.firstPage();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        final int id = buttonView.getId();
        if (id == R.id.search_sales) {
            if (isChecked == true) {
                search_sales.setTextColor(getResources().getColorStateList(R.color.text_red));
                searchRefreshHandler.putmParams("sale", 1);
            } else {
                search_sales.setTextColor(getResources().getColorStateList(R.color.black_333));
                searchRefreshHandler.putmParams("sale", 2);
            }

            gone_price.setVisibility(View.VISIBLE);
            search_price.setVisibility(View.GONE);
            ll_price_tv.setTextColor(Color.rgb(69, 69, 69));
            searchRefreshHandler.removeParams("price");
            searchRefreshHandler.firstPage();
            mRecyclerView.scrollTo(0, 0);
        } else if (id == R.id.search_mode) {
            if (isChecked == true) {
                searchDataConverter.setType(10);
                searchDataConverter.setSpansize(1);
                searchRefreshHandler.getLoadMoreDataConverter().setSpansize(1);
                searchRefreshHandler.getLoadMoreDataConverter().setType(10);
            } else if (isChecked == false) {
                searchDataConverter.setType(20);
                searchDataConverter.setSpansize(2);
                searchRefreshHandler.getLoadMoreDataConverter().setSpansize(2);
                searchRefreshHandler.getLoadMoreDataConverter().setType(20);
            }
            searchRefreshHandler.firstPage();
        }

    }

    private LattePopupWindow popupWindow = null;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_screen) {
            ll_screen.getChildAt(1).performClick();
            ll_screen_tv.setTextColor(Color.rgb(242, 48, 48));
            if (screenTag == true) {
                popupWindow = Lattepopup.create(getContext(), Screenview);
                popupWindow.showAsDropDown(ll_screen);
                graymask.setVisibility(View.VISIBLE);
                popupWindow.setOnDismissListener(this);
                screenTag = false;
            }
        } else if (v.getId() == R.id.s_pirce_ll) {
            gone_price.setVisibility(View.GONE);
            search_price.setVisibility(View.VISIBLE);
            ll_price.getChildAt(1).performClick();
            ll_price_tv.setTextColor(Color.rgb(242, 48, 48));
            if (priceTag == false) {
                searchRefreshHandler.putmParams("price", 2);
                priceTag = true;
            } else {
                searchRefreshHandler.putmParams("price", 1);
                priceTag = false;
            }
            search_sales.setChecked(false);
            searchRefreshHandler.removeParams("sale");
            searchRefreshHandler.firstPage();
            mRecyclerView.scrollTo(0, 0);
        }
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (popupWindow != null) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    screenTag = true;
                    popupWindow = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onDismiss() {
        ll_screen.getChildAt(1).performClick();
        ll_screen_tv.setTextColor(Color.rgb(69, 69, 69));
        screenTag = true;
        graymask.setVisibility(View.GONE);
    }

    private String ScreenUrl = null;
    private VerticalListDelegate listDelegate;
    private ArrayList<MultipleItemEmity> leftData = null;
    private Search_LeftAdapter leftAdapter = null;
    private ArrayList<MultipleItemEmity> righData = null;
    private Search_RightAdapter rightAdapter = null;

    private void initPopup() {
        final ListView rightView = (ListView) Screenview.findViewById(R.id.sort_content);
        final ListView leftView = (ListView) Screenview.findViewById(R.id.sort_list);
        final TextView screen_cancle = (TextView) Screenview.findViewById(R.id.screen_cancle);
        final TextView screen_confim = (TextView) Screenview.findViewById(R.id.screen_confim);
        final TagFlowLayout flowscreen = (TagFlowLayout) Screenview.findViewById(R.id.screen_tagFlow);
        screen_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null)
                    popupWindow.dismiss();
            }
        });
        screen_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null)
                    popupWindow.dismiss();
                if (ScreenUrl != null)
                    searchRefreshHandler.setUrl(ScreenUrl);
                removeScreenTop();
                if (mulitpleScreen != null && mulitpleScreen.size() > 0) {
                    Iterator data = mulitpleScreen.iterator();
                    Integer ScreenChoice = 0;
                    while (data.hasNext()) {
                        ScreenChoice = (Integer) data.next();
                        switch (ScreenChoice) {
                            case 0:
                                searchRefreshHandler.putmParams("isrecommand", 1);
                                break;
                            case 1:
                                searchRefreshHandler.putmParams("isnew", 1);
                                break;
                            case 2:
                                searchRefreshHandler.putmParams("ishot", 1);
                                break;
                            case 3:
                                searchRefreshHandler.putmParams("isdiscount", 1);
                                break;
                            case 4:
                                searchRefreshHandler.putmParams("issendfree", 1);
                                break;
                            case 5:
                                searchRefreshHandler.putmParams("istime", 1);
                                break;
                            default:
                                break;
                        }
                    }
                }
                searchRefreshHandler.firstPage();
                mRecyclerView.scrollTo(0, 0);
            }
        });
        RestClient.builder()
                .url("?s=category.getgoodssort&parentid=0")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        leftData = new VerticalListDataConverter().setJsonData(response).convert();
                        leftAdapter = new Search_LeftAdapter(getContext(), R.layout.search_sort, leftData);
                        leftView.setAdapter(leftAdapter);
                    }
                })
                .build().get();
        leftView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftAdapter.setSelectedPosition(position);
                leftAdapter.notifyDataSetChanged();
                RestClient.builder()
                        .url((String) leftData.get(position).getField(MultipleFields.URL))
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                righData = new SectionDataConverter().setJsonData(response).convert();
                                rightAdapter = new Search_RightAdapter(getContext(), righData);
                                rightView.setAdapter(rightAdapter);
                                rightView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        rightAdapter.setSelectedPosition(position);
                                        rightAdapter.notifyDataSetChanged();
                                        ScreenUrl = righData.get(position).getField(MultipleFields.URL);
                                    }
                                });
                            }
                        }).build().get();
            }
        });
        String[] mVals = new String[]{"推荐商品", "新品上市", "热卖商品", "促销商品", "卖家包邮", "限时抢购"};
        final ViewGroup viewGroup = (ViewGroup) Screenview.findViewById(R.id.screen_tagFlow);
        flowscreen.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.screen_btn, viewGroup, false);
                tv.setText(s);
                return tv;
            }
        });

        flowscreen.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                mulitpleScreen = selectPosSet;
            }
        });

    }



    private void removeScreenTop() {
        if (searchRefreshHandler.getRefreshYield() != null) {
            searchRefreshHandler.removeParams("isrecommand");
            searchRefreshHandler.removeParams("isnew");
            searchRefreshHandler.removeParams("ishot");
            searchRefreshHandler.removeParams("isdiscount");
            searchRefreshHandler.removeParams("issendfree");
            searchRefreshHandler.removeParams("istime");
        }

    }
}
