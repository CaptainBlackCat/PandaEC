package com.wta.NewCloudApp.jiuwei138940.main.me;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wta.NewCloudApp.jiuwei138940.R;
import com.wta.NewCloudApp.jiuwei138940.R2;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.pullRefreshLayout.QMUIPullRefreshLayout;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.signview.OKSignView;
import com.wta.NewCloudApp.jiuwei138940.latte_ui.timeline.OkTimeLine;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Lenovo on 2017/9/2.
 */

public class IntegralDelegate extends LatteDelegate {
    @BindView(R2.id.integral_signview)
    OKSignView integralSignview;
    @BindView(R2.id.integral_signbtn)
    TextView integralSign;
    @BindView(R2.id.pull_to_refresh)
    QMUIPullRefreshLayout mPullRefreshLayout;
    @BindView(R2.id.integral_timeline)
    OkTimeLine integralTimeline;
    @BindView(R2.id.integral_detailed_record)
    TextView mSignRecord;
    private int i = 1;
    private ArrayList<Integer> list;
//  @BindView(R.id.integral_signview)
//  SignView integralSignview;
//  private List<SignEntity> data;


    public static IntegralDelegate newInstance() {

        Bundle args = new Bundle();

        IntegralDelegate fragment = new IntegralDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_integral;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);

        integralSignview.setCurrentData(2017, 9, 10);
        integralSignview.setOnToDayClickListener(new OKSignView.OnToDayClickListener() {
            @Override
            public void ClickListener(int postion) {
                Toast.makeText(_mActivity, "" + postion, Toast.LENGTH_SHORT).show();
            }
        });

        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(8);
        integralSignview.setSigns(list);
//        Calendar calendarToday = Calendar.getInstance();
//        int dayOfMonthToday = calendarToday.get(Calendar.DAY_OF_MONTH);
//
//        data = new ArrayList<>();
//        Random ran = new Random();
//
//        for (int i = 0; i < 30; i++) {
//            SignEntity signEntity = new SignEntity();
//            if (dayOfMonthToday == i + 1)
//                signEntity.setDayType(2);
//            else
//                signEntity.setDayType((ran.nextInt(1000) % 2 == 0) ? 0 : 1);
//            data.add(signEntity);
//        }
//
//        SignAdapter signAdapter = new SignAdapter(data);
//        integralSignview.setAdapter(signAdapter);
    }

    @Override
    protected void initEvent() {

        mPullRefreshLayout.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                mPullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });

        mSignRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(SignRecordDelegate.newInstance());
            }
        });

        integralSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integralTimeline.setProgress(i);
                i++;

                integralSignview.sign("http://api.sgin", true, new OKSignView.OnSignListener() {
                    @Override
                    public void signSuccess(String msg) {
                        Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void signFailure(String msg) {
                        Toast.makeText(_mActivity, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    protected void initView(View view) {
        integralTimeline.setProgressArr(new int[]{10,20,30});
    }
}
