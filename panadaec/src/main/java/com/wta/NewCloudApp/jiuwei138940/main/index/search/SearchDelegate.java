package com.wta.NewCloudApp.jiuwei138940.main.index.search;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_core.ui.recycler.MultipleItemEmity;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.NormalUtils;
import com.wta.NewCloudApp.jiuwei138940.latte_core.util.storage.LattePreference;
import com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList.SearchListDelegate;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchDelegate extends LatteDelegate implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {
    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerview = null;
    @BindView(R2.id.search_center)
    AppCompatEditText mSearchEdit = null;
    private SearchAdapter adapter = null;
    private List<MultipleItemEmity> data = null;
    private SearchDataConverter searchDataConverter = new SearchDataConverter();
    private static final int KEYWORD_LIST = 1;
    private static final int SORT_LIST = 2;
    public static SearchDelegate newInstance() {

        Bundle args = new Bundle();

        SearchDelegate fragment = new SearchDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R2.id.search_btn)

    void onClickSearch() {
        final String searchItemText = mSearchEdit.getText().toString();
        saveItem(searchItemText);
        mSearchEdit.setText("");
        SearchListDelegate msearchListDelegate = SearchListDelegate.newInstance("", KEYWORD_LIST);
        msearchListDelegate.setKeyword(searchItemText);
        getSupportDelegate().startWithPop(msearchListDelegate);
    }

    @OnClick(R2.id.searchv_back)
    void onClickback() {
        getSupportDelegate().pop();
    }


    @SuppressWarnings("unchecked")
    private void saveItem(String item) {
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)) {
            List<String> history;
            final String historystr =
                    LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historystr)) {
                history = new ArrayList<>();
            } else {
                history = JSON.parseObject(historystr, ArrayList.class);
            }
            history.add(item);
            NormalUtils.rmRepeadtedElementByOrder((ArrayList<String>) history);
            final String json = JSON.toJSONString(history);
            LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mSearchEdit.setFocusable(true);
        mSearchEdit.setFocusableInTouchMode(true);
        mSearchEdit.requestFocus();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(manager);
        data = searchDataConverter.convert();
        adapter = new SearchAdapter(data);
        mRecyclerview.setAdapter(adapter);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                if (position == adapter.getData().size()) {
                    return null;
                } else {
                    return new Divider.Builder()
                            .size(2)
                            .margin(20, 20)
                            .color(Color.GRAY)
                            .build();
                }
            }
        });
        View view = LayoutInflater.from(getContext()).inflate(R.layout.history_delete, null);
        adapter.addFooterView(view);
        view.setOnClickListener(this);
        mRecyclerview.addItemDecoration(itemDecoration);
        adapter.setOnItemClickListener(this);
        //监听键盘搜索按键
        mSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onClickSearch();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<String> history;
        final String historystr =
                LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
        if (StringUtils.isEmpty(historystr)) {
            history = new ArrayList<>();
        } else {
            //noinspection unchecked
            history = JSON.parseObject(historystr, ArrayList.class);
        }
        String contentKeyword = history.get(position);
        SearchListDelegate msearchListDelegate = SearchListDelegate.newInstance("", KEYWORD_LIST);
        msearchListDelegate.setKeyword(contentKeyword);
        getSupportDelegate().startWithPop(msearchListDelegate);
    }

    @Override
    public void onClick(View v) {
        LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, "");
        data.clear();
        adapter.notifyDataSetChanged();
    }
}
