package com.wta.NewCloudApp.jiuwei138940.latte_ui.timeline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Lenovo on 2017/9/4.
 */

public class TimeLine extends View {

    private int color_lien = 0xFFFFFFFF;
    private int color_bg = 0xFFF5F5F5;
    private int color_progress = 0xFFEF3030;
    private int color_text_top = 0xFF666666;
    private int color_text_buttom = 0xFF666666;
    private int mMaxProgress = 120;
    private int mCurrentProgress = 0;
    private int mWidth;
    private int mHeight;
    private Context mContext;
    private Paint mPaintTimeLine;
    private Paint mPaintProgress;
    private Paint mPaintTextTop;
    private Paint mPaintTextButtom;
    private Paint mPaintButton;
    private int mTopTextSize = 30;
    private int mButtomTextSize = 40;
    private float mLineWidth = 15;
    private OnClickListener onClickListener;
    private int mSize = 5;
    private float mRadius = 20;
    private int mAvgLength;
    private String[] mTextTops = {"积分", "积分", "积分", "积分", "积分"};
    private String[] mTextButtoms = {"7天", "15天", "30天", "60天", "90天"};

    public TimeLine(Context context) {
        this(context, null);
    }

    public TimeLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initPaint();
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

        mPaintTextTop = new Paint();
        mPaintTextTop.setColor(color_text_top);
        mPaintTextTop.setAntiAlias(true);
        mPaintTextTop.setTextSize(mTopTextSize);
        mPaintTextTop.setTextAlign(Paint.Align.CENTER);

        mPaintTextButtom = new Paint();
        mPaintTextButtom.setColor(color_text_buttom);
        mPaintTextButtom.setAntiAlias(true);
        mPaintTextButtom.setTextSize(mButtomTextSize);
        mPaintTextButtom.setTextAlign(Paint.Align.CENTER);

        mPaintButton = new Paint();
        mPaintButton.setColor(color_text_buttom);
        mPaintButton.setAntiAlias(true);
        mPaintButton.setStrokeWidth(3);
        mPaintButton.setStyle(Paint.Style.STROKE);
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

        mAvgLength = mWidth / (mSize + 1);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color_bg);
        drawTextTops(canvas);
        drawTimeLine(canvas);
        drawTextButtoms(canvas);
        drawButton(canvas);
        drawProgressLine(canvas);
    }

    private void drawButton(Canvas canvas) {
        for (int i = 0; i < mSize; i++) {
            mPaintButton.setColor(color_text_buttom);
            mPaintTextButtom.setColor(color_text_buttom);
            RectF rectF = new RectF(mAvgLength * (i + 1) - 2 * mTopTextSize, mHeight / 2 + 2 * mButtomTextSize + 10, mAvgLength * (i + 1) + 2 * mTopTextSize, mHeight / 2 + 4 * mButtomTextSize);
            canvas.drawRect(rectF, mPaintButton);
            canvas.drawText("领取", mAvgLength * (i + 1), mHeight / 2 + 7 * mButtomTextSize / 2, mPaintTextButtom);
        }
    }

    private void drawTextTops(Canvas canvas) {
        for (int i = 0; i < mSize; i++) {
            RectF rectF = new RectF(mAvgLength * (i + 1) - 2 * mTopTextSize, mHeight / 2 - 4 * mTopTextSize, mAvgLength * (i + 1) + 2 * mTopTextSize, mHeight / 2 - 3 * mTopTextSize / 2);
            canvas.drawRect(rectF, mPaintTimeLine);
            canvas.drawText("+10", mAvgLength * (i + 1), mHeight / 2 - 3 * mTopTextSize, mPaintTextTop);
            canvas.drawText(mTextTops[i], mAvgLength * (i + 1), mHeight / 2 - 2 * mTopTextSize, mPaintTextTop);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            for (int i = 0; i < mSize; i++) {
                if (x > mAvgLength * (i + 1) - 2 * mTopTextSize && x < mAvgLength * (i + 1) + 2 * mTopTextSize && y > mHeight / 2 + 2 * mButtomTextSize + 10 && y < mHeight / 2 + 4 * mButtomTextSize) {
                    if (onClickListener != null) {
                        onClickListener.click(i);
                    }
                }
            }
        }

        return true;
    }


    private void drawTextButtoms(Canvas canvas) {
        for (int i = 0; i < mSize; i++) {
            mPaintTextButtom.setColor(color_text_buttom);
            canvas.drawText(mTextButtoms[i], mAvgLength * (i + 1), mHeight / 2 + 3 * mButtomTextSize / 2, mPaintTextButtom);
        }
    }

    private void drawProgressLine(Canvas canvas) {
        float current = 0;
        mPaintButton.setColor(color_progress);
        mPaintTextButtom.setColor(color_progress);
        if(mCurrentProgress<7){
            current = ((float) (mCurrentProgress * 10000 / 15)) / 10000 * 2 * mAvgLength;
        }else if(mCurrentProgress>=7 && mCurrentProgress<15){
            current = ((float) (mCurrentProgress * 10000 / 15)) / 10000 * 2 * mAvgLength;
            put(canvas,0);
        }else if (mCurrentProgress < 15) {
            put(canvas,0);
            current = ((float) (mCurrentProgress * 10000 / 15)) / 10000 * 2 * mAvgLength;
        } else if (mCurrentProgress >= 15 && mCurrentProgress < 30) {
            put(canvas,0);
            put(canvas,1);
            current = 2 * mAvgLength + ((float) (mCurrentProgress - 15) * 10000) / 15 / 10000 * mAvgLength;
        } else if (mCurrentProgress >= 30 && mCurrentProgress<60) {
            put(canvas,0);
            put(canvas,1);
            put(canvas,2);
            current = 3 * mAvgLength + (float) ((mCurrentProgress - 30) * 10000) / 90 / 10000 * 3 * mAvgLength;
        }else if(mCurrentProgress >=60 && mCurrentProgress<90){
            current = 3 * mAvgLength + (float) ((mCurrentProgress - 30) * 10000) / 90 / 10000 * 3 * mAvgLength;
            put(canvas,0);
            put(canvas,1);
            put(canvas,2);
            put(canvas,3);
        }else if(mCurrentProgress >=90){
            put(canvas,0);
            put(canvas,1);
            put(canvas,2);
            put(canvas,3);
            put(canvas,4);
            current = 3 * mAvgLength + (float) ((mCurrentProgress - 30) * 10000) / 90 / 10000 * 3 * mAvgLength;
        }
        canvas.drawLine(0, mHeight / 2, current, mHeight / 2, mPaintProgress);
    }

    private void put(Canvas canvas,int i) {
        canvas.drawCircle(mAvgLength * (i + 1), mHeight / 2, mRadius, mPaintProgress);
        RectF rectF = new RectF(mAvgLength * (i + 1) - 2 * mTopTextSize, mHeight / 2 + 2 * mButtomTextSize + 10, mAvgLength * (i + 1) + 2 * mTopTextSize, mHeight / 2 + 4 * mButtomTextSize);
        canvas.drawRect(rectF, mPaintButton);
        canvas.drawText("领取", mAvgLength * (i + 1), mHeight / 2 + 7 * mButtomTextSize / 2, mPaintTextButtom);
    }

    private void drawTimeLine(Canvas canvas) {
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaintTimeLine);
        for (int i = 0; i < mSize; i++) {
            canvas.drawCircle(mAvgLength * (i + 1), mHeight / 2, mRadius, mPaintTimeLine);

        }
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setProgress(int progress) {
        this.mCurrentProgress = progress;
        this.invalidate();
    }

    public void setTextTops(String[] mTextTops) {
        this.mTextTops = mTextTops;
    }

    public void setButtoms(String[] mTextButtoms) {
        this.mTextButtoms = mTextButtoms;
        this.invalidate();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void click(int postion);
    }
}
