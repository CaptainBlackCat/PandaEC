package com.wta.NewCloudApp.jiuwei138940.latte_ui.signview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.wta.NewCloudApp.jiuwei138940.latte_ui.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Zzc on 2017/9/2.
 */

public class OKSignView extends View {

    private  int color_bg=0xFFFFFFFF;
    private  int color_line=0xFFDDDDDD;
    private  int color_sign=0xFFEF3030;
    private  int color_text=0xFF333333;
    private Paint paintBg;
    private Paint paintLine;
    private Paint paintSign;
    private Paint paintText;

    private int mCurrentDay=-1;

    private int mWidth;
    private int mHeigh;
    private int mSizeWidh;
    private int maxDate=31;
    private int textSize=50;
    private Context mContext;

    private OnToDayClickListener onToDayClickListener;
    private List<Integer> mSigns;
    private boolean isSignSuccess;
    private int mSizeHeigh;

    public OKSignView(Context context) {
        this(context,null);
    }

    public OKSignView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OKSignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OKSignView);
        color_line=typedArray.getColor(R.styleable.OKSignView_dividerColor,0xFFDDDDDD);
        color_sign=typedArray.getColor(R.styleable.OKSignView_signColor,0xFFEF3030);
        color_text=typedArray.getColor(R.styleable.OKSignView_textColor,0xFF333333);
        color_bg=typedArray.getColor(R.styleable.OKSignView_bgColor,0xFFFFFFFF);
        textSize=typedArray.getDimensionPixelSize(R.styleable.OKSignView_textSize,50);
        typedArray.recycle();
        mContext=context;
        initPaint();
    }

    private void initPaint() {
        paintBg = new Paint();
        paintBg.setAntiAlias(true);
        paintBg.setColor(color_bg);
        paintBg.setTextAlign(Paint.Align.CENTER);

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(color_line);
        paintLine.setStrokeWidth(3);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setTextAlign(Paint.Align.CENTER);

        paintSign = new Paint();
        paintSign.setAntiAlias(true);
        paintSign.setColor(color_sign);
        paintSign.setStrokeWidth(10);
        paintSign.setTextAlign(Paint.Align.CENTER);

        paintText=new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setColor(color_text);
        paintText.setTextSize(textSize);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color_bg);
        drawDivider(canvas);
        drawDate(canvas);

        if(mCurrentDay!=-1){
            drawCurrentDay(canvas);
        }

        if(mSigns!=null) {
            drawSign(canvas);
        }

    }

    private void drawCurrentDay(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
               if(mCurrentDay==((7*j)+i+1)){
                   RectF rectF=new RectF(i*mSizeWidh,j*mSizeHeigh,(i+1)*mSizeWidh,(j+1)*mSizeHeigh);
                   paintLine.setColor(color_sign);
                   canvas.drawRect(rectF,paintLine);
               }
            }
        }
    }

    private void drawSign(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                for (int i1 = 0; i1 < mSigns.size(); i1++) {
                    if(((7*j)+i+1)==mSigns.get(i1)){
                        canvas.drawCircle(i*mSizeWidh+mSizeWidh/2,j*mSizeHeigh+mSizeHeigh/2,mSizeWidh/4,paintSign);
                        paintText.setColor(color_bg);
                        canvas.drawText("" + ((7 * j) + i + 1), i * mSizeWidh + mSizeWidh / 2, j * mSizeHeigh + mSizeHeigh / 5 * 3, paintText);
                    }
                }
            }
        }
    }

    public void setSigns(List<Integer> list){
        this.mSigns=list;
        this.invalidate();
    }

    private void drawDate(Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if(((7*j)+i)<maxDate){
                    Log.i("AAA", "drawDate: "+((7*j)+i));
                    if(mCurrentDay!=-1 && mCurrentDay==((7*j)+i)+1){
                        paintText.setColor(color_sign);
                        canvas.drawText("" + ((7 * j) + i + 1), i * mSizeWidh + mSizeWidh / 2, j * mSizeHeigh + mSizeHeigh / 5 * 3, paintText);
                    }else {
                        paintText.setColor(color_text);
                        canvas.drawText("" + ((7 * j) + i + 1), i * mSizeWidh + mSizeWidh / 2, j * mSizeHeigh + mSizeHeigh / 5 * 3, paintText);
                    }
                }
            }
        }
    }


    private void drawDivider(Canvas canvas) {
        int startH=0;
        int startV=mSizeHeigh;

        for (int i = 0; i < 6; i++) {
            paintLine.setColor(color_line);
            //画6条横线
            canvas.drawLine(startH,i*startV,mWidth,i*startV,paintLine);

            //画6条竖线

            canvas.drawLine((i+1)*mSizeWidh,startH+20,(i+1)*mSizeWidh,mHeigh-20,paintLine);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeigh=MeasureSpec.getMode(heightMeasureSpec);

        if(modeWidth==MeasureSpec.EXACTLY){
            mWidth=MeasureSpec.getSize(widthMeasureSpec);
        }else{
            WindowManager windowManager= (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            mWidth=outMetrics.widthPixels;
        }

        mSizeWidh=mWidth/7;

        if(modeHeigh==MeasureSpec.EXACTLY){
            mHeigh=MeasureSpec.getSize(heightMeasureSpec);
        }else{
            mHeigh=mSizeWidh*5;
        }

        mSizeHeigh = mHeigh / 5;
        setMeasuredDimension(mWidth,mHeigh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){
            float x = event.getX();
            float y = event.getY();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 5; j++) {
                    if((x>i*mSizeWidh && (i+1)*mSizeWidh>x) && (y>j*mSizeHeigh && mSizeHeigh*(j+1)>y)){
                        if(onToDayClickListener!=null){
                            onToDayClickListener.ClickListener((7*j)+i);
                        }
                    }
                }
            }
        }
        return true;
    }

    public void setOnToDayClickListener(OnToDayClickListener onToDayClickListener){
        this.onToDayClickListener=onToDayClickListener;
    }

    public interface OnToDayClickListener{
        void ClickListener(int postion);
    }


    public void setCurrentData(int year,int month,int day){
        this.mCurrentDay=day;
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        this.invalidate();
    }

    public void sign(String url,boolean test,OnSignListener onSignListener){
        if(mCurrentDay!=-1){
            //请求服务签到接口
            if(test){
                onSignListener.signSuccess("成功");
                mSigns.add(mCurrentDay);
                this.invalidate();
            }else{
                onSignListener.signFailure("失败");
            }

        }else{
            new RuntimeException("没有获取到当前日期，请先调用setCurrentData()方法，设置当前日期。");
        }
    }



    public interface OnSignListener{
        void signSuccess(String msg);

        void signFailure(String msg);
    }
}
