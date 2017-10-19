package com.wta.NewCloudApp.jiuwei138940.latte_ui.timeline;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.wta.NewCloudApp.jiuwei138940.latte_ui.R;

/**
 * Created by Lenovo on 2017/9/4.
 */

public class OkTimeLine extends View {

    private int color_lien = 0xFFFFFFFF;
    private int color_bg = 0xFFF5F5F5;
    private int color_progress = 0xFFEF3030;
    private int[] mProgressArr={7,15,30,60,90};
    private int mCurrentProgress = 0;
    private int mWidth;
    private int mHeight;
    private Context mContext;
    private Paint mPaintTimeLine;
    private Paint mPaintProgress;


    private float mLineWidth = 15;
    private float mRadius = 20;
    private int mAvgLength;
;

    public OkTimeLine(Context context) {
        this(context, null);
    }

    public OkTimeLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OkTimeLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initAttrs(context,attrs);
        initPaint();
    }

    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OkTimeLine);
        color_progress=typedArray.getColor(R.styleable.OkTimeLine_progressColor,0xFFEF3030);
        mLineWidth=typedArray.getDimension(R.styleable.OkTimeLine_timeLineWidth,15);
        mRadius=typedArray.getDimension(R.styleable.OkTimeLine_radius,20);
        color_lien=typedArray.getColor(R.styleable.OkTimeLine_timeLienColor,0xFFFFFFFF);
    }

    private void initPaint() {

        mPaintTimeLine = new Paint();
        mPaintTimeLine.setColor(color_lien);
        mPaintTimeLine.setAntiAlias(true);
        mPaintTimeLine.setStrokeWidth(mLineWidth);

        mPaintProgress = new Paint();
        mPaintProgress.setColor(color_progress);
        mPaintProgress.setAntiAlias(true);
        mPaintProgress.setStrokeWidth(mLineWidth);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(metrics);
            mWidth = metrics.widthPixels;
        }

        if (heighMode == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            mHeight = mWidth / 2;
        }
        mAvgLength = mWidth / (mProgressArr.length + 1);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color_bg);
        drawTimeLine(canvas);
        drawProgressLine(canvas);
    }






    private void drawProgressLine(Canvas canvas) {
        float current = 0;

        for (int i = 0; i <mProgressArr.length ; i++) {
            if (mCurrentProgress>=mProgressArr[i]){
                if(i==0){
                    canvas.drawCircle(mAvgLength*(i+1) , mHeight / 2, mRadius, mPaintProgress);
                    current=(i+1)*mAvgLength+mRadius+((float)((mCurrentProgress-mProgressArr[i])*100)/(mProgressArr[i+1]-mProgressArr[i]-1)/100)*(mAvgLength-2*mRadius);

                }else if(i==mProgressArr.length-1){
                    current=mAvgLength*mProgressArr.length+mRadius+(float)((mCurrentProgress-mProgressArr[mProgressArr.length-1])*100)/(mProgressArr[mProgressArr.length-1]-mProgressArr[mProgressArr.length-2])/100*(mAvgLength-mRadius);
                }else{
                    current=(i+1)*mAvgLength+mRadius+((float)((mCurrentProgress-mProgressArr[i])*100)/(mProgressArr[i+1]-mProgressArr[i]-1)/100)*(mAvgLength-2*mRadius);
                }
                canvas.drawCircle(mAvgLength*(i+1) , mHeight / 2, mRadius, mPaintProgress);
            }else if (mCurrentProgress<mProgressArr[0]){
                current= (float)(mCurrentProgress*100/(mProgressArr[0]-1))/100*(mAvgLength-mRadius);
            }
        }

        canvas.drawLine(0, mHeight / 2, current, mHeight / 2, mPaintProgress);

    }


    private void drawTimeLine(Canvas canvas) {
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaintTimeLine);
        for (int i = 0; i < mProgressArr.length; i++) {
            canvas.drawCircle(mAvgLength * (i + 1), mHeight / 2, mRadius, mPaintTimeLine);
        }
    }


    public void setProgress(int progress) {
        this.mCurrentProgress = progress;
        this.invalidate();
    }

    public void setProgressArr(int[] progressArr){
        this.mProgressArr=progressArr;
    }
}
